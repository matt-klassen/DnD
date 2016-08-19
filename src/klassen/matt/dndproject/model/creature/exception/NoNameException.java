package klassen.matt.dndproject.model.creature.exception;

/**
 * Represents an exception thrown if no name is passed for a Hero or Monster
 */
public class NoNameException extends Exception {

    public NoNameException() {
        super();
    }

    public NoNameException(String msg) {
        super(msg);
    }

}
