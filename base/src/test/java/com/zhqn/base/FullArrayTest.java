package com.zhqn.base;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * FileName: FullArrayTest
 * Author:   zhouquan3
 * Date:     2022/10/18 16:24
 * Description:
 */
public class FullArrayTest {

    private void fullArray(int arr[], Stack<Integer> stack, int curIndex, int maxNum) {
        if (curIndex >= maxNum) {
            for (Integer item : stack) {
                System.out.print(item + ",");
            }
            System.out.println();
            return;
        }
        for (int item : arr) {
            stack.push(item);
            fullArray(arr, stack, curIndex + 1, maxNum);
            stack.pop();
        }
    }

    private void fullArray1(int arr[], Stack<Integer> stack, int curIndex, int maxNum) {
        if (curIndex >= maxNum) {
            for (Integer item : stack) {
                System.out.print(item + ",");
            }
            System.out.println();
            return;
        }
        for (int item : arr) {
            if (!stack.contains(item)) {
                stack.push(item);
                fullArray1(arr, stack, curIndex + 1, maxNum);
                stack.pop();
            }

        }
    }

    private void fullArray2(int arr[], Stack<Integer> stack, int curIndex, int maxNum, int index) {
        if (curIndex >= maxNum) {
            if (stack.size() == maxNum) {
                System.out.println(stack);
            }
            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + ",");
            }
            System.out.println();
            return;
        }
        for (int item : arr) {
            if (!stack.contains(item)) {
                stack.push(item);
                fullArray2(arr, stack, curIndex + 1, maxNum, index);
            }
        }
    }

    @Test
    public void testFullArray() {
        Stack<Integer> stack = new Stack<>();
        int arr[] = {1,2,3};
        fullArray2(arr, stack, 0, 3, 0);


    }

    void quicksort(int arr[]) {
        quicksort(arr, 0, arr.length -1);
    }

    private void quicksort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        //基准位置
        int pivotIndex = partionIndex(arr, start, end);

        printArray(arr);

        quicksort(arr, start, pivotIndex - 1);
        quicksort(arr, pivotIndex + 1, end);
    }

    private int partionIndex(int[] arr, int left, int right) {
        int midIndex = (left + right) / 2;
        swap(arr, midIndex, left);
        int l = left + 1;

        while (l < right) {
            while (l < right && arr[right] > arr[l]) --right;
            while (l < right && arr[l] < arr[left]) ++l;
            if (l < right) {
                swap(arr, l, right);
            }

        }
        swap(arr, left, l);
        return l;
    }


    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void printArray(int[] arr) {
        for (int item : arr) {
            System.out.print(item + ",");
        }
        System.out.println();
    }

    @Test
    public void testQuickSort() {
        int arr[] = new int[]{1,2,3, 6, 5, 1, 7, 2, 10, 9};
        quicksort(arr);
        printArray(arr);
    }

}
