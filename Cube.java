import java.awt.Color;

public class Cube extends Object3D{

    Cube(int size, Color c, Point3D cube_origin)
    {
        super(new Point3D[]{
                cube_origin,
                new Point3D(cube_origin.x, cube_origin.y, cube_origin.z + size),
                new Point3D(cube_origin.x, cube_origin.y + size, cube_origin.z),
                new Point3D(cube_origin.x, cube_origin.y + size, cube_origin.z + size),
                new Point3D(cube_origin.x + size, cube_origin.y, cube_origin.z),
                new Point3D(cube_origin.x + size, cube_origin.y, cube_origin.z + size),
                new Point3D(cube_origin.x + size, cube_origin.y + size, cube_origin.z),
                new Point3D(cube_origin.x + size, cube_origin.y + size, cube_origin.z + size)
        },
            new int[][] {
                {0, 1},
                {0, 2},
                {3, 1},
                {3, 2},
                {4, 5},
                {4, 6},
                {7, 5},
                {7, 6},
                {0, 4},
                {1, 5},
                {2, 6},
                {3, 7}
        },
            c
        );
    }
}
