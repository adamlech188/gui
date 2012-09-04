/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * The <CODE>LightSourceType</CODE> Enum enumerates the different types of light
 * sources.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public enum LightSourceType {
    /**
     * A directional light source.
     */
    DIRECTIONAL_LIGHT,
    /**
     * A positional light source.
     */
    POSITIONAL_LIGHT,
    /**
     * A spot light.
     */
    SPOTLIGHT;
}
