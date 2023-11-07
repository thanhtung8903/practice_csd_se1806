/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------

public class Graph {

    int[][] a;
    int n;
    char v[];
    int deg[];

    Graph() {
        v = "ABCDEFGHIJKLMNOP".toCharArray();
        deg = new int[20];
        a = new int[20][20];
        n = 0;
    }

    void loadData(int k) //do not edit this function
    {
        RandomAccessFile f;
        int i, j, x;
        String s;
        StringTokenizer t;
        a = new int[20][20];
        try {
            f = new RandomAccessFile("data.txt", "r");
            for (i = 0; i < k; i++) {
                f.readLine();
            }
            s = f.readLine();
            s = s.trim();
            n = Integer.parseInt(s);
            for (i = 0; i < n; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < n; j++) {
                    x = Integer.parseInt(t.nextToken().trim());
                    a[i][j] = x;
                }
            }
            f.close();
        } catch (Exception e) {
        }

    }

    void dispAdj() {
        int i, j;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
        }
    }

    void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(" " + v[i]);
    }

    void breadth(boolean[] en, int i, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void breadth(int k, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        breadth(en, k, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                breadth(en, i, f);
            }
        }
    }

    void breadth(boolean[] en, int i, ArrayList<Integer> count, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        while (!q.isEmpty()) {
            r = q.dequeue();
            count.add(r);
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void breadth(int k, ArrayList<Integer> count, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        breadth(en, k, count, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                breadth(en, i, count, f);
            }
        }
    }

    void depth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, f);
            }
        }
    }

    void depth(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth(visited, i, f);
            }
        }
    }

    void depth(boolean[] visited, int k, ArrayList<Integer> count, RandomAccessFile f) throws Exception {
        count.add(k);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, count, f);
            }
        }
    }

    void depth(int k, ArrayList<Integer> count, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth(visited, k, count, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth(visited, i, count, f);
            }
        }
    }

    int deg(int i) {
        int s, j;
        s = 0;
        for (j = 0; j < n; j++) {
            s += a[i][j];
        }
        s += a[i][i];
        return (s);
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void depth2(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        f.writeBytes("(" + deg(k) + ")");
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth2(visited, i, f);
            }
        }
    }

    void depth2(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth2(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth2(visited, i, f);
            }
        }
    }

    void breadth2(boolean[] en, int i, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        f.writeBytes("\n");
        while (!q.isEmpty()) {
            r = q.dequeue();
            f.writeBytes(deg(r) + " ");
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void breadth2(int k, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        breadth2(en, k, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                breadth2(en, i, f);
            }
        }
    }

    void dijkstra(int from, int to, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        int[] previous = new int[n];
        int INF = 99;

        for (int i = 0; i < n; i++) {
            visited[i] = false;
            distance[i] = a[from][i];
            previous[i] = from;
        }

        visited[from] = true;

        while (true) {
            int minDistance = INF;
            int k = -1;
            for (int i = 0; i < n; i++) {
                if (visited[i] == true) {
                    continue;
                }
                if (distance[i] < minDistance) {
                    minDistance = distance[i];
                    k = i;
                }
            }
            if (k == -1) {
                return;
            }
            visited[k] = true;
            if (k == to) {
                break;
            }
            for (int i = 0; i < n; i++) {
                if (visited[i] == true) {
                    continue;
                }
                if (distance[i] > distance[k] + a[k][i]) {
                    distance[i] = distance[k] + a[k][i];
                    previous[i] = k;
                }
            }
        }
        ArrayList<Integer> points = new ArrayList<>();
        points.add(points.size(), to);
        while (from != to) {
            to = previous[to];
            points.add(points.size(), to);
        }
        for (int i = points.size() - 1; i >= 0; i--) {
//            System.out.print(v[points.get(i)] + " ");
            f.writeBytes(v[points.get(i)] + " ");
        }
//        System.out.println("");
        f.writeBytes("\n");
        for (int i = points.size() - 1; i >= 0; i--) {
//            System.out.print(distance[points.get(i)] + " ");
            f.writeBytes(distance[points.get(i)] + " ");
        }
    }

    void dijkstra(int from, int to, ArrayList<Character> ver, ArrayList<Integer> dis) throws Exception {
        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        int[] previous = new int[n];
        int INF = 999;

        for (int i = 0; i < n; i++) {
            visited[i] = false;
            distance[i] = a[from][i];
            previous[i] = from;
        }

        visited[from] = true;

        while (true) {
            int minDistance = INF;
            int k = -1;
            for (int i = 0; i < n; i++) {
                if (visited[i] == true) {
                    continue;
                }
                if (distance[i] < minDistance) {
                    minDistance = distance[i];
                    k = i;
                }
            }
            if (k == -1) {
                return;
            }
            visited[k] = true;
            if (k == to) {
                break;
            }
            for (int i = 0; i < n; i++) {
                if (visited[i] == true) {
                    continue;
                }
                if (distance[i] >= distance[k] + a[k][i]) {
                    distance[i] = distance[k] + a[k][i];
                    previous[i] = k;
                }
            }
        }
        ArrayList<Integer> points = new ArrayList<>();
        points.add(points.size(), to);
        while (from != to) {
            to = previous[to];
            points.add(points.size(), to);
        }
        for (int i = points.size() - 1; i >= 0; i--) {
            ver.add(v[points.get(i)]);
        }

        for (int i = points.size() - 1; i >= 0; i--) {
            dis.add(distance[points.get(i)]);
        }
    }

    void f1(int lineNum) throws Exception {
        loadData(lineNum);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        depth(1, f);
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------
        depth2(1, f);
        f.writeBytes("\r\n");
        f.close();
    }
//===========================================================================

    void f2(int lineNum) throws Exception {
        loadData(lineNum);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------------------------------------------------------------------------
        breadth(0, f);
        breadth2(0, f);
        f.writeBytes("\r\n");
        f.close();
    }

//===========================================================================
/*
Algorithm for finding an Euler cycle from the v X using stack 
//Input: Connected graph G with all vertices having even degrees
//Output: Euler cycle

declare a stack S of characters
declare empty array E (which will contain Euler cycle)
push the v X to S
while(S is not empty)
 {r = top element of the stack S 
  if r is isolated then remove it from the stack and put it to E
   else
   select the first v Y (by alphabet order), which is aacent
   to r, push  Y  to  S and remove the edge (r,Y) from the graph   
 }
 the last array E obtained is an Euler cycle of the graph
     */
    int count = 0;

    void f3(int lineNum) throws Exception {
        loadData(lineNum);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        depth(1, f);
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------
        ArrayList<Integer> count = new ArrayList<>();
        depth(1, count, f);
        for (int i = 2; i <= 5; i++) {
            fvisit(count.get(i), f);
        }
        f.writeBytes("\r\n");
        f.close();
    }

    void f4(int lineNum) throws Exception {
        loadData(lineNum);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(1, f);
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------
        ArrayList<Integer> count = new ArrayList<>();
        breadth(1, count, f);
        for (int i = 1; i <= 3; i++) {
            fvisit(count.get(i), f);
        }
        f.writeBytes("\r\n");
        f.close();
    }

    void f5(int lineNum) throws Exception {
        loadData(lineNum);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------
        dijkstra(1, 4, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void f6(int lineNum) throws Exception {
        loadData(lineNum);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------

        ArrayList<Character> v1 = new ArrayList<>();
        ArrayList<Integer> dis1 = new ArrayList<>();
        dijkstra(1, 4, v1, dis1);
        f.writeBytes(v1.get(0) + " " + v1.get(v1.size() - 1) + "->" + dis1.get(dis1.size() - 1) + "\n");

        ArrayList<Character> v2 = new ArrayList<>();
        ArrayList<Integer> dis2 = new ArrayList<>();
        dijkstra(0, 5, v2, dis2);
        if (v2.size() >= 4) {
            for (int i = v2.size() - 4; i < v2.size(); i++) {
                f.writeBytes(v2.get(i) + "-" + dis2.get(i) + " ");
            }
        } else {
            for (int i = 0; i < v2.size(); i++) {
                f.writeBytes(v2.get(i) + "-" + dis2.get(i) + " ");
            }
        }
        f.writeBytes("\r\n");
        f.close();
    }

    void f7(int lineNum) throws Exception {
        loadData(lineNum);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------
        ArrayList<Character> v1 = new ArrayList<>();
        ArrayList<Integer> dis1 = new ArrayList<>();
        dijkstra(1, 4, v1, dis1);

        for (int i = 0; i < v1.size(); i++) {
            f.writeBytes(v1.get(i) + " ");
        }
        f.writeBytes("\n" + dis1.get(dis1.size() - 1) + "\n");

        ArrayList<Character> v2 = new ArrayList<>();
        ArrayList<Integer> dis2 = new ArrayList<>();
        dijkstra(1, 6, v2, dis2);
        
        for (Character temp : v2) {
            System.out.print(temp);
        }
        
        for (int i = v2.size() - 4; i < v2.size(); i++) {
            f.writeBytes(v2.get(i) + "-" + dis2.get(i) + " ");
        }
        f.writeBytes("\r\n");
        f.close();
    }

    void f8(int lineNum) throws Exception {
        loadData(lineNum);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------
        eulerCycle(4, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void dijkstra2(int fro, int to, ArrayList<Character> ver, ArrayList<Integer> dis) throws Exception {
        int i, j, k, t, INF;
        INF = 999;
        boolean[] S = new boolean[n];
        int[] d = new int[n];
        int[] p = new int[n];
        for (i = 0; i < n; i++) {
            S[i] = false;
            d[i] = a[fro][i];
            p[i] = fro;
        }

        int[] ss = new int[n]; // ss[0], ss[1], ss[2],... are vertices sequentially selected to S
        int[] pp = new int[n]; // ss[0] -> ss[1] -> ss[2],... is the shortest path
        int m, r; // m is number of vertices in S,
        // r is the number of vertices in shortest path 
        j = 0;

        // select fro into the set S
        S[fro] = true;
        ss[0] = fro;
        while (true) {// find min d[i] in the set of those vertices not selected into S
            k = -1;
            t = INF;
            for (i = 0; i < n; i++) {
                if (S[i] == true) {
                    continue;
                }
                if (d[i] < t) {
                    k = i;
                    t = d[i];
                }
            }
            if (k == -1) {
                return; // no solution
            }           // select k into the set S
            S[k] = true;
            j++;
            ss[j] = k;
            if (k == to) {
                break;
            }
            // Recalculate d[i]
            for (i = 0; i < n; i++) {
                if (S[i] == true) {
                    continue;
                }
                if (d[i] > d[k] + a[k][i]) {
                    d[i] = d[k] + a[k][i];
                    p[i] = k;
                }
            }
        }
        m = j;
        Stack s = new Stack();
        i = to;
        while (true) {
            s.push(i);
            if (i == fro) {
                break;
            }
            i = p[i];
        }
        j = 0;
        while (!s.isEmpty()) {
            i = s.pop();
            pp[j++] = i;
        }
        r = j;
        ver.add(v[pp[0]]);
//        f.writeBytes("" + v[pp[0]]);
        for (i = 1; i < r; i++) {
//            f.writeBytes("   " + v[pp[i]]);
            ver.add(v[pp[i]]);

        }
//        f.writeBytes("\r\n");
//        f.writeBytes("" + d[pp[0]]);
         dis.add(d[pp[0]]);
        for (i = 1; i < r; i++) {
//            f.writeBytes("   " + d[pp[i]]);
            dis.add(d[pp[i]]);
        }
//        f.writeBytes("\r\n");
    }

    void eulerCycle(int k, RandomAccessFile f) throws Exception {
        int[] eu = new int[100];
        int m = 0;
        int i, j, r;
        Stack s = new Stack();
        s.push(k);
        while (!s.isEmpty()) {
            r = s.top();
            for (i = 0; i < n; i++) {
                if (a[r][i] > 0) {
                    break;
                }
            }
            if (i == n) { // r isolated
                s.pop();
                eu[m++] = r;
            } else {
                s.push(i);
                a[r][i]--;
                a[i][r]--;
            }
        }
        f.writeBytes(v[eu[0]] + "");
        for (i = 1; i < m; i++) {
            f.writeBytes(" " + v[eu[i]]);
        }
        System.out.println();
    }
}
