package matej.tejkogames.models.general.payload.requests;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.yamb.BoxType;
import matej.tejkogames.models.yamb.Dice;
import matej.tejkogames.models.yamb.YambChallenge;
import matej.tejkogames.models.yamb.YambForm;
import matej.tejkogames.models.yamb.YambType;
import matej.tejkogames.constants.YambConstants;

public class YambRequest {

    @NotBlank
    private User user;

    @NotBlank
    private YambType type;

    @Min(YambConstants.NUMBER_OF_COLUMNS_MIN)
    @Max(YambConstants.NUMBER_OF_COLUMNS_MAX)
    private Integer numberOfColumns;

    @Min(YambConstants.NUMBER_OF_DICE_MIN)
    @Max(YambConstants.NUMBER_OF_DICE_MAX)
    private Integer numberOfDice;

    private YambForm form;

    private BoxType announcement;

    private Set<Dice> diceSet;

    private Integer rollCount;

    private YambChallenge challenge;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public YambRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public YambType getType() {
        return type;
    }

    public void setType(YambType type) {
        this.type = type;
    }

    public Integer getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(Integer numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public Integer getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(Integer numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public YambForm getForm() {
        return form;
    }

    public void setForm(YambForm form) {
        this.form = form;
    }

    public BoxType getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(BoxType announcement) {
        this.announcement = announcement;
    }

    public Set<Dice> getDiceSet() {
        return diceSet;
    }

    public void setDiceSet(Set<Dice> diceSet) {
        this.diceSet = diceSet;
    }

    public Integer getRollCount() {
        return rollCount;
    }

    public void setRollCount(Integer rollCount) {
        this.rollCount = rollCount;
    }

    public YambChallenge getChallenge() {
        return challenge;
    }

    public void setChallenge(YambChallenge challenge) {
        this.challenge = challenge;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    


}
