package cs3744.model.exceptions;


import cs3744.model.events.interfaces.IDataChangeEvent;
import cs3744.model.exceptions.interfaces.IDataChangeVetoException;


/**
 * The <CODE>DataChangeVetoException</CODE> is a reference implementation of an
 * <CODE>IDataChangeVetoException</CODE>.
 * 
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see Exception
 * @see IDataChangeEvent
 */
public class DataChangeVetoException
        extends Exception
        implements IDataChangeVetoException {


    private static final long serialVersionUID = 1L;

    private final IDataChangeEvent vetoedEvent;


    @Override
    public IDataChangeEvent getVetoedEvent() {

        return this.vetoedEvent;
    }


    /**
     * Constructs a new <CODE>DataChangeVetoException</CODE> with a detailed
     * message and the vetoed event.
     * 
     * @param message
     *            The detailed message.
     * @param vetoedEvent
     *            The vetoed event.
     */
    public DataChangeVetoException(String message, IDataChangeEvent vetoedEvent) {

        super(message);

        if (vetoedEvent == null) {
            throw new IllegalArgumentException(
                    "DataChangeVetoException needs to specify the vetoed event!");
        }

        this.vetoedEvent = vetoedEvent;

    }


    /**
     * Provides a String representation of this exception.
     * 
     * @return a String representation of this exception.
     */
    @Override
    public String toString() {

        String returnValue = "Attempted change is to be rejected due to a veto:\n";
        returnValue += "\tReason: " + this.getMessage() + "\n";

        returnValue += "\tVetoed Change:\n\t" + this.vetoedEvent;

        return returnValue;
    }

}
