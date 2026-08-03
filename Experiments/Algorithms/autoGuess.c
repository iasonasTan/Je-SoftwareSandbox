#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <unistd.h>

int total_len = 1000000;
int secret_num;
int val;
int start;
int end;
int ans;

void initSecretNum();
void init();
int checkAns();
void printStats();

int main ()
{
    init();
    initSecretNum();

    start = 0;
    end = total_len;
    while (1 < 2) {
        val = (start+end)/2;
        ans = val;
        if (val>secret_num) {
            end = val;
        } else if (val<secret_num) {
            start = val;
        }

        printStats();
        
        if (checkAns(ans)) {
            break;
        }
    }

    return 0;
}

void printStats () {
    printf("num: %d, start: %d, end: %d, val: %d\n", secret_num, start, end, val);
}

int checkAns () {
    if (ans==secret_num) {
        printf("Found! ans: %d\n", ans);
        return 1;
    }

    return 0;
}

void init () {
    val = total_len;   
}

void initSecretNum () {
    srand(time(NULL));
    secret_num = (rand()%total_len)+1;
}
