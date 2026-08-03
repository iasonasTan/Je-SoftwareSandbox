#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void print(int*, int);
void sort(int*, int);
void swap(int*, int, int);
void randomize(int*, int);

void randomize(int* arr, int n)
{
    for (int i=0; i<n; i++)
        arr[i] = (rand()%n)+1;
}

void swap (int *arr, int n1_x, int n2_x)
{
    int temp = arr[n1_x];
    arr[n1_x] = arr[n2_x];
    arr[n2_x] = temp;
}

void sort (int *arr, int n)
{
    for (int i=0; i<n; i++)
    {
        print(arr, n);

        int minIdx = i;
        for (int j=i+1; j<n; j++)
            if (arr[minIdx] > arr[j])
                minIdx = j;

        swap(arr, minIdx, i);
    }
}

void print (int *arr, int n)
{
    for (int i=0; i<n; i++)
    {
        printf("%d", arr[i]);

        if (i!=n-1)
            printf(", ");
    }
    printf("\n");
}

int main ()
{
    srand(time(NULL));

    int n = 35;
    int nums[n];
    randomize(nums, n);
    
    sort(nums, n);
    print(nums, n);

    return 0;
}
