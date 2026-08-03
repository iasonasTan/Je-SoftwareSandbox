function decode(code, start) {
    function get_times(code, start) {
        var out_str = "";
        for (var i = start; i < code.length; i++) {
            var char = code.charAt(i);
            if (isNaN(parseInt(char))) {
                break;
            }
            else {
                out_str += char;
            }
        }
        return parseInt(out_str);
    }
    var out = "";
    var startOfChapter_idx = 0;
    var times = 0;
    for (var i = start; i < code.length; i++) {
        var char = code.charAt(i);
        if (isNaN(parseInt(char))) { // not a number
            if (char == ']' && times >= 0) {
                i = startOfChapter_idx;
                times--;
            }
            if (char != '[') {
                startOfChapter_idx = i;
            }
        }
        else {
            times = get_times(code, i);
            out += decode(code, start + (times + "").length + 1);
        }
    }
    return out;
}
var input = "2[a10[dj]]5[fx]";
// expected output: adjdjdjdjdjdjdjdjdjdjadjdjdjdjdjdjdjdjdjfxfxfxfxfx
var output = decode(input, 0);
console.log("'" + output + "'");
