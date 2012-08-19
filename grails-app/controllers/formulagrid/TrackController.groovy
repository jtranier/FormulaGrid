package formulagrid

class TrackController {

    def index() {
        return [track: TrackFactory.buildDemoTrack()]
    }
}
