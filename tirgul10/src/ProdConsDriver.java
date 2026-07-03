import java.util.LinkedList;
import java.util.Queue;

// שלב 1: המשאב המשותף עם הגבלת מקום
class BoundedQueue {
    private final Queue<Integer> workingQueue = new LinkedList<>();
    private final int MAX_SIZE = 10;

    public void produce(int num) {
        // שימוש בתור עצמו כמוניטור
        synchronized (workingQueue) {
            // כל עוד התור מלא - היצרן ממתין
            while (workingQueue.size() == MAX_SIZE) {
                try {
                    System.out.println("Queue is full! Producer is waiting...");
                    workingQueue.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // הוספת איבר לתור
            workingQueue.add(num);
            System.out.println("Produced: " + num + " (Size: " + workingQueue.size() + ")");
            // הערת כל התהליכונים (כולל צרכנים שאולי ממתינים לאיבר)
            workingQueue.notifyAll();
        }
    }

    public int consume() {
        int item = -1;
        synchronized (workingQueue) {
            // כל עוד התור ריק - הצרכן ממתין
            while (workingQueue.isEmpty()) {
                try {
                    System.out.println("Queue is empty! Consumer is waiting...");
                    workingQueue.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // הוצאת איבר מהתור
            item = workingQueue.poll();
            System.out.println("Consumed: " + item + " (Size: " + workingQueue.size() + ")");
            // הערת כל התהליכונים (כולל יצרנים שאולי ממתינים למקום פנוי)
            workingQueue.notifyAll();
        }
        return item;
    }
}



public class ProdConsDriver {
    public static void main(String[] args) {
        BoundedQueue sharedQueue = new BoundedQueue();

        Thread producer1 = new Thread(new Producer(sharedQueue, 1));
        Thread producer2 = new Thread(new Producer(sharedQueue, 2));

        Thread consumer1 = new Thread(new Consumer(sharedQueue));
        Thread consumer2 = new Thread(new Consumer(sharedQueue));
        Thread consumer3 = new Thread(new Consumer(sharedQueue));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}