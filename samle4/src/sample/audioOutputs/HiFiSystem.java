package sample.audioOutputs;

import java.io.Serializable;
import java.util.ArrayList;

import java.io.*;

//Director for builders.AudioOutputBuilder
//contents collection of created objects
public class HiFiSystem implements Serializable {

    //list of created objects
    public ArrayList<AudioOutput> devices = new ArrayList<AudioOutput>();

    public String CustomerName;

    public void save(String fileName) throws IOException {
        //we use file stream for file
        FileOutputStream fos = new FileOutputStream(fileName);
        //object stream for serializing
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //save instance
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public void load(String fileName) throws IOException {
        //we use file stream for file
        FileInputStream fis = new FileInputStream(fileName);
        //object stream for serializing
        ObjectInputStream oin = new ObjectInputStream(fis);
        try {
            //read instance
            HiFiSystem system = (HiFiSystem) oin.readObject();
            CustomerName=system.CustomerName;
            devices =system.devices;
        }
        catch (ClassNotFoundException e){

        }
        oin.close();
    }
}
