import java.util.ArrayList;

class Test{
    public static void main(String[] args){
        /* a partir de exo 2 c'est classe abstraite donc ne peut plus ztre instancze
        Vehicule vehiculeTest = new Vehicule("Yaris", 5, 1200);
        String valeurs = vehiculeTest.toString();
        System.out.println(valeurs);
        */

        Avion testAvion = new Avion("A380", 380, 380000, 10000);
        String valAvion = testAvion.toString();
        System.out.println(valAvion);

        Voiture testVoiture = new Voiture("Clio", 5, 1000, 3);
        String valVoit = testVoiture.toString();
        System.out.println(valVoit);

        Voiture testVoitureBis = new Voiture("Bugatti veyron", 2, 2500, 3, Propulsion.ESSENCE);
        String valVoitBis = testVoitureBis.toString();
        System.out.println(valVoitBis);

        Voiture testVoitureTer = new Voiture("Bugatti veyron 2", 2, 2000, 3, Propulsion.ESSENCE, 1000);
        String valVoitTer = testVoitureTer.toString();
        System.out.println(valVoitTer);

        Planeur testPlaneur = new Planeur("Planeur", 1, 20, 1000, 300);
        String valPlan = testPlaneur.toString();
        System.out.println(valPlan);

        Chasseur testChasseur = new Chasseur("Chasseur", 2, 1000, 30000, 7000);
        String valCha = testChasseur.toString();
        System.out.println(valCha);

        ArrayList<Vehicule> vehiculeListe = new ArrayList<>();
        vehiculeListe.add(new Voiture("Mercedes Classe E (Type 213)", 5, 1600, 4, Propulsion.DIESEL, 137000));
        vehiculeListe.add(new Voiture("Audi TT 8S", 4, 1345, 2, Propulsion.ESSENCE, 145000));
        vehiculeListe.add(new Avion("A380", 380, 380000, 10000));
        vehiculeListe.add(new Planeur("Planeur Liste", 2, 20, 1000, 300));
        vehiculeListe.add(new Chasseur("Chasseur", 2, 1000, 30000, 7000));

        for(Vehicule vehicule : vehiculeListe)
            System.out.println(vehicule);
        System.out.println("Nombre de vehicules : " + testAvion.getNombreVehicule()) ;
    };
}
