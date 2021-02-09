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
    public String getNom(){ return nom; }
    public int getTaille(){ return taille; }
    public Character[] getBoardShip(){ return boardShip; }
    public boolean[] getBoardHit(){ return boardHit; }

    public void setNom(String nom){ this.nom = nom; }

    public void setBoat(char colonne, int ligne){ this.boardShip[(colonne-'A') + (ligne-1)*this.taille] = 'A'; }
    public void setHit(char colonne, int ligne){ this.boardHit[(colonne-'A') + (ligne-1)*this.taille] = true; }

    // la fonction est très moche actuellement, elle sera refaite plus proprement plus tard
    public void print(){
        String ligneCourante;
        // en-tête
        ligneCourante = "Navires :";
        for(int i=0; i<(this.taille)*2+3-9 +3; i++){
            ligneCourante = ligneCourante + ' ';
        }
        ligneCourante = ligneCourante + "Frappes :";
        System.out.println(ligneCourante);

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

        // affichage grille
        for(int i=0; i<taille; i++){
            ligneCourante = ""+(i+1);
            for(int j=0; j < 2 - (i+1)/10; j++){
                ligneCourante = ligneCourante + ' ';
            }
            for(int j=0; j<taille; j++){
                ligneCourante = ligneCourante + this.boardShip[i*taille + j] + ' ';
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

        System.out.println("");
    }
}