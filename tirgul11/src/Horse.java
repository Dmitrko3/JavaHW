import java.util.Random;

abstract class Horse implements Runnable {
    protected int index;
    protected String type;
    protected int distanceLeft;
    protected Race race; // הפניה למרוץ כדי לבדוק אם הוא עדיין פעיל

    public Horse(int index, int distanceLeft, Race race) {
        this.index = index;
        this.distanceLeft = distanceLeft;
        this.race = race;
    }

    public int getIndex() { return index; }
    public String getType() { return type; }
    public int getDistanceLeft() { return distanceLeft; }

    @Override
    public void run() {
        Random rand = new Random();
        // הסוס רץ כל עוד נשאר לו מרחק וכל עוד המרוץ לא נעצר ע"י סוס אחר
        while (distanceLeft > 0 && race.isRaceActive()) {
            int step = rand.nextInt(15) + 1; // צעד אקראי בין 1 ל-15
            distanceLeft -= step;

            if (distanceLeft <= 0) {
                distanceLeft = 0;
                race.stopRace(); // סוס זה הגיע לקו הסיום, עוצרים את המרוץ
            }

            try {
                Thread.sleep(200); // שינה של 200 מילי-שניות
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class ArabianHorse extends Horse {
    public ArabianHorse(int index, int distanceLeft, Race race) {
        super(index, distanceLeft, race);
        this.type = "Arabian";
    }
}

class MustangHorse extends Horse {
    public MustangHorse(int index, int distanceLeft, Race race) {
        super(index, distanceLeft, race);
        this.type = "Mustang";
    }
}

class PonyHorse extends Horse {
    public PonyHorse(int index, int distanceLeft, Race race) {
        super(index, distanceLeft, race);
        this.type = "Pony";
    }
}

// --- שלב 2: תבנית Factory Method ---
class HorseFactory {
    public static Horse createHorse(int typeId, int index, int distance, Race race) {
        switch (typeId) {
            case 0: return new ArabianHorse(index, distance, race);
            case 1: return new MustangHorse(index, distance, race);
            default: return new PonyHorse(index, distance, race);
        }
    }
}