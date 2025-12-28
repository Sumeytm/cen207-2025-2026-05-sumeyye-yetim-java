package com.pomodorotimer.pomodorotimer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Pomodoro Timer sınıfı
 * Pomodoro tekniğini uygular:
 * - 25 dakika çalışma
 * - 5 dakika kısa mola
 * - Her 4 Pomodoro'dan sonra 15-30 dakikalık uzun mola
 */
public class PomodoroTimer {
    
    // Zaman sabitleri (saniye cinsinden - test için kısa tutulmuş, gerçek kullanımda dakika * 60)
    public static final int WORK_DURATION = 25 * 60; // 25 dakika
    public static final int SHORT_BREAK_DURATION = 5 * 60; // 5 dakika
    public static final int LONG_BREAK_DURATION_MIN = 15 * 60; // 15 dakika
    public static final int LONG_BREAK_DURATION_MAX = 30 * 60; // 30 dakika
    public static final int POMODOROS_BEFORE_LONG_BREAK = 4; // 4 Pomodoro sonrası uzun mola
    
    private Timer timer;
    private int remainingSeconds;
    private TimerState state;
    private int completedPomodoros;
    private PomodoroTimerListener listener;
    
    /**
     * Timer durumları
     */
    public enum TimerState {
        IDLE,           // Başlatılmamış
        WORKING,        // Çalışma modu
        SHORT_BREAK,    // Kısa mola
        LONG_BREAK      // Uzun mola
    }
    
    /**
     * Constructor
     */
    public PomodoroTimer() {
        this.timer = new Timer();
        this.state = TimerState.IDLE;
        this.completedPomodoros = 0;
        this.remainingSeconds = WORK_DURATION;
    }
    
    /**
     * Listener set eder
     */
    public void setListener(PomodoroTimerListener listener) {
        this.listener = listener;
    }
    
    /**
     * Çalışma modunu başlatır
     */
    public void startWork() {
        if (state == TimerState.WORKING) {
            return; // Zaten çalışıyor
        }
        
        stop();
        state = TimerState.WORKING;
        remainingSeconds = WORK_DURATION;
        startTimer();
        
        if (listener != null) {
            listener.onWorkStarted();
        }
    }
    
    /**
     * Kısa molayı başlatır
     */
    public void startShortBreak() {
        if (state == TimerState.SHORT_BREAK) {
            return;
        }
        
        stop();
        state = TimerState.SHORT_BREAK;
        remainingSeconds = SHORT_BREAK_DURATION;
        startTimer();
        
        if (listener != null) {
            listener.onShortBreakStarted();
        }
    }
    
    /**
     * Uzun molayı başlatır
     */
    public void startLongBreak() {
        if (state == TimerState.LONG_BREAK) {
            return;
        }
        
        stop();
        state = TimerState.LONG_BREAK;
        // Uzun mola süresi 15-30 dakika arası (varsayılan 20 dakika)
        remainingSeconds = (LONG_BREAK_DURATION_MIN + LONG_BREAK_DURATION_MAX) / 2;
        startTimer();
        
        if (listener != null) {
            listener.onLongBreakStarted();
        }
    }
    
    /**
     * Timer'ı durdurur
     */
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = new Timer();
        }
        
        if (state != TimerState.IDLE && listener != null) {
            listener.onTimerStopped();
        }
        
        state = TimerState.IDLE;
    }
    
    /**
     * Timer'ı başlatır
     */
    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingSeconds--;
                
                if (listener != null) {
                    listener.onTick(remainingSeconds);
                }
                
                if (remainingSeconds <= 0) {
                    timerFinished();
                }
            }
        }, 1000, 1000); // Her 1 saniyede bir çalışır
    }
    
    /**
     * Timer bittiğinde çağrılır
     */
    private void timerFinished() {
        stop();
        
        if (state == TimerState.WORKING) {
            completedPomodoros++;
            
            if (listener != null) {
                listener.onWorkCompleted(completedPomodoros);
            }
            
            // 4 Pomodoro tamamlandıysa uzun mola, değilse kısa mola
            if (completedPomodoros % POMODOROS_BEFORE_LONG_BREAK == 0) {
                if (listener != null) {
                    listener.onLongBreakDue();
                }
            } else {
                if (listener != null) {
                    listener.onShortBreakDue();
                }
            }
        } else if (state == TimerState.SHORT_BREAK || state == TimerState.LONG_BREAK) {
            if (listener != null) {
                listener.onBreakCompleted();
            }
        }
        
        state = TimerState.IDLE;
    }
    
    /**
     * Kalan süreyi döndürür
     */
    public int getRemainingSeconds() {
        return remainingSeconds;
    }
    
    /**
     * Mevcut durumu döndürür
     */
    public TimerState getState() {
        return state;
    }
    
    /**
     * Tamamlanan Pomodoro sayısını döndürür
     */
    public int getCompletedPomodoros() {
        return completedPomodoros;
    }
    
    /**
     * Tamamlanan Pomodoro sayısını sıfırlar
     */
    public void resetCompletedPomodoros() {
        this.completedPomodoros = 0;
    }
    
    /**
     * Saniyeyi dakika:saniye formatına çevirir
     */
    public static String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }
}

