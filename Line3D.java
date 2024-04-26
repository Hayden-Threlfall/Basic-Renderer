import java.awt.*;
import java.awt.geom.Path2D;

public class Line3D extends Object3D
{
    Vertex[] points;

    Color color;

    Line3D(Vertex[] p, Color c)
    {
        this.points = p;
        this.color = c;
    }

    public void draw(Graphics2D g2, Matrix4 x_r_transform, Matrix4 z_r_transform, Matrix4 map_projection)
    {
        Path2D path = new Path2D.Double();
        g2.setColor(this.color);

        // Rotate
        Vertex t_p1 = z_r_transform.multiplyPoint(x_r_transform.multiplyPoint(this.points[0]));
        Vertex t_p2 = z_r_transform.multiplyPoint(x_r_transform.multiplyPoint(this.points[1]));

        // Translate / Project
        t_p1.z += 4;
        t_p2.z += 4;

        Vertex p_p1 = map_projection.multiplyPoint(t_p1);
        Vertex p_p2 = map_projection.multiplyPoint(t_p2);

        // Scale Point
        p_p1.x += 1;
        p_p1.y += 1;
        p_p2.x += 1;
        p_p2.y += 1;

        p_p1.x *= 0.5 * Renderer.WIDTH;
        p_p1.y *= 0.5 * Renderer.HEIGHT;
        p_p2.x *= 0.5 * Renderer.WIDTH;
        p_p2.y *= 0.5 * Renderer.HEIGHT;


//        System.out.println("Draw line " + this.color + ", from (" + p_p1.x + ", " + p_p1.y + ") to (" + p_p2.x + ", " + p_p2.y + ").");

        path.moveTo(p_p1.x, p_p1.y);
        path.lineTo(p_p2.x, p_p2.y);
        path.closePath();
        g2.draw(path);
    }

}
