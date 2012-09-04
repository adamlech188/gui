package cs3744.model.interfaces;


/**
 * The <CODE>IMessageChannelContainer</CODE> interface represents objects that
 * contain a message channel.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IMessageChannel
 */
public interface IMessageChannelContainer {

    /**
     * Retrieves the message channel of the message channel container.
     * 
     * @return The message channel.
     */
    public IMessageChannel getMessageChannel();
}
