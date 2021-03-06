package com.raj.ds;

public class MinHeap {
	int heapsize;
	int arr[];

	public void min_heapify(int i) {
		int smallest, l, r;
		l = getLeft(i);
		r = getRight(i);
		if (l != -1 && arr[l] < arr[i]) {
			smallest = l;
		} else {
			smallest = i;
		}
		if (r != -1 && arr[r] < arr[smallest]) {
			smallest = r;
		}

		if (smallest != i) {
			// swap largest and i
			int temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;
			min_heapify(smallest);
		}
	}

	public void build_heap(int a[]) {
		arr = a;
		this.heapsize = arr.length;
		for (int i = getLeavesFrom() - 1; i >= 0; i--) {
			min_heapify(i);
		}
	}

	public void print_heap() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public int extract_min() {
		if (this.heapsize < 1)
			return -1;

		int max = arr[0];
		arr[0] = arr[this.heapsize - 1];
		resizeHeap(this.heapsize, this.heapsize - 1);
		min_heapify(0);
		return max;
	}

	public void decrease_key(int i, int key) {
		if (i < 0 && i > this.heapsize - 1) {
			return;
		}
		if (arr[i] < key) {
			return;
		}

		int parent = getParent(i);
		if (parent < 0)
			return;
		arr[i] = key;

		while (i > 0 && arr[i] < arr[parent]) {
			int temp = arr[parent];
			arr[parent] = arr[i];
			arr[i] = temp;

			i = parent;
			parent = getParent(i);
			if (parent < 0)
				return;
		}
	}

	public void insert_key(int key) {
		resizeHeap(this.heapsize, this.heapsize + 1);
		arr[this.heapsize - 1] = Integer.MAX_VALUE;
		decrease_key(this.heapsize - 1, key);
	}

	public void resizeHeap(int oldsize, int newsize) {
		int[] na = null;

		na = new int[newsize];
		this.heapsize = newsize;

		if (newsize > oldsize) {
			for (int i = 0; i < oldsize; i++) {
				na[i] = arr[i];
			}
		} else {
			for (int i = 0; i < newsize; i++) {
				na[i] = arr[i];
			}
		}

		arr = null;
		arr = na;
	}

	public int getLeft(int i) {
		int j = 2 * i + 1;
		if (j < this.heapsize) {
			return j;
		}
		return -1;
	}

	public int getRight(int i) {
		int j = 2 * i + 2;
		if (j < this.heapsize) {
			return j;
		}
		return -1;
	}

	public int getParent(int i) {
		int j = (i - 1) / 2;
		if (j >= 0) {
			return j;
		}
		return -1;
	}

	public int getLeavesFrom() {
		int j = this.heapsize / 2;
		if (j >= 0) {
			return j;
		}
		return -1;
	}

	public void heap_sort() {
		int temp;
		for (int i = this.heapsize - 1; i > 0; i--) {
			temp = arr[0];
			arr[0] = arr[this.heapsize - 1];
			arr[this.heapsize - 1] = temp;
			min_heapify(i);
		}
	}
	

	public static void main(String[] args) {
		MinHeap obj = new MinHeap();
//		int b[] = { 1, 14, 10, 8, 7, 9, 3, 2, 4, 6 };
//		obj.build_heap(b);
//		obj.print_heap();
//	
//		obj.insert_key(0);
//		obj.print_heap();
		int freq[] = { 5, 9, 12, 13, 16, 45 };
		obj.build_heap(freq);
		obj.print_heap();
		
//		obj.heap_sort();
//		obj.print_heap();

	}

}
