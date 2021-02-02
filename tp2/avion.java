class Avion extends Vehicule{
    private int altitudeMax;

    public int getAltitude(){ return altitudeMax; }
    public void setAltitude(int altitudeMax){ this.altitudeMax = altitudeMax; }

    public Avion(String modele, int nombrePlaces, int poids, int altitudeMax){
        super(modele, nombrePlaces, poids);
        this.altitudeMax=altitudeMax;
    }

    public String toString(){ return super.toString() + " Altitude max : " + altitudeMax; }
}
