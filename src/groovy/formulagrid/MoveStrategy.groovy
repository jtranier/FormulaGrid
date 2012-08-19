package formulagrid

public interface MoveStrategy {

    MoveResult calculateMoveResult(Track track, List<Point> path)
}