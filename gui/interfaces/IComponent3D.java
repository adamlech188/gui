package cs3744.gui.interfaces;


import cs3744.graphics.interfaces.IScene;


/**
 * The <CODE>IComponent3D</CODE> interface provides a contract for components
 * that draw a 3D scene.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IComponent3D
        extends IComponent {

    /**
     * Returns the scene of this 3D component.
     * 
     * @return the scene.
     */
    public abstract IScene getScene();

}