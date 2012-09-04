package cs3744.gui.interfaces;


import java.util.List;

import cs3744.gui.common.interfaces.IGraphics;
import cs3744.gui.events.interfaces.IGUIEvent;


/**
 * The <CODE>IContainer</CODE> interface provides a contract for generic
 * container object. Containers are components that can contain other
 * components. Components added to a container are tracked in a list. The order
 * of the list will define the components' front-to-back stacking order within
 * the container. If no index is specified when adding a component to a
 * container, it will be added to the end of the list (and hence to the bottom
 * of the stacking order).
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IContainer
        extends IComponent {


    /**
     * Gets the layout manager for this container.
     * 
     * @return the layout manager.
     */
    public ILayoutManager getLayout();


    /**
     * Sets the layout manager for this container and invalidates the container.
     * 
     * @param layoutManager
     *            the layout manager to set.
     */
    public void setLayout(ILayoutManager layoutManager);


    /**
     * Appends the specified component to the end of this container. If
     * applicable, removes the component from its current parent's list of
     * components before setting this container as its parent. If a layout is
     * set, adds the component to the layout.
     * 
     * @param component
     *            the component to be added.
     * @return the component, if it was added successfully; <CODE>null</CODE>
     *         otherwise.
     */
    public IComponent add(IComponent component);


    /**
     * Appends the specified component to the end of this container. If
     * applicable, removes the component from its current parent's list of
     * components before setting this container as its parent. If a layout is
     * set, adds the component to the layout at the provided index.
     * 
     * @param component
     *            the component to be added.
     * @param index
     *            the index at which to insert the component.
     * @return the component, if it was added successfully; <CODE>null</CODE>
     *         otherwise.
     */
    public IComponent add(IComponent component, int index);


    /**
     * Removes the component from the container and sets its parent to null.
     * 
     * @param component
     *            the component to remove.
     */
    public void remove(IComponent component);


    /**
     * Returns the children of this container.
     * 
     * @return the container's children.
     */
    public List<IComponent> getComponents();


    /**
     * Returns the last focused component of this container (only used in the
     * top-level container).
     * 
     * @return the last focused component.
     */
    public abstract IComponent getLastFocusedComponent();


    /**
     * Sets the last focused component of this container (only used in the
     * top-level container).
     * 
     * @param component
     *            the last focused component.
     */
    public abstract void setLastFocusedComponent(IComponent component);


    /**
     * First calls <CODE>super.doLayout()</CODE>. If no layout manager is
     * registered, iterates through the list of its components and asks them to
     * perform their own layout. If a layout manager is registered, the layout
     * manager is asked to perform the layout of the container.
     */
    @Override
    public abstract void doLayout();


    /**
     * After a call to <CODE>super.paint(graphics)</CODE>, the container paints
     * its child components in reverse order.
     */
    @Override
    public abstract void paint(IGraphics graphics);


    /**
     * Relays GUI Events.
     * 
     * In case of a mouse event, the container forwards the event to its child
     * components if the mouse click location was within its bounds.
     * 
     * In case of a key event, the container simply forwards the event to its
     * child containers.
     * 
     * In case of a focus event, the container does one of three things:
     * 
     * (1) it forwards the event to its children (in case of a loss of focus)
     * 
     * (2) it sends the event to its parent (in case of a gain of focus in a
     * sub-container)
     * 
     * (3) it updates the last focused component and fires a loss of focus event
     * (in case of a gain of focus in the top-level container)
     * 
     */
    @Override
    public abstract void fireGUIEvent(IGUIEvent event);
}
