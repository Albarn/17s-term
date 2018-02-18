package builders;

import audioOutputs.Speakers;

public class FancySpeakersBuilder extends AudioOutputBuilder{

    @Override
    public void createNewAudioOutputProduct() {
        audioOutput=new Speakers();
    }

    @Override
    public void buildName() {
        audioOutput.name="Gauder Akustik Berlina RC 11";
    }

    @Override
    public void buildPrice() {
        audioOutput.setPrice(5126380);
    }

    @Override
    public void buildPower() {
        audioOutput.setPower(970);
    }

    @Override
    public void buildWeight() {
        audioOutput.setWeight(230000);
    }

    @Override
    public void buildInheritedFields() {
        Speakers sp=(Speakers)audioOutput;
        sp.setMinFrequency(65);
        sp.setMaxFrequency(20000);
    }
}
