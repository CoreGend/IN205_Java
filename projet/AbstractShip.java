abstract class AbstractShip
{
    private Character label;
    private String nom;
    private int taille;
    private Orientation orientation;

    public AbstractShip(String nom, Character label, int taille, Orientation orientation)
    {
        this.nom = nom; this.label = label; this.taille = taille; this.orientation = orientation;
    }

    public Character getLabel(){ return label; }
    public String getNom(){return nom; }
    public int getTaille(){return taille;}
    public Orientation getOrientation(){return orientation;}

    public void setOrientation(Orientation orientation){this.orientation = orientation; }
}