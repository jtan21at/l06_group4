package L04;

public class OptimizedRecursion {

    static long powerN(int x, int n){
        if(n == 0){
            return 1;
        }

        else{
            return x * powerN(x,n-1);
        }
    }


    }
