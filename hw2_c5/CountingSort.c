#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// bool 타입 정의
typedef enum {
	false,
	true
} bool;


// Counting Sort 함수
void countingSort(int* arr1, int* arr2, int size, int maxValue) {
	
	int i;
	int tempArr[maxValue+1];
	
	memset(tempArr, 0, sizeof(tempArr));
	
	for (i = 0; i < size; i++) {
		tempArr[arr1[i]]++;
	}
	
	for (i = 1; i <= maxValue; i++) {
		tempArr[i] += tempArr[i-1];
	}
	
	for (i = size - 1; i >= 0; i--) {
		arr2[--tempArr[arr1[i]]] = arr1[i];
	}
}


// counting Sort 가 올바르게 되었는지 확인하는 함수
bool isSorted (int* arr, int end) {
	
	int i;
	
	for (i = 0; i < end; i++) {
		if (arr[i] > arr[i+1]) return false;
	}
	
	return true;
}


// 10개의 배열을 생성하고 counting Sort 결과를 확인하는 함수
void testCountingSort (void) {
	
	int i, j;
	int arr[10][10];
	int result[10][10];
	
	for (i = 0; i < 10; i++) {		// 10개 배열 생성
		printf("arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {		// 배열 길이 = 1 ~ 10
			
			arr[i][j] = rand()%30;		// 배열의 값 = 30 미만의 랜덤 자연수
			
			printf("%d, ", arr[i][j]);
		}
		printf("]\n");
	}
	
	for (i = 0; i < 10; i++) {
		
		countingSort(&arr[i][0], &result[i][0], i+1, 30);	// counting sort 수행
	}
	
	for (i = 0; i < 10; i++) {
		printf("sorted result of arr%d = %d\n", i+1, isSorted(&result[i][0], i));
	}
	
	for (i = 0; i < 10; i++) {
		
		printf("Sorted arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {
			printf("%d, ", result[i][j]);
		}
		printf("]\n");
	}
	
}



int main(int argc, char * argv[]) {
	
	testCountingSort();
	
	
}
