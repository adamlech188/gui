/**
 * 
 */
package cs3744.graphics.interfaces;


import java.util.List;


/**
 * The <CODE>IMesh</CODE> interface provides a contract for classes implementing
 * polygon meshes.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IMesh
        extends IPrimitive {

    /**
     * Returns the face list of the mesh.
     * 
     * @return the face list.
     */
    public List<IFace> getFaceList();


    /**
     * Adds a face to the mesh.
     * 
     * @param face
     *            the face to add.
     */
    public void addFace(IFace face);


    /**
     * Removes a face from the mesh.
     * 
     * @param face
     *            the face to remove.
     */
    public void removeFace(IFace face);


    /**
     * Clears the list of faces.
     */
    public void clearFaces();
}
