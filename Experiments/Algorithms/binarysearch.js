// BINARY SEARCH .JS

function search (source=[], requested=0) {
    const isSorted = (arr=[]) => {
        let i=0;
        while (i<arr.length-1) {
            if (arr[i]>arr[i+1])
                return false;
    
            i++;
        }
        return true;
    };
    if (!isSorted(source))
        return -1;
    const n=source.length;

    let min=0;
    let max=n-1;

    while (min<max) {
        let mid = parseInt((min+max)/2);
        let current=source[mid];
        if (current==requested) {
            return mid;
        }
        if (current<requested) {
            min+=mid/2;
        }
        if (current>requested) {
            max-=mid/2;
        }
    }

    return -1;
}
let array = [];
for (let i=0; i<10; i++) {
    array.push(parseInt(Math.random()*400));
}
console.log(array.toString());
import { sort } from "./quicksort.js"
array = sort(array);
console.log(array.toString());
for (let v of array)
    console.log(search(array, v));