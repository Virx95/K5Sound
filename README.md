# K5Sound
Rakenduse käivitamiseks on vajalik gradle (https://gradle.org/) ja mongodb v3.4.3. Mongodb conf on failis `application.properties`.
Real `spring.data.mongodb.uri=mongodb://<username>:<passowrd>@<host>:<port>/<database>` tuleb määrata oma kohaliku andmebaasi väärtused.

Rakenduse esmakordsel avamisel on vaja käivitada käsk `gradle build` seejärel saab rakendust käivitada käsuga `gradle bootrun`.
Rakendust käivitades peab protsess jääma seisma 80% peale ja viimane rida konsoolis peaks olema `Started HeliApplication in 7.596 seconds (JVM running for 8.238)`.

NB! Rakendus käivitub isegi siis kui andmebaasiga ühendust ei saa. Sel juhul peaks konsoolis olema näha mingi exception.
