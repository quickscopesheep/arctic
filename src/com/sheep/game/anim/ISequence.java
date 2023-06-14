package com.sheep.game.anim;

public abstract class ISequence {
    public abstract void OnInit(Sequencer sequencer);
    public abstract void OnPlay(Sequencer sequencer);
    public abstract void OnTick(Sequencer sequencer);
    public abstract void OnStop(Sequencer sequencer);

    public void OnLoop(Sequencer sequencer){

    }
}
