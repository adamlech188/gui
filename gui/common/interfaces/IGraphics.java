package cs3744.gui.common.interfaces;


import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import cs3744.graphics.interfaces.IColor;
import cs3744.gui.common.interfaces.IPoint;


/**
 * The <CODE>IGraphics</CODE> interface provides a contract for classes that
 * provide a graphics context with rendering capabilities.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGraphics {

    /**
     * Sets the drawable of this graphics object.
     * 
     * @param drawable
     *            the drawable object.
     */
    public abstract void setDrawable(GLAutoDrawable drawable);


    /**
     * Gets the drawable object.
     * 
     * @return the drawable object.
     */
    public abstract GLAutoDrawable getDrawable();


    /**
     * Returns the drawable object.
     * 
     * @return the drawable object.
     */
    public abstract GL2 getGL2();


    /**
     * Returns the width of the drawable object
     * 
     * @return the width.
     */
    public abstract int getWidth();


    /**
     * Returns the height of the drawable object.
     * 
     * @return the height.
     */
    public abstract int getHeight();


    /**
     * Sets the font of the text renderer.
     * 
     * @param font
     *            the font to set.
     */
    public abstract void setFont(IFont font);


    /**
     * 
     * @param color
     */
    public abstract void setFontColor(IColor color);


    /**
     * 
     * @param size
     */
    public abstract void setFontSize(int size);


    /**
     * Returns the dimensions of the provided text in the given font.
     * 
     * @param text
     *            the text.
     * @return the dimensions of the text rendered in the provided font.
     */
    public abstract IDimension getTextDimensions(String text);


    /**
     * Sets up the graphics context for text rendering.
     */
    public abstract void setUpTextRendering();


    /**
     * Renders the provided text at the location with the provided size.
     * 
     * @param text
     *            the text to render.
     * @param location
     *            the location.
     * @param size
     *            the size.
     */
    public abstract void renderText(String text, IPoint location,
            IDimension size);


    /**
     * Renders the provided text at the location with the provided size and the
     * given offsets.
     * 
     * @param text
     *            the text to render.
     * @param location
     *            the location.
     * @param size
     *            the size.
     * @param xOffset
     *            the x offset.
     * @param yOffset
     *            the y offset.
     */
    public abstract void renderText(String text, IPoint location,
            IDimension size, int xOffset, int yOffset);


    /**
     * Cleans up the graphics context after text rendering.
     */
    public abstract void cleanUpTextRendering();


    /**
     * Draws a unit square using the current OpenGL graphics context.
     * 
     */
    public abstract void drawUnitSquare();


    /**
     * Draws a quadrilateral of the specified color within a component between
     * the provided points.
     * 
     * @param componentSize
     *            the size of the component.
     * @param bottomLeft
     *            the bottom left point.
     * @param bottomRight
     *            the bottom right point.
     * @param topRight
     *            the top right point.
     * @param topLeft
     *            the top left point.
     * @param color
     *            the color.
     */
    public abstract void drawQuadrilateral(IDimension componentSize,
            IPoint bottomLeft, IPoint bottomRight, IPoint topRight,
            IPoint topLeft, IColor color);


    /**
     * Renders a border around the component of the specified size with the
     * border width and color.
     * 
     * @param componentSize
     *            the component's size.
     * @param borderWidth
     *            the border width.
     * @param borderColor
     *            the border color.
     * 
     */
    public abstract void drawBorder(IDimension componentSize, int borderWidth,
            IColor borderColor);

}