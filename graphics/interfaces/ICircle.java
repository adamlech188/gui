package cs3744.graphics.interfaces;


/**
 * The <CODE>ICircle</CODE> interface provides a contract for classes
 * implementing a circle.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ICircle
        extends IFace {

    /**
     * Returns the number of steps for drawing the circle.
     * 
     * @return the number of steps.
     */
    public abstract Integer getSteps();

}