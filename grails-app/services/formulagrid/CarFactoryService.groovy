package formulagrid

class CarFactoryService {

    public final static String NAME_DEMO = "DEMO"

    Car getDemoCar() {

        Car demoCar = Car.findByName(NAME_DEMO)

        if(!demoCar) {
            demoCar = new Car(name: NAME_DEMO)
            demoCar.save(failOnError: true)
            return demoCar
        }

        return demoCar
    }
}
