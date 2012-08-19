package formulagrid

class RaceSessionService {

    static scope = "session"
    private Race sessionRace

    Race getRace() {
        if(!sessionRace) {
            sessionRace = createDemoRace()
        }

        return sessionRace
    }

    private Race createDemoRace() {
        Race race = new Race(TrackFactory.buildDemoTrack())
        race.addPlayer("John")
        race.addPlayer("Loris")
        race.start()
        return race
    }
}
