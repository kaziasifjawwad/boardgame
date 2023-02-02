package com.hishab.boardgame.mapper;
import com.hishab.boardgame.domain.CurrentStateResponse;
import com.hishab.boardgame.domain.PlayerScore;
import com.hishab.boardgame.domain.UserProfile;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class ScoreMapper {
    public List<CurrentStateResponse> map(Map<UserProfile, PlayerScore> mapOfPlayerToScore){
        return mapOfPlayerToScore
                .keySet()
                .stream().map(
                        key-> new CurrentStateResponse()
                                .setScore(mapOfPlayerToScore.get(key).getScore())
                                .setName(key.getName())
                )
                .collect(Collectors.toList());
    }
}
