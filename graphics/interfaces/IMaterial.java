/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * The <CODE>IMaterial</CODE> interface provides a contract for classes
 * implementing a material with diffuse, specular, ambient, and emissive
 * components.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IMaterial
        extends IAttribute {

    /**
     * Returns the ambient component.
     * 
     * @return the ambient component.
     */
    public abstract IColor getAmbientComponent();


    /**
     * Returns the diffuse component.
     * 
     * @return the diffuse component.
     */
    public abstract IColor getDiffuseComponent();


    /**
     * Returns the specular component.
     * 
     * @return the specular component.
     */
    public abstract IColor getSpecularComponent();


    /**
     * Returns the Phong coefficient.
     * 
     * @return the Phong coefficient.
     */
    public abstract float getPhongCoefficient();


    /**
     * Returns the emissive component.
     * 
     * @return the emissive component.
     */
    public abstract IColor getEmissiveComponent();
}
