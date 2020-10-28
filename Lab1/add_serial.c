#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/*
    schelet pentru exercitiul 5
*/

#define NUM_THREADS 5

int* arr;
int array_size;

struct arg_struct {
    int start;
    int end;
};

void *f(void *arg) {

    struct arg_struct *args = arg;
	int i, sum = 0;
    int start = args->start;
    int end = args->end;
	for (i = start; i < end; i++) {
  		arr[i] += 100;
	}
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        perror("Specificati dimensiunea array-ului\n");
        exit(-1);
    }
    pthread_t threads[NUM_THREADS];
  	int r;
  	long id;
  	void *status;
  	long arguments[NUM_THREADS];
	
    array_size = atoi(argv[1]);

    arr = malloc(array_size * sizeof(int));
    for (int i = 0; i < array_size; i++) {
        arr[i] = i;
    }

    for (int i = 0; i < array_size; i++) {
        printf("%d", arr[i]);
        if (i != array_size - 1) {
            printf(" ");
        } else {
            printf("\n");
        }
    }

    // TODO: aceasta operatie va fi paralelizata
  	// for (int i = 0; i < array_size; i++) {
    //     arr[i] += 100;
    // }

    for (id = 0; id < NUM_THREADS; id++) {
        struct arg_struct args;
        args.start = id * (double)array_size / NUM_THREADS;
        args.end = (id + 1) * (double)array_size / NUM_THREADS > array_size ? array_size : (id + 1) * (double)array_size / NUM_THREADS;
        
		r = pthread_create(&threads[id], NULL, f, &args);

		if (r) {
	  		printf("Eroare la crearea thread-ului %ld\n", id);
	  		exit(-1);
		}
  	}
    
    for (id = 0; id < NUM_THREADS; id++) {
		r = pthread_join(threads[id], &status);

		if (r) {
	  		printf("Eroare la asteptarea thread-ului %ld\n", id);
	  		exit(-1);
		}
  	}

    for (int i = 0; i < array_size; i++) {
        printf("%d", arr[i]);
        if (i != array_size - 1) {
            printf(" ");
        } else {
            printf("\n");
        }
    }

  	pthread_exit(NULL);
}
