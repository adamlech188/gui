package cs3744.gui.events.interfaces;


/**
 * The <CODE>IGUIEvent</CODE provides a base interface for all GUI-related
 * events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGUIEvent {


    /**
     * Returns the source of the event.
     * 
     * @return the source.
     */
    public abstract Object getSource();


    /**
     * Consumes the event.
     */
    public abstract void consume();


    /**
     * Returns whether this event has been consumed.
     * 
     * @return <CODE>true</CODE> if the event has been consumed;
     *         <CODE>false</CODE> otherwise.
     */
    public abstract boolean isConsumed();
}
