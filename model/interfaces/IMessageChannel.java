package cs3744.model.interfaces;


import java.util.List;

import cs3744.model.events.interfaces.ICollectionChangeSource;
import cs3744.model.events.interfaces.IVetoableCollectionChangeSource;
import cs3744.model.exceptions.CollectionChangeVetoException;


/**
 * The <CODE>IMessageChannel</CODE> interface provides management for chat
 * messages.
 * 
 * Changes to the list of messages are <b>vetoable</b>.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IMessage
 */
public interface IMessageChannel
        extends IVetoableCollectionChangeSource, ICollectionChangeSource {

    /**
     * Getter for the name field.
     * 
     * @return The name of the message channel.
     */
    public String getName();


    /**
     * Adds the message to the channel's list of messages.
     * 
     * @param message
     *            The message.
     * @throws CollectionChangeVetoException
     *             if the message cannot be added.
     */
    public void addMessage(IMessage message)
            throws CollectionChangeVetoException;


    /**
     * Returns a read-only list of messages received by this message channel.
     * 
     * @return A list of messages received by this message channel.
     */
    public List<IMessage> getMessages();
}
