// Tracks the finishing order of the horses
public class FinishingLine {
    private int place = 1;

    // Synchronized prevents overlapping prints if horses finish at the exact same time
    public synchronized void arrive(Horse h) {
        System.out.println("Place " + place + ": " + h.getId() + " has crossed the finish line!");
        place++;
    }
}