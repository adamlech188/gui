package cs3744.graphics.common;

import cs3744.graphics.common.solution.ProjectionMode;
import cs3744.graphics.common.solution.Vector;
import cs3744.graphics.interfaces.ICamera;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IRotation;
import cs3744.graphics.interfaces.ITransformation;
import cs3744.graphics.interfaces.ITranslation;
import cs3744.graphics.interfaces.IVector;
import cs3744.graphics.primitives.Point;

/**
 * // -------------------------------------------------------------------------
 * /** Camera class.Stores all the necessary data for camera.
 *
 * @author Adam Lech (PID: adaml8)
 * @version Aug 10, 2012
 */
public class Camera
    extends Object
    implements ICamera
{
    // ~Instance field
    private final IPoint    eyePosition;
    private final IVector   upGuess;
    private final IPoint    lookAtPoint;
    private ProjectionMode  projectionMode;
    private ITransformation viewingTransformation;
    private ITranslation    translation;
    private boolean         noTranslation;
    private IRotation       rotation;
    private boolean         noRotation;
    private Float           viewAngle;
    private Float           aspectRatio;
    private Float           nearDistance;
    private Float           farDistance;
    private ITransformation projectionTransformation;
    private boolean         noProjection;


    // ----------------------------------------------------------
    /**
     * Creates a new default Camera. The camera's location is at (0, 0, 1)
     * looking at (0, 0, 0) with up-direction towards positive Y. The aspect
     * ratio is 1, the view angle is 70 degrees, the near distance is 1, the far
     * distance is 100.
     */
    public Camera()
    {
        this(
            new Point(0f, 0f, 1f),
            Point.ORIGIN,
            Vector.J_UNIT_VECTOR,
            70f,
            1f,
            1f,
            100f);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new Camera at the default position. The viewing volume is set
     * up using the provided parameters.
     *
     * @param viewAngle
     * @param aspectRatio
     * @param nearDistance
     * @param farDistance
     */
    public Camera(
        Float viewAngle,
        Float aspectRatio,
        Float nearDistance,
        Float farDistance)
    {
        this(
            new Point(0f, 0f, 1f),
            Point.ORIGIN,
            Vector.J_UNIT_VECTOR,
            viewAngle,
            aspectRatio,
            nearDistance,
            farDistance);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new Camera at the given position looking at the given look-at
     * point with the provided up-direction. The viewing volume is that of the
     * default camera.
     *
     * @param eyePosition
     * @param lookAtPoint
     * @param upGuess
     */
    public Camera(IPoint eyePosition, IPoint lookAtPoint, IVector upGuess)
    {
        this(eyePosition, lookAtPoint, upGuess, 70f, 1f, 1f, 100f);

    }


    // ----------------------------------------------------------
    /**
     * Creates a new Camera at the provided position and sets up a viewing
     * volume with the provided parameters.
     *
     * @param eyePosition
     * @param lookAtPoint
     * @param upGuess
     * @param viewAngle
     * @param aspectRatio
     * @param nearDistance
     * @param farDistance
     */
    public Camera(
        IPoint eyePosition,
        IPoint lookAtPoint,
        IVector upGuess,
        Float viewAngle,
        Float aspectRatio,
        Float nearDistance,
        Float farDistance)
    {

        this.eyePosition = eyePosition;
        this.lookAtPoint = lookAtPoint;
        this.upGuess = upGuess;
        this.viewAngle = viewAngle;
        this.aspectRatio = aspectRatio;
        this.nearDistance = nearDistance;
        this.farDistance = farDistance;
        rotation = new Rotation();
        noRotation = true;
        translation = new Translation();
        noTranslation = true;
        this.viewAngle = viewAngle;
        this.aspectRatio = aspectRatio;
        projectionMode = ProjectionMode.PERSPECTIVE;
        noProjection = true;
    }


    // ----------------------------------------------------------
    /**
     * Creates a new Camera at the provided position and sets up a viewing
     * volume with the provided parameters.
     *
     * @param eyePosition
     * @param lookAtPoint
     * @param upGuess
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param nearDistance
     * @param farDistance
     */
    public Camera(
        IPoint eyePosition,
        IPoint lookAtPoint,
        IVector upGuess,
        Float left,
        Float right,
        Float bottom,
        Float top,
        Float nearDistance,
        Float farDistance)
    {
        this.eyePosition = eyePosition;
        this.lookAtPoint = lookAtPoint;
        this.upGuess = upGuess;
        this.nearDistance = nearDistance;
        this.farDistance = farDistance;
        float height = top - bottom;
        float width = right - left;
        aspectRatio = (width) / (height);
        viewAngle =
            2f * Operations.radianToDegree((float)Math
                .atan((height / (2 * nearDistance))));
        rotation = new Rotation();
        noRotation = true;
        projectionMode = ProjectionMode.PERSPECTIVE;
        noProjection = true;
        translation = new Translation();
        noTranslation = true;

    }


    /**
     * Returns the eye position.
     */
    public IPoint getEyePosition()
    {
        return this.eyePosition;
    }


    /**
     * Returns the eye position;
     */
    public IPoint getLookAtPoint()
    {

        return this.lookAtPoint;
    }


    /**
     * Returns the eye position;
     */
    public IVector getUpGuessVector()
    {

        return this.upGuess;
    }


    /**
     * Returns the eye position;
     */
    public ProjectionMode getProjectionMode()
    {

        return this.projectionMode;
    }


    /**
     * Sets the projection mode of the camera.
     */
    public void setProjectionMode(ProjectionMode projectionMode)
    {
        this.projectionMode = projectionMode;

    }


    /**
     * Sets the projection mode of the camera.
     */
    public ITranslation getTranslation()
    {

        return this.translation;
    }


    /**
     * Sets the camera's translation.
     */
    public void setTranslation(ITranslation translation)
    {
        this.translation = translation;

    }


    /**
     * Returns the camera's rotation.
     */
    public IRotation getRotation()
    {

        return this.rotation;
    }


    /**
     * Returns the camera's rotation.
     */
    public void setRotation(IRotation rotation)
    {
        this.rotation = rotation;

    }


    /**
     * Returns the camera's rotation.
     */
    public Float getViewAngle()
    {

        return this.viewAngle;
    }


    /**
     * Returns the camera's rotation.
     */
    public void setViewAngle(Float viewAngle)
    {
        this.viewAngle = viewAngle;

    }


    /**
     * Returns the aspect ratio.
     */
    public Float getAspectRatio()
    {

        return this.aspectRatio;
    }


    /**
     * Returns the aspect ratio.
     */
    public void setAspectRatio(Float aspectRatio)
    {
        this.aspectRatio = aspectRatio;

    }


    /**
     * Returns the aspect ratio.
     */
    public Float getNearDistance()
    {

        return this.nearDistance;
    }


    /**
     * Returns the aspect ratio.
     */
    public void setNearDistance(Float nearDistance)
    {
        this.nearDistance = nearDistance;

    }


    /**
     * Returns the aspect ratio.
     */
    public Float getFarDistance()
    {
        return this.farDistance;
    }


    /**
     * Returns the aspect ratio.
     */
    public void setFarDistance(Float farDistance)
    {
        this.farDistance = farDistance;

    }


    /**
     * The ITransformation interface provides a contract for classes
     * implementing Transformation matrices.
     */
    public ITransformation getViewingTransformation()
    {
        if (noTranslation || noRotation)
        {
            ITransformation translationTransformation =
                Operations.translationTransformation(translation);
            ITransformation rotationTransfromation =
                Operations.rotationTransformation(rotation);
            ITransformation translationRotation =
                Operations.combine(
                    translationTransformation,
                    rotationTransfromation);
            ITransformation viewTransformation =
                this.calculateViewingTransformation();
            viewingTransformation =
                Operations.combine(translationRotation, viewTransformation);
            noTranslation = false;
            noRotation = false;

        }
        return viewingTransformation;
    }


    /**
     * Returns the projection transformation of this camera.
     */
    public ITransformation getProjectionTransformation()
    {
        if (noProjection)
        {
            this.calculateProjectionTransformation();
            noProjection = false;
        }
        return projectionTransformation;
    }


    /*
     * Private helper method. Calculates viewing Normal.
     * @return IVector - viewing normal
     */
    private IVector calculateViewingNormal()
    {
        return Operations.subtract(eyePosition, lookAtPoint).normalizedVector();
    }


    /**
     * Private helper's method.Calculates right Vector.
     *
     * @return IVector - right Vector.
     */
    private IVector calculateRightVector(IVector viewingNormal)
    {
        return Vector.crossProduct(upGuess, viewingNormal).normalizedVector();
    }


    private IVector calculateRealUp(IVector viewingNormal, IVector rightVector)
    {
        return Vector.crossProduct(viewingNormal, rightVector)
            .normalizedVector();
    }


    /**
     * Private helpers method. Calculates displacement.
     */
    private Translation calculateDisplacement(
        IVector rightVector,
        IVector realUpVector,
        IVector viewingNormal)
    {
        IVector displacementFromOrigin =
            Operations.subtract(eyePosition, Point.ORIGIN);
        Float xD = Vector.dotProduct(displacementFromOrigin, rightVector);
        Float yD = Vector.dotProduct(displacementFromOrigin, realUpVector);
        Float zD = Vector.dotProduct(displacementFromOrigin, viewingNormal);
        return new Translation(xD, yD, zD);

    }


    private ITransformation calculateViewingTransformation()
    {
        IVector viewingNormal = this.calculateViewingNormal();
        IVector rightVector = this.calculateRightVector(viewingNormal);
        IVector realUp = this.calculateRealUp(viewingNormal, rightVector);
        Translation displacement =
            this.calculateDisplacement(rightVector, realUp, viewingNormal);
        IVector iColumn =
            new Vector(rightVector.getX(), realUp.getX(), viewingNormal.getX());
        IVector jColumn =
            new Vector(rightVector.getY(), realUp.getY(), viewingNormal.getY());
        IVector kColumn =
            new Vector(rightVector.getZ(), realUp.getZ(), viewingNormal.getZ());
        Point dTrans =
            new Point(
                -displacement.getXTranslation(),
                -displacement.getYTranslation(),
                -displacement.getZTranslation());
        return new Transformation(
            iColumn.normalizedVector(),
            jColumn.normalizedVector(),
            kColumn.normalizedVector(),
            dTrans);

    }


    /**
     * Private helper's method. Calculates projection transformation.
     */
    private void calculateProjectionTransformation()
    {
        float height =
            nearDistance * 2
                * ((float)Math.tan(Operations.degreeToRadian(viewAngle) / 2));
        float width = height * aspectRatio;
        float left = 0;
        float right = width;
        float bottom = 0;
        float top = height;
        Tuple iColumn = null;
        Tuple jColumn = null;
        Tuple kColumn = null;
        Tuple sTrans = null;
        switch (projectionMode)
        {
            case ORTHOGRAPHIC_2D:
                iColumn = new Tuple(2f / (right - left), 0f, 0f, 0f);
                jColumn = new Tuple(0f, 2f / (top - bottom), 0f, 0f);
                kColumn = new Tuple(0f, 0f, -1f, 0f);
                sTrans = new Tuple(0f, 0f, 0f, 1f);
                break;
            case ORTHOGRAPHIC_3D:
                iColumn = new Tuple(2f / (right - left), 0f, 0f, 0f);
                jColumn = new Tuple(0f, 2f / (top - bottom), 0f, 0f);
                kColumn =
                    new Tuple(0f, 0f, -2f / (farDistance - nearDistance), 0f);
                sTrans =
                    new Tuple(-(right + left) / (right - left), -(top + bottom)
                        / (top - bottom), -(farDistance + nearDistance)
                        / (farDistance - nearDistance), 1f);
                break;
            case PERSPECTIVE:
                float a =
                    -(farDistance + nearDistance)
                        / (farDistance - nearDistance);
                float b =
                    -(2f * farDistance * nearDistance)
                        / (farDistance - nearDistance);
                iColumn =
                    new Tuple((2f * nearDistance) / (right - left), 0f, 0f, 0f);
                jColumn =
                    new Tuple(0f, (2f * nearDistance) / (top - bottom), 0f, 0f);
                kColumn =
                    new Tuple((right + left) / (right - left), (top + bottom)
                        / (top - bottom), a, -1f);
                sTrans = new Tuple(0f, 0f, b, 0f);
                break;

        }
        projectionTransformation =
            new Transformation(iColumn, jColumn, kColumn, sTrans);

    }
}
