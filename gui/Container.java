/**
 *
 */
package cs3744.gui;


import java.util.List;

import cs3744.common.IndexedProperty;
import cs3744.common.Property;
import cs3744.gui.common.Point;
import cs3744.gui.common.interfaces.IGraphics;
import cs3744.gui.common.interfaces.IPoint;
import cs3744.gui.events.interfaces.IFocusEvent;
import cs3744.gui.events.interfaces.IGUIEvent;
import cs3744.gui.events.interfaces.IKeyEvent;
import cs3744.gui.events.interfaces.IMouseEvent;
import cs3744.gui.events.interfaces.IFocusEvent.FocusModifier;
import cs3744.gui.events.solution.FocusEvent;
import cs3744.gui.interfaces.IComponent;
import cs3744.gui.interfaces.IContainer;
import cs3744.gui.interfaces.ILayoutManager;


/**
 * The <CODE>Container</CODE> class provides a reference implementation of the
 * <CODE>IContainer</CODE> interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public class Container
        extends Component
        implements IContainer {


    private static final long serialVersionUID = 1L;


    private final Property<ILayoutManager> layout;
    private final IndexedProperty<IComponent> components;
    private final Property<IComponent> lastFocusedComponent;


    /**
     * Constructs a new <CODE>IContainer</CODE>. <CODE>IContainer</CODE>s can be
     * extended directly, but are lightweight in this case and must be contained
     * by a parent somewhere higher up in the component tree that is native
     * (e.g., <CODE>IFrame</CODE>).
     *
     * The default layout manager is null.
     */
    public Container() {

        this(null);
    }


    /**
     * Constructs a new <CODE>IContainer</CODE>. <CODE>IContainer</CODE>s can be
     * extended directly, but are lightweight in this case and must be contained
     * by a parent somewhere higher up in the component tree that is native
     * (e.g., <CODE>IFrame</CODE>).
     *
     * @param layout
     *            the layout of this container.
     */
    public Container(ILayoutManager layout) {

        super();

        this.layout = new Property<ILayoutManager>("layout", layout);
        this.components = new IndexedProperty<IComponent>("components");
        this.lastFocusedComponent = new Property<IComponent>(
                "lastFocusedComponent", null);
    }


    /**
     * Constructs a new <CODE>IContainer</CODE>. <CODE>IContainer</CODE>s can be
     * extended directly, but are lightweight in this case and must be contained
     * by a parent somewhere higher up in the component tree that is native
     * (e.g., <CODE>IFrame</CODE>).
     *
     * @param layout
     *            the layout of this container.
     * @param name
     *            the name of the container.
     */
    public Container(ILayoutManager layout, String name) {

        super();
        super.setName(name);

        this.layout = new Property<ILayoutManager>("layout", layout);
        this.components = new IndexedProperty<IComponent>("components");
        this.lastFocusedComponent = new Property<IComponent>(
                "lastFocusedComponent", null);
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IContainer#getLayout()
     */
    @Override
    public ILayoutManager getLayout() {

        return this.layout.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IContainer#setLayout(cs3744.gui.interfaces.
     * ILayoutManager)
     */
    @Override
    public void setLayout(ILayoutManager layout) {

        this.layout.setPropertyValue(layout);
        this.invalidate();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IContainer#add(cs3744.gui.interfaces.IComponent)
     */
    @Override
    public IComponent add(IComponent component) {

        if (component != null && this.components.addPropertyValue(component)) {
            if (component.getParent() != null) {
                component.getParent().remove(component);
            }
            component.setParent(this);

            if (this.getLayout() != null) {
                this.getLayout().addLayoutComponent(component.getName(),
                        component);
            }
            return component;
        }
        else {
            return null;
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IContainer#add(cs3744.gui.interfaces.IComponent,
     * int)
     */
    @Override
    public IComponent add(IComponent component, int index) {

        if (component != null) {
            this.components.addPropertyValue(component, index);

            if (component.getParent() != null) {
                component.getParent().remove(component);
            }
            component.setParent(this);

            if (this.layout != null) {
                this.getLayout().addLayoutComponent(component.getName(),
                        component);
            }
            return component;
        }
        else {
            return null;
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * cs3744.gui.interfaces.IContainer#remove(cs3744.gui.interfaces.IComponent)
     */
    @Override
    public void remove(IComponent component) {

        if (this.components.removePropertyValue(component)) {
            component.setParent(null);
            this.getLayout().removeLayoutComponent(component);
            invalidate();
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IContainer#getComponents()
     */
    @Override
    public List<IComponent> getComponents() {

        return this.components.getPropertyValues();
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IContainer#getLastFocusedComponent()
     */
    @Override
    public IComponent getLastFocusedComponent() {

        return this.lastFocusedComponent.getPropertyValue();
    }


    /*
     * (non-Javadoc)
     *
     * @see cs3744.gui.interfaces.IContainer#setLastFocusedComponent(cs3744.gui.
     * interfaces.IComponent)
     */
    @Override
    public void setLastFocusedComponent(IComponent component) {

        this.lastFocusedComponent.setPropertyValue(component);
    }


    @Override
    public void doLayout() {

        super.doLayout();
        if (this.getLayout() == null) {
            for (int i = this.components.getPropertyValues().size() - 1; i >= 0; i--) {

                this.components.getPropertyValues().get(i).doLayout();
            }
        }
        else {
            this.getLayout().layoutContainer(this);
        }
    }


    @Override
    public void paint(IGraphics graphics) {

        super.paint(graphics);
        for (int i = this.components.getPropertyValues().size() - 1; i >= 0; i--) {

            this.components.getPropertyValues().get(i).paint(graphics);
        }
    }


    @Override
    public void fireGUIEvent(IGUIEvent event) {

        if (event instanceof IMouseEvent) {
            IMouseEvent mouseEvent = (IMouseEvent) event;
            IPoint mouseEventLocation = new Point(mouseEvent.getX(),
                    mouseEvent.getY());
            if (this.contains(mouseEventLocation)) {

                for (IComponent component : this.components.getPropertyValues()) {

                    if (component.contains(mouseEventLocation)) {
                        component.fireGUIEvent(mouseEvent);
                        break;
                    }
                }
            }
        }
        else if (event instanceof IKeyEvent) {
            IKeyEvent keyEvent = (IKeyEvent) event;
            for (IComponent component : this.components.getPropertyValues()) {
                component.fireGUIEvent(keyEvent);
            }
        }
        else if (event instanceof IFocusEvent) {
            IFocusEvent focusEvent = (IFocusEvent) event;
            switch (focusEvent.getModifier()) {
                case FOCUS_GAINED:
                    if (this.getParent() != null) {

                        this.getParent().fireGUIEvent(focusEvent);
                    }
                    else {

                        IComponent lastFocus = this.getLastFocusedComponent();

                        IComponent newFocusedComponent = (IComponent) focusEvent
                                .getSource();
                        this.setLastFocusedComponent(newFocusedComponent);

                        IFocusEvent focusLostEvent = new FocusEvent(
                                newFocusedComponent, FocusModifier.FOCUS_LOST,
                                lastFocus);

                        for (IComponent component : this.components
                                .getPropertyValues()) {
                            component.fireGUIEvent(focusLostEvent);
                        }
                    }
                    break;
                case FOCUS_LOST:

                    for (IComponent component : this.components
                            .getPropertyValues()) {
                        component.fireGUIEvent(focusEvent);
                    }
                    break;
            }
        }
    }


    @Override
    public String toString() {

        return "IContainer(" + this.getName() + "@" + this.getLocation() + ", "
                + this.getSize() + ")";
    }
}
