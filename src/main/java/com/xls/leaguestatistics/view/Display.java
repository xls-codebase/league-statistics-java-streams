package com.xls.leaguestatistics.view;

import com.xls.leaguestatistics.model.LeagueStatistics;
import com.xls.leaguestatistics.model.Player;
import com.xls.leaguestatistics.model.Team;
import com.xls.leaguestatistics.view.table.Table;

import java.util.List;

/**
 * Provides console view
 */
public class Display {

    public static void displayTeamStatistics(List<Team> teams) {
        List<String>columnIdentifiers = List.of("Name", "Points", "Goals", "Wins", "Draws", "Losses");
        List<Team> sortedTeams = LeagueStatistics.getAllTeamsSorted(teams);
        List<List<String>> tableData = sortedTeams.stream().map(team -> List.of(team.getName(),
                "" + team.getCurrentPoints(),
                "" + team.getPlayers().stream().mapToInt(Player::getGoals).sum(),
                "" + team.getWins(),
                "" + team.getDraws(),
                "" + team.getLoses())).toList();

        Table table = new Table(tableData, columnIdentifiers);
        table.render();
    }

    public static void displaySingleMatchResult(Team team1, int scoredGoalsTeam1, Team team2, int scoredGoalsTeam2) {
        List<List<String>> tableData = List.of(List.of(team1.getName(), "" + scoredGoalsTeam1, "" + scoredGoalsTeam2, team2.getName()));
        Table table = new Table();
        table.setData(tableData);
        table.render();
    }
}
