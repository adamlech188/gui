/**
 * 
 */
package cs3744.vectorAlgebra;


/**
 * Provides a 4x4 matrix using tuples in homogeneous representation.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Matrix4f {

    /**
     * The unit matrix.
     */
    public static final Matrix4f UNIT_MATRIX = new Matrix4f(
            Vector4f.I_UNIT_VECTOR, Vector4f.J_UNIT_VECTOR,
            Vector4f.K_UNIT_VECTOR, Point4f.ORIGIN);

    private final Tuple4f i;
    private final Tuple4f j;
    private final Tuple4f k;
    private final Tuple4f s;


    /**
     * Getter for the first column of the matrix.
     * 
     * @return The first column of the matrix.
     */
    public Tuple4f getI() {

        return this.i;
    }


    /**
     * Getter for the second column of the matrix.
     * 
     * @return The second column of the matrix.
     */
    public Tuple4f getJ() {

        return this.j;
    }


    /**
     * Getter for the third column of the matrix.
     * 
     * @return The third column of the matrix.
     */
    public Tuple4f getK() {

        return this.k;
    }


    /**
     * Getter for the fourth column of the matrix.
     * 
     * @return The fourth column of the matrix.
     */
    public Tuple4f getS() {

        return this.s;
    }


    /**
     * Constructs a new <CODE>Matrix4f</CODE> object with the provided columns.
     * 
     * @param i
     *            The first column of the matrix.
     * @param j
     *            The second column of the matrix.
     * @param k
     *            The third column of the matrix.
     * @param s
     *            The fourth column of the matrix.
     */
    public Matrix4f(Tuple4f i, Tuple4f j, Tuple4f k, Tuple4f s) {

        if (i == null || j == null || k == null || s == null) {
            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing components!");
        }
        this.i = i;
        this.j = j;
        this.k = k;
        this.s = s;
    }


    /**
     * Copy Constructor.
     * 
     * @param matrixToCopy
     *            the matrix to copy.
     */
    public Matrix4f(Matrix4f matrixToCopy) {

        this(matrixToCopy.getI(), matrixToCopy.getJ(), matrixToCopy.getK(),
                matrixToCopy.getS());
    }


    /**
     * Returns this matrix in a column-major array.
     * 
     * @return the matrix in a column-major array.
     */
    public float[] toColumnMajorArray() {

        float array[] = new float[16];

        array[0] = this.i.getX();
        array[1] = this.i.getY();
        array[2] = this.i.getZ();
        array[3] = this.i.getW();

        array[4] = this.j.getX();
        array[5] = this.j.getY();
        array[6] = this.j.getZ();
        array[7] = this.j.getW();

        array[8] = this.k.getX();
        array[9] = this.k.getY();
        array[10] = this.k.getZ();
        array[11] = this.k.getW();

        array[12] = this.s.getX();
        array[13] = this.s.getY();
        array[14] = this.s.getZ();
        array[15] = this.s.getW();

        return array;
    }


    /**
     * Returns this matrix in a row-major array.
     * 
     * @return the matrix in a row-major array.
     */
    public float[] toRowMajorArray() {

        float array[] = new float[16];

        array[0] = this.i.getX();
        array[1] = this.j.getX();
        array[2] = this.k.getX();
        array[3] = this.s.getX();

        array[4] = this.i.getY();
        array[5] = this.j.getY();
        array[6] = this.k.getY();
        array[7] = this.s.getY();

        array[8] = this.i.getZ();
        array[9] = this.j.getZ();
        array[10] = this.k.getZ();
        array[11] = this.s.getZ();

        array[12] = this.i.getW();
        array[13] = this.j.getW();
        array[14] = this.k.getW();
        array[15] = this.s.getW();

        return array;
    }


    /**
     * Returns the transpose matrix of this instance.
     * 
     * @return the transpose matrix.
     */
    public Matrix4f transpose() {

        Tuple4f it = new Tuple4f(this.i.getX(), this.j.getX(), this.k.getX(),
                this.s.getX());
        Tuple4f jt = new Tuple4f(this.i.getY(), this.j.getY(), this.k.getY(),
                this.s.getY());
        Tuple4f kt = new Tuple4f(this.i.getZ(), this.j.getZ(), this.k.getZ(),
                this.s.getZ());
        Tuple4f st = new Tuple4f(this.i.getW(), this.j.getW(), this.k.getW(),
                this.s.getW());

        return new Matrix4f(it, jt, kt, st);
    }


    @Override
    public String toString() {

        String returnValue = "\n|";
        float array[] = this.toRowMajorArray();

        for (int v = 0; v < 16; v++) {
            if (v > 0 && v % 4 == 0) {
                returnValue += "\t\t|\n|";
            }
            returnValue += "\t\t" + String.format("%.3f", array[v]);
        }

        returnValue += "\t\t|\n";

        return returnValue;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {

            if (obj instanceof Matrix4f) {

                Matrix4f otherMatrix = (Matrix4f) obj;

                float[] thisMatrix = this.toColumnMajorArray();
                float[] other = otherMatrix.toColumnMajorArray();

                for (int index = 0; index < thisMatrix.length; index++) {

                    if (Math.abs(thisMatrix[index] - other[index]) > Tuple4f.DELTA) {
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }


    @Override
    public int hashCode() {

        int hashCode = 7;
        float[] thisMatrix = this.toColumnMajorArray();

        for (int index = 0; index < thisMatrix.length; index++) {
            hashCode += new Float((thisMatrix[index])).hashCode();
        }
        return hashCode;
    }
}
