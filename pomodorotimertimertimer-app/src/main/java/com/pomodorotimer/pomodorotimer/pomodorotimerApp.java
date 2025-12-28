package com.pomodorotimer.pomodorotimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pomodoro Timer Ana Uygulama
 * 
 * Çalışma Döngüsü:
 * - 25 Dakika Çalışma: Sadece tek bir göreve odaklanırsınız.
 * - 5 Dakika Kısa Mola: Zihninizi dinlendirirsiniz.
 * - Uzun Mola: Her 4 çalışma bloğundan (4 Pomodoro) sonra 15-30 dakikalık daha uzun bir mola verirsiniz.
 * 
 * Faydaları:
 * - Odaklanmayı Artırır: Sürenin kısıtlı olduğunu bilmek (25 dakika), beyni daha hızlı odaklanmaya teşvik eder.
 * - Tükenmişliği Önler: Düzenli kısa molalar vermek, zihinsel yorgunluğu azaltır.
 * - Ertelemeyi Yener: Büyük projeleri "sadece 25 dakika çalışacağım" diyerek başlatmak daha kolaydır.
 * - Zaman Takibi Sağlar: Bir işin kaç "Pomodoro" sürdüğünü görerek, gelecekteki işler için tahmin yapabilirsiniz.
 */
public class pomodorotimerApp extends JFrame implements PomodoroTimerListener {
    
    private PomodoroTimer timer;
    
    // UI Bileşenleri
    private JLabel timeLabel;
    private JLabel statusLabel;
    private JLabel pomodoroCountLabel;
    private JButton startWorkButton;
    private JButton startShortBreakButton;
    private JButton startLongBreakButton;
    private JButton stopButton;
    private JButton resetButton;
    private JTextArea benefitsArea;
    
    public pomodorotimerApp() {
        timer = new PomodoroTimer();
        timer.setListener(this);
        
        initializeUI();
    }
    
