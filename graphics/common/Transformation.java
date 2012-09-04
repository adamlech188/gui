package cs3744.graphics.common;


import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.ITransformation;
import cs3744.graphics.interfaces.IVector;
import cs3744.vectorAlgebra.Matrix4f;
import cs3744.vectorAlgebra.Tuple4f;


/**
 * Thin wrapper around a Matrix4f.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Transformation
        implements ITransformation {

    /**
     * Wrapper around the unit matrix representing no transformation.
     */
    public static ITransformation NO_TRANSFORMATION = new Transformation(
            Matrix4f.UNIT_MATRIX);


    private final Matrix4f matrix;


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.ITransformation#getMatrix()
     */
    @Override
    public Matrix4f getMatrix() {

        return this.matrix;
    }


    /**
     * Constructs a new <CODE>Transformation</CODE> by creating a new matrix
     * with the components provided.
     * 
     * @param i
     *            The x-component of the new matrix.
     * @param j
     *            The y-component of the new matrix.
     * @param k
     *            The z-component of the new matrix.
     * @param s
     *            The s-component of the new matrix.
     */
    public Transformation(IVector i, IVector j, IVector k, IPoint s) {

        this(i.getVector(), j.getVector(), k.getVector(), s.getPoint());
    }


    /**
     * Constructs a new <CODE>Transformation</CODE> by creating a new matrix
     * with the components provided.
     * 
     * @param i
     *            The x-component of the new matrix.
     * @param j
     *            The y-component of the new matrix.
     * @param k
     *            The z-component of the new matrix.
     * @param s
     *            The s-component of the new matrix.
     */
    public Transformation(Tuple i, Tuple j, Tuple k, Tuple s) {

        this(i.getTuple(), j.getTuple(), k.getTuple(), s.getTuple());
    }


    /**
     * Constructs a new <CODE>Transformation</CODE> by creating a new matrix
     * with the components provided.
     * 
     * @param i
     *            The x-component of the new matrix.
     * @param j
     *            The y-component of the new matrix.
     * @param k
     *            The z-component of the new matrix.
     * @param s
     *            The s-component of the new matrix.
     */
    public Transformation(final Tuple4f i, final Tuple4f j, final Tuple4f k,
            Tuple4f s) {

        this(new Matrix4f(i, j, k, s));
    }


    /**
     * Constructs a new <CODE>Matrix</CODE> with the provided component.
     * 
     * @param matrix
     *            The component of this matrix.
     */
    public Transformation(Matrix4f matrix) {

        this.matrix = matrix;
    }


    /**
     * Copy Constructor.
     * 
     * @param matrix
     *            The matrix to be copied.
     */
    public Transformation(ITransformation matrix) {

        this.matrix = matrix.getMatrix();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.graphics.common.solution.ITransformation#toColumnMajorArray()
     */
    @Override
    public float[] toColumnMajorArray() {

        return this.matrix.toColumnMajorArray();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.ITransformation#toRowMajorArray()
     */
    @Override
    public float[] toRowMajorArray() {

        return this.matrix.toRowMajorArray();
    }


    @Override
    public String toString() {

        return this.matrix.toString();
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            if (obj instanceof Transformation) {
                ITransformation otherTransformation = (ITransformation) obj;

                return this.matrix.equals(otherTransformation.getMatrix());
            }
        }

        return false;
    }


    @Override
    public int hashCode() {

        return this.matrix.hashCode();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.common.solution.ITransformation#transpose()
     */
    @Override
    public ITransformation transpose() {

        return new Transformation(this.matrix.transpose());
    }
}
