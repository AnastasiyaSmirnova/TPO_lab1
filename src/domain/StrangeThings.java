package domain;

public class StrangeThings {
    private int number;

    public StrangeThings(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Current number of strange things is " + number;
    }
}
