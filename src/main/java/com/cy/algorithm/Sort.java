package com.cy.algorithm;

public class Sort {

    private static int[] tmp = new int[100010];

    public static void main(String[] args) {
        //冒泡排序
       /* int[] nums ={22,11,55,8,7,18,1};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));*/

        //插入排序
        /*int[] nums ={22,11,55,8,7,18,1};
        insertSort(nums);
        System.out.println(Arrays.toString(nums));*/

        //选择排序
        /*int[] nums ={22,11,55,8,7,18,1};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));*/

        //归并排序
        /*Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();*/
        int[] nums = {22,11,55,8,7,18,1};
       /* for (int i = 0; i < n; ++i) {
            nums[i] = sc.nextInt();
        }*/
        mergeSort(nums, 0, nums.length);
        for (int i = 0; i < nums.length; ++i) {
            System.out.printf("%d ", nums[i]);
        }
    }

    /**
     * 冒泡排序
     * @param nums
     */
    public static void bubbleSort(int[] nums){
        boolean hasChange = true;
        for (int i = 0; i < nums.length-1&&hasChange; i++) {
            hasChange = false;
            for (int j = 0; j < nums.length-1; j++) {
                if(nums[j]>nums[j+1]){
                    hasChange = true;
                    int num = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = num;
                }
            }
        }
    }

    /**
     * 插入排序
     * @param nums
     */
    public static void insertSort(int[] nums){
        for (int i = 1,j,n = nums.length; i < n; ++i) {
            int num = nums[i];
            for (j = i-1; j >= 0&&nums[j]>num; --j) {
                    nums[j+1] = nums[j];
            }

            nums[j+1] = num;
        }

      /*  for (int i = 1, j, n = nums.length; i < n; ++i) {
            int num = nums[i];
            for (j = i - 1; j >=0 && nums[j] > num; --j) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = num;
        }*/
    }

    /**
     *选择排序
     * @param nums
     */
    private static void selectionSort(int[] nums) {
        for (int i = 0, n = nums.length; i < n - 1; ++i) {
            int minIndex = i;
            for (int j = i; j < n; ++j) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int t = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = t;
        }
    }


    /**
     * 归并排序
     * @param nums
     * @param left
     * @param right
     */
    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >>> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        for (i = left, j = 0; i <= right; ++i, ++j) {
            nums[i] = tmp[j];
        }
    }
}
