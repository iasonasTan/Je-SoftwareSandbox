package app.finder;

import java.util.function.Consumer;

public interface Matrix<T> {
	int rows();
	int cols();
	T get(int r, int c);
	void forEach(Consumer<T> consumer);
}
