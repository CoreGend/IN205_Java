import java.net.http.WebSocket;

class Board{
    private String nom;
    private int taille;
    private ShipState[] boardShip;
    private Boolean[] boardHit;
    
    // constructor
    public Board(String nom, int taille){
        this.nom = nom;
        this.taille = taille;
        this.boardShip = new ShipState[taille*taille];
        this.boardHit  = new Boolean[taille*taille];

        for(int i=0; i<taille*taille; i++)
        {
            boardShip[i] = new ShipState(null);
            boardHit[i] = null;
        }
    }

    public Board(String nom){
        this(nom, 10);
    }

    // fonctions    
    public String getName(){ return nom; }
    public int getSize(){ return taille; }
    public ShipState[] getBoardShip(){ return boardShip; }
    public Boolean[] getBoardHit(){ return boardHit; }

    public void setNom(String nom){ this.nom = nom; }

    private void print_en_tete(){
        // en-tête
        System.out.print("Navires :");
        for(int i=0; i<taille*2+3-9 +3; i++){
            System.out.print(' ');
        }
        System.out.print("Frappes :");
        System.out.println("");
    }

    private void print_first_line(){
        // première ligne
        System.out.print("   ");
        for(int i=0; i<taille; i++){
            System.out.print((char) ('A'+i));
            System.out.print(' ');
        }
        for(int i=0; i<6; i++){
            System.out.print(' ');
        }
        for(int i=0; i<taille; i++){
            System.out.print((char) ('A'+i));
            System.out.print(' ');
        }
        System.out.println("");
    }

    private void print_grid(){
        // affichage grille
        for(int i=0; i<taille; i++){
            System.out.print(""+(i+1));
            for(int j=0; j < 2 - (i+1)/10; j++){
                System.out.print(' ');
            }
            for(int j=0; j<taille; j++){
                System.out.print(boardShip[i*taille + j].toString() + ' ');
            }

            System.out.print("   " + (i+1));
            for(int j=0; j < 2 - (i+1)/10; j++){
                System.out.print(' ');
            }            
            for(int j=0; j<taille; j++){
                if(boardHit[i*taille + j] == null)
                { System.out.print(". ");}
                else if(boardHit[i*taille + j] == true){
                    System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.RED));
                }
                else if(boardHit[i*taille + j] == false){
                    System.out.print("X ");
                }
            }
            System.out.println("");
        }
    }

    public void print(){
        print_en_tete();
        print_first_line();
        print_grid();        
        System.out.println("");
    }

    private void checkPlace(AbstractShip ship, int x, int y) throws NotEnoughSpace{
        Orientation orientation = ship.getOrientation();
        int len = ship.getLength();

        if( (orientation == Orientation.EAST && x > taille-len) 
            || (orientation == Orientation.WEST && x < len)
            || (orientation == Orientation.NORTH && y < len)
            || (orientation == Orientation.SOUTH && y > taille-len) )
            { throw new NotEnoughSpace("There is not enough space to place the boat"); }
    }

    private void checkBoat(AbstractShip ship, int x, int y) throws Intersect
    {
        Orientation orientation = ship.getOrientation();
        int len = ship.getLength();

        for(int i=0; i<len; i++){
            if(hasShip(x, y)) throw new Intersect("The boat intersects another boat");

            if(orientation == Orientation.SOUTH) y+=1;
            else if(orientation == Orientation.NORTH) y-=1;
            else if(orientation == Orientation.EAST) x+=1;
            else if(orientation == Orientation.WEST) x-=1;
        }
    }

    public void putShip(AbstractShip ship, int x, int y) throws NotEnoughSpace, Intersect{
        try{
            checkPlace(ship, x, y);
            checkBoat(ship, x, y);

            Orientation orientation = ship.getOrientation();
            int len = ship.getLength();
            for(int i=0; i<len; i++){
                boardShip[x+taille*y] = new ShipState(ship);
        
                if(orientation == Orientation.SOUTH) y+=1;
                else if(orientation == Orientation.NORTH) y-=1;
                else if(orientation == Orientation.EAST) x+=1;
                else if(orientation == Orientation.WEST) x-=1;
            }
        }
        catch(NotEnoughSpace e)
        {   System.out.println("There is not enough space to place the boat");
            throw new NotEnoughSpace("There is not enough space to place the boat");}
        catch(Intersect e)
        {   System.out.println("The boat intersects another boat"); 
            throw new Intersect("The boat intersects another boat"); }
    }
    
    private boolean hasShip(int x, int y){
        if (boardShip[x+y*taille].getShip() != null) return true;
        return false;
    }

    public void setHit(boolean hit, int x, int y){
        if(hit == true) boardHit[x+y*taille] = true;
        else boardHit[x+y*taille] = false;
    }

    public Boolean getHit(int x, int y){
        if (hasShip(x, y) == true) 
        {
            boardShip[x+y*taille].addStrike();
            return true;
        }
        else return false; 
    }

    Hit sendHit(int x, int y)
    {
        boolean hit = getHit(x, y);
        if(hit == true)
        {
            boolean dead = boardShip[x+y*taille].getShip().isSunk();
            if(dead == true){
                switch(boardShip[x+y*taille].getShip().getLabel())
                {
                    case 'C':
                        return Hit.CARRIER;
                    case 'S':
                        return Hit.SUBMARINE;
                    case 'B':
                        return Hit.BATTLESHIP;
                    case 'D':
                        return Hit.DESTROYER;
                }
            } 
            else return Hit.STRIKE;
        }
        
        return Hit.MISS;
    }
}