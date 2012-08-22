package formulagrid

import grails.plugin.spock.UnitSpec

class CarSpec extends UnitSpec {

    def "intendedMove - without position"() {
        given:
        Car car = new Car()

        when:
        car.intendedMove()

        then:
        def e = thrown(IllegalStateException)
        e.message == "The car has no position."
    }

    def "intendedMove - with position"(int px, int py, int vx, int vy) {
        setup:
        Car car = new Car(
                position: new Point(px, py),
                speed: new Point(vx, vy)
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
                speed: new Point(initialSpeedX, initialSpeedY)
        )


        when:
        car.accelerate(new Point(deltaSpeedX, deltaSpeedY))

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
