import java.awt.Color;

public class Cube extends Object3D{

    Cube(double size, Color c, Vertex cube_origin)
    {
        super(new Triangle[]{
                // South Face
                new Triangle(
                        cube_origin,
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z),
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z),
                        c
                ),
                new Triangle(
                        new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z),
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z),
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z),
                        c
                ),

                // East Face
                new Triangle(
                        new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z),
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z),
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z + size),
                        c
                ),
                new Triangle(
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z + size),
                        new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z + size),
                        new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z),
                        c
                ),

                // North Face
                new Triangle(
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z + size),
                        new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z + size),
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z + size),
                        c
                ),
                new Triangle(
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z + size),
                        new Vertex(cube_origin.x, cube_origin.y, cube_origin.z + size),
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z + size),
                        c
                ),

                // West Face
                new Triangle(
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z),
                        cube_origin,
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z + size),
                        c
                ),
                new Triangle(
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z + size),
                        cube_origin,
                        new Vertex(cube_origin.x, cube_origin.y, cube_origin.z + size),
                        c
                ),

                // Bottom
                new Triangle(
                        cube_origin,
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z),
                        new Vertex(cube_origin.x, cube_origin.y, cube_origin.z + size),
                        c
                ),
                new Triangle(
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z + size),
                        new Vertex(cube_origin.x + size, cube_origin.y, cube_origin.z),
                        new Vertex(cube_origin.x, cube_origin.y, cube_origin.z + size),
                        c
                ),

                // Top
                new Triangle(
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z),
                        new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z),
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z + size),
                        c
                ),
                new Triangle(
                        new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z + size),
                        new Vertex(cube_origin.x + size, cube_origin.y + size, cube_origin.z),
                        new Vertex(cube_origin.x, cube_origin.y + size, cube_origin.z + size),
                        c
                ),
            },
            c
        );
    }
}
