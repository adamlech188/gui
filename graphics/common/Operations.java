/**
 * 
 */
package cs3744.graphics.common;


import cs3744.graphics.interfaces.ILine;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IRotation;
import cs3744.graphics.interfaces.IScaling;
import cs3744.graphics.interfaces.IShear;
import cs3744.graphics.interfaces.ITransformation;
import cs3744.graphics.interfaces.ITranslation;
import cs3744.graphics.interfaces.IVector;
import cs3744.graphics.primitives.Line;
import cs3744.graphics.primitives.Point;


/**
 * Provides a wrapper around the cs3744.vectorAlgebra.Operations class for
 * convenience.
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

        return cs3744.vectorAlgebra.Operations.degreeToRadian(angleInDegree);
    }


    /**
     * Converts an angle in radians to the same angle in degree.
     * 
     * @param angleInRadians
     *            the angle in radians.
     * @return the angle in degree.
     */
    public static float radianToDegree(final float angleInRadians) {

        return cs3744.vectorAlgebra.Operations.radianToDegree(angleInRadians);
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
    public static IVector add(final IVector lhs, final IVector rhs) {

        return new Vector(cs3744.vectorAlgebra.Operations.add(lhs.getVector(),
                rhs.getVector()));
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
    public static IPoint add(final IPoint lhs, final IVector rhs) {

        return new Point(cs3744.vectorAlgebra.Operations.add(lhs.getPoint(),
                rhs.getVector()), lhs.getParent());
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
    public static IPoint add(final IVector lhs, final IPoint rhs) {

        return Operations.add(rhs, lhs);
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
    public static IVector subtract(final IPoint lhs, final IPoint rhs) {

        return new Vector(cs3744.vectorAlgebra.Operations.subtract(
                lhs.getPoint(), rhs.getPoint()));
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
    public static IPoint subtract(final IPoint lhs, final IVector rhs) {

        return new Point(cs3744.vectorAlgebra.Operations.subtract(
                lhs.getPoint(), rhs.getVector()), lhs.getParent());
    }


    /**
     * Scales the vector by the provided scalar.
     * 
     * @param lhs
     *            The vector.
     * @param rhs
     *            The scalar.
     * @return The scaled point.
     */
    public static IVector scale(final IVector lhs, final float rhs) {

        return new Vector(cs3744.vectorAlgebra.Operations.multiply(
                lhs.getVector(), rhs));
    }


    /**
     * Scales the vector by the provided scalar.
     * 
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The vector.
     * @return The scaled point.
     */
    public static IVector scale(final float lhs, final IVector rhs) {

        return new Vector(cs3744.vectorAlgebra.Operations.multiply(lhs,
                rhs.getVector()));
    }


    /**
     * Scales the vector by the provided scalar.
     * 
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The vector.
     * @return The scaled vector.
     */
    public static IVector multiply(final float lhs, final IVector rhs) {

        return Operations.scale(rhs, lhs);
    }


    /**
     * Applies the transformation to the provided vector.
     * 
     * @param transformation
     *            the transformation.
     * @param vector
     *            the vector.
     * @return The resulting vector.
     */
    public static IVector applyTransformation(
            final ITransformation transformation, final IVector vector) {

        return new Vector(cs3744.vectorAlgebra.Operations.multiply(
                transformation.getMatrix(), vector.getVector()));
    }


    /**
     * Applies the transformation to the provided point.
     * 
     * @param transformation
     *            the transformation.
     * @param point
     *            the point.
     * @return The resulting point.
     */
    public static IPoint applyTransformation(
            final ITransformation transformation, final IPoint point) {

        return new Point(cs3744.vectorAlgebra.Operations.multiply(
                transformation.getMatrix(), point.getPoint()),
                point.getParent());
    }


    /**
     * Combines the two transformations provided.
     * 
     * If the resulting transformation is applied to a point or vector, it works
     * as if the right-hand side transformation is applied to the point or
     * vector first, followed by the left-hand side transformation.
     * 
     * @param lhs
     *            the left-hand side transformation.
     * @param rhs
     *            the right-hand side transformation.
     * @return the resulting transformation.
     */
    public static ITransformation combine(final ITransformation lhs,
            final ITransformation rhs) {

        return new Transformation(cs3744.vectorAlgebra.Operations.multiply(
                lhs.getMatrix(), rhs.getMatrix()));
    }


    /**
     * Creates the translation transformation from the provided translation.
     * 
     * @param translation
     *            the translation.
     * @return the translation transformation.
     */
    public static ITransformation translationTransformation(
            final ITranslation translation) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations.translationMatrix(
                        translation.getXTranslation(),
                        translation.getYTranslation(),
                        translation.getZTranslation()));
    }


    /**
     * Creates the inverse translation transformation from the provided
     * translation.
     * 
     * @param translation
     *            the translation.
     * @return the translation transformation.
     */
    public static ITransformation inverseTranslationTransformation(
            final ITranslation translation) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations.inverseTranslationMatrix(
                        translation.getXTranslation(),
                        translation.getYTranslation(),
                        translation.getZTranslation()));
    }


    /**
     * Creates the scaling transformation from the provided scaling.
     * 
     * @param scaling
     *            the scaling.
     * @return the scaling transformation.
     */
    public static ITransformation scalingTransformation(final IScaling scaling) {


        return new Transformation(
                cs3744.vectorAlgebra.Operations.scalingMatrix(
                        scaling.getScaleFactorX(), scaling.getScaleFactorY(),
                        scaling.getScaleFactorZ()));
    }


    /**
     * Creates the inverse scaling transformation from the provided scaling.
     * 
     * @param scaling
     *            the scaling.
     * @return the scaling transformation.
     */
    public static ITransformation inverseScalingTransformation(
            final IScaling scaling) {


        return new Transformation(
                cs3744.vectorAlgebra.Operations.inverseScalingMatrix(
                        scaling.getScaleFactorX(), scaling.getScaleFactorY(),
                        scaling.getScaleFactorZ()));
    }


    /**
     * Creates the linear scaling transformation from the provided scaling.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @return the scaling transformation.
     */
    public static ITransformation linearScalingTransformation(
            final float scalingFactor) {


        return new Transformation(
                cs3744.vectorAlgebra.Operations
                        .linearScalingMatrix(scalingFactor));
    }


    /**
     * Creates the inverse linear scaling transformation from the provided
     * scaling.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @return the scaling transformation.
     */
    public static ITransformation inverseLinearScalingTransformation(
            final float scalingFactor) {


        return new Transformation(
                cs3744.vectorAlgebra.Operations
                        .inverseLinearScalingMatrix(scalingFactor));
    }


    /**
     * Creates the shear transformation for shearing by the shear.
     * 
     * @param shear
     *            the shear.
     * @return the shear transformation.
     */
    public static ITransformation shearTransformation(final IShear shear) {

        return new Transformation(cs3744.vectorAlgebra.Operations.shearMatrix(
                shear.getShearComponent(), shear.getShearBy()));
    }


    /**
     * Creates the inverse shear transformation for shearing by the shear.
     * 
     * @param shear
     *            the shear.
     * @return the shear transformation.
     */
    public static ITransformation inverseShearTransformation(final IShear shear) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations.inverseShearMatrix(
                        shear.getShearComponent(), shear.getShearBy()));
    }


    /**
     * Creates the rotation transformation for the rotation.
     * 
     * @param rotation
     *            the rotation
     * @return the rotation transformation.
     */
    public static ITransformation rotationTransformation(final IRotation rotation) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations.rotationMatrix(rotation
                        .getRotationAxis().getVector(), rotation
                        .getRotationAngle()));
    }


    /**
     * Creates the inverse rotation transformation for the rotation.
     * 
     * @param rotation
     *            the rotation
     * @return the rotation transformation.
     */
    public static ITransformation inverseRotationTransformation(
            final IRotation rotation) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations.inverseRotationMatrix(rotation
                        .getRotationAxis().getVector(), rotation
                        .getRotationAngle()));
    }


    /**
     * Creates the rotation transformation around the x-axis by the provided
     * rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation transformation.
     */
    public static ITransformation xRotationTransformation(
            final float rotationAngle) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations.xRotationMatrix(rotationAngle));
    }


    /**
     * Creates the inverse of the rotation transformation around the x-axis by
     * the provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation transformation.
     */
    public static ITransformation inverseXRotationTransformation(
            final float rotationAngle) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations
                        .inverseXRotationMatrix(rotationAngle));
    }


    /**
     * Creates the rotation transformation around the y-axis by the provided
     * rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation transformation.
     */
    public static ITransformation yRotationTransformation(
            final float rotationAngle) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations.yRotationMatrix(rotationAngle));
    }


    /**
     * Creates the inverse of the rotation transformation around the y-axis by
     * the provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation transformation.
     */
    public static ITransformation inverseYRotationTransformation(
            final float rotationAngle) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations
                        .inverseYRotationMatrix(rotationAngle));
    }


    /**
     * Creates the rotation transformation around the z-axis by the provided
     * rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation transformation.
     */
    public static ITransformation zRotationTransformation(
            final float rotationAngle) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations.zRotationMatrix(rotationAngle));
    }


    /**
     * Creates the inverse of the rotation transformation around the z-axis by
     * the provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation transformation.
     */
    public static ITransformation inverseZRotationTransformation(
            final float rotationAngle) {

        return new Transformation(
                cs3744.vectorAlgebra.Operations
                        .inverseZRotationMatrix(rotationAngle));
    }


    /**
     * Translates the point by the translation.
     * 
     * @param pointToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @return the translated point.
     */
    public static IPoint translate(final IPoint pointToTranslate,
            final ITranslation translation) {

        ITransformation translationMatrix = Operations
                .translationTransformation(translation);

        return Operations.applyTransformation(translationMatrix,
                pointToTranslate);
    }


    /**
     * Reverts the translation of the point by the translation.
     * 
     * @param pointToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @return the translated point.
     */
    public static IPoint revertTranslate(final IPoint pointToTranslate,
            final ITranslation translation) {

        ITransformation translationMatrix = Operations
                .inverseTranslationTransformation(translation);

        return Operations.applyTransformation(translationMatrix,
                pointToTranslate);
    }


    /**
     * Scales the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     * @return the scaled point.
     */
    public static IPoint scale(final IPoint pointToScale,
            final IScaling scalingFactor) {

        ITransformation scaleMatrix = Operations
                .scalingTransformation(scalingFactor);

        return Operations.applyTransformation(scaleMatrix, pointToScale);
    }


    /**
     * Reverts the scaling of the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     * @return the scaled point.
     */
    public static IPoint revertScale(final IPoint pointToScale,
            final IScaling scalingFactor) {

        ITransformation scaleMatrix = Operations
                .inverseScalingTransformation(scalingFactor);

        return Operations.applyTransformation(scaleMatrix, pointToScale);
    }


    /**
     * Uniformly scales the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled point.
     */
    public static IPoint linearScale(final IPoint pointToScale,
            final float scalingFactor) {

        ITransformation scaleMatrix = Operations
                .linearScalingTransformation(scalingFactor);

        return Operations.applyTransformation(scaleMatrix, pointToScale);
    }


    /**
     * Reverts the uniform scaling of the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled point.
     */
    public static IPoint revertLinearScale(final IPoint pointToScale,
            final float scalingFactor) {

        ITransformation scaleMatrix = Operations
                .inverseLinearScalingTransformation(scalingFactor);

        return Operations.applyTransformation(scaleMatrix, pointToScale);
    }


    /**
     * Shears the point by the provided shear factor.
     * 
     * @param pointToShear
     *            the point to shear.
     * @param shear
     *            the shear.
     * @return the sheared point.
     */
    public static IPoint shear(final IPoint pointToShear, final IShear shear) {

        ITransformation shearMatrix = Operations.shearTransformation(shear);

        return Operations.applyTransformation(shearMatrix, pointToShear);
    }


    /**
     * Reverts the shear of the point by the provided shear factor.
     * 
     * @param pointToShear
     *            the point to shear.
     * @param shear
     *            the shear.
     * @return the sheared point.
     */
    public static IPoint revertShear(final IPoint pointToShear,
            final IShear shear) {

        ITransformation shearMatrix = Operations
                .inverseShearTransformation(shear);

        return Operations.applyTransformation(shearMatrix, pointToShear);
    }


    /**
     * Rotates the point around by the rotation angle around the rotation axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotation
     *            the rotation.
     * @return the rotated point.
     * 
     */
    public static IPoint rotate(final IPoint pointToRotate,
            final IRotation rotation) {

        ITransformation rotationMatrix = Operations
                .rotationTransformation(rotation);

        return Operations.applyTransformation(rotationMatrix, pointToRotate);
    }


    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * rotation axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotation
     *            the rotation.
     * @return the rotated point.
     * 
     */
    public static IPoint revertRotate(final IPoint pointToRotate,
            final IRotation rotation) {

        ITransformation rotationMatrix = Operations
                .inverseRotationTransformation(rotation);

        return Operations.applyTransformation(rotationMatrix, pointToRotate);
    }


    /**
     * Rotates the point around by the rotation angle around the x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static IPoint rotateX(final IPoint pointToRotate, float rotationAngle) {

        ITransformation rotationMatrix = Operations
                .xRotationTransformation(rotationAngle);

        return Operations.applyTransformation(rotationMatrix, pointToRotate);
    }


    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static IPoint revertRotateX(final IPoint pointToRotate,
            float rotationAngle) {

        ITransformation rotationMatrix = Operations
                .inverseXRotationTransformation(rotationAngle);

        return Operations.applyTransformation(rotationMatrix, pointToRotate);
    }


    /**
     * Rotates the point around by the rotation angle around the y-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static IPoint rotateY(final IPoint pointToRotate, float rotationAngle) {

        ITransformation rotationMatrix = Operations
                .yRotationTransformation(rotationAngle);

        return Operations.applyTransformation(rotationMatrix, pointToRotate);
    }


    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static IPoint revertRotateY(final IPoint pointToRotate,
            float rotationAngle) {

        ITransformation rotationMatrix = Operations
                .inverseYRotationTransformation(rotationAngle);

        return Operations.applyTransformation(rotationMatrix, pointToRotate);
    }


    /**
     * Rotates the point around by the rotation angle around the z-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static IPoint rotateZ(final IPoint pointToRotate, float rotationAngle) {

        ITransformation rotationMatrix = Operations
                .zRotationTransformation(rotationAngle);

        return Operations.applyTransformation(rotationMatrix, pointToRotate);
    }


    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static IPoint revertRotateZ(final IPoint pointToRotate,
            float rotationAngle) {

        ITransformation rotationMatrix = Operations
                .inverseZRotationTransformation(rotationAngle);

        return Operations.applyTransformation(rotationMatrix, pointToRotate);
    }


    /**
     * Translates the line by the translation.
     * 
     * @param lineToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @return the translated line.
     */
    public static ILine translate(final ILine lineToTranslate,
            final ITranslation translation) {


        IPoint translatedSource = Operations.translate(
                lineToTranslate.getSource(), translation);

        IPoint translatedSink = Operations.translate(lineToTranslate.getSink(),
                translation);


        return new Line(translatedSource, translatedSink);
    }


    /**
     * Reverts the translation of the line by the translation.
     * 
     * @param lineToTranslate
     *            the line to translate.
     * @param translation
     *            the translation.
     * @return the translated line.
     */
    public static ILine revertTranslate(final ILine lineToTranslate,
            final ITranslation translation) {

        IPoint translatedSource = Operations.revertTranslate(
                lineToTranslate.getSource(), translation);

        IPoint translatedSink = Operations.revertTranslate(
                lineToTranslate.getSink(), translation);


        return new Line(translatedSource, translatedSink,
                lineToTranslate.getParent());
    }


    /**
     * Scales the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     * @return the scaled line.
     */
    public static ILine scale(final ILine lineToScale,
            final IScaling scalingFactor) {

        IPoint scaledSource = Operations.scale(lineToScale.getSource(),
                scalingFactor);

        IPoint scaledSink = Operations.scale(lineToScale.getSink(),
                scalingFactor);


        return new Line(scaledSource, scaledSink, lineToScale.getParent());
    }


    /**
     * Reverts the scaling of the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     * @return the scaled line.
     */
    public static ILine revertScale(final ILine lineToScale,
            final IScaling scalingFactor) {

        IPoint scaledSource = Operations.revertScale(lineToScale.getSource(),
                scalingFactor);

        IPoint scaledSink = Operations.revertScale(lineToScale.getSink(),
                scalingFactor);


        return new Line(scaledSource, scaledSink, lineToScale.getParent());
    }


    /**
     * Uniformly scales the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled line.
     */
    public static ILine linearScale(final ILine lineToScale,
            final float scalingFactor) {

        IPoint scaledSource = Operations.linearScale(lineToScale.getSource(),
                scalingFactor);

        IPoint scaledSink = Operations.linearScale(lineToScale.getSink(),
                scalingFactor);


        return new Line(scaledSource, scaledSink, lineToScale.getParent());
    }


    /**
     * Reverts the uniform scaling of the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled line.
     */
    public static ILine revertLinearScale(final ILine lineToScale,
            final float scalingFactor) {

        IPoint scaledSource = Operations.revertLinearScale(
                lineToScale.getSource(), scalingFactor);

        IPoint scaledSink = Operations.revertLinearScale(lineToScale.getSink(),
                scalingFactor);


        return new Line(scaledSource, scaledSink, lineToScale.getParent());
    }


    /**
     * Shears the line by the provided shear factor.
     * 
     * @param lineToShear
     *            the line to shear.
     * @param shear
     *            the shear.
     * @return the sheared line.
     */
    public static ILine shear(final ILine lineToShear, final IShear shear) {

        IPoint shearedSource = Operations.shear(lineToShear.getSource(), shear);

        IPoint shearedSink = Operations.shear(lineToShear.getSink(), shear);


        return new Line(shearedSource, shearedSink, lineToShear.getParent());
    }


    /**
     * Reverts the shear of the line by the provided shear factor.
     * 
     * @param lineToShear
     *            the line to shear.
     * @param shear
     *            the shear.
     * @return the sheared line.
     */
    public static ILine revertShear(final ILine lineToShear, final IShear shear) {

        IPoint shearedSource = Operations.revertShear(lineToShear.getSource(),
                shear);

        IPoint shearedSink = Operations.revertShear(lineToShear.getSink(),
                shear);


        return new Line(shearedSource, shearedSink, lineToShear.getParent());
    }


    /**
     * Rotates the line around by the rotation angle around the rotation axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotation
     *            the rotation.
     * @return the rotated line.
     * 
     */
    public static ILine rotate(final ILine lineToRotate, final IRotation rotation) {

        IPoint rotatedSource = Operations.rotate(lineToRotate.getSource(),
                rotation);

        IPoint rotatedSink = Operations
                .rotate(lineToRotate.getSink(), rotation);


        return new Line(rotatedSource, rotatedSink, lineToRotate.getParent());
    }


    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * rotation axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotation
     *            the rotation.
     * @return the rotated line.
     * 
     */
    public static ILine revertRotate(final ILine lineToRotate,
            final IRotation rotation) {

        IPoint rotatedSource = Operations.revertRotate(
                lineToRotate.getSource(), rotation);

        IPoint rotatedSink = Operations.revertRotate(lineToRotate.getSink(),
                rotation);


        return new Line(rotatedSource, rotatedSink, lineToRotate.getParent());
    }


    /**
     * Rotates the line around by the rotation angle around the x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static ILine rotateX(final ILine lineToRotate, float rotationAngle) {

        IPoint rotatedSource = Operations.rotateX(lineToRotate.getSource(),
                rotationAngle);

        IPoint rotatedSink = Operations.rotateX(lineToRotate.getSink(),
                rotationAngle);


        return new Line(rotatedSource, rotatedSink, lineToRotate.getParent());
    }


    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static ILine revertRotateX(final ILine lineToRotate,
            float rotationAngle) {

        IPoint rotatedSource = Operations.revertRotateX(
                lineToRotate.getSource(), rotationAngle);

        IPoint rotatedSink = Operations.revertRotateX(lineToRotate.getSink(),
                rotationAngle);


        return new Line(rotatedSource, rotatedSink, lineToRotate.getParent());
    }


    /**
     * Rotates the line around by the rotation angle around the y-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static ILine rotateY(final ILine lineToRotate, float rotationAngle) {

        IPoint rotatedSource = Operations.rotateY(lineToRotate.getSource(),
                rotationAngle);

        IPoint rotatedSink = Operations.rotateY(lineToRotate.getSink(),
                rotationAngle);


        return new Line(rotatedSource, rotatedSink, lineToRotate.getParent());
    }


    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static ILine revertRotateY(final ILine lineToRotate,
            float rotationAngle) {

        IPoint rotatedSource = Operations.revertRotateY(
                lineToRotate.getSource(), rotationAngle);

        IPoint rotatedSink = Operations.revertRotateY(lineToRotate.getSink(),
                rotationAngle);


        return new Line(rotatedSource, rotatedSink, lineToRotate.getParent());
    }


    /**
     * Rotates the line around by the rotation angle around the z-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static ILine rotateZ(final ILine lineToRotate, float rotationAngle) {

        IPoint rotatedSource = Operations.rotateZ(lineToRotate.getSource(),
                rotationAngle);

        IPoint rotatedSink = Operations.rotateZ(lineToRotate.getSink(),
                rotationAngle);


        return new Line(rotatedSource, rotatedSink, lineToRotate.getParent());
    }


    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static ILine revertRotateZ(final ILine lineToRotate,
            float rotationAngle) {

        IPoint rotatedSource = Operations.revertRotateZ(
                lineToRotate.getSource(), rotationAngle);

        IPoint rotatedSink = Operations.revertRotateZ(lineToRotate.getSink(),
                rotationAngle);


        return new Line(rotatedSource, rotatedSink, lineToRotate.getParent());
    }
}
