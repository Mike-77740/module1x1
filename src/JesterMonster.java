import java.util.Random;
import java.util.Scanner;

public class JesterMonster extends Monster {
    JesterMonster(int sizeBoard) {
        super(sizeBoard);
        setImage("\uD83E\uDD21");
        setName(" J");
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        Random random = new Random();
        int n = random.nextInt(5);
        if (n == 0) {
            System.out.println("Как надо есть бутерброд с колбасой:");
            System.out.println("А: колбасой вверх.");
            System.out.println("Б: без колбасы.");
            System.out.println("В: колбасой вниз.");
            Scanner scanner = new Scanner(System.in);

            if (scanner.nextLine().equals("В")) {
                System.out.println("Верно! Ты победил монстра");
                return true;
            } else {
                System.out.println("Ты проиграл эту битву!");
                return false;
            }
        } else if (n == 1) {
            System.out.println("Что можно поймать, но нельзя бросить:");
            System.out.println("А: пузырь");
            System.out.println("Б: простуда");
            System.out.println("В: танк");
            Scanner scanner = new Scanner(System.in);

            if (scanner.nextLine().equals("Б")) {
                System.out.println("Верно! Ты победил монстра");
                return true;
            } else {
                System.out.println("Ты проиграл эту битву!");
                return false;
            }
        } else if (n == 2) {
            System.out.println(" Какой город летает:");
            System.out.println("А: Орёл");
            System.out.println("Б: Дубай");
            System.out.println("В: Москва");
            Scanner scanner = new Scanner(System.in);

            if (scanner.nextLine().equals("А")) {
                System.out.println("Верно! Ты победил монстра");
                return true;
            } else {
                System.out.println("Ты проиграл эту битву!");
                return false;
            }
        } else if (n == 3) {
            System.out.println("Что увеличится если его перевернуть:");
            System.out.println("А: чифра 9");
            System.out.println("Б: клоп");
            System.out.println("В: дом");
            Scanner scanner = new Scanner(System.in);

            if (scanner.nextLine().equals("В")) {
                System.out.println("Верно! Ты победил монстра");
                return true;
            } else {
                System.out.println("Ты проиграл эту битву!");
                return false;
            }
        } else {
            System.out.println("Ты воздухан:");
            System.out.println("А: да");
            System.out.println("Б: да");
            System.out.println("В: да");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("В")) {
                System.out.println("Верно! Ты воздухан!");
                return true;
            } else {
                System.out.println("Ты проиграл эту битву!");
                return false;
            }
        }
    }

    public int damageMonster() {
        return 7;
    }
}