// SELECTION SORT .JS

function main () {
    function sort (arr) {
        for (let i=0; i<arr.length; i++) {
            let min_idx = i;
            for (let j=i+1; j<arr.length; j++) {
                if (arr[j]<arr[min_idx]){
                    min_idx=j;
                }
            }
            // swap
            let $temp=arr[min_idx];
            arr[min_idx]=arr[i];
            arr[i]=$temp;
        }
        return arr;
    }

    const array = [40,8,54,45,32,7,2,13,98];
    console.log(array);
    console.log(sort(array));
}

main();