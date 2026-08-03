function decode(code: string, start: number) : string {
    function get_times(code: string, start: number) : number {
        let out_str:string="";
        for(let i:number=start; i<code.length; i++) {
            let char:string=code.charAt(i);
            if (isNaN(parseInt(char))) {
                break;
            } else {
                out_str+=char;
            }
        }
        return parseInt(out_str);
    }
    let out:string="";
    let startOfChapter_idx:number=0;
    let times:number=0;
    for(let i:number=start; i<code.length; i++) {
        let char:string=code.charAt(i);
        if(isNaN(parseInt(char))) { // not a number
            if (char==']'&&times>=0) {
                i=startOfChapter_idx;
                times--;
            }
            if(char!='[') {
                startOfChapter_idx=i;
            }
        } else {
            times=get_times(code, i);
            out+=decode(code, start+(times+"").length+1);
        }
    }
    return out;
}

const input:string="2[a10[dj]]5[fx]";
// expected output: adjdjdjdjdjdjdjdjdjdjadjdjdjdjdjdjdjdjdjfxfxfxfxfx
const output:string=decode(input,0);
console.log("'"+output+"'");