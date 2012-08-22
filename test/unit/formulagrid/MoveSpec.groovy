package formulagrid

import spock.lang.Specification

class MoveSpec extends Specification {
    def "getDeltaX"(int fromX, int fromY, int toX, int toY) {
        setup:
        Move move = new Move(
                from: new Point(fromX, fromY),
                to: new Point(toX, toY)
        )

        expect:
        move.deltaX == Math.abs(fromX - toX)

        where:
        [fromX, fromY, toX, toY] << ([[-1, 0, 1]] * 4).combinations()

    }

    def "getDeltaY"(int fromX, int fromY, int toX, int toY) {
        setup:
        Move move = new Move(
                from: new Point(fromX, fromY),
                to: new Point(toX, toY)
        )

        expect:
        move.deltaY == Math.abs((int) fromY - (int) toY)

        where:
        [fromX, fromY, toX, toY] << ([[-1, 0, 1]] * 4).combinations()
    }

    def "toString"() {
        expect:
        new Move(from: from, to: to).toString()

        where:
        from = new Point(0, 0)
        to = new Point(1, 1)
    }

    def "equals"(int xFrom1,
                 int yFrom1,
                 int xTo1,
                 int yTo1,
                 int xFrom2,
                 int yFrom2,
                 int xTo2,
                 int yTo2,
                 Point from1,
                 Point to1,
                 Point from2,
                 Point to2,
                 Move move1,
                 Move move2) {
        expect:
        if (from1 == from2 && to1 == to2) {
            move1 == move2
        }
        else {
            move1 != move2
        }
        move1 == move1
        move1 != "another class"

        where:
        [xFrom1, yFrom1, xTo1, yTo1, xFrom2, yFrom2, xTo2, yTo2] << ([[-1, 1]] * 8).combinations()
        from1 = new Point(xFrom1, yFrom1)
        to1 = new Point(xTo1, yTo1)
        from2 = new Point(xFrom2, yFrom2)
        to2 = new Point(xTo2, yTo2)
        move1 = new Move(from: from1, to: to1)
        move2 = new Move(from: from2, to: to2)
    }

    def "hashCode"() {
        expect:
        move.hashCode()

        where:
        from = new Point(0, 0)
        to = new Point(1, 1)
        move = new Move(from: from, to: to)
    }
}
