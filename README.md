# league-statistics-java-streams [^1]
## Football League Statistics
**What are you going to learn?**
- Use Java 8 Stream API.
- Understand method references.
- Use lambda expressions.
- Understand the practical usage of the Model-View-Controller design pattern.

## Tasks

### Season
Implement the 'Season' class, where all matches are be played on a peer-to-peer basis using the 'League' team collection.

1. The @playMatch() method provides the result of a match played between two teams. It increments the Wins, Losts, or Draws value of the teams.
2. The @scoredGoals() method returns the number of goals scored by a team in one match. The method contains the logic of the scoring chance of each player. The method increments the Goals stats of players.
3. The @playAllGames() method contains the logic of all matches played in one round. Every team has to play a match against every team exactly once.

### League Statistics
Implement the methods in League Statistics class using Streams.

1. All methods in League Statistics class are public static methods.
2. No loops (while, do-while, for, foreach) are used in League Statistics class.
3. All methods in League Statistics class are single-line return methods, and consist of just streams, lambda expressions, and method references.

### Display
Implement the Display class with all necessary methods for creating a console view for the league statistics.


1. Methods are implemented for displaying the following.
   - A table with name, points, goals, wins, draws, losses columns.
   - Single match results.

[^1]: This exercise is an excerpt from my Full Stack Development course.