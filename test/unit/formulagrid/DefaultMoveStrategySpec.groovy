package formulagrid

import spock.lang.Specification

class DefaultMoveStrategySpec extends Specification {

    DefaultMoveStrategy strategy = new DefaultMoveStrategy()

    def "calculatePathAccordingToMap - without obstacle"(Path path) {
        setup:
        TrackPoint freePoint = new TrackPoint()
        TrackMap map = Mock(TrackMap)
        map.getTrackPoint(_) >> freePoint

        Track track = Mock(Track)
        track.map >> map

        expect:
        Path finalPath = strategy.calculatePathAccordingToMap(track, path)
        !finalPath.crash
        finalPath.to == path.to

        where:
        path = new Path(
                from: new Point(1, 1),
                allIntermediaryPoint: [new Point(2, 2)],
                to: new Point(3, 3)
        )
    }

    def "calculatePathAccordingToMap - with obstacle"(Path intendedPath) {
        setup:
        TrackPoint freePoint = new TrackPoint()
        TrackPoint obstaclePoint = new TrackPoint(obstacle: true)
        TrackMap map = Mock(TrackMap)
        Track track = Mock(Track)
        track.map >> map

        when:
        Path effectivePath = strategy.calculatePathAccordingToMap(track, intendedPath)

        then:
        map.getTrackPoint({Point p -> p.x != 2}) >> freePoint
        map.getTrackPoint({Point p -> p.x == 2}) >> obstaclePoint

        then:
        effectivePath.crash
        effectivePath.to == intendedPath.from

        and:
        when:
        effectivePath = strategy.calculatePathAccordingToMap(track, intendedPath)

        then:
        map.getTrackPoint({Point p -> p.x != 3}) >> freePoint
        map.getTrackPoint({Point p -> p.x == 3}) >> obstaclePoint

        then:
        effectivePath.crash
        effectivePath.to == intendedPath.allIntermediaryPoint[0]

        where:
        intendedPath = new Path(
                from:  new Point(1, 1),
                allIntermediaryPoint: [new Point(2, 2)],
                to:  new Point(3, 3)
        )
    }
}
