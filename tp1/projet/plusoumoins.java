import java.util.Scanner;

class PlusOuMoins{
    public static int tirageAlea()
    {
        int tirage = (int) ( Math.random() * 99 + 1);

        return tirage;
    };

    public static void main(String[] args)
    {
        int valeur = tirageAlea();
        int nombreEssai = 7;
        boolean trouve = false;
        Scanner scanner;
        int valeurProposee;
        int nombreTentatives;

        while( nombreEssai>0 && trouve == false)
        {
            System.out.println("Proposer un nombre, vous avez "+ nombreEssai +" essais :");
            scanner = new Scanner(System.in);
            valeurProposee = scanner.nextInt();
            nombreTentatives = 8-nombreEssai;
            if(valeurProposee == valeur)
            {
                trouve = true;
                System.out.println("Reussi ! Vous avez gagne en " + nombreTentatives + " essais !");
            }
            else if(valeurProposee < valeur)
            {
                System.out.println("Plus !");
            }
            else
            {
                System.out.println("Moins !");
            }
            nombreEssai--;
        }

        if(trouve == false)
        {
            System.out.println("Perdu ! La valeur a trouver etait " + valeur);
        }

    };
}
