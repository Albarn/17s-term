package builders;

import audioOutputs.AudioOutput;
import audioOutputs.RadioState;

import java.io.Serializable;
import java.util.ArrayList;

//Director for builders.AudioOutputBuilder
//contents collection of created objects
public class HiFiSystem implements Serializable, Runnable {
    //builder field
    private AudioOutputBuilder audioOutputBuilder;

    //list of created objects
    public ArrayList<AudioOutput> constructedObjects = new ArrayList<AudioOutput>();

    //setter for audioOutputBuilder
    public void setAudioOutputBuilder(AudioOutputBuilder audioOutputBuilder) {
        this.audioOutputBuilder = audioOutputBuilder;
    }

    //getter for last created object
    public AudioOutput getAudioOutput() {
        return audioOutputBuilder.getAudioOutput();
    }

    //construct new object
    public void constructAudioOutput() {
        audioOutputBuilder.createNewAudioOutputProduct();
        audioOutputBuilder.buildName();
        audioOutputBuilder.buildPower();
        audioOutputBuilder.buildPrice();
        audioOutputBuilder.buildWeight();
        audioOutputBuilder.buildInheritedFields();
        constructedObjects.add(audioOutputBuilder.getAudioOutput());
    }

    public  HiFiSystem(){

    }

    public  HiFiSystem(RadioState radioState){
        this.radioThread=radioThread;
        this.radioState = radioState;
        hifiThread=new Thread(this);
    }

    private Thread hifiThread=null;

    public Thread getHifiThread() {
        return hifiThread;
    }

    private Thread radioThread=null;

    public Thread getRadioThread() {
        return radioThread;
    }

    public void setRadioThread(Thread radioThread) {
        this.radioThread = radioThread;
    }

    private RadioState radioState;

    @Override
    public void run() {
        if(radioThread==null) {
            throw new IllegalThreadStateException();
        }
        synchronized (radioState){

            //hi-fi works works
            while (true) {

                //when radio works we should make this thread WAITING
                if(radioState.isWorking || radioThread.getState()==Thread.State.RUNNABLE){

                    //notify for radio
                    radioState.notify();
                    try {

                        //wait for notification
                        radioState.wait();
                    } catch (InterruptedException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
        }
    }
}
