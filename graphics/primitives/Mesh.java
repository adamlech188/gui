/**
 *
 */
package cs3744.graphics.primitives;

import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.List;
import cs3744.graphics.interfaces.IFace;
import cs3744.graphics.interfaces.IMesh;
import cs3744.graphics.interfaces.IPrimitive;

/**
 * @author peter
 */
public abstract class Mesh
    extends Primitive
    implements IMesh
{
    private final Lock  faceListLock;
    private List<IFace> faceList;


    /**
     *
     */
    public Mesh()
    {

        this(null);
    }


    /**
     * @param parent
     */
    public Mesh(IPrimitive parent)
    {

        super(parent);
        faceListLock = new ReentrantLock();
        faceList = new LinkedList<IFace>();

    }


    /**
     * @param primitiveToCopy
     * @param parent
     */
    public Mesh(IMesh primitiveToCopy, IPrimitive parent)
    {

        super(primitiveToCopy, parent);
        faceListLock = new ReentrantLock();
        faceList = new ArrayList<IFace>();
        List<IFace> toCopyList = primitiveToCopy.getFaceList();
        for (IFace faceToCopy : toCopyList)
        {
            faceList.add(faceToCopy);
        }

    }


    /*
     * (non-Javadoc)
     * @see cs3744.graphics.interfaces.IMesh#getFaceList()
     */
    @Override
    public List<IFace> getFaceList()
    {

        List<IFace> returnValue;
        try
        {
            faceListLock.lock();
            returnValue = Collections.unmodifiableList(faceList);
        }
        finally
        {
            faceListLock.unlock();
        }
        return returnValue;
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.graphics.interfaces.IMesh#addFace(cs3744.graphics.interfaces.IFace
     * )
     */
    @Override
    public void addFace(IFace face)
    {

        try
        {
            faceListLock.lock();
            faceList.add(face);
            face.setParent(this);
        }
        finally
        {
            faceListLock.unlock();
        }

    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.graphics.interfaces.IMesh#removeFace(cs3744.graphics.interfaces
     * .IFace)
     */
    @Override
    public void removeFace(IFace face)
    {

        try
        {
            faceListLock.lock();
            faceList.remove(face);
        }
        finally
        {
            faceListLock.unlock();
        }

    }


    /*
     * (non-Javadoc)
     * @see cs3744.graphics.interfaces.IMesh#clearFaces()
     */
    @Override
    public void clearFaces()
    {

        try
        {
            faceListLock.lock();
            faceList.clear();
        }
        finally
        {
            faceListLock.unlock();
        }

    }

}
