package klassen.matt.dndproject.model.actions.exception;

/**
 * Represents the exception raised when an illegal spell level
 * is passed in an attempt to set spell level
 */
public class SpellException extends Exception {

    public SpellException() { super(); }

    public SpellException(String msg) { super(msg); }

}
