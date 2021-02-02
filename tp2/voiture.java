class Voiture extends Vehicule implements Motorise{
    private int nombrePortes;
    private Propulsion propulsion;
    private int puissance;

    public int getNombrePortes(){ return nombrePortes; }
    public void setNombrePortes(int nombrePortes){ this.nombrePortes = nombrePortes; }

    public Propulsion getPropulsion(){ return propulsion; }
    public void setPropulsion(Propulsion propulsion){ this.propulsion= propulsion; }

    public int getPuissance(){ return puissance; }
    public void setPuissance(int puissance){ this.puissance = puissance; }

    public Voiture(String modele, int nombrePlaces, int poids, int nombrePortes){
        super(modele, nombrePlaces, poids);
        this.nombrePortes=nombrePortes;
    }

    public Voiture(String modele, int nombrePlaces, int poids, int nombrePortes, Propulsion propulsion){
        this(modele, nombrePlaces, poids, nombrePortes);
        this.propulsion = propulsion;
    }

    public Voiture(String modele, int nombrePlaces, int poids, int nombrePortes, Propulsion propulsion, int puissance){
        this(modele, nombrePlaces, poids, nombrePortes);
        this.propulsion = propulsion;
        this.puissance = puissance;
    }

    public float getConsommation(){
        if(propulsion != null){
        switch(propulsion){
        case ESSENCE:
            return (puissance*5)/poids;
        case DIESEL:
            return (puissance*3)/poids;
        }}
        return 0.0f;
    }

    public String toString()
    { return super.toString() + " Nombre de portes : " + nombrePortes + " Propulsion : " + propulsion + " Puissance : " + puissance + " Consommation : " + this.getConsommation(); }
}
