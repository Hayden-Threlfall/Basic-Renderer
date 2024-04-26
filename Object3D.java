import java.awt.*;

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

    public void draw(Graphics2D g2, Matrix4 x_r_transform, Matrix4 z_r_transform, Matrix4 map_projection)
    {
        g2.setColor(this.color);

        for (Triangle t : this.triangles)
        {
            t.draw(g2, x_r_transform, z_r_transform, map_projection);
        }
    }

    public void print()
    {
        for (Triangle t : this.triangles)
        {
            for (Vertex p : t.points)
            {
                p.print();
            }
            System.out.println();
        }
    }
}
