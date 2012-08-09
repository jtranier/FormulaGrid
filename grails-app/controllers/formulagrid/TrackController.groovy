package formulagrid

class TrackController {

    def index() {
        // Test TrackFileParser
        URL trackFileUrl = this.class.getResource('/formulagrid/resources/demo.trk')
        File trackFile = new File(trackFileUrl.file)
        Track track = TrackFileParser.parseTrack(trackFile)

        return [track: track]
    }
}
