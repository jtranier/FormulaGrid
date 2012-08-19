package formulagrid

import spock.lang.Specification

class TrackMapSpec extends Specification {

    TrackMap trackMap = new TrackMap()

    def "getTrackPoint"() {
        setup:
        TrackPoint[][] bitmap = createBitmapTest(3, 3)
        trackMap.bitmap = bitmap

        expect:
        trackMap.getTrackPoint(new Point(x: (int) x, y: (int) y)) == bitmap[x][y]

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
        trackMap.bitmap = createBitmapTest(3, 3)

        when:
        trackMap.getTrackPoint(new Point(x: 10, y: 10))

        then:
        thrown(ArrayIndexOutOfBoundsException)
    }

    TrackPoint[][] createBitmapTest(int width, int height) {
        TrackPoint[][] bitmap = new TrackPoint[width][height]
        width.times { int x ->
            height.times { int y ->
                bitmap[x][y] = new TrackPoint()
            }
        }

        return bitmap
    }
}
