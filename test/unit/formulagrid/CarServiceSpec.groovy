package formulagrid

import grails.plugin.spock.UnitSpec

class CarServiceSpec extends UnitSpec {

    def "makeAMove - normal"() {
        setup:
        CarService carService = new CarService()
        Car car = Mock(Car)

        when:
        carService.makeAMove(car, deltaSpeed)

        then:
        1 * car.accelerate(deltaSpeed)

        then:
        1 * car.move()

        then:
        1 * car.save(_)

        where:
        deltaSpeed = new Speed(x: 1, y: 2)
    }
}
