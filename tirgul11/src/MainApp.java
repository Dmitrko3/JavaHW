import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

// --- שלב 1: מחלקות הסוסים ---


// --- שלב 3 + 4: מחלקת Race כתבנית Thread-Safe Singleton ---
class Race {
    // אתחול בזמן טעינת המחלקה (Class Loading) לבטיחות מירבית ב-Multi-Threading
    private static final Race instance = new Race();

    private Horse[] horses = new Horse[10];
    private volatile boolean isRaceActive = true; // volatile מבטיח שכל התהליכונים רואים את השינוי מיד

    // בנאי פרטי למניעת יצירת מופעים נוספים
    private Race() {}

    public static Race getInstance() {
        return instance;
    }

    public boolean isRaceActive() {
        return isRaceActive;
    }

    public void stopRace() {
        this.isRaceActive = false;
    }

    public void startRace(int totalDistance) {
        Random rand = new Random();

        // אתחול מערך הסוסים בעזרת ה-Factory והפעלת התהליכונים
        for (int i = 0; i < horses.length; i++) {
            int randomType = rand.nextInt(3); // 0, 1 או 2
            horses[i] = HorseFactory.createHorse(randomType, i, totalDistance, this);
            new Thread(horses[i]).start();
        }

        System.out.println("=== The Race Begins! ===");

        // לולאת מעקב של המנהל
        while (isRaceActive) {
            try {
                Thread.sleep(500); // המתנה של 500 מילי-שניות
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (!isRaceActive) {
                break; // אם המרוץ הסתיים במהלך ההמתנה, צא מהלולאה
            }

            // יצירת עותק של המערך לצורך מיון (כדי לא לשבש את האינדקסים המקוריים)
            Horse[] sortedHorses = Arrays.copyOf(horses, horses.length);
            // מיון לפי המרחק שנותר (מהקטן לגדול)
            Arrays.sort(sortedHorses, Comparator.comparingInt(Horse::getDistanceLeft));

            System.out.println("\n--- Top 3 Horses Current Status ---");
            for (int i = 0; i < 3; i++) {
                Horse h = sortedHorses[i];
                System.out.printf("Place %d: [Index: %d, Type: %s, Distance Left: %d]%n",
                        (i + 1), h.getIndex(), h.getType(), h.getDistanceLeft());
            }
        }

        // המרוץ הסתיים
        System.out.println("\n=== Race Finished! ===");
        // מציאת המנצח (זה שהמרחק שלו הוא 0)
        for (Horse h : horses) {
            if (h.getDistanceLeft() == 0) {
                System.out.printf("The Winner is Horse Index %d (%s)!%n", h.getIndex(), h.getType());
                break;
            }
        }
    }
}

// --- נקודת הכניסה ---
public class MainApp {
    public static void main(String[] args) {
        // השגת מופע ה-Singleton היחיד והפעלת המרוץ למרחק של 100
        Race race = Race.getInstance();
        race.startRace(100);
    }
}