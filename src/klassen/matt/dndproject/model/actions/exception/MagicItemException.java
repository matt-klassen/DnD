package klassen.matt.dndproject.model.actions.exception;

/**
 * Represents the exception raised when an attempt is made to enchant an already-magic item
 */
public class MagicItemException extends Exception {

    public MagicItemException() { super(); }

    public MagicItemException(String msg) { super(msg); }


}
