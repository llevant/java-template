package edu.spbu.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class IntSort {

  // Слияние двух подмассивов arr[l..m] и arr[m+1..r] массива arr[]

  static void merge(int arr[], int l, int m, int r)
  {
    int n1 = m - l + 1;
    int n2 = r - m;

        /* временные массивы */
    int L[] = new int [n1];
    int R[] = new int [n2];

    for (int i=0; i<n1; ++i)
      L[i] = arr[l + i];
    for (int j=0; j<n2; ++j)
      R[j] = arr[m + 1+ j];


        /* сливаем временные массивы */
    int i = 0, j = 0;
    int k = l;
    while (i < n1 && j < n2)
    {
      if (L[i] <= R[j])
      {
        arr[k] = L[i];
        i++;
      }
      else
      {
        arr[k] = R[j];
        j++;
      }
      k++;
    }

        /* копир оставшиеся элементы L[] */
    while (i < n1)
    {
      arr[k] = L[i];
      i++;
      k++;
    }

        /* копир оставшиеся элементы R[] */
    while (j < n2)
    {
      arr[k] = R[j];
      j++;
      k++;
    }
  }

  // рекурс функция сортировки массива arr[l..r]
  static void sort(int arr[], int l, int r)
  {
    if (l < r)
    {
      int m = (l+r)/2;
      // сортируем половинки
      sort(arr, l, m);
      sort(arr ,m+1, r);

      // и сливаем их
      merge(arr, l, m, r);
    }
  }

  // вывод
  static void printArray(int arr[])
  {
    int n = arr.length;
    for (int i=0; i<n; ++i)
      System.out.print(arr[i] + " ");
    System.out.println();
  }

  public static void sort (int array[]) {

    sort(array, 0, array.length-1);

  }

  public static void sort (List<Integer> list) {
    Collections.sort(list);
  }
}
