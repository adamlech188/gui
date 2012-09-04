/**
 * 
 */
package cs3744.gui.events.interfaces;


import cs3744.gui.common.interfaces.IPoint;


/**
 * The <CODE>IInputEvent</CODE> interface provides a contract for abstract
 * events around input devices like mice and keyboards.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IInputEvent
        extends IGUIEvent {

    /**
     * Returns the x coordinate of the event.
     * 
     * @return the x coordinate of the event.
     */
    public abstract int getX();


    /**
     * Returns the y coordinate of the event.
     * 
     * @return the y coordinate of the event.
     */
    public abstract int getY();


    /**
     * Returns the location of the event.
     * 
     * @return the location of the event.
     */
    public abstract IPoint getLocation();
}
