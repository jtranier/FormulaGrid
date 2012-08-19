package formulagrid

class Car {

    Integer num = null

    Point position = new Point(x: 0, y: 0)

    Point speed = new Point(x: 0, y: 0)

    Move intendedMove() {
        return new Move(
                from: new Point(x: position.x, y: position.y),
                to: new Point(x: position.x+speed.x, y:  position.y+speed.y)
        )
    }

    void accelerate(Point deltaSpeed) {
        speed.x += deltaSpeed.x
        speed.y += deltaSpeed.y
    }

    @Override
    public String toString() {
        return "Car{" +
                ", positionX=" + position.x +
                ", positionY=" + position.y +
                ", speedX=" + speed.x +
                ", speedY=" + speed.y +
                '}';
    }
}
