// Main class to set up and start the race
public class HorsesRace {
    public static void main(String[] args) {
        int raceDistance = 50;
        FinishingLine finishLine = new FinishingLine();

        System.out.println("The race has started! Distance to cover: " + raceDistance);
        System.out.println("---------------------------------------------------");

        // Create and start 10 horse threads
        for (int i = 1; i <= 10; i++) {
            Horse horse = new Horse("Horse-" + i, finishLine, raceDistance);
            Thread thread = new Thread(horse);
            thread.start();
        }
    }
}