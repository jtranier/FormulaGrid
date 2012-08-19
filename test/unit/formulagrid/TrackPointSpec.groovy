package formulagrid

import spock.lang.Specification

class TrackPointSpec extends Specification {

    def "isFree"(boolean obstacle, Car car) {
        setup:
        TrackPoint trackPoint = new TrackPoint(
                obstacle: obstacle,
                car:  car
        )

        expect:
        trackPoint.isFree() == (!obstacle && !car)

        where:
        [obstacle, car] << [[true, false], [null, new Car()]].combinations()
    }
}
