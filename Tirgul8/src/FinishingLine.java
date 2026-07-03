// Tracks the finishing order of the horses
public class FinishingLine {
    private int place = 1;
    private Horse winner = null;

    // Eager-initialized singleton instance (thread-safe)
    private static final FinishingLine INSTANCE = new FinishingLine();

    // Private constructor prevents direct instantiation
    private FinishingLine() { }

    public static FinishingLine getInstance() {
        return INSTANCE;
    }

    // Keep the synchronized section as small as possible: update shared state only
    public void arrive(Horse h) {
        int myPlace;
        boolean amWinner = false;
        synchronized (this) {
            myPlace = place;
            if (place == 1) {
                winner = h;
                amWinner = true;
            }
            place++;
        }

        // Heavy operations (printing) occur outside the lock
        System.out.println("Place " + myPlace + ": " + h.getId() + " has crossed the finish line!");
        if (amWinner) {
            System.out.println("Winner: " + winner.getId());
        }
    }

    // Synchronized getter to safely publish the winner
    public synchronized Horse getWinner() {
        return winner;
    }
}