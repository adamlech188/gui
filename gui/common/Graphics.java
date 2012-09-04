package cs3744.gui.common;


import java.awt.geom.Rectangle2D;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import com.jogamp.opengl.util.awt.TextRenderer;

import cs3744.graphics.common.solution.Color;
import cs3744.graphics.interfaces.IColor;
import cs3744.gui.common.interfaces.IDimension;
import cs3744.gui.common.interfaces.IFont;
import cs3744.gui.common.interfaces.IGraphics;
import cs3744.gui.common.interfaces.IPoint;


/**
 * The <CODE>Graphics</CODE> class provides a wrapper around the
 * <CODE>cs3744.graphics</CODE> library.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Graphics
        implements IGraphics {

    private final Lock drawableLock;
    private GLAutoDrawable drawable;
    private final Lock textRendererLock;
    private TextRenderer textRenderer = null;
    private IColor textColor = Color.BLACK;


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#setDrawable(javax.media.opengl.
     * GLAutoDrawable )
     */
    @Override
    public void setDrawable(GLAutoDrawable drawable) {

        this.drawable = drawable;
    }


    /**
     * 
     */
    public Graphics() {

        this.drawableLock = new ReentrantLock();
        this.drawable = null;
        this.textRendererLock = new ReentrantLock();
        this.textRenderer = new TextRenderer(Font.DEFAULT_FONT.getAWTFont());
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#getDrawable()
     */
    @Override
    public GLAutoDrawable getDrawable() {

        try {

            this.drawableLock.lock();
            return this.drawable;
        }
        finally {
            this.drawableLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#getGL2()
     */
    @Override
    public GL2 getGL2() {

        try {

            this.drawableLock.lock();
            return this.drawable.getGL().getGL2();
        }
        finally {
            this.drawableLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#getWidth()
     */
    @Override
    public int getWidth() {

        try {

            this.drawableLock.lock();
            return this.drawable.getWidth();
        }
        finally {
            this.drawableLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#getHeight()
     */
    @Override
    public int getHeight() {

        try {

            this.drawableLock.lock();
            return this.drawable.getHeight();
        }
        finally {
            this.drawableLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#textBegin()
     */
    @Override
    public void setUpTextRendering() {

        try {

            this.textRendererLock.lock();
            this.textRenderer.beginRendering(this.drawable.getWidth(),
                    this.drawable.getHeight());
            this.setFontColor(this.textColor);
        }
        finally {
            this.textRendererLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.solution.common.IGraphics#setTextColor(cs3744.graphics.interfaces
     * .IColor )
     */
    @Override
    public void setFontColor(IColor color) {

        try {

            this.textRendererLock.lock();
            this.textRenderer.setColor(color.getRedComponent(),
                    color.getGreenComponent(), color.getBlueComponent(),
                    color.getAlphaComponent());
            this.textColor = color;
        }
        finally {
            this.textRendererLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#setTextSize(int)
     */
    @Override
    public void setFontSize(int size) {

        try {

            this.textRendererLock.lock();
            this.textRenderer = new TextRenderer(new java.awt.Font("SansSerif",
                    java.awt.Font.BOLD, size));
        }
        finally {
            this.textRendererLock.unlock();
        }
    }

    /* (non-Javadoc)
     * @see cs3744.gui.common.interfaces.IGraphics#setFont(cs3744.gui.common.interfaces.IFont)
     */
    @Override
    public void setFont(IFont font) {

        try {

            this.textRendererLock.lock();
            this.textRenderer = new TextRenderer(font.getAWTFont());
        }
        finally {
            this.textRendererLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#drawText(java.lang.String,
     * cs3744.gui.solution.common.interfaces.IPoint,
     * cs3744.gui.solution.common.Dimension)
     */
    @Override
    public void renderText(String text, IPoint location, IDimension size) {

        this.renderText(text, location, size, 0, 0);
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#drawText(java.lang.String,
     * cs3744.gui.solution.common.interfaces.IPoint,
     * cs3744.gui.solution.common.Dimension, int, int)
     */
    @Override
    public void renderText(String text, IPoint location, IDimension size,
            int xOffset, int yOffset) {

        try {
            this.textRendererLock.lock();

            int x = xOffset
                    + location.getX()
                    + (size.getWidth() - ((int) this.textRenderer.getBounds(
                            text).getWidth())) / 2;
            int y = -yOffset
                    + location.getY()
                    + size.getHeight()
                    - ((size.getHeight() - (int) this.textRenderer.getBounds(
                            text).getHeight())) / 2;

            this.textRenderer.draw(text, x, getHeight() - y);
        }
        finally {
            this.textRendererLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IGraphics#textEnd()
     */
    @Override
    public void cleanUpTextRendering() {

        try {

            this.textRendererLock.lock();
            this.textRenderer.endRendering();
        }
        finally {
            this.textRendererLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.common.interfaces.IGraphics#drawUnitSquare()
     */
    @Override
    public void drawUnitSquare() {

        try {

            this.drawableLock.lock();
            GL2 gl2 = this.getGL2();
            gl2.glBegin(GL2.GL_QUADS);
            gl2.glVertex2f(0.0f, 0.0f);
            gl2.glVertex2f(1.0f, 0.0f);
            gl2.glVertex2f(1.0f, 1.0f);
            gl2.glVertex2f(0.0f, 1.0f);
            gl2.glEnd();

        }
        finally {
            this.drawableLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.common.interfaces.IGraphics#drawUnitSquare()
     */
    @Override
    public void drawQuadrilateral(IDimension componentSize, IPoint bottomLeft,
            IPoint bottomRight, IPoint topRight, IPoint topLeft, IColor color) {

        try {

            this.drawableLock.lock();
            GL2 gl2 = this.getGL2();

            color.applyAttribute(gl2);

            float scalarX = 1f / (componentSize.getWidth() * 1f);
            float scalarY = 1f / (componentSize.getHeight() * 1f);


            gl2.glBegin(GL2.GL_QUADS);
            gl2.glVertex2f(bottomLeft.getX() * scalarX, bottomLeft.getY()
                    * scalarY);
            gl2.glVertex2f(bottomRight.getX() * scalarX, bottomRight.getY()
                    * scalarY);
            gl2.glVertex2f(topRight.getX() * scalarX, topRight.getY() * scalarY);
            gl2.glVertex2f(topLeft.getX() * scalarX, topLeft.getY() * scalarY);
            gl2.glEnd();

        }
        finally {
            this.drawableLock.unlock();
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.common.interfaces.IGraphics#drawBorder(cs3744.gui.common.
     * interfaces.IDimension, int, cs3744.graphics.interfaces.IColor)
     */
    @Override
    public void drawBorder(IDimension componentSize, int borderWidth,
            IColor color) {

        try {

            this.drawableLock.lock();
            GL2 gl2 = this.getGL2();

            color.applyAttribute(gl2);

            gl2.glBegin(GL.GL_LINE_LOOP);

            float borderX = (borderWidth * 1f)
                    / (componentSize.getWidth() * 1f);
            float borderY = (borderWidth * 1f)
                    / (componentSize.getHeight() * 1f);

            float startX = borderX;
            float startY = 1f - borderY;

            float endX = 1f - borderX;
            float endY = borderY;

            gl2.glVertex2f(startX, startY);
            gl2.glVertex2f(endX, startY);
            gl2.glVertex2f(endX, endY);
            gl2.glVertex2f(startX, endY);


            gl2.glEnd();
        }
        finally {
            this.drawableLock.unlock();
        }
    }


    @Override
    public IDimension getTextDimensions(final String text) {

        try {

            this.textRendererLock.lock();
            Rectangle2D boundingBox = this.textRenderer.getBounds(text);

            return new Dimension((int) boundingBox.getWidth(),
                    (int) boundingBox.getHeight());

        }
        finally {
            this.textRendererLock.unlock();
        }
    }
}
