package cs3744.gui.interfaces;


import java.awt.event.KeyListener;


/**
 * The <CODE>IFrame</CODE> interface provides a contract for a top-level window
 * with a title and a border. The size of the frame includes any area designated
 * for the border but not the window bar.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public interface IFrame
        extends IWindow, KeyListener {


    /**
     * Shows or hides this <CODE>IFrame</CODE> depending on the value of
     * parameter b.
     * 
     * @param visible
     *            if <CODE>true</CODE>, makes the frame visible, otherwise hides
     *            the frame.
     */
    public abstract void setVisible(boolean visible);


    /**
     * Disposes of the window.
     */
    public abstract void dispose();
}
