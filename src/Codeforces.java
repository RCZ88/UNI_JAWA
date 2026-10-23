import java.util.Scanner;

public class Codeforces {
    static Scanner sc = new Scanner(System.in);
    static void removeSmallest(){
        int t = sc.nextInt();
        for(int i = 0; i<t; i++){
            int n = sc.nextInt();
            int[]arr = new int[n];
            for(int j = 0; j<n; j++){
                arr[j] = sc.nextInt();
            }
            for(int j = n-1; j>0; j--){
                int diff = arr[j]-arr[j-1];

            }
        }
    }
}
