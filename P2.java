import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class P2 {

    static class Task {
        public final static String INPUT_FILE = "p2.in";
        public final static String OUTPUT_FILE = "p2.out";

        int n, m, k;
        int[][] mat;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                m = sc.nextInt();
                k = sc.nextInt();
                mat = new int[n][m];
                for (int i = 0; i < n; i++){
                	for(int j = 0; j < m; j++) {
                		mat[i][j] = sc.nextInt();	
                	}
                }
                sc.close();              
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result){
            try{
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d", result);               
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
       private int bfs(Vector <LinkedList <Integer> > adj, int x){
    	   	int[] vizitat = new int[n*m];
            int count = 1;
            int matVal = mat[x/m][x%m];
            Queue<Integer> coada = new LinkedList<>(); 
            vizitat[x] = 1;
            coada.add(x);
            while(coada.size() > 0){
                int aux = coada.poll();
                for(int i : adj.get(aux)){
                    if(vizitat[i] == 0 && mat[i/m][i%m] >= matVal && mat[i/m][i%m] <= matVal + k){
                        count++;
                        coada.add(i);
                        vizitat[i] = 1;
                    }
                }
            }
            return count;
        }

        private int getResult(){

        	Vector <LinkedList <Integer> > adj = new Vector<>();
        	
        	for(int i = 0; i < n*m; i++) {
        		adj.add(new LinkedList<>());
        	}
        	
        	if(n == 1) {
           	  for(int i = 1; i < m-1; i++) {
    	 		  adj.get(i).add(i + 1);
    			  adj.get(i).add(i - 1);
    			      			  
    	 		  adj.get((n-1)*m + i).add((n-1)*m + i - 1);
    			  adj.get((n-1)*m + i).add((n-1)*m + i + 1);  
         	  }         	  
         	  adj.get(0).add(1);    
         	  adj.get(m - 1).add(m-2);  
        	} else {
	     	   for(int i = 1; i < n -1; i++) {
	     		   for(int j = 1; j < m - 1; j++) {
	     			   adj.get(i*m+j).add(i*m+j+1);
	     			   adj.get(i*m+j).add(i*m+j-1);
	     			   adj.get(i*m+j).add((i+1)*m+j);
	     			   adj.get(i*m+j).add((i-1)*m+j);     			   
	     		   }
	     	   }
	     	  for(int i = 1; i < n-1; i++) {
	     		  adj.get(i*m).add(i*m+1);
				  adj.get(i*m).add((i+1)*m);
				  adj.get(i*m).add((i-1)*m);
				  
				  adj.get(i*m+m-1).add(i*m+m-2);
				  adj.get(i*m+m-1).add((i+1)*m+m-1);
				  adj.get(i*m+m-1).add((i-1)*m+m-1);			  
	     	  }
	     	  for(int i = 1; i < m-1; i++) {
		 		  adj.get(i).add(i + 1);
				  adj.get(i).add(i - 1);
				  adj.get(i).add(m + i);
				  
		 		  adj.get((n-1)*m + i).add((n-1)*m + i - 1);
				  adj.get((n-1)*m + i).add((n-1)*m + i + 1);
				  adj.get((n-1)*m + i).add((n-1)*m + i - m);
	     	  }
     	  
	     	  adj.get(0).add(1);  
	     	  adj.get(0).add(m);
	     	  
	     	  adj.get(m - 1).add(m-2);  
	     	  adj.get(m - 1).add(2*m -1);
	     	  
	     	  adj.get((n-1)*m).add((n-1)*m+1);  
	     	  adj.get((n-1)*m).add((n-2)*m);
	     	  
	     	  adj.get(n*m - 1).add(n*m-2);  
	     	  adj.get(n*m - 1).add(n*m - m  -1);
        	}    	   
     	  int max = 0;
     	  
     	  for(int i = 0; i < n; i++) {
     		  for(int j = 0; j < m; j++) {
     			  int res = bfs(adj, i*m+j);
     			  if(res > max)
     				  max = res;
     		  }
     	  }        	
        	return max;
        }

        public void solve(){
        	readInput();
            writeOutput(getResult());
        }
    }

    public static void main (String[] args){
        new Task().solve();
    }
}
