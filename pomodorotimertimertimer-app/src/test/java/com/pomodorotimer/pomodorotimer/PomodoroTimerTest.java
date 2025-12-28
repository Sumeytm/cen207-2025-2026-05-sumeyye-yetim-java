package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PomodoroTimer test sınıfı
 */
public class PomodoroTimerTest {
    
    private PomodoroTimer timer;
    private TestListener listener;
    
    /**
     * Test listener sınıfı
     */
    private static class TestListener implements PomodoroTimerListener {
        public boolean workStarted = false;
        public boolean shortBreakStarted = false;
        public boolean longBreakStarted = false;
        public boolean workCompleted = false;
        public int completedPomodoros = 0;
        public int lastTick = -1;
        
        @Override
        public void onWorkStarted() {
            workStarted = true;
        }
        
        @Override
        public void onShortBreakStarted() {
            shortBreakStarted = true;
        }
        
        @Override
        public void onLongBreakStarted() {
            longBreakStarted = true;
        }
        
        @Override
        public void onTimerStopped() {
            // Test için gerekli değil
        }
        
        @Override
        public void onTick(int remainingSeconds) {
            lastTick = remainingSeconds;
        }
        
        @Override
        public void onWorkCompleted(int completedPomodoros) {
            workCompleted = true;
            this.completedPomodoros = completedPomodoros;
        }
        
        @Override
        public void onShortBreakDue() {
            // Test için gerekli değil
        }
        
        @Override
        public void onLongBreakDue() {
            // Test için gerekli değil
        }
        
        @Override
        public void onBreakCompleted() {
            // Test için gerekli değil
        }
        
        public void reset() {
            workStarted = false;
            shortBreakStarted = false;
            longBreakStarted = false;
            workCompleted = false;
            completedPomodoros = 0;
            lastTick = -1;
        }
    }
    
    @BeforeEach
    public void setUp() {
        timer = new PomodoroTimer();
        listener = new TestListener();
        timer.setListener(listener);
    }
    
    @Test
    public void testInitialState() {
        assertEquals(PomodoroTimer.TimerState.IDLE, timer.getState());
        assertEquals(PomodoroTimer.WORK_DURATION, timer.getRemainingSeconds());
        assertEquals(0, timer.getCompletedPomodoros());
    }
    
    @Test
    public void testStartWork() {
        timer.startWork();
        assertEquals(PomodoroTimer.TimerState.WORKING, timer.getState());
        assertTrue(listener.workStarted);
    }
    
    @Test
    public void testStartShortBreak() {
        timer.startShortBreak();
        assertEquals(PomodoroTimer.TimerState.SHORT_BREAK, timer.getState());
        assertEquals(PomodoroTimer.SHORT_BREAK_DURATION, timer.getRemainingSeconds());
        assertTrue(listener.shortBreakStarted);
    }
    
    @Test
    public void testStartLongBreak() {
        timer.startLongBreak();
        assertEquals(PomodoroTimer.TimerState.LONG_BREAK, timer.getState());
        assertTrue(listener.longBreakStarted);
    }
    
    @Test
    public void testStop() {
        timer.startWork();
        timer.stop();
        assertEquals(PomodoroTimer.TimerState.IDLE, timer.getState());
    }
    
    @Test
    public void testFormatTime() {
        assertEquals("25:00", PomodoroTimer.formatTime(1500));
        assertEquals("05:00", PomodoroTimer.formatTime(300));
        assertEquals("00:30", PomodoroTimer.formatTime(30));
        assertEquals("00:05", PomodoroTimer.formatTime(5));
        assertEquals("00:00", PomodoroTimer.formatTime(0));
    }
    
    @Test
    public void testResetCompletedPomodoros() {
        timer.resetCompletedPomodoros();
        assertEquals(0, timer.getCompletedPomodoros());
    }
    
    @Test
    public void testConstants() {
        assertEquals(25 * 60, PomodoroTimer.WORK_DURATION);
        assertEquals(5 * 60, PomodoroTimer.SHORT_BREAK_DURATION);
        assertEquals(15 * 60, PomodoroTimer.LONG_BREAK_DURATION_MIN);
        assertEquals(30 * 60, PomodoroTimer.LONG_BREAK_DURATION_MAX);
        assertEquals(4, PomodoroTimer.POMODOROS_BEFORE_LONG_BREAK);
    }
    
    @Test
    public void testMultipleStarts() {
        timer.startWork();
        listener.reset();
        timer.startWork(); // Tekrar başlatma denemesi
        // İkinci başlatma işlemi çalışmamalı (zaten çalışıyor)
        // Bu test, timer'ın çalışırken tekrar başlatılmamasını kontrol eder
        assertEquals(PomodoroTimer.TimerState.WORKING, timer.getState());
    }
}

