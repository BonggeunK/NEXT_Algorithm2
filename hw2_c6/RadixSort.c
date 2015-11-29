#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

// bool 타입 정의
typedef enum {
	false,
	true
} bool;


// Counting Sort 변형 함수
void countingSort(int* arr, int size, int k, int digit) {
	
	int i;
	int tempArr[k];
	int digitArr[size]; // arr 에서, digit 자리에 해당하는 숫자들만 저장한 배열
	int toBeCopiedArray[size];
	
	for (i = 0; i < size; i++) {
		digitArr[i] = (int)(arr[i]/pow(10, digit-1))%10;	// digit번째 자리의 숫자를 저장
	}
	
	memset(tempArr, 0, sizeof(tempArr));
	
	for (i = 0; i < size; i++) {
		tempArr[digitArr[i]]++;
	}
	
	for (i = 1; i < k; i++) {
		tempArr[i] += tempArr[i-1];
	}
	
	for (i = size - 1; i >= 0; i--) {
		toBeCopiedArray[--tempArr[digitArr[i]]] = arr[i];	// stable 순서로 다른 배열(카피 배열)에 저장
	}
	
	for (i = 0; i <= size-1; i++) {
		arr[i] = toBeCopiedArray[i];	// 정렬된 카피 배열의 값을 순서대로 원래 배열에 저장
	}
}


// Radix Sort 함수
void radixSort (int* arr, int size, int digit) {
	
	int i;
	
	for (i = 1; i <= digit; i++) {
		countingSort(arr, size, 10, i);
	}
}



// radix Sort 가 올바르게 되었는지 확인하는 함수
bool isSorted (int* arr, int end) {
	
	int i;
	
	for (i = 0; i < end; i++) {
		if (arr[i] > arr[i+1]) return false;
	}
	
	return true;
}


// 10개의 배열을 생성하고 radix Sort 결과를 확인하는 함수
void testRadixSort (void) {
	
	int i, j;
	int arr[10][10];
	int hundreds, tens, ones;
	
	for (i = 0; i < 10; i++) {		// 10개 배열 생성
		printf("arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {		// 배열 길이 = 1 ~ 10
			
			hundreds = rand()%10;
			if (hundreds == 0) hundreds++;
			
			tens = rand()%10;
			ones = rand()%10;
			
			arr[i][j] = 100*hundreds + 10*tens + ones;	// 배열 값 = 3자리 수의 랜덤 자연수
			
			printf("%d, ", arr[i][j]);
		}
		printf("]\n");
	}
	
	for (i = 0; i < 10; i++) {
		radixSort(&arr[i][0], i+1, 3);	// radix sort 수행
	}
	
	for (i = 0; i < 10; i++) {
		printf("sorted result of arr%d = %d\n", i+1, isSorted(&arr[i][0], i));
	}
	
	for (i = 0; i < 10; i++) {
		
		printf("Sorted arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {
			printf("%d, ", arr[i][j]);
		}
		printf("]\n");
	}
	
}



int main(int argc, char * argv[]) {
	
	testRadixSort();
	
	
}