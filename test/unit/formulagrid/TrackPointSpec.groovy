package formulagrid

import spock.lang.Specification

class TrackPointSpec extends Specification {

    def "parseFromCharacter - valid "() {
        expect:
        TrackPoint.parseFromCharacter(c).character == c

        where:
        c << ['.', '*', '1', '2', '3', '4', '5', '6', '='].collect { it as char }
    }

    def "parseFromCharacter - invalid "() {
        when:
        TrackPoint.parseFromCharacter(invalidCharacter)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "'$invalidCharacter' is not a valid character."

        where:
        invalidCharacter = "&" as char
    }

    def "isStartPosition"(TrackPoint trackPoint) {
        expect:
        trackPoint.isStartPosition() ==
                (trackPoint in [
                        TrackPoint.START_POSITION_1,
                        TrackPoint.START_POSITION_2,
                        TrackPoint.START_POSITION_3,
                        TrackPoint.START_POSITION_4,
                        TrackPoint.START_POSITION_5,
                        TrackPoint.START_POSITION_6,
                ])

        where:
        trackPoint << TrackPoint.values()
    }

    def "getStartPositionNum - valid"(int num) {
        expect:
        TrackPoint."START_POSITION_$num".startPositionNum == num

        where:
        num << [1, 2, 3, 4, 5, 6]
    }

    def "getStartPositionNum - invalid"(TrackPoint notStartPosition) {
        when:
        notStartPosition.startPositionNum

        then:
        def e = thrown(IllegalStateException)
        e.message == "$notStartPosition is not a start position"

        where:
        notStartPosition << [
                TrackPoint.IN,
                TrackPoint.OUT,
                TrackPoint.FINISH_LINE
        ]
    }
}
