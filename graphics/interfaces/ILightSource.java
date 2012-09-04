/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * The <CODE>ILightSource</CODE> interface provides a contract for classes
 * implementing light sources.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public interface ILightSource
        extends IPrimitive {


    /**
     * Returns the light's number.
     * 
     * @return the light's number.
     */
    public abstract Integer getLightNumber();


    /**
     * Returns the type of this light source.
     * 
     * @return the type of this light source.
     */
    public abstract LightSourceType getLightSourceType();


    /**
     * Returns the ambient component.
     * 
     * @return the ambient component.
     */
    public abstract IColor getAmbientComponent();


    /**
     * Sets the ambient component.
     * 
     * @param ambientComponent
     *            the ambient component.
     */
    public abstract void setAmbientComponent(IColor ambientComponent);


    /**
     * Returns the diffuse component.
     * 
     * @return the diffuse component.
     */
    public abstract IColor getDiffuseComponent();


    /**
     * Sets the diffuse component
     * 
     * @param diffuseComponent
     *            the diffuse component.
     */
    public abstract void setDiffuseComponent(IColor diffuseComponent);


    /**
     * Returns the specular component.
     * 
     * @return the specular component.
     */
    public abstract IColor getSpecularComponent();


    /**
     * Sets the specular component.
     * 
     * @param specularComponent
     *            the specular component.
     */
    public abstract void setSpecularComponent(IColor specularComponent);


    /**
     * Returns the constant attenuation factor.
     * 
     * @return the constant attenuation factor.
     */
    public abstract float getConstantAttenuationFactor();


    /**
     * Sets the constant attenuation factor.
     * 
     * @param constantAttenuationFactor
     *            the constant attenuation factor.
     */
    public abstract void setConstantAttenuationFactor(
            float constantAttenuationFactor);


    /**
     * Returns the linear attenuation factor.
     * 
     * @return the linear attenuation factor.
     */
    public abstract float getLinearAttenuationFactor();


    /**
     * Sets the linear attenuation factor.
     * 
     * @param linearAttenuationFactor
     *            the linear attenuation factor.
     */
    public abstract void setLinearAttenuationFactor(
            float linearAttenuationFactor);


    /**
     * Returns the quadratic attenuation factor.
     * 
     * @return the quadratic attenuation factor.
     */
    public abstract float getQuadraticAttenuationFactor();


    /**
     * Sets the quadratic attenuation factor.
     * 
     * @param quadraticAttenuationFactor
     *            the quadratic attenuation factor.
     */
    public abstract void setQuadraticAttenuationFactor(
            float quadraticAttenuationFactor);
}
