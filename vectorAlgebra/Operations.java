package cs3744.vectorAlgebra;


/**
 * This class is a utility class for Tuple and Matrix operations.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Operations {

    /**
     * Converts an angle in degree to the same angle in radians.
     * 
     * @param angleInDegree
     *            the angle in degrees.
     * @return the angle in radians.
     */
    public static float degreeToRadian(final float angleInDegree) {

        return angleInDegree * ((float) Math.PI / 180f);
    }


    /**
     * Converts an angle in radians to the same angle in degree.
     * 
     * @param angleInRadians
     *            the angle in radians.
     * @return the angle in degree.
     */
    public static float radianToDegree(final float angleInRadians) {

        return angleInRadians * (180f / (float) Math.PI);
    }


    /**
     * Adds the left-hand tuple to the right-hand tuple.
     * 
     * @param lhs
     *            The left-hand tuple.
     * @param rhs
     *            The right-hand tuple.
     * @return The vector sum of the tuples.
     * @throws IllegalArgumentException
     *             if two points are provided.
     * @throws ArithmeticException
     *             if the result is not a homogeneous coordinate.
     */
    protected static Tuple4f add(final Tuple4f lhs, final Tuple4f rhs) {

        Tuple4f returnValue = new Tuple4f(lhs.getX() + rhs.getX(), lhs.getY()
                + rhs.getY(), lhs.getZ() + rhs.getZ(), lhs.getW() + rhs.getW());

        return returnValue;
    }


    /**
     * Adds the left-hand vector to the right-hand vector.
     * 
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand vector.
     * @return The vector sum of the vectors.
     */
    public static Vector4f add(final Vector4f lhs, final Vector4f rhs) {

        Vector4f returnValue = new Vector4f(Operations.add((Tuple4f) lhs,
                (Tuple4f) rhs));

        return returnValue;
    }


    /**
     * Adds the left-hand point to the right-hand vector.
     * 
     * @param lhs
     *            The left-hand point.
     * @param rhs
     *            The right-hand vector.
     * @return The vector sum of the point and the vector.
     */
    public static Point4f add(final Point4f lhs, final Vector4f rhs) {

        Point4f returnValue = new Point4f(Operations.add((Tuple4f) lhs,
                (Tuple4f) rhs));

        return returnValue;
    }


    /**
     * Adds the left-hand vector to the right-hand point.
     * 
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand point.
     * @return The vector sum of the vector and the point.
     */
    public static Point4f add(final Vector4f lhs, final Point4f rhs) {

        return Operations.add(rhs, lhs);
    }


    /**
     * Subtracts the right-hand tuple from the left-hand tuple.
     * 
     * @param lhs
     *            the left-hand tuple.
     * @param rhs
     *            the right-hand tuple.
     * @return the difference between the tuples.
     * @throws ArithmeticException
     *             if the operation is not a valid arithmetic operation.
     */
    protected static Tuple4f subtract(final Tuple4f lhs, final Tuple4f rhs) {

        // System.out.println("In tuple subtract.");
        if (Math.abs(0f - lhs.getW()) < Tuple4f.DELTA
                && Math.abs(0f - rhs.getW()) > Tuple4f.DELTA) {
            throw new IllegalArgumentException(
                    "Cannot add subtract a point from a vector!");
        }
        Tuple4f returnValue = new Tuple4f(lhs.getX() - rhs.getX(), lhs.getY()
                - rhs.getY(), lhs.getZ() - rhs.getZ(), lhs.getW() - rhs.getW());

        return returnValue;

    }


    /**
     * Subtracts the right-hand point from the left-hand point.
     * 
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand point.
     * @return the difference between the points.
     */
    public static Vector4f subtract(final Point4f lhs, final Point4f rhs) {

        return new Vector4f(Operations.subtract((Tuple4f) lhs, (Tuple4f) rhs));
    }


    /**
     * Subtracts the right-hand vector from the left-hand point.
     * 
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand vector.
     * @return the difference between the point and the vector.
     */
    public static Point4f subtract(final Point4f lhs, final Vector4f rhs) {

        return new Point4f(Operations.subtract((Tuple4f) lhs, (Tuple4f) rhs));
    }


    /**
     * Multiplies the tuple by the provided scalar.
     * 
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @return The scaled tuple.
     * @throws ArithmeticException
     *             if the operation is not a valid arithmetic operation.
     */
    protected static Tuple4f multiply(final Tuple4f lhs, final float rhs) {

        if (lhs.getW() == 1f) {
            throw new IllegalArgumentException("Cannot scale a point!");
        }
        return new Tuple4f(lhs.getX() * rhs, lhs.getY() * rhs,
                lhs.getZ() * rhs, lhs.getW() * rhs);

    }


    /**
     * Multiplies the vector by the provided scalar.
     * 
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @return The scaled tuple.
     */
    public static Vector4f multiply(final Vector4f lhs, final float rhs) {

        return new Vector4f(Operations.multiply((Tuple4f) lhs, rhs));
    }


    /**
     * Multiplies the vector by the provided scalar.
     * 
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The tuple.
     * @return The scaled tuple.
     */
    public static Vector4f multiply(final float lhs, final Vector4f rhs) {

        return new Vector4f(Operations.multiply((Tuple4f) rhs, lhs));
    }


    /**
     * Multiplies the matrix by the provided tuple.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the tuple.
     * @return The resulting tuple.
     */
    public static Tuple4f multiply(final Matrix4f lhs, final Tuple4f rhs) {

        float ix = lhs.getI().getX();
        float iy = lhs.getI().getY();
        float iz = lhs.getI().getZ();
        float iw = lhs.getI().getW();

        float jx = lhs.getJ().getX();
        float jy = lhs.getJ().getY();
        float jz = lhs.getJ().getZ();
        float jw = lhs.getJ().getW();

        float kx = lhs.getK().getX();
        float ky = lhs.getK().getY();
        float kz = lhs.getK().getZ();
        float kw = lhs.getK().getW();

        float sx = lhs.getS().getX();
        float sy = lhs.getS().getY();
        float sz = lhs.getS().getZ();
        float sw = lhs.getS().getW();

        float vx = rhs.getX();
        float vy = rhs.getY();
        float vz = rhs.getZ();
        float vw = rhs.getW();


        float x = (ix * vx) + (jx * vy) + (kx * vz) + (sx * vw);

        float y = (iy * vx) + (jy * vy) + (ky * vz) + (sy * vw);

        float z = (iz * vx) + (jz * vy) + (kz * vz) + (sz * vw);

        float w = (iw * vx) + (jw * vy) + (kw * vz) + (sw * vw);


        return new Tuple4f(x, y, z, w);
    }


    /**
     * Multiplies the matrix by the provided point.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the point.
     * @return The resulting point.
     */
    public static Point4f multiply(final Matrix4f lhs, final Point4f rhs) {


        return new Point4f(Operations.multiply(lhs, (Tuple4f) rhs));
    }


    /**
     * Multiplies the matrix by the provided vector.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the vector.
     * @return The resulting vector.
     */
    public static Vector4f multiply(final Matrix4f lhs, final Vector4f rhs) {


        return new Vector4f(Operations.multiply(lhs, (Tuple4f) rhs));
    }


    /**
     * Multiplies the two 4x4 matrices provided.
     * 
     * @param lhs
     *            the left-hand side matrix.
     * @param rhs
     *            the right-hand side matrix.
     * @return the resulting matrix.
     */
    public static Matrix4f multiply(final Matrix4f lhs, final Matrix4f rhs) {


        float a_ix = lhs.getI().getX();
        float a_iy = lhs.getI().getY();
        float a_iz = lhs.getI().getZ();
        float a_iw = lhs.getI().getW();

        float a_jx = lhs.getJ().getX();
        float a_jy = lhs.getJ().getY();
        float a_jz = lhs.getJ().getZ();
        float a_jw = lhs.getJ().getW();

        float a_kx = lhs.getK().getX();
        float a_ky = lhs.getK().getY();
        float a_kz = lhs.getK().getZ();
        float a_kw = lhs.getK().getW();

        float a_sx = lhs.getS().getX();
        float a_sy = lhs.getS().getY();
        float a_sz = lhs.getS().getZ();
        float a_sw = lhs.getS().getW();


        float b_ix = rhs.getI().getX();
        float b_iy = rhs.getI().getY();
        float b_iz = rhs.getI().getZ();
        float b_iw = rhs.getI().getW();

        float b_jx = rhs.getJ().getX();
        float b_jy = rhs.getJ().getY();
        float b_jz = rhs.getJ().getZ();
        float b_jw = rhs.getJ().getW();

        float b_kx = rhs.getK().getX();
        float b_ky = rhs.getK().getY();
        float b_kz = rhs.getK().getZ();
        float b_kw = rhs.getK().getW();

        float b_sx = rhs.getS().getX();
        float b_sy = rhs.getS().getY();
        float b_sz = rhs.getS().getZ();
        float b_sw = rhs.getS().getW();


        float r_ix = (a_ix * b_ix) + (a_jx * b_iy) + (a_kx * b_iz)
                + (a_sx * b_iw);
        float r_jx = (a_ix * b_jx) + (a_jx * b_jy) + (a_kx * b_jz)
                + (a_sx * b_jw);
        float r_kx = (a_ix * b_kx) + (a_jx * b_ky) + (a_kx * b_kz)
                + (a_sx * b_kw);
        float r_sx = (a_ix * b_sx) + (a_jx * b_sy) + (a_kx * b_sz)
                + (a_sx * b_sw);


        float r_iy = (a_iy * b_ix) + (a_jy * b_iy) + (a_ky * b_iz)
                + (a_sy * b_iw);
        float r_jy = (a_iy * b_jx) + (a_jy * b_jy) + (a_ky * b_jz)
                + (a_sy * b_jw);
        float r_ky = (a_iy * b_kx) + (a_jy * b_ky) + (a_ky * b_kz)
                + (a_sy * b_kw);
        float r_sy = (a_iy * b_sx) + (a_jy * b_sy) + (a_ky * b_sz)
                + (a_sy * b_sw);


        float r_iz = (a_iz * b_ix) + (a_jz * b_iy) + (a_kz * b_iz)
                + (a_sz * b_iw);
        float r_jz = (a_iz * b_jx) + (a_jz * b_jy) + (a_kz * b_jz)
                + (a_sz * b_jw);
        float r_kz = (a_iz * b_kx) + (a_jz * b_ky) + (a_kz * b_kz)
                + (a_sz * b_kw);
        float r_sz = (a_iz * b_sx) + (a_jz * b_sy) + (a_kz * b_sz)
                + (a_sz * b_sw);


        float r_iw = (a_iw * b_ix) + (a_jw * b_iy) + (a_kw * b_iz)
                + (a_sw * b_iw);
        float r_jw = (a_iw * b_jx) + (a_jw * b_jy) + (a_kw * b_jz)
                + (a_sw * b_jw);
        float r_kw = (a_iw * b_kx) + (a_jw * b_ky) + (a_kw * b_kz)
                + (a_sw * b_kw);
        float r_sw = (a_iw * b_sx) + (a_jw * b_sy) + (a_kw * b_sz)
                + (a_sw * b_sw);


        Tuple4f i = new Tuple4f(r_ix, r_iy, r_iz, r_iw);

        Tuple4f j = new Tuple4f(r_jx, r_jy, r_jz, r_jw);

        Tuple4f k = new Tuple4f(r_kx, r_ky, r_kz, r_kw);

        Tuple4f s = new Tuple4f(r_sx, r_sy, r_sz, r_sw);

        return new Matrix4f(i, j, k, s);
    }


    /**
     * Creates the translation matrix from the provided components.
     * 
     * @param tx
     *            the x translation component.
     * @param ty
     *            the y translation component.
     * @param tz
     *            the z translation component.
     * @return the translation matrix.
     */
    public static Matrix4f translationMatrix(final float tx, final float ty,
            final float tz) {

        return new Matrix4f(Vector4f.I_UNIT_VECTOR, Vector4f.J_UNIT_VECTOR,
                Vector4f.K_UNIT_VECTOR, new Point4f(tx, ty, tz));
    }


    /**
     * Creates the inverse translation matrix from the provided components.
     * 
     * @param tx
     *            the x translation component.
     * @param ty
     *            the y translation component.
     * @param tz
     *            the z translation component.
     * @return the translation matrix.
     */
    public static Matrix4f inverseTranslationMatrix(final float tx,
            final float ty, final float tz) {

        return Operations.translationMatrix(-tx, -ty, -tz);
    }


    /**
     * Creates the scaling matrix from the provided components.
     * 
     * @param sx
     *            the x scaling component.
     * @param sy
     *            the y scaling component.
     * @param sz
     *            the z scaling component.
     * @return the scaling matrix.
     */
    public static Matrix4f scalingMatrix(final float sx, final float sy,
            final float sz) {


        return new Matrix4f(Operations.multiply(Vector4f.I_UNIT_VECTOR, sx),
                Operations.multiply(Vector4f.J_UNIT_VECTOR, sy),
                Operations.multiply(Vector4f.K_UNIT_VECTOR, sz), Point4f.ORIGIN);
    }


    /**
     * Creates the inverse scaling matrix from the provided components.
     * 
     * @param sx
     *            the x scaling component.
     * @param sy
     *            the y scaling component.
     * @param sz
     *            the z scaling component.
     * @return the scaling matrix.
     */
    public static Matrix4f inverseScalingMatrix(final float sx, final float sy,
            final float sz) {


        return Operations.scalingMatrix(1 / sx, 1 / sy, 1 / sz);
    }


    /**
     * Creates the linear scaling matrix from the provided components.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @return the scaling transformation.
     */
    public static Matrix4f linearScalingMatrix(final float scalingFactor) {


        return Operations.scalingMatrix(scalingFactor, scalingFactor,
                scalingFactor);
    }


    /**
     * Creates the inverse linear scaling matrix from the provided components.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @return the scaling transformation.
     */
    public static Matrix4f inverseLinearScalingMatrix(final float scalingFactor) {


        return Operations.inverseScalingMatrix(scalingFactor, scalingFactor,
                scalingFactor);
    }


    /**
     * Creates the shear matrix for shearing by the shearCoefficient and the
     * selected component.
     * 
     * @param shearCoefficient
     *            the x translation component.
     * @param shearBy
     *            the component to shear by.
     * @return the shear matrix.
     */
    public static Matrix4f shearMatrix(final float shearCoefficient,
            final ShearBy shearBy) {


        Vector4f i = null;
        Vector4f j = null;
        Vector4f k = null;

        switch (shearBy) {
            case X_BY_Y:
                i = Vector4f.I_UNIT_VECTOR;
                j = new Vector4f(shearCoefficient, 1f, 0f);
                k = Vector4f.K_UNIT_VECTOR;
                break;
            case X_BY_Z:
                i = Vector4f.I_UNIT_VECTOR;
                j = Vector4f.J_UNIT_VECTOR;
                k = new Vector4f(shearCoefficient, 0f, 1f);
                break;
            case Y_BY_X:
                i = new Vector4f(1f, shearCoefficient, 0f);
                j = Vector4f.J_UNIT_VECTOR;
                k = Vector4f.K_UNIT_VECTOR;
                break;
            case Y_BY_Z:
                i = Vector4f.I_UNIT_VECTOR;
                j = Vector4f.J_UNIT_VECTOR;
                k = new Vector4f(0f, shearCoefficient, 1f);
                break;
            case Z_BY_X:
                i = new Vector4f(1f, 0f, shearCoefficient);
                j = Vector4f.J_UNIT_VECTOR;
                k = Vector4f.K_UNIT_VECTOR;
                break;
            case Z_BY_Y:
                i = Vector4f.I_UNIT_VECTOR;
                j = new Vector4f(0f, 1f, shearCoefficient);
                k = Vector4f.K_UNIT_VECTOR;
                break;
            default:
                throw new IllegalArgumentException(
                        "Cannot shear by provided component!");
        }


        return new Matrix4f(i, j, k, Point4f.ORIGIN);
    }


    /**
     * Creates the inverse shear matrix for shearing by the shearCoefficient and
     * the selected component.
     * 
     * @param shearCoefficient
     *            the x translation component.
     * @param shearBy
     *            the component to shear by.
     * @return the shear matrix.
     */
    public static Matrix4f inverseShearMatrix(final float shearCoefficient,
            final ShearBy shearBy) {

        return Operations.shearMatrix(-shearCoefficient, shearBy);
    }


    /**
     * Creates the rotation matrix for a rotation around the rotation axis by
     * the provided angle.
     * 
     * @param rotationAxis
     *            the axis of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static Matrix4f rotationMatrix(final Tuple4f rotationAxis,
            final float rotationAngle) {

        float cos = (float) Math.cos(rotationAngle);
        float sin = (float) Math.sin(rotationAngle);

        float ux = rotationAxis.getX();
        float uy = rotationAxis.getY();
        float uz = rotationAxis.getZ();


        float r_xi = (1 - cos) * (ux * ux) + cos;
        float r_yi = (uy * ux) * (1 - cos) + (uz * sin);
        float r_zi = (uz * ux) * (1 - cos) - (uy * sin);

        float r_xj = (uy * ux) * (1 - cos) - (uz * sin);
        float r_yj = (uy * uy) * (1 - cos) + cos;
        float r_zj = (uy * uz) * (1 - cos) + (ux * sin);

        float r_xk = (uz * ux) * (1 - cos) + (uy * sin);
        float r_yk = (uy * uz) * (1 - cos) - (ux * sin);
        float r_zk = (uz * uz) * (1 - cos) + cos;


        Vector4f i = new Vector4f(r_xi, r_yi, r_zi);
        Vector4f j = new Vector4f(r_xj, r_yj, r_zj);
        Vector4f k = new Vector4f(r_xk, r_yk, r_zk);

        return new Matrix4f(i, j, k, Point4f.ORIGIN);
    }


    /**
     * Creates the inverse of the rotation matrix for a rotation around the
     * rotation axis by the provided angle.
     * 
     * @param rotationAxis
     *            the axis of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static Matrix4f inverseRotationMatrix(final Vector4f rotationAxis,
            final float rotationAngle) {

        return Operations.rotationMatrix(rotationAxis, rotationAngle)
                .transpose();
    }


    /**
     * Creates the rotation matrix around the x-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static Matrix4f xRotationMatrix(final float rotationAngle) {

        return Operations.rotationMatrix(Vector4f.I_UNIT_VECTOR, rotationAngle);
    }


    /**
     * Creates the inverse of the rotation matrix around the x-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static Matrix4f inverseXRotationMatrix(final float rotationAngle) {

        return Operations.rotationMatrix(Vector4f.I_UNIT_VECTOR, rotationAngle)
                .transpose();
    }


    /**
     * Creates the rotation matrix around the y-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static Matrix4f yRotationMatrix(final float rotationAngle) {

        return Operations.rotationMatrix(Vector4f.J_UNIT_VECTOR, rotationAngle);
    }


    /**
     * Creates the inverse of the rotation matrix around the y-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static Matrix4f inverseYRotationMatrix(final float rotationAngle) {

        return Operations.rotationMatrix(Vector4f.J_UNIT_VECTOR, rotationAngle)
                .transpose();
    }


    /**
     * Creates the rotation matrix around the z-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static Matrix4f zRotationMatrix(final float rotationAngle) {

        return Operations.rotationMatrix(Vector4f.K_UNIT_VECTOR, rotationAngle);
    }


    /**
     * Creates the inverse of the rotation matrix around the z-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static Matrix4f inverseZRotationMatrix(final float rotationAngle) {

        return Operations.rotationMatrix(Vector4f.K_UNIT_VECTOR, rotationAngle)
                .transpose();
    }


    /**
     * Translates the tuple by the x, y, and z translation.
     * 
     * @param tupleToTranslate
     *            the tuple to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @return the translated tuple.
     */
    public static Tuple4f translate(final Tuple4f tupleToTranslate,
            final float xTranslation, final float yTranslation,
            final float zTranslation) {

        Matrix4f translationMatrix = Operations.translationMatrix(xTranslation,
                yTranslation, zTranslation);

        return Operations.multiply(translationMatrix, tupleToTranslate);
    }


    /**
     * Reverts the translate of the tuple by the x, y, and z translation.
     * 
     * @param tupleToTranslate
     *            the tuple to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @return the original tuple.
     */
    public static Tuple4f revertTranslate(final Tuple4f tupleToTranslate,
            final float xTranslation, final float yTranslation,
            final float zTranslation) {

        Matrix4f translationMatrix = Operations.inverseTranslationMatrix(
                xTranslation, yTranslation, zTranslation);

        return Operations.multiply(translationMatrix, tupleToTranslate);
    }


    /**
     * Scales the tuple by the x, y, and z scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @return the scaled tuple.
     */
    public static Tuple4f scale(final Tuple4f tupleToScale,
            final float xScalingFactor, final float yScalingFactor,
            final float zScalingFactor) {

        Matrix4f scaleMatrix = Operations.scalingMatrix(xScalingFactor,
                yScalingFactor, zScalingFactor);

        return Operations.multiply(scaleMatrix, tupleToScale);
    }


    /**
     * Revert scaling of the tuple by the x, y, and z scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @return the original tuple.
     */
    public static Tuple4f revertScale(final Tuple4f tupleToScale,
            final float xScalingFactor, final float yScalingFactor,
            final float zScalingFactor) {

        Matrix4f scaleMatrix = Operations.inverseScalingMatrix(xScalingFactor,
                yScalingFactor, zScalingFactor);

        return Operations.multiply(scaleMatrix, tupleToScale);
    }


    /**
     * Uniformly scales the tuple by the scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the uniformly scaled tuple.
     */
    public static Tuple4f linearScale(final Tuple4f tupleToScale,
            final float scalingFactor) {

        return Operations.scale(tupleToScale, scalingFactor, scalingFactor,
                scalingFactor);
    }


    /**
     * Reverts the uniform scale of the tuple by the scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the original tuple.
     */
    public static Tuple4f revertLinearScale(final Tuple4f tupleToScale,
            final float scalingFactor) {

        return Operations.revertScale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor);
    }


    /**
     * Shears the tuple by the provided shear factor and the provided component.
     * 
     * @param tupleToShear
     *            the tuple to shear.
     * @param shearFactor
     *            the shear factor.
     * @param shearBy
     *            identifier of the component to shear by.
     * @return the sheared tuple.
     */
    public static Tuple4f shear(final Tuple4f tupleToShear,
            final float shearFactor, final ShearBy shearBy) {

        Matrix4f shearMatrix = Operations.shearMatrix(shearFactor, shearBy);


        return Operations.multiply(shearMatrix, tupleToShear);

    }


    /**
     * Reverts the shear of the tuple by the provided shear factor and the
     * provided component.
     * 
     * @param tupleToShear
     *            the tuple to shear.
     * @param shearFactor
     *            the shear factor.
     * @param shearBy
     *            identifier of the component to shear by.
     * @return the original tuple.
     */
    public static Tuple4f revertShear(final Tuple4f tupleToShear,
            final float shearFactor, final ShearBy shearBy) {

        Matrix4f shearMatrix = Operations.inverseShearMatrix(shearFactor,
                shearBy);


        return Operations.multiply(shearMatrix, tupleToShear);

    }


    /**
     * Rotates the tuple around by the rotation angle around the rotation axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated tuple.
     * 
     */
    public static Tuple4f rotate(final Tuple4f tupleToRotate,
            final Vector4f rotationAxis, final float rotationAngle) {

        Matrix4f rotationMatrix = Operations.rotationMatrix(rotationAxis,
                rotationAngle);

        return Operations.multiply(rotationMatrix, tupleToRotate);
    }


    /**
     * Reverses the rotation of the tuple around by the rotation angle around
     * the rotation axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @return the original tuple.
     * 
     */
    public static Tuple4f revertRotate(final Tuple4f tupleToRotate,
            final Vector4f rotationAxis, final float rotationAngle) {

        Matrix4f rotationMatrix = Operations.inverseRotationMatrix(
                rotationAxis, rotationAngle);

        return Operations.multiply(rotationMatrix, tupleToRotate);
    }


    /**
     * Rotates the tuple by the rotation angle around the x-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated tuple.
     * 
     */
    public static Tuple4f rotateX(final Tuple4f tupleToRotate,
            float rotationAngle) {

        return Operations.rotate(tupleToRotate, Vector4f.I_UNIT_VECTOR,
                rotationAngle);
    }


    /**
     * Reverts rotation of the tuple by the rotation angle around the x-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the original tuple.
     * 
     */
    public static Tuple4f revertRotateX(final Tuple4f tupleToRotate,
            float rotationAngle) {

        return Operations.revertRotate(tupleToRotate, Vector4f.I_UNIT_VECTOR,
                rotationAngle);
    }


    /**
     * Rotates the tuple around by the rotation angle around the y-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated tuple.
     * 
     */
    public static Tuple4f rotateY(final Tuple4f tupleToRotate,
            float rotationAngle) {

        return Operations.rotate(tupleToRotate, Vector4f.J_UNIT_VECTOR,
                rotationAngle);
    }


    /**
     * Reverts rotation of the tuple by the rotation angle around the y-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the original tuple.
     * 
     */
    public static Tuple4f revertRotateY(final Tuple4f tupleToRotate,
            float rotationAngle) {

        return Operations.revertRotate(tupleToRotate, Vector4f.J_UNIT_VECTOR,
                rotationAngle);
    }


    /**
     * Rotates the tuple around by the rotation angle around the z-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated tuple.
     * 
     */
    public static Tuple4f rotateZ(final Tuple4f tupleToRotate,
            float rotationAngle) {

        return Operations.rotate(tupleToRotate, Vector4f.K_UNIT_VECTOR,
                rotationAngle);
    }


    /**
     * Reverts rotation of the tuple by the rotation angle around the z-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the original tuple.
     * 
     */
    public static Tuple4f revertRotateZ(final Tuple4f tupleToRotate,
            float rotationAngle) {

        return Operations.revertRotate(tupleToRotate, Vector4f.K_UNIT_VECTOR,
                rotationAngle);
    }
}
