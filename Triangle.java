import java.awt.*;
import java.awt.geom.Path2D;

public class Triangle {

    Point3D[] points;
    Color color;

    Triangle(Point3D p1, Point3D p2, Point3D p3, Color color)
    {
        this.points = new Point3D[]{p1, p2, p3};
        this.color = color;
    }

    Triangle(Point3D[] p, Color c)
    {
        this.points = p;
        this.color = c;
    }

    public void draw(Graphics2D g2, Matrix3 transform)
    {
        Path2D path = new Path2D.Double();
        g2.setColor(this.color);

        // Translate & Draw Points
        for (int i = 0; i < this.points.length; i++)
        {
            Point3D translated_point = transform.transform(this.points[i]);

            if (i == 0)
            {
                path.moveTo(translated_point.x, translated_point.y);
            } else {
                path.lineTo(translated_point.x, translated_point.y);
            }
        }

        path.closePath();
        g2.draw(path);

    }
}
