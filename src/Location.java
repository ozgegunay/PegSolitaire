
//Location class to hold the each point in the board
public class Location {
    //Checks whether there is a pawn or not
    private boolean check;
    //Holds the value of the specified coordinates
    private int label;

    //Constructor
    public Location(){
        this.check = false;
        this.label = -1;
    }

    public Location(boolean check, int label) {
        this.check = check;
        this.label = label;
    }

    //Getter and setter
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
