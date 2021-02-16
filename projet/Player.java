import java.io.Serializable;
import java.util.List;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getLength());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();

            switch(res.orientation)
            {
                case "n":
                    s.setOrientation(Orientation.NORTH);
                    break;
                case "s":
                    s.setOrientation(Orientation.SOUTH);
                    break;
                case "e":
                    s.setOrientation(Orientation.EAST);
                    break;
                case "w":
                    s.setOrientation(Orientation.WEST);
                    break;
            }

            try{ board.putShip(s, res.x, res.y); }
            catch(Exception e){--i;}

            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            
            // TODO call sendHit on this.opponentBoard
            if(this.board.getBoardHit()[hitInput.x0+taille*hitInput.y] != null)
            { print("La case a déjà été frappée"); }
            else{
                hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
            
            // coords est un tableau, on lui met dedans les coordonnees de hitInput
                coords = new int[2];
                coords[0] = hitInput.x; coords[1] = hitInput.y;
                done = true;
            }
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
