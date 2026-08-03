use std::io;

fn main() {
    println!("Enter input: ");
    let mut input: String = String::new();
    let _ = io::stdin().read_line(&mut input);

    let res: String = decode(input);
    println!("Program output:\n{}", res);
}

fn decode(code_str: String) -> String {
    let mut out: String = String::new();
    let mut i: i32 = 0;
    let length: i32 = code_str.len() as i32;
    while i < length {
        let index: usize = i as usize;
        let character: Option<char> = code_str.chars().nth(index);
        match character {
            Some(c) => {
                if c != ' ' { handle_char(&mut out, c); }
            }, None => println!("Character does not exist."),
        }
        i += 1;
    }
    return out;
}

fn handle_char(out: &mut String, c: char) {
    
}