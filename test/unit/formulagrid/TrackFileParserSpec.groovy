package formulagrid

import spock.lang.Specification
import java.awt.Point

class TrackFileParserSpec extends Specification {

    def "parseTrack - wrong minimal height"() {
        when:
        TrackFileParser.parseTrack(new StringReader(content))

        then:
        def e = thrown(IllegalStateException)
        (e.message == "Minimal height is 3 (current: $height).") ||
                (e.message == "Minimal width is 3 (current: $height).")

        where:
        height | content
        0      | ""
        2      | "***\n***"
    }

    def "parseTrack - wrong minimal width"() {
        when:
        TrackFileParser.parseTrack(new StringReader(content))

        then:
        def e = thrown(IllegalStateException)
        (e.message == "Minimal height is 3 (current: $width).") ||
                (e.message == "Minimal width is 3 (current: $width).")

        where:
        width | content
        0     | ""
        2     | "**\n**\n**"
    }

    def "parseTrack - wrong line width"() {
        when:
        TrackFileParser.parseTrack(new StringReader(content))

        then:
        def e = thrown(IllegalStateException)
        e.message == "Length of line 1 is 4 whereas track's width is 3."

        where:
        content = "***\n****\n***"
    }

    def "parseTrack - invalid startGrid"() {
        when:
        TrackFileParser.parseTrack(new StringReader(content))

        then:
        def e = thrown(IllegalStateException)
        e.message == "Start position 1 is undefined."

        where:
        content = "***\n***\n***" // no start grid
    }

    def "parseTrack - valid"() {
        when:
        Track track = TrackFileParser.parseTrack(new StringReader(content))

        then:
        track.width == 4
        track.height == 5
        track.startGrid.getStartPositionPoint(1) == new Point(1,2)
        track.startGrid.getStartPositionPoint(2) == new Point(2,2)
        track.bitmap[0][0] == TrackPoint.OUT
        track.bitmap[1][0] == TrackPoint.IN
        track.bitmap[2][0] == TrackPoint.IN
        track.bitmap[3][0] == TrackPoint.OUT
        track.bitmap[0][1] == TrackPoint.FINISH_LINE
        track.bitmap[1][2] == TrackPoint.START_POSITION_1
        track.bitmap[2][2] == TrackPoint.START_POSITION_2

        where:
        content ="*..*\n====\n.12.\n*..*\n****"
    }
}
