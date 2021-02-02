class Planeur extends Avion{
    private int portance;

    public int getPortance(){ return portance; }
    public void setPortance(int portance){ this.portance = portance; }

    public Planeur(String modele, int nombrePlaces, int poids, int altitudeMax, int portance){
        super(modele, nombrePlaces, poids, altitudeMax);
        this.portance=portance;
    }

    public String toString(){ return super.toString() + " Portance : " + portance; }
}
