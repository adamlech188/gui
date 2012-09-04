package cs3744.graphics.interfaces;


import javax.media.opengl.GL2;

import cs3744.vectorAlgebra.Vector4f;


/**
 * The <CODE>IVector</CODE> interface provides a contract for classes
 * implementing vectors in homogeneous representation.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IVector {


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
     * Returns the rotation of the vector.
     * 
     * @return the rotation.
     */
    public IRotation getRotation();


    /**
     * Sets the rotation of the vector.
     * 
     * @param rotation
     *            the rotation.
     */
    public void setRotation(IRotation rotation);


    /**
     * Returns the scaling of the vector.
     * 
     * @return the scaling.
     */
    public IScaling getScaling();


    /**
     * Sets the scaling of the vector.
     * 
     * @param scaling
     *            the scaling
     */
    public void setScaling(IScaling scaling);


    /**
     * Returns the shear of the vector.
     * 
     * @return the shear.
     */
    public IShear getShear();


    /**
     * Sets the shear of the vector.
     * 
     * @param shear
     *            the shear.
     */
    public void setShear(IShear shear);


    /**
     * Returns the direction of this vector.
     * 
     * @return the direction.
     */
    public abstract Vector4f getVector();


    /**
     * Returns the x component.
     * 
     * @return the x component.
     */
    public abstract Float getX();


    /**
     * Returns the y component.
     * 
     * @return the y component.
     */
    public abstract Float getY();


    /**
     * Returns the z component.
     * 
     * @return the z component.
     */
    public abstract Float getZ();


    /**
     * Returns the w component.
     * 
     * @return the w component.
     */
    public abstract Float getW();


    /**
     * Returns the length of this vector (its Euclidean norm).
     * 
     * @return the length of this vector.
     */
    public abstract float length();


    /**
     * Returns a normalized version of this vector.
     * 
     * @return the normalized vector.
     */
    public abstract IVector normalizedVector();


    /**
     * Returns an array representation of the enclosed vector.
     * 
     * @return the array representation.
     */
    public abstract float[] toArray();


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