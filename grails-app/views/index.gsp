<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="formulagrid"/>
    <title>Formula Grid</title>
    <r:require modules="bootstrapDocs"/>
</head>

<body>
<!-- Masthead
================================================== -->
<header class="jumbotron subhead" id="overview">
    <h1>Formula Grid</h1>

    <p class="lead">La version numérique de la course automobile sur une feuille de papier</p>
</header>

<div class="container">
    <section>
        <div class="row features">

            <div class="span4">
                <center>
                    <g:img dir="images/FormulaGrid/icons" file="car.png"/>

                    <h3>Le jeu</h3>

                    <p>
                        Formula Grid est un jeu de course automobile minimaliste.

                    </p>
                    <g:link controller="race">Accéder au jeu</g:link>
                </center>
            </div>

            <div class="span4">
                <center>
                    <g:img dir="images/FormulaGrid/icons" file="sourcecode.png"/>
                    <h3>Le projet open-source</h3>

                    <p>Le code source de Formula Grid est disponible sur GitHub.</p>
                    <a href="https://github.com/jtranier/FormulaGrid" target="_blank">Accéder au code source</a>
                </center>
            </div>

            <div class="span4">
                <center>
                    <g:img dir="images/FormulaGrid/icons" file="lab.png"/>
                    <h3>Mon laboratoire personnel</h3>

                    <p>Formula Grid est avant tout un support qui me permet de tester et mettre en oeuvre différentes technologies qui ont retenues mon attention.</p>
                </center>
            </div>

        </div>

</body>
</html>