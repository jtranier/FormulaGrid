package formulagrid

class CarController {

    CarFactoryService carFactoryService
    CarService carService

    def index() {
        return [car: carFactoryService.demoCar]
    }

    def makeAMove(AccelerateCommand command) {
        if(!command.validate()) {
            throw new IllegalArgumentException(command.errors.toString())
        }

        Speed deltaSpeed = getAccelerationFromCode(command.accelerationCode)
        carService.makeAMove(carFactoryService.demoCar, deltaSpeed)
        redirect(action:  "index")
    }

    private Speed getAccelerationFromCode(int accelerationCode) {
        int deltaSpeedX = 0
        if (accelerationCode in [1, 4, 7]) {
            deltaSpeedX = -1
        }
        else if (accelerationCode in [3, 6, 9]) {
            deltaSpeedX = 1
        }

        int deltaSpeedY = 0
        if (accelerationCode in [1, 2, 3]) {
            deltaSpeedY = -1
        }
        else if (accelerationCode in [7, 8, 9]) {
            deltaSpeedY = 1
        }

        return new Speed(x: deltaSpeedX, y: deltaSpeedY)
    }

}
