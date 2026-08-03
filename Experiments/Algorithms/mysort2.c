// C

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

void sort(int* arr, int n) {
    int map_neg[];
    int map_pos[];

    int map_n_pos=INT_MIN;
    int map_n_neg=INT_MAX;

    for(int i=0; i<n; i++) {
        if(arr[i]>map_n_pos) {
            map_n_pos=arr[i];
        }
        if (arr[i]<map_n_neg) {
            ap_n_neg=arr[i];
        }
    }
}

int main()
{
    int arr[]={1,6,3,0,-23,-1,6,0};
    int n=sizeof(arr)/sizeof(int);

    sort(arr, n);

    for(int i=0; i<n; i++) {
        printf("%d, ", arr[i]);
    }

    return 0;
}