package formulagrid

class Car {

    String name

    int positionX = 0
    int positionY = 0

    int speedX = 0
    int speedY = 0

    void move() {
        positionX += speedX
        positionY += speedY
    }

    void accelerate(Speed deltaSpeed) {
        speedX += deltaSpeed.x
        speedY += deltaSpeed.y
    }

    static constraints = {
        name(unique: true)
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Car car = (Car) o

        if (name != car.name) return false

        return true
    }

    int hashCode() {
        return (name != null ? name.hashCode() : 0)
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                '}';
    }
}
