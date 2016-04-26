package klassen.matt.dndproject.model.creature.exception;

/**
 * Represents an exception thrown if an invalid level is passed as an argument
 */
public class LevelException extends IllegalValueException {

    public LevelException() {
        super();
    }

    public LevelException(String msg) {
        super(msg);
    }

}