    /**
     * UI'ı başlatır
     */
    private void initializeUI() {
        setTitle("Pomodoro Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 700);
        setLocationRelativeTo(null);
        
        // Ana panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Üst panel - Timer ve durum
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Pomodoro Timer"));
        
        // Zaman gösterimi
        timeLabel = new JLabel("25:00", JLabel.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setForeground(new Color(220, 53, 69)); // Kırmızı renk
        topPanel.add(timeLabel, BorderLayout.CENTER);
        
        // Durum etiketi
        statusLabel = new JLabel("Hazır - Çalışmaya başlamak için 'Çalışmayı Başlat' butonuna tıklayın", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(statusLabel, BorderLayout.SOUTH);
        
        // Pomodoro sayacı
        pomodoroCountLabel = new JLabel("Tamamlanan Pomodoro: 0", JLabel.CENTER);
        pomodoroCountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        pomodoroCountLabel.setForeground(new Color(40, 167, 69)); // Yeşil renk
        topPanel.add(pomodoroCountLabel, BorderLayout.NORTH);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Orta panel - Butonlar
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        startWorkButton = new JButton("Çalışmayı Başlat (25 dk)");
        startWorkButton.setFont(new Font("Arial", Font.BOLD, 14));
        startWorkButton.setBackground(new Color(220, 53, 69));
        startWorkButton.setForeground(Color.WHITE);
        startWorkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.startWork();
            }
        });
        
        startShortBreakButton = new JButton("Kısa Mola (5 dk)");
        startShortBreakButton.setFont(new Font("Arial", Font.BOLD, 14));
        startShortBreakButton.setBackground(new Color(40, 167, 69));
        startShortBreakButton.setForeground(Color.WHITE);
        startShortBreakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.startShortBreak();
            }
        });
        
        startLongBreakButton = new JButton("Uzun Mola (15-30 dk)");
        startLongBreakButton.setFont(new Font("Arial", Font.BOLD, 14));
        startLongBreakButton.setBackground(new Color(23, 162, 184));
        startLongBreakButton.setForeground(Color.WHITE);
        startLongBreakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.startLongBreak();
            }
        });
        
        stopButton = new JButton("Durdur");
        stopButton.setFont(new Font("Arial", Font.BOLD, 14));
        stopButton.setBackground(new Color(108, 117, 125));
        stopButton.setForeground(Color.WHITE);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        
        resetButton = new JButton("Sıfırla");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(255, 193, 7));
        resetButton.setForeground(Color.BLACK);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.resetCompletedPomodoros();
                updatePomodoroCount();
            }
        });
        
        buttonPanel.add(startWorkButton);
        buttonPanel.add(startShortBreakButton);
        buttonPanel.add(startLongBreakButton);
        buttonPanel.add(stopButton);
        
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Alt panel - Faydalar
        JPanel benefitsPanel = new JPanel(new BorderLayout());
        benefitsPanel.setBorder(BorderFactory.createTitledBorder("Pomodoro Tekniğinin Faydaları"));
        
        benefitsArea = new JTextArea();
        benefitsArea.setEditable(false);
        benefitsArea.setFont(new Font("Arial", Font.PLAIN, 12));
        benefitsArea.setBackground(new Color(248, 249, 250));
        benefitsArea.setText(
            "1. Odaklanmayı Artırır:\n" +
            "   Sürenin kısıtlı olduğunu bilmek (25 dakika), beyni daha hızlı odaklanmaya teşvik eder ve dikkatin dağılmasını engeller.\n\n" +
            "2. Tükenmişliği Önler:\n" +
            "   Düzenli kısa molalar vermek, zihinsel yorgunluğu azaltır ve gün boyu enerjik kalmanızı sağlar.\n\n" +
            "3. Ertelemeyi Yener:\n" +
            "   Büyük ve korkutucu görünen projeleri, 'sadece 25 dakika çalışacağım' diyerek başlatmak çok daha kolaydır.\n\n" +
            "4. Zaman Takibi Sağlar:\n" +
            "   Bir işin kaç 'Pomodoro' sürdüğünü görerek, gelecekteki işler için ne kadar zamana ihtiyacınız olduğunu daha iyi tahmin edersiniz."
        );
        benefitsArea.setLineWrap(true);
        benefitsArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(benefitsArea);
        scrollPane.setPreferredSize(new Dimension(0, 150));
        benefitsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Sıfırla butonu
        JPanel resetPanel = new JPanel();
        resetPanel.add(resetButton);
        benefitsPanel.add(resetPanel, BorderLayout.SOUTH);
        
        mainPanel.add(benefitsPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Çalışma modu başladığında
     */
    @Override
    public void onWorkStarted() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText("Çalışma modu aktif - Odaklanın!");
                statusLabel.setForeground(new Color(220, 53, 69));
                timeLabel.setForeground(new Color(220, 53, 69));
            }
        });
    }
    
    /**
     * Kısa mola başladığında
     */
    @Override
    public void onShortBreakStarted() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText("Kısa mola - Zihninizi dinlendirin");
                statusLabel.setForeground(new Color(40, 167, 69));
                timeLabel.setForeground(new Color(40, 167, 69));
            }
        });
    }
    
    /**
     * Uzun mola başladığında
     */
    @Override
    public void onLongBreakStarted() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText("Uzun mola - İyi bir dinlenme zamanı!");
                statusLabel.setForeground(new Color(23, 162, 184));
                timeLabel.setForeground(new Color(23, 162, 184));
            }
        });
    }
    
    /**
     * Timer durduğunda
     */
    @Override
    public void onTimerStopped() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText("Timer durduruldu");
                statusLabel.setForeground(Color.BLACK);
            }
        });
    }
    
    /**
     * Her saniye çağrılır
     */
    @Override
    public void onTick(int remainingSeconds) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                timeLabel.setText(PomodoroTimer.formatTime(remainingSeconds));
            }
        });
    }
    
    /**
     * Çalışma modu tamamlandığında
     */
    @Override
    public void onWorkCompleted(int completedPomodoros) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updatePomodoroCount();
                statusLabel.setText("Çalışma tamamlandı! Tebrikler!");
                statusLabel.setForeground(new Color(40, 167, 69));
                
                // Bildirim göster
                JOptionPane.showMessageDialog(
                    pomodorotimerApp.this,
                    "Pomodoro tamamlandı! Mola zamanı!",
                    "Başarılı",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
    
    /**
     * Kısa mola gerektiğinde
     */
    @Override
    public void onShortBreakDue() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int option = JOptionPane.showConfirmDialog(
                    pomodorotimerApp.this,
                    "Çalışma tamamlandı! Kısa mola başlatılsın mı?",
                    "Mola Zamanı",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (option == JOptionPane.YES_OPTION) {
                    timer.startShortBreak();
                }
            }
        });
    }
    
    /**
     * Uzun mola gerektiğinde
     */
    @Override
    public void onLongBreakDue() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int option = JOptionPane.showConfirmDialog(
                    pomodorotimerApp.this,
                    "4 Pomodoro tamamlandı! Uzun mola zamanı! Uzun mola başlatılsın mı?",
                    "Uzun Mola Zamanı",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (option == JOptionPane.YES_OPTION) {
                    timer.startLongBreak();
                }
            }
        });
    }
    
    /**
     * Mola tamamlandığında
     */
    @Override
    public void onBreakCompleted() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText("Mola tamamlandı - Yeni bir çalışma başlatabilirsiniz");
                statusLabel.setForeground(Color.BLACK);
                timeLabel.setText("25:00");
                timeLabel.setForeground(new Color(220, 53, 69));
                
                JOptionPane.showMessageDialog(
                    pomodorotimerApp.this,
                    "Mola tamamlandı! Yeni bir çalışma başlatabilirsiniz.",
                    "Mola Bitti",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
    
    /**
     * Pomodoro sayacını günceller
     */
    private void updatePomodoroCount() {
        int count = timer.getCompletedPomodoros();
        pomodoroCountLabel.setText("Tamamlanan Pomodoro: " + count);
        
        // 4'ün katıysa özel mesaj
        if (count > 0 && count % PomodoroTimer.POMODOROS_BEFORE_LONG_BREAK == 0) {
            pomodoroCountLabel.setText("Tamamlanan Pomodoro: " + count + " - Uzun mola zamanı!");
        }
    }
    
    /**
     * Ana metod
     */
    public static void main(String[] args) {
        // Swing UI'ı başlat
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Sistem görünümünü kullan
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                pomodorotimerApp app = new pomodorotimerApp();
                app.setVisible(true);
            }
        });
    }
}

