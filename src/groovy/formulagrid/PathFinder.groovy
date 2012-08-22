package formulagrid

class PathFinder {

    Path findPath(Move move) {
        StraightLineEquation equation = new StraightLineEquation(move.from, move.to)

        List<Point> allPoint = []

        if (move.deltaX > move.deltaY) {
            allPoint = (move.from.x..move.to.x).collect { int x ->
                equation.getPointFromX(x)
            }
        }
        else {
            allPoint =  (move.from.y..move.to.y).collect { int y ->
                equation.getPointFromY(y)
            }
        }

        return new Path(
                from: allPoint.first(),
                to:  allPoint.last(),
                allIntermediaryPoint: allPoint.size() > 2 ? allPoint[1..-2] : []
        )
    }


}
