package Game.Map;

public class Flag {
    private int x,y;
    private Flag nextFlag;

    public Flag(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Flag getNextFlag() {
        return nextFlag;
    }

    public void setNextFlag(Flag nextFlag) {
        this.nextFlag = nextFlag;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
