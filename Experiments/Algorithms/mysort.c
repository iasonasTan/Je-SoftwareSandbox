// MY SORT .C

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int isSorted (int* arr, int n)
{
    for (int i=0; i<n-1; i++) {
        if (arr[i]>arr[i+1]) {
            return 0;
        }
    }
    return 1;
}

int getMax (int* arr, int n, int p)
{
    int out=INT_MIN;
    if (p>0) {
        for (int i=0; i<n; i++) {
            if (arr[i]>out&&arr[i]>0) {
                out=arr[i];
            }
        }
    } else {
        for (int i=0; i<n; i++) {
            int cur=abs(arr[i]);
            if (out>cur&&arr[i]<0) {
                out=cur;
            }
        }
    }
    return out;
}

void printArr (int* arr, int n)
{
    for (int i=0; i<n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void sort (int* arr, int n)
{
    const int max_pos=getMax(arr, n, 1);
    const int max_neg=getMax(arr, n, -1);
    int map1[max_pos];
    int map2[max_neg];

    // set maps to 0
    for (int i=0; i<max_pos; i++)
        map1[i]=0;
    for (int i=0; i<max_neg; i++)
        map2[i]=0;

    // generate maps
    for (int i=0; i<n; i++)
        if (arr[i]>0)
            map1[arr[i]]++;
        else if (arr[i]<0)
            map2[abs(arr[i])]++;

    // extract maps to input array
    int arr_idx=0;
    for (int i=-max_neg; i<0; i++) {
        while (map2[abs(i)]>0) {
            arr[arr_idx]=i;
            arr_idx++;
            map2[abs(i)]--;
        }
    }
    for (int i=0; i<max_pos; i++) {
        while (map1[i]>0) {
            arr[arr_idx]=i;
            arr_idx++;
            map1[i]--;
        }
    }
}

int main ()
{
    const int n=8;
    int array[] = {32,5,3,1,23,5,-100,-5,0,23,76};

    sort(array, n);
    printf("%d\n", isSorted(array, n));

    return 0;
}