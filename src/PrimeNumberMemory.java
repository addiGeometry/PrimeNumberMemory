import Grafics.MemoryUI;

public class PrimeNumberMemory {
    public static void main(String args[]){
        System.out.println("Welcome to Prime Number Memory version 1.0");

        if(args.length < 1){
            System.err.println("there needs to be a player for a game");
            System.exit(1);
        }

        System.out.println("Greatings " + args[0]);
        System.out.println("Enjoy the ride");

        MemoryUI userCMD = new MemoryUI(args[0],System.in, System.out);

        userCMD.printInstructions();
        userCMD.doCommandLoop();
    }
}
