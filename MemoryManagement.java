import java.util.*;

public class MemoryManagement {
    LinkedList<Block> memory;

    public MemoryManagement(LinkedList<Block> memory) {
        this.memory = memory;
    }

    public void allocateProcess(String processID, int size, int strategy) {
        boolean allocatedSuccessfully = false;
        switch (strategy) {
            case 1:
                for (int i = 0; i < memory.size(); i++) {
                    Block b = memory.get(i);
                    if (b.getIsAllocated() == false && size <= b.getSize()) {
                        b.setProcessID(processID);
                        b.setIsAllocated(true);
                        b.setInternalFragmentation(b.getSize() - size);
                        System.out.println(b.getProcessID() + " allocated at address: " + b.getStartAddress()
                                + ", and the internal fragmentation is: " + b.getInternalFragmentation());

                        allocatedSuccessfully = true;
                        break;
                    }
                }
                if (!allocatedSuccessfully){System.out.println("There is no space in the memory for your process:(");}
                break;

            case 2:
                int min = -1;
                for (int i = 0; i < memory.size(); i++) {
                    Block b = memory.get(i);
                    if (b.getIsAllocated() == false && size <= b.getSize()) {
                        min = i;
                        break;
                    }
                }
                if (min != -1){
                    for (int i = min+1; i < memory.size(); i++) {
                        Block b = memory.get(i);
                        if (b.getIsAllocated() == false && size <= b.getSize() && b.getSize() < memory.get(min).getSize()) {
                            min = i;
                        }
                    }
                    Block b = memory.get(min);
                    b.setProcessID(processID);
                    b.setIsAllocated(true);
                    b.setInternalFragmentation(b.getSize() - size);
                    System.out.println(b.getProcessID() + " allocated at address: " + b.getStartAddress()
                            + ", and the internal fragmentation is: " + b.getInternalFragmentation());
                }
                else
                    System.out.println("There is no space in the memory for your process:(");              
                
                break;
            case 3:
                int max = -1;
                for (int i = 0; i < memory.size(); i++) {
                    Block b = memory.get(i);
                    if (b.getIsAllocated() == false && size <= b.getSize()) {
                        max = i;
                        break;
                    }
                }
                if (max != -1){
                    for (int i = max+1; i < memory.size(); i++) {
                        Block b = memory.get(i);
                        if (b.getIsAllocated() == false && size <= b.getSize() && b.getSize() > memory.get(max).getSize()) {
                            max = i;
                        }
                    }                
                    Block b = memory.get(max);
                    b.setProcessID(processID);
                    b.setIsAllocated(true);
                    b.setInternalFragmentation(b.getSize() - size);
                    System.out.println(b.getProcessID() + " allocated at address: " + b.getStartAddress()
                            + ", and the internal fragmentation is: " + b.getInternalFragmentation());
                    }
                    else
                        System.out.println("There is no space in the memory for your process:(");               
                break;

            default:
                System.out.println("Invalid strategy");
                break;
        }
    }

    public void deAllocateProcess(String processID) {
        boolean deAllocatedSuccessfully = false;
        for (int i = 0; i < memory.size(); i++) {
            Block b = memory.get(i);
            if (b.getIsAllocated() && processID.equalsIgnoreCase(b.getProcessID())) {
                b.setIsAllocated(false);
                b.setInternalFragmentation(0);
                System.out.println(b.getProcessID() + " is deallocated from address " + b.getStartAddress());

                b.setProcessID(null);
                deAllocatedSuccessfully = true;
                break;
            }
        }
        if (!deAllocatedSuccessfully){
            System.out.println("There is no such process with the id " + processID + " has been found in the memory");
        }
    }

public void printMemoryReport() {

    System.out.println("=================================================================================");
    System.out.println("Block#\tSize\tStart-End\tStatus\t\tProcessID\tFragmentation");
    System.out.println("=================================================================================");

    for (int i = 0; i < memory.size(); i++) {

        Block block = memory.get(i);
        System.out.printf("Block%d\t%d\t%d-%d\t\t%-10s\t%s\t\t%d\n",

                i, block.getSize(), block.getStartAddress(), block.getEndAddress(),

                (block.getIsAllocated() ? "Allocated" : "Free"),

                block.getProcessID(), block.getInternalFragmentation());
    }
    System.out.println("=================================================================================");
}
}
