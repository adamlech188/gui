/**
 * 
 */
package cs3744.graphics.interfaces;


import javax.media.opengl.GL2;


/**
 * Provides an interface for Attributes
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IAttribute {

    /**
     * Applies the attribute to the drawing context.
     * 
     * @param gl2
     *            The OpenGL state machine.
     */
    public void applyAttribute(GL2 gl2);
}
