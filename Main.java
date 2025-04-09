import java.util.*;

public class Main {
  public static void main (String [] args){

    LinkedList<Block> listOfBlocks = new LinkedList<>();
    MemoryManagement memo = new MemoryManagement(listOfBlocks);

    Scanner read = new Scanner(System.in);
    System.out.print("Enter the total number of blocks: ");
    int numOfBlocks = read.nextInt();

    int sizeOfBlock = 0;
    int startOfNextMemory = 0;
    System.out.print("Enter the size of each block in KB: ");
    for (int i = 0 ; i < numOfBlocks ; i++){
        sizeOfBlock = read.nextInt();
        Block b = new Block(sizeOfBlock, startOfNextMemory, startOfNextMemory+(sizeOfBlock-1));
        listOfBlocks.add(b);
        startOfNextMemory+= sizeOfBlock;
    }

    System.out.print("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): ");
    int strategy = read.nextInt();

    System.out.println("Memory blocks are created…\nMemory blocks:");
    System.out.println("============================================");
    System.out.println("Block#\tSize\tStart-End\tStatus");
    System.out.println("============================================");

    for (int i = 0; i < listOfBlocks.size(); i++) {

        Block block = listOfBlocks.get(i);
        System.out.printf("Block%d\t%d\t%d-%d\t\t%s\n",

                i, block.getSize(), block.getStartAddress(), block.getEndAddress(),

                (block.getIsAllocated() ? "Allocated" : "Free"));
    }
    System.out.println("============================================");


    int choice = 0;
    String pID;
    int pSize;
    do{
        System.out.print("1) Allocates memory blocks\n2) De-allocates memory blocks\n");
        System.out.print("3) Print report about the current state of memory and internal Fragmentation\n4) Exit\n");
        System.out.println("============================================");

        System.out.print("Enter your choice: ");
        choice = read.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter the process ID and size of process: ");
                pID = read.next();
                pSize = read.nextInt();
                memo.allocateProcess(pID,pSize,strategy);
                break;
            case 2:
                System.out.print("Enter the process ID: ");
                pID = read.next();
                memo.deAllocateProcess(pID);               
                break;
            case 3:
                memo.printMemoryReport();
                break;
            case 4:
                System.out.println("All memory freed! (o^3^o) Don't forget me~");
                break;
            default:
                System.out.println("invalid input, Try a number from 1 to 4");
        }
        System.out.println("============================================");

    } while(choice != 4);







  } 
}
