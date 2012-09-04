package cs3744.graphics.interfaces;


import javax.media.opengl.GL2;


/**
 * Provides an interface for a drawable object.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IPrimitive {


    /**
     * Provides a copy of the current object.
     * 
     * @return the copy.
     */
    public IPrimitive copy();


    /**
     * Provides a copy of the current object and sets its parent.
     * 
     * @param parent
     *            the parent.
     * @return the copy.
     */
    public IPrimitive copy(IPrimitive parent);


    /**
     * Returns the parent container of this primitive.
     * 
     * @return the parent container.
     */
    public IPrimitive getParent();


    /**
     * Sets the parent container of this primitive.
     * 
     * @param parent
     *            the parent container.
     */
    public void setParent(IPrimitive parent);


    /**
     * Returns the translation of the primitive.
     * 
     * @return the translation.
     */
    public ITranslation getTranslation();


    /**
     * Sets the translation of the primitive.
     * 
     * @param translation
     *            the translation.
     * 
     */
    public void setTranslation(ITranslation translation);


    /**
     * Returns the rotation of the primitive.
     * 
     * @return the rotation.
     */
    public IRotation getRotation();


    /**
     * Sets the rotation of the primitive.
     * 
     * @param rotation
     *            the rotation.
     */
    public void setRotation(IRotation rotation);


    /**
     * Returns the scaling of the primitive.
     * 
     * @return the scaling.
     */
    public IScaling getScaling();


    /**
     * Sets the scaling of the primitive.
     * 
     * @param scaling
     *            the scaling
     */
    public void setScaling(IScaling scaling);


    /**
     * Returns the shear of the primitive.
     * 
     * @return the shear.
     */
    public IShear getShear();


    /**
     * Sets the shear of the primitive.
     * 
     * @param shear
     *            the shear.
     */
    public void setShear(IShear shear);


    /**
     * Returns the color of the primitive.
     * 
     * @return the color.
     */
    public IColor getColor();


    /**
     * Sets the color of the primitive.
     * 
     * @param color
     *            the color.
     */
    public void setColor(IColor color);


    /**
     * Returns the material of the primitive.
     * 
     * @return the material.
     */
    public IMaterial getMaterial();


    /**
     * Sets the material of the primitive.
     * 
     * @param material
     *            the material to set.
     */
    public void setMaterial(IMaterial material);


    /**
     * Returns whether the primitive should be drawn as a solid or a wireframe.
     * 
     * @return <CODE>true</CODE>, if the primitive should be drawn as a solid;
     *         <CODE>false</CODE> otherwise.
     */
    public Boolean isSolid();


    /**
     * Sets whether the primitive should be drawn as a solid or a wireframe.
     * 
     * @param solid
     *            whether or not the primitive should be drawn as a solid.
     * 
     */
    public void setSolid(Boolean solid);


    /**
     * Returns the object transformation of this primitive.
     * 
     * @return the object transformation.
     */
    public ITransformation getObjectTransformation();


    /**
     * Returns the current transformation of this primitive, which is the
     * object's own transformation multiplied by its parent's current
     * transformation.
     * 
     * @return the current transformation.
     */
    public ITransformation getCurrentTransformation();


    /**
     * Paints the primitive.
     * 
     * @param gl2
     *            The OpenGL state machine.
     */
    public void paint(GL2 gl2);
}
