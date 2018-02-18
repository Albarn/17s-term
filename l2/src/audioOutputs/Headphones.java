package audioOutputs;

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
    public void setMaxFrequency(double maxFrequency) {
        if (maxFrequency > minFrequency && maxFrequency < 30000)
            this.maxFrequency = maxFrequency;
    }

    //getter for minFrequency
    public double getMinFrequency() {
        return minFrequency;
    }

    //set correct frequency above zero
    public void setMinFrequency(double minFrequency) {
        if (minFrequency > 0 && minFrequency < maxFrequency)
            this.minFrequency = minFrequency;
    }

    //set correct length between 0 and 3 metres
    public void setLength(double length) {
        if (length > 0 && length < 3)
            this.length = length;
    }

    //getter for length
    public double getLength() {
        return length;
    }

    @Override
    //set power below 1W
    public void setPower(double power) {
        if (power > 0 && power < 1)
            this.power = power;
    }

    @Override
    //set price under 140000 hrn
    public void setPrice(double price) {
        if (price > 30 && price < 140000)
            this.price = price;
    }

    @Override
    //set weight below 600g
    public void setWeight(double weight) {
        if (weight > 0 && weight < 600)
            this.weight = weight;
    }
}
