package sample.audioOutputs;

public class Headphones extends AudioOutput {
    //cable length in metres
    private double length;
    //playback frequency in Hz
    private double minFrequency;
    private double maxFrequency;

    public boolean garniture;

    @Override
    //toString method with additional parameters
    public String toString() {
        return super.toString() + " " + length + " m " + minFrequency + "-" + maxFrequency + " Hz " + (garniture ? "garniture" : "");
    }

    //getter for maxFrequency
    public double getMaxFrequency() {
        return maxFrequency;
    }

    //set correct frequency below 30 KHz
    public void setMaxFrequency(double maxFrequency) throws IllegalArgumentException{
        if (maxFrequency > minFrequency && maxFrequency < 30000)
            this.maxFrequency = maxFrequency;
        else
            throw new IllegalArgumentException("max freq should be under 30 000 and above min freq");
    }

    //getter for minFrequency
    public double getMinFrequency() {
        return minFrequency;
    }

    //set correct frequency above zero
    public void setMinFrequency(double minFrequency) throws IllegalArgumentException {
        if (minFrequency > 0 && minFrequency < maxFrequency)
            this.minFrequency = minFrequency;
        else
            throw new IllegalArgumentException("min freq should be under max freq and above 0");
    }

    //set correct length between 0 and 3 metres
    public void setLength(double length) throws IllegalArgumentException {
        if (length > 0 && length < 3)
            this.length = length;
        else
            throw new IllegalArgumentException("length should be under 3 and above 0");
    }

    //getter for length
    public double getLength() {
        return length;
    }

    @Override
    //set power below 1W
    public void setPower(double power) throws IllegalArgumentException {
        if (power > 0 && power < 1)
            this.power = power;
        else
            throw new IllegalArgumentException("power should be under 1 and above 0");
    }

    @Override
    //set price under 140000 hrn
    public void setPrice(double price) throws IllegalArgumentException {
        if (price > 30 && price < 140000)
            this.price = price;
        else
            throw new IllegalArgumentException("price should be under 140 000 and above 30");
    }

    @Override
    //set weight below 600g
    public void setWeight(double weight) {
        if (weight > 0 && weight < 600)
            this.weight = weight;
        else
            throw new IllegalArgumentException("weight should be under 600 and above 0");
    }
}
