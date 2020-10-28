#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
 

#define NUM_THREADS 2
void *first_task(void *arg) {
	long id = *(long*) arg;
	int i, sum = 0;
	for (i = 0; i < 100; i++) {
  		sum += i;
	}
	printf("%d\n", sum);
}

void *f(void *arg) {
  	long id = *(long*) arg;
	int i;
	// nu se afiseaza in ordine 
	for (i = 0; i < 50; i++) {
  		printf("Hello World din thread-ul %ld!\n", id);
	}
  	pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
	
    long cores = sysconf(_SC_NPROCESSORS_CONF);
	pthread_t threads[NUM_THREADS];
  	int r;
  	long id;
  	void *status;
  	long arguments[NUM_THREADS];
	
  	// for (id = 0; id < cores; id++) {
  	// 	arguments[id] = id;
	// 	r = pthread_create(&threads[id], NULL, f, &arguments[id]);

	// 	if (r) {
	//   		printf("Eroare la crearea thread-ului %ld\n", id);
	//   		exit(-1);
	// 	}
  	// }

	for (id = 0; id < NUM_THREADS; id++) {
  		arguments[id] = id;
		if (id == 0) {
			r = pthread_create(&threads[id], NULL, f, &arguments[id]);
		}
		if (id == 1) {
			r = pthread_create(&threads[id], NULL, first_task, &arguments[id]);
		}

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

  	pthread_exit(NULL);
}
