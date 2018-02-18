package builders;

import audioOutputs.AudioOutput;

//abstract builder for audio outputs
//builds name, price, power, weight
//and inherited fields for headphones,
//radio and speakers
abstract public class AudioOutputBuilder {
    //object field
    protected AudioOutput audioOutput;

    //getter for audioOutput
    public AudioOutput getAudioOutput() {
        return audioOutput;
    }

    //new object creation
    public abstract void createNewAudioOutputProduct();

    //build methods
    public abstract void buildName();
    public abstract void buildPrice();
    public abstract void buildPower();
    public abstract void buildWeight();
    public abstract void buildInheritedFields();
}
