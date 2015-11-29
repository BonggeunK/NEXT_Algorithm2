package hw2_c2;

import java.util.Collections;
import java.util.List;

public class MaxHeap {

	int[] element;	// 테스트 코드에서 설정됨.
	private int size;		// MaxHeap 클래스의 buildMaxHeap 에서 초기화 됨.
	
/*	// Max_Heapify 메소드
	void max_heapify(MaxHeap heap, int index) {
			
		if ((heap == null) || (heap.element == null) || (heap.element.length <= 1) || (index < 1)){
			System.out.println("Cannot max_heapify because one of the format of input heap and input index is inappropriate.");
			return;
		}
		
		int left = 2*index;
		int right = 2*index + 1;
		int largest = index;
		
		if (left <= heap.size && heap.element[left] > heap.element[index]) {
			largest = left;
		}
		if (right <= heap.size && heap.element[right] > heap.element[largest]) {
			largest = right;
		}
		
		if (largest != index) {
			int temp = heap.element[index];
			heap.element[index] = heap.element[largest];
			heap.element[largest] = temp;
				
			max_heapify(heap, largest);
		}
	}
*/	
	
	// Max_Heapify 메소드
	void max_heapify(int index) {
				
		if (index < 1){
			System.out.println("Cannot max_heapify because input index is inappropriate.");
			return;
		}
			
		int left = 2*index;
		int right = 2*index + 1;
		int largest = index;
			
		if (left <= this.size && this.element[left] > this.element[index]) {
			largest = left;
		}
		if (right <= this.size && this.element[right] > this.element[largest]) {
			largest = right;
		}
			
		if (largest != index) {
			Collections.swap(this.element, index, largest);
			int temp = this.element[index];
			this.element[index] = this.element[largest];
			this.element[largest] = temp;
				
			max_heapify(largest);
		}
	}
	
/*	// build max heap 메소드
	void buildMaxHeap(MaxHeap heap) {
		
		heap.size = heap.element.length-1;
		
		for (int i = heap.element.length/2; i >= 1; i--)
			this.max_heapify(heap, i);
	}
*/	
	
	// build max heap 메소드
	void buildMaxHeap() {
			
		this.size = this.element.length-1;
			
		for (int i = this.element.length/2; i >= 1; i--)
			this.max_heapify(i);
	}
	
	
/*	// heap_sort 메소드
	void heapSort(MaxHeap heap) {
		
		this.buildMaxHeap(heap);
		
		for (int i = heap.element.length-1; i >= 2; i--) {
			int temp = heap.element[i];
			heap.element[i] = heap.element[1];
			heap.element[1] = temp;
			
			heap.size--;
			
			this.max_heapify(heap, 1);
		}
	}
*/
	
	
	// heap_sort 메소드
	void heapSort() {
		
		this.buildMaxHeap();
		
		for (int i = this.element.length-1; i >= 2; i--) {
			int temp = this.element[i];
			this.element[i] = this.element[1];
			this.element[1] = temp;
			
			this.size--;
			
			this.max_heapify(1);
		}
	}

	
	
/*	// max Heap 에서 최대값 추출 메소드
	int heapExtractMax (MaxHeap heap) {
		
		if (heap.size < 1) {
			System.out.println("heap size error: heap size = " + heap.size);
			return Integer.MIN_VALUE;
		}
	
		int max = heap.element[1];
		heap.element[1] = heap.size--;
		
		this.max_heapify(heap, 1);
		
		return max;
	}
*/
	
	// max Heap 에서 최대값 추출 메소드
	int heapExtractMax () {
		
		if (this.size < 1) {
			System.out.println("heap size error: heap size = " + this.size);
			return Integer.MIN_VALUE;
		}
	
		int max = this.element[1];
		this.element[1] = this.size--;
		
		this.max_heapify(1);
		
		return max;
	}
	
	
/*	// heap의 노드 값을 key로 증가시키는 메소드
	void heapIncreaseKey (MaxHeap heap, int index, int key) {
		
		if (key < heap.element[index]) {
			System.out.println("key error : key = " + key + ", heap[i] = " + heap.element[index]);
		}
		
		heap.element[index] = key;
		
		while (index > 1 && heap.element[index/2] < heap.element[index]) {
			int temp = heap.element[index];
			heap.element[index] = heap.element[index/2];
			heap.element[index/2] = temp;
			
			index /= 2;
		}
	}
*/	

	// heap의 노드 값을 key로 증가시키는 메소드
	void heapIncreaseKey (int index, int key) {
		
		if (key < this.element[index]) {
			System.out.println("key error : key = " + key + ", heap[i] = " + this.element[index]);
		}
		
		this.element[index] = key;
		
		while (index > 1 && this.element[index/2] < this.element[index]) {
			int temp = this.element[index];
			this.element[index] = this.element[index/2];
			this.element[index/2] = temp;
			
			index /= 2;
		}
	}

	
	
/*	// max heap 에 key 값을 가진 노드 삽입 메소드
	void maxHeapInsert (MaxHeap heap, int key) {
		
		heap.size++;
		
		heap.element[heap.size] = Integer.MIN_VALUE;
		
		this.heapIncreaseKey(heap, heap.size, key);
	}
*/
	
	// max heap 에 key 값을 가진 노드 삽입 메소드
	void maxHeapInsert (int key) {
		
		this.size++;
		
		this.element[this.size] = Integer.MIN_VALUE;
		
		this.heapIncreaseKey(this.size, key);
	}
	
	
	
}
