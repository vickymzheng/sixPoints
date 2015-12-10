package app.sixpts.CustomObjects;

/**
 * Created by paulkowa on 12/9/15.
 */
public class Tuple {
    private String selectedLot;
    private String suggestedLot;
    private int selectedLotValue;
    private int suggestedLotValue;

    public Tuple() {
        selectedLot = "";
        suggestedLot = "";
        selectedLotValue = 100;
        suggestedLotValue = 100;
    }

    public Tuple (String selectedLot, String suggestedLot, int selectedLotValue, int suggestedLotValue) {
        this.selectedLot = selectedLot;
        this.suggestedLot = suggestedLot;
        this.suggestedLotValue = suggestedLotValue;
        this.selectedLotValue = selectedLotValue;
    }


    public void setSelectedLot(String selectedLot) {
        this.selectedLot = selectedLot;
    }

    public void setSelectedLotValue(int selectedLotValue) {
        this.selectedLotValue = selectedLotValue;
    }

    public void setSuggestedLot(String suggestedLot) {
        this.suggestedLot = suggestedLot;
    }

    public void setSuggestedLotValue(int suggestedLotValue) {
        this.suggestedLotValue = suggestedLotValue;
    }

    public int getSelectedLotValue() {
        return selectedLotValue;
    }

    public int getSuggestedLotValue() {
        return suggestedLotValue;
    }

    public String getSelectedLot() {
        return selectedLot;
    }

    public String getSuggestedLot() {
        return suggestedLot;
    }
}
