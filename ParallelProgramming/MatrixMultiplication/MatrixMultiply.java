package edu.coursera.parallel;

import static edu.rice.pcdp.PCDP.forseq2d;
import static edu.rice.pcdp.PCDP.forall2dChunked;
import static edu.rice.pcdp.PCDP.forasync;

/**
 * Wrapper class for implementing matrix multiply efficiently in parallel.
 */
public final class MatrixMultiply {
    /**
     * Default constructor.
     */
    private MatrixMultiply() {
    }

    /**
     * Perform a two-dimensional matrix multiply (A x B = C) sequentially.
     *
     * @param A An input matrix with dimensions NxN
     * @param B An input matrix with dimensions NxN
     * @param C The output matrix
     * @param N Size of each dimension of the input matrices
     */
    public static void seqMatrixMultiply(final double[][] A, final double[][] B,
            final double[][] C, final int N) {
        forseq2d(0, N - 1, 0, N - 1, (i, j) -> {
            C[i][j] = 0.0;
            for (int k = 0; k < N; k++) {
                C[i][j] += A[i][k] * B[k][j];
            }
        });
    }

    /**
     * Perform a two-dimensional matrix multiply (A x B = C) in parallel.
     *
     * @param A An input matrix with dimensions NxN
     * @param B An input matrix with dimensions NxN
     * @param C The output matrix
     * @param N Size of each dimension of the input matrices
     */
    public static void parMatrixMultiply(final double[][] A, final double[][] B,
            final double[][] C, final int N) {
        forall2dChunked(0, N - 1, 0, N - 1, (i, j) -> {
            C[i][j] = 0.0;
            for (int k = 0; k < N; k++) {
                C[i][j] += A[i][k] * B[k][j];
            }
        });
    }
    
    /*
     * chunk size = n/tasks
     * 
     * for(int iter = 0; iter < iterations; i++){
     * 		forall(0, tasks-1, (i) -> {
     * 			for(int j = 1 * (n/tasks) + 1; j<=(i+1) * (n/tasks); j++ )
     * 				myNew[j] = (myVal[j-1] + myVal[j+1]) / 2.0;
     * 		});
     *  	double[] temp = myNew;
     *  	myNew = myVal;
     *  	myVal = temp;
     *  }
     *  
     *  barrier based solution for above logic
     *  -----------------------
     *  forallPhased(0, tasks-1, (i) -> {
     *  	double[]  myVal = this.myVal;
     *  	double[]  myNew = this.myNew;
     *  	for(int iter = 0; iter < iterations; iter++){
     *  		for(int j = i * (n/tasks) + 1; j <= (i+1)*(n/tasks); j++)
     *  			myNew[j] = (myVal[j-1] + myVal[j+1]) / 2.0;
     *  		
     *  		next(); //barrier
     *  		
     *  		double[] temp = myNew;
     *  		myNew = myVal;
     *  		myVal = temp;	
     *  	}
     *  });
     *  
     * */
}
