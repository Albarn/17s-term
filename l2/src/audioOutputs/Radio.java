package audioOutputs;

public class Radio
        extends AudioOutput
        implements Runnable {

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
    public void setPower(double power) {
        if (power > 0 && power < 900)
            this.power = power;
    }

    @Override
    //set price under 1500000 hrn
    public void setPrice(double price) {
        if (price > 500 && price < 1500000)
            this.price = price;
    }

    @Override
    //set weight between 500 and 4000
    public void setWeight(double weight) {
        if (weight > 500 && weight < 4000)
            this.weight = weight;
    }

    public Radio(){

    }

    public Radio(RadioState radioState){
        radioThread=new Thread(this);
        this.radioState =radioState;
    }

    private Thread radioThread;

    public Thread getRadioThread() {
        return radioThread;
    }

    private Thread hifiThread=null;

    public Thread getHifiThread() {
        return hifiThread;
    }

    public void setHifiThread(Thread hifiThread) {
        this.hifiThread = hifiThread;
    }

    private RadioState radioState;

    @Override
    public void run() {
        synchronized (radioState){

            //radio works
            while (true) {

                //when radio does not work we should make this thread WAITING
                if(!radioState.isWorking || hifiThread.getState()==Thread.State.RUNNABLE){

                    //notify for hi-fi system
                    radioState.notify();
                    try {

                        //wait notification
                        radioState.wait();
                    } catch (InterruptedException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
        }
    }
}
