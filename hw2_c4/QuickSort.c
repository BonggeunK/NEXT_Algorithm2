#include <stdio.h>
#include <limits.h>
#include <stdlib.h>

// bool 타입 정의
typedef enum {
	false,
	true
} bool;

// arr 의 pos 값들을 swap 하는 함수
void swap (int* arr, int swapPos1, int swapPos2) {
	
	int temp;
	
	temp = arr[swapPos1];
	arr[swapPos1] = arr[swapPos2];
	arr[swapPos2] = temp;
}

// arr 를 end 값을 기준으로 partition 하는 함수
int partition (int* arr, int start, int end) {
	
	int pivot;
	int endOfLowBlock;
	int checkPos;
	
	if (!arr) return -1;
	
	pivot = arr[end];
	endOfLowBlock = start-1;
	
	for (checkPos = start; checkPos <= end - 1; checkPos++) {
		
		if (arr[checkPos] <= pivot) {
			
			endOfLowBlock++;
			
			swap(arr, endOfLowBlock, checkPos);
		}
	}
	
	swap(arr, endOfLowBlock + 1, end);
	
	return endOfLowBlock+1;
}



// quickSort 함수
void quickSort(int* arr, int start, int end) {
	
	int pivot;
	
	if (start < end) {
		
		pivot = partition(arr, start, end);
		quickSort(arr, start, pivot-1);
		quickSort(arr, pivot+1, end);
	}
}


// quick Sort 가 올바르게 되었는지 확인하는 함수
bool isSorted (int* arr, int end) {
	
	int i;
	
	for (i = 0; i < end; i++) {
		if (arr[i] > arr[i+1]) return false;
	}
	
	return true;
}


// 16개의 배열을 생성하고 quickSort 결과를 확인하는 함수
void testQuickSort (void) {
	
	int i, j;
	int arr[16][16];
	
	for (i = 0; i < 16; i++) {		// 16개 배열 생성
		printf("arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {		// 배열 길이 = 1 ~ 16
			
			arr[i][j] = rand()%20;		// 배열의 값 = 20 미만의 랜덤 자연수
			
			printf("%d, ", arr[i][j]);
		}
		printf("]\n");
	}
	
	for (i = 0; i < 16; i++) {
		quickSort(&arr[i][0], 0, i);		// quick Sort 수행
	}
	
	for (i = 0; i < 16; i++) {
		printf("sorted result of arr%d = %d\n", i+1, isSorted(&arr[i][0], i));
	}
	
	for (i = 0; i < 16; i++) {
		
		printf("Sorted arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {
			printf("%d, ", arr[i][j]);
		}
		printf("]\n");
	}
	
}



int main(int argc, char * argv[]) {
	
	int arrAscend[10] = { 2, 5, 7, 9, 11, 14, 17, 23, 26, 31};
	int arrDescend[10] = { 31, 26, 23, 17, 14, 11, 9, 7, 5, 2};
	int i;
	
	testQuickSort();
	
	quickSort(arrAscend, 0, 9);
	quickSort(arrDescend, 0, 9);
	
	printf("Sorted result of arrAscend = %d\n", isSorted(arrAscend, 9));
	printf("Sorted result of arrDescend = %d\n", isSorted(arrDescend, 9));
	
	printf("Sorted arrAsend = [ ");
	for (i = 0; i < 10; i++) {
		printf("%d, ", arrAscend[i]);
	}
	printf("]\n");
	
	printf("Sorted arrDesend = [ ");
	for (i = 0; i < 10; i++) {
		printf("%d, ", arrDescend[i]);
	}
	printf("]\n");
	
}