package builders;

import audioOutputs.Speakers;

public class SimpleSpeakersBuilder extends AudioOutputBuilder {

    @Override
    public void createNewAudioOutputProduct() {
        audioOutput=new Speakers();
    }

    @Override
    public void buildName() {
        audioOutput.name="Kitsound Mini Buddy Speaker Father Chris";
    }

    @Override
    public void buildPrice() {
        audioOutput.setPrice(67);
    }

    @Override
    public void buildPower() {
        audioOutput.setPower(2);
    }

    @Override
    public void buildWeight() {
        audioOutput.setWeight(1100);
    }

    @Override
    public void buildInheritedFields() {
        Speakers sp=(Speakers)audioOutput;
        sp.setMaxFrequency(20000);
        sp.setMinFrequency(100);
    }
}
