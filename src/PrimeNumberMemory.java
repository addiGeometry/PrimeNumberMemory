import Grafics.MemoryUI;

public class PrimeNumberMemory {
    public static void main(String args[]){


        MemoryUI userCMD = new MemoryUI(args[0],System.in, System.out);

        userCMD.printInstructions();
        userCMD.doCommandLoop();
    }
}
