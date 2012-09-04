package cs3744.graphics.common;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.media.opengl.GL2;

import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.interfaces.IRotation;
import cs3744.graphics.interfaces.IScaling;
import cs3744.graphics.interfaces.IShear;
import cs3744.graphics.interfaces.ITransformation;
import cs3744.graphics.interfaces.IVector;
import cs3744.vectorAlgebra.Vector4f;


/**
 * Thin wrapper around a Vector4f.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Vector
        implements IVector {

    /**
     * The null vector.
     */
    public static final IVector NULL_VECTOR = new Vector(0f, 0f, 0f);
    /**
     * The i unit vector.
     */
    public static final IVector I_UNIT_VECTOR = new Vector(1f, 0f, 0f);
    /**
     * The j unit vector.
     */
    public static final IVector J_UNIT_VECTOR = new Vector(0f, 1f, 0f);
    /**
     * The k unit vector.
     */
    public static final IVector K_UNIT_VECTOR = new Vector(0f, 0f, 1f);


    private final Lock parentLock;
    private IPrimitive parent;

    private final Lock rotationLock;
    private IRotation rotation;

    private final Lock scalingLock;
    private IScaling scaling;

    private final Lock shearLock;
    private IShear shear;

    private final Vector4f vector;


    @Override
    public IPrimitive getParent() {

        try {

            this.parentLock.lock();
            return this.parent;
        }
        finally {
            this.parentLock.unlock();
        }
    }


    @Override
    public void setParent(IPrimitive parent) {

        try {
            this.parentLock.lock();
            this.parent = parent;
        }
        finally {
            this.parentLock.unlock();
        }
    }


    @Override
    public IRotation getRotation() {

        try {
            this.rotationLock.lock();

            return this.rotation;
        }
        finally {
            this.rotationLock.unlock();
        }
    }


    @Override
    public void setRotation(IRotation rotation) {

        try {
            this.rotationLock.lock();

            this.rotation = rotation;
        }
        finally {
            this.rotationLock.unlock();
        }
    }


    @Override
    public IScaling getScaling() {

        try {
            this.scalingLock.lock();

            return this.scaling;
        }
        finally {
            this.scalingLock.unlock();
        }
    }


    @Override
    public void setScaling(IScaling scaling) {

        try {
            this.scalingLock.lock();

            this.scaling = scaling;
        }
        finally {
            this.scalingLock.unlock();
        }
    }


    @Override
    public IShear getShear() {

        try {
            this.shearLock.lock();
            return this.shear;
        }
        finally {
            this.shearLock.unlock();
        }
    }


    @Override
    public void setShear(IShear shear) {

        try {
            this.shearLock.lock();
            this.shear = shear;
        }
        finally {
            this.shearLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.IVector#getVector()
     */
    @Override
    public Vector4f getVector() {

        return this.vector;
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.IVector#getX()
     */
    @Override
    public Float getX() {

        return this.vector.getX();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.IVector#getY()
     */
    @Override
    public Float getY() {

        return this.vector.getY();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.IVector#getZ()
     */
    @Override
    public Float getZ() {

        return this.vector.getZ();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.IVector#getW()
     */
    @Override
    public Float getW() {

        return this.vector.getW();
    }


    /**
     * Constructs a new <CODE>Vector</CODE> by creating a new vector with the
     * directions provided.
     * 
     * @param x
     *            The x-direction of the new vector.
     * @param y
     *            The y-direction of the new vector.
     * @param z
     *            The z-direction of the new vector.
     */
    public Vector(final float x, final float y, final float z) {

        this(new Vector4f(x, y, z));
    }


    /**
     * Constructs a new <CODE>Vector</CODE> by creating a new vector with the
     * directions provided.
     * 
     * @param x
     *            The x-direction of the new vector.
     * @param y
     *            The y-direction of the new vector.
     * @param z
     *            The z-direction of the new vector.
     * @param parent
     *            the parent of this vector.
     */
    public Vector(final float x, final float y, final float z, IPrimitive parent) {

        this(new Vector4f(x, y, z), parent);
    }


    /**
     * Constructs a new <CODE>Vector</CODE> with the provided direction.
     * 
     * @param vector
     *            The direction of this vector.
     */
    public Vector(Vector4f vector) {

        this(vector, null);
    }


    /**
     * Constructs a new <CODE>Vector</CODE> with the provided direction.
     * 
     * @param vector
     *            The direction of this vector.
     * @param parent
     *            the parent of this vector.
     */
    public Vector(Vector4f vector, IPrimitive parent) {

        this.vector = vector;
        this.parent = parent;
        this.parentLock = new ReentrantLock();
        this.rotationLock = new ReentrantLock();
        this.scalingLock = new ReentrantLock();
        this.shearLock = new ReentrantLock();
    }


    /**
     * Copy Constructor.
     * 
     * @param vector
     *            The vector to be copied.
     */
    public Vector(IVector vector) {

        this.parent = null;
        this.parentLock = new ReentrantLock();
        this.rotationLock = new ReentrantLock();
        this.scalingLock = new ReentrantLock();
        this.shearLock = new ReentrantLock();
        this.vector = vector.getVector();

    }


    /**
     * Copy Constructor.
     * 
     * @param vector
     *            The vector to be copied.
     * @param parent
     *            the parent.
     */
    public Vector(IVector vector, IPrimitive parent) {

        this.parentLock = new ReentrantLock();
        this.rotationLock = new ReentrantLock();
        this.scalingLock = new ReentrantLock();
        this.shearLock = new ReentrantLock();

        this.parent = parent;

        this.vector = vector.getVector();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.IVector#length()
     */
    @Override
    public float length() {

        return this.vector.length();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.IVector#normalizedVector()
     */
    @Override
    public IVector normalizedVector() {

        return new Vector(this.vector.normalizedVector());
    }


    @Override
    public String toString() {

        return this.vector.toString();
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            if (obj instanceof Vector) {
                IVector otherVector = (IVector) obj;

                return this.vector.equals(otherVector.getVector());
            }
        }

        return false;
    }


    @Override
    public int hashCode() {

        return this.vector.hashCode();
    }


    /**
     * Calculates the dot product of two vectors.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the dot product.
     */
    public static Float dotProduct(IVector lhs, IVector rhs) {

        return Vector4f.dotProduct(lhs.getVector(), rhs.getVector());
    }


    /**
     * Calculates the cross product of the two vectors.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the cross product (a vector orthogonal to both vectors provided).
     */
    public static IVector crossProduct(IVector lhs, IVector rhs) {

        return new Vector(Vector4f.crossProduct(lhs.getVector(),
                rhs.getVector()));
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.IVector#toArray()
     */
    @Override
    public float[] toArray() {

        return this.vector.toArray();
    }


    @Override
    public void paint(GL2 gl2) {


        // We retrieve the current transformation which is the result of this
        // object's transformation and its parent's current transformation.
        ITransformation currentTransformation = this.getCurrentTransformation();

         // System.out.println("\n=================CurrentTransformation=========================\n"
         // + currentTransformation);

        // We transform our point by our current transformation.
        IVector transformedVector = Operations.applyTransformation(
                currentTransformation, this).normalizedVector();


        // Drawing the transformed point.
         // System.out.println("Setting normal " + this.vector);
        gl2.glNormal3fv(transformedVector.toArray(), 0);
        // System.out.println("Transformed: " + transformedVector);


    }


    @Override
    public ITransformation getObjectTransformation() {

        ITransformation currentTransformation = Transformation.NO_TRANSFORMATION;
        // System.out.println("=======================================");
        // System.out.println("Object transformation for: " + this);
        // System.out.println("Initial current transformation:\n"
        // + currentTransformation);


        if (this.getShear() != null) {
            // System.out.println("Shearing:");
            ITransformation shearMatrix = Operations.shearTransformation(this
                    .getShear());

            currentTransformation = Operations.combine(currentTransformation,
                    shearMatrix);


            // System.out.println("Shear Matrix:\n" + shearMatrix);

            // System.out.println("Current transformation:\n"
            // + currentTransformation);
        }

        if (this.getRotation() != null) {
            // System.out.println("Rotating:");
            ITransformation rotationMatrix = Operations
                    .rotationTransformation(this.getRotation());
            // System.out.println("Rotation Matrix:\n" + rotationMatrix);

            currentTransformation = Operations.combine(currentTransformation,
                    rotationMatrix);

            // System.out.println("Current transformation:\n"
            // + currentTransformation);
        }


        if (this.getScaling() != null) {

            // System.out.println("Scaling:");
            ITransformation scalingMatrix = Operations
                    .scalingTransformation(this.getScaling());

            currentTransformation = Operations.combine(currentTransformation,
                    scalingMatrix);

            // System.out.println("Scaling Matrix:\n" + scalingMatrix);

            // System.out.println("Current transformation:\n"
            // + currentTransformation);
        }

        // System.out.println("Final Current transformation:\n"
        // + currentTransformation);

        // System.out.println("=======================================");
        return currentTransformation;
    }


    @Override
    public ITransformation getCurrentTransformation() {

        // System.out.println("=======================================");
        // System.out.println("Current transformation for: " + this);
        ITransformation currentTransformation = Transformation.NO_TRANSFORMATION;

        if (this.parent != null) {
            currentTransformation = this.parent.getCurrentTransformation();

            // System.out.println("Parent's Current transformation:\n"
            // + currentTransformation);

        }

        // System.out.println("=======================================");

        return Operations.combine(currentTransformation,
                this.getObjectTransformation());
    }


}