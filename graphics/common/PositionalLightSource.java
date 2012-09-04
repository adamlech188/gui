package cs3744.graphics.common;

import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IPositionalLightSource;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.interfaces.LightSourceType;
import cs3744.graphics.primitives.Point;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;

// -------------------------------------------------------------------------
/**
 * Class that stores all the data necessary for the positional light source.
 *
 * @author Adam Lech(PID: adaml8)
 * @version Aug 10, 2012
 */
public class PositionalLightSource
    extends LightSource
    implements IPositionalLightSource
{
    private final Lock positionLock;
    private IPoint     position;


    // ----------------------------------------------------------
    /**
     * Creates a new PositionalLightSource with white light components, no
     * attenuation, and no parent.
     *
     * @param lightNumber
     */
    public PositionalLightSource(int lightNumber)
    {
        this(lightNumber, null);

    }


    // ----------------------------------------------------------
    /**
     * Creates a new PositionalLightSource with white light components, the
     * provided attenuation and no parent.
     *
     * @param lightNumber
     * @param constantAttenuationFactor
     * @param linearAttenuationFactor
     * @param quadraticAttenuationFactor
     */
    protected PositionalLightSource(
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
     * Creates a new PositionalLightSource with white light components, the
     * provided attenuation, and the provided parent.
     *
     * @param lightNumber
     * @param constantAttenuationFactor
     * @param linearAttenuationFactor
     * @param quadraticAttenuationFactor
     * @param parent
     */
    protected PositionalLightSource(
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
        position = new Point(0f, 0f, -1f);
        positionLock = new ReentrantLock();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new PositionalLightSource with the provided light components,
     * no attenuation, and no parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     */
    protected PositionalLightSource(
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
     * Creates a new PositionalLightSource with the provided light components,
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
    protected PositionalLightSource(
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
     * Creates a new PositionalLightSource with the provided light components,
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
    protected PositionalLightSource(
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
            new Point(0f, 0f, -1f),
            parent);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new PositionalLightSource with the provided light components,
     * attenuation, position and parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param constantAttenuationFactor
     * @param linearAttenuationFactor
     * @param quadraticAttenuationFactor
     * @param position
     * @param parent
     */
    protected PositionalLightSource(
        int lightNumber,
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        float constantAttenuationFactor,
        float linearAttenuationFactor,
        float quadraticAttenuationFactor,
        IPoint position,
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
        this.position = position;
        positionLock = new ReentrantLock();

    }


    // ----------------------------------------------------------
    /**
     * Creates a new PositionalLightSource with the provided light components,
     * no attenuation, and the provided parent.
     *
     * @param lightNumber
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param parent
     */
    protected PositionalLightSource(
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
        position = new Point(0f, 0f, -1f);
        positionLock = new ReentrantLock();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new PositionalLightSource with white light components, no
     * attenuation, and the provided parent..
     *
     * @param lightNumber
     * @param parent
     */
    PositionalLightSource(int lightNumber, IPrimitive parent)
    {
        super(lightNumber, parent);
        position = new Point(0f, 0f, -1f);
        positionLock = new ReentrantLock();

    }


    // ----------------------------------------------------------
    /**
     * Copy constructor.
     *
     * @param primitiveToCopy
     * @param parent
     */
    PositionalLightSource(
        IPositionalLightSource primitiveToCopy,
        IPrimitive parent)
    {
        super(primitiveToCopy, parent);
        position = new Point(0f, 0f, -1f);
        positionLock = new ReentrantLock();

    }


    /**
     * Returns the type of this light source.
     */
    public LightSourceType getLightSourceType()
    {

        return LightSourceType.POSITIONAL_LIGHT;
    }


    /**
     * Provides a copy of the current object.
     */
    public IPrimitive copy()
    {

        return this.copy(null);
    }


    /**
     * Provides a copy of the current object and sets its parent.
     */
    public IPrimitive copy(IPrimitive parent)
    {
        IPrimitive copy = new PositionalLightSource(this, parent);
        return copy;
    }


    /**
     * Returns the position of this light.
     */
    public IPoint getPosition()
    {
        IPoint returnValue;
        try
        {
            positionLock.lock();
            returnValue = position;
        }
        finally
        {
            positionLock.unlock();
        }
        return returnValue;
    }


    /**
     * Sets the position of this light.
     */
    public void setPosition(IPoint position)
    {
        try
        {
            positionLock.lock();
            this.position = position;
        }
        finally
        {
            positionLock.unlock();
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
            position.toArray(),
            0);

    }
}
