package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tejko.interfaces.mappers.GameMapperInterface;
import com.tejko.models.general.payload.responses.BoxResponse;
import com.tejko.models.general.payload.responses.ColumnResponse;
import com.tejko.models.general.payload.responses.DiceResponse;
import com.tejko.models.general.payload.responses.SheetResponse;
import com.tejko.models.general.payload.responses.YambResponse;
import com.tejko.models.general.payload.responses.GameResponse;
import com.tejko.models.yamb.Box;
import com.tejko.models.yamb.Column;
import com.tejko.models.yamb.Dice;
import com.tejko.models.yamb.Sheet;
import com.tejko.models.yamb.Yamb;
import com.tejko.models.general.Game;

@Component
public class GameMapper implements GameMapperInterface {

    @Override
    public GameResponse toApiResponse(Game game) {
        if (Yamb.class.isInstance(game)) {
            return toYambResponse((Yamb) game);
        }
        return null;
    }

    private YambResponse toYambResponse(Yamb yamb) {
        SheetResponse sheet = getSheetResponseFromSheet(yamb.getSheet());
        List<DiceResponse> diceList = yamb.getDiceList().stream().map(this::getDiceResponseFromDice).collect(Collectors.toList());

        return new YambResponse(
            yamb.getId(), 
            yamb.getCreatedDate(), 
            yamb.getLastModifiedDate(), 
            sheet, 
            diceList, 
            yamb.getRollCount(), 
            yamb.getAnnouncement()
        );
    }

    @Override
    public List<GameResponse> toApiResponseList(List<Game> gameList) {
        return gameList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

    private DiceResponse getDiceResponseFromDice(Dice dice) {
        return new DiceResponse(
            dice.getValue(), 
            dice.getOrder()
        );
    }

    private SheetResponse getSheetResponseFromSheet(Sheet sheet) {
        List<ColumnResponse> columnList = sheet.getColumnList().stream().map(this::getColumnResponseFromColumn).collect(Collectors.toList());
        return new SheetResponse(
            columnList, 
            sheet.getTopSectionSum(), 
            sheet.getMiddleSectionSum(), 
            sheet.getBottomSectionSum(), 
            sheet.getTotalSum(), 
            sheet.isCompleted()
        );   
    }

    private ColumnResponse getColumnResponseFromColumn(Column column) {
        List<BoxResponse> boxList = column.getBoxList().stream().map(this::getBoxResponseFromBox).collect(Collectors.toList());
        return new ColumnResponse(
            column.getType(), 
            boxList, 
            column.getTopSectionSum(), 
            column.getMiddleSectionSum(), 
            column.getBottomSectionSum(), 
            column.isCompleted()
        );
    }

    public BoxResponse getBoxResponseFromBox(Box box) {
        return new BoxResponse(
            box.getType(), 
            box.getValue(), 
            box.isFilled(), 
            box.isAvailable()
        );
    }

}
