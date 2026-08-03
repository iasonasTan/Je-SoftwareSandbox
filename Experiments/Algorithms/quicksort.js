function getLess(arr, num) {
    let out = [];
    for (let val of arr) {
        if (val < num) {
            out.push(val);
        }
    }
    return out;
}

function getMore(arr, num) {
    let out = [];
    for (let val of arr) {
        if (val > num) {
            out.push(val);
        }
    }
    return out;
}

export function sort(arr) {
    if (arr.length <= 1)
        return arr;

    let border = arr[0];
    let less = sort(getLess(arr, border));
    let more = sort(getMore(arr, border));
    arr = less;
    arr.push(border);
    arr.push(...more);
    return arr;
}

const array = [40, 8, 54, 45, 32, 7, 2, 13, 98];
console.log(array);
console.log(sort(array));