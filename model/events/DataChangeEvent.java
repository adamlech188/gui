/**
 *
 */
package cs3744.model.events;

import java.util.EventObject;
import cs3744.model.events.interfaces.IDataChangeEvent;

/**
 * One-sentence description here. Longer descriptions here.
 *
 * @author Your name here
 * @version 1.0
 */
public class DataChangeEvent
    extends EventObject
    implements IDataChangeEvent
{

    private static final long serialVersionUID = 1L;
    private final String      dataName;
    private final Object      oldValue;
    private final Object      newValue;


    // ----------------------------------------------------------
    /**
     * Create a new DataChangeEvent object.
     *
     * @param source
     *            - source of the event
     * @param dataName
     *            - name of data
     * @param oldValue
     *            - value that is changed
     * @param newValue
     *            - value changed into
     */
    public DataChangeEvent(
        Object source,
        String dataName,
        Object oldValue,
        Object newValue)
    {

        super(source);

        this.dataName = dataName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }


    /**
     * Gets the new value
     *
     * @returns newValue
     */
    @Override
    public Object getNewValue()
    {
        return this.newValue;
    }


    /**
     * Gets the old value
     *
     * @return old value
     */
    @Override
    public Object getOldValue()
    {
        return this.oldValue;
    }


    /**
     * Get the name of data
     *
     * @return data name
     */
    @Override
    public String getDataName()
    {

        return this.dataName;
    }


    /**
     * Gets the string representation of DataChangeEvent
     *
     * @return string representation of DataChangeEvent
     */
    public String toString()
    {
        StringBuilder result;
        result = new StringBuilder();
        result.append("From: " + "<" + source.toString() + ">"
            + " requested a change:\n");
        result.append("\tTo: " + dataName + "\n");
        result.append("\told value: " + oldValue + "\n");
        result.append("\tnew value: " + newValue + "\n");

        return result.toString();
    }
}
