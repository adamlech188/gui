/**
 * 
 */
package cs3744.model.interfaces;


import java.util.Date;


/**
 * The <CODE>ITimestamp</CODE> interface defines the contract for a class
 * providing a thin wrapper around a <CODE>Date</CODE> object.
 * 
 * It is used for providing the capability of customizing the precision needed
 * in comparisons as well as customizing pretty printing.
 * 
 * @author Peter J. Radics
 * @version 1.1
 * 
 * @see java.util.Date
 */
public interface ITimestamp
        extends Comparable<ITimestamp> {

    /**
     * Getter for the date field.
     * 
     * @return The date of this timestamp.
     */
    public Date getDate();


    /**
     * Tests if this timestamp is after the specified timestamp.
     * 
     * @param timestamp
     *            The other timestamp.
     * @return <CODE>true</CODE>, if and only if the instant represented by this
     *         Timestamp object is strictly later than the other timestamp;
     *         <CODE>false</CODE> otherwise. Otherwise <CODE>false</CODE>.
     * @throws NullPointerException
     *             if the other timestamp is null.
     */
    public boolean after(ITimestamp timestamp);


    /**
     * Tests if this timestamp is before the specified timestamp.
     * 
     * @param timestamp
     *            The other timestamp.
     * @return <CODE>true</CODE>, if and only if the instant represented by this
     *         Timestamp object is strictly before than the other timestamp;
     *         <CODE>false</CODE> otherwise. Otherwise <CODE>false</CODE>.
     * @throws NullPointerException
     *             if the other timestamp is null.
     */
    public boolean before(ITimestamp timestamp);


    /**
     * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
     * represented by this Timestamp object.
     * 
     * @return the number of milliseconds since January 1, 1970, 00:00:00 GMT
     *         represented by this date.
     */
    public long getTime();
}
