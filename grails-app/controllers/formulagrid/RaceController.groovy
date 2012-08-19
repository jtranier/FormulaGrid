package formulagrid

class RaceController {

    RaceSessionService raceSessionService

    def index() {
        return [race: raceSessionService.race]
    }

    def makeAMove(AccelerateCommand command) {
        if(!command.validate()) {
            throw new IllegalArgumentException(command.errors.toString())
        }

        Point deltaSpeed = getAccelerationFromCode(command.accelerationCode)
        raceSessionService.race.play(deltaSpeed)
        redirect(action:  "index")
    }

    private Point getAccelerationFromCode(int accelerationCode) {
        int deltaSpeedX = 0
        if (accelerationCode in [1, 4, 7]) {
            deltaSpeedX = -1
        }
        else if (accelerationCode in [3, 6, 9]) {
            deltaSpeedX = 1
        }

        int deltaSpeedY = 0
        if (accelerationCode in [1, 2, 3]) {
            deltaSpeedY = 1
        }
        else if (accelerationCode in [7, 8, 9]) {
            deltaSpeedY = -1
        }

        return new Point(x: deltaSpeedX, y: deltaSpeedY)
    }
}
