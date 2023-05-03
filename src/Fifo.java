import java.util.ArrayList;
import java.util.List;

public class Fifo {
    private List<Frame> frameList = new ArrayList<>();
    private Frame move;
    private boolean turn = false;
    Fifo(int numberOfFrames){

    }

    public void add(Frame f){
        if(frameList.size() == 0){
            frameList.add(f);
            move = frameList.get(0);
        }else{
            move.setNext(f);
            move = move.getNext();
        }
    }

    public void close(){
        if(move != null)
            move.setNext(frameList.get(0));
    }

    public Frame getBeginning(){
        return frameList.get(0);
    }

    public void increaseFrozenFor(){
        move = getBeginning();
        while(turn != true){
            if(move != null){
                move.frozenFor++;
                move = move.getNext();
                if(move == getBeginning()|| move == null)
                    turn = true;
            }else
                break;
        }
    }

    public void turn(int steps){
        for(int i = 0; i < steps; i++){
            getBeginning().SetWasUsed(false);
            frameList = frameList.get(0).getNext();

        }
    }
}
