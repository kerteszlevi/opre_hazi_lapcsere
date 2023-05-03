public class Frame {
    private char character;
    private int number;
    public int frozenFor = 0;
    private boolean wasUsed = false;
    private Frame next;

    Frame(char c){
        this.character = c;
    }

    public void setNext(Frame f){
        this.next = f;
    }

    public Frame getNext(){
        return this.next;
    }
    public boolean GetWasUsed(){
        return wasUsed;
    }
    public void SetWasUsed(boolean b){
        wasUsed = b;
    }
}
