package me.abir.dailycricketbd.interfaces;

import me.abir.dailycricketbd.model.poll_v2.PollQuestionModel;

/**
 * Created by mayes on 11/8/2017.
 */

public interface OnPollResponseListener {
    void onPollResponse(PollQuestionModel pollModel);

    void onVoteResponse(String voteResponse);
}
