package ensta;

import javax.print.attribute.standard.OrientationRequested;

public class Destroyer extends AbstractShip
{
    public Destroyer(Orientation orientation)
    {
        super("Destroyer", 'D', 2, orientation);
    }

    public Destroyer()
    {
        this(Orientation.EAST);
    }
}