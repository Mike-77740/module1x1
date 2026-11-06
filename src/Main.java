import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String castle = "\uD83C\uDFF0";

        int sizeBoard = 5;
        int step = 0;
        int winMonsters = 0; // колличество побежденных монстров

        Person person = new Person(sizeBoard);

        Random random = new Random();
        int castleX = 1 + random.nextInt(sizeBoard);
        int castleY = 1;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";

            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        Monster[] monsters = new Monster[countMonster];
        int count = 0;

        while (count < countMonster) {
            Monster monster;
            int n = random.nextInt(100); // задаёт вероятность определённому монстру
            if (n < 50) {
                monster = new Monster(sizeBoard);
            } else  if (n < 80){
                monster = new BigMonster(sizeBoard);
            } else {
                monster = new JesterMonster(sizeBoard);
            }

            int row = monster.getY() - 1;
            int column = monster.getX() - 1;

            if (board[row][column].equals("  ")) {
                board[row][column] = monster.getImage();
                monsters[count] = monster;
                count++;
            }
        }

        board[castleY - 1][castleX - 1] = castle;

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        System.out.println("Ваш ответ:\t" + answer);

        switch (answer) {
            case "ДА":
                System.out.println("Выбери сложность игры (от 1 до 5):");
                int difficultGame = scanner.nextInt();
                scanner.nextLine(); // Что бы убрать /n после scanner.nextInt()
                System.out.println("Выбранная сложность:\t" + difficultGame);

                while (true) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive());

                    System.out.println("Введите свой ход (w-вверх, s-вниз, a-влево, d-вправо)");

                    int x, y;

                    label:
                    while (true) {
                        String direction = scanner.nextLine();
                        switch (direction) {
                            case "w", "W":
                                x = person.getX();
                                y = person.getY() - 1;
                                break label;
                            case "a", "A":
                                x = person.getX() - 1;
                                y = person.getY();
                                break label;
                            case "s", "S":
                                x = person.getX();
                                y = person.getY() + 1;
                                break label;
                            case "d", "D":
                                x = person.getX() + 1;
                                y = person.getY();
                                break label;
                            default:
                                System.out.println("Неверный ввод, введите заново.");
                        }
                    }


                    if (board[y - 1][x - 1].equals("  ")) {
                        board[person.getY() - 1][person.getX() - 1] = "  ";
                        person.move(x, y);
                        step++;

                        System.out.println("Ход корректный; новые координаты: "
                                + person.getX() + ", " + person.getY()
                                + "\nХод номер: " + step);
                    } else if (board[y - 1][x - 1].equals(castle)) {
                        System.out.println("Вы прошли игру!");
                        System.out.println("Колличество шагов: " + step + ".");
                        System.out.println("Колличество побежденных монстров: " + winMonsters + ".");
                        System.out.println("Колличество оставшихся жизней: " + person.getLive() + ".");
                        break;
                    } else {
                        for (Monster monster : monsters) {
                            if (monster.conflictPerson(x, y)) {
                                if (monster.taskMonster(difficultGame)) {
                                    board[person.getY() - 1][person.getX() - 1] = "  ";
                                    person.move(x, y);
                                    step++;
                                    winMonsters++;
                                } else {
                                    switch (monster.getName()) {
                                        case "Monster" -> System.out.println("Вас ранил обычный монстр.");
                                        case "BigMonster" -> System.out.println("Вас ранил большой монстр.");
                                        case "Jester" -> System.out.println("Вас взорвал шут!");
                                        default -> System.out.println("Вас ранила неизвестная сущность?");
                                    }
                                    person.downLive(monster.damageMonster());
                                }
                                break;
                            }
                        }
                    }



                    if (person.getLive() <= 0) {
                        break;
                    }
                }

                if (person.getLive() <= 0) {
                    System.out.println("Закончились жизни. Итог:");
                    System.out.println("колличество шагов: " + step + ",");
                    System.out.println("колличество побежденных монстров: " + winMonsters + ".");
                }
                break;

            case "НЕТ":
                System.out.println("Жаль, приходи ещё!");
                break;

            default:
                System.out.println("Данные введены некорректно");
                break;
        }
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] row : board) {
            System.out.println(wall);
            for (String col : row) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);

        System.out.println("Количество жизней:\t" + live + "\n");
    }
}