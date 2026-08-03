// MY SORT .JS 
function sort(arr) {
    var nn = Math.max.apply(Math, arr);
    var map = Array(nn);
    var v;
    for (var _i = 0, arr_1 = arr; _i < arr_1.length; _i++) {
        v = arr_1[_i];
        map[v] = true;
    }
    var out = [];
    for (var i = 0; i < map.length; i++) {
        if (map[i] === true)
            out.push(i);
    }
    return out;
}
var array = [];
for (var i = 0; i < 15; i++) {
    var randVal = Math.floor(Math.random() * 10000);
    array.push(randVal);
}
console.log(array.toString());
console.log(sort(array).toString());
