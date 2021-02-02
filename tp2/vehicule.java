abstract class Vehicule{
    protected String modele;
    protected int nombrePlaces;
    protected int poids;
    private static int nbVehicule = 0;

    public Vehicule(String modele, int nombrePlaces, int poids){
        this.modele = modele;
        this.nombrePlaces = nombrePlaces;
        this.poids = poids;
        nbVehicule++;
    };

    public String getModele(){ return modele; }
    public int getNombrePlaces(){ return nombrePlaces; }
    public int getPoids(){ return poids; }
    public static int getNombreVehicule(){ return nbVehicule; }

    public void setModele(String modele){ this.modele = modele; }
    public void setNombrePlaces(int nombrePlaces){ this.nombrePlaces = nombrePlaces; }
    public void setPoids(int poids){ this.poids = poids; }

    @Override
    public String toString(){
        return "Modele: "+modele+" Nombre de places : "+nombrePlaces + " Poids : " + poids;
    }
}
