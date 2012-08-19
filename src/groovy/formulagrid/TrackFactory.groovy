package formulagrid

class TrackFactory {

    static Track buildDemoTrack() {
        // Test TrackFileParser
        URL trackFileUrl = TrackFactory.getResource('/formulagrid/resources/demo.trk')
        File trackFile = new File(trackFileUrl.file)
        Track track = TrackFileParser.parseTrack(trackFile)
        return track
    }
}
