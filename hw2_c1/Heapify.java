package hw2_c1;

public class Heapify {

	// Max_Heapify 함수
	public static void max_heapify(Heap heap, int index) {
		
		if ((heap == null) || (heap.element == null) || (heap.element.length <= 1) || (index < 1)){
			System.out.println("Cannot max_heapify because one of the format of input heap and input index is inappropriate.");
			return;
		}

		int left = 2*index;
		int right = 2*index + 1;
		int largest = index;
		
		if (left <= heap.element.length-1 && heap.element[left] > heap.element[index]) {
			largest = left;
		}
		if (right <= heap.element.length-1 && heap.element[right] > heap.element[largest]) {
			largest = right;
		}
		
		if (largest != index) {
			int temp = heap.element[index];
			heap.element[index] = heap.element[largest];
			heap.element[largest] = temp;
			
			max_heapify(heap, largest);
		}
	}
	
	// size를 입력 받아, Max Heap 테스트용 heap을 만들어 반환하는 함수.
	// 테스트용 heap은 루트 노드를 제외한 왼쪽 서브트리 / 오른쪽 서브트리가 각각 max heap 특성을 만족함.
	public static Heap makeSampleHeap (int size) {
		
		if (size < 1) {
			System.out.println("Heap size must be a positive integer.");
			return null;
		}
		
		Heap heap = new Heap();
		heap.element = new int[size+1];

		heap.element[0] = Integer.MAX_VALUE; // heap.element 배열의 맨 앞 = 빈 칸임.
		heap.element[1] = size*2;			// 루트 노드의 값

		if (size > 1) {
			for (int i = 2; i <= size; i++){
				heap.element[i] = heap.element[i/2] - (i+1)/2;
			}
			heap.element[1] = 0;	// 루트 노드의 값을 max heap 특성에 어긋나도록 갱신함.
		}
		return heap;
	}
	
	// Max Heap 인지 여부를 확인하는 함수
	public static boolean isMaxHeap(Heap heap) {
		
		if (heap == null || heap.element == null || heap.element.length <= 1) 
			return false;
		
		for (int i = 1; i < heap.element.length; i++){
			
			if (heap.element[i] > heap.element[i/2])
				return false;
		}
		
		return true;
	}
	
	// Max Heap을 콘솔에 출력하는 함수
	public static void printHeap(Heap heap) {
	
		if (heap == null || heap.element == null || heap.element.length <= 1) 
			return;

		System.out.println("Heap = ");
		for (int i = 1; i < heap.element.length; i++){
			System.out.print(heap.element[i]+ ", ");
		}
		System.out.println();
	}
	
	// heapify 여부를 확인하는 함수
	public static void testHeapify(Heap heap) {
		
		if (heap == null || heap.element == null || heap.element.length <= 1) {
			System.out.println("Heap must have at least one element.");
			return;
		}
		
		if (isMaxHeap(heap)){
			printHeap(heap);
		} else {
			printHeap(heap);
			System.out.println("The input array is not a max heap.");
		}

	}
	
	
	public static void main(String[] args) {
		
		Heap h0 = makeSampleHeap(0);
		testHeapify(h0);
		max_heapify(h0, 0);
		testHeapify(h0);
		System.out.println();
		
		Heap h1 = makeSampleHeap(1);
		testHeapify(h1);
		max_heapify(h1, 1);
		testHeapify(h1);
		System.out.println();
		
		Heap h2 = makeSampleHeap(2);
		testHeapify(h2);
		max_heapify(h2, 1);
		testHeapify(h2);
		System.out.println();
		
		Heap h3 = makeSampleHeap(3);
		testHeapify(h3);
		max_heapify(h3, 1);
		testHeapify(h3);
		System.out.println();
		
		Heap h4 = makeSampleHeap(4);
		testHeapify(h4);
		max_heapify(h4, 1);
		testHeapify(h4);
		System.out.println();
		
		Heap h10 = makeSampleHeap(10);
		testHeapify(h10);
		max_heapify(h10, 1);
		testHeapify(h10);
		System.out.println();
		
		
	}

	
	
	
	
}
