@echo off

:: Enable necessary extensions
@setlocal enableextensions

echo Get the current directory
set "currentDir=%CD%"

echo Change the current working directory to the script directory
@cd /d "%~dp0"

:: Set project directory name
set "PROJECT_DIR=pomodorotimer-app"

echo Delete the "docs" folder and its contents
if exist "%PROJECT_DIR%\target\site\coverxygen" rd /S /Q "%PROJECT_DIR%\target\site\coverxygen"
if exist "%PROJECT_DIR%\target\site\coveragereport" rd /S /Q "%PROJECT_DIR%\target\site\coveragereport"
if exist "%PROJECT_DIR%\target\site\doxygen" rd /S /Q "%PROJECT_DIR%\target\site\doxygen"

echo Delete and Create the "release" folder and its contents
if exist "release" rd /S /Q "release"
mkdir release 2>nul

echo Change directory to %PROJECT_DIR%
if not exist "%PROJECT_DIR%" (
    echo ERROR: Project directory "%PROJECT_DIR%" not found!
    pause
    exit /b 1
)
cd %PROJECT_DIR%

echo Perform Maven clean, test, and packaging
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo WARNING: Maven (mvn) is not installed or not in PATH. Skipping Maven commands.
    echo Please install Maven or add it to your PATH.
) else (
    call mvn clean test package
    if %ERRORLEVEL% NEQ 0 (
        echo WARNING: Maven build failed, but continuing...
    )
)

echo Return to the previous directory
cd ..

echo Create Required Folders coverxygen/coveragereport/doxygen
if not exist "%PROJECT_DIR%\target" mkdir "%PROJECT_DIR%\target"
if not exist "%PROJECT_DIR%\target\site" mkdir "%PROJECT_DIR%\target\site"
if not exist "%PROJECT_DIR%\target\site\coverxygen" mkdir "%PROJECT_DIR%\target\site\coverxygen"
if not exist "%PROJECT_DIR%\target\site\coveragereport" mkdir "%PROJECT_DIR%\target\site\coveragereport"
if not exist "%PROJECT_DIR%\target\site\doxygen" mkdir "%PROJECT_DIR%\target\site\doxygen"

echo Generate Doxygen HTML and XML Documentation
where doxygen >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo WARNING: Doxygen is not installed or not in PATH. Skipping Doxygen generation.
) else (
    if exist "Doxyfile" (
        call doxygen Doxyfile
    ) else (
        echo WARNING: Doxyfile not found. Skipping Doxygen generation.
    )
)

echo Change directory to %PROJECT_DIR%
cd %PROJECT_DIR%

echo Generate ReportGenerator HTML Report
where reportgenerator >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo WARNING: ReportGenerator is not installed or not in PATH. Skipping ReportGenerator.
) else (
    if exist "target\site\jacoco\jacoco.xml" (
        call reportgenerator "-reports:target\site\jacoco\jacoco.xml" "-sourcedirs:src\main\java" "-targetdir:target\site\coveragereport" -reporttypes:Html
    ) else (
        echo WARNING: Jacoco report not found. Skipping ReportGenerator HTML report.
    )
)

echo Generate ReportGenerator Badges
where reportgenerator >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo WARNING: ReportGenerator is not installed or not in PATH. Skipping badges.
) else (
    if exist "target\site\jacoco\jacoco.xml" (
        call reportgenerator "-reports:target\site\jacoco\jacoco.xml" "-sourcedirs:src\main\java" "-targetdir:target\site\coveragereport" -reporttypes:Badges
    ) else (
        echo WARNING: Jacoco report not found. Skipping badges.
    )
)

echo Display information about the binary file
echo Our Binary is a Single Jar With Dependencies. You Do Not Need to Compress It.

echo Return to the previous directory
cd ..

echo Run Coverxygen
where python >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo WARNING: Python is not installed or not in PATH. Skipping Coverxygen.
) else (
    if exist "%PROJECT_DIR%\target\site\doxygen\xml" (
        call python -m coverxygen --xml-dir ./%PROJECT_DIR%/target/site/doxygen/xml --src-dir ./ --format lcov --output ./%PROJECT_DIR%/target/site/coverxygen/lcov.info --prefix %currentDir%/%PROJECT_DIR%/
    ) else (
        echo WARNING: Doxygen XML directory not found. Skipping Coverxygen.
    )
)

echo Run lcov genhtml
where perl >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo WARNING: Perl is not installed or not in PATH. Skipping lcov genhtml.
) else (
    if exist "%PROJECT_DIR%\target\site\coverxygen\lcov.info" (
        call perl C:\ProgramData\chocolatey\lib\lcov\tools\bin\genhtml --legend --title "Documentation Coverage Report" ./%PROJECT_DIR%/target/site/coverxygen/lcov.info -o %PROJECT_DIR%/target/site/coverxygen
    ) else (
        echo WARNING: lcov.info file not found. Skipping genhtml.
    )
)

