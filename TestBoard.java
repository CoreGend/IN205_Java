class TestBoard{
    public static void main(String[] args){
        Board boardTest1 = new Board("Marcel", 7);
        Board boardTest2 = new Board("Michel");
        Board boardTest3 = new Board("Antonin", 15);

        boardTest1.print();
        boardTest2.print();
        boardTest3.setHit('C', 3);
        boardTest3.setBoat('L', 14);
        boardTest3.print();
    }
}