package formulagrid

import grails.plugin.spock.UnitSpec
import grails.test.mixin.TestFor

@TestFor(RaceController)
class AccelerationCommandSpec extends UnitSpec {

    def "validate"() {
        setup:
        mockCommandObject(AccelerateCommand)
        AccelerateCommand command = new AccelerateCommand(accelerationCode: accelerationCode)

        expect:
        command.validate() == (accelerationCode in 1..9)

        where:
        accelerationCode << [null, -1, 0, 1, 2, 9, 10]
    }
}
