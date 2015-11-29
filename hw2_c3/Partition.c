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


// 파티션이 올바르게 되었는지 확인하는 함수
bool isCorrectlyPartitioned (int* arr, int pivot, int end) {
	
	int i;
	
	for (i = 0; i < pivot; i++) {
		if (arr[i] > arr[pivot]) return false;
	}
	
	for (i = pivot + 1; i <= end; i++) {
		if (arr[pivot] > arr[i]) return false;
	}
	
	return true;
}


// 32개의 배열을 생성하고 partition 결과를 확인하는 함수
void testPartition (void) {
	
	int i, j;
	int arr[32][32];
	int pivot[32];
	
	for (i = 0; i < 32; i++) {		// 32개 배열 생성
		printf("arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {		// 배열 길이 = 1 ~ 32
			
			arr[i][j] = rand()%20;		// 배열의 값 = 20 미만의 랜덤 자연수
			
			printf("%d, ", arr[i][j]);
		}
		printf("]\n");
	}
	
	for (i = 0; i < 32; i++) {
		pivot[i] = partition(&arr[i][0], 0, i);						// partition 수행
		printf("pivot position of arr%d = %d\n", i+1, pivot[i]);
	}
	
	for (i = 0; i < 32; i++) {
		printf("partition result of arr%d = %d\n", i+1, isCorrectlyPartitioned(&arr[i][0], pivot[i], i));
	}
	
	for (i = 0; i < 32; i++) {
		
		printf("partitioned arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {
			printf("%d, ", arr[i][j]);
		}
		printf("]\n");
	}
	
}



int main(int argc, char * argv[]) {
	
	int arrAscend[10] = { 2, 5, 7, 9, 11, 14, 17, 23, 26, 31};
	int arrDescend[10] = { 31, 26, 23, 17, 14, 11, 9, 7, 5, 2};
	int pivot1, pivot2;
	int i;
	
	testPartition();
	
	pivot1 = partition(arrAscend, 0, 9);
	pivot2 = partition(arrDescend, 0, 9);
	
	printf("partition result of arrAscend = %d\n", isCorrectlyPartitioned(arrAscend, pivot1, 9));
	printf("partition result of arrDescend = %d\n", isCorrectlyPartitioned(arrDescend, pivot2, 9));
	
	printf("partitioned arrAsend = [ ");
	for (i = 0; i < 10; i++) {
		printf("%d, ", arrAscend[i]);
	}
	printf("]\n");
	
	printf("partitioned arrDesend = [ ");
	for (i = 0; i < 10; i++) {
		printf("%d, ", arrDescend[i]);
	}
	printf("]\n");
	
}