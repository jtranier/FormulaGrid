package formulagrid

import spock.lang.Specification
import java.awt.Point

class TrackStartGridSpec extends Specification {

    def "constructor"() {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()

        expect: "Initially, there's no start position"
        for(int num in 1..6) {
            startGrid.getStartPositionPoint(num) == null
        }
    }

    def "addStartPosition - valid"() {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()

        when:
        startGrid.addStartPosition(num, x, y)

        then:
        startGrid.getStartPositionPoint(num) == new Point(x,y)

        where:
        num | x | y
        1   | 1 | 1
        2   | 2 | 2
        3   | 3 | 3
        4   | 4 | 4
        5   | 5 | 5
        6   | 6 | 6
    }

    def "addStartPosition - redefine a start position"() {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()
        startGrid.addStartPosition(1, 0, 0)

        when:
        startGrid.addStartPosition(1, 0, 0)

        then:
        def e = thrown(IllegalStateException)
        e.message == "Start position 1 is already defined."
    }

    def "addStartPosition - 2 start position on same location"() {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()
        startGrid.addStartPosition(1, 0, 0)

        when:
        startGrid.addStartPosition(2, 0, 0)

        then:
        def e = thrown(IllegalStateException)
        e.message == "Start position 1 occupies location (0,0)."
    }

    def "addStartPosition - invalid num"(int invalidNum) {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()

        when:
        startGrid.addStartPosition(invalidNum, 0, 0)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Argument 'num' must be between 1 and 6 ; given $invalidNum."

        where:
        invalidNum << [-1, 0, 7]
    }

    def "validate - valid"() {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()
        startGrid.addStartPosition(1, 1, 1)
        startGrid.addStartPosition(2, 2, 2)

        expect:
        startGrid.validate()
    }

    def "validate - no start position"() {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()

        when:
        startGrid.validate()

        then:
        def e = thrown(IllegalStateException)
        e.message == "Start position 1 is undefined."
    }

    def "validate - missing start position"() {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()
        startGrid.addStartPosition(1, 1, 1)
        startGrid.addStartPosition(3, 3, 3)

        when:
        startGrid.validate()

        then:
        def e = thrown(IllegalStateException)
        e.message == "Start position 3 is defined wheras start position 2 is undefined."
    }

    def "(de)normalizeNum"() {
        setup:
        TrackStartGrid startGrid = new TrackStartGrid()

        expect:
        startGrid.denormalizeNum(startGrid.normalizeNum(num)) == num

        where:
        num << [1, 2, 3, 4, 5, 9]
    }
}
