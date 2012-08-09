package formulagrid

public enum TrackPoint {
    IN("." as char),
    OUT("*" as char),
    START_POSITION_1("1" as char),
    START_POSITION_2("2" as char),
    START_POSITION_3("3" as char),
    START_POSITION_4("4" as char),
    START_POSITION_5("5" as char),
    START_POSITION_6("6" as char),
    FINISH_LINE("=" as char)

    private char character

    private TrackPoint(char character) {
        this.character = character
    }

    static TrackPoint parseFromCharacter(char c) {
        TrackPoint trackPoint = TrackPoint.values().find { TrackPoint point ->
            if (point.character == c) {
                return point
            }
        }

        if (trackPoint) {
            return trackPoint
        }
        else {
            throw new IllegalArgumentException("'$c' is not a valid character.")
        }
    }

    String getCharacter() {
        return this.character
    }

    boolean isStartPosition() {
        return this in [
                START_POSITION_1,
                START_POSITION_2,
                START_POSITION_3,
                START_POSITION_4,
                START_POSITION_5,
                START_POSITION_6
        ]
    }

    int getStartPositionNum() {
        switch(this) {
            case START_POSITION_1: return 1
            case START_POSITION_2: return 2
            case START_POSITION_3: return 3
            case START_POSITION_4: return 4
            case START_POSITION_5: return 5
            case START_POSITION_6: return 6
            default: throw new IllegalStateException(
                    "$this is not a start position"
            )
        }
    }
}