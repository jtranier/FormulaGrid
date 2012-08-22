package formulagrid

class TrackFileParser {

    static Track parseTrack(File file) {
        file.withReader { Reader reader ->
            parseTrack(reader)
        }
    }

    static Track parseTrack(Reader reader) {
        String[] lines = reader.readLines()

        // Extract height
        int height = lines.length
        if (height < 3) {
            throw new IllegalStateException("Minimal height is 3 (current: $height).")
        }

        // Extract width
        int width = lines[0].size()
        if (width < 3) {
            throw new IllegalStateException("Minimal width is 3 (current: $width).")
        }
        lines.eachWithIndex { String line, int index ->
            if(line.size() != width) {
                throw new IllegalStateException(
                        "Length of line $index is ${line.size()} whereas track's width is $width."
                )
            }
        }

        // Prepare TrackStartGrid
        TrackStartGrid startGrid = new TrackStartGrid()

        // Extract bitmap
        TrackPoint[][] bitmap = new TrackPoint[width][height]
        lines.eachWithIndex { String line, int y ->
            line.eachWithIndex { String character, int x ->
                TrackPoint point = TrackPoint.parseFromCharacter(character as char)
                bitmap[x][y] = point

                if(point.isStartPosition()) {
                    startGrid.addStartPosition(point.startPositionNum, x, y)
                }
            }
        }

        startGrid.validate()

        return new Track(
                map: new TrackMap(bitmap),
                startGrid: startGrid
        )
    }

}
