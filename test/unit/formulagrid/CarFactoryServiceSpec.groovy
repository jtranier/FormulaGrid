package formulagrid

import grails.plugin.spock.UnitSpec

class CarFactoryServiceSpec extends UnitSpec {

    def "getDemoCar"() {
        setup:
        CarFactoryService carFactoryService = new CarFactoryService()
        mockDomain(Car)

        expect:
        carFactoryService.demoCar.name == CarFactoryService.NAME_DEMO
        carFactoryService.demoCar.id == carFactoryService.demoCar.id
    }
}
