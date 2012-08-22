package formulagrid

import spock.lang.Specification

class PathSpec extends Specification {
    def "equals"() {

        expect:
        path1 == path1
        path1 != "another class"
        path1 == new Path(
                from: new Point(0, 0),
                to: new Point(2, 2),
                allIntermediaryPoint: [new Point(1, 1)],
                crash: true
        )

        path1 != new Path(
                from: differentPoint,
                to: new Point(2, 2),
                allIntermediaryPoint: [new Point(1, 1)],
                crash: true
        )

        path1 != new Path(
                from: new Point(0, 0),
                to: differentPoint,
                allIntermediaryPoint: [new Point(1, 1)],
                crash: true
        )

        path1 != new Path(
                from: new Point(0, 0),
                to: new Point(2, 2),
                allIntermediaryPoint: [differentPoint],
                crash: true
        )

        path1 != new Path(
                from: new Point(0, 0),
                to: new Point(2, 2),
                allIntermediaryPoint: [new Point(1, 1)],
                crash: false
        )

        where:
        path1 = new Path(
                from: new Point(0, 0),
                to: new Point(2, 2),
                allIntermediaryPoint: [new Point(1, 1)],
                crash: true
        )

        differentPoint = new Point(4, 4)
    }
}
