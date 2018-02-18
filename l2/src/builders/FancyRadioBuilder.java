package builders;

import audioOutputs.Radio;

public class FancyRadioBuilder extends AudioOutputBuilder {

    @Override
    public void createNewAudioOutputProduct() {
        audioOutput=new Radio();
    }

    @Override
    public void buildName() {
        audioOutput.name="Jadis Calliope";
    }

    @Override
    public void buildPrice() {
        audioOutput.setPrice(1094800);
    }

    @Override
    public void buildPower() {
        audioOutput.setPower(2);
    }

    @Override
    public void buildWeight() {
        audioOutput.setWeight(1000);
    }

    @Override
    public void buildInheritedFields() {
        Radio radio=(Radio)audioOutput;
        radio.CD=true;
    }
}
