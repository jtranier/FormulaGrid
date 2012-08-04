package formulagrid

import grails.plugin.spock.ControllerSpec
import grails.test.mixin.TestFor

@TestFor(CarController)
class CarControllerSpec extends ControllerSpec {

    def "index"() {
        setup :
        CarFactoryService carFactoryService = Mock(CarFactoryService)
        carFactoryService.getDemoCar() >> demoCar
        controller.carFactoryService = carFactoryService

        expect:
        controller.index() == [car: demoCar]

        where:
        demoCar = new Car(name: "demo")
    }

    def "makeAMove - valid request"() {
        setup:
        AccelerateCommand command = new AccelerateCommand(accelerationCode: accelerationCode)

        CarService carService = Mock(CarService)
        controller.carService = carService

        CarFactoryService carFactoryService = Mock(CarFactoryService)
        carFactoryService.demoCar >> demoCar
        controller.carFactoryService = carFactoryService

        mockCommandObject(AccelerateCommand)

        // Redirect doesn't work properly (issue with Grails 2.1.0 ?)
        // We redefine the method to test it
        String redirectAction = ""
        controller.metaClass.redirect { Map map -> redirectAction = map.action}

        when:
        controller.makeAMove(command)

        then:
        1 * carService.makeAMove(demoCar, new Speed(x:  speedX, y:  speedY))

        then:
        redirectAction == "index"

        where:
        accelerationCode | speedX  | speedY
        1                | -1      | -1
        2                |  0      | -1
        3                | +1      | -1
        4                | -1      |  0
        5                |  0      |  0
        6                | +1      |  0
        7                | -1      | +1
        8                |  0      | +1
        9                | +1      | +1

        demoCar = new Car(name: "demo")
    }

    def "makeAMove - Invalid request"() {
        setup:
        mockCommandObject(AccelerateCommand)

        when:
        controller.makeAMove(new AccelerateCommand(accelerationCode: wrongAccelerationCode))

        then:
        thrown(IllegalArgumentException)

        where:
        wrongAccelerationCode << [null, 0, 10]
    }
}
