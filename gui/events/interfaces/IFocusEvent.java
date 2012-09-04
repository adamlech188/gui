/**
 * 
 */
package cs3744.gui.events.interfaces;


import cs3744.gui.interfaces.IComponent;


/**
 * The <CODE>FocusEvent</CODE> interface provides a contract for classes
 * implementing focus events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IFocusEvent
        extends IGUIEvent {

    /**
     * The <CODE>FocusModifier</CODE> enum enumerates the possible event
     * modifiers of a focus event.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * 
     */
    public enum FocusModifier {
        /**
         * Occurs when a component gains focus.
         */
        FOCUS_GAINED,
        /**
         * Occurs when a component loses focus
         */
        FOCUS_LOST;
    }


    /**
     * Returns the focus modifier (i.e., whether a component has gained or lost
     * focus).
     * 
     * @return the focus modifier.
     */
    public abstract FocusModifier getModifier();


    /**
     * Returns the other Component involved in this focus change.
     * 
     * @return the opposite component.
     */
    public abstract IComponent getOppositeComponent();
}
