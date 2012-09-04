package cs3744.gui.interfaces;




/**
 * The <CODE>IListPanel</CODE> interface provides a contract for classes
 * implementing list panels.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IListPanel
        extends IPanel {


    @Override
    public abstract IComponent add(IComponent component);


    @Override
    public abstract IComponent add(IComponent component, int index);


    @Override
    public abstract void remove(IComponent component);

}