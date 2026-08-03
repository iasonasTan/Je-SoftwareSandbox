// MY SORT .JS 

function sort (arr: Array<number>): Array<number> {
    const nn:number=Math.max(...arr);
    let map:Array<boolean>=Array(nn);
    let v: number;
    for (v of arr) {
        map[v]=true;
    }
    let out:Array<number>=[];
    for (let i:number=0; i<map.length; i++) {
        if (map[i]===true)
            out.push(i);
    }
    return out;
}

const array:Array<number> = [];
for (let i:number=0; i<15; i++) {
    const randVal:number=Math.floor(Math.random()*100);
    array.push(randVal);
}
console.log(array.toString());
console.log(sort(array).toString());