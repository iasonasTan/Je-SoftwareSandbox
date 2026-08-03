// STALIN SORT .JS

function sort (arr) {
    const out=[arr[0]];
    for (let i=1; i<arr.length; i++) {
        const v=arr[i];
        if (v >= out[out.length-1]) {
            out.push(v);
        }
    }
    return out;
}

const array = [40,80,5,100];
console.log(array);
console.log(sort(array));