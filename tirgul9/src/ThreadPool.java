import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// שלב 1: הגדרת מחלקת המשימה
class CountDown implements Runnable {
    private final int taskId;

    public CountDown(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        // ספירה לאחור מ-7 ומטה
        for (int count = 7; count >= 0; count--) {
            System.out.println("Task " + taskId + ", count = " + count);

            // ויתור מרצון על המעבד לטובת תהליכונים אחרים במאגר
            Thread.yield();
        }
    }
}

// שלב 2 + 3: מחלקת הניהול והמאגר
public class ThreadPool {
    public static void main(String[] args) {
        // הגדרת מאגר תהליכונים המאפשר ריצה של 4 תהליכונים בו זמנית בלבד
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // יצירה והרצה של 6 תהליכונים (משימות)
        for (int i = 0; i < 6; i++) {
            executor.execute(new CountDown(i));
        }

        // סגירת המאגר לקבלת משימות חדשות והמתנה לסיום המשימות הקיימות
        executor.shutdown();

        try {
            // חסימת תהליכון ה-main עד שכל 6 המשימות מסיימות (או עד שעובר הזמן המקסימלי שהוגדר)
            if (executor.awaitTermination(1, TimeUnit.HOURS)) {
                // הדפסה בסיום העבודה של המשימה האחרונה
                System.out.println("End of work");
            }
        } catch (InterruptedException e) {
            System.err.println("The execution was interrupted.");
        }
    }
}