import java.awt.*;
import java.awt.geom.Path2D;

public abstract class Object3D
{
    Triangle[] triangles;
    Color color;

    Object3D(Triangle[] t, Color c)
    {
        this.triangles = t;
        this.color = c;
    }

    Object3D() {}

    public void draw(Graphics2D g2, Matrix3 transform)
    {
        g2.setColor(this.color);

        for (Triangle t : this.triangles)
        {
            t.draw(g2, transform);
        }
    }
}
