#include <stdio.h>
#include <stdlib.h>

// Class
struct {
	// Fields
	int x, y, w, h;
} typedef Rectangle;

// Constructor
Rectangle* newRectangle(int x, int y, int w, int h) {
	Rectangle* rect = malloc(sizeof(Rectangle));
	rect->x = x;
	rect->y = y;
	rect->w = w;
	rect->h = h;
	return rect;
}

// Method print
void printRectangle(Rectangle rect) {
	printf("Rectangle{%d, %d, %d, %d}", rect.x, rect.y, rect.w, rect.h);
}

int main() {
	Rectangle* rectangle = newRectangle(0, 0, 100, 50);
	printRectangle(*rectangle);
	return 0;
}
