package ensta;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        if (!loadSave()) {
            // init attributes
            System.out.println("entre ton nom:");

            // TODO use a scanner to read player name
            Scanner sin = new Scanner(System.in);
            String[] pseudo = sin.nextLine().split(" ");

            // TODO init boards
            Board b1, b2;
            b1 = new Board(pseudo[0]);
            b2 = new Board("IA");

            List<AbstractShip> ships = createDefaultShips();
           /* ships.add(new Destroyer());
            ships.add(new Submarine());
            ships.add(new Submarine());
            ships.add(new Battleship());
            ships.add(new Carrier());*/

            // TODO init this.player1 & this.player2
            player1 = new Player(b1, b2, ships);
            player2 = new AIPlayer(b2, b1, ships);

            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Board b2 = player1.opponentBoard;
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            System.out.println(b1.getName() + " à ton tour de jouer !");
            b2.print();
            hit = player1.sendHit(coords); // TODO player1 send a hit
            int x = coords[0]; int y=coords[1];
            boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)
            player1.board.setHit(strike, x, y);
            char x_affiche = (char) ('A'+x);
            int y_affiche = y+1;

            System.out.println("Reussi ? " + strike + " Valeur : " + player2.board.getBoardShip()[x+y*10]);
            done = updateScore();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));
            b1.print();

            save();

            if (!done && !strike) {
                do {
                    System.out.println(b2.getName() + " à ton tour de jouer !");
                    hit = player2.sendHit(coords); // TODO player2 send a hit.

                    strike = hit != Hit.MISS;
                    x = coords[0]; y=coords[1];
                    player2.board.setHit(strike, x, y);
                    x_affiche = (char) ('A'+x);
                    y_affiche = y+1;
                    System.out.println("Reussi ? " + strike);
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    if (strike) {
                        b1.print();
                    }

                    done = updateScore();
                    if (!done) {
                        save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
      //  sin.close();
    }


    private void save() {
        /*try {
            // TODO bonus 2 : uncomment
            //  if (!SAVE_FILE.exists()) {
            //      SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            //  }

            // TODO bonus 2 : serialize players

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            /*try {
                // TODO bonus 2 : deserialize players

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }*/
            return true;
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}
