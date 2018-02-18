package builders;

import audioOutputs.Headphones;

public class SimpleHeadphonesBuilder extends AudioOutputBuilder{

    @Override
    public void createNewAudioOutputProduct() {
        audioOutput=new Headphones();
    }

    @Override
    public void buildName() {
        audioOutput.name="Defender Trendy-705";
    }

    @Override
    public void buildPrice() {
        audioOutput.setPrice(37);
    }

    @Override
    public void buildPower() {
        audioOutput.setPower(0.5);
    }

    @Override
    public void buildWeight() {
        audioOutput.setWeight(15);
    }

    @Override
    public void buildInheritedFields() {
        Headphones hp=(Headphones)audioOutput;
        hp.setLength(1.1);
        hp.setMaxFrequency(20000);
        hp.setMinFrequency(20);
    }
}
