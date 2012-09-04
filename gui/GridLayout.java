/**
 *
 */
package cs3744.gui;

import cs3744.gui.common.Point;
import cs3744.gui.common.Dimension;
import cs3744.gui.common.interfaces.IDimension;
import java.util.List;
import cs3744.common.Property;
import cs3744.common.IndexedProperty;
import cs3744.gui.interfaces.IComponent;
import cs3744.gui.interfaces.IContainer;
import cs3744.gui.interfaces.IGridLayout;

/**
 * Class that provides all necessary information for the GridLayout.
 *
 * @author Adam Lech
 * @version 1.0
 */

public class GridLayout
    implements IGridLayout
{

    private final IndexedProperty<IComponent> layoutComponents;
    private final Property<Integer>           rows;
    private final Property<Integer>           columns;
    private final Property<Integer>           horizontalGap;
    private final Property<Integer>           verticalGap;


    /**
     * Basic 1,1 grid.
     */
    public GridLayout()
    {

        this(1, 1);
    }


    /**
     * @param rows
     * @param columns
     */
    public GridLayout(int rows, int columns)
    {

        this(rows, columns, 0, 0);
    }


    /**
     * @param rows
     * @param columns
     * @param horizontalGap
     * @param verticalGap
     */
    public GridLayout(int rows, int columns, int horizontalGap, int verticalGap)
    {

        layoutComponents = new IndexedProperty<IComponent>("layoutComponents");
        this.rows = new Property<Integer>("rows", Integer.valueOf(rows));
        this.columns =
            new Property<Integer>("columns", Integer.valueOf(columns));
        this.horizontalGap =
            new Property<Integer>(
                "horizontalGap",
                Integer.valueOf(horizontalGap));
        this.verticalGap =
            new Property<Integer>("verticalGap", Integer.valueOf(verticalGap));

    }


    @Override
    public synchronized void addLayoutComponent(
        String name,
        IComponent component)
    {

        layoutComponents.addPropertyValue(component);

    }


    @Override
    public synchronized void addLayoutComponent(
        String name,
        IComponent component,
        int index)
    {

        layoutComponents.addPropertyValue(component, index);

    }


    @Override
    public synchronized void removeLayoutComponent(IComponent component)
    {

        layoutComponents.removePropertyValue(component);

    }


    @Override
    public void layoutContainer(IContainer container)
    {
        int index = 0;
        List<IComponent> children = container.getComponents();
        int width = container.getSize().getWidth();
        int height = container.getSize().getHeight();
        int childWidth =
            (width - getHorizontalGap() * (getColumns() - 1)) / getColumns();
        int childHeight =
            (height - getVerticalGap() * (getRows() - 1)) / getRows();
        IDimension size = new Dimension(childWidth, childHeight);
        for (int i = 0; i < getRows(); i++)
        {
            for (int j = 0; j < this.getColumns(); j++)
                if (index < children.size())
                {

                    IComponent child = children.get(index++);
                    child.setSize(size);
                    int addedHorizontalGap = 0;
                    int addedVerticalGap = 0;
                    if (j > 0)
                        addedHorizontalGap = getHorizontalGap();
                    if (i > 0)
                        addedVerticalGap = getVerticalGap();
                    int childXOffset =
                        container.getLocation().getX() + j * childWidth
                            + addedHorizontalGap;
                    int childYOffset =
                        container.getLocation().getY() + i * childHeight
                            + addedVerticalGap;
                    child.setLocation(new Point(childXOffset, childYOffset));
                    child.doLayout();
                }

        }

    }


    @Override
    public int getRows()
    {

        return this.rows.getPropertyValue().intValue();

    }


    @Override
    public void setRows(int rows)
    {

        this.rows.setPropertyValue(rows);

    }


    @Override
    public int getColumns()
    {

        return this.columns.getPropertyValue().intValue();

    }


    @Override
    public void setColumns(int columns)
    {

        this.columns.setPropertyValue(columns);

    }


    @Override
    public int getHorizontalGap()
    {

        return this.horizontalGap.getPropertyValue().intValue();

    }


    @Override
    public void setHorizontalGap(int horizontalGap)
    {

        this.horizontalGap.setPropertyValue(horizontalGap);

    }


    @Override
    public int getVerticalGap()
    {

        return this.verticalGap.getPropertyValue().intValue();

    }


    @Override
    public void setVerticalGap(int verticalGap)
    {

        this.verticalGap.setPropertyValue(verticalGap);

    }

}
