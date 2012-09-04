package cs3744.model;


import java.text.SimpleDateFormat;
import java.util.Date;

import cs3744.model.interfaces.ITimestamp;


/**
 * The <CODE>Timestamp</CODE> provides an implementation of the
 * <CODE>ITimestamp</CODE> interface, using a <CODE>SimpleDateFormat</CODE> to
 * provide pretty printing.
 * 
 * @author Peter J. Radics
 * @version 1.1
 * 
 * @see java.text.SimpleDateFormat
 */
public class Timestamp
        implements ITimestamp {


    private final Date date;
    private final SimpleDateFormat formatter;


    /**
     * Getter for the date field.
     */
    @Override
    public Date getDate() {

        return this.date;
    }


    /**
     * Constructs a new Timestamp with the current Date and sets the formatting
     * of the toString() method to a default pattern.
     * 
     */
    public Timestamp() {

        // {full day with leading zero}/{Abbreviated month name}/{Year with
        // century} {HH}:{MM}:{SS}
        this("dd/MMM/yyyy HH:mm:ss");

    }


    /**
     * Constructs a new Timestamp with the current Date and sets the formatting
     * of the toString() method to the provided pattern.
     * 
     * @param pattern
     *            The pattern to format the timestamp with.
     */
    public Timestamp(String pattern) {

        this.date = new Date();
        this.formatter = new SimpleDateFormat(pattern);
    }


    @Override
    public boolean after(ITimestamp timestamp) {

        return this.date.after(timestamp.getDate());
    }


    @Override
    public boolean before(ITimestamp timestamp) {

        return this.date.before(timestamp.getDate());
    }


    @Override
    public long getTime() {

        return this.date.getTime();
    }


    /**
     * Compares this <CODE>Timestamp</Code> to another Object. If the Object is
     * a <CODE>ITimestamp</CODE> Date, this function behaves like compareTo(
     * <CODE>Date</CODE>). Otherwise, it throws a
     * <CODE>ClassCastException</CODE> (as <CODE>Timestamp</CODE>s are
     * comparable only to other <CODE>ITimestamp</CODE>s).
     * 
     * 
     * @param other
     *            the Object to be compared.
     * @return the value 0 if the argument is a Timestamp equal to this
     *         Timestamp; a value less than 0 if the argument is a Timestamp
     *         after this Timestamp; and a value greater than 0 if the argument
     *         is a Timestamp before this Timestamp.
     * 
     * @throws ClassCastException
     *             if the argument is not a <CODE>ITimestamp</CODE>.
     */
    @Override
    public int compareTo(ITimestamp other) {

        return this.date.compareTo(other.getDate());
    }


    /**
     * Returns a String representation of the object using the
     * <CODE>SimpleDateFormatter</CODE> wrapped in this instance.
     * 
     * @return A String representation of this object.
     */
    @Override
    public String toString() {

        return this.formatter.format(this.date);
    }


    /**
     * Tests whether this object's wrapped <CODE>Date</CODE> equals the other
     * object's wrapped<CODE>Date</CODE>.
     * 
     * @return <CODE>true</CODE> if the date objects are equal;
     *         <CODE>false</CODE> otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            if (obj.getClass().equals(Timestamp.class)) {
                Timestamp other = (Timestamp) obj;
                if (this.date.equals(other.date)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Returns the hash code of the wrapped <CODE>Date</CODE> object.
     * 
     * @return The hash code of the wrapped <CODE>Date</CODE> object.
     */
    @Override
    public int hashCode() {

        return this.date.hashCode();
    }


}
