import java.awt.*;
import java.awt.geom.Path2D;

public class Triangle {

    Vertex[] points;
    Color color;

    Triangle(Vertex p1, Vertex p2, Vertex p3, Color color)
    {
        this.points = new Vertex[]{p1, p2, p3};
        this.color = color;
    }

    Triangle(Vertex[] p, Color c)
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
            Vertex p = this.points[i];

            // Rotate
            Vertex rotated_point = z_r_transform.multiplyPoint(x_r_transform.multiplyPoint(p));


            // Translate / Project
            rotated_point.z += 5;

            Vertex projected_point = map_projection.multiplyPoint(rotated_point);

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
