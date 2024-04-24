import java.awt.*;
import java.awt.geom.Path2D;

public class Pyramid extends Object3D {

    Point3D[] points;
    Color color;

    Pyramid(Point3D p1, Point3D p2, Point3D p3, Point3D p4, Color color)
    {
        this.points = new Point3D[]{p1, p2, p3, p4};
        this.color = color;
    }

    public void draw(Graphics2D g2, Matrix3 transform)
    {
        short i = 0;
        Path2D path = new Path2D.Double();
        g2.setColor(this.color);

        Point3D[] translate_points = new Point3D[4];

        // Translate Points to camera
        for (Point3D point : this.points)
        {
            translate_points[i] = transform.transform(point);

            i++;
        }


        // Draw all Lines
        for (Point3D point : translate_points)
        {
            path.moveTo(point.x, point.y);
            for (Point3D point2 : translate_points)
            {
                if (point == point2)
                    continue;

                path.lineTo(point2.x, point2.y);

            }
        }

        path.closePath();
        g2.draw(path);

    }
}
