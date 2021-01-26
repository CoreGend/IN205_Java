class Tableau {
    public static void printTab(double[] tab){
          System.out.println("Taille du tableau tab : " + tab.length);

        for (int index = 0; index < tab.length ; index++) {
        if(index == tab.length - 1) {
            System.out.println(tab[index]);
        } else {
          System.out.print(tab[index] + ", ");
        }
      }
    };

    public static double[] concatenate(double[] tableau, double valeur){
        int size = tableau.length+1;
        double[] res; res = new double[size];

        for(int i=0; i<tableau.length; ++i){
            res[i] = tableau[i];
        }
        res[tableau.length] = valeur;
        return res;
    }

    public static double[] triRapide(double[] tableauATrier){
        double[] res = new double[tableauATrier.length];

        if(tableauATrier.length == 1) return tableauATrier;

        double pivot = tableauATrier[0];
        int lgt = tableauATrier.length;
        double[] tableauInf, tableauSup;
        tableauInf = new double[0]; tableauSup = new double[0];

        for(int i=1; i<lgt; i++){
            if(tableauATrier[i] < pivot){
                tableauInf = concatenate(tableauInf, tableauATrier[i]);
            }
            else{
                tableauSup = concatenate(tableauSup, tableauATrier[i]);
            }
        }
        tableauInf = triRapide(tableauInf);
        tableauSup = triRapide(tableauSup);

        for(int i=0; i<tableauATrier.length; ++i){
            if(i<tableauInf.length) { res[i] = tableauInf[i]; }
            else if(i == tableauInf.length) { res[i] = pivot; }
            else { res[i] = tableauSup[i-tableauInf.length-1]; }
        }

        return res;
    };

  public static void main(String[] args) {
      /**
      Les diverses manieres de déclaration d'un tableau.
      **/

      double[] doubleArray;
      double [] doubleArray1;
      double doubleArray2[];
      double doubleArray3 [];

      /**
      Une simple déclaration de tableau ne permet pas d'y acceder.
      Décommenter la ligne suivante, voir ce qui se passe à la compilation
      **/

      //System.out.println(stringArray[0]);

      /**
      Instanciation du tableau precedemment déclaré.
      Dans l'instruction ecrite ci-dessous, on instancie un tableau de 3 elements
      **/

      doubleArray = new double[3];

      /**
      Ecrire ci-dessous une instruction qui permet d'acceder à un element du tableau
      Que remarquez-vous ?
      **/

      // Todo ...
      System.out.println(doubleArray[2]); //initialisé à 0

      /**
      Changement d'un emplacement du tableau
      **/

      doubleArray[0] = 1.0;

      /**
      En Java, il n'est pas possible d'acceder à un emplacement qui depasse n-1 (où n est la taille du tableau)
      Décommenter l'instruction suivante, voir ce qui se passe à l'execution
      **/

      // il y a une interruption car on dépasse la taille
//      doubleArray[3] = 2.0;

      /**
      Autres manieres d'instanciation
      **/

      int[] intArray = new int[] {42, 55, 99};

      int[] intArrayAnonymous = {42, 55, 99, 100};

      //**************//

      System.out.println("Taille du tableau intArrayAnonymous : " + intArrayAnonymous.length); // Avoir la taille du tableau

      /**
      Parcourir un tableau et afficher les elements
      **/

      for (int index = 0; index < intArrayAnonymous.length ; index++) {
        if(index == intArrayAnonymous.length - 1) {
            System.out.println(intArrayAnonymous[index]);
        } else {
          System.out.print(intArrayAnonymous[index] + ", ");
        }
      }

      /***** Ou encore *****/

      for (int value : intArrayAnonymous) {
        System.out.println(value);
      }

      double[] arrayTest = { 2.0, 1.0, 5.0, 7.0, 4.0 };
      System.out.println("Taille du tableau arrayTest : " + arrayTest.length);

      for (int index = 0; index < arrayTest.length ; index++) {
        if(index == arrayTest.length - 1) {
            System.out.println(arrayTest[index]);
        } else {
          System.out.print(arrayTest[index] + ", ");
        }
      }

      arrayTest = triRapide(arrayTest);
      for (int index = 0; index < arrayTest.length ; index++) {
        if(index == arrayTest.length - 1) {
            System.out.println(arrayTest[index]);
        } else {
          System.out.print(arrayTest[index] + ", ");
        }
      }

  }
}
