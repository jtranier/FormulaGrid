package formulagrid

import spock.lang.Specification

class PathFinderSpec extends Specification {

    PathFinder pathFinder = new PathFinder()

    def "findPath"(int fromX, int fromY, int toX, int toY, Move move, StraightLineEquation equation) {
        when:
        Path path = pathFinder.findPath(move)

        then:
        path.from == move.from
        path.to == move.to
        (path.allIntermediaryPoint + path.from + path.to).each { Point point ->
            point == equation.getPointFromX(point.x)
            point == equation.getPointFromY(point.y)
        }
        !path.allIntermediaryPoint.contains(path.from)
        !path.allIntermediaryPoint.contains(path.to)
        if(path.allIntermediaryPoint) {
            new Move(from: path.from, to: path.allIntermediaryPoint.first()).distance == 1
            new Move(from: path.allIntermediaryPoint.last(), to: path.to).distance == 1
            path.allIntermediaryPoint[1..-1].eachWithIndex { Point point, int i ->
                new Move(from: path.allIntermediaryPoint[i-1], to:  point).distance == 1
            }
        }


        where:
        [fromX, fromY] << ([[-5, 0, 5]] * 2).combinations()
        [toX, toY] << ([[-4, 1, 4]] * 2).combinations()

        move = new Move(from: new Point(fromX, fromY), to: new Point(toX, toY))
        equation = new StraightLineEquation(move.from, move.to)
    }
}
