import javax.lang.model.util.ElementScanner6;

class ShipState
{
    private AbstractShip ship;
    private boolean struck;

    public ShipState(AbstractShip ship, boolean struck)
    {
        this.ship = ship; this.struck = struck;
    }
    public ShipState(AbstractShip ship)
    {
        this(ship, false);
    }

    public void addStrike()
    { 
        if(struck == false)
            ship.addStrike();
        struck = true;
    }

    public boolean isStruck()
    {   if(ship.getStrikeCount() != 0) return true;
        return false;
    }

    public String toString()
    {
        if(ship == null){ return ".";}
        else
        {
            if(struck == true)
            { return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED); }
            else
            { return String.valueOf(ship.getLabel()); }
        }
    }

    public boolean isSunk()
    {
        if(ship.isSunk())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public AbstractShip getShip()
    {
        return ship;
    }
}