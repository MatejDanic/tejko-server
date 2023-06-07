package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.tejko.interfaces.mappers.ScoreMapperInterface;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.responses.ScoreResponse;

public class ScoreMapper implements ScoreMapperInterface {

    @Override
    public ScoreResponse toApiResponse(Score score) {
        return new ScoreResponse(
            score.getCreatedDate(), 
            score.getLastModifiedDate(), 
            score.getId(), 
            score.getApp().getId(),
            score.getValue()
        );
    }

    @Override
    public List<ScoreResponse> toApiResponseList(List<Score> scoreList) {
        return scoreList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
