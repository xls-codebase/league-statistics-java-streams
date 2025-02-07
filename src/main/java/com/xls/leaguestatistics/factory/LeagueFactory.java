package com.xls.leaguestatistics.factory;

import com.xls.leaguestatistics.Utils;
import com.xls.leaguestatistics.model.Division;
import com.xls.leaguestatistics.model.Player;
import com.xls.leaguestatistics.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Provides full set of teams with players
 */
public class LeagueFactory {

    /**
     * For each division, creates given amount of teams. Each team gets a newly created collection of players.
     * The amount of players should be taken from Utils.TEAM_SIZE
     * @param teamsInDivision Indicates number of teams are in division
     * @return Full set of teams with players
     */
    public static List<Team> createLeague(int teamsInDivision) {
        List<Team> teams = new ArrayList<>();
        Stream.of(Division.values()).forEach(division -> {
            Stream.generate(Team::new).limit(teamsInDivision).forEach(team -> {
                team.setDivision(division);
                team.setPlayers(getPlayers(Utils.TEAM_SIZE));
                teams.add(team);
            });
        });
        return teams;
    }

    /**
     * Returns a collection with a given amount of newly created players
     */
    private static List<Player> getPlayers(int amount) {
        List<Player> players = Stream.generate(Player::new)
                .limit(amount)
                .toList();
        players.forEach(player -> player.setSkillRate(getPlayerSkillRate()));
        return players;

    }

    private static int getPlayerSkillRate() {
        return Utils.getRandomValue(5, 21);
    }
}
