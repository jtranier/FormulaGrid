package formulagrid

import grails.plugin.spock.UnitSpec

class CarSpec extends UnitSpec {

    def "move"(int initialX, int initialY, int speedX, int speedY) {
        setup:
        Car car = new Car(
                positionX: initialX,
                positionY: initialY,
                speedX: speedX,
                speedY: speedY
        )


        when:
        car.move()

        then:
        car.positionX == initialX + speedX
        car.positionY == initialY + speedY

        where:
        [initialX, initialY, speedX, speedY] << ([[-1, 0, 1]]*4).combinations()
    }

    def "accelerate"(int initialSpeedX, int initialSpeedY, int deltaSpeedX, int deltaSpeedY) {
        setup:
        Car car = new Car(
                speedX: initialSpeedX,
                speedY: initialSpeedY
        )


        when:
        car.accelerate(new Speed(x: deltaSpeedX, y: deltaSpeedY))

        then:
        car.speedX == initialSpeedX + deltaSpeedX
        car.speedY == initialSpeedY + deltaSpeedY

        where:
        [
                initialSpeedX,
                initialSpeedY,
                deltaSpeedX,
                deltaSpeedY
        ] << ([[-1, 0, 1]]*4).combinations()
    }
}
