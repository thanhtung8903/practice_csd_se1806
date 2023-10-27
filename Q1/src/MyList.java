/* thangnv75
 */
//(1)==============================================================
import java.io.*;
import java.util.ArrayList;

class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != tail) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        fvisit(p, f);
        f.writeBytes("\r\n");
    }

    void ftraverse(ArrayList<Node> count) {
        Node p = head;
        while (p != null) {
            count.add(p);
            p = p.next;
        }
    }

    void loadData(int k) //do not edit this function
    {
        File f = new File("data.txt");
        if (!f.isFile()) {
            System.out.println("not file");
        }
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addHead(a[i], b[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void addHead(String xOwner, int xPrice) {//You should write here appropriate statements to complete this function.
        if (xOwner.charAt(xOwner.length() - 1) == 'B' || xPrice > 100) {
            return;
        }
        addFirst(new Car(xOwner, xPrice));
    }

    void addTail(String xOwner, int xPrice) {//You should write here appropriate statements to complete this function.

    }

    void remove(Node q) {
        if (q == null) {
            return;
        }
        if (q == head) {
            head = head.next;
            return;
        }
        Node p = head;
        while (p != null) {
            if (p.next == q) {
                break;
            }
            p = p.next;
        }

        if (q == tail) {
            p.next = null;
            tail = p;
            return;
        }

        Node a = (p.next).next;
        p.next = a;

    }

    void addFirst(Car x) {
        Node car = new Node(x);
        if (isEmpty()) {
            head = tail = car;
            return;
        }

        car.next = head;
        head = car;
    }

    void insertBefore(Node q, Car x) {
        if (q == null) {
            return;
        }
        if (q == head) {
            addFirst(x);
            return;
        }
        Node fNode = head;
        while (fNode != null && fNode.next != q) {
            fNode = fNode.next;
        }
        if (fNode == null) {
            return;//khong tim thay q
        }
        Node newNode = new Node(x);
        newNode.next = fNode.next;
        fNode.next = newNode;

//        insertAfter(fNode,x);
    }

    void f1(int line) throws Exception {/* You do not need to edit this function. Your task is to complete the addLast  function
        above only.
         */
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f2(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Node x = new Node(new Car("X", 1));

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        Node q = null;
        for (Node temp : count) {
            if (temp.info.price > 7) {
                q = temp;
                break;
            }
        }
        insertBefore(q, new Car("X", 1));
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f3(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Node x = new Node(new Car("X", 1));
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        int pos = 0;
        Node q = null;
        for (Node temp : count) {
            if (temp.info.price > 10) {
                ++pos;
                if (pos == 2) {
                    q = temp;
                    break;
                }
            }
        }
        insertBefore(q, new Car("X", 1));
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f4(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Node x = new Node(new Car("X", 1));
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        Node q = null;
        for (Node temp : count) {
            if (temp.info.price > 10) {
                q = temp;
            }
        }
        insertBefore(q, new Car("X", 1));
        ftraverse(f);
        f.close();
    }
//==================================================================

    void f5(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        Node q = null;
        for (Node temp : count) {
            if (temp.info.price > 10 && temp.info.owner.charAt(1) == 'C') {
                q = temp.next;
                break;
            }
        }
        remove(q);
        ftraverse(f);
        f.close();
    }

    void f6(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        Node q = null;
        for (Node temp : count) {
            if (temp.info.price > 10 && temp.info.owner.charAt(1) == 'C') {
                q = temp.next;
            }
        }
        remove(q);
        ftraverse(f);
        f.close();
    }

    void f7(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        Node q = null;
        for (Node temp : count) {
            if (temp.next.info.price > 10 && temp.next.info.owner.charAt(1) == 'C') {
                q = temp;
                break;
            }
        }
        remove(q);
        ftraverse(f);
        f.close();
    }

    void f8(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        Node q = null;
        for (Node temp: count) {
            if (temp.next.info.price > 10) {
                q = temp;
            }
        }
        remove(q);
        ftraverse(f);
        f.close();
    }

    void f9(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        Node max = head;
        for (Node temp : count ) {
            if (temp.info.price >= max.info.price) {
                max = temp;
            }
        }
        Node a = max.next;
        for (Node i = head; i != a; i = i.next) {
            for (Node j = i.next; j != a; j = j.next) {
                if (i.info.price > j.info.price) {
                    Car temp = i.info;
                    i.info = j.info;
                    j.info = temp;
                }
            }
        }
        for (Node i = a; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (i.info.price < j.info.price) {
                    Car temp = i.info;
                    i.info = j.info;
                    j.info = temp;
                }
            }
        }
        
        ftraverse(f);
        f.close();
    }

    void f10(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        ftraverse(count);
        Node min = head;
        for (Node temp : count ) {
            if (temp.info.price < min.info.price) {
                min = temp;
            }
        }
        System.out.println("");
        Node a = min.next;
        for (Node i = head; i != a; i = i.next) {
            for (Node j = i.next; j != a; j = j.next) {
                if (i.info.owner.compareTo(j.info.owner) < 0) {
                    Car temp = i.info;
                    i.info = j.info;
                    j.info = temp;
                }
            }
        }
        for (Node i = a; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (i.info.owner.compareTo(j.info.owner) > 0) {
                    Car temp = i.info;
                    i.info = j.info;
                    j.info = temp;
                }
            }
        }
        
        ftraverse(f);
        f.close();
    }
}
