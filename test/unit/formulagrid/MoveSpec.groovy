package formulagrid

import spock.lang.Specification

class MoveSpec extends Specification {
    def "getDeltaX"() {
        setup:
        Move move =  new Move(
                from: new Point(x: (int)fromX, y: (int)fromY),
                to: new Point(x:  (int)toX, y: (int)toY)
        )

        expect:
        move.deltaX == Math.abs((int)fromX - (int)toX)

        where:
        [fromX, fromY, toX, toY] << ([[-1, 0, 1]] *4).combinations()

    }

    def "getDeltaY"() {
        setup:
        Move move =  new Move(
                from: new Point(x: (int)fromX, y: (int)fromY),
                to: new Point(x:  (int)toX, y: (int)toY)
        )

        expect:
        move.deltaY == Math.abs((int)fromY - (int)toY)

        where:
        [fromX, fromY, toX, toY] << ([[-1, 0, 1]] *4).combinations()
    }
}
