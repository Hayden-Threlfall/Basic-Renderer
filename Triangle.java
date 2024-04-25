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

    public void draw(Graphics2D g2, Matrix4 x_r_transform, Matrix4 z_r_transform, Matrix4 map_projection)
    {
        Path2D path = new Path2D.Double();
        g2.setColor(this.color);

        // Translate & Draw Points
        for (int i = 0; i < this.points.length; i++)
        {
            Point3D p = this.points[i];

            // Rotate
            Point3D rotated_point = z_r_transform.multiplyPoint(x_r_transform.multiplyPoint(p));


            // Translate / Project
            rotated_point.z += 5;

            Point3D projected_point = map_projection.multiplyPoint(rotated_point);

            // Scale Point
            projected_point.x += 1;
            projected_point.y += 1;

            projected_point.x *= 0.5 * Renderer.WIDTH;
            projected_point.y *= 0.5 * Renderer.HEIGHT;



            if (i == 0)
            {
                path.moveTo(projected_point.x, projected_point.y);
            } else {
                path.lineTo(projected_point.x, projected_point.y);
            }
        }

        path.closePath();
        g2.draw(path);

    }
}
