//Authors: Benjamin Wu, Jason Tan, Byron Chen
public class Team7SortCompetition extends SortCompetition {

    public int challengeOne(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        int median = 0;
        if (arr.length % 2 == 0) {
            median = (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2;
        } else if (arr.length % 2 != 0) {
            median = arr[arr.length / 2];
        }
        return median;
    }

    public int challengeTwo(String[] arr, String query) {
        int a = -1;
        mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(query)) {
                a = i;
            }
        }
        return a;
    }

    public int challengeThree(int[] arr) {
        insertionSort3(arr,0, arr.length-1);
        return getMedian3(arr);
    }

    @Override
    public int challengeFour(int[][] arr) {
        for(int i = 0; i < arr.length; i++){
            insertionSort4(arr[i]);
        }
        quickSort(arr, 0, arr.length-1);
        int[] medians = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            medians[i] = getMedian(arr[i]);
        }
        return getMedian(medians);
    }


    public int challengeFive(Comparable[] arr, Comparable query) {
        insertionSort5(arr);
        return binarySearch5(arr, query);
    }

    public String greeting() {
        return "Hi Mr. Levin! We're Ben, Jason, Byron and we're TEAM 7";
    }
    //----------------------------------------------------------------------------------------------------------------
    //Creates random Int Arr
    public static int[] randIntArr(int Count) {
        int[] array = new int[Count];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * Count + 1);
        }
        return array;
    }
    //Creates random String arr
    public static String[] randStringArr(int num, int length) {
        String[] array = new String[num];
        while (num > 0) {
            int i = 0;
            String s = "";
            while (i < length) {
                char c = (char) ((Math.random() * 26) + 97);
                s = s + c;
                i++;
            }
            num--;
            array[num] = s;
        }
        return array;
    }
    //Prints String Arr
    public static String printStrArr(String[] arr) {
        String r = "";
        for (int i = 0; i < arr.length; i++) {
            r = r + "[" + arr[i] + "]";
        }
        return r;
    }
    //Prints Int Arr
    public static String printArr(int[] arr) {
        String r = "  ";

        for (int num : arr) {
            r = r + " " + num + " ";
        }
        return r;
    }

    public static int getMedian(int[] sortedArray){
        int l = sortedArray.length;
        int mid = l/2;
        if(l == 0){
            return -1;
        }
        if (l % 2 == 0){
            return ((sortedArray[mid]+sortedArray[mid-1])/2);
        }
        else {
            return (sortedArray[mid]);
        }
    }
    //--------------------------------CHALLENGE ONE----------------------------------------------------------------
    public void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }
    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }
    //--------------------------------CHALLENGE TWO----------------------------------------------------------------
    public static void mergeSort(String[] arr) {
        if (arr.length >= 2) {
            String[] left = new String[arr.length / 2];
            String[] right = new String[arr.length - arr.length / 2];
            for (int i = 0; i < left.length; i++) {
                left[i] = arr[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = arr[i + arr.length / 2];
            }
            mergeSort(left);
            mergeSort(right);
            merge(arr, left, right);
        }
    }

    public static void merge(String[] arr, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < arr.length; i++) {
            if (b >= right.length || (a < left.length && left[a].compareToIgnoreCase(right[b]) < 0)) {
                arr[i] = left[a];
                a++;
            } else {
                arr[i] = right[b];
                b++;
            }
        }
    }
    //--------------------------------CHALLENGE THREE----------------------------------------------------------------
    public static void insertionSort3(int [] arr, int left, int right)
    {
        for (int i =left; i < right +1; i++)
        {
            for(int a = i; a >0; a--)
            {
                if (arr[a]<arr[a-1])
                {
                    swap3(arr,a,a-1);
                }
                else a = 0;
            }
        }
    }

    public static void swap3 ( int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static int getMedian3 (int [] arr)
    {
        int a = arr.length/2;
        if (arr.length % 2 != 0) {
            return (arr[(a) + 1 ]);
        } else {
            return (arr [a] + arr [a-1 ] ) / 2;
        }
    }
    //--------------------------------CHALLENGE FOUR----------------------------------------------------------------
    public static void quickSort(int[][] arr, int low, int high)
    {
        if (low < high)
        {
            int split = quickSortHelper(arr, low, high);

            quickSort(arr, low, split - 1);  // Before pi
            quickSort(arr, split +1, high); // After pi
        }
    }

    public static int quickSortHelper(int[][] arr, int low, int high){
        int[] top = arr[high];
        int i = low -1;

        for (int j = low; j < high; j++){
            if(getMedian(arr[j]) <= getMedian(top)){
                i++;
                swap4(arr, i ,j);
            }
        }
        i++;
        swap4(arr, i, high);
        return i;
    }

    public static void insertionSort4(int[] arr){
        for (int i = 1; i < arr.length; i++){
            for (int j = i; j > 0; j--){
                if(arr[j-1] > arr[j]){
                    swap4(arr, j, j-1);
                }
                else {
                    break;
                }
            }
        }
    }

    public static void swap4(int[][] arr, int a, int b){
        int[] temp = arr[a];
        arr[a]=arr[b];
        arr[b] = temp;
    }

    public static void swap4(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a]=arr[b];
        arr[b] = temp;
    }
    //--------------------------------CHALLENGE FIVE----------------------------------------------------------------
    public static void insertionSort5(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1].compareTo(arr[j]) > 0) {
                    swap5(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static int binarySearch5(Comparable[] arr, Comparable x) {
        int a = 0;
        int b = arr.length - 1;
        while (a <= b) {
            int d = a + (b - a) / 2;
            int c = x.compareTo(arr[d]);
            if (c == 0)
                return d;
            if (c > 0)
                a = d + 1;
            else
                b = d - 1;
        }
        return -1;
    }
    public static void swap5(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

//