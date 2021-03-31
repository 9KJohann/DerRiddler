# Der Riddler

Der Riddler ist ein Konsolen basiertes Escape Room Spiel geschrieben in Java und ist die Projekt Arbeit der Gruppe 8 der Informatik Studenten der Hochschule Coburg.
Das Spiel ist gemeinsam durch Arjanit Gashi, Jannek Jeske, Johann Dietrich, Lukas Goller und Sophia Haberland entstanden.


## Einleitung

Im Gegensatz zu einem gewöhnlichen Escape Room findet sich der Spieler in einer gewohnten Situation – zuhause am PC – um dann herauszufinden, dass dieser von einem Hackerangriff betroffen ist. Um die Adminrechte für seinen Computer zurückzugewinnen, muss der Spieler 5 Rätsel lösen.

Alle Rätsel des Escape Rooms haben die Form von Java-Programmen, wobei man jeweils eine bestimmte Variable so eingeben muss, dass am Ende „Richtig“ zurückgegeben wird. Die einzelnen Antworten, welche Zahlen oder Buchstaben sein können, müssen dann zu einem Passwort zusammengesetzt werden, um „zu entkommen“. Die Zahlen sind hierbei Ascii-Codes, welche in die dementsprechenden Buchstaben umgewandelt werden müssen.

Passend zur Situation ist die Ein- und Ausgabe in der Konsole der IDE der Ästhetik einer Linux-Konsole nachempfunden. Man navigiert den Escape Room also mit Konsolenbefehlen.

### Konsolenbefehle

Der ls-Befehl zeigt das Verzeichnis mit allen Dateien an. (Rätsel, Ascii-Tabelle).
```
ls
```

Der Befehl „cat“ ermöglicht es dem Spieler, den Inhalt einer Datei anzuzeigen.
```
cat <Dateiname>
// z.B.
cat Riddle2.java
```

Der Befehl "javac" kompiliert die jeweilige Java-Datei. Dies ist notwendig damit Sie mit dem Befehl "java" ausgeführt werden kann.
```
javac <JavaDatei>
// z.B.
javac Riddle1.java
```

Mit dem "java" Befehl kann man eine ".class" Datei ausführen. Dies ist notwendig um die Lösung eines Rätsels zu lösen oder die beigelgte Ascii Tabelle auszuführen.
```
java <mainclass> [args...]
// z.B.
java Riddle3 meineLösung
```

"help" listet alle Befehle mit Beschreibung auf.
```
help
```
Mit dem Befehl "exit" kann man das Spiel beenden.
```
exit
```

Mit dem Befehl "su" kann man sich als Admin anmelden. Dies ist notwendig um den Befehl "zoom" erfolgreich auszuführen und das Spiel zu gewinnen.
```
su
```

Wenn man sich erfolgreich als Admin angemeldet hat, gewinnt man durch eingabe des Befehls "zoom".
```
zoom
```

## Rätsel erweitern

Um das Spiel um ein neues Rätsel zu erweitern, muss die Datei **DerRiddler.java** bearbeitet werden.

```java
// DerRiddler.java
...
@Override
public List<Riddle> makeRiddles(Game game) {
    return List.of(
            new Riddle1(game),
            new Riddle2(game),
            new Riddle3(game),
            new Riddle4(game),
            new Riddle5(game)
    );
}
...
```

Hier kann nun ein Riddle Objekt oder eins das von der Klasse Riddle geerbt hat eingefügt werden.
```java
// DerRiddler.java
return List.of(
        new Riddle1(game),
        new Riddle2(game),
        new Riddle3(game),
        new Riddle4(game),
        new Riddle5(game),
        new Riddle("Riddle6", "public class Riddle6{....}") {
            @Override
            public void execute(String[] args) {
                //Der Code der ausgeführt werden soll, wenn im Spiel dieses Rätsel aufgerufen wird
            }
        }
);
```
Der erste Parameter ist der Name des Rätsels und wird im Spiel mit ".java" ergänzt.

Der zweite Parameter ist der Source Code. Dieser muss nicht zwingend gültiges Java sein, da es aber im Spiel durch den Befehl "cat" ausgegeben wird, sollte er korrekt sein.

In der Methode execute kommt dann der Code rein, der ausgeführt werden soll, wenn das Rätsel mit "java Riddle meinLösung" aufgerufen wird.