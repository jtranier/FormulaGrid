package formulagrid

import spock.lang.Specification

class TrackFactorySpec extends Specification {

    def "buildDemoTrack"() {
        when:
        Track track = TrackFactory.buildDemoTrack()

        then:
        track
        track.width > 3
        track.height > 3
        track.map
        track.startGrid
    }
}
