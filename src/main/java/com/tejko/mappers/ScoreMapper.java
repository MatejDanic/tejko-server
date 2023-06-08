package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tejko.interfaces.mappers.ScoreMapperInterface;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.responses.ScoreResponse;

@Component
public class ScoreMapper implements ScoreMapperInterface {

    @Override
    public ScoreResponse toApiResponse(Score score) {
        return new ScoreResponse(
            score.getId(), 
            score.getCreatedDate(), 
            score.getLastModifiedDate(),
            score.getUser().getId(),
            score.getValue()
        );
    }

    @Override
    public List<ScoreResponse> toApiResponseList(List<Score> scoreList) {
        return scoreList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
