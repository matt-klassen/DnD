package klassen.matt.dndproject.model.creature.exception;

/**
 * Exception thrown when illegal values are passed
 */
public class IllegalValueException extends Exception {

    public IllegalValueException() {
        super();
    }

    public IllegalValueException(String message) {
        super(message);
    }
}
