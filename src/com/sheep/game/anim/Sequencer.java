package com.sheep.game.anim;

import java.util.Hashtable;

public class Sequencer {
    public float duration;
    public boolean looping;

    public Hashtable<String, Object> outputs;
    public ISequence sequence;

    float time;
    int loopIteration;
    boolean running;

    public Sequencer(float duration, boolean shouldLoop, ISequence sequence){
        this.duration = duration;
        this.looping = shouldLoop;
        this.sequence = sequence;
        outputs = new Hashtable<>();

        sequence.OnInit(this);
    }

    public void Play(){
        time = 0;
        running = true;
        sequence.OnPlay(this);
    }

    public void Tick(){
        if(!running)
            return;

        time += 1/60f;

        if(time > duration){
            if(looping){
                time = 0;
                loopIteration++;
                sequence.OnLoop(this);
            }
            else
                Stop();
        }else {
            sequence.OnTick(this);
        }
    }

    public void Stop(){
        running = false;
        sequence.OnStop(this);
    }

    public float getTime() {
        return time;
    }

    public float getNormalizedTime(){
        return time/duration;
    }

    public float getDuration() {
        return duration;
    }

    public int getLoopIteration() {
        return loopIteration;
    }

    public Hashtable<String, Object> getOutputs() {
        return outputs;
    }
}
