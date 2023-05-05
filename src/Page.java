public class Page {
    private int number;
    private int lockedFor = 3;

    public Page(int number) {
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }
    public int getLockedFor(){
        return this.lockedFor;
    }
    public void setLockedFor(int lockedFor){
        this.lockedFor = lockedFor;
    }
    public void reduceLockedFor(){
        this.lockedFor--;
    }
}
