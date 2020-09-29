package domain;

public class IncorrectNameException extends Exception {
    public IncorrectNameException() {
        super("Incorrect name: empty name not available");
    }
}
