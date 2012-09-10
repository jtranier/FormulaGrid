import grails.converters.JSON
import formulagrid.Race
import formulagrid.Point

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(Point, Point.MARSHALLER)
        JSON.registerObjectMarshaller(Race, Race.MARSHALLER)
    }
    def destroy = {
    }
}
