package hw2_c2;

public class MaxHeapTest {

	// size 크기의 샘플 max heap 만드는 함수 
	public static MaxHeap makeSampleHeap (int size) {
		
		if (size < 1) {
			System.out.println("Heap size must be a positive integer.");
			return null;
		}
		
		MaxHeap heap = new MaxHeap();
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

	// Max Heap을 콘솔에 출력하는 함수
	public static void printHeap(MaxHeap heap) {
	
		if (heap == null || heap.element == null || heap.element.length <= 1) 
			return;

		System.out.println("Heap = ");
		for (int i = 1; i < heap.element.length; i++){
			System.out.print(heap.element[i]+ ", ");
		}
		System.out.println();
	}
	
	
	
	public static void main(String[] args) {

		MaxHeap h1 = makeSampleHeap(1);
		printHeap(h1);
		h1.buildMaxHeap();
		printHeap(h1);
		
		
		
		System.out.println();
		

		MaxHeap h5 = makeSampleHeap(5);
		printHeap(h5);
		h1.buildMaxHeap();
		printHeap(h5);
		
		System.out.println();
		
		
		
	}

}
