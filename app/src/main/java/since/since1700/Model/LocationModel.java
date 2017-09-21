package since.since1700.Model;

/**
 * Created by Sandhiya on 9/19/2017.
 */

public  class LocationModel {

    private String location;
    private boolean isSelected;
    private  boolean colorSelection;

    public boolean isColorSelection() {
        return colorSelection;
    }

    public void setColorSelection(boolean colorSelection) {
        this.colorSelection = colorSelection;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
