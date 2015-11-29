package hw3;

public class MaximumSubarrayTest {

	/* O(n^2) 시간 복잡도를 가진 brute-force 알고리즘 */
	
	private static int[] findMaxSubarrayBruteForce (int[] arr){
		
		if (arr == null || arr.length <= 0) return new int[3];

		double maxSum = Double.NEGATIVE_INFINITY;
		int[] result = {0, arr.length, arr[0]};
		
		for (int i = 0; i < arr.length; i++){
			
			int sum = 0;
			
			for (int j = i; j < arr.length; j++){
				sum += arr[j];
		
				if (sum > maxSum) {
					maxSum = (double) sum;
					result[0] = i;
					result[1] = j;
				}
			}
		}
		
		result[2] = (int) maxSum;
		
		return result;
	}
	
	
	/* O(n lg n) 시간 복잡도를 가진 recursive 알고리즘 */
	
	private static int[] findMaxCrossingSubarray (int[] arr, int low, int mid, int high) {

		if (arr == null || arr.length <= 0) return new int[3];
		
		int[] result = {mid, mid+1, arr[mid]}; // left측의 startIdx, right측의 startIdx, sum=arr[mid] 으로 초기화.
		
		double leftSum = Double.NEGATIVE_INFINITY;
		int sum = 0;
		
		for (int i = mid; i >= low; i--){
			sum += arr[i];
			
			if (sum > leftSum){
				leftSum = (double) sum;
				result[0] = i;
			}	
		}
		
		double rightSum = Double.NEGATIVE_INFINITY;
		sum = 0;
		
		for (int j = mid+1; j <= high; j++){
			sum += arr[j];
			
			if (sum > rightSum){
				rightSum = (double) sum;
				result[1] = j;
			}
		}
		
		result[2] = (int) leftSum + (int) rightSum;
		
		return result;
	}
	

	private static int[] findMaxSubarray(int[] arr, int low, int high){
		
		if (arr == null || arr.length <= 0) return new int[3];
		
		if (low == high) {
			int[] result = {low, high, arr[low]};
			return result;
		} else {
			
			int mid = low + (high - low)/2;

			int[] leftResult = findMaxSubarray(arr, low, mid);
			int[] rightResult = findMaxSubarray(arr, mid+1, high);
			int[] crossResult = findMaxCrossingSubarray(arr, low, mid, high);
			
			if ((leftResult[2] >= rightResult[2]) && (leftResult[2] >= crossResult[2])){
				return leftResult;
			} else if ((rightResult[2] >= leftResult[2]) && (rightResult[2] >= crossResult[2])) {
				return rightResult;
			} else {
				return crossResult;
			}
		}
	}
	
	private static void printArray (int[] arr){
		
		if (arr == null || arr.length <= 0) return;
		
		int i;
		
		System.out.print("array = { ");
		for (i = 0; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[i] + " } ");
	}
	
	
	public static void main(String[] args) {
		
		int[] arr1 = null;
		int[] arr2 = { };
		int[] arr3 = { 2 };
		int[] arr4 = { -2 };
		int[] arr5 = { 2, 5 };
		int[] arr6 = { 2, -5};
		int[] arr7 = { -2, 5};
		int[] arr8 = { -2, -5};
		int[] arr9 = { 2, 3, 4, 5 };
		int[] arr10 = { -2, -3, -4, -5 };
		int[] arr11 = { -2, 3, 4, 5 };
		int[] arr12 = { -2, -3, 4, 5 };
		int[] arr13 = { -2, 3, -4, 5 };
		int[] arr14 = { 2, -3, 4, -5 };
		int[] arr15 = { -2, -3, -4, 5};
		int[] arr16 = { -2, -3, 4, -5};
		int[] arr17 = { -2, 3, -4, -5};
		int[] arr18 = { 2, -3, -4, -5};
		int[] arr19 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
						11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		int[] arr20 = { -1, -2, -3, -4, -5, -6, -7, -8, -9, -10,
						-11, -12, -13, -14, -15, -16, -17, -18, -19, -20};
		int[] arr21 = { 1, -2, 3, -4, 5, -6, 7, -8, 9, -10,
						11, -12, 13, -14, 15, -16, 17, -18, 19, -20};
		int[] arr22 = { 1, 2, 3, 4, -5, 6, 7, 8, -9, 10,
						11, 12, -13, 14, 15, 16, 17, -18, 19, -20};
		
		
		int maxSubarrayLeftIdx = 0;
		int maxSubarrayRightIdx = 0;
		int maxSum = 0;
		
		int[] result = {maxSubarrayLeftIdx, maxSubarrayRightIdx, maxSum};
		
		System.out.println("array = null 인 경우");
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr1, 0, 0);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		System.out.println("array = { } 인 경우");
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr2);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr2, 0, 0);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr3);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr3);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr3, 0, arr3.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr4);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr4);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr4, 0, arr4.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr5);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr5);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr5, 0, arr5.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();

		
		printArray(arr6);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr6);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr6, 0, arr6.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();

		
		printArray(arr7);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr7);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr7, 0, arr7.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();

		
		printArray(arr8);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr8);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr8, 0, arr8.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr9);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr9);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr9, 0, arr9.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		

		printArray(arr10);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr10);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr10, 0, arr10.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr11);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr11);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr11, 0, arr11.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();

		
		printArray(arr12);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr12);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr12, 0, arr12.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr13);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr13);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr13, 0, arr13.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();

		
		printArray(arr14);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr14);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr14, 0, arr14.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr15);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr15);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr15, 0, arr15.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr16);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr16);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr16, 0, arr16.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr17);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr17);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr17, 0, arr17.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr18);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr18);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr18, 0, arr18.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr19);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr19);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr19, 0, arr19.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr20);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr20);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr20, 0, arr20.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr21);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr21);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr21, 0, arr21.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
		
		printArray(arr22);
		System.out.println("brute-force 알고리즘 결과:");
		result = findMaxSubarrayBruteForce(arr22);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println("recursive 알고리즘 결과:");
		result = findMaxSubarray(arr22, 0, arr22.length-1);
		System.out.println("left idx = " + result[0] + ", right idx = " + result[1] + ", max Sum = " + result[2]);
		System.out.println();
		
	}

}
