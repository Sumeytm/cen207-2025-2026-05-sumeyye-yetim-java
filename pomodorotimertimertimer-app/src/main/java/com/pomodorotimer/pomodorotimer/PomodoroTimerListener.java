package com.pomodorotimer.pomodorotimer;

/**
 * Pomodoro Timer olaylarını dinlemek için interface
 */
public interface PomodoroTimerListener {
    
    /**
     * Çalışma modu başladığında çağrılır
     */
    void onWorkStarted();
    
    /**
     * Kısa mola başladığında çağrılır
     */
    void onShortBreakStarted();
    
    /**
     * Uzun mola başladığında çağrılır
     */
    void onLongBreakStarted();
    
    /**
     * Timer durduğunda çağrılır
     */
    void onTimerStopped();
    
    /**
     * Her saniye çağrılır (tick)
     */
    void onTick(int remainingSeconds);
    
    /**
     * Çalışma modu tamamlandığında çağrılır
     */
    void onWorkCompleted(int completedPomodoros);
    
    /**
     * Kısa mola gerektiğinde çağrılır
     */
    void onShortBreakDue();
    
    /**
     * Uzun mola gerektiğinde çağrılır
     */
    void onLongBreakDue();
    
    /**
     * Mola tamamlandığında çağrılır
     */
    void onBreakCompleted();
}

