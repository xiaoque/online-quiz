/*
 * @source 
 * @author xiaoque
 * @date 2025.03.22
 */
public class Sort {

	/**
	 * @param arr
	 * @return
	 * Keep swap large number into the end of arr
	 */
	public static int[] bubbleSort(int[] arr) {
		if (arr.length == 0)
			return arr;
		boolean swap = false;
		for (int i = 0; i < arr.length - 1; i++) {
			swap = false;
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j + 1] < arr[j]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					swap = true;
				}
			}
			if (!swap)
				break;
		}
		return arr;
	}

	/**
	 * @param arr
	 * @return
	 * Insert arr[i] into [0, i] at each iteration
	 */
	public static int[] insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int current = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > current) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = current;
		}
		return arr;
	}

	/***
	 * @param arr
	 * @return
	 * merge sort
	 */
	public static int[] mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
		return arr;
	}

	public static void mergeSort(int[] arr, int left, int right) {
		// end condition
		if (left >= right)
			return;

		// divide arr
		int mid = left + (right - left) / 2;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);

		merge(arr, left, mid, right);
	}

	private static void merge(int[] arr, int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int k = 0;
		// use a temp arr to sorted subArray
		int[] temp = new int[right - left + 1];
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}
		while (i <= mid) {
			temp[k++] = arr[i++];
		}
		while (j <= right) {
			temp[k++] = arr[j++];
		}

        System.arraycopy(temp, 0, arr, left, temp.length);
	}


}
