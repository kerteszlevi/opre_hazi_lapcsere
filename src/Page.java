public class Page {
    private int number;
    private int lockedFor = 4;

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
        if(0<this.lockedFor)this.lockedFor--;
    }
    public String toString(){
        return "number:"+this.number+" lockedFor:"+this.lockedFor;
    }
}
