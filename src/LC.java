import java.util.HashMap;

public class LC {
    public static int subarraySum(int[] nums, int k) {
        int left = 0;
        int sum = 0;
        int count = 0;
       /* while(right < nums.length || left < nums.length){
            System.out.println("R: " + right + " L: " + left + " sum: " + sum);
            if(sum < )

        }*/
        for(int right = 0; right < nums.length; right++){
            sum+= nums[right];
            System.out.println("R: " + right + " L: " + left + " sum: " + sum);
            while(sum > k){
                System.out.println("R: " + right + " L: " + left + " sum: " + sum);
                sum -= nums[left++];
            }
            if(sum == k){
                count++;
            }
        }
        return count;
    }
    static void main() {
        int count = subarraySum(new int[]{2, -1, 2}, 2);
        System.out.println(count);
    }

}
