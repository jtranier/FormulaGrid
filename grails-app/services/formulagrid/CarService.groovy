package formulagrid

class CarService {

    void makeAMove(Car car, Speed deltaSpeed) {
        car.accelerate(deltaSpeed)
        car.move()
        car.save(failOnError: true)
    }
}
