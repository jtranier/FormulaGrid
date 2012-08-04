package formulagrid

import grails.validation.Validateable

@Validateable
class AccelerateCommand {
    Integer accelerationCode

    static constraints = {
        accelerationCode(inList: 1..9)
    }
}
