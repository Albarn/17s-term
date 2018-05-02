package sample.audioOutputs;

public class Radio
        extends AudioOutput{

    public boolean AM;
    public boolean FM;
    public boolean CD;
    public boolean USB;

    @Override
    //toString method with additional parameters
    public String toString() {
        return super.toString() + (AM ? " AM" : "") + (FM ? " FM" : "") + (CD ? " CD" : "") + (USB ? " USB" : "");
    }

    @Override
    //set power below 900W
    public void setPower(double power) throws IllegalArgumentException {
        if (power > 0 && power < 900)
            this.power = power;
        else
            throw new IllegalArgumentException("power should be under 900 and above 0");
    }

    @Override
    //set price under 1500000 hrn
    public void setPrice(double price) throws IllegalArgumentException {
        if (price > 500 && price < 1500000)
            this.price = price;
        else
            throw new IllegalArgumentException("price should be under 1 500 000 and above 500");
    }

    @Override
    //set weight between 500 and 4000
    public void setWeight(double weight) throws IllegalArgumentException {
        if (weight > 500 && weight < 4000)
            this.weight = weight;
        else
            throw new IllegalArgumentException("weight should be under 4 000 and above 500");
    }
}
