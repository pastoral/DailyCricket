package me.abir.dailycricketbd.interfaces;

import java.util.List;

import me.abir.dailycricketbd.model.single_live_match.Commentary;

public interface CommentaryResponseListener {
    void onCommentaryResponse(List<Commentary> commentaries);
}
