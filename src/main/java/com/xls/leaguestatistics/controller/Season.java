package com.xls.leaguestatistics.controller;

import com.xls.leaguestatistics.Utils;
import com.xls.leaguestatistics.factory.LeagueFactory;
import com.xls.leaguestatistics.model.Player;
import com.xls.leaguestatistics.model.Team;
import com.xls.leaguestatistics.view.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Provides all necessary methods for season simulation
 */
public class Season {

    private List<Team> league;

    public Season() {
        league = new ArrayList<>();
    }

    /**
     * Fills league with new teams and simulates all games in season.
     * After all games played calls table to be displayed.
     */
    public void run() {
        this.league = LeagueFactory.createLeague(6);
        playAllGames();
        // Call Display methods below
        Display.displayTeamStatistics(league);

    }

    /**
     * Playing whole round. Everyone with everyone one time. Number of teams in league should be even.
     * Following solution represents the robin-round tournament.
     */
    private void playAllGames() {
        if (league.size() % 2 != 0) {
            throw new RuntimeException("Number of teams in league must be even");
        }
        IntStream.range(0, league.size()).forEach(i -> {
            IntStream.range(i + 1, league.size()).forEach(j -> {
                playMatch(league.get(i), league.get(j));
            });
        });
    }

    /**
     * Plays single game between two teams and displays result after.
     */
    private void playMatch(Team team1, Team team2) {
        int scoredGoalsTeam1 = getScoredGoals(team1);
        int scoredGoalsTeam2 = getScoredGoals(team2);

        if (scoredGoalsTeam1 > scoredGoalsTeam2) {
            team1.setWins(team1.getWins() + 1);
            team2.setLoses(team2.getLoses() + 1);
        } else if (scoredGoalsTeam1 < scoredGoalsTeam2) {
            team2.setWins(team2.getWins() + 1);
            team1.setLoses(team1.getLoses() + 1);
        } else {
            team1.setDraws(team1.getDraws() + 1);
            team2.setDraws(team2.getDraws() + 1);
        }
        Display.displaySingleMatchResult(team1, scoredGoalsTeam1, team2, scoredGoalsTeam2);
    }

    /**
     * Checks for each player of given team chance to score based on skillrate.
     * Adds scored goals to player's and team's statistics.
     * @return All goals scored by the team in current game
     */
    private int getScoredGoals(Team team) {
        List<Player> playersWhoScoredAGoalBasedOnSkillRate = team.getPlayers().stream()
                .filter(player -> Utils.getRandomValue(0, (int) Math.ceil(player.getSkillRate() * 0.1)) > 0)
                .toList();

        playersWhoScoredAGoalBasedOnSkillRate.forEach(player -> player.setGoals(player.getGoals() + 1));

        return playersWhoScoredAGoalBasedOnSkillRate.size();

    }
}
