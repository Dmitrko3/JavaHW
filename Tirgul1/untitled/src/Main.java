import java.util.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    {
        System.out.println("=== Sorting with Bubble Sort ===");
        // 1. קליטת מערך מהמשתמש
        int[] array1 = readArray();
        // 2. מיון באמצעות Bubble Sort
        bubbleSort(array1);
        // 3. הדפסה
        printArray(array1);

        System.out.println("\n=== Sorting with Merge Sort ===");
        // 1. קליטת מערך מהמשתמש
        int[] array2 = readArray();
        // 2. מיון באמצעות Merge Sort
        mergeSort(array2, 0, array2.length - 1);
        // 3. הדפסה
        printArray(array2);
    }
}

public int[] readArray(){
    int size=0;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the size of the array");
    size=scanner.nextInt();
    //input by user for size
    int[] array = new int[size];
    System.out.println("Enter the elements of the array");
    for(int i=0; i<size; i++){
        array[i] = scanner.nextInt();
    }
    return array;

}

public void printArray(int[] array){
    System.out.println("The elements of the array are:");
    for(int i=0; i<array.length; i++){
        System.out.print(array[i] + " ");
    }
    System.out.println();}

public int[] bubbleSort(int[] array){
    for(int i=0; i<array.length; i++){
        for(int j=0; j<array.length-i-1; j++){
            if(array[j]>array[j+1]){
                int temp=array[j];
                array[j]=array[j+1];
                array[j+1]=temp;
            }
        }
    }
    return array;
}

public void merge(int arr[], int left, int middle, int right)
{
    // Find sizes of two subarrays to be merged
    int n1 = middle - left + 1;
    int n2 = right - middle;

    // Create temp arrays
    int Left[] = new int[n1];
    int Right[] = new int[n2];

    // Copy data to temp arrays
    for (int i = 0; i < n1; ++i)
        Left[i] = arr[left + i];
    for (int j = 0; j < n2; ++j)
        Right[j] = arr[middle + 1 + j];

    // Merge the temp arrays

    // Initial indices of first and second subarrays
    int i = 0, j = 0;

    // Initial index of merged subarray array
    int k = left;
    while (i < n1 && j < n2) {
        if (Left[i] <= Right[j]) {
            arr[k] = Left[i];
            i++;
        }
        else {
            arr[k] = Right[j];
            j++;
        }
        k++;
    }

    // Copy remaining elements of L[] if any
    while (i < n1) {
        arr[k] = Left[i];
        arr[k] = Left[i];
        i++;
        k++;
    }

    // Copy remaining elements of R[] if any
    while (j < n2) {
        arr[k] = Right[j];
        j++;
        k++;
    }
}

// Main function that sorts arr[l..r] using
// merge()
public void mergeSort(int arr[], int left, int right)
{
    if (left < right)
    {
        // Find the middle point
        int middle = left + (right - left) / 2;
        // Sort first and second halves
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        // Merge the sorted halves
        merge(arr, left, middle, right);
    }
}
