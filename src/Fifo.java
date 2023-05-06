
import java.util.ArrayList;
import java.util.List;

public class Fifo {

    private List<Frame>frameList = new ArrayList<Frame>();

    public Fifo() {
    }

    public void addFrame(Frame frame){
        frameList.add(frame);
    }

    //this is the getfirst function that returns the first frame in the frameList.
    public Frame getFirst(){
        return frameList.get(0);
    }

    //this is the removeFirst function that removes the first frame in the frameList.
    public Frame removeFirst(){
        return frameList.remove(0);
    }

    public Frame getFirstNotLocked(){
        for(Frame f:frameList){
            if(f.getPageContainer().getLockedFor() == 0){
                return f;
            }
        }
        return null;
    }
    public Frame removeFrame(Frame f){
        frameList.remove(f);
        return f;
    }

    public void reduceLockedForFramesInFifo(){
        for(Frame f:frameList){
            f.getPageContainer().reduceLockedFor();
        }
    }
}
