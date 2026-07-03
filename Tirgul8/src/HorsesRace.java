// Main class to set up and start the race
public class HorsesRace {
    public static void main(String[] args) {
        int raceDistance = 50;
        FinishingLine finishLine = FinishingLine.getInstance();

        System.out.println("The race has started! Distance to cover: " + raceDistance);
        System.out.println("---------------------------------------------------");

        // Create and start 10 horse threads and keep references to join later
        Thread[] threads = new Thread[10];
        for (int i = 1; i <= 10; i++) {
            Horse horse = new Horse("Horse-" + i, raceDistance);
            Thread thread = new Thread(horse);
            threads[i - 1] = thread;
            thread.start();
        }

        // Wait for all horses to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println("Main thread interrupted while waiting for horses to finish.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Race concluded — retrieve and print the winner from the singleton
        Horse winner = FinishingLine.getInstance().getWinner();
        if (winner != null) {
            System.out.println("The race is over! Winner: " + winner.getId());
        } else {
            System.out.println("The race is over! No winner recorded.");
        }
    }
}