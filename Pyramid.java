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

    public void draw(Graphics2D g2, Point3D camera, double d)
    {
        short i = 0;
        Path2D path = new Path2D.Double();
        g2.setColor(this.color);

        Point2D[] translate_points = new Point2D[4];

        // Translae Points to camera
        for (Point3D point : this.points)
        {
            double camera_plane = point.z - camera.z;
            // double path_x = ((point.x - camera.x) * (camera_plane / point.z)) + camera.x;
            // double path_y = ((point.x - camera.x) * (camera_plane / point.z)) + camera.x;
            double path_x = point.x * (d / point.z);
            double path_y = point.y * (d / point.z);


            translate_points[i] = new Point2D(path_x, path_y);
            i++;
        }


        // Draw all Lines
        for (Point2D point : translate_points)
        {
            path.moveTo(point.x, point.y);
            for (Point2D point2 : translate_points)
            {
                if (point == point2)
                    continue;

                path.lineTo(point2.x, point2.y);

            }
        }


        System.out.println("stop");
        path.closePath();
        g2.draw(path);

    }
}
