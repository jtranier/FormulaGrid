<code>
    <g:each in="${0..track.height - 1}" var="y">
        <g:each in="${0..track.width - 1}" var="x">
            ${track.map.bitmap[x][y].asciRepresentation}
        </g:each>
        <br/>
    </g:each>
</code>