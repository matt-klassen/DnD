package klassen.matt.dndproject.model.creature.exception;

/**
 * Represents the exception thrown in the event of an illegal hitpoint value
 */
public class HitPointException extends IllegalValueException {

    public HitPointException() { super(); }

    public HitPointException(String msg) {
        super(msg);
    }

}
