class RippleBoxData {
  double[] values;
  double[] Dvalues;
  double[] Mat;
  double[] Mat2;
  double[] BMat;
  double[] BMat2;
  double dt;
  int c;
  int cursorX;
  int cursorY;
  double width;
  int N;

  RippleBoxData(int N, double dt, double width) {
    this.dt = dt;
    this.width = width;
    this.N = N;
    this.c = 0;
    this.cursorX = (int)(width/2);
    this.cursorY = (int)(width/2);
    values = new double[N*N];
    Dvalues = new double[N*N];
    Mat = new double[N*N];
    Mat2 = new double[N*N];
    BMat = new double[N*N];
    BMat2 = new double[N*N];

    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        values[idx(i,j)] = 0.5;
        Dvalues[idx(i,j)] = 0.0;
        Mat[idx(i,j)] = 0.0;
        Mat2[idx(i,j)] = 0.0;
        BMat[idx(i,j)] = 0.0;
        BMat2[idx(i,j)] = 0.0;
      }
    }
  }

  int idx(int i,int j) {
    return (N*i+j);
  }

  void update() {
    c++;

    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if((i==0)||(j==0)||(i==N-1)||(j==N-1)) {
          Mat[idx(i,j)] = 0.5;
          Mat2[idx(i,j)] = 0.0;
          BMat[idx(i,j)] = 0.5;
          BMat[idx(i,j)] = 0.0;
        } else {
          Mat[idx(i,j)] = values[idx(i,j)];
          Mat2[idx(i,j)] = Dvalues[idx(i,j)]
            + 10*dt*(Math.sin(c/10))*Math.exp((-80)*(Math.pow(2*i-cursorY,2)/(50*50)+Math.pow(2*j-cursorX,2)/(50*50))) 
            - dt*0.01*Dvalues[idx(i,j)];
          BMat[idx(i,j)] = Mat[idx(i,j)];
          BMat2[idx(i,j)] = Mat2[idx(i,j)];
          Dvalues[idx(i,j)] = Mat2[idx(i,j)];
        }
      }
    }

    for( int k=0; k<5; k++) {
      for(int i=1; i<N-1; i++) {
        for(int j=1; j<N-1; j++) {
          BMat[idx(i,j)] = Mat2[idx(i,j)]*dt;
          BMat2[idx(i,j)] = dt*2*(Mat[idx(i-1,j)]+Mat[idx(i+1,j)]+Mat[idx(i,j-1)]+Mat[idx(i,j+1)]-4*Mat[idx(i,j)]);
          values[idx(i,j)] += BMat[idx(i,j)];
          Dvalues[idx(i,j)] += BMat2[idx(i,j)];
        }
      }

      if(k==0) {
        for(int i=0; i<N; i++) {
          Mat[idx(i,0)] = 0.0;
          BMat[idx(i,0)] = 0.0;
          Mat[idx(i,N-1)] = 0.0;
          BMat[idx(i,N-1)] = 0.0;
        }

        for(int j=0; j<N; j++) {
          Mat[idx(0,j)] = 0.0;
          BMat[idx(0,j)] = 0.0;
          Mat[idx(N-1,j)] = 0.0;
          BMat[idx(N-1,j)] = 0.0;
        }
      }

      for(int i=1; i<N-1; i++) {
        for(int j=1; j<N-1; j++) {
          Mat[idx(i,j)] = BMat2[idx(i,j)]*dt;
          Mat2[idx(i,j)] = dt*2*(BMat[idx(i-1,j)]+BMat[idx(i+1,j)]+BMat[idx(i,j-1)]+BMat[idx(i,j+1)]-4*BMat[idx(i,j)]);
          values[idx(i,j)] += Mat[idx(i,j)];
          Dvalues[idx(i,j)] += Mat2[idx(i,j)];
        }
      }

    }


  }
}
