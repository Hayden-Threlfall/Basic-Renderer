public class Camera {
    Vertex pos;
    Vertex rot;
    double userX;
    double userY;
    double speed = 10;


    Camera() {
        this.pos = new Vertex(0, 0, 0);
        this.rot = new Vertex(0, 0, 0);
        this.userX = 0;
        this.userY = 0;
    }

    Camera(double x, double y, double z) {
        this.pos = new Vertex(x, y, z);
        this.rot = new Vertex(0, 0, 0);
        this.userX = 0;
        this.userY = 0;
    }

    Camera(double x, double y, double z, double rotX, double rotY, double rotZ) {
        this.pos = new Vertex(x, y, z);
        this.rot = new Vertex(rotX, rotY, rotZ);
        this.userX = 0;
        this.userY = 0;
    }

    //SIMPLE MOVES NOT USING ROTATION YET//
    void moveLeft() {
        this.pos.x -= speed;
    }

    void moveRight() {
        this.pos.x += speed;
    }

    void moveFoward() {
        this.pos.y += speed;
    }

    void moveBack() {
        this.pos.y -= speed;
    }
}
