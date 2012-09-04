/**
 *
 */
package cs3744.model;

import cs3744.model.interfaces.IMessage;
import cs3744.model.interfaces.IPlayer;
import cs3744.model.interfaces.ITimestamp;

/**
 * This class implements IMessage interface.It contains all the data fields
 * necessary to store messages.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class Message
    extends Object
    implements IMessage
{
    // ~Instance field
    private final IPlayer    sender;
    private final String     message;
    private final ITimestamp timestamp;


    // ----------------------------------------------------------
    /**
     * Constructor for a message. Create a new Message object.
     *
     * @param sender
     *            - player
     * @param message
     *            - message
     * @param timestamp
     *            - timestamp
     * @throws NullPointerException
     */
    public Message(IPlayer sender, String message, ITimestamp timestamp)
        throws NullPointerException
    {
        if (sender == null || message == null || timestamp == null)
        {
            throw new NullPointerException(
                "You have to provide valid arguments.");
        }
        else
        {

            this.sender = sender;
            this.message = message;
            this.timestamp = timestamp;

        }
    }


    /**
     * Getter for the message.
     *
     * @return message
     */
    @Override
    public String getMessage()
    {
        return message;
    }


    /**
     * Obtains timestamp
     *
     * @return - timestamp
     */
    @Override
    public ITimestamp getTimestamp()
    {
        return timestamp;
    }


    /**
     * Obtains player.
     *
     * @return IPlayer - sender
     */

    @Override
    public IPlayer getSender()
    {
        return sender;
    }


    /**
     * Tests whether this instance equals the object provided.
     *
     * @param object
     *            to be compared
     * @returns true if object is equal to value compared , false otherwise
     */
    public boolean equals(Object object)
    {
        if (object != null)
        {
            if (object instanceof IMessage)
            {
                IMessage objectToCompare = (IMessage)object;
                if (this.message.equals(objectToCompare.getMessage())
                    && this.sender.equals(objectToCompare.getSender())
                    && this.timestamp.equals(objectToCompare.getTimestamp()))
                {

                    return true;
                }
            }

        }
        return false;
    }


    /**
     * Creates a hash code from the message, the sender, and the timestamp.
     *
     * @return A hash code for this IMessage instance.
     */
    public int hashCode()
    {

        int returnValue;
        returnValue =
            this.getSender().hashCode() + this.getTimestamp().hashCode()
                + this.getMessage().hashCode();
        return returnValue;
    }


    /**
     * Returns a String representation of this message.
     *
     * @return String representation of the message
     */
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append(timestamp + ": " + "<" + sender + "> " + '"' + message
            + '"');
        return result.toString();

    }

}
