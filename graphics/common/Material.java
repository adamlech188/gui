package cs3744.graphics.common;

import javax.media.opengl.GL;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import cs3744.graphics.interfaces.IColor;
import javax.media.opengl.GL2;
import cs3744.graphics.interfaces.IMaterial;

// -------------------------------------------------------------------------
/**
 * Class that stores all the necessary information for material.
 *
 * @author Adam Lech (PID: adaml8)
 * @version Aug 10, 2012
 */
public class Material
    implements IMaterial
{
    private final IColor    diffuseComponent;
    private final IColor    specularComponent;
    private final IColor    emissiveComponent;
    private final IColor    ambientComponent;
    private final float     phongCooefficient;
    /**
     * Black plastic .
     */
    public static IMaterial BLACK_PLASTIC   = new Material(
                                                new Color(0, 0, 0),
                                                new Color(0.01f, 0.01f, 0.01f),
                                                new Color(0.5f, 0.5f, 0.5f),
                                                32f,
                                                null);
    /**
     * Brass .
     */
    public static IMaterial BRASS           = new Material(new Color(
                                                0.329412f,
                                                0.223529f,
                                                0.027451f), new Color(
                                                0.780392f,
                                                0.568627f,
                                                0.113725f), new Color(
                                                0.992157f,
                                                0.941176f,
                                                0.807843f), 27.8974f, null);

    /**
     * Bronz
     */
    public static IMaterial BRONZE          = new Material(new Color(
                                                0.2125f,
                                                0.1275f,
                                                0.054f), new Color(
                                                0.714f,
                                                0.4284f,
                                                0.18144f), new Color(
                                                0.393548f,
                                                0.271906f,
                                                0.166721f), 25.6f, null);
    /**
     * Chrome .
     */
    public static IMaterial CHROME          = new Material(new Color(
                                                0.25f,
                                                0.25f,
                                                0.25f), new Color(
                                                0.4f,
                                                0.4f,
                                                0.4f), new Color(
                                                0.774597f,
                                                0.774597f,
                                                0.774597f), 76.8f, null);
    /**
     * Copper .
     */
    public static IMaterial COPPER          = new Material(new Color(
                                                0.19125f,
                                                0.0735f,
                                                00225f), new Color(
                                                0.7038f,
                                                0.27048f,
                                                0.0828f), new Color(
                                                0.256777f,
                                                0.137622f,
                                                0.086014f), 12.8f, null);
    /**
     * Gold .
     */
    public static IMaterial GOLD            = new Material(new Color(
                                                0.24725f,
                                                0.1995f,
                                                0.0745f), new Color(
                                                0.75164f,
                                                0.60648f,
                                                0.22648f), new Color(
                                                0.628281f,
                                                0.555802f,
                                                0.366065f), 51.2f, null);
    /**
     * Pewter .
     */
    public static IMaterial PEWTER          = new Material(new Color(
                                                0.10588f,
                                                0.058824f,
                                                0.113725f), new Color(
                                                0.427451f,
                                                0.470588f,
                                                0.541176f), new Color(
                                                0.3333f,
                                                0.3333f,
                                                0.521569f), 9.84615f, null);
    /**
     * Silver .
     */
    public static IMaterial SILVER          = new Material(new Color(
                                                0.19225f,
                                                0.19225f,
                                                0.19225f), new Color(
                                                0.50754f,
                                                0.50754f,
                                                0.50754f), new Color(
                                                0.508273f,
                                                0.508273f,
                                                0.508273f), 51.2f, null);
    /**
     * Polished silver .
     */
    public static IMaterial POLISHED_SILVER = new Material(new Color(
                                                0.23125f,
                                                0.23125f,
                                                0.23125f), new Color(
                                                0.2775f,
                                                0.2775f,
                                                0.2775f), new Color(
                                                0.773911f,
                                                0.773911f,
                                                0.773911f), 89.6f, null);


    // ----------------------------------------------------------
    /**
     * Creates a black material..
     */
    public Material()
    {
        this(Color.BLACK, Color.BLACK, Color.BLACK, 1f);
    }


    // ----------------------------------------------------------
    /**
     * Create a new Material object.
     *
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param phongCoefficient
     */
    public Material(
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        Float phongCoefficient)
    {
        this(
            ambientComponent,
            diffuseComponent,
            specularComponent,
            phongCoefficient,
            null);

    }


    // ----------------------------------------------------------
    /**
     * Creates a new Material with the provided ambient, diffuse, specular, and
     * emissive components with the provided phong coefficient.
     *
     * @param ambientComponent
     * @param diffuseComponent
     * @param specularComponent
     * @param phongCooefficient
     * @param emissiveComponent
     */
    public Material(
        IColor ambientComponent,
        IColor diffuseComponent,
        IColor specularComponent,
        Float phongCooefficient,
        IColor emissiveComponent)
    {
        this.ambientComponent = ambientComponent;
        this.diffuseComponent = diffuseComponent;
        this.specularComponent = specularComponent;
        this.phongCooefficient = phongCooefficient;
        this.emissiveComponent = emissiveComponent;
    }


    /**
     * Applies the attribute to the drawing context.
     */
    public void applyAttribute(GL2 gl2)
    {
        gl2.glMaterialfv(
            GL.GL_FRONT,
            GLLightingFunc.GL_AMBIENT,
            this.ambientComponent.toArray(),
            0);
        gl2.glMaterialfv(
            GL.GL_FRONT,
            GLLightingFunc.GL_DIFFUSE,
            this.diffuseComponent.toArray(),
            0);
        gl2.glMaterialfv(
            GL.GL_FRONT,
            GLLightingFunc.GL_SPECULAR,
            this.specularComponent.toArray(),
            0);
        if (emissiveComponent != null)
        {
            gl2.glMaterialfv(
                GL.GL_FRONT,
                GLLightingFunc.GL_EMISSION,
                this.emissiveComponent.toArray(),
                0);
        }
    }


    /**
     * Applies the attribute to the drawing context.
     */
    @Override
    public IColor getAmbientComponent()
    {

        return ambientComponent;
    }


    /**
     * Returns the diffuse component.
     */
    @Override
    public IColor getDiffuseComponent()
    {

        return diffuseComponent;
    }


    /**
     * Returns the specular component.
     */
    @Override
    public IColor getSpecularComponent()
    {

        return specularComponent;
    }


    /**
     * Returns the Phong coefficient.
     */
    @Override
    public float getPhongCoefficient()
    {

        return phongCooefficient;
    }


    /**
     * Returns the Phong coefficient.
     */
    @Override
    public IColor getEmissiveComponent()
    {

        return emissiveComponent;
    }

}
