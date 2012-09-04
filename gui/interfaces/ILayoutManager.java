package cs3744.gui.interfaces;


// import cs3744.gui.common.interfaces.IDimension;


/**
 * The <CODE>ILayoutManager</CODE> provides a contract for classes that lay out
 * containers.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ILayoutManager {

    /**
     * If the layout manager uses a per-component string, adds the component
     * <CODE>component</CODE> to the layout, associating it with the string
     * specified by <CODE>name</CODE>.
     * 
     * @param name
     *            the string to be associated with the component.
     * @param component
     *            the component to be added.
     */
    public void addLayoutComponent(String name, IComponent component);


    /**
     * If the layout manager uses a per-component string, adds the component
     * <CODE>component</CODE> to the layout, associating it with the string
     * specified by <CODE>name</CODE> at the index provided.
     * 
     * @param name
     *            the string to be associated with the component.
     * @param component
     *            the component to be added.
     * @param index
     *            the index at which to insert the component.
     */
    public void addLayoutComponent(String name, IComponent component, int index);


    /**
     * Removes the specified component from the layout.
     * 
     * @param component
     *            the component to be removed.
     */
    public void removeLayoutComponent(IComponent component);


    /**
     * Calculates the preferred size dimensions for the specified container,
     * given the components it contains.
     * 
     * @param parent
     *            the container to be laid out.
     * @return the preferred size dimensions.
     */
    // public IDimension preferredLayoutSize(IContainer parent);


    /**
     * Calculates the minimum size dimensions for the specified container, given
     * the components it contains.
     * 
     * @param parent
     *            the component to be laid out.
     * @return the minimum size dimensions.
     */
    // public IDimension minimumLayoutSize(IContainer parent);


    /**
     * Lays out the specified container.
     * 
     * @param parent
     *            the container to be laid out.
     */
    public void layoutContainer(IContainer parent);
}
