// BUBBLE SORT .JS
const isSorted = (arr=[]) => {
    let i=0;
    while (i<arr.length-1) {
        if (arr[i]>arr[i+1])
            return false;

        i++;
    }
    return true;
}
const sort = (arr=[]) => {
    while (!isSorted(arr)) {
        for (let i=0; i<arr.length-1; i++) {
            if (arr[i]>arr[i+1]) {
                // swap
                const temp=arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=temp;
            }
        }
    }
    return arr;
}

const array = [40,8,54,45,32,7,2,13,98];
console.log(array);
console.log(sort(array));