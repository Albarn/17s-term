package builders;

import audioOutputs.AudioOutput;

import java.util.ArrayList;

//Director for builders.AudioOutputBuilder
//contents collection of created objects
public class HiFiSystem {
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
}
