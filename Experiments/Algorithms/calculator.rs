use std::io;
use std::process;

fn main() {
    print_header();
    let mut input = String::new();
    let _ = io::stdin().read_line(&mut input);
    let instructions: Vec<&str> = input.trim().split_whitespace().collect();
    let mut out:i32 = 0;
    let mut is_value:bool = true;
    let mut last_op:&str = "add";
    let mut has_error = false;
    println!("Calculating...");
    println!("Starting at zero.");
    for ins in instructions {
        if has_error {
            break;
            
        }
        if !is_value {
            last_op = ins;
        } else {
            process_ins(&ins, &last_op, &mut out, &mut has_error);
        }
        is_value = !is_value;
    }
    if !has_error {
        println!("\nResult is: {}", out);
    } else {
        println!("\nError found in command, available operatins are:");
        println!("div for division, mul for multiplication, add for addition and rem for removal.")
    }
}

fn process_ins(ins: &str, last_op: &str, out: &mut i32, has_error: &mut bool) {
    if !is_parsable(&ins) {
        println!("Not a number! {}", ins);
        process::exit(1);
    }
    let val: i32 = match ins.trim().parse() {
        Ok(v) => v,
        Err(_) => {
            println!("Failed to parse: {}", ins);
            process::exit(1);
        }
    };
    let mut div_with_zero: bool = false;
    print!("{} {} {} ", *out, last_op, val);
    match last_op.to_lowercase().as_str() {
        "pow" => {
            if val == 0 {
                print!("(a^0 = 1)");
            }
            if val == 1 {
                print!("(a^1) == a");
            }
            let base: i32 = *out;
            let pow: u32 = val as u32;
            *out = base.pow(pow);
            // *out = Pow::pow(base, val);
        }, "add" => {
            if val < 0 {
                print!("({}+({}) = {}-{})", *out, val, *out, -val);
            }
            *out += val;
        }, "rem" => {
            if val < 0 {
                print!("({}-(-{}) = {}+{})", *out, val, *out, -val);
            }
            *out -= val;
        }, "div" => {
            if val == 0 {
                div_with_zero = true;
                print!("(division with zero is not valid)")
            } else {
                *out /= val;
            }
        }, "mul" => {
            if *out == 0 || val == 0 {
                print!("(x * 0 = 0)")
            }
            if *out < 0 || val < 0 {
                print!("(|a| * -|b| = -|a*b|)");
            }
            if *out < 0 && val < 0 {
                print!("-|a| * -|b| = |a*b|")
            }
            *out *= val;
        }, _ => {
            println!("\nWARNING: Unknown command {}", last_op);
            *has_error = true;
            return;
        },
    }
    print!(" => {},", *out);
    if div_with_zero {
        print!(" Cannot divide with zero, operation skipped!");
    }
    println!();
}

fn is_parsable(text: &str) -> bool {
    let allowed_chars:[char; 10] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0']; 
    let mut first:bool = true;
    for ch in text.trim().chars() { 
        if !allowed_chars.contains(&ch) {
            if !first || ch != '-' {
                return false; 
            } 
        }     
        first = false;
    } 
    true
}

fn print_header() {
    println!("****************************************");
    println!("*************MATH EXPLAINER*************");
    println!("****************************************");
    println!("Type any number and then add/rem/div/mul/pow to do math!");
    println!("You can type things serialy e.g.: 10 sub 1 div 3...");
    println!("Result will be explained.");
}
