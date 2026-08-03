module logic;

interface Computer {
	void put(int v);
	Response calculate();
	void clear();
}

final class AverageComputer : Computer {
	private int counter = 0;
	private int sum = 0;

	override public void put(int v) {
		counter += 1;
		sum += v;
	}

	override public void clear() {
		counter = 0;
		sum = 0;
	}

	override public Response calculate() {
		if(counter == 0){
			return new Response.Err("No values added.");
		}
		return new Response.Ok(sum/counter);
	}
}

interface Response {
	static final class Ok : Response {
		public const int value;

		this(int v) {
			value = v;
		}
	}

	static final class Err : Response {
		public const string msg;

		this(string msg) {
			this.msg = msg;
		}
	}
}