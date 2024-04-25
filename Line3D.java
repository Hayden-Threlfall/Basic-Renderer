import java.awt.*;
import java.awt.geom.Path2D;

public class Line3D extends Object3D
{
    Point3D[] points;

    Color c;

    Line3D(Point3D[] p, Color c)
    {
        this.points = p;
        this.color = c;
    }

    public void draw(Graphics2D g2, Matrix3 transform)
    {
        Path2D path = new Path2D.Double();
        g2.setColor(this.color);

        Point3D t_p1 = transform.transform(this.points[0]);
        Point3D t_p2 = transform.transform(this.points[1]);

        path.moveTo(t_p1.x, t_p1.y);
        path.lineTo(t_p2.x, t_p2.y);
        path.closePath();
        g2.draw(path);
    }
}
