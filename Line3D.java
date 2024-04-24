import java.awt.Color;

public class Line3D extends Object3D
{
    Line3D(Point3D[] p, Color c)
    {
        super(p, new int[][]{{0, 1}}, c);
    }
}
