class Fibonacci{
    public static int fibonacci(int iteration){
        int iter0 = 0;
        int iter1 = 1;
        int res = 1;
        if(iteration == 0){ res = iter0; }

        for(int i=2; i <= iteration; ++i){
            res = iter0+iter1;
            iter0 = iter1;
            iter1 = res;
        }
        return res;
    };

    public static void main(String[] args){
        int iter = 8;
        int resultat = fibonacci(iter);
        System.out.println("res:"+resultat);
    };
}
