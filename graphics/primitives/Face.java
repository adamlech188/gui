package cs3744.graphics.primitives;

import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.IMaterial;
import cs3744.graphics.common.solution.Operations;
import java.util.Collections;
import cs3744.graphics.common.solution.Vector;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedHashMap;
import cs3744.graphics.interfaces.IPrimitive;
import java.util.concurrent.locks.Lock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import cs3744.graphics.interfaces.IFace;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IVector;

/**
 * Your description here.
 *cs3744.graphics.primitives.
 * @author Your name here
 */
public abstract class Face
    extends Primitive
    implements IFace
{

    private final Lock           isFlatLock;
    private boolean              isFlat;
    private final Lock           vertexNormalMapLock;
    private Map<IPoint, IVector> vertexNormalMap;
    private final Lock           surfaceNormalLock;
    private IVector              surfaceNormal;


    // ----------------------------------------------------------
    /**
     * Create a new Face object.
     */
    public Face()
    {
        this(null);

    }


    // ----------------------------------------------------------
    /**
     * Create a new Face object.
     * @param primitiveToCopy
     * @param parent
     */
    public Face(IFace primitiveToCopy, IPrimitive parent)
    {
        super(primitiveToCopy, parent);

        vertexNormalMapLock = new ReentrantLock();
        isFlatLock = new ReentrantLock();
        surfaceNormalLock = new ReentrantLock();

        vertexNormalMap = new LinkedHashMap<IPoint, IVector>();

        for (IPoint point : primitiveToCopy.getVertices())
        {
            this.vertexNormalMap.put((IPoint)point.copy(), primitiveToCopy
                .getVertexNormalMap().get(point));
        }
        primitiveToCopy.getVertexNormalMap();

        isFlat = primitiveToCopy.isFlat();
    }


    // ----------------------------------------------------------
    /**
     * Create a new Face object.
     * @param parent
     */
    public Face(IPrimitive parent)
    {
        super(parent);
        vertexNormalMap = new LinkedHashMap<IPoint, IVector>();
        vertexNormalMapLock = new ReentrantLock();
        isFlatLock = new ReentrantLock();
        surfaceNormalLock = new ReentrantLock();
        isFlat = false;

    }


    @Override
    public Boolean isFlat()
    {

        boolean returnValue;
        try
        {
            isFlatLock.lock();
            returnValue = isFlat;
        }
        finally
        {
            isFlatLock.unlock();
        }
        return returnValue;
    }


    @Override
    public void setFlat(boolean isFlat)
    {
        try
        {
            isFlatLock.lock();
            this.isFlat = isFlat;
        }
        finally
        {
            isFlatLock.unlock();
        }

    }


    @Override
    public IVector getSurfaceNormal()
    {

        IVector returnValue;
        try
        {
            surfaceNormalLock.lock();
            if (surfaceNormal == null ) {
                this.calculateAndSetSurfaceNormal();
            }
            returnValue = surfaceNormal;
        }
        finally
        {
            surfaceNormalLock.unlock();
        }
        return returnValue;
    }


    @Override
    public void setSurfaceNormal(IVector surfaceNormal)
    {

        try
        {
            surfaceNormalLock.lock();
            this.surfaceNormal = surfaceNormal;
        }
        finally
        {
            surfaceNormalLock.unlock();
        }

    }


    @Override
    public Map<IPoint, IVector> getVertexNormalMap()
    {

        Map<IPoint, IVector> returnValue;
        try
        {
            vertexNormalMapLock.lock();
            returnValue = vertexNormalMap;
        }
        finally
        {
            vertexNormalMapLock.unlock();
        }
        return returnValue;
    }


    @Override
    public Set<IPoint> getVertices()
    {
        Set<IPoint> returnValue;
        try
        {
            vertexNormalMapLock.lock();
            returnValue = Collections.unmodifiableSet(vertexNormalMap.keySet());
        }
        finally
        {
            vertexNormalMapLock.unlock();
        }

        return returnValue;
    }


    @Override
    public Collection<IVector> getNormals()
    {
        Collection<IVector> returnValue;
        try
        {
            vertexNormalMapLock.lock();
            returnValue =
                Collections.unmodifiableCollection(vertexNormalMap.values());
        }
        finally
        {
            vertexNormalMapLock.unlock();
        }

        return returnValue;

    }


    @Override
    public void addVertex(IPoint vertex)
    {

        this.addVertex(vertex, null);

    }


    @Override
    public void addVertex(IPoint vertex, IVector normal)
    {
        try
        {
            vertexNormalMapLock.lock();
            if (normal != null)
            {
                normal.setParent(this);
            }

            vertexNormalMap.put(new Point(vertex, this), normal);

        }
        finally
        {
            vertexNormalMapLock.unlock();
        }
    }


    @Override
    public void removeVertex(IPoint vertex)
    {

        try
        {
            vertexNormalMapLock.lock();
            vertexNormalMap.remove(vertex);
            vertex.setParent(null);
        }
        finally
        {
            vertexNormalMapLock.unlock();
        }
    }


    @Override
    public void clearVertices()
    {

        try
        {
            vertexNormalMapLock.lock();
            vertexNormalMap.clear();
        }
        finally
        {
            vertexNormalMapLock.unlock();
        }

    }


    @Override
    public void calculateAndSetVertexNormals()
    {

        try
        {
            vertexNormalMapLock.lock();
            IPoint[] points = vertexNormalMap.keySet().toArray(new Point[0]);
            for (int i = 0; i < points.length; i++)
            {
                IPoint previousPoint =
                    points[((i - 1) % points.length + points.length)
                        % points.length];
                IPoint currentPoint = points[i];
                IPoint nextPoint = points[(i + 1) % points.length];
                IVector firstVector =
                    Operations.subtract(previousPoint, currentPoint);
                IVector secondVector =
                    Operations.subtract(currentPoint, nextPoint);
                IVector normal = Vector.crossProduct(firstVector, secondVector);
                normal.setParent(this);
                vertexNormalMap.put(currentPoint, normal);

            }
        }
        finally
        {
            vertexNormalMapLock.unlock();
        }

    }

    @Override
    public void calculateAndSetSurfaceNormal()
    {

        IVector normal = null;
        try
        {
            vertexNormalMapLock.lock();
            IPoint[] points = vertexNormalMap.keySet().toArray(new Point[0]);
            float x = 0;
            float y = 0;
            float z = 0;
            for (int i = 0; i < points.length; i++)
            {
                IPoint currentPoint = points[i];
                IPoint nextPoint = points[(i + 1) % points.length];
                x +=
                    (currentPoint.getY() - nextPoint.getY())
                        * (currentPoint.getZ() + nextPoint.getZ());
                y +=
                    (currentPoint.getZ() - nextPoint.getZ())
                        * (currentPoint.getX() + nextPoint.getX());
                z +=
                    (currentPoint.getX() - nextPoint.getX())
                        * (currentPoint.getY() + nextPoint.getY());
            }
            normal = new Vector(x, y, z);
        }
        finally
        {

            vertexNormalMapLock.unlock();

        }
        try
        {
            this.surfaceNormalLock.lock();
            this.setSurfaceNormal(normal);
        }
        finally
        {
            this.surfaceNormalLock.unlock();
        }

    }


    @Override
    public void setMaterial(IMaterial material)
    {

        super.setMaterial(material);
        for (IPoint vertex : this.getVertices())
        {

            vertex.setMaterial(material);


        }

    }


    @Override
    public void setColor(IColor color)
    {
        super.setColor(color);
        for (IPoint vertex : this.getVertices())
        {
            vertex.setColor(color);


        }
    }
}
