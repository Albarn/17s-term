public class Speakers extends AudioOutput {
    //playback frequency in Hz
    private double minFrequency;
    private double maxFrequency;

    public boolean portable;

    //default constructor
    public Speakers(String name) {
        //call super class constructor
        //and set default values
        super(name);
        minFrequency = maxFrequency = 0;
        portable = false;
    }


    @Override
    //toString method with additional parameters
    public String toString() {
        return super.toString() + minFrequency + "-" + maxFrequency + " Hz " + (portable ? "portable" : "");
    }

    //getter for maxFrequency
    public double GetMaxFrequency() {
        return maxFrequency;
    }

    //set correct frequency below 70 KHz
    public void SetMaxFrequency(double maxFrequency) {
        if (maxFrequency > minFrequency && maxFrequency < 70000)
            this.maxFrequency = maxFrequency;
    }

    //getter for minFrequency
    public double GetMinFrequency() {
        return minFrequency;
    }

    //set correct frequency above zero
    public void SetMinFrequency(double minFrequency) {
        if (minFrequency > 0 && minFrequency < maxFrequency)
            this.minFrequency = minFrequency;
    }

    @Override
    //set power below 1500W
    public void SetPower(double power) {
        if (power > 0 && power < 1500)
            this.power = power;
    }

    @Override
    //set price under 5500000 hrn
    public void SetPrice(double price) {
        if (price > 50 && price < 5500000)
            this.price = price;
    }

    @Override
    //set weight between 1000 and 10000
    public void SetWeight(double weight) {
        if (weight > 1000 && weight < 10000)
            this.weight = weight;
    }
}
