package audioOutputs;

//audio output device class
//патерн builder hi fi система
//построение системы из данных классов
public abstract class AudioOutput {
    //name of device
    public String name;
    //price in ukrainian hryvnia
    protected double price;
    //device power in W
    protected double power;
    //device weight in grammes
    protected double weight;

    //sorting devices by their name
    public static AudioOutput[] sortByName(AudioOutput[] devices) {

        int n = devices.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                //common bubble sort
                //compare and exchange
                if (devices[i].name.compareTo(devices[j].name) > 0) {
                    AudioOutput z = devices[i];
                    devices[i] = devices[j];
                    devices[j] = z;
                }
            }
        }
        //return sorted array
        return devices;
    }

    //sort devices by their price
    public static AudioOutput[] sortByPrice(AudioOutput[] devices) {

        int n = devices.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                //same bubble sort as in sortByName method
                if (devices[i].price > devices[j].price) {
                    AudioOutput z = devices[i];
                    devices[i] = devices[j];
                    devices[j] = z;
                }
            }
        }
        //return sorted array
        return devices;
    }


    //find device in array with maximum price
    public static AudioOutput maxPrice(AudioOutput[] devices) {
        int n = devices.length;
        //if no elements in array - return null
        if (n == 0) return null;
        int max = 0;
        for (int i = 1; i < n; i++)
            //compare and store
            if (devices[i].price > devices[max].price)
                max = i;

        //return max element
        return devices[max];
    }

    //find device in array with minimum price
    public static AudioOutput minPrice(AudioOutput[] devices) {
        int n = devices.length;
        //if no elements in array - return null
        if (n == 0) return null;
        int min = 0;
        for (int i = 1; i < n; i++)
            //compare and store
            if (devices[i].price < devices[min].price)
                min = i;

        //return element with minimum price
        return devices[min];
    }

    @Override
    //object in string expression
    public String toString() {
        return name + " " + price + " hrn " + power + " W " + weight + " g";
    }

    //getter for price
    public double getPrice() {
        return price;
    }

    //set correct positive price
    public abstract void setPrice(double price);

    //getter for power
    public double getPower() {
        return power;
    }

    //set correct positive power
    public abstract void setPower(double power);

    //getter for weight
    public double getWeight() {
        return weight;
    }

    //set correct positive weight
    public abstract void setWeight(double weight);
}
