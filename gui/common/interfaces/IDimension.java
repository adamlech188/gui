package cs3744.gui.common.interfaces;


import cs3744.gui.interfaces.IComponent;
import cs3744.gui.interfaces.ILayoutManager;


/**
 * The <CODE>IDimension</CODE> interface provides a contract for classes that
 * want to encapsulates the width and height of a component (in integer
 * precision) in a single object. The class is associated with certain
 * properties of components.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IComponent
 * @see ILayoutManager
 */
public interface IDimension {


    /**
     * Returns the width.
     * 
     * @return the width.
     */
    public abstract int getWidth();


    /**
     * Returns the height.
     * 
     * @return the height.
     */
    public abstract int getHeight();


}
