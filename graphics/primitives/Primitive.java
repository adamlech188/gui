/**
 * 
 */
package cs3744.graphics.primitives;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cs3744.graphics.common.Color;
import cs3744.graphics.common.Material;
import cs3744.graphics.common.Operations;
import cs3744.graphics.common.Transformation;
import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.IMaterial;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.interfaces.IRotation;
import cs3744.graphics.interfaces.IScaling;
import cs3744.graphics.interfaces.IShear;
import cs3744.graphics.interfaces.ITransformation;
import cs3744.graphics.interfaces.ITranslation;


/**
 * Abstract base class for IPrimitive implementations.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public abstract class Primitive
        implements IPrimitive {

    /**
     * Specifies whether lighting is enabled.
     */
    public static boolean lightingEnabled = false;

    private final Lock parentLock;
    private IPrimitive parent;

    private final Lock translationLock;
    private ITranslation translation;

    private final Lock rotationLock;
    private IRotation rotation;

    private final Lock scalingLock;
    private IScaling scaling;

    private final Lock shearLock;
    private IShear shear;

    private final Lock colorLock;
    private IColor color;

    private final Lock materialLock;
    private IMaterial material;

    private final Lock isSolidLock;
    private Boolean isSolid;


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
    public ITranslation getTranslation() {

        try {
            this.translationLock.lock();
            return this.translation;
        }
        finally {
            this.translationLock.unlock();
        }
    }


    @Override
    public void setTranslation(ITranslation translation) {

        try {
            this.translationLock.lock();
            this.translation = translation;
        }
        finally {
            this.translationLock.unlock();
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


    @Override
    public IColor getColor() {

        try {
            this.colorLock.lock();

            if (this.color == null) {
                return Color.BLACK;
            }
            return this.color;
        }
        finally {
            this.colorLock.unlock();
        }
    }


    @Override
    public void setColor(IColor color) {

        try {
            this.colorLock.lock();

            this.colorSetter(color);
        }
        finally {
            this.colorLock.unlock();
        }
    }


    @Override
    public IMaterial getMaterial() {

        try {
            this.materialLock.lock();

            if (this.material == null) {
                return Material.BLACK_PLASTIC;
            }
            return this.material;
        }
        finally {
            this.materialLock.unlock();
        }
    }


    @Override
    public void setMaterial(IMaterial material) {

        try {
            this.materialLock.lock();

            this.materialSetter(material);
        }
        finally {
            this.materialLock.unlock();
        }

    }


    @Override
    public Boolean isSolid() {

        try {

            this.isSolidLock.lock();
            return this.isSolid;
        }
        finally {
            this.isSolidLock.unlock();
        }
    }


    @Override
    public void setSolid(Boolean isSolid) {

        try {

            this.isSolidLock.lock();
            this.isSolid = isSolid;
        }
        finally {
            this.isSolidLock.unlock();
        }
    }


    /**
     * Returns the lock around the parent.
     * 
     * @return the parent lock.
     */
    protected final Lock getParentLock() {

        return this.parentLock;
    }


    /**
     * Returns the lock around the translation.
     * 
     * @return the translation lock.
     */
    protected final Lock getTranslationLock() {

        return this.translationLock;
    }


    /**
     * Returns the lock around the rotation.
     * 
     * @return the rotation lock.
     */
    protected final Lock getRotationLock() {

        return this.rotationLock;
    }


    /**
     * Returns the lock around the scaling.
     * 
     * @return the scaling lock.
     */
    protected final Lock getScalingLock() {

        return this.scalingLock;
    }


    /**
     * Returns the lock around the shear.
     * 
     * @return the shear lock.
     */
    protected final Lock getShearLock() {

        return this.shearLock;
    }


    /**
     * Returns the lock around the color.
     * 
     * @return the color lock.
     */
    protected final Lock getColorLock() {

        return this.colorLock;
    }


    /**
     * Returns the lock around the isSolid flag.
     * 
     * @return the isSolid lock.
     */
    protected final Lock getIsSolidLock() {

        return this.isSolidLock;
    }


    /**
     * Creates a new primitive.
     * 
     */
    public Primitive() {

        this(null);
    }


    /**
     * Creates a new primitive with the provided parent container.
     * 
     * @param parent
     *            the parent container.
     */
    public Primitive(IPrimitive parent) {

        this.parentLock = new ReentrantLock();
        this.translationLock = new ReentrantLock();
        this.rotationLock = new ReentrantLock();
        this.scalingLock = new ReentrantLock();
        this.shearLock = new ReentrantLock();
        this.colorLock = new ReentrantLock();
        this.materialLock = new ReentrantLock();
        this.isSolidLock = new ReentrantLock();

        this.parent = parent;
        this.isSolid = true;
    }


    /**
     * Copy Constructor.
     * 
     * @param primitiveToCopy
     *            the primitive to copy.
     * @param parent
     *            the new instance's parent.
     */
    public Primitive(IPrimitive primitiveToCopy, IPrimitive parent) {

        if (primitiveToCopy == null) {
            throw new IllegalArgumentException("Cannot copy null primitive!");
        }
        this.parentLock = new ReentrantLock();
        this.translationLock = new ReentrantLock();
        this.rotationLock = new ReentrantLock();
        this.scalingLock = new ReentrantLock();
        this.shearLock = new ReentrantLock();
        this.colorLock = new ReentrantLock();
        this.materialLock = new ReentrantLock();
        this.isSolidLock = new ReentrantLock();

        this.parent = parent;
        this.colorSetter(primitiveToCopy.getColor());
        this.materialSetter(primitiveToCopy.getMaterial());
        this.rotation = primitiveToCopy.getRotation();
        this.scaling = primitiveToCopy.getScaling();
        this.shear = primitiveToCopy.getShear();
        this.translation = primitiveToCopy.getTranslation();
        this.isSolid = primitiveToCopy.isSolid();
    }


    @Override
    public ITransformation getObjectTransformation() {

        ITransformation currentTransformation = Transformation.NO_TRANSFORMATION;
        // System.out.println("=======================================");
        // System.out.println("Object transformation for: " + this);
        // System.out.println("Initial current transformation:\n"
        // + currentTransformation);

        if (this.getTranslation() != null) {
            // System.out.println("Translating:");
            ITransformation translationMatrix = Operations
                    .translationTransformation(this.getTranslation());

            currentTransformation = Operations.combine(currentTransformation,
                    translationMatrix);


            // System.out.println("Translation Matrix:\n" + translationMatrix);

            // System.out.println("Current transformation:\n"
            // + currentTransformation);
        }


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


    private void colorSetter(IColor thecolor) {

        try {
            this.colorLock.lock();

            if (Color.BLACK.equals(thecolor)) {
                this.color = null;
            }
            else {
                this.color = thecolor;
            }
        }
        finally {
            this.colorLock.unlock();
        }
    }


    private void materialSetter(IMaterial theMaterial) {

        try {
            this.materialLock.lock();

            if (Material.BLACK_PLASTIC.equals(theMaterial)) {
                this.material = null;
            }
            else {
                this.material = theMaterial;
            }
        }
        finally {
            this.materialLock.unlock();
        }
    }
}
