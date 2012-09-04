package cs3744.graphics.interfaces;


import javax.media.opengl.GLEventListener;

import cs3744.graphics.common.solution.SceneOptions;


/**
 * The <CODE>IScene</CODE> interface defines a contract for classes implementing
 * a scene.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IScene
        extends GLEventListener, IPrimitive {

    /**
     * Returns the camera.
     * 
     * @return the camera.
     */
    public abstract ICamera getCamera();


    /**
     * Sets the camera.
     * 
     * @param camera
     *            the camera.
     */
    public abstract void setCamera(ICamera camera);


    /**
     * Adds a primitive to the display list.
     * 
     * @param primitive
     *            the primitive to add.
     */
    public abstract void addPrimitive(IPrimitive primitive);


    /**
     * Removes a primitive to the display list.
     * 
     * @param primitive
     *            the primitive to remove.
     */
    public abstract void removePrimitive(IPrimitive primitive);


    /**
     * Clears the display list.
     * 
     */
    public abstract void clearPrimitives();


    /**
     * Returns the scene options.
     * 
     * @return the scene options.
     */
    public abstract SceneOptions getSceneOptions();

}