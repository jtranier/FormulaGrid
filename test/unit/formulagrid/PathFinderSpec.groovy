package formulagrid

import spock.lang.Specification

class PathFinderSpec extends Specification {

    PathFinder pathFinder = new PathFinder()

    def "findPath"(int fromX, int fromY, int toX, int toY, Move move, StraightLineEquation equation) {
        when:
        List<Point> path = pathFinder.findPath(move)

        then:
        path.size() == Math.max(move.deltaX, move.deltaY) + 1
        path.first() == move.from
        path.last() == move.to
        path.each { Point point ->
            point == equation.getPointFromX(point.x)
            point == equation.getPointFromY(point.y)
        }

        where:
        [fromX, fromY] << ([[-5, 0, 5]] * 2).combinations()
        [toX, toY] << ([[-4, 1, 4]] * 2).combinations()

        move = new Move(from: new Point(x: fromX, y: fromY), to: new Point(x: toX, y: toY))
        equation = new StraightLineEquation(move.from, move.to)
    }
}
