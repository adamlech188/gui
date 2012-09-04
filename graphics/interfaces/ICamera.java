package cs3744.graphics.interfaces;


import cs3744.graphics.common.solution.ProjectionMode;


/**
 * The <CODE>ICamera</CODE> interface provides the contract for a camera that
 * sets up a viewing coordinate system and a viewing volume for projection.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public interface ICamera {

    /**
     * Returns the eye position;
     *
     * @return the eye position.
     */
    public abstract IPoint getEyePosition();


    /**
     * Returns the look-at point.
     *
     * @return the look-at point.
     */
    public abstract IPoint getLookAtPoint();


    /**
     * Returns the camera's up direction.
     *
     * @return the up direction.
     */
    public abstract IVector getUpGuessVector();


    /**
     * Returns the projection mode of the camera.
     *
     * @return the projection mode.
     */
    public abstract ProjectionMode getProjectionMode();


    /**
     * Sets the projection mode of the camera.
     *
     * @param projectionMode
     *            the projection mode.
     */
    public abstract void setProjectionMode(ProjectionMode projectionMode);


    /**
     * Returns the camera's translation.
     *
     * @return the translation.
     */
    public abstract ITranslation getTranslation();


    /**
     * Sets the camera's translation.
     *
     * @param translation
     *            the new translation.
     */
    public abstract void setTranslation(ITranslation translation);


    /**
     * Returns the camera's rotation.
     *
     * @return the rotation.
     */
    public abstract IRotation getRotation();


    /**
     * Sets the camera's rotation.
     *
     * @param rotation
     *            the new rotation.
     */
    public abstract void setRotation(IRotation rotation);


    /**
     * Returns the view angle.
     *
     * @return the view angle.
     */
    public abstract Float getViewAngle();


    /**
     * Sets the view angle
     *
     * @param viewAngle
     *            the new view angle.
     */
    public abstract void setViewAngle(Float viewAngle);


    /**
     * Returns the aspect ratio.
     *
     * @return the aspect ratio.
     */
    public abstract Float getAspectRatio();


    /**
     * Sets the aspect ratio.
     *
     * @param aspectRatio
     *            the new aspect ratio.
     */
    public abstract void setAspectRatio(Float aspectRatio);


    /**
     * Returns the near distance.
     *
     * @return the near distance.
     */
    public abstract Float getNearDistance();


    /**
     * Sets the near distance.
     *
     * @param nearDistance
     *            the new near distance.
     */
    public abstract void setNearDistance(Float nearDistance);


    /**
     * Returns the far distance.
     *
     * @return the far distance.
     */
    public abstract Float getFarDistance();


    /**
     * Sets the far distance.
     *
     * @param farDistance
     *            the new far distance.
     */
    public abstract void setFarDistance(Float farDistance);


    /**
     * Returns the viewing transformation of this camera.
     *
     * @return the viewing transformation.
     */
    public abstract ITransformation getViewingTransformation();


    /**
     * Returns the projection transformation of this camera.
     *
     * @return the projection transformation.
     */
    public abstract ITransformation getProjectionTransformation();

}