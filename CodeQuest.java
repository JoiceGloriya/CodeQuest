import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

class Question {
    private String ques;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
    private int crt_ans;
    private int score;

    public void setQuestion(String q, String a1, String a2, String a3, String a4, int option, int marks) {
        ques = q;
        option_1 = a1;
        option_2 = a2;
        option_3 = a3;
        option_4 = a4;
        crt_ans = option;
        score = marks;
    }

    public void askQuestion(Scanner sc) {
        System.out.println("\n" + ques);
        System.out.println("1. " + option_1);
        System.out.println("2. " + option_2);
        System.out.println("3. " + option_3);
        System.out.println("4. " + option_4);
        System.out.print("What is your answer? (enter the option number): ");
        int guess = sc.nextInt();

        if (guess == crt_ans) {
            System.out.println("\nGreat! You are correct.");
            Quiz.total += score;
            System.out.println("Score: " + score + " out of " + score);
        } else {
            System.out.println("\nOops! You are wrong.");
            System.out.println("Score: 0 out of " + score);
            System.out.println("The correct answer is: " + crt_ans);
        }
    }
}

class Quiz {
    private static final String[] WELCOME_MESSAGE = {
            "~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~",
            "~  ~  ~  ~  ~                             ~  ~  ~  ~  ~",
            "~  ~  ~  ~                                   ~  ~  ~  ~",
            "~  ~  ~                                         ~  ~  ~",
            "~  ~         WELCOME TO THE JAVA QUIZ :)           ~  ~",
            "~                                                     ~",
            "~       ------------------------------------          ~",
            "~                                                     ~",
            "~  ~                  By                           ~  ~",
            "~  ~  ~                                         ~  ~  ~",
            "~  ~  ~  ~       Joice and Pooja             ~  ~  ~  ~",
            "~  ~  ~  ~  ~                             ~  ~  ~  ~  ~",
            "~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~"
    };

    public static int total = 0;

    public static void printWelcomeMessage() {
        for (String line : WELCOME_MESSAGE) {
            System.out.println("\t\t\t" + line);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProgressBarExample obj = new ProgressBarExample();
        obj.loadingStuff(); // Loading bar

        Scanner sc = new Scanner(System.in);

        printWelcomeMessage();

        System.out.print("\nAre you ready to take the Quiz? (y/n): ");
        char input = sc.next().charAt(0);

        if (input == 'n' || input == 'N') {
            System.out.println("\nNo problem. Take the Quiz whenever you are ready.");
            return;
        }

        sc.nextLine(); // Consume a newline.
        System.out.print("\nEnter the name of the player: ");
        String name = sc.nextLine();
        System.out.println("Press Enter to start the Quiz.");
        sc.nextLine();

        // Start timer
        Timer timer = new Timer(60); // 60 seconds timer
        Thread timerThread = new Thread(timer);
        timerThread.start();

        // Array to store all the questions
        Question[] questions = new Question[10];

        // Setting questions using a loop
        String[][] questionData = {
    {"What is the principle of restricting access to certain details of an object called?", 
     "Encapsulation", "Abstraction", "Inheritance", "Polymorphism", 
     "1", "10"},
    {"Which of the following is not a feature of Object-Oriented Programming?", 
     "Encapsulation", "Polymorphism", "Data Abstraction", "Compilation", 
     "4", "10"},
    {"What keyword is used to inherit a class in Java?", 
     "extends", "inherits", "implements", "instanceof", 
     "1", "10"},
    {"Which of the following concepts allows a class to use methods of another class?", 
     "Composition", "Encapsulation", "Inheritance", "Aggregation", 
     "3", "10"},
    {"Which feature of OOP allows a subclass to provide a specific implementation of a method that is already defined in its superclass?", 
     "Polymorphism", "Overloading", "Overriding", "Encapsulation", 
     "3", "10"},
    {"What is it called when a class has multiple methods with the same name but different parameters?", 
     "Overloading", "Overriding", "Polymorphism", "Abstraction", 
     "1", "10"},
    {"Which of the following allows an object to be treated as an instance of its parent class?", 
     "Encapsulation", "Inheritance", "Polymorphism", "Abstraction", 
     "3", "10"},
    {"What is the term for a class that cannot be instantiated on its own and is meant to be subclassed?", 
     "Abstract class", "Interface", "Concrete class", "Static class", 
     "1", "10"},
    {"Which keyword is used to create an interface in Java?", 
     "interface", "class", "implements", "extends", 
     "1", "10"},
    {"What does the 'this' keyword refer to in a class?", 
     "The current object", "The parent class", "Static members", "An instance of the class", 
     "1", "10"}
};

        for (int i = 0; i < questionData.length; i++) {
            questions[i] = new Question();
            questions[i].setQuestion(questionData[i][0], questionData[i][1], questionData[i][2], questionData[i][3], questionData[i][4], Integer.parseInt(questionData[i][5]), Integer.parseInt(questionData[i][6]));
            questions[i].askQuestion(sc);
        }

        // Stop timer when quiz ends
        timer.stopTimer();

        System.out.println("\nFINAL RESULTS ARE OUT!!!");
        System.out.println("\nYour Total score is: " + total + " out of 100.");

        if (total >= 60) {
            System.out.println("\nYou passed the Quiz!");
            System.out.println("\t\t\t$ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $");
            System.out.println("\t\t\t$                                           $");
            System.out.println("\t\t\t$            CONGRATULATIONS                $");
            System.out.println("\t\t\t$                                           $");
            System.out.println("\t\t\t$ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $");
        } else {
            System.out.println("\nOops! You failed. Better luck next time.");
        }
        sc.close();
    }
}

class ProgressBarExample extends JFrame {
    JProgressBar progressBar;

    public ProgressBarExample() {
        setTitle("Loading...");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.black);

        add(progressBar, BorderLayout.CENTER);
    }

    public void updateProgress(int value) {
        progressBar.setValue(value);
    }

    public void loadingStuff() {
        setVisible(true);

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50); // To simulate time-consuming task
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateProgress(i);
        }
    }
}

class Timer implements Runnable {
    private int time;
    private boolean running;

    public Timer(int time) {
        this.time = time;
        this.running = true;
    }

    public void run() {
        while (running && time > 0) {
            System.out.println("Time remaining: " + time + " seconds");
            try {
                Thread.sleep(1000); // Wait for a second
                time--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (time == 0) {
            System.out.println("Time's up!");
        }
    }

    public void stopTimer() {
        running = false;
    }
}
