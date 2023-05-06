public class Frame {
    private boolean referenced;
    private char letter;
    private Page pageContainer;

    public Frame(char letter) {
        this.referenced = false;
        this.letter = letter;
        this.pageContainer = null;
    }

    public void setReferenced(boolean referenced){
        this.referenced = referenced;
    }

    public boolean getReferenced(){
        return this.referenced;
    }

    public void setPageContainer(Page page){
        this.pageContainer = page;
    }

    public Page getPageContainer(){
        return this.pageContainer;
    }
    public char getLetter(){
        return this.letter;
    }

    public String toString(){
        return "letter:"+this.letter+" referenced:"+this.referenced+" pageContainer:"+this.pageContainer;
    }
}
