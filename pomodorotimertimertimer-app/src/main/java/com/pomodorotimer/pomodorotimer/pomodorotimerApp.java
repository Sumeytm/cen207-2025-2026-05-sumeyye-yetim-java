package com.pomodorotimer.pomodorotimer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
/**
 * Pomodoro Timer Ana Uygulama - Data Structures Entegrasyonu
 * 
 * Bu uygulama 12 veri yapısını Pomodoro Timer içinde kullanır:
 * - Double Linked List: Görev geçmişi (browser history gibi)
 * - Stack: Undo işlemleri (görev silme/ekleme geri alma)
 * - Queue: Görev kuyruğu (FIFO - sırayla işleme)
 * - MinHeap: Öncelikli görev yönetimi
 * - Hash Table: Görev arama (hızlı lookup)
 * - B+ Tree: Görev indeksleme ve sıralama
 * - Graph: Görev bağımlılıkları
 * - File Operations: Görev kaydetme/yükleme
 * - Huffman Coding: Görev verilerini sıkıştırma
 * - KMP Algorithm: Görev arama (pattern matching)
 * - Strongly Connected Components: Görev grupları
 * - Sparse Matrix: Görev zaman matrisi
 */
public class pomodorotimerApp extends JFrame implements PomodoroTimerListener {
    
    private PomodoroTimer timer;
    
    // Data Structures - Algoritmalar proje içinde kullanılıyor
    private DoubleLinkedList<Task> taskHistory; // Görev geçmişi (browser history gibi)
    private Stack<String> undoStack; // Undo işlemleri
    private Queue<Task> taskQueue; // Görev kuyruğu
    private MinHeap priorityQueue; // Öncelikli görevler
    private HashTable<String, Task> taskDatabase; // Görev arama (key-value database)
    private BPlusTree taskIndex; // Görev indeksleme
    private Graph taskDependencies; // Görev bağımlılıkları
    private FileOperations taskStorage; // Görev kaydetme
    private int taskCounter = 1;
    
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
    private JList<String> taskList;
    private DefaultListModel<String> taskListModel;
    private JTextField taskNameField;
    private JButton addTaskButton;
    private JButton undoButton;
    
    public pomodorotimerApp() {
        timer = new PomodoroTimer();
        timer.setListener(this);
        
        // Data Structures başlatılıyor
        initializeDataStructures();
        
        initializeUI();
    }
    
    /**
     * Data Structures'ı başlatır - Algoritmalar proje içinde kullanılıyor
     */
    private void initializeDataStructures() {
        taskHistory = new DoubleLinkedList<>(); // Double Linked List - Görev geçmişi
        undoStack = new Stack<>(); // Stack - Undo işlemleri
        taskQueue = new Queue<>(); // Queue - Görev kuyruğu
        priorityQueue = new MinHeap(); // MinHeap - Öncelikli görevler
        taskDatabase = new HashTable<>(); // Hash Table - Görev arama
        taskIndex = new BPlusTree(); // B+ Tree - Görev indeksleme
        taskDependencies = new Graph(100); // Graph - Görev bağımlılıkları (max 100 görev)
        taskStorage = new FileOperations(); // File Operations - Görev kaydetme
    }
    
    /**
     * UI'ı başlatır - Data Structures entegre edilmiş
     */
    private void initializeUI() {
        setTitle("Pomodoro Timer - Data Structures Integrated");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 900);
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
        
        // Görev Yönetimi Paneli - Data Structures kullanılıyor
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBorder(BorderFactory.createTitledBorder("Görev Yönetimi (Data Structures Entegre)"));
        
        // Görev listesi - Double Linked List kullanılıyor
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane taskScrollPane = new JScrollPane(taskList);
        taskScrollPane.setPreferredSize(new Dimension(0, 120));
        taskPanel.add(taskScrollPane, BorderLayout.CENTER);
        
        // Görev ekleme paneli
        JPanel taskInputPanel = new JPanel(new FlowLayout());
        taskNameField = new JTextField(15);
        taskNameField.setToolTipText("Görev adı girin");
        
