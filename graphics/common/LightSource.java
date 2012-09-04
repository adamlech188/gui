package cs3744.graphics.common;

import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.ILightSource;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.primitives.solution.Primitive;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;

// -------------------------------------------------------------------------
/**
 * This class stores all the information necessary for the light source.
 *
 * @author Adam Lech (PID: adaml8)
 * @version Aug 10, 2012
 */
public abstract class LightSource
    extends Primitive
    implements ILightSource

{
    private final int  lightNumber;
    private final Lock constantAttenuationFactorLock;
    private float      constantAttenuationFactor;
    private final Lock linearAttenuationFactorLock;
    private float      linearAttenuationFactor;
    private final Lock quadraticAttenuationFactorLock;
    private float      quadraticAttenuationFactor;
    private final Lock ambientComponentLock;
    private IColor     ambientComponent;
    private final Lock specularComponentLock;
    private IColor     specularComponent;
    private Lock       diffuseComponentLock;

    private IColor     diffuseComponent;


    /**
     * Creates a new DirectionalLightSource with white light components, no
     * attenuation, and the provided parent..
     *
     * @param primitiveToCopy
     * @param parent
     */
    protected LightSource(ILightSource primitiveToCopy,

    IPrimitive parent)
    {
        super(primitiveToCopy, parent);

        this.lightNumber = primitiveToCopy.getLightNumber();
        this.ambientComponent = primitiveToCopy.getAmbientComponent();
        this.diffuseComponent = primitiveToCopy.getDiffuseComponent();
        this.specularComponent = primitiveToCopy.getSpecularComponent();
        this.constantAttenuationFactor =
            primitiveToCopy.getConstantAttenuationFactor();
        this.linearAttenuationFactor =
            primitiveToCopy.getLinearAttenuationFactor();
        this.quadraticAttenuationFactor =
            primitiveToCopy.getQuadraticAttenuationFactor();
        specularComponentLock = new ReentrantLock();
        quadraticAttenuationFactorLock = new ReentrantLock();
        linearAttenuationFactorLock = new ReentrantLock();
        constantAttenuationFactorLock = new ReentrantLock();
        ambientComponentLock = new ReentrantLock();

    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with white light components, no
     * attenuation, and no parent..
     *
     * @param lightNumber
     */
    protected LightSource(int lightNumber)
    {
        this(
            lightNumber,
            Color.WHITE,
            Color.WHITE,
            Color.WHITE,
            1f,
            0f,
            0f,
            null);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with white light components, the
     * provided attenuation and no parent.
     *
     * @param lightNumber
     * @param constantAttenuationFactor
     * @param linearAttenuationFactor
     * @param quadraticAttenuationFactor
     */
    protected LightSource(
        int lightNumber,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor)
    {

        this(
            lightNumber,
            Color.WHITE,
            Color.WHITE,
            Color.WHITE,
            constantAttenuationFactor,
            linearAttenuationFactor,
            quadraticAttenuationFactor,
            null);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with white light components, the
     * provided attenuation, and the provided parent.
     *
     * @param lightNumber
     * @param constantAttenuationFactor
     * @param linearAttenuationFactor
     * @param quadraticAttenuationFactor
     * @param parent
     */
    protected LightSource(
        int lightNumber,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor,
        IPrimitive parent)
    {
        this(
            lightNumber,
            Color.WHITE,
            Color.WHITE,
            Color.WHITE,
            constantAttenuationFactor,
            linearAttenuationFactor,
            quadraticAttenuationFactor,
            parent);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with the provided light components,
     * attenuation, and no parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param constantAttenuationFactor
     * @param linearAttenuationFactor
     * @param quadraticAttenuationFactor
     */
    protected LightSource(
        int lightNumber,
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor)
    {
        this(
            lightNumber,
            ambientComponent,
            diffuseComponent,
            specularComponent,
            constantAttenuationFactor,
            linearAttenuationFactor,
            quadraticAttenuationFactor,
            null);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with the provided light components,
     * attenuation, and parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param constantAttenuationFactor
     * @param linearAttenuationFactor
     * @param quadraticAttenuationFactor
     * @param parent
     */
    protected LightSource(
        int lightNumber,
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor,
        IPrimitive parent)
    {
        super(parent);
        this.lightNumber = lightNumber;
        this.ambientComponent = ambientComponent;
        this.diffuseComponent = diffuseComponent;
        this.specularComponent = specularComponent;
        this.constantAttenuationFactor =

        constantAttenuationFactor;
        this.linearAttenuationFactor =

        linearAttenuationFactor;
        this.quadraticAttenuationFactor =

        quadraticAttenuationFactor;
        ambientComponentLock = new ReentrantLock();
        diffuseComponentLock = new ReentrantLock();
        specularComponentLock = new ReentrantLock();
        constantAttenuationFactorLock = new ReentrantLock();
        linearAttenuationFactorLock = new ReentrantLock();
        quadraticAttenuationFactorLock = new ReentrantLock();

    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with the provided light components,
     * no attenuation, and no parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     */
    protected LightSource(
        int lightNumber,
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent)
    {
        this(
            lightNumber,
            ambientComponent,
            diffuseComponent,
            specularComponent,
            1,
            0,
            0,
            null);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with the provided light number, no
     * attenuation,no light components and with parent.
     *
     * @param lightNumber
     * @param parent
     */
    protected LightSource(int lightNumber, IPrimitive parent)
    {
        this(
            lightNumber,
            Color.WHITE,
            Color.WHITE,
            Color.WHITE,
            1f,
            0f,
            0f,
            parent);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with the provided light
     * components,light number no attenuation, and with parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param parent
     */
    protected LightSource(
        int lightNumber,
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        IPrimitive parent)
    {
        this(
            lightNumber,
            ambientComponent,
            diffuseComponent,
            specularComponent,
            1f,
            0f,
            0f,
            parent);
    }


    /**
     * Creates a new DirectionalLightSource with the provided light components,
     * no attenuation, and no parent.
     */
    public void paint(GL2 gl2)
    {
        gl2.glLightfv(
            lightNumber,
            GLLightingFunc.GL_AMBIENT,
            ambientComponent.toArray(),
            0);
        gl2.glLightfv(
            lightNumber,
            GLLightingFunc.GL_DIFFUSE,
            diffuseComponent.toArray(),
            0);
        gl2.glLightfv(
            lightNumber,
            GLLightingFunc.GL_SPECULAR,
            specularComponent.toArray(),
            0);
        gl2.glLightf(
            lightNumber,
            GLLightingFunc.GL_CONSTANT_ATTENUATION,
            constantAttenuationFactor);
        gl2.glLightf(
            lightNumber,
            GLLightingFunc.GL_LINEAR_ATTENUATION,
            linearAttenuationFactor);
        gl2.glLightf(
            lightNumber,
            GLLightingFunc.GL_QUADRATIC_ATTENUATION,
            quadraticAttenuationFactor);
        gl2.glEnable(lightNumber);

    }


    /**
     * Creates a new DirectionalLightSource with the provided light components,
     * no attenuation, and no parent.
     */
    public Integer getLightNumber()
    {

        return lightNumber;
    }


    /**
     * Creates a new DirectionalLightSource with the provided light components,
     * no attenuation, and no parent.
     */
    public IColor getAmbientComponent()
    {
        IColor returnValue;
        try
        {
            ambientComponentLock.lock();
            returnValue = this.ambientComponent;
        }
        finally
        {
            ambientComponentLock.unlock();
        }
        return returnValue;
    }


    /**
     *
     Sets the ambient component.
     */
    public void setAmbientComponent(IColor ambientComponent)
    {

        try
        {
            ambientComponentLock.lock();
            this.ambientComponent = ambientComponent;
        }
        finally
        {
            ambientComponentLock.unlock();
        }

    }


    /**
     * Returns the diffuse component.
     */
    public IColor getDiffuseComponent()
    {
        IColor returnValue;
        try
        {
            diffuseComponentLock.lock();
            returnValue = diffuseComponent;
        }
        finally
        {
            diffuseComponentLock.unlock();
        }
        return returnValue;
    }


    /**
     * Sets the diffuse component
     */
    public void setDiffuseComponent(IColor diffuseComponent)
    {

        try
        {
            diffuseComponentLock.lock();
            this.diffuseComponent = diffuseComponent;
        }
        finally
        {
            diffuseComponentLock.unlock();
        }

    }


    /**
     * Returns the specular component.
     */
    public IColor getSpecularComponent()
    {
        IColor returnValue;
        try
        {
            specularComponentLock.lock();
            returnValue = this.specularComponent;
        }
        finally
        {
            specularComponentLock.unlock();
        }
        return returnValue;
    }


    /**
     * Sets the specular component.
     */
    public void setSpecularComponent(IColor specularComponent)
    {

        try
        {
            specularComponentLock.lock();
            this.specularComponent = specularComponent;
        }
        finally
        {
            ambientComponentLock.unlock();
        }

    }


    /**
     * Sets the specular component.
     */
    public float getConstantAttenuationFactor()
    {
        float returnValue;
        try
        {
            constantAttenuationFactorLock.lock();
            returnValue = constantAttenuationFactor;
        }
        finally
        {
            constantAttenuationFactorLock.unlock();
        }
        return returnValue;
    }


    /**
     * Sets the constant attenuation factor.
     */
    public void setConstantAttenuationFactor(float constantAttenuationFactor)
    {

        try
        {
            constantAttenuationFactorLock.lock();
            this.constantAttenuationFactor = constantAttenuationFactor;
        }
        finally
        {
            constantAttenuationFactorLock.unlock();
        }

    }


    /**
     * Returns the linear attenuation factor.
     */
    public float getLinearAttenuationFactor()
    {
        float returnValue;
        try
        {
            linearAttenuationFactorLock.lock();
            returnValue = linearAttenuationFactor;
        }
        finally
        {
            linearAttenuationFactorLock.unlock();
        }
        return returnValue;
    }


    /**
     * Sets the linear attenuation factor.
     */
    public void setLinearAttenuationFactor(float linearAttenuationFactor)
    {

        try
        {
            linearAttenuationFactorLock.lock();
            this.linearAttenuationFactor = linearAttenuationFactor;
        }
        finally
        {
            constantAttenuationFactorLock.unlock();
        }

    }


    /**
     * Returns the quadratic attenuation factor
     */
    public float getQuadraticAttenuationFactor()
    {
        float returnValue;
        try
        {
            quadraticAttenuationFactorLock.lock();
            returnValue = quadraticAttenuationFactor;
        }
        finally
        {
            quadraticAttenuationFactorLock.unlock();
        }
        return returnValue;
    }


    /**
     * Sets the quadratic attenuation factor.
     */
    public void setQuadraticAttenuationFactor(float quadraticAttenuationFactor)
    {

        try
        {
            quadraticAttenuationFactorLock.lock();
            this.quadraticAttenuationFactor = quadraticAttenuationFactor;
        }
        finally
        {
            quadraticAttenuationFactorLock.unlock();
        }

    }

}
