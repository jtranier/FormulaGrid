<div id="modalRules" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

        <h3 id="myModalLabel">Règles du jeu</h3>
    </div>

    %{-- TODO: Définir une classe css --}%
    <div class="modal-body" style="max-height: 350px; overflow: auto;">
        <h4>Le principe du jeu</h4>

        <p>
            Le consiste en une course automobile qui se déroule tour par tour, sur un circuit quadrillé.<br/>
            Chaque joueur dispose d'une voiture représentée par un rond de couleur.<br/>
            L'objectif est simple : terminer le tour avant ses adversaires.<br/>
            La ligne d'arrivée n'est pas encore représentée ... Mais ça ne saurait tarder ;-)
        </p>

        <hr>

        <h4>Les déplacements</h4>

        <p>
            A tour de rôle, chaque joueur va pouvoir effectuer un déplacement.<br/>
            Le déplacement qu'effectue une auto dépend de sa vitesse, qui est initialement nulle.<br/>
            Avant que le déplacement ne soit effectué, le joueur peut modifier la vitesse de son auto en accélérant (ou en décélérant).<br/>
            L'accélération est réalisée en cliquant sur l'un des boutons du pavé numériques ; chacun représentant la direction de l'accélération.<br/>
            Le bouton "8" correspond à une accélération d'une case vers le haut, le bouton "9" à une accélération d'une case en haut et une case à droite.
            Le bouton "5" correspond à une accélération nulle (la vitesse reste donc constante).
        </p>

        <hr/>

        <h4>Les collisions et les sorties de routes</h4>
        <p>
            Les joueurs doivent éviter d'entre en collision (i.e. se placer sur une position déjà occupée par une autre auto).<br/>
            Ils doivent aussi éviter de sortir du circuit.
            Lorsque la collision ou la sortie de route est inévitable, l'auto est stoppée sur la dernière position libre sur son trajet, et sa vitesse redevient nulle.
        </p>
    </div>

    <div class="modal-footer">
        <button class="btn" data-dismiss="modal">Close</button>
    </div>
</div>