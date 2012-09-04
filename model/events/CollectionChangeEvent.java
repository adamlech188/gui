package cs3744.model.events;

import cs3744.model.events.interfaces.ICollectionChangeEvent;
import cs3744.model.events.solution.CollectionChangeOperation;
import java.util.EventObject;

/**
 * Class that contains CollectionChangeEvent. It provides all data and methods
 * necessary to notify the listeners about the event.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class CollectionChangeEvent
    extends EventObject
    implements ICollectionChangeEvent
{

    private static final long               serialVersionUID = 1L;
    private final String                    collectionName;
    private final Object                    causeOfChange;
    private final CollectionChangeOperation operation;


    // ----------------------------------------------------------
    /**
     * Create a new CollectionChangeEvent object. It passes source argument into
     * the superclass.
     *
     * @param source
     * @param collectionName
     * @param causeOfChange
     * @param operation
     */
    public CollectionChangeEvent(
        Object source,
        String collectionName,
        Object causeOfChange,
        CollectionChangeOperation operation)
    {
        super(source);
        this.collectionName = collectionName;
        this.causeOfChange = causeOfChange;
        this.operation = operation;
    }


    // ----------------------------------------------------------
    /**
     * Constructs a new CollectionChangeEvent with the provided collection name,
     * source, and cause of change.It passes source argument into a superclass.
     *
     * @param source
     * @param collectionName
     * @param causeOfChange
     */
    public CollectionChangeEvent(
        Object source,
        String collectionName,
        Object causeOfChange)
    {
        super(source);
        this.collectionName = collectionName;
        this.causeOfChange = causeOfChange;
        operation = null;
    }


    /**
     * Getter for the name of the collection.
     *
     *
     * @return the name of the changed collection.
     */
    @Override
    public String getCollectionName()
    {
        return this.collectionName;
    }


    /**
     * Getter for the cause of the change.
     *
     * @return The cause of the change. May be null if the collection was
     *         cleared.
     */
    @Override
    public Object getCauseOfChange()
    {
        return this.causeOfChange;
    }


    /**
     * Getter for the operation.
     *
     * @return operation
     */
    @Override
    public CollectionChangeOperation getOperation()
    {
        return this.operation;
    }


    /**
     * Returns a string representation of CollectionChange event.
     *
     * @return string representation of the event
     */
    public String toString()
    {

        StringBuilder returnValue = new StringBuilder();
        returnValue.append("From: " + "<" + source + ">" + "\n");
        returnValue.append("\tTo: " + collectionName + "\n");
        returnValue.append("\tOperation: " + operation + "\n" + "\tElement: "
            + causeOfChange + "\n");

        return returnValue.toString();
    }
}
