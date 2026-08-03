// INSERTION SORT .JS

function sort (arr) {
    for (let i=1; i<arr.length; i++) {
        for (let j=i; j>=1; j--) {
            if (arr[j]<arr[j-1]) {
                // swap
                const temp=arr[j-1];
                arr[j-1]=arr[j];
                arr[j]=temp;
            }
        }
    }
    return arr;
}

const array = [40,8,54,45,32,7,2,13,98];
console.log(array);
console.log(sort(array));