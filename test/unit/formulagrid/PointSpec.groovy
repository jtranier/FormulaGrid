package formulagrid

import spock.lang.Specification

class PointSpec extends Specification{

    Point point = new Point()

    def "plus"(int x1, int y1, int x2, int y2) {
        setup:
        Point p1 = new Point(x1, y1)
        Point p2 = new Point(x2, y2)

        expect:
        p1 + p2 == p2 + p1
        p1 + p2 == new Point(x1+x2, y1+y2)

        and:
        when:
        p1+p2

        then: "operand are not modified"
        p1 == new Point(x1, y1)
        p2 == new Point(x2, y2)

        where:
        [x1, y1, x2, y2] << ([[-1, 0, 1]] *4).combinations()
    }

    def "equals"(int x1, int y1, int x2, int y2) {
        setup:
        Point p1 = new Point(x1, y1)
        Point p2 = new Point(x2, y2)

        expect:
        !(p1 == "another class")
        if(x1 == x2 && y1 == y2) {
            p1 == p2
        }
        else {
            !(p1 == p2)
        }

        where:
        [x1, y1, x2, y2] << ([[-1, 0, 1]] *4).combinations()
    }

    def "hashCode"() {
        when:
        point.hashCode()

        then:
        true

    }

    def "toString"() {
        expect:
        point.toString()
    }
}
