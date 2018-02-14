public class Radio extends AudioOutput {

    public boolean AM;
    public boolean FM;
    public boolean CD;
    public boolean USB;

    //default constructor
    public Radio(String name) {
        //call super class constructor
        //and set default values
        super(name);
        AM = FM = CD = USB = false;
    }


    @Override
    //toString method with additional parameters
    public String toString() {
        return super.toString() + (AM ? " AM" : "") + (FM ? " FM" : "") + (CD ? " CD" : "") + (USB ? " USB" : "");
    }

    @Override
    //set power below 900W
    public void SetPower(double power) {
        if (power > 0 && power < 900)
            this.power = power;
    }

    @Override
    //set price under 1500000 hrn
    public void SetPrice(double price) {
        if (price > 500 && price < 1500000)
            this.price = price;
    }

    @Override
    //set weight between 500 and 4000
    public void SetWeight(double weight) {
        if (weight > 500 && weight < 4000)
            this.weight = weight;
    }
}
