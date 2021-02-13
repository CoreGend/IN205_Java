import java.net.http.WebSocket;

class Board{
    private String nom;
    private int taille;
    private Character[] boardShip;
    private boolean[] boardHit;
    
    // constructor
    public Board(String nom, int taille){
        this.nom = nom;
        this.taille = taille;
        this.boardShip = new Character[taille*taille];
        this.boardHit  = new boolean[taille*taille];

        for(int i=0; i<taille*taille; i++)
        {
            boardShip[i] = '.'; boardHit[i] = false;
        }
    }

    public Board(String nom){
        this(nom, 10);
    }

    // fonctions    
    public String getName(){ return nom; }
    public int getSize(){ return taille; }
    public Character[] getBoardShip(){ return boardShip; }
    public boolean[] getBoardHit(){ return boardHit; }

    public void setNom(String nom){ this.nom = nom; }

    public void setBoat(char colonne, int ligne){ boardShip[(colonne-'A') + (ligne-1)*taille] = 'A'; }
    public void setHit(char colonne, int ligne){ boardHit[(colonne-'A') + (ligne-1)*taille] = true; }

    private void print_en_tete(){
        String ligneCourante;
        // en-tête
        ligneCourante = "Navires :";
        for(int i=0; i<taille*2+3-9 +3; i++){
            ligneCourante = ligneCourante + ' ';
        }
        ligneCourante = ligneCourante + "Frappes :";
        System.out.println(ligneCourante);
    }

    private void print_first_line(){
        String ligneCourante;
        // première ligne
        ligneCourante = "   ";
        for(int i=0; i<taille; i++){
            ligneCourante = ligneCourante + (char) ('A'+i) + ' ';
        }
        for(int i=0; i<6; i++){
            ligneCourante = ligneCourante + ' ';
        }
        for(int i=0; i<taille; i++){
            ligneCourante = ligneCourante + (char) ('A'+i) + ' ';
        }
        System.out.println(ligneCourante);
    }

    private void print_grid(){
        String ligneCourante;
        // affichage grille
        for(int i=0; i<taille; i++){
            ligneCourante = ""+(i+1);
            for(int j=0; j < 2 - (i+1)/10; j++){
                ligneCourante = ligneCourante + ' ';
            }
            for(int j=0; j<taille; j++){
                ligneCourante = ligneCourante + boardShip[i*taille + j] + ' ';
            }

            ligneCourante = ligneCourante + "   " + (i+1);
            for(int j=0; j < 2 - (i+1)/10; j++){
                ligneCourante = ligneCourante + ' ';
            }            
            for(int j=0; j<taille; j++){
                if(boardHit[i*taille + j] == true){
                    ligneCourante = ligneCourante + "X ";
                }
                else{
                    ligneCourante = ligneCourante + ". ";
                }
            }
            System.out.println(ligneCourante);
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
        int len = ship.getTaille();

        if( (orientation == Orientation.EAST && x > taille-len) 
            || (orientation == Orientation.WEST && x < len)
            || (orientation == Orientation.NORTH && y < len)
            || (orientation == Orientation.SOUTH && y > taille-len) )
            { throw new NotEnoughSpace("There is not enough space to place the boat"); }
    }

    private void checkBoat(AbstractShip ship, int x, int y) throws Intersect
    {
        Orientation orientation = ship.getOrientation();
        int len = ship.getTaille();

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
            int len = ship.getTaille();
            for(int i=0; i<len; i++){
                boardShip[x+taille*y] = ship.getLabel();
        
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
        if (boardShip[x+y*taille] != '.') return true;
        return false;
    }

    public void setHit(boolean hit, int x, int y){
        if(hit == true) boardHit[x+y*taille] = true;
        else boardHit[x+y*taille] = false;
    }

    public Boolean getHit(int x, int y){
        if (hasShip(x, y) == true) return true;
        else return false; 
    }

}