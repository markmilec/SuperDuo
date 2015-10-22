package barqsoft.footballscores;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by yehya khaled on 3/3/2015.
 */
// FIX: SPELLING
public class Utilities {
    // FIX: UPDATED LEAGUE IDS FOR 2015/16 SEASON
    public static final int BUNDESLIGA = 394;
    public static final int PRIMERA_DIVISION = 399;
    public static final int PREMIER_LEAGUE = 398;
    public static final int CHAMPIONS_LEAGUE = 405;
    public static final int SERIE_A = 401;


    public static String getLeague(Context context, int league_num) {
        switch (league_num) {
            // FIX: SPELLING
            case SERIE_A:
                return context.getString(R.string.serie_a);
            case PREMIER_LEAGUE:
                return context.getString(R.string.premier_league);
            case CHAMPIONS_LEAGUE:
                return context.getString(R.string.champions_league);
            case PRIMERA_DIVISION:
                return context.getString(R.string.primera_divison);
            case BUNDESLIGA:
                return context.getString(R.string.bundesliga);
            default:
                return context.getString(R.string.unknown_league);
        }
    }

    public static String getMatchDay(Context context, int match_day, int league_num) {
        if (league_num == CHAMPIONS_LEAGUE) {
            if (match_day <= 6) {
                return context.getString(R.string.group_stage_text) + ", "
                        + context.getString(R.string.matchday_text) + "6" ;
            } else if (match_day == 7 || match_day == 8) {
                return context.getString(R.string.first_knockout_round_text);
            } else if (match_day == 9 || match_day == 10) {
                return context.getString(R.string.quarter_final_text);
            } else if (match_day == 11 || match_day == 12) {
                return context.getString(R.string.semi_final_text);
            } else {
                return context.getString(R.string.final_text);
            }
        } else {
            return context.getString(R.string.matchday_text) + String.valueOf(match_day);
        }
    }

    // FIX: VARIABLE NAME CONSISTENCY
    public static String getScores(int home_goals, int away_goals) {
        if (home_goals < 0 || away_goals < 0) {
            return " - ";
        } else {
            return String.valueOf(home_goals) + " - " + String.valueOf(away_goals);
        }
    }

    public static int getTeamCrestByTeamName(Context context, String team_name) {
        if (team_name == null) {
            return R.drawable.no_icon;
        } else if (team_name.equals(context.getString(R.string.Team_Arsenal))) {
            return R.drawable.arsenal;
        } else if (team_name.equals(context.getString(R.string.Team_Manchester))) {
            return R.drawable.manchester_united;
        } else if (team_name.equals(context.getString(R.string.Team_Swansea))) {
            return R.drawable.swansea_city_afc;
        } else if (team_name.equals(context.getString(R.string.Team_Leicester))) {
            return R.drawable.leicester_city_fc_hd_logo;
        } else if (team_name.equals(context.getString(R.string.Team_Everton))) {
            return R.drawable.everton_fc_logo1;
        } else if (team_name.equals(context.getString(R.string.Team_West_Ham))) {
            return R.drawable.west_ham;
        } else if (team_name.equals(context.getString(R.string.Team_Tottenham))) {
            return R.drawable.tottenham_hotspur;
        } else if (team_name.equals(context.getString(R.string.Team_West_Bromwich))) {
            return R.drawable.west_bromwich_albion_hd_logo;
        } else if (team_name.equals(context.getString(R.string.Team_Sunderland))) {
            return R.drawable.sunderland;
        } else if (team_name.equals(context.getString(R.string.Team_Stoke))) {
            return R.drawable.stoke_city;
        } else {
            return R.drawable.no_icon;
        }
    }
}