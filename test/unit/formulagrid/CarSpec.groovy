package formulagrid

import grails.plugin.spock.UnitSpec

class CarSpec extends UnitSpec {

    def "intendedMove"() {
        setup:
        Car car = new Car(
                position: new Point(x: (int)px, y: (int)py),
                speed: new Point(x:  (int)vx, y: (int)vy)
        )

        expect:
        Move move = car.intendedMove()
        move.from == car.position
        move.to == car.position + car.speed

        where:
        [px, py, vx, vy] << ([[-1, 0, 1]] *4).combinations()
    }

    def "accelerate"(int initialSpeedX, int initialSpeedY, int deltaSpeedX, int deltaSpeedY) {
        setup:
        Car car = new Car(
                speed: new Point(x:  initialSpeedX, y: initialSpeedY)
        )


        when:
        car.accelerate(new Point(x: deltaSpeedX, y: deltaSpeedY))

        then:
        car.speed.x == initialSpeedX + deltaSpeedX
        car.speed.y == initialSpeedY + deltaSpeedY

        where:
        [
                initialSpeedX,
                initialSpeedY,
                deltaSpeedX,
                deltaSpeedY
        ] << ([[-1, 0, 1]]*4).combinations()
    }

    def "toString"() {
        setup:
        Car car = new Car()

        expect:
        car.toString()
    }
}
