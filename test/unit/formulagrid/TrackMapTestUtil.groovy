package formulagrid

class TrackMapTestUtil {

    public static TrackPoint[][] createBitmapTest(int width, int height) {
        TrackPoint[][] bitmap = new TrackPoint[width][height]
        width.times { int x ->
            height.times { int y ->
                bitmap[x][y] = new TrackPoint()
            }
        }

        return bitmap
    }
}
