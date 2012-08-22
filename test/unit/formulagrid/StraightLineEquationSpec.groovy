package formulagrid

import spock.lang.Specification
import spock.lang.Shared

class StraightLineEquationSpec extends Specification {

    @Shared
    Random random = new Random()

    def "constructor"() {
        when:
        new StraightLineEquation(p1, p2)

        then:
        notThrown(Exception)

        and:
        when:
        new StraightLineEquation(p1, p1)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Two distinct points are required."

        where:
        p1 = new Point(0, 0)
        p2 = new Point(1, 1)
    }

    def "getPointFromX - y = ax+b"(int x1, int x2, int y1, int y2, Point p1, Point p2) {
        setup:
        if (p1.x == p2.x) {
            // Bad luck ... The random generator picked a vertical line
            p2.x++
        }
        if (p1 == p2) {
            // Bad luck ... The random generator picked p1 == p2
            p2.x++
        }
        StraightLineEquation equation = new StraightLineEquation(p1, p2)

        expect:
        equation.getPointFromX(p1.x) == p1
        equation.getPointFromX(p2.x) == p2

        where:
        [x1, y1] << ((1..9).collect { [random.nextInt(1000), random.nextInt(1000)] }) + [[0, 1]]
        [x2, y2] << ((1..9).collect { [random.nextInt(1000), random.nextInt(1000)] }) + [[0, 2]]

        p1 = new Point(x1, y1)
        p2 = new Point(x2, y2)
    }

    def "getPointFromX - vertical line"() {
        setup:
        StraightLineEquation equation = new StraightLineEquation(
                new Point(x: x, y: y1),
                new Point(x: x, y: y2)
        )

        when:
        equation.getPointFromX(x)

        then:
        def e = thrown(IllegalStateException)
        e.message == "Cannot invoke getPointFromX : Equation is of the form 'x = N."

        where:
        x = 0
        y1 = 0
        y2 = 1
    }

    def "getPointFromY"(int x1, int x2, int y1, int y2, Point p1, Point p2) {
        setup:
        if (p1 == p2) {
            // Bad luck ... The random generator picked p1 == p2
            p2.x++
        }
        StraightLineEquation equation = new StraightLineEquation(p1, p2)

        expect:
        equation.getPointFromY(p1.y) == p1
        equation.getPointFromY(p2.y) == p2

        where:
        [x1, y1] << ((1..9).collect { [random.nextInt(1000), random.nextInt(1000)] }) + [[0, 1]]
        [x2, y2] << ((1..9).collect { [random.nextInt(1000), random.nextInt(1000)] }) + [[0, 2]]

        p1 = new Point(x1, y1)
        p2 = new Point(x2, y2)
    }
}
