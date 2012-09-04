/**
 * 
 */
package cs3744.gui.interfaces;


import cs3744.graphics.interfaces.IColor;
import cs3744.gui.common.interfaces.IDimension;
import cs3744.gui.common.interfaces.IFont;
import cs3744.gui.common.interfaces.IGraphics;
import cs3744.gui.common.interfaces.IPoint;
import cs3744.gui.events.interfaces.IGUIEvent;


/**
 * The <CODE>IComponent</CODE> interface provides a contract for non
 * menu-related components.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IComponent {


    /**
     * Gets the name of the component.
     * 
     * @return the name.
     */
    public String getName();


    /**
     * Sets the name of the component.
     * 
     * @param name
     *            the name to set.
     */
    public void setName(String name);


    /**
     * Gets the parent of the component.
     * 
     * @return the parent.
     */
    public IContainer getParent();


    /**
     * Sets the parent of the component.
     * 
     * @param parent
     *            the parent to set.
     */
    public void setParent(IContainer parent);


    /**
     * Gets the background color of the component.
     * 
     * @return the background color.
     */
    public IColor getBackgroundColor();


    /**
     * Sets the background color of the component.
     * 
     * @param backgroundColor
     *            the background color to set.
     */
    public void setBackgroundColor(IColor backgroundColor);


    /**
     * Gets the foreground color of the component.
     * 
     * @return the foreground color.
     */
    public IColor getForegroundColor();


    /**
     * Sets the foreground color of the component.
     * 
     * @param foregroundColor
     *            the foreground color to set.
     */
    public void setForegroundColor(IColor foregroundColor);


    /**
     * Gets the location of the <component (within the parent's coordinate
     * system) .
     * 
     * @return the location.
     */
    public IPoint getLocation();


    /**
     * Sets the location of the component (within the parent's coordinate
     * system).
     * 
     * @param location
     *            the location to set.
     */
    public void setLocation(IPoint location);


    /**
     * Gets the size of the component.
     * 
     * @return the size.
     */
    public IDimension getSize();


    /**
     * Sets the size of the component.
     * 
     * @param size
     *            the size to set.
     */
    public void setSize(IDimension size);


    /**
     * Gets the font of the component. If the font is null, it returns either
     * the default font (if the component's parent is also null) or the parent's
     * font.
     * 
     * @return the font.
     */
    public IFont getFont();


    /**
     * Sets the font of the component.
     * 
     * @param font
     *            the font to set
     */
    public void setFont(IFont font);


    /**
     * Returns the width of the border around the component.
     * 
     * @return the border width.
     */
    public abstract int getBorderWidth();


    /**
     * Sets the width of the border around the component. Makes sure that the
     * value is at least zero.
     * 
     * @param borderWidth
     *            the border width.
     */
    public abstract void setBorderWidth(int borderWidth);


    /**
     * Gets the absolute location of the component (within the corresponding
     * native window coordinate system).
     * 
     * @return the absolute location.
     */
    public IPoint getAbsoluteLocation();


    /**
     * Gets the viewport location of the component (within the corresponding
     * native window coordinate system).
     * 
     * @return the viewport location.
     */
    public IPoint getViewportLocation();


    /**
     * Gets the viewport size of the component.
     * 
     * @return the viewport size.
     */
    public IDimension getViewportSize();


    /**
     * Invalidates the component.
     * 
     * Invalidates the parent container if it is not null. Otherwise, since it
     * is the top-level container, retrieves the GLJpanel stored in itself (cast
     * to IWindow necessary) and issues a <CODE>repaint()</CODE> on the
     * GLJPanel.
     */
    public abstract void invalidate();


    /**
     * In case of a mouse event, the component processes the event if it
     * contains the location of the mouse event and if it is not a container.
     * 
     * In case of the key event, the component processes the event in case it is
     * not a container.
     * 
     * In case of a focus event, the component either relays the event to its
     * parent (in case of a gain of focus), or processes the event (in case of a
     * loss of focus).
     * 
     * @param event
     *            the event to be fired.
     */
    public abstract void fireGUIEvent(IGUIEvent event);


    /**
     * The base component implementation just prints out the component who
     * received the event as well as the event (for debugging purposes).
     * 
     * @param event
     *            the event to be processed.
     */
    public void processGUIEvent(IGUIEvent event);


    /**
     * There are multiple cases to consider:
     * 
     * (1) if the component is the top-level container and does have a layout,
     * or the component's parent does have a layout its absolute location and
     * viewport location are its set location, and its viewport size is its
     * size. (The component either has already been layed out by its parent, or
     * is the top-level container and does not need changes in location/size).
     * 
     * (2) if the component is not the top-level container and its parent does
     * not provide a layout, the component uses absolute positioning in
     * relationship to its parent's absolute location (clamping the value to
     * possible locations). Its viewport location is clamped based on its
     * absolute location, the paren'ts viewport location and viewport size.Its
     * viewport size is clamped based on its absolute location and the parent's
     * viewport location and viewport size.
     * 
     * (3) if the component is the top-level container and does not have a
     * layout, its absolute location is its set location, the viewport location
     * is (0, 0), and the viewport size are the dimensions of the window.
     */
    public abstract void doLayout();


    /**
     * Sets up parameters for painting
     * 
     * @param graphics
     *            the graphics context to use for painting.
     */
    public abstract void setUpPaint(IGraphics graphics);


    /**
     * Paints this component. This method is called when the contents of the
     * component should be painted; such as when the component is first being
     * shown or is damaged and in need of repair.
     * 
     * @param graphics
     *            the graphics context to use for painting.
     */
    public abstract void paint(IGraphics graphics);


    /**
     * Paints the body of the component.
     * 
     * @param graphics
     *            the graphics context to use for painting.
     */
    public void paintBody(IGraphics graphics);


    /**
     * Cleans up after painting.
     * 
     * @param graphics
     *            the graphics context to use for painting.
     */
    public void cleanUpPaint(IGraphics graphics);


    /**
     * Returns whether the component contains the point.
     * 
     * @param point
     *            the point to check.
     * @return <CODE>true</CODE> if the component contains the point;
     *         <CODE>false</CODE> otherwise.
     */
    public boolean contains(IPoint point);


    /**
     * Returns a point within the viewport that is the closest to the absolute
     * location.
     * 
     * @param absoluteLocation
     *            the absolute location.
     * @param viewportLocation
     *            the viewport location.
     * @param viewportSize
     *            the viewport size.
     * @return a point within the viewport.
     */
    public IPoint clamp(IPoint absoluteLocation, IPoint viewportLocation,
            IDimension viewportSize);


    /**
     * Returns a rectangle within the viewport that is the closest to the
     * component's size.
     * 
     * @param componentLocation
     *            the component location
     * @param componentSize
     *            the component size.
     * @param viewportLocation
     *            the viewport location.
     * @param viewportSize
     *            the viewport size.
     * @return the effective component size.
     */
    public IDimension clamp(IPoint componentLocation, IDimension componentSize,
            IPoint viewportLocation, IDimension viewportSize);


    /**
     * Returns a string representation of this <CODE>IComponent</CODE> object.
     * This method is intended to be used only for debugging purposes. The
     * content and format of the returned string might vary between
     * implementations. The returned string might be empty but cannot be null.
     * 
     * @return a string representation of this <CODE>IComponent</CODE> object.
     */
    @Override
    public String toString();
}
