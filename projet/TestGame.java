package ensta;

class TestGame
{
    public static void main(String[] args){
        /*Board firstBoard = new Board("Player 1");

        AbstractShip[] ships;
        ships = new AbstractShip[5];
        ships[0] = new Destroyer();
        ships[1] = new Submarine();
        ships[2] = new Submarine();
        ships[3] = new Battleship();
        ships[4] = new Carrier();

        BattleShipsAI ai = new BattleShipsAI(firstBoard, firstBoard);
        int navDestr = 0;
        ai.putShips(ships);
        firstBoard.print();

        int[] coords = new int[2];
        Hit hit;
        while(navDestr<5)
        {
            hit = ai.sendHit(coords);
            char x = (char) ('A' + coords[0]);
            int y = coords[1]+1;
            System.out.print("Tir envoyé en " + x + y + " : ");
            if((hit.toString() != "manqué") && (hit.toString() != "touché"))
            { 
                navDestr++;
                System.out.println(hit.toString() + " coulé, navires detruits : " + navDestr);
            }
            else
            {
                System.out.println(hit.toString());
            }

            firstBoard.print();
        }*/

        Game newGame = new Game();
        newGame.init();
        newGame.run();
    }
}