class Chasseur extends Avion implements Motorise{
    private int puissance;

    public int getPuissance(){ return puissance; }
    public void setPuissance(int puissance){ this.puissance = puissance; }

    public Chasseur(String modele, int nombrePlaces, int poids, int altitudeMax, int puissance){
        super(modele, nombrePlaces, poids, altitudeMax);
        this.puissance=puissance;
    }

    public float getConsommation(){
        return this.puissance/this.poids;
    }

    public String toString(){ return super.toString() + " Puissance : " + puissance + " Consommation  : " + this.getConsommation(); }
}
