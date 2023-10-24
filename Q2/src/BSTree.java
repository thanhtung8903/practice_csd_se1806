/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }

        //if (p.info.price>=3 && p.info.price<=5)
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void preOrder(Node p, ArrayList<Node> count) throws Exception {
        if (p == null) {
            return;
        }
        count.add(p);
        preOrder(p.left, count);
        preOrder(p.right, count);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void inOrder(Node p, ArrayList<Node> count) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, count);
        count.add(p);
        inOrder(p.right, count);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void postOrder(Node p, ArrayList<Node> count) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, count);
        postOrder(p.right, count);
        count.add(p);
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void breadth(Node p, ArrayList<Node> count) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            count.add(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void loadData(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xOwner, int xPrice) {
        //You should insert here statements to complete this function
        if (xOwner.charAt(1) == 'A' || xPrice > 100) {
            return;
        }
        insert(new Car(xOwner, xPrice));
    }

    void insert(Car x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.price == x.price) {
                return;
            }
            f = p;
            if (x.price < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.price < f.info.price) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }

    void deleteByCopying(int xPrice) {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.price == xPrice) {
                break;
            }
            f = p;
            if (xPrice < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println("The key does not existed, no deletetion");
            return;
        }
//        case 1: p is leaf-node
        if (p.left == null && p.right == null) {
            if (f == null) { //p = root
                root = null;
            } else {
                if (p == f.left) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }
//        case 2: p has left child node
        if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else {
                if (p == f.left) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
        }

//        case 2: p has right child node
        if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else {
                if (p == f.left) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
        }

        if (p.left != null && p.right != null) {
            Node q = p.left;
            Node r, fr; //fr is r's father
            fr = null;
            r = q;
            while (r.right != null) {
                fr = r;
                r = r.right;
            } // r is right most node
            p.info = r.info;
            if (fr == null) {
                p.left = q.left;
                return;
            }
            fr.right = r.left;
        }
    }

    Node father(int xPrice) {
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.price == xPrice) {
                break;
            }
            f = p;
            if (xPrice < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return f;
    }

    void deleteByMerging(int xPrice) {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.price == xPrice) {
                break;
            }
            f = p;
            if (xPrice < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
//        case 1: p is leaf-node
        if (p.left == null && p.right == null) {
            if (f == null) { //p = root
                root = null;
            } else {
                if (p == f.left) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }
//        case 2: p has left child node
        if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else {
                if (p == f.left) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
        }

//        case 2: p has right child node
        if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else {
                if (p == f.left) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
        }

//        case 3: 
        if (p.left != null && p.right != null) {
            Node k = p.left;
            while (k.right != null) {
                k = k.right;
            }
            if (p == root) {
                root = root.left;
            } else {
                if (f.left == p) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            k.right = p.right;
        }
    }

    int height(Node p) {
        if (p == null) {
            return 0;
        }

        int leftHeight = height(p.left);
        int rightHeight = height(p.right);
        int result = Math.max(leftHeight, rightHeight) + 1;

        return result;
    }

    Node rotateRight(Node p) {
        if (p == null || p.left == null) {
            return p;
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return q;
    }

    Node rotateLeft(Node p) {
        if (p == null || p.left == null) {
            return p;
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }

    void f1(int line) throws Exception {/* You do not need to edit this function. Your task is to complete insert  function
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
        breadth(root, f);
        f.writeBytes("\r\n");

        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        inOrder(root, count);
        for (Node temp : count) {
            if (temp.info.price >= 3 && temp.info.price <= 50) {
                fvisit(temp, f);
            }
        }

        f.writeBytes("\r\n");
        f.close();
    }

// f.writeBytes(" k = " + k + "\r\n");
//=============================================================
    void f3(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
//        inorder2(root);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        postOrder(root, count);
        Node remove = null;
        for (Node temp : count) {
            if (temp.left != null && temp.right != null && temp.info.price >= 30 && temp.info.price <= 70) {
                remove = temp;
                break;
            }
        }
        deleteByCopying(remove.info.price);
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f4(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------+-----------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        inOrder(root, count);
        Node remove = null;
        for (Node temp : count) {
            if (temp.left != null && temp.info.price >= 30 && temp.info.price <= 70) {
                remove = temp;
                break;
            }
        }
        deleteByMerging(remove.info.price);
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void f5(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------+-----------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        preOrder(root, count);
        Node remove = count.get(3);
        deleteByCopying(remove.info.price);
        preOrder(root, f);
        f.writeBytes("\r\n");
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
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------+-----------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        breadth(root, count);
        Node remove = count.get(1);
        deleteByMerging(remove.info.price);
        breadth(root, f);
        f.writeBytes("\r\n");
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
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------+-----------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        preOrder(root, count);
        Node p = null;
        for (Node temp : count) {
            if (temp.right != null && temp.info.price >= 30 && temp.info.price <= 70) {
                p = temp;
                break;
            }
        }
        Node r = rotateLeft(p);
        Node fa = father(p.info.price);
        if (fa == null) {
            root = r;
        } else {
            if (fa.left == p) {
                fa.left = r;
            } else {
                fa.right = r;
            }
        }
        preOrder(root, f);
        f.writeBytes("\r\n");
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
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------+-----------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        preOrder(root, count);
        Node p = null;
        for (Node temp : count) {
            if (temp.left != null && temp.info.price >= 30 && temp.info.price <= 70) {
                p = temp;
                break;
            }
        }
        Node r = rotateRight(p);
        Node fa = father(p.info.price);
        if (fa == null) {
            root = r;
        } else {
            if (fa.left == p) {
                fa.left = r;
            } else {
                fa.right = r;
            }
        }

        preOrder(root, f);
        f.writeBytes("\r\n");
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
        inOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------+-----------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        inOrder(root, count);
        Node p = count.get(4);
        int k = height(p);
        f.writeBytes(String.valueOf(k));

//        preOrder(root, f);
//        f.writeBytes("\r\n");
//        f.close();
    }

    int count(Node p) {
        if (p == null) {
            return 0;
        }

        int leftCount = count(p.left);
        int rightCount = count(p.right);

        return 1 + leftCount + rightCount;
    }

    void f10(int line) throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------+-----------------------------------------------------------------
        ArrayList<Node> count = new ArrayList<>();
        inOrder(root, count);
        Node p = count.get(4);
        int k = count(p);
        f.writeBytes(String.valueOf(k));

//        preOrder(root, f);
//        f.writeBytes("\r\n");
//        f.close();
    }

}
