/**
 * 
 */
package cs3744.gui.interfaces;


/**
 * The <CODE>IGridLayout</CODE> provides a contract for layout managers that lay
 * out their containers in a grid.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGridLayout
        extends ILayoutManager {


    /**
     * Returns the number of rows this grid layout has.
     * 
     * @return the number of rows.
     */
    public abstract int getRows();


    /**
     * Sets the number of rows this grid layout has.
     * 
     * @param rows
     *            number of rows.
     */
    public abstract void setRows(int rows);


    /**
     * Returns the number of columns this grid layout has.
     * 
     * @return the number of columns.
     */
    public abstract int getColumns();


    /**
     * Sets the number of columns this grid layout has.
     * 
     * @param columns
     *            number of columns.
     */
    public abstract void setColumns(int columns);


    /**
     * Returns the preferred horizontal spacing between components.
     * 
     * @return the horizontal gap.
     */
    public abstract int getHorizontalGap();


    /**
     * Sets the preferred horizontal spacing between components.
     * 
     * @param horizontalGap
     *            the horizontal gap to set.
     */
    public abstract void setHorizontalGap(int horizontalGap);


    /**
     * Returns the preferred vertical spacing between components.
     * 
     * @return the vertical gap.
     */
    public abstract int getVerticalGap();


    /**
     * Sets the preferred vertical spacing between components.
     * 
     * @param verticalGap
     *            the vertical gap to set.
     */
    public abstract void setVerticalGap(int verticalGap);


    /**
     * The GridLayout divides its width and height into equal chunks depending
     * on the number of rows and columns (and adjusted by horizontal and
     * vertical gaps between the rows and columns).
     * 
     * It then traverses the list of components in-order, setting each child's
     * location and calling its <CODE>doLayout()</CODE> method.
     * 
     * @param container
     *            the container to lay out.
     */
    @Override
    public abstract void layoutContainer(IContainer container);
}
