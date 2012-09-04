/**
 *
 */
package cs3744.gui;


import java.io.Serializable;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLJPanel;

import cs3744.common.Property;
import cs3744.graphics.common.solution.Color;
import cs3744.graphics.interfaces.IColor;
import cs3744.gui.common.Dimension;
import cs3744.gui.common.Font;
import cs3744.gui.common.Point;
import cs3744.gui.common.interfaces.IDimension;
import cs3744.gui.common.interfaces.IFont;
import cs3744.gui.common.interfaces.IGraphics;
import cs3744.gui.common.interfaces.IPoint;
import cs3744.gui.events.interfaces.IFocusEvent;
import cs3744.gui.events.interfaces.IGUIEvent;
import cs3744.gui.events.interfaces.IKeyEvent;
import cs3744.gui.events.interfaces.IMouseEvent;
import cs3744.gui.interfaces.IComponent;
import cs3744.gui.interfaces.IContainer;
import cs3744.gui.interfaces.ILayoutManager;
import cs3744.gui.interfaces.IWindow;


/**
 * The <CODE>Component</CODE> class provides a reference implementation of the
 * <CODE>IComponent</CODE> interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public class Component
        implements IComponent, Serializable {

    private static final long serialVersionUID = 1L;


    private final Property<String> name;
    private final Property<IContainer> parent;
    private final Property<IColor> backgroundColor;
    private final Property<IColor> foregroundColor;
    private final Property<IPoint> location;
    private final Property<IDimension> size;
    private final Property<IPoint> absoluteLocation;
    private final Property<IPoint> viewportLocation;
    private final Property<IDimension> viewportSize;
    private final Property<IFont> font;
    private final Property<Integer> borderWidth;


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getName()
     */
    @Override
    public String getName() {

        return this.name.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {

        this.name.setPropertyValue(name);
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getParent()
     */
    @Override
    public IContainer getParent() {

        return this.parent.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#setParent(cs3744.gui.interfaces.IContainer
     * )
     */
    @Override
    public void setParent(IContainer parent) {

        this.parent.setPropertyValue(parent);
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getBackgroundColor()
     */
    @Override
    public IColor getBackgroundColor() {

        return this.backgroundColor.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#setBackgroundColor(cs3744.graphics.
     * interfaces.IColor)
     */
    @Override
    public void setBackgroundColor(IColor backgroundColor) {

        this.backgroundColor.setPropertyValue(backgroundColor);
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getForegroundColor()
     */
    @Override
    public IColor getForegroundColor() {

        return this.foregroundColor.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#setForegroundColor(cs3744.graphics.
     * interfaces.IColor)
     */
    @Override
    public void setForegroundColor(IColor foregroundColor) {

        this.foregroundColor.setPropertyValue(foregroundColor);
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getLocation()
     */
    @Override
    public IPoint getLocation() {

        return this.location.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#setLocation(cs3744.gui.common.interfaces
     * .IPoint)
     */
    @Override
    public void setLocation(IPoint location) {

        this.location.setPropertyValue(new Point(location));
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getSize()
     */
    @Override
    public IDimension getSize() {

        return this.size.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#setSize(cs3744.gui.common.interfaces
     * .IDimension)
     */
    @Override
    public void setSize(IDimension size) {

        this.size.setPropertyValue(new Dimension(size));
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getFont()
     */
    @Override
    public IFont getFont() {

        if (this.font == null) {
            if (this.parent == null) {
                return Font.DEFAULT_FONT;
            }
            else {
                return this.getParent().getFont();
            }
        }
        return this.font.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#setFont(cs3744.gui.common.interfaces
     * .IFont)
     */
    @Override
    public void setFont(IFont font) {

        this.font.setPropertyValue(font);
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.ITextField#getBorderWidth()
     */
    @Override
    public int getBorderWidth() {

        return this.borderWidth.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.ITextField#setBorderWidth(int)
     */
    @Override
    public void setBorderWidth(int borderWidth) {

        if (borderWidth < 0) {
            this.borderWidth.setPropertyValue(0);
        }
        else {
            this.borderWidth.setPropertyValue(borderWidth);
        }

    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getAbsoluteLocation()
     */
    @Override
    public IPoint getAbsoluteLocation() {

        return this.absoluteLocation.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getViewportLocation()
     */
    @Override
    public IPoint getViewportLocation() {

        return this.viewportLocation.getPropertyValue();
    }


    /**
     * Sets the viewport location. (thread-safe access)
     *
     * @param viewportLocation
     *            the viewportLocation to set
     */
    protected void setViewportLocation(IPoint viewportLocation) {

        this.viewportLocation.setPropertyValue(viewportLocation);
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#getViewportSize()
     */
    @Override
    public IDimension getViewportSize() {

        return this.viewportSize.getPropertyValue();
    }


    /**
     * Sets the viewport size. (thread-safe access)
     *
     * @param viewportSize
     *            the viewportSize to set
     */
    protected void setViewportSize(IDimension viewportSize) {

        this.viewportSize.setPropertyValue(viewportSize);
    }


    /**
     * Constructs a new <CODE>IComponent</CODE> object. It can be extended
     * directly to create a component.
     */
    protected Component() {

        this.name = new Property<String>("name");
        this.parent = new Property<IContainer>("parent");


        this.location = new Property<IPoint>("location", new Point(0, 0));
        this.size = new Property<IDimension>("size", new Dimension(0, 0));
        this.font = new Property<IFont>("font", Font.DEFAULT_FONT);
        this.borderWidth = new Property<Integer>("borderWidth", 0);
        this.backgroundColor = new Property<IColor>("backgroundColor",
                Color.LIGHT_GRAY);
        this.foregroundColor = new Property<IColor>("foregroundColor",
                Color.BLACK);

        this.absoluteLocation = new Property<IPoint>("absoluteLocation",
                new Point(0, 0));
        this.viewportLocation = new Property<IPoint>("viewportLocation",
                new Point(0, 0));
        this.viewportSize = new Property<IDimension>("viewportSize",
                new Dimension(0, 0));

    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#invalidate()
     */
    @Override
    public void invalidate() {

        if (this.getParent() == null) {

            try {
                IWindow window = (IWindow) this;
                if (window != null) {
                    GLAutoDrawable drawable = window.getDrawable();
                    GLJPanel gljPanel = (GLJPanel) drawable;
                    gljPanel.repaint();
                }
            }
            catch (ClassCastException e) {
                System.out.println("Trying to invalidate component " + this
                        + " without a "
                        + "parent! Did you forget to add the component "
                        + "to a container?");
            }

        }
        else {
            this.getParent().invalidate();
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#fireGUIEvent(cs3744.gui.events.interfaces
     * .IGUIEvent)
     */
    @Override
    public void fireGUIEvent(IGUIEvent event) {

        if (event instanceof IMouseEvent) {

            IMouseEvent mouseEvent = (IMouseEvent) event;
            if (this.contains(mouseEvent.getLocation())
                    && !(this instanceof IContainer)) {
                this.processGUIEvent(event);
            }
        }
        else if (event instanceof IKeyEvent) {
            IKeyEvent keyEvent = (IKeyEvent) event;
            if (!(this instanceof IContainer)) {
                this.processGUIEvent(keyEvent);
            }
        }
        else if (event instanceof IFocusEvent) {
            IFocusEvent focusEvent = (IFocusEvent) event;
            switch (focusEvent.getModifier()) {
                case FOCUS_GAINED:
                    if (this.getParent() != null) {
                        this.getParent().fireGUIEvent(focusEvent);
                    }
                    break;
                case FOCUS_LOST:
                default:
                    this.processGUIEvent(focusEvent);
            }

        }
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#processGUIEvent(cs3744.gui.events.interfaces
     * .IGUIEvent)
     */
    @Override
    public void processGUIEvent(IGUIEvent event) {

         System.out.println(this + " processed\n\t" + event);
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IComponent#doLayout()
     */
    @Override
    public void doLayout() {

        IPoint parentViewportLocation = null;
        IDimension parentViewportSize = null;
        ILayoutManager currentLayout = null;

        if (this.getParent() == null) {

            // Attempting to get the layout from us as a window
            currentLayout = ((IWindow) this).getLayout();
        }
        else {

            // Trying to get the layout from our parent.
            currentLayout = this.getParent().getLayout();
        }


        if (currentLayout == null) {
            if (this.getParent() == null) {

                this.absoluteLocation.setPropertyValue(new Point(this
                        .getLocation()));
                parentViewportLocation = new Point(0, 0);
                parentViewportSize = new Dimension(((IWindow) this).getWidth(),
                        ((IWindow) this).getHeight());
            }
            else {
                this.absoluteLocation
                        .setPropertyValue(addPoints(this.getParent()
                                .getAbsoluteLocation(), this.getLocation()));
                parentViewportLocation = this.getParent().getViewportLocation();
                parentViewportSize = this.getParent().getViewportSize();
            }
            this.viewportLocation.setPropertyValue(clamp(
                    this.getAbsoluteLocation(), parentViewportLocation,
                    parentViewportSize));
            this.viewportSize.setPropertyValue(clamp(
                    this.getAbsoluteLocation(), this.getSize(),
                    parentViewportLocation, parentViewportSize));
        }
        else {
            this.absoluteLocation.setPropertyValue(this.getLocation());
            this.viewportLocation.setPropertyValue(this.getLocation());
            this.viewportSize.setPropertyValue(this.getSize());
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#paint(cs3744.gui.common.interfaces.IGraphics
     * )
     */
    @Override
    public void paint(IGraphics graphics) {

        setUpPaint(graphics);
        paintBody(graphics);
        cleanUpPaint(graphics);
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#setUpPaint(cs3744.gui.common.interfaces
     * .IGraphics)
     */
    @Override
    public void setUpPaint(IGraphics graphics) {

        GL2 gl2 = graphics.getGL2();
        float frameWidth = graphics.getWidth();
        float frameHeight = graphics.getHeight();


        gl2.glScissor(this.getViewportLocation().getX(), graphics.getHeight()
                - this.getViewportLocation().getY()
                - this.getViewportSize().getHeight(), this.getViewportSize()
                .getWidth(), this.getViewportSize().getHeight());

        gl2.glPushMatrix();
        gl2.glLoadIdentity();

        gl2.glTranslatef(this.getAbsoluteLocation().getX() / frameWidth, -(this
                .getAbsoluteLocation().getY() + this.getSize().getHeight())
                / frameHeight, 0.0f);
        gl2.glScalef(this.getSize().getWidth() / frameWidth, this.getSize()
                .getHeight() / frameHeight, 1.0f);
        this.getBackgroundColor().applyAttribute(gl2);

    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#paintBody(cs3744.gui.common.interfaces
     * .IGraphics)
     */
    @Override
    public void paintBody(IGraphics graphics) {

        graphics.drawUnitSquare();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#cleanUpPaint(cs3744.gui.common.interfaces
     * .IGraphics)
     */
    @Override
    public void cleanUpPaint(IGraphics graphics) {

        GL2 gl2 = graphics.getGL2();
        gl2.glPopMatrix();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IComponent#contains(cs3744.gui.common.interfaces
     * .IPoint)
     */
    @Override
    public boolean contains(IPoint p) {

        if (this.getViewportLocation() != null
                && this.getViewportSize() != null) {
            if (p.getX() >= this.getViewportLocation().getX()
                    && p.getX() <= this.getViewportLocation().getX()
                            + this.getViewportSize().getWidth()) {
                if (p.getY() >= this.getViewportLocation().getY()
                        && p.getY() <= this.getViewportLocation().getY()
                                + this.getViewportSize().getHeight()) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Returns the sum of two <CODE>IPoint</CODE> objects.
     *
     * @param firstPoint
     *            the first <CODE>IPoint</CODE> object.
     * @param secondPoint
     *            the second <CODE>IPoint</CODE> object.
     * @return the sum of two <CODE>IPoint</CODE> objects.
     */
    private IPoint addPoints(IPoint firstPoint, IPoint secondPoint) {

        return new Point(firstPoint.getX() + secondPoint.getX(),
                firstPoint.getY() + secondPoint.getY());
    }


    @Override
    public IPoint clamp(IPoint myAbsoluteLocation,
            IPoint parentViewportLocation, IDimension parentViewportSize) {

        int viewportX = myAbsoluteLocation.getX();
        int viewportY = myAbsoluteLocation.getY();

        int minX = parentViewportLocation.getX();
        int maxX = parentViewportLocation.getX()
                + parentViewportSize.getWidth();

        int minY = parentViewportLocation.getY();
        int maxY = parentViewportLocation.getY()
                + parentViewportSize.getHeight();


        if (viewportX > maxX) {
            viewportX = maxX;
        }
        else if (viewportX < minX) {
            viewportX = minX;
        }
        if (viewportY > maxY) {
            viewportY = maxY;
        }
        else if (viewportY < minY) {
            viewportY = minY;
        }
        return new Point(viewportX, viewportY);
    }


    /**
     * Returns a rectangle within the viewport that is the closest to the
     * component's size.
     *
     * @param myLocation
     *            the component location
     * @param mySize
     *            the component size.
     * @param parentViewportLocation
     *            the viewport location.
     * @param parentViewportSize
     *            the viewport size.
     * @return the effective component size.
     */
    @Override
    public IDimension clamp(IPoint myLocation, IDimension mySize,
            IPoint parentViewportLocation, IDimension parentViewportSize) {

        int vminX = myLocation.getX();
        int vmaxX = myLocation.getX() + mySize.getWidth();

        int vminY = myLocation.getY();
        int vmaxY = myLocation.getY() + mySize.getHeight();

        int minX = parentViewportLocation.getX();
        int maxX = parentViewportLocation.getX()
                + parentViewportSize.getWidth();

        int minY = parentViewportLocation.getY();
        int maxY = parentViewportLocation.getY()
                + parentViewportSize.getHeight();

        if (vminX > maxX) {
            vminX = maxX;
        }
        else if (vminX < minX) {
            vminX = minX;
        }
        if (vmaxX < minX) {
            vmaxX = minX;
        }
        else if (vmaxX > maxX) {
            vmaxX = maxX;
        }
        if (vminY > maxY) {
            vminY = maxY;
        }
        else if (vminY < minY) {
            vminY = minY;
        }
        if (vmaxY < minY) {
            vmaxY = minY;
        }
        else if (vmaxY > maxY) {
            vmaxY = maxY;
        }
        return new Dimension(vmaxX - vminX, vmaxY - vminY);
    }


    @Override
    public String toString() {

        return "IComponent(" + this.getName() + "@" + this.getLocation() + ", "
                + this.getSize() + ")";
    }

}
