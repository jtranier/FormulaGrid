package formulagrid

class PathFinder {

    List<Point> findPath(Move move) {
        StraightLineEquation equation = new StraightLineEquation(move.from, move.to)

        if (move.deltaX > move.deltaY) {
            return (move.from.x..move.to.x).collect { int x ->
                equation.getPointFromX(x)
            }
        }
        else {
            return (move.from.y..move.to.y).collect { int y ->
                equation.getPointFromY(y)
            }
        }
    }


}
