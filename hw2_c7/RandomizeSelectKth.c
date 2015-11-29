#include <stdio.h>
#include <stdlib.h>
#include <string.h>


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
	
	if (!arr || start > end) return -1;
	
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


// Randomized Partition 함수
int randomizedPartition (int* arr, int start, int end) {
	
	int pivot;
	
	if (!arr || start > end) return -1;
	
	pivot = start + (int)rand()%(end-start+1);	// pivot = random(start, end)
	swap(arr, end, pivot);
	
	return partition(arr, start, end);
}


// Randomize Select Kth 함수 - k번째 작은 원소를 선형 시간에 찾는 함수
int randomizeSelectKth (int* arr, int first, int last, int kth) {
	
	int pivotPos;
	
	if (!arr) return -1;
	
	if (first == last)
		return arr[first];
	
	pivotPos = randomizedPartition(arr, first, last);
	
	if (kth == pivotPos) {
		return arr[kth];
	} else if (kth > pivotPos) {
		return randomizeSelectKth(arr, pivotPos+1, last, kth);
	} else {
		return randomizeSelectKth(arr, first, pivotPos-1, kth);
	}
}


// 10개의 배열을 생성하고 Randomize Select Kth 결과를 확인하는 함수
void testRandomizeSelectKth (void) {
	
	int i, j, k;
	int arr[10][10];
	int selectedValue[10][10];
	
	for (i = 0; i < 10; i++) {		// 10개 배열 생성
		printf("arr%d = [ ", i+1);
		
		for (j = 0; j <= i; j++) {		// 배열 길이 = 1 ~ 10
			
			arr[i][j] = rand()%20;	// 배열 값 = 20 미만의 랜덤 자연수
			
			printf("%d, ", arr[i][j]);
		}
		printf("]\n");
	}
	
	for (i = 0; i < 10; i++) {
		
		for (k = 0; k <= i; k++) {
			selectedValue[i][k] = randomizeSelectKth(&arr[i][0], 0, i, k);
			printf("%dth small element in arr%d = %d\n", k+1, i+1, selectedValue[i][k]);
		}
	}
	
}



int main(int argc, char * argv[]) {
	
	testRandomizeSelectKth();
	
	
}