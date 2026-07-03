import java.util.Random;

// Represents a single horse running as a separate thread
public class Horse implements Runnable {
    private String id;
    private FinishingLine f;
    private int distance;
    private Random random;

    // Sets up the horse's ID, the shared finish line, and the distance to run
    public Horse(String id, FinishingLine f, int distance) {
        this.id = id;
        this.f = f;
        this.distance = distance;
        this.random = new Random();
    }

    // The actual race logic for the thread
    @Override
    public void run() {
        try {
            // Keep running while there is still distance left
            while (distance > 0) {
                Thread.sleep(1000); // Wait 1 second
                int step = random.nextInt(10) + 1; // Run forward by 1 to 10 units
                distance -= step;
            }
            // Tell the finish line this horse arrived
            f.arrive(this);
        } catch (InterruptedException e) {
            System.err.println("Horse " + id + " was interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    public String getId() {
        return id;
    }
}