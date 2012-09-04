package cs3744.gui.events.interfaces;


/**
 * The <CODE>IMouseEvent</CODE> interface provides a contract for EventObjects
 * that provide mouse events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IMouseEvent
        extends IInputEvent {

    /**
     * The <CODE>MouseEventModifier</CODE> enum enumerates the possible event
     * modifiers of a mouse event.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * 
     */
    public enum MouseEventModifier {

        /**
         * The "mouse clicked" event.
         */
        MOUSE_CLICKED,
//        /**
//         * The "mouse dragged" event.
//         */
//        MOUSE_DRAGGED,
        /**
         * The "mouse entered" event.
         */
        MOUSE_ENTERED,
        /**
         * The "mouse exited" event.
         */
        MOUSE_EXITED,
//        /**
//         * The "mouse moved" event.
//         */
//        MOUSE_MOVED,
        /**
         * The "mouse pressed" event.
         */
        MOUSE_PRESSED,
        /**
         * The "mouse released" event.
         */
        MOUSE_RELEASED,
//        /**
//         * The "mouse wheel" event.
//         */
//        MOUSE_WHEEL;


    }

    /**
     * The <CODE>MouseButton</CODE> enum enumerates the buttons of a mouse.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * 
     */
    public enum MouseButton {
        /**
         * Indicates mouse button #1; used by getButton().
         */
        BUTTON1,
        /**
         * Indicates mouse button #2; used by getButton().
         */
        BUTTON2,
        /**
         * Indicates mouse button #3; used by getButton().
         */
        BUTTON3,
        /**
         * Indicates that no button was pressed.
         */
        NOBUTTON;

    }


    /**
     * Provides the button that was pressed (if applicable).
     * 
     * @return the button.
     */
    public abstract MouseButton getButton();


    /**
     * Provides the mouse event modifier (the description of the event).
     * 
     * @return the modifier.
     */
    public abstract MouseEventModifier getModifier();


    /**
     * Returns how often a mouse button was clicked (if any).
     * 
     * @return the click count.
     */
    public abstract int getClickCount();
}
