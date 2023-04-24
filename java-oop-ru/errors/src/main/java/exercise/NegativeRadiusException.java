package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    public NegativeRadiusException() {
        super("radius less than zero");
    }
}
// END
