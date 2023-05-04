public class Frame {
    private boolean referenced;
    private char letter;
    private Frame frameContainer;

    public Frame(char letter) {
        this.referenced = false;
        this.letter = letter;
        this.frameContainer = null;
    }

    public void setReferenced(boolean referenced){
        this.referenced = referenced;
    }

    public boolean getReferenced(){
        return this.referenced;
    }

    public void setFrame(Frame frame){
        this.frameContainer = frame;
    }
}