echo Copy badge files to the "assets" directory
if not exist "assets" mkdir "assets"
if exist "%PROJECT_DIR%\target\site\coveragereport\badge_combined.svg" (
    copy "%PROJECT_DIR%\target\site\coveragereport\badge_combined.svg" "assets\badge_combined.svg" >nul 2>&1
    copy "%PROJECT_DIR%\target\site\coveragereport\badge_branchcoverage.svg" "assets\badge_branchcoverage.svg" >nul 2>&1
    copy "%PROJECT_DIR%\target\site\coveragereport\badge_linecoverage.svg" "assets\badge_linecoverage.svg" >nul 2>&1
    copy "%PROJECT_DIR%\target\site\coveragereport\badge_methodcoverage.svg" "assets\badge_methodcoverage.svg" >nul 2>&1
) else (
    echo WARNING: Badge files not found. Skipping badge copy.
)

if exist "assets\rteu_logo.jpg" (
    if not exist "%PROJECT_DIR%\src\site\resources\images" mkdir "%PROJECT_DIR%\src\site\resources\images"
    copy "assets\rteu_logo.jpg" "%PROJECT_DIR%\src\site\resources\images\rteu_logo.jpg" >nul 2>&1
)

echo Copy the "assets" folder and its contents to "maven site images" recursively
if exist "assets" (
    if not exist "%PROJECT_DIR%\src\site\resources\assets" mkdir "%PROJECT_DIR%\src\site\resources\assets"
    call robocopy assets "%PROJECT_DIR%\src\site\resources\assets" /E /NFL /NDL /NJH /NJS >nul 2>&1
) else (
    echo WARNING: assets folder not found. Skipping assets copy.
)

echo Copy the "README.md" file to "%PROJECT_DIR%\src\site\markdown\readme.md"
if exist "README.md" (
    if not exist "%PROJECT_DIR%\src\site\markdown" mkdir "%PROJECT_DIR%\src\site\markdown"
    copy README.md "%PROJECT_DIR%\src\site\markdown\readme.md" >nul 2>&1
) else (
    echo WARNING: README.md not found. Skipping README copy.
)

cd %PROJECT_DIR%
echo Perform Maven site generation
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo WARNING: Maven (mvn) is not installed or not in PATH. Skipping Maven site.
) else (
    call mvn site
)
cd ..

echo Package Output Jar Files
if exist "%PROJECT_DIR%\target\*.jar" (
    powershell -Command "Compress-Archive -Path '%PROJECT_DIR%\target\*.jar' -DestinationPath 'release\application-binary.zip' -Force"
    echo Created: release\application-binary.zip
) else (
    echo WARNING: No JAR files found. Skipping JAR packaging.
)

echo Package Jacoco Test Coverage Report (Optional)
if exist "%PROJECT_DIR%\target\site\jacoco" (
    powershell -Command "Compress-Archive -Path '%PROJECT_DIR%\target\site\jacoco\*' -DestinationPath 'release\test-jacoco-report.zip' -Force"
    echo Created: release\test-jacoco-report.zip
) else (
    echo WARNING: Jacoco report directory not found. Skipping Jacoco packaging.
)

echo Package ReportGenerator Test Coverage Report
if exist "%PROJECT_DIR%\target\site\coveragereport" (
    powershell -Command "Compress-Archive -Path '%PROJECT_DIR%\target\site\coveragereport\*' -DestinationPath 'release\test-coverage-report.zip' -Force"
    echo Created: release\test-coverage-report.zip
) else (
    echo WARNING: Coverage report directory not found. Skipping coverage report packaging.
)

echo Package Code Documentation
if exist "%PROJECT_DIR%\target\site\doxygen" (
    powershell -Command "Compress-Archive -Path '%PROJECT_DIR%\target\site\doxygen\*' -DestinationPath 'release\application-documentation.zip' -Force"
    echo Created: release\application-documentation.zip
) else (
    echo WARNING: Doxygen documentation directory not found. Skipping documentation packaging.
)

echo Package Documentation Coverage
if exist "%PROJECT_DIR%\target\site\coverxygen" (
    powershell -Command "Compress-Archive -Path '%PROJECT_DIR%\target\site\coverxygen\*' -DestinationPath 'release\doc-coverage-report.zip' -Force"
    echo Created: release\doc-coverage-report.zip
) else (
    echo WARNING: Documentation coverage directory not found. Skipping doc coverage packaging.
)

echo Package Product Site
if exist "%PROJECT_DIR%\target\site" (
    powershell -Command "Compress-Archive -Path '%PROJECT_DIR%\target\site\*' -DestinationPath 'release\application-site.zip' -Force"
    echo Created: release\application-site.zip
) else (
    echo WARNING: Site directory not found. Skipping site packaging.
)

echo ....................
echo Operation Completed!
echo ....................
echo.
echo NOTE: Some operations may have been skipped due to missing dependencies.
echo Check the warnings above for details.
echo.
pause
