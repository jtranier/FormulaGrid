import grails.converters.JSON
import formulagrid.Race
import formulagrid.Point
import formulagrid.Car

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(Point, Point.MARSHALLER)
        JSON.registerObjectMarshaller(Car, Car.MARSHALLER)
        JSON.registerObjectMarshaller(Race, Race.MARSHALLER)
    }
    def destroy = {
    }
}
