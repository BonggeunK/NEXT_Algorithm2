package hw2;

public class MergeSortTest {

	// 배열 arr의 [인덱스 p ~ q]과, [인덱스 q+1 ~ r]의 원소들을 오름차순 정렬되게 병합하는 함수. 
	private static int[] merge (int[] arr, int firstBegin, int firstLast, int secondLast){
		
		int i, j, idx;
		
		if (arr == null) return null;
		
		// 분할 배열 생성 후, 좌측, 우측에 arr의 원소들을 복사.
		int[] divisionArr = new int[arr.length];
		
		for (i = firstBegin; i <= firstLast; i++){
			divisionArr[i] = arr[i];
		}
		for (j = firstLast+1; j <= secondLast; j++){
			divisionArr[j] = arr[j];
		}
		
		// 복사된 배열의 좌측 인덱스, 우측 인덱스와 각각 비교하면서 arr의 값을 오름차순으로 복사.
		i = firstBegin;
		j = firstLast+1;
		
		for (idx = firstBegin; idx <= secondLast; idx++){
			
			if (i > firstLast){
				arr[idx] = divisionArr[j++];
			} 
			else if (j > secondLast) {
				arr[idx] = divisionArr[i++];
			} 
			else if (divisionArr[i] < divisionArr[j]) {
				arr[idx] = divisionArr[i++];
			} 
			else {
				arr[idx] = divisionArr[j++];
			}
		}
		return arr;
	}
	
	// 병합 정렬하는 함수.
	private static void mergeSort (int[] arr, int startIdx, int endIdx) {
		
		if (startIdx < endIdx) {
			int mid = startIdx + (endIdx-startIdx)/2;
			
			mergeSort(arr, startIdx, mid);
			mergeSort(arr, mid+1, endIdx);
			merge(arr, startIdx, mid, endIdx);
		}
		
	}
	
	// 배열의 원소들을 출력하는 함수.
	private static void printArr (int[] arr){
		
		if (arr == null) {
			System.out.println();
			return;
		}
		for (int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + ", ");
		}
		System.out.println();
		return;
	}

	// 오름차순 정렬 여부 확인하는 함수.
	private static int isSorted(int[] arr){
		
		if (arr == null || arr.length < 1) return 1;
		
		for (int i = 1; i < arr.length; i++){
			if (arr[i] < arr[i-1]) {
				return 0;
			}
		}
		return 1;
	}
	

	
	public static void main(String[] args) {
		
		int[] arr1 = null;
		int[] arr2 = {};
		int[] arr3 = {3};
		int[] arr4 = {3, 3};
		int[] arr5 = {3, 5};
		int[] arr6 = {5, 3};
		int[] arr7 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
						21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
		int[] arr8 = {30, 29, 28, 27, 26, 25, 24, 23, 22, 21,
						20, 19, 18, 17, 16, 15, 14, 13, 12, 11};
		int[] arr9 = {13, 19, 17, 23, 11, 28, 29, 14, 18, 12,
						30, 15, 21, 16, 20, 25, 22, 24, 27, 26};
		int[] arr10 = {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 
						11, 11, 11, 11, 11, 11, 11, 11, 11, 11};

		System.out.println("null array");
		printArr(arr1);
		mergeSort(arr1, 0, 0);
		printArr(arr1);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr1));
		System.out.println();

		System.out.println("array = {}");
		printArr(arr2);
		mergeSort(arr2, 0, arr2.length-1);
		printArr(arr2);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr2));
		System.out.println();
		
		printArr(arr3);
		mergeSort(arr3, 0, arr3.length-1);
		printArr(arr3);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr3));
		System.out.println();

		printArr(arr4);
		mergeSort(arr4, 0, arr4.length-1);
		printArr(arr4);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr4));
		System.out.println();
		
		printArr(arr5);
		mergeSort(arr5, 0, arr5.length-1);
		printArr(arr5);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr5));
		System.out.println();
		
		printArr(arr6);
		mergeSort(arr6, 0, arr6.length-1);
		printArr(arr6);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr6));
		System.out.println();
		
		printArr(arr7);
		mergeSort(arr7, 0, arr7.length-1);
		printArr(arr7);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr7));
		System.out.println();
		
		printArr(arr8);
		mergeSort(arr8, 0, arr8.length-1);
		printArr(arr8);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr8));
		System.out.println();
		
		printArr(arr9);
		mergeSort(arr9, 0, arr9.length-1);
		printArr(arr9);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr9));
		System.out.println();
		
		printArr(arr10);
		mergeSort(arr10, 0, arr10.length-1);
		printArr(arr10);
		System.out.println("오름차순 정렬 여부(yes = 1, no = 0) : " + isSorted(arr10));
		
	}

}