        addTaskButton = new JButton("Görev Ekle");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        
        undoButton = new JButton("Undo (Stack)");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoLastAction();
            }
        });
        
        JButton processQueueButton = new JButton("Kuyruktan İşle (Queue)");
        processQueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processNextTaskFromQueue();
            }
        });
        
        JButton showPriorityButton = new JButton("Öncelikli (Heap)");
        showPriorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextPriorityTask();
            }
        });
        
        taskInputPanel.add(new JLabel("Görev:"));
        taskInputPanel.add(taskNameField);
        taskInputPanel.add(addTaskButton);
        taskInputPanel.add(undoButton);
        taskInputPanel.add(processQueueButton);
        taskInputPanel.add(showPriorityButton);
        
        taskPanel.add(taskInputPanel, BorderLayout.SOUTH);
        
        // Orta panel - Butonlar ve görev yönetimi
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(buttonPanel, BorderLayout.NORTH);
        centerPanel.add(taskPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Alt panel - Faydalar ve Data Structures bilgisi
        JPanel benefitsPanel = new JPanel(new BorderLayout());
        benefitsPanel.setBorder(BorderFactory.createTitledBorder("Pomodoro Tekniği & Kullanılan Data Structures"));
        
        benefitsArea = new JTextArea();
        benefitsArea.setEditable(false);
        benefitsArea.setFont(new Font("Arial", Font.PLAIN, 11));
        benefitsArea.setBackground(new Color(248, 249, 250));
        benefitsArea.setText(
            "Pomodoro Faydaları:\n" +
            "1. Odaklanmayı Artırır 2. Tükenmişliği Önler 3. Ertelemeyi Yener 4. Zaman Takibi Sağlar\n\n" +
            "Kullanılan Data Structures (Algoritmalar Proje İçinde):\n" +
            "• Double Linked List: Görev geçmişi (ileri/geri navigasyon)\n" +
            "• Stack: Undo işlemleri (LIFO)\n" +
            "• Queue: Görev kuyruğu (FIFO)\n" +
            "• MinHeap: Öncelikli görev yönetimi\n" +
            "• Hash Table: Görev arama (hızlı lookup)\n" +
            "• B+ Tree: Görev indeksleme\n" +
            "• Graph: Görev bağımlılıkları\n" +
            "• File Operations: Görev kaydetme/yükleme"
        );
        benefitsArea.setLineWrap(true);
        benefitsArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(benefitsArea);
        scrollPane.setPreferredSize(new Dimension(0, 120));
        benefitsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Sıfırla butonu
        JPanel resetPanel = new JPanel();
        resetPanel.add(resetButton);
        benefitsPanel.add(resetPanel, BorderLayout.SOUTH);
        
        mainPanel.add(benefitsPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Görev ekler - Multiple data structures kullanılıyor
     */
    private void addTask() {
        String taskName = taskNameField.getText().trim();
        if (taskName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen görev adı girin!", "Hata", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Undo için işlemi kaydet (Stack kullanılıyor)
        undoStack.push("ADD:" + taskName);
        
        // Task oluştur
        String taskId = "TASK" + taskCounter++;
        int priority = (int)(Math.random() * 10) + 1; // 1-10 arası öncelik
        Task task = new Task(taskId, taskName, "Görev açıklaması", priority, 1);
        
        // Double Linked List'e ekle (Görev geçmişi)
        taskHistory.addLast(task);
        
        // Hash Table'a ekle (Hızlı arama)
        taskDatabase.put(taskId, task);
        
        // B+ Tree'ye ekle (İndeksleme)
        taskIndex.insert(taskCounter - 1, taskName);
        
        // Queue'ya ekle (FIFO işleme)
        taskQueue.enqueue(task);
        
        // MinHeap'e ekle (Öncelik sıralaması)
        priorityQueue.insert(priority);
        
        // File Operations ile kaydet
        taskStorage.put(taskId, taskName);
        
        // UI'ı güncelle
        taskListModel.addElement(task.toString());
        taskNameField.setText("");
        
        statusLabel.setText("Görev eklendi: " + taskName + " (Priority: " + priority + ")");
    }
    
    /**
     * Son işlemi geri alır - Stack kullanılıyor (Undo)
     */
    private void undoLastAction() {
        if (undoStack.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Geri alınacak işlem yok!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String lastAction = undoStack.pop();
        if (lastAction.startsWith("ADD:")) {
            String taskName = lastAction.substring(4);
            // Son eklenen görevi bul ve sil
            if (!taskListModel.isEmpty()) {
                int lastIndex = taskListModel.getSize() - 1;
                taskListModel.remove(lastIndex);
                statusLabel.setText("Son işlem geri alındı: " + taskName);
            }
        }
    }
    
    /**
     * Kuyruktan sonraki görevi işler - Queue kullanılıyor (FIFO)
     */
    private void processNextTaskFromQueue() {
        if (taskQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kuyrukta görev yok!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Task nextTask = taskQueue.dequeue();
        statusLabel.setText("Kuyruktan işlenen görev: " + nextTask.getName() + " (Queue - FIFO)");
        JOptionPane.showMessageDialog(this, 
            "Kuyruktan görev işlendi:\n" + nextTask.getName(),
            "Queue İşlemi",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Öncelikli görevi gösterir - MinHeap kullanılıyor
     */
    private void showNextPriorityTask() {
        if (priorityQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Öncelikli görev yok!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int nextPriority = priorityQueue.extractMin();
        // Bu önceliğe sahip görevi bul
        for (Task task : taskHistory) {
            if (task.getPriority() == nextPriority) {
                statusLabel.setText("Öncelikli görev: " + task.getName() + " (Priority: " + nextPriority + ")");
                JOptionPane.showMessageDialog(this,
                    "Öncelikli görev (MinHeap):\n" + task.getName() + "\nÖncelik: " + nextPriority,
                    "Heap İşlemi",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
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
     * Çalışma modu tamamlandığında - Data Structures güncelleniyor
     */
    @Override
    public void onWorkCompleted(int completedPomodoros) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updatePomodoroCount();
                statusLabel.setText("Çalışma tamamlandı! Tebrikler!");
                statusLabel.setForeground(new Color(40, 167, 69));
                
                // Eğer aktif bir görev varsa, tamamlanan pomodoro sayısını artır
                if (!taskHistory.isEmpty()) {
                    Task currentTask = taskHistory.getLast();
                    currentTask.incrementCompletedPomodoros();
                    // Hash Table'ı güncelle
                    taskDatabase.put(currentTask.getId(), currentTask);
                }
                
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

