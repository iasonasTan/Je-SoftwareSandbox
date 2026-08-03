// LINEAR SEARCH .JS
function search (source_arr=[], requested_obj=0)
{
    for (let i=0; i<source_arr.length; i++) {
        let current=source_arr[i];
        if (current===requested_obj)
            return i;
    }
    return -1;
}
const array = [40,8,54,45,32,7,2,13,9,3,832,88];
console.log(search(array, 32)); // outputs 4
console.log(search(array, 234)); // outputs -1
console.log(search(array, 832)); // outputs 10