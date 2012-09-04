package cs3744.graphics.interfaces;


import java.util.Collection;
import java.util.Map;
import java.util.Set;



/**
 * The <CODE>IFace</CODE> interface provides a contract for classes that
 * implement faces.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IFace
        extends IPrimitive {

    @Override
    public IFace copy();


    @Override
    public IFace copy(IPrimitive parent);


    /**
     * Returns whether or not this face is flat.
     * 
     * @return <CODE>true</CODE>, if the face is flat; <CODE>false</CODE>
     *         otherwise.
     */
    public abstract Boolean isFlat();


    /**
     * Sets whether or not the surface is flat.
     * 
     * @param isFlat
     *            whether or not the surface is flat.
     */
    public abstract void setFlat(boolean isFlat);


    /**
     * Returns the surface normal.
     * 
     * @return the surface normal.
     */
    public IVector getSurfaceNormal();


    /**
     * Sets the surface normal.
     * 
     * @param surfaceNormal
     *            the surface normal.
     */
    public void setSurfaceNormal(IVector surfaceNormal);


    /**
     * Returns the vertex-normal map.
     * 
     * @return the vertex-normal map.
     */
    public abstract Map<IPoint, IVector> getVertexNormalMap();


    /**
     * Returns the vertices (points) of the face.
     * 
     * @return the vertices.
     */
    public abstract Set<IPoint> getVertices();


    /**
     * Returns the vertex-IVectors.
     * 
     * @return the IVectors.
     */
    public abstract Collection<IVector> getNormals();


    /**
     * Adds a vertex without an associated IVector.
     * 
     * @param vertex
     *            the vertex to add.
     */
    public abstract void addVertex(IPoint vertex);


    /**
     * Adds a vertex with an associated normal.
     * 
     * @param vertex
     *            the vertex to add.
     * @param normal
     *            the associated normal
     */
    public abstract void addVertex(IPoint vertex, IVector normal);


    /**
     * Removes a vertex.
     * 
     * @param vertex
     *            the vertex to remove.
     */
    public abstract void removeVertex(IPoint vertex);


    /**
     * Clears the vertices.
     */
    public abstract void clearVertices();


    /**
     * Calculates the vertex normal based on the crossproduct of a point with
     * the two adjacent points.
     */
    public abstract void calculateAndSetVertexNormals();


    /**
     * Calculates the normal of the surface based on the robust method suggested
     * by Newell.
     */
    public abstract void calculateAndSetSurfaceNormal();

}