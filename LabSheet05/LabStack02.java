import java.util.Stack;

public class LabStack02 {
    public static void main(String[] args) {
        Stack<Integer> track = new Stack<>();
        int[] trains = {1, 2, 3, 4, 5};

        
        for (int i = 0; i < trains.length; i++) {
            track.push(trains[i]);
            System.out.println("Push in track : " + trains[i]);
        }
        System.out.println("Train car after into dead-end track : " + track);

        System.out.println();

        
        while (!track.isEmpty()) {
            int car = track.pop();
            System.out.println("Pop from track: " + car);
        }
        System.out.println("Train car after out of dead-end track : " + track);
    }
}