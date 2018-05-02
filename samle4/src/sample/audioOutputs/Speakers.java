package sample.audioOutputs;

public class Speakers extends AudioOutput{
    //playback frequency in Hz
    private double minFrequency;
    private double maxFrequency;

    public boolean portable;

    @Override
    //toString method with additional parameters
    public String toString() {
        return super.toString() +" "+ minFrequency + "-" + maxFrequency + " Hz " + (portable ? "portable" : "");
    }

    //getter for maxFrequency
    public double getMaxFrequency() {
        return maxFrequency;
    }

    //set correct frequency below 70 KHz
    public void setMaxFrequency(double maxFrequency) throws IllegalArgumentException{
        if (maxFrequency > minFrequency && maxFrequency < 70000)
            this.maxFrequency = maxFrequency;
        else
            throw new IllegalArgumentException("max freq should be under 70 000 and above min freq");
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

    @Override
    //set power below 1500W
    public void setPower(double power) throws IllegalArgumentException {
        if (power > 0 && power < 1500)
            this.power = power;
        else
            throw new IllegalArgumentException("power should be under 1 500 and above 0");
    }

    @Override
    //set price under 5500000 hrn
    public void setPrice(double price) throws IllegalArgumentException {
        if (price > 50 && price < 5500000)
            this.price = price;
        else
            throw new IllegalArgumentException("price should be under 5 500 000 and above 50");
    }

    @Override
    //set weight between 1000 and 10000
    public void setWeight(double weight) throws IllegalArgumentException {
        if (weight > 1000 && weight < 10000)
            this.weight = weight;
        else
            throw new IllegalArgumentException("weight should be under 10 000 and above 1 000");
    }
}
