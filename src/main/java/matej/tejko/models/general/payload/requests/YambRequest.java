package matej.tejko.models.general.payload.requests;

public class YambRequest extends GameRequest {

    private String formCode;

    private Integer numberOfDice;

    public YambRequest() {
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public Integer getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(Integer numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

}
