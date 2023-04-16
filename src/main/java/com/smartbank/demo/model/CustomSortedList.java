package com.smartbank.demo.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * This class may not be as effective as it includes resize
 */
public class CustomSortedList<T extends Comparable<T>> {
    private T[] arr;
    private int size;

    public CustomSortedList() {
        arr = (T[]) new Comparable[10];
        size = 0;
    }

    public void add(T val) {
        if (size == arr.length) {
            resize();
        }

        int i = size - 1;
        while (i >= 0 && arr[i].compareTo(val) > 0) {
            arr[i + 1] = arr[i];
            i--;
        }
        arr[i + 1] = val;
        size++;
    }

    private void resize() {
        T[] newArr = (T[]) new Comparable[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }

    public boolean exists(T val) {
        return search(val, 0, size - 1);
    }

    private boolean search(T val, int left, int right) {
        if (left > right) {
            return false;
        }

        int mid = left + (right - left) / 2;
        int compareResult = arr[mid].compareTo(val);
        if (compareResult == 0) {
            return true;
        } else if (compareResult < 0) {
            return search(val, mid + 1, right);
        } else {
            return search(val, left, mid - 1);
        }
    }

    public List<T> getAll() {
        return Arrays.asList(arr);
    }

    @Override
    public String toString() {
        StringBuilder asText = new StringBuilder();
        for (int i = 0; i < size; i++) {
            asText.append(arr[i] + "\n");
        }
        return asText.toString();
    }
}