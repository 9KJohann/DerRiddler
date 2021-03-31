# Der Riddler

Der Riddler ist ein Konsolen basiertes Escape Room Spiel geschrieben in Java und ist die Projekt Arbeit der Gruppe 8 der Informatik Studenten der Hochschule Coburg.
Das Spiel ist gemeinsam durch Arjanit Gashi, Jannek Jeske, Johann Dietrich, Lukas Goller und Sophia Haberland entstanden.


## Rätsel erweitern

Um das Spiel um ein neues Rätsel zu erweitern, muss die Datei **DerRiddle.java** bearbeitet werden

```java:
// DerRiddle.java
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

