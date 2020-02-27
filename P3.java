import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;


class P3 {
    static class Task {
        public static final String INPUT_FILE = "p3.in";
        public static final String OUTPUT_FILE = "p3.out";
        public static final int NMAX = 2000;

        int n;
        int m;
        int t;
        int source;
        int[][] penalizare;

        public class Edge {
            public int node;
            public int cost;

            Edge(int _node, int _cost) {
                node = _node;
                cost = _cost;
            }
        }

        @SuppressWarnings("unchecked")
        ArrayList<Edge> adj[] ;

        private void readInput() {
            try {
                adj= new ArrayList[NMAX];
                Scanner sc = new Scanner(new BufferedReader(new FileReader(
                        INPUT_FILE)));
                n = sc.nextInt();
                m = sc.nextInt();
                t = sc.nextInt();

                for (int i = 1; i <= n; i++) {
                    adj[i] = new ArrayList<>();
                }
                for (int i = 1; i <= m; i++) {
                    int x, y, w, a;
                    x = sc.nextInt();
                    y = sc.nextInt();
                    w = sc.nextInt();
                    a = sc.nextInt();
                    adj[x].add(new Edge(y, w));
                    adj[y].add(new Edge(x, w));
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(
                        OUTPUT_FILE));
                StringBuilder sb = new StringBuilder();
                sb.append(result);
                bw.write(sb.toString());
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int getResult() {
            Comparator<Integer> comparator = new IntComparator();
            PriorityQueue<Integer> q = new PriorityQueue<>(n, comparator);
            ArrayList<Integer> d = new ArrayList<>(n+1);
            ArrayList<Integer> P = new ArrayList<>(n+1);
            boolean vizitat[] = new boolean[n+1];
            boolean selectat[] = new boolean[n+1];
            Arrays.fill(selectat, false);
            Arrays.fill(selectat, false);

            q.add(1);
            P.add(null);
            d.add(0);
            for (int i = 1; i <= n; i++){
                if(i==1)
                    d.add(0);
                else
                    d.add(999999);

                P.add(null);
            }

            while(!q.isEmpty()){
                Integer u = q.poll();
                if(vizitat[u])
                    continue;
                vizitat[u] =  true;

                for (Edge edge : adj[u]){
                    if(d.get(edge.node) > d.get(u) + edge.cost)
                    {
                        vizitat[edge.node] = false;
                        d.set(edge.node, d.get(u) + edge.cost);
                        P.set(edge.node, d.get(u));
                        q.remove(edge.node);
                        q.add(edge.node);
                    }
                }
            }
            for(int i= 1; i<= n ;i++){
                if(d.get(i) == 999999){
                    d.set(i, -1);
                }
            }
            System.out.println(d.get(n));
            return d.get(n);
        }

        public void solve() {
            readInput();
            writeOutput(getResult());
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}

class IntComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer x, Integer y){
        if(x > y){
            return -1;
        }
        if(x<y){
            return 1;
        }
        return 0;
    }
}