#include <stdio.h>
#include <limits.h>
#include <string.h>
#include <stdlib.h>

// heap 구조체 정의
typedef struct heap {
	int* element;
	int size;
} heap_t;

// Max_Heapify 함수
void max_heapify(heap_t* heap, int index) {
	
	int temp = 0;
	
	if ((heap == NULL) || (heap->element == NULL) || (index < 1)){
		printf("heap pointer error or index error.\n");
		return;
	}
	
	int left = 2*index;
	int right = 2*index + 1;
	int largest = index;
	
	if (left <= heap->size && heap->element[left] > heap->element[index]) {
		largest = left;
	}
	if (right <= heap->size && heap->element[right] > heap->element[largest]) {
		largest = right;
	}
	
	if (largest != index) {
		temp = heap->element[index];
		heap->element[index] = heap->element[largest];
		heap->element[largest] = temp;
		
		max_heapify(heap, largest);
	}
}

// build Max Heap 함수
void buildMaxHeap(heap_t* heap, int length) {
	
	if (!heap) return;
	
	heap->size = length;
	
	for (int i = length/2; i >= 1; i--)
		max_heapify(heap, i);
}

// heap sort 함수
void heapSort(heap_t* heap, int length) {
	
	int temp = 0;
	int i;
	
	if (!heap) return;
	
	buildMaxHeap(heap, length);
	
	for (i = length; i >= 2; i--) {
		temp = heap->element[i];
		heap->element[i] = heap->element[1];
		heap->element[1] = temp;
		
		heap->size--;
		
		max_heapify(heap, 1);
	}
}


// max Heap 에서 최대값 추출 함수
int heapExtractMax (heap_t* heap) {
 
	int max = 0;
	
	if (heap->size < 1) {
		printf("heap size error.\n");
		return INT_MAX;
	}
	
	max = heap->element[1];
	heap->element[1] = heap->size;
	heap->size--;
 
	max_heapify(heap, 1);
 
	return max;
}

// heap의 노드 값을 key로 증가시키는 함수
void heapIncreaseKey (heap_t* heap, int index, int key) {
	
	int temp = 0;
	
	if (key < heap->element[index]) {
		printf("key error.\n");
	}
	
	heap->element[index] = key;
	
	while (index > 1 && heap->element[index/2] < heap->element[index]) {
		temp = heap->element[index];
		heap->element[index] = heap->element[index/2];
		heap->element[index/2] = temp;
		
		index /= 2;
	}
}

// max heap 에 key 값을 가진 노드 삽입 함수
void maxHeapInsert (heap_t* heap, int key) {
	
	heap->size++;
	
	heap->element[heap->size] = INT_MIN;
	
	heapIncreaseKey(heap, heap->size, key);
}



// size 크기의 샘플 max heap 만드는 함수
heap_t* makeSampleHeap (int size) {
	
	if (size < 1) {
		printf("Heap size must be a positive integer.\n");
		return NULL;
	}
	
	heap_t* heap = (heap_t*) malloc(sizeof(heap_t));
	if (!heap) return NULL;
	heap->element = (int *) malloc(sizeof(int) * (size+1));
	if (!heap->element) {
		free(heap);
		return NULL;
	}
	
	heap->size = size;
	
	heap->element[0] = INT_MAX; // heap->element 배열의 맨 앞 = 빈 칸임.
	heap->element[1] = size*2;			// 루트 노드의 값
	
	if (size > 1) {
		for (int i = 2; i <= size; i++){
			heap->element[i] = heap->element[i/2] - (i+1)/2;
		}
		heap->element[1] = 0;	// 루트 노드의 값을 max heap 특성에 어긋나도록 갱신함.
	}
	return heap;
}

// heap 을 free 하는 함수
void releaseHeap (heap_t* heap) {
	
	if (heap) {
		if (heap->element) {
			free(heap->element);
		}
		free(heap);
	}
}


// Max Heap을 콘솔에 출력하는 함수
void printHeap(heap_t* heap, int length) {
	
	int i;
	
	if (heap == NULL || heap->element == NULL) return;
	
	printf("Heap = ");
	for (i = 1; i <= length; i++){
		printf("%d, ", heap->element[i]);
	}
	printf("\n");
}



int main(int argc, char * argv[]) {
	
	heap_t* h2 = makeSampleHeap(2);
	printHeap(h2, 2);
	buildMaxHeap(h2, 2);
	printf("< After Heapify >\n");
	printHeap(h2, 2);
	heapSort(h2, 2);
	printf("< After Heap Sort >\n");
	printHeap(h2, 2);
	releaseHeap(h2);
	printf("\n");
	
	heap_t* h3 = makeSampleHeap(3);
	printHeap(h3, 3);
	buildMaxHeap(h3, 3);
	printf("< After Heapify >\n");
	printHeap(h3, 3);
	printf("Extract Max = %d\n", heapExtractMax(h3));
	printf("< After Extracting Max >\n");
	printHeap(h3, 2);
	maxHeapInsert(h3, 8);
	printf("After Inserting key = %d >\n", 8);
	printHeap(h3, 3);
	releaseHeap(h3);
	printf("\n");
	
	heap_t* h4 = makeSampleHeap(4);
	printHeap(h4, 4);
	buildMaxHeap(h4, 4);
	printf("< After Heapify >\n");
	printHeap(h4, 4);
	heapSort(h4, 4);
	printf("< After Heap Sort >\n");
	printHeap(h4, 4);
	releaseHeap(h4);
	printf("\n");
	
	heap_t* h5 = makeSampleHeap(5);
	printHeap(h5, 5);
	buildMaxHeap(h5, 5);
	printf("< After Heapify >\n");
	printHeap(h5, 5);
	printf("Extract Max = %d\n", heapExtractMax(h5));
	printf("< After Extracting Max >\n");
	printHeap(h5, 4);
	maxHeapInsert(h5, 12);
	printf("< After Inserting key = %d >\n", 12);
	printHeap(h5, 5);
	releaseHeap(h5);
	printf("\n");
	
}


