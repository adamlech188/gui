package cs3744.model.interfaces;


/**
 * The <CODE>IMessage</CODE> interface provides a timestamped message with a
 * sender in form of a <CODE>IPlayer</CODE> instance.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see ITimestamp
 * @see IPlayer
 */
public interface IMessage {

    /**
     * Getter for the message field.
     * 
     * @return The message.
     */
    public String getMessage();


    /**
     * Getter for the timestamp field.
     * 
     * @return The timestamp.
     */
    public ITimestamp getTimestamp();


    /**
     * Getter for the sender field.
     * 
     * @return The sender.
     */
    public IPlayer getSender();
}
