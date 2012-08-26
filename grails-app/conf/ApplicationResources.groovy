modules = {
    application {
        resource url:'js/application.js'
    }

    d3 {
        resource url:'js/d3.v2.js', disposition: 'head'
    }

    bootstrapDocs {
        resource url: 'css/bootstrap-docs.css'
        dependsOn "bootstrap"
    }

    race {
        resource url: 'js/FormulaGrid/race.js', disposition: 'head'
        dependsOn 'd3'
    }
}