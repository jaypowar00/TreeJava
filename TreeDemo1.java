import java.util.ArrayList;
import java.util.Collections;

public class TreeDemo1 {

    private TreeNode root;
    private int maxLevel=0;
    private ArrayList<ArrayList<Integer>> NodesInArray = new ArrayList<>();

    private class TreeNode {
        private int data;
        private TreeNode left=null;
        private TreeNode right=null;

        TreeNode(int data) {
            this.data = data;
        }
    }

    private void createBinaryTree() {

        //Add more nodes if you wish...

        TreeNode first = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode third = new TreeNode(3);
        TreeNode fourth = new TreeNode(4);
        TreeNode fifth = new TreeNode(5);
        TreeNode sixth = new TreeNode(6);
        TreeNode seventh = new TreeNode(7);
        TreeNode eigth = new TreeNode(8);
        TreeNode nine = new TreeNode(9);
        TreeNode ten = new TreeNode(10);
        TreeNode eleven = new TreeNode(11);
        TreeNode twelve = new TreeNode(12);
        TreeNode thirteen = new TreeNode(13);
        TreeNode fourteen = new TreeNode(14);
        TreeNode fifteen = new TreeNode(15);

        root = first;           //root 2-1-3
        first.left = second;
        first.right = third;
        second.left = fourth;   //2     4-2-5
        second.right = fifth;
        third.left = sixth;     //3     6-3-7
        third.right = seventh;
        fourth.left = eigth;    //4     8-4-9
        fourth.right = nine;
        fifth.left = ten;       //5     10-5-11
        fifth.right = eleven;
        sixth.left = twelve;    //6     12-6-13
        sixth.right = thirteen;
        seventh.left = fourteen;//7     14-7-15
        seventh.right = fifteen;

    }

    private void traversePreOrder(TreeNode node){
        if (node.left != null)
            traversePreOrder(node.left);
        System.out.print(node.data+" ");
        if (node.right != null)
            traversePreOrder(node.right);
    }

    private void fetchLevelsAndNodes(TreeNode node, int level) {
        if(level >= maxLevel)
            maxLevel = level;
        ArrayList<Integer> temp;
        if(NodesInArray.size()-1 < level) {
            temp = new ArrayList<>();
            NodesInArray.add(temp);
        }else {
            temp = NodesInArray.get(level);
        }
        if(node.left != null)
            fetchLevelsAndNodes(node.left, level+1);
        temp.add(node.data);
        if (node.right != null)
            fetchLevelsAndNodes(node.right, level+1);
    }

    private ArrayList<Integer> getSlashCounts(){
        ArrayList<Integer> series = new ArrayList<>();
        series.add(1);
        int a=-1, b=2, c, t=0;
        for(int k=0; k<maxLevel-1; k++) {
            b+=a;
            a+=2;
            c = series.get(series.size()-1);
            series.add(a+b+c);
        }
        return series;
    }

    private ArrayList<Integer> getInitialSpacesCounts() {
        ArrayList<Integer> series = new ArrayList<>();
        series.add(1);
        int a=-1, b=2, c, t=0;
        for(int k=0; k<maxLevel; k++) {
            b+=a;
            a+=2;
            c = series.get(series.size()-1);
            series.add(a+b+c);
        }
        series.remove(0);
        series.add(0, 0);
        return series;
    }

    private ArrayList<Integer> getMiddleSpacesCounts() {
        ArrayList<Integer> series = new ArrayList<>();
        int a=3;
        if(maxLevel>1)
            for(int i=0; i<maxLevel-1; i++) {
                a=a*2+2;
                series.add(a);
            }
        return series;
    }

    private void printTree(ArrayList<Integer> slashes, ArrayList<Integer> intSpaces, ArrayList<Integer> middleSpaces) {
        for(int i=0; i<NodesInArray.size(); i++) {
            ArrayList<Integer> temp = NodesInArray.get(i);
            for(int j=0; j < temp.size(); j++) {
                if(j==0)
                    for(int t=0; t<intSpaces.get(i); t++)
                        System.out.print(" ");
                else{
                    if(i == NodesInArray.size()-1) {
                        if(j%2==1)
                            System.out.print("    ");
                        else
                            System.out.print("  ");
                    }else {
                        if(i!=0) {
                            for(int t=0; t<middleSpaces.get(i-1); t++)
                                System.out.print(" ");
                        }
                    }
                }
                System.out.printf("%02d", temp.get(j));
            }
            System.out.println();
            if(i<slashes.size()) {
                for (int t = 0; t < slashes.get(i); t++) {
                    for (int p=0; p < temp.size(); p++) {
                        int st = slashes.get(i);
                        if (st != 1) {
                            if (p == 0) {
                                for (int t2=0; t2 < st*2+1-t; t2++) {
                                    System.out.print(" ");
                                }
                            } else {
                                for (int t2=0; t2 < (st*2+1)*2+2-(t*2); t2++) {
                                    System.out.print(" ");
                                }
                            }
                        } else {
                            if(p==0)
                                System.out.print("  ");
                            else
                                System.out.print("      ");
                        }
                        System.out.print("/");
                        if (st != 1) {
                            for (int t2 = 0; t2 < 2 * t + 2; t2++)
                                System.out.print(" ");
                            System.out.print("\\");
                            if(p==temp.size()-1)
                                System.out.println();
                        }else {
                            System.out.print("  ");
                            System.out.print("\\");
                            if(t==slashes.get(i)-1 && p==temp.size()-1)
                                System.out.println();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeDemo1 tree = new TreeDemo1();
        System.out.println("[=] Creating Binary Tree...");
        tree.createBinaryTree();
        System.out.print("[+] Pre-Order Traversal: ");
        tree.traversePreOrder(tree.root);
        System.out.println("\n[=] Fetching all levels and Nodes...");
        tree.fetchLevelsAndNodes(tree.root, 0);
        ArrayList<Integer> slashes= tree.getSlashCounts();
        ArrayList<Integer> spaces= tree.getInitialSpacesCounts();
        ArrayList<Integer> midSpaces= tree.getMiddleSpacesCounts();
        Collections.sort(slashes);
        Collections.reverse(slashes);
        Collections.sort(spaces);
        Collections.reverse(spaces);
        Collections.sort(midSpaces);
        Collections.reverse(midSpaces);
        System.out.println("[+] Slashes array: "+slashes);
        System.out.println("[+] Init-Spaces array: "+spaces);
        System.out.println("[+] Mid-Spaces array: "+midSpaces);
        System.out.println("[+] Array of all Nodes(level wise): "+tree.NodesInArray);
        System.out.println("[+] Tree Representation:");
        tree.printTree(slashes, spaces, midSpaces);
    }

}