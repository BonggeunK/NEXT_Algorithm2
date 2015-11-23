package hw1;

public class InsertionSort {

	// 삽입 정렬 메소드
	void sort(int[] arr){
		
		if (arr.length == 0) return;
		
		for (int i = 1 ; i < arr.length; i++){
			
			int posVal = arr[i];
			int prePos = i - 1;
			
			while ((prePos >= 0) && (arr[prePos] > posVal)){
				arr[prePos + 1] = arr[prePos];
				prePos--;
			}			
			arr[prePos + 1] = posVal;
		}
	}
	
	// 정렬 여부 확인 메소드
	int isSorted(int[] arr){
		
		if (arr.length > 0) {
		
			for (int i = 0; i < arr.length-1; i++){
				
				if (arr[i] > arr[i+1]){
					return 0;
				}
			}
		}		
		return 1;
	}

	// 배열 원소 출력 메소드
	void printArray(int[] arr){
		
		if (arr.length > 0) {
			
			for (int i = 0; i < arr.length; i++){
				System.out.print(arr[i] + ",");
			}
		}
		System.out.println();
	}
	
}
