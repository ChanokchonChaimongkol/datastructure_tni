public class TrainLinkedList {

    static void main() {

        LinkedList greeline = new LinkedList();

        //12
        //12.1
        System.out.println();
        System.out.println();

        greeline.insert("Mo-chit");
        //12.2
        greeline.insert("Ari");
        //12.3
        greeline.insert("Sanam-Pao");
        //12.4
        System.out.println("All Station   : " + greeline.traversal());
        //12.5
        greeline.insert(2,"Rachakru");
        //12.6
        System.out.println("All Station   : " + greeline.traversal());
        //12.7
        System.out.println("Total Station : " + greeline.length());
        //12.8
        System.out.println("First Station : " + greeline.get(0));
        //12.9
        greeline.set(0,"Mo-chit (Central Ladprao)");
        //12.10
        System.out.println("All Station   : " + greeline.traversal());
        //12.11
        greeline.remove(2);
        //12.12
        System.out.println("All Station   : " + greeline.traversal());
        //12.13
        greeline.removeLastElement();
        //12.14
        System.out.println("All Station   : " + greeline.traversal());
        //12.15
        greeline.clear();
        //12.16
        System.out.println("Total Station : " + greeline.length());
        //12.17
        System.out.println("All Station   : " + greeline.traversal());



    }

}
