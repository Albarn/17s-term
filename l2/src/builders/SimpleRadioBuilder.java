package builders;

import audioOutputs.Radio;

public class SimpleRadioBuilder extends AudioOutputBuilder {

    @Override
    public void createNewAudioOutputProduct() {
        audioOutput = new Radio();
    }

    @Override
    public void buildName() {
        audioOutput.name = "BBK BS05 blue";
    }

    @Override
    public void buildPrice() {
        audioOutput.setPrice(599);
    }

    @Override
    public void buildPower() {
        audioOutput.setPower(2.4);
    }

    @Override
    public void buildWeight() {
        audioOutput.setWeight(870);
    }

    @Override
    public void buildInheritedFields() {
        Radio radio = (Radio) audioOutput;
        radio.CD = true;
        radio.AM = true;
        radio.FM = true;
    }
}
