use std::io;
use std::process;

fn handle(n: f64, typ: &str) {
    let output: String = match typ {
        "exit" => {
            println!("Aborting...");
            process::exit(0);
        },
        "x" => as_x(n),
        "l" => as_l(n),
        _ => {
            println!("Error: Unrecognized parameter!");
            process::exit(1);
        },
    };
    println!("Output: {output}");
}

fn as_l(l: f64) -> String {
    let (a, b, c): (f64, f64, f64) = (4.0, 12.0, 25.0-l);
    let d = b.powi(2) -4.0 *a *c;
    
    let x1 = (-b + d.sqrt()) / (2.0*a);
    let x2 = (-b - d.sqrt()) / (2.0*a);
    
    format!("[{:.2}, {:.2}]", x1, x2)
}

fn as_x(x: f64) -> String {
    format!("{:.2}", (2.0*x+5.0).powf(2.0)-8.0*x)
}

fn main() {
    println!("Enter any number: ");
    let mut input: String = String::new();
    let _ = io::stdin().read_line(&mut input);

    println!("Is this x or l?(l/x): ");
    let mut typ: String = String::new();
    let _ = io::stdin().read_line(&mut typ);

    match input.trim().parse::<f64>() {
        Ok(n) => handle(n, typ.trim()),
        Err(e) => println!("There's an error: {e}"),
    };
    
    println!("Type 'exit' to exit");
    let mut answ = String::new();
    let _ = io::stdin().read_line(&mut answ);
    if answ == "exit\n" {
        process::exit(0);
    } else {
        println!();
        main();
    }
}
