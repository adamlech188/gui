/**
 * 
 */
package cs3744.graphics.common;


import cs3744.graphics.interfaces.IRotation;
import cs3744.graphics.interfaces.IVector;
import cs3744.vectorAlgebra.Vector4f;


/**
 * Provides a reference implementation of the <CODE>IRotation</CODE> class.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Rotation
        implements IRotation {


    private final IVector rotationAxis;
    private final float rotationAngle;


    @Override
    public IVector getRotationAxis() {

        return this.rotationAxis;
    }


    @Override
    public float getRotationAngle() {

        return this.rotationAngle;
    }


    /**
     * Constructs a <CODE>Rotation</CODE> that does not perform any rotation.
     */
    public Rotation() {

        this(Vector.NULL_VECTOR, 0f);
    }


    /**
     * Constructs a new <CODE>Rotation</CODE>.
     * 
     * @param x
     *            the x component of the rotation axis
     * @param y
     *            the y component of the rotation axis
     * @param z
     *            the z component of the rotation axis
     * 
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotation(float x, float y, float z, float rotationAngle) {

        this(new Vector4f(x, y, z), rotationAngle);
    }


    /**
     * Constructs a new <CODE>Rotation</CODE>.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotation(Vector4f rotationAxis, float rotationAngle) {

        this(new Vector(rotationAxis), rotationAngle);
    }


    /**
     * Constructs a new <CODE>Rotation</CODE>. The rotation axis provided is
     * normalized to avoid distorting of objects during rotation.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotation(IVector rotationAxis, float rotationAngle) {

        this.rotationAxis = rotationAxis.normalizedVector();
        this.rotationAngle = rotationAngle;
    }


    /**
     * Provides a rotation by the provided rotation angle around the x axis.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation.
     */
    public static Rotation rotationX(float rotationAngle) {

        return new Rotation(Vector.I_UNIT_VECTOR, rotationAngle);
    }


    /**
     * Provides a rotation by the provided rotation angle around the y axis.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation.
     */
    public static Rotation rotationY(float rotationAngle) {

        return new Rotation(Vector.J_UNIT_VECTOR, rotationAngle);
    }


    /**
     * Provides a rotation by the provided rotation angle around the z axis.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation.
     */
    public static Rotation rotationZ(float rotationAngle) {

        return new Rotation(Vector.K_UNIT_VECTOR, rotationAngle);
    }


    @Override
    public String toString() {

        return "Rotation Axis: " + this.rotationAxis + ", Angle: "
                + this.rotationAngle;
    }
}
