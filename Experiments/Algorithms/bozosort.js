// BOZO SORT .JS

const isSorted = (arr=[]) => {
    let i=0; while (i<arr.length-1) {
    if (arr[i]>arr[i+1]) return false;
    i++; } return true;
}
function shuffle (arr) {
    const n = arr.length;
    let idx1=parseInt(Math.random()*n);
    let idx2=parseInt(Math.random()*n);
    // swap
    const temp=arr[idx1];
    arr[idx1]=arr[idx2];
    arr[idx2]=temp;
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