import java.util.Random;

public class Person {
    private int x, y;
    private String image = "\uD83E\uDDD9";
    private int live = 10;

    Person(int sizeBoard) {
        Random random = new Random();
        x = 1 + random.nextInt(sizeBoard);
        y = sizeBoard;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLive() {
        return live;
    }

    public String getImage() {
        return image;
    }

    void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void downLive(int i) {
        if (live <= 0) {
            live = 0;
        } else {
            live -= i;
        }
    }
}