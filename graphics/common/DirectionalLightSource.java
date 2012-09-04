package cs3744.graphics.common;

import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.GL2;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.IVector;
import cs3744.graphics.interfaces.LightSourceType;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.interfaces.IDirectionalLightSource;

// -------------------------------------------------------------------------
/**
 * This class stores all the information necessary for the directional ligth
 * source.
 *
 * @author Adam
 * @version Aug 10, 2012
 */
public class DirectionalLightSource
    extends LightSource
    implements IDirectionalLightSource

{
    private final Lock directionLock;
    private IVector    direction;


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with white light components, no
     * attenuation, and no parent.
     *
     * @param lightNumber
     */
    public DirectionalLightSource(int lightNumber)
    {
        this(lightNumber, null);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with white light components, no
     * attenuation, and the provided parent..
     *
     * @param lightNumber
     * @param parent
     */
    protected DirectionalLightSource(int lightNumber, IPrimitive parent)
    {
        super(lightNumber, parent);

        direction = new Vector(0f, 0f, -1f);
        directionLock = new ReentrantLock();

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
    protected DirectionalLightSource(
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
            null);

    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with the provided light components,
     * no attenuation, and the provided parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param parent
     */
    protected DirectionalLightSource(
        int lightNumber,
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        IPrimitive parent)
    {
        super(
            lightNumber,
            ambientComponent,
            diffuseComponent,
            specularComponent,
            parent);
        direction = new Vector(0f, 0f, -1f);
        directionLock = new ReentrantLock();
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
    protected DirectionalLightSource(
        int lightNumber,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor)
    {
        this(
            lightNumber,
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
    protected DirectionalLightSource(
        int lightNumber,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor,
        IPrimitive parent)
    {
        super(
            lightNumber,
            constantAttenuationFactor,
            linearAttenuationFactor,
            quadraticAttenuationFactor,
            parent);
        direction = new Vector(0f, 0f, -1f);
        directionLock = new ReentrantLock();

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
    protected DirectionalLightSource(
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
            new Vector(0f, 0f, 1f),
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
    protected DirectionalLightSource(
        int lightNumber,
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor,
        IPrimitive parent)
    {
        this(
            lightNumber,
            ambientComponent,
            diffuseComponent,
            specularComponent,
            constantAttenuationFactor,
            linearAttenuationFactor,
            quadraticAttenuationFactor,
            new Vector(0f, 0f, 1f),
            parent);

    }


    // ----------------------------------------------------------
    /**
     * Creates a new DirectionalLightSource with the provided light components,
     * attenuation, direction and parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param constantAttenuationFactor
     * @param linearAttenuationFactor
     * @param quadraticAttenuationFactor
     * @param direction
     * @param parent
     */
    protected DirectionalLightSource(
        int lightNumber,
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor,
        IVector direction,
        IPrimitive parent)
    {
        super(
            lightNumber,
            ambientComponent,
            diffuseComponent,
            specularComponent,
            constantAttenuationFactor,
            linearAttenuationFactor,
            quadraticAttenuationFactor,
            parent);
        this.direction = new Vector(direction, this);
        directionLock = new ReentrantLock();

    }


    // ----------------------------------------------------------
    /**
     * Copy constructor. .
     *
     * @param primitiveToCopy
     * @param parent
     */
    protected DirectionalLightSource(
        IDirectionalLightSource primitiveToCopy,
        IPrimitive parent)
    {
        super(primitiveToCopy, parent);
        direction = new Vector(0f, 0f, -1f);
        directionLock = new ReentrantLock();
    }


    /**
     * Returns the type of this light source.
     */
    public LightSourceType getLightSourceType()
    {

        return LightSourceType.DIRECTIONAL_LIGHT;
    }


    /**
     * Provides a copy of the current object.
     */

    public IPrimitive copy()
    {

        return this.copy(null);
    }


    /**
     * Provides a copy of the current object.
     */
    public IPrimitive copy(IPrimitive parent)
    {
        return new DirectionalLightSource(this, parent);
    }


    /**
     * Provides a copy of the current object.
     */
    public IVector getDirection()
    {
        IVector returnValue;
        try
        {
            directionLock.lock();
            returnValue = direction;
        }
        finally
        {
            directionLock.unlock();
        }
        return returnValue;
    }


    /**
     * Sets the direction of this light.
     */
    public void setDirection(IVector direction)
    {
        try
        {
            directionLock.lock();
            this.direction = direction;
        }
        finally
        {
            directionLock.unlock();
        }

    }


    /**
     * Paints the primitive.
     */
    @Override
    public void paint(GL2 gl2)
    {
        super.paint(gl2);
        gl2.glLightfv(
            this.getLightNumber(),
            GLLightingFunc.GL_POSITION,
            direction.toArray(),
            0);
    }

}
