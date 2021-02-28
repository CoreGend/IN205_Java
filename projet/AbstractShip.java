package ensta;

abstract public class AbstractShip
{
    private Character label;
    private String name;
    private int length;
    public Orientation orientation;
    private int strikeCount;

    public AbstractShip(String name, Character label, int length, Orientation orientation)
    {
        this.name = name; this.label = label; this.length = length; this.orientation = orientation; strikeCount = 0;
    }

    public Character getLabel() { return label; }
    public String getName() { return name; }
    public int getLength() { return length; }
    public Orientation getOrientation() { return orientation; }
    public int getStrikeCount() { return strikeCount; }

    public void setOrientation(Orientation orientation) { this.orientation = orientation; }
    public void addStrike() { strikeCount++; }
    
    public boolean isSunk()
    { 
        if(strikeCount == length) 
            { return true; } 
        return false; 
    }

}