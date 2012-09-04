package cs3744.graphics.interfaces;


/**
 * The <CODE>ILine</CODE> interface provides a contract for classes implementing
 * lines.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ILine
        extends IPrimitive {

    /**
     * Returns the source of this line.
     * 
     * @return the source.
     */
    public abstract IPoint getSource();


    /**
     * Returns the sink of this line.
     * 
     * @return the sink
     */
    public abstract IPoint getSink();

}