package hw1;

public class InsertionSortTest {
	
	public static void main(String[] args) {
		
		InsertionSort insertSort = new InsertionSort();
		
		// 원소 개수 = 0
		int[] arr0 = new int[0];
		
		// 원소 개수 = 1
		int[] arr1 = {1};
		
		// 원소 개수 = 2
		int[] arr2 = {1, 1};
		int[] arr2i = {1, 3};
		int[] arr2d = {3, 1};
		
		// 원소 개수 = 20
		int[] arr20 = new int[20];
		for (int i = 0; i < 20; i++){
			arr20[i] = 11;
		}
		
		int[] arr20i = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
						21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
		int[] arr20d = {30, 29, 28, 27, 26, 25, 24, 23, 22, 21,
						20, 19, 18, 17, 16, 15, 14, 13, 12, 11};
		int[] arr20r = {28, 11, 14, 18, 26, 20, 24, 23, 13, 21,
						22, 19, 29, 17, 12, 15, 25, 30, 16, 27};
		
		
		// 각각의 배열을 정렬하고, 오름차순 정렬 여부 확인.
		insertSort.sort(arr0);
		System.out.println("원소 개수 0개인 배열의 sort : ");
		insertSort.printArray(arr0);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr0));

		insertSort.sort(arr1);
		System.out.println("배열 {1}의 sort : ");
		insertSort.printArray(arr1);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr1));
		
		insertSort.sort(arr2);
		System.out.println("배열 {1, 1}의 sort : ");
		insertSort.printArray(arr2);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr2));
		
		insertSort.sort(arr2i);
		System.out.println("배열 {1, 3}의 sort : ");
		insertSort.printArray(arr2i);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr2i));
		
		insertSort.sort(arr2d);
		System.out.println("배열 {3, 1}의 sort : ");
		insertSort.printArray(arr2d);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr2d));
		
		insertSort.sort(arr20);
		System.out.println("20개의 모든 원소가 11인 배열의 sort : ");
		insertSort.printArray(arr20);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr20));
		
		insertSort.sort(arr20i);
		System.out.println("11 ~ 30까지 원소를 가진 배열의 sort : ");
		insertSort.printArray(arr20i);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr20i));
		
		insertSort.sort(arr20d);
		System.out.println("30 ~ 11까지 원소를 가진 배열의 sort : ");
		insertSort.printArray(arr20d);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr20d));
		
		insertSort.sort(arr20r);
		System.out.println("11 ~ 30까지 랜덤 배치된 원소를 가진 배열의 sort : ");
		insertSort.printArray(arr20r);
		System.out.println("오름차순 정렬 되었는가? " + insertSort.isSorted(arr20r));
	}

}
