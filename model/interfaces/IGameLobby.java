/**
 * 
 */
package cs3744.model.interfaces;


/**
 * At this revision, the <CODE>ILobby</CODE> interface only contains a list of
 * <CODE>IPlayer</CODE>s as well as an <CODE>IMessageChannel</CODE>.
 * 
 * All changes to the player list as well as the message channel are
 * <b>vetoable</b>.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IPlayer
 * @see IMessageChannel
 */
public interface IGameLobby
        extends ILobby, IGameListContainer {

    // The game lobby interface is a "tagging interface" and does not contain any
    // methods of its own.
}
