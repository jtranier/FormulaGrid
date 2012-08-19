package formulagrid

class DefaultMoveStrategy implements MoveStrategy {

    MoveResult calculateMoveResult(Track track, List<Point> path) {
        Point lastFreePoint = path[0]

        Point obstaclePoint = path[1..-1].find { Point point ->
            boolean obstacle = !track.map.getTrackPoint(point).isFree()
            if(!obstacle) {
                lastFreePoint = point
            }
            return obstacle
        }

        return new MoveResult(
                finalPosition: lastFreePoint,
                crash: obstaclePoint != null
        )
    }
}
