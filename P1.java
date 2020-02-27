import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;


public class P1 {

    static class Task {
        public final static String INPUT_FILE = "p1.in";
        public final static String OUTPUT_FILE = "p1.out";

        int n, k;

        private Vector<Integer> readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                Vector<Integer> v = new Vector<Integer>();
                for (int i = 0; i < n; i++){
                    v.add(sc.nextInt());
                }
                sc.close();
                return v;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(Vector <LinkedList <Integer> > result){
            try{
            	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
            	StringBuilder sb = new StringBuilder(100000);

                int ok = 1;
                for(int i = 0; i < result.size(); i++) {
                	if(result.get(i).size() == 0) {
                		ok = 0;
                	}
                }
                if(result.get(0).size() > 1)
                	ok = 0;
                
                if(ok == 0)
                	pw.printf("-1");
                else {
	                int count = 0;
	                for(int i = 0; i < result.size(); i++) {
	                	count += result.get(i).size();
	                }
	                count--;
	                pw.printf("%d\n", count);
	                for(int i = 1; i < result.size(); i++) {
	                	for(int j = 0; j < result.get(i).size(); j++) {
	                		sb.append(result.get(i-1).get(0));
	                		sb.append(' ');
	                		sb.append(result.get(i).get(j));
	                		sb.append("\n");
	                	}               	
	                }
              }
                pw.printf("%s", sb.toString());
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private Vector <LinkedList <Integer> > getResult(Vector<Integer> v){
        	Vector <LinkedList <Integer> > adj = new Vector<>();
        	int max = Collections.max(v);
        	for(int i = 0; i <= max; i++) {
        		adj.add(new LinkedList<>());
        	}
        	for(int i = 0; i < v.size(); i++) {
        		adj.get(v.get(i)).add(i+1);
        	}
        	return adj;
        }

        public void solve(){
            writeOutput(getResult(readInput()));
        }
    }

    public static void main (String[] args){
        new Task().solve();
    }
}
