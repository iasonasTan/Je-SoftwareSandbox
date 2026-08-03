#include <iostream>
#include <vector>
#include <thread>
#include <chrono>
#include <algorithm>
#include <cstdlib>

#include <sys/ioctl.h>
//#include <windows.h>
#include <stdlib.h>
#include <time.h>

using std::cout;
using std::endl;
using std::vector;
using std::string;

namespace Random {
    char chars[] = {'$', '&', '#', '@', '%', '?', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'f'};

    char randChar () {
        char out;
        int len = (int)(sizeof(chars)/sizeof(char));
        int ranInt;
        // get random val less than len
        ranInt = rand() % len;
        return chars[ranInt];
    }
}

int WIDTH;
int HEIGHT;
int obj_counter = 0;
string os;

class Letter {
    public:
        char c;
        int x;
        int y;

    public:
        Letter(int x) {
            this->x = x;
            this->y = HEIGHT;
            c = Random::randChar();
        }

        void update () {
            c = Random::randChar();
            y--;
        }

        bool operator==(const Letter& other) const {
            return this->x == other.x && this->y == other.y && this->c == other.c;
        }
};

vector<Letter> letters;

void draw () {
    for (int i=HEIGHT; i>0; i--) { // y
        for (int j=WIDTH; j>0; j--) { // x
            if (j==WIDTH || j==1) {
                cout << '|';
            } else if (i==HEIGHT || i==1) {
                cout << '-';
            } else {
                bool drawed = false;

                for (Letter& letter : letters) {
                    if (letter.x==j&&letter.y==i) {
                        drawed = true;
                        cout << letter.c;
                    }
                }
                if (!drawed)
                    cout << ' ';
            }
            // end of width
        }
        cout << endl;
    }

}

void update () {
    for (Letter& letter : letters) {
        letter.update();
        if (letter.y < 0 || letter.y-10 > HEIGHT) {
            letters.erase(std::remove(letters.begin(), letters.end(), letter), letters.end());
        }
        obj_counter = letters.size();
    }
}

void addLetter (int x) {
    const int min = 6;
    const int max = 10;
    const int size = (rand() % (max-min))+min;

    for (int i=0; i<size; i++) {
        Letter l = Letter(x);
        l.y += i;

        letters.push_back(l);
    }
}

int main ()
{
    printf("\nEnter width: ");
    scanf("%d", &WIDTH);
    printf("\nEnter height: ");
    scanf("%d", &HEIGHT);
    srand(time(NULL));
    while (1) {
        cout << "Letters: " << obj_counter << endl;
        update();
        draw();
        if (rand() % 1 == 0)
            addLetter(rand() % WIDTH-2);

        std::this_thread::sleep_for(std::chrono::milliseconds(60));
        #if defined(_WIN32) || defined(_WIN64)
            system("cls");
        #elif defined(__linux__) || defined(__unix__)
            system("clear");
        #endif
    }

    return 0;
}

