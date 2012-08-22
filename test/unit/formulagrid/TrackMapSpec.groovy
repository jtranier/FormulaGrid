package formulagrid

import spock.lang.Specification
import com.sun.jna.PointerType

class TrackMapSpec extends Specification {

    TrackMap trackMap = new TrackMap()

    def "getTrackPoint"(int x, int y) {
        setup:
        TrackPoint[][] bitmap = TrackMapTestUtil.createBitmapTest(3, 3)
        trackMap.bitmap = bitmap

        expect:
        trackMap.getTrackPoint(new Point(x, y)) == bitmap[x][y]

        where:
        [x, y] << [[0, 1, 2], [0, 1, 2]].combinations()
    }

    def "getTrackPoint - with null argument"() {
        when:
        trackMap.getTrackPoint(null)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Argument 'point' must be not null."
    }

    def "getTrackPoint - with null bitmap"() {
        when:
        trackMap.getTrackPoint(new Point(x: 0, y: 0))

        then:
        thrown(NullPointerException)
    }

    def "getTrackPoint - with wrong coordinates"() {
        setup:
        trackMap.bitmap = TrackMapTestUtil.createBitmapTest(3, 3)

        when:
        trackMap.getTrackPoint(new Point(x: 10, y: 10))

        then:
        thrown(ArrayIndexOutOfBoundsException)
    }

    def "moveCarTo - position"() {
        setup:
        TrackMap map = new TrackMap(TrackMapTestUtil.createBitmapTest(3, 3))
        map.getTrackPoint(from).car = car

        when:
        map.moveCarTo(car, to)

        then:
        map.getTrackPoint(from).car == null
        car.position == to
        map.getTrackPoint(to).car == car

        where:
        from = new Point(1, 1)
        to = new Point(2, 2)
        car = new Car(position: from)
    }

    def "moveCarTo - path"() {
        setup:
        TrackMap map = new TrackMap(TrackMapTestUtil.createBitmapTest(3, 3))
        map.getTrackPoint(from).car = car

        when:
        map.moveCarTo(car, path)

        then:
        map.getTrackPoint(from).car == null
        car.position == to
        map.getTrackPoint(to).car == car

        where:
        from = new Point(1, 1)
        to = new Point(2, 2)
        path = new Path(from: from, to: to)
        car = new Car(position: from)
    }

    def "getAsciiRepresentation"() {
        setup:
        TrackPoint[][] bitmap = TrackMapTestUtil.createBitmapTest(3, 3)
        TrackMap map = new TrackMap(bitmap: bitmap)

        when:
        Character[][] asciiRepresentation = map.asciiRepresentation

        then:
        3.times { x ->
            3.times { y ->
                asciiRepresentation[x][y] == bitmap[x][y].asciiRepresentation
            }
        }
    }
}
