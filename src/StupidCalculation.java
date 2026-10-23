import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class StupidCalculation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int inputMenu = 0;
        ArrayList<String> logHistory = new ArrayList<>();

        try {
            FileWriter writer = new FileWriter("LogHistory.csv", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (inputMenu != 4) {
            System.out.println("""
                    ==CALCULATION PROGRAM==
                    Choose Your Action:
                    (1)Addition
                    (2)Substraction
                    (3)View calculation history
                    (4)Exit""");
            System.out.print("Enter ur Action: ");
            inputMenu = sc.nextInt();
            int numberAmount = 3;
            int []nums = new int[numberAmount];
            StringBuilder sb = new StringBuilder();
            switch (inputMenu) {
                case 1:
                    int sum = 0;
                    for(int i =0; i < nums.length; i++){
                        System.out.print("num (" + i + "): ");
                        nums[i] = sc.nextInt();
                        sum+= nums[i];
                        sb.append(nums[i]).append(i != nums.length-1 ? " + " : " = ");

                    }
                    sb.append(sum);
                    logHistory.add(sb.toString());
                    System.out.println("Result of Substraction (" + sb + ") is  " + sum);
                    break;
                case 2:
                    int result;
                    System.out.print("num (0): ");
                    nums[0] = sc.nextInt();
                    sb.append(nums[0]).append(" + ");
                    result = nums[0];
                    for(int i = 1; i < numberAmount; i++){
                        System.out.print("num (" + i + "): ");
                        nums[i] = sc.nextInt();
                        result -= nums[i];
                        sb.append(nums[i]).append(i != numberAmount -1 ? " + " : " = ");
                    }
                    sb.append(result);
                    logHistory.add(sb.toString());
                    System.out.println("Result of Substraction (" + sb + ") is  " + result);
                    break;
                case 3:
                    System.out.println("Calculation History:");
                    for(int i = 0; i < logHistory.size(); i++){
                        System.out.println(i + ".) " + logHistory.get(i));
                    }
                    break;
            }

        }
    }
}
