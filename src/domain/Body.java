package domain;

public class Body {
    private BodyPosition position;
    private int numberOfHeads;

    public Body(BodyPosition position) {
        this.position = position;
        this.numberOfHeads = 1;
    }

    public Body(BodyPosition position, int numberOfHeads) {
        this.position = position;
        this.numberOfHeads = numberOfHeads;
    }

    public void setPosition(BodyPosition position) {
        this.position = position;
    }

    public BodyPosition getPosition() {
        return position;
    }

    public int getNumberOfHeads() {
        return numberOfHeads;
    }

    public void setNumberOfHeads(int numberOfHeads) {
        this.numberOfHeads = numberOfHeads;
    }

}
