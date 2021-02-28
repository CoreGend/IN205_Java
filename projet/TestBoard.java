package ensta;

import java.util.*;

public class TestBoard{
    public static void main(String[] args) throws NotEnoughSpace, Intersect{
        Board boardTest2 = new Board("Michel");

/*        Board boardTest1 = new Board("Marcel", 7);
        Board boardTest3 = new Board("Antonin", 15);

        boardTest1.print();
        boardTest3.setHit('C', 3);
        boardTest3.setBoat('L', 14);
        boardTest3.print();
*/
        Carrier car = new Carrier(Orientation.NORTH);
        Submarine sub = new Submarine(Orientation.NORTH);
        Destroyer destro = new Destroyer();


        try{ boardTest2.putShip(destro, 2, 3); }
        catch(Exception e){}

        try{ boardTest2.putShip(car, 5, 3); }
        catch(Exception e){}

        try{ boardTest2.putShip(sub, 6, 6); }
        catch(Exception e){}

        try{ boardTest2.putShip(car, 1, 5); }
        catch(Exception e){}

        Hit test = boardTest2.sendHit(1, 5);
        System.out.print(test.toString());
        System.out.println("");
        test = boardTest2.sendHit(2, 3);
        System.out.print(test.toString());
        System.out.println("");
        test = boardTest2.sendHit(3, 3);
        System.out.print(test.toString());
        System.out.println("");
        test = boardTest2.sendHit(0, 0);
        System.out.print(test.toString());
        System.out.println("");
        boardTest2.print();

        Random rnd = new Random();
        int res = rnd.nextInt(10);
        System.out.println(res);
    }
}