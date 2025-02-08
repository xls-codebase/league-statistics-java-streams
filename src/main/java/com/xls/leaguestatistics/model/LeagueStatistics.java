package com.xls.leaguestatistics.model;

import java.util.Comparator;
import java.util.List;

/**
 * Provides all necessary statistics of played season.
 */
public class LeagueStatistics {

    /**
     * Gets all teams with highest points order, if points are equal next deciding parameter is sum of goals of the team.
     */
    public static List<Team> getAllTeamsSorted(List<Team> teams) {
    return teams.stream().sorted(Comparator.comparingInt(Team::getCurrentPoints)
                    .thenComparingInt(team -> team.getPlayers().stream().mapToInt(Player::getGoals).sum()).reversed())
                .toList();
    }

    /**
     * Gets all players from each team in one collection.
     */
    public static List<Player> getAllPlayers(List<Team> teams) {
        return teams.stream().map(Team::getPlayers).flatMap(List::stream).toList();
    }

    /**
     * Gets team with the longest name
     */
    public static Team getTeamWithTheLongestName(List<Team> teams) {
        return teams.stream().max(Comparator.comparingInt(team -> team.getName().length())).get();
    }

    /**
     * Gets top teams with least number of lost matches.
     * If the amount of lost matches is equal, next deciding parameter is team's current points value.
     * @param teamsNumber The number of Teams to select.
     * @return Collection of selected Teams.
     */
    public static List<Team> getTopTeamsWithLeastLoses(List<Team> teams, int teamsNumber) {
        return teams.stream().sorted(Comparator.comparingInt(Team::getLoses)
                .thenComparing(Comparator.comparingInt(Team::getCurrentPoints).reversed()))
                .limit(teamsNumber).toList();
    }

    /**
     * Gets a player with the biggest goals number from each team.
     */
    public static List<Player> getTopPlayersFromEachTeam(List<Team> teams) {
        return teams.stream().map(team -> team.getPlayers().stream()
                .max(Comparator.comparingInt(Player::getGoals)).get()).toList();
    }

    /**
     * Gets all teams, where there are players with no scored goals.
     */
    public static List<Team> getTeamsWithPlayersWithoutGoals(List<Team> teams){
        return teams.stream().filter(team -> team.getPlayers().stream()
                .anyMatch(player -> player.getGoals() == (0))).toList();
    }

    /**
     * Gets players with given or higher number of goals scored.
     * @param goals The minimal number of goals scored.
     * @return Collection of Players with given or higher number of goals scored.
     */
    public static List<Player> getPlayersWithAtLeastXGoals(List<Team> teams, int goals) {
        return teams.stream().map(Team::getPlayers)
                .flatMap(List::stream).filter(player -> player.getGoals() >= goals).toList();
    }

    /**
     * Gets the player with the highest skill rate for given Division.
     */
    public static Player getMostTalentedPlayerInDivision(List<Team> teams, Division division) {
        throw new RuntimeException("getMostTalentedPlayerInDivision method not implemented");
    }

    /**
     * OPTIONAL
     * Returns the division with greatest amount of points.
     * If there is more than one division with the same amount current points, then check the amounts of wins.
     */
    public static Division getStrongestDivision(List<Team> teams) {
        throw new RuntimeException("getStrongestDivision method not implemented");
    }
}
