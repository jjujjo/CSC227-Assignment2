public class Block {
    int size;
    int startAddress;
    int endAddress;
    boolean isAllocated;
    String processID;
    int internalFragmentation;

    public Block(int size, int startAddress, int endAddress) {
        this.size = size;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        isAllocated = false;
        processID = null;
        internalFragmentation = 0;
    }

    public void setIsAllocated(boolean isAllocated) {
        this.isAllocated = isAllocated;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public void setInternalFragmentation(int internalFragmentation) {
        this.internalFragmentation = internalFragmentation;
    }

    public int getSize() {
        return size;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public int getEndAddress() {
        return endAddress;
    }

    public boolean getIsAllocated() {
        return isAllocated;
    }

    public String getProcessID() {
        return processID;
    }

    public int getInternalFragmentation() {
        return internalFragmentation;
    }
}