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

    public void draw(Graphics2D g2, Point3D camera, double d)
    {
        short i = 0;
        Path2D path = new Path2D.Double();
        g2.setColor(this.color);

        // Translae Points to camera
        for (Point3D point : this.points)
        {
            double camera_plane = point.z - camera.z;
            // double path_x = ((point.x - camera.x) * (camera_plane / point.z)) + camera.x;
            // double path_y = ((point.x - camera.x) * (camera_plane / point.z)) + camera.x;
            double path_x = point.x * (d / point.z);
            double path_y = point.y * (d / point.z);


            if (i == 0)
            {
                path.moveTo(path_x, path_y);
            }
            else
            {
                path.lineTo(path_x, path_y);
            }
            i++;
        }

        System.out.println("stop");
        path.closePath();
        g2.draw(path);

    }
}
