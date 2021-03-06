package formulagrid

class Car {

    Integer num = null
    Point position = null
    Point speed = new Point(0, 0)
    List<Path> trajectory = []

    void followPath(Path path) {
        trajectory << path
        position = path.to
    }

    Move intendedMove() {
        if (!position) {
            throw new IllegalStateException("The car has no position.")
        }

        return new Move(
                from: new Point(position.x, position.y),
                to: new Point(position.x + speed.x, position.y + speed.y)
        )
    }

    void accelerate(Point deltaSpeed) {
        speed.x += deltaSpeed.x
        speed.y += deltaSpeed.y
    }

    static final Closure MARSHALLER = { Car car ->
        Map result = [
                num: car.num,
                trajectory: car.trajectory.collect { Path path ->
                    path.from
                } + [car.position],
                position: car.position,
                speed: car.speed
        ]

        return result
    }

    @Override
    public String toString() {
        return "Car{" +
                ", positionX=" + position?.x +
                ", positionY=" + position?.y +
                ", speedX=" + speed.x +
                ", speedY=" + speed.y +
                '}';
    }
}
