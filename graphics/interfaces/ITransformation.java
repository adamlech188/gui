package cs3744.graphics.interfaces;


import cs3744.vectorAlgebra.Matrix4f;


/**
 * The <CODE>ITransformation</CODE> interface provides a contract for classes
 * implementing Transformation matrices.
 * 
 * @author Peter J. Radics.
 * @version 1.0
 * 
 */
public interface ITransformation {

    /**
     * Returns the matrix of this transformation.
     * 
     * @return the matrix.
     */
    public abstract Matrix4f getMatrix();


    /**
     * Returns a column major array that represents this transformation.
     * 
     * @return a column major array.
     */
    public abstract float[] toColumnMajorArray();


    /**
     * Returns a column row array that represents this transformation.
     * 
     * @return a column row array.
     */
    public abstract float[] toRowMajorArray();


    /**
     * Returns a transpose of this transformation.
     * 
     * @return the transpose of this transformation.
     */
    public abstract ITransformation transpose();

}