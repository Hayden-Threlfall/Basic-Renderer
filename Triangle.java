import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

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

        Vertex[] projected_points = new Vertex[3];

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

            // Draw Lines for Triangles
            if (i == 0)
            {
                path.moveTo(projected_point.x, projected_point.y);
            } else {
                path.lineTo(projected_point.x, projected_point.y);
            }

            // Store for projected points for rasterisation
            projected_points[i] = projected_point;
        }

        // Draw Triangle Lines
        path.closePath();
        g2.draw(path);

        // Rasterisation
        Vertex v1 = projected_points[0];
        Vertex v2 = projected_points[1];
        Vertex v3 = projected_points[2];

        BufferedImage img = new BufferedImage(Renderer.WIDTH, Renderer.HEIGHT, BufferedImage.TYPE_INT_ARGB);

        // Calculate Ranges
        int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
        int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
        int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
        int maxY = (int) Math.min(img.getHeight() - 1, Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                Vertex p = new Vertex(x, y, 0);

                // Judge once for each vertex
                boolean V1 = sameSide(v1, v2, v3, p);
                boolean V2 = sameSide(v2, v3, v1, p);
                boolean V3 = sameSide(v3, v1, v2, p);
                if (V3 && V2 && V1) {
                    img.setRGB(x, y, this.color.getRGB());
                }
            }
        }

        g2.drawImage(img, 0, 0, null);
    }

    static boolean sameSide(Vertex A, Vertex B, Vertex C, Vertex P)
    {
        Vertex AB = new Vertex(B.x - A.x, B.y - A.y, B.z - A.z);
        Vertex AC = new Vertex(C.x - A.x, C.y - A.y, C.z - A.z);
        Vertex AP = new Vertex(P.x - A.x, P.y - A.y, P.z - A.z);

        // Take cross products
        double ABcrossAC = AB.x * AC.y - AC.x * AB.y;
        double ABcrossAP = AB.x * AP.y - AP.x * AB.y;

        return ABcrossAC * ABcrossAP >= 0;

    }
}
