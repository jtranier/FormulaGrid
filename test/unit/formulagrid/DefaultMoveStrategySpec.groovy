package formulagrid

import spock.lang.Specification

class DefaultMoveStrategySpec extends Specification {

    DefaultMoveStrategy strategy = new DefaultMoveStrategy()

    def "calculateMoveResult - without obstacle"(List<Point> path) {
        setup:
        TrackPoint freePoint = new TrackPoint()
        TrackMap map = Mock(TrackMap)
        map.getTrackPoint(_) >> freePoint

        Track track = Mock(Track)
        track.map >> map

        expect:
        MoveResult moveResult = strategy.calculateMoveResult(track, path)
        !moveResult.crash
        moveResult.finalPosition == path.last()

        where:
        path = [
                new Point(x: 1, y: 1),
                new Point(x: 2, y: 2),
                new Point(x: 3, y: 3)
        ]
    }

    def "calculateMoveResult - with obstacle"(List<Point> path) {
        setup:
        TrackPoint freePoint = new TrackPoint()
        TrackPoint obstaclePoint = new TrackPoint(obstacle: true)
        TrackMap map = Mock(TrackMap)
        Track track = Mock(Track)
        track.map >> map

        when:
        MoveResult moveResult = strategy.calculateMoveResult(track, path)

        then:
        map.getTrackPoint( {Point p -> p.x != 2} ) >> freePoint
        map.getTrackPoint( {Point p -> p.x == 2} ) >> obstaclePoint

        then:
        moveResult.crash
        moveResult.finalPosition == path.first()

        and:
        when:
        moveResult = strategy.calculateMoveResult(track, path)

        then:
        map.getTrackPoint( {Point p -> p.x != 3} ) >> freePoint
        map.getTrackPoint( {Point p -> p.x == 3} ) >> obstaclePoint

        then:
        moveResult.crash
        moveResult.finalPosition == path[1]

        where:
        path = [
                new Point(x: 1, y: 1),
                new Point(x: 2, y: 2),
                new Point(x: 3, y: 3)
        ]
    }
}
