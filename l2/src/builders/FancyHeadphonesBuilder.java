package builders;

import audioOutputs.Headphones;

public class FancyHeadphonesBuilder extends AudioOutputBuilder {

    @Override
    public void createNewAudioOutputProduct() {
        audioOutput=new Headphones();
    }

    @Override
    public void buildName() {
        audioOutput.name="Focal Utopia";
    }

    @Override
    public void buildPrice() {
        audioOutput.setPrice(136725);
    }

    @Override
    public void buildPower() {
        audioOutput.setPower(0.5);
    }

    @Override
    public void buildWeight() {
        audioOutput.setWeight(490);
    }

    @Override
    public void buildInheritedFields() {
        Headphones hp=(Headphones)audioOutput;
        hp.setLength(3);
        hp.setMinFrequency(5);
        hp.setMaxFrequency(50000);
    }
}
