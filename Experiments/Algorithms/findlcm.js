// FIND LCM .JS

const lcm = (arr=[1]) => {
    const valid = (arr=[1], val=1) => {
        for (const v of arr) {
            if (!Number.isInteger(v/val))
                return false;
        }
        return true;
    };
    const max=Math.max(...arr);
    let mul=1;
    while (!valid(arr, mul*max)) {
        mul++;
    }
    return mul;
};

const array=[2,3];
console.log(lcm(array));
