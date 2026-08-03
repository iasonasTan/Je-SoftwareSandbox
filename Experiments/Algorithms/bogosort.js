// BOGO SORT .JS
const isSorted = (arr=[]) => {
    let i=0; while (i<arr.length-1) {
    if (arr[i]>arr[i+1]) return false;
    i++; } return true;
}
function shuffle (arr) {
    const n = arr.length;
    for (let i=n-1; i>0; i--) {
        const idx=parseInt(Math.random()*i);
        // swap
        const temp=arr[idx];
        arr[idx]=arr[i];
        arr[i]=temp;
    }
    return arr;
}
function sort (arr=[]) {
    while (!isSorted(arr)) {
        arr = shuffle(arr);
        console.log(arr.toString());
    }
    return arr;
}

const array = [40,8,54];
console.log(array);
console.log(sort(array));