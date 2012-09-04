package cs3744.gui.interfaces;


import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import cs3744.gui.common.interfaces.IDimension;

import java.awt.event.MouseListener;


/**
 * The <CODE>IWindow</CODE> interface provides a contract for top-level windows
 * with no borders and no menu bar. The default layout for a window is no
 * layout. A window must have either a frame, dialog, or another window defined
 * as its owner when it's constructed.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IWindow
        extends IContainer, GLEventListener, MouseListener {


    /**
     * Returns the owner of this window.
     * 
     * @return the owner.
     */
    public IWindow getOwner();


    /**
     * Returns the window's width.
     * 
     * @return the width.
     */
    public int getWidth();


    /**
     * Returns the window's height.
     * 
     * @return the height.
     */
    public int getHeight();


    /**
     * Sets the owner of this window.
     * 
     * @param owner
     *            the owner.
     */
    public void setOwner(IFrame owner);


    /**
     * Returns the drawable.
     * 
     * @return the drawable.
     */
    public GLAutoDrawable getDrawable();


    /**
     * Updates the dimensions of this window.
     * 
     * @param dimensions
     *            the new dimensions.
     */
    public abstract void updateSize(IDimension dimensions);

}
