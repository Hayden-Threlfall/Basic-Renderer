public class Camera {
    Vertex pos;
    Vertex rot;
    double userX;
    double userY;


    Camera(double x, double y, double z)
    {
        this.pos = new Vertex(x, y, z);
        this.rot = new Vertex(0, 0, 0);
        this.userX = 0;
        this.userY = 0;
    }

    Camera(double x, double y, double z, double rotX, double rotY, double rotZ)
    {
        this.pos = new Vertex(x, y, z);
        this.rot = new Vertex(rotX, rotY, rotZ);
        this.userX = 0;
        this.userY = 0;
    }

    //SIMPLE MOVES NOT USING ROTATION YET//
    moveLeft(double num) {
        pos.x -= 10;
    }

    moveRight(double num) {
        pos.x += 10;
    }

    moveFoward(double num) {
        pos.y += 10;
    }

    moveBack(double num) {
        pos.y -= 10;
    }
}
