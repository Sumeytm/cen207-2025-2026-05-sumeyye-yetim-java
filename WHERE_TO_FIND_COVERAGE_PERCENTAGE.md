# Where to Find Coverage Percentage

This guide shows you exactly where to look for the coverage percentage in the report.

---

## Location 1: Top of the Page (Main Summary)

When you open `index.html`, **at the very top** you'll see a table that looks like this:

```
┌─────────────────────────────────────────────────────────┐
│ Element          │ Missed │ Covered │ Coverage          │
├──────────────────┼────────┼─────────┼───────────────────┤
│ Instructions     │ 123    │ 456     │ 78.8%            │
│ Branches         │ 45     │ 67      │ 59.8%            │
│ Lines            │ 89     │ 234     │ 72.4%            │
│ Methods          │ 12     │ 45      │ 78.9%            │
│ Classes          │ 2      │ 14      │ 87.5%            │
└─────────────────────────────────────────────────────────┘
```

**Look for the "Coverage" column** - that's your percentage!

### What Each Row Means:
- **Instructions**: Code instructions coverage
- **Branches**: If/else branches coverage
- **Lines**: Line coverage (most important!)
- **Methods**: Method coverage
- **Classes**: Class coverage

**Most Important**: Look at **"Lines"** row - that's usually what people mean by "coverage percentage"

---

## Location 2: Package Summary Table

**Scroll down a bit** and you'll see a table with packages:

```
┌─────────────────────────────────────────────────────────┐
│ Package                          │ Coverage             │
├──────────────────────────────────┼──────────────────────┤
│ com.pomodorotimer.pomodorotimer  │ 85.2%                │
└─────────────────────────────────────────────────────────┘
```

This shows coverage for each package.

---

## Location 3: Individual Class Coverage

**Click on a package name** to see classes:

```
┌─────────────────────────────────────────────────────────┐
│ Class              │ Coverage │ Missed │ Covered        │
├────────────────────┼──────────┼────────┼────────────────┤
│ DoubleLinkedList   │ 95.5%    │ 2      │ 42             │
│ XORLinkedList      │ 87.3%    │ 5      │ 34             │
│ Stack              │ 100%     │ 0      │ 28             │
└─────────────────────────────────────────────────────────┘
```

This shows coverage for each class.

---

## Visual Guide: What to Look For

### Step 1: Open the Report
- File: `index.html`
- Opens in your browser

### Step 2: Look at the Top
- **First thing you see** = Summary table
- **Rightmost column** = Coverage percentage
- **"Lines" row** = Most important percentage

### Step 3: Check the Number
- Example: **"72.4%"** in the Lines row
- That's your overall coverage!

---

## Example Screenshot Description

When you look at the page, you should see something like:

```
JaCoCo Code Coverage Report

[Summary Table]
Element          Coverage
─────────────────────────
Instructions     78.8%
Branches         59.8%
Lines            72.4%  ← THIS IS YOUR MAIN COVERAGE!
Methods          78.9%
Classes          87.5%
```

**The "Lines" percentage (e.g., 72.4%) is your main coverage percentage!**

---

## Color Coding

The percentages might be color-coded:
- 🟢 **Green**: Good coverage (80%+)
- 🟡 **Yellow**: Medium coverage (50-80%)
- 🔴 **Red**: Low coverage (<50%)

---

## Quick Check

1. **Open the report** (index.html)
2. **Look at the very top** of the page
3. **Find the table** with "Element", "Missed", "Covered", "Coverage"
4. **Look at the "Lines" row**
5. **Read the percentage** in the "Coverage" column
6. **That's your coverage!**

---

## What Percentage Should You Have?

According to the PDF:
- **Minimum**: 80% (for passing)
- **Ideal**: 100% (for full points)
- **Current setting**: 80% minimum in pom.xml

---

## If You Can't Find It

1. **Make sure you're looking at the top** of the page
2. **Scroll up** if needed
3. **Look for a table** with numbers
4. **Check the browser title** - should say "JaCoCo" or "Coverage"

---

## Next Steps After Finding Percentage

1. **Note your current percentage**
2. **If below 80%**: Add more tests
3. **If 80-100%**: Good! But aim for 100%
4. **If 100%**: Perfect! ✅

---

## Screenshot for Final Report

Take a screenshot of:
- The summary table at the top
- Make sure the "Lines" coverage percentage is visible
- This will go in your final report!

Good luck! 🎯

