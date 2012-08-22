package formulagrid

class DefaultMoveStrategy implements MoveStrategy {

    Path calculatePathAccordingToMap(Track track, Path intendedPath) {
        Point lastFreePoint = intendedPath.from
        Point beforeLastFreePoint = null

        List allIntermediaryFreePoint = []

        Point obstaclePoint = (intendedPath.allIntermediaryPoint + intendedPath.to).find { Point point ->
            boolean obstacle = !track.map.getTrackPoint(point).isFree()
            if(!obstacle) {
                beforeLastFreePoint = lastFreePoint
                lastFreePoint = point
                if(beforeLastFreePoint) {
                    allIntermediaryFreePoint << point
                }
            }
            return obstacle
        }

        return new Path(
                from:  intendedPath.from,
                to: lastFreePoint,
                allIntermediaryPoint: allIntermediaryFreePoint,
                crash: lastFreePoint != intendedPath.to
        )
    }
}
