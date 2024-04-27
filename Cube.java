import java.awt.Color;

public class Cube extends Object3D{

    Cube(double size, Color c, Vertex cube_origin, boolean center_on_origin)
    {
        super();

        if (center_on_origin)
        {
            cube_origin.x -= size / 2;
            cube_origin.y -= size / 2;
            cube_origin.z -= size / 2;
        }

        Vertex[] vertices = new Vertex[]{
                cube_origin,
                new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z),
                new Vertex(cube_origin.x, cube_origin.y, cube_origin.z + size),
                new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z + size),

                new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z),
                new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z),
                new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z + size),
                new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z + size),
        };

        super.triangles = new Triangle[]{
                // South Face
                new Triangle(
                        cube_origin, vertices[4], vertices[5],
                        c
                ),
                new Triangle(
                        cube_origin, vertices[5], vertices[1],
                        c
                ),

                // East Face
                new Triangle(
                        vertices[1], vertices[5], vertices[7],
                        c
                ),
                new Triangle(
                        vertices[1], vertices[7], vertices[3],
                        c
                ),

                // North Face
                new Triangle(
                        vertices[2], vertices[6], vertices[7],
                        c
                ),
                new Triangle(
                        vertices[2], vertices[7], vertices[3],
                        c
                ),

                // West Face
                new Triangle(
                        cube_origin, vertices[4], vertices[6],
                        c
                ),
                new Triangle(
                        cube_origin, vertices[6], vertices[2],
                        c
                ),

                // Bottom
                new Triangle(
                        cube_origin, vertices[2], vertices[3],
                        c
                ),
                new Triangle(
                        cube_origin, vertices[3], vertices[1],
                        c
                ),

                // Top
                new Triangle(
                        vertices[4], vertices[6], vertices[7],
                        c
                ),
                new Triangle(
                        vertices[4], vertices[7], vertices[5],
                        c
                ),
            };
    }

    public void setFaceColor(char Face, Color c)
    {
        switch (Face) {
            case 'S' -> {
                super.triangles[0].color = c;
                super.triangles[1].color = c;
            }
            case 'E' -> {
                super.triangles[2].color = c;
                super.triangles[3].color = c;
            }
            case 'N' -> {
                super.triangles[4].color = c;
                super.triangles[5].color = c;
            }
            case 'W' -> {
                super.triangles[6].color = c;
                super.triangles[7].color = c;
            }
            case 'B' -> {
                super.triangles[8].color = c;
                super.triangles[9].color = c;
            }
            case 'T' -> {
                super.triangles[10].color = c;
                super.triangles[11].color = c;
            }
        }

    }
}
