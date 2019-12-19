package me.abir.dailycricketbd.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.model.results_module.Item;
import me.abir.dailycricketbd.model.results_module.ResultsGroupChildModel;
import me.abir.dailycricketbd.model.results_module.ResultsModel;
import me.abir.dailycricketbd.model.results_module.ResultsModelWrapper;
import retrofit2.Response;

public class CricResponseParser {

    private static final String TAG = "CricResponseParser";


    public static String getInningsName(int val) {
        switch (val) {
            case 0:
                return "1st";
            case 1:
                return "2nd";
            case 2:
                return "3rd";
            case 3:
                return "4th";
            default:
                return "";
        }
    }

    public static List<ResultsGroupChildModel> parseScoreData(Response<ResultsModel> response, boolean withoutTimeZone) {
        try {
            ResultsModel model = response.body();
            me.abir.dailycricketbd.model.results_module.Response modelResponse
                    = model.getResponse();
            List<Item> items = modelResponse.getItems();

            List<ResultsGroupChildModel> models = new ArrayList<>();
            List<ResultsModelWrapper> domList = new ArrayList<>();
            List<ResultsModelWrapper> intList = new ArrayList<>();

            boolean firstInternational = true, firstDomestic = true;

            for (Item item : items) {
                String date;
                if (withoutTimeZone) {
                    date = CricUtil.changeDateFormat("yyyy-MM-dd hh:mm:ss",
                            "E, dd MMM yyy", item.getDateStart());
                } else {
                    date = CricUtil.changeDateFormat("yyyy-MM-dd hh:mm:ss",
                            "E dd MMM, hh:mm z", item.getDateStart());
                }


                String tournament = item.getSubtitle() + ", " + item.getCompetition().getTitle();
                String matchTimeInUserTimeZone = CricUtil.getTimeFromLong(item.getTimestampStart());

                ResultsModelWrapper wrapper = new ResultsModelWrapper();
                wrapper.setMatchId(String.valueOf(item.getMatchId()));
                wrapper.setTournamentName(tournament);
                wrapper.setDateAndMatchNo(date);
                //wrapper.setTeamA(getTeamName(item.getTeama().getName()));
                wrapper.setTeamA(item.getTeama().getName());
                wrapper.setTeamAScore(item.getTeama().getScores());
                wrapper.setTeamAScoreFull(item.getTeama().getScoresFull());
                wrapper.setTeamAUrl(item.getTeama().getLogoUrl());
                //wrapper.setTeamB(getTeamName(item.getTeamb().getName()));
                wrapper.setTeamB(item.getTeamb().getName());
                wrapper.setTeamBScore(item.getTeamb().getScores());
                wrapper.setTeamBScoreFull(item.getTeamb().getScoresFull());
                wrapper.setTeamBUrl(item.getTeamb().getLogoUrl());
                wrapper.setMatchStatus(item.getStatusStr());
                wrapper.setDomestic(Integer.parseInt(item.getDomestic()));
                wrapper.setMatchNotes(item.getStatusNote());
                wrapper.setMatchStartTime(matchTimeInUserTimeZone);

                // Setting section flag for domestic
                if (firstDomestic && wrapper.getDomestic() == 1) {
                    firstDomestic = false;
                    wrapper.setSection(true);
                }

                // Setting section flag for international
                if (firstInternational && wrapper.getDomestic() != 1) {
                    firstInternational = false;
                    wrapper.setSection(true);
                }

                if (wrapper.getDomestic() == 1) {
                    domList.add(wrapper);
                } else {
                    intList.add(wrapper);
                }
            }

            if (domList.size() > 0) {
                ResultsGroupChildModel mod =
                        new ResultsGroupChildModel(Configuration.TAG_DOMESTIC, domList);
                models.add(mod);
            }

            if (intList.size() > 0) {
                ResultsGroupChildModel mod =
                        new ResultsGroupChildModel(Configuration.TAG_INTERNATIONAL, intList);
                models.add(mod);
            }

            return models;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getTeamName(String givenTeamName) {
        try {
            String[] wordArray = givenTeamName.trim().split("\\s+");
            Log.e(TAG, "getTeamName: wordCount " + wordArray.length);
            if (wordArray.length < 3) {
                return givenTeamName;
            } else {
                StringBuilder part1 = new StringBuilder();
                StringBuilder part2 = new StringBuilder();
                for (int i = 0; i < wordArray.length; i++) {
                    if (i < 2) {
                        part1.append(wordArray[i]);
                        part1.append(" ");
                    } else {
                        part2.append(wordArray[i]);
                        part2.append(" ");
                    }
                }
                return part1 + "\n" + part2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
