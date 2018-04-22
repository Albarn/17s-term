import audioOutputs.AudioOutput;
import builders.*;

import java.io.*;
import java.util.Scanner;
/*
1 hi-fi system thread
2 radio thread
* thread1 and thread2 should not work in one time
* radio should start and stop random

 */
public class Main {
    public static void main(String[] args) {
        System.out.print("welcome to audio-output systems\n");
        HiFiSystem hiFiSystem = new HiFiSystem();
        Scanner in = new Scanner(System.in);
        System.out.print("load hi-fi system from file(y/n)?\n");

        //deserialize hi-fi system instance from file
        if(in.nextLine().equals("y")){
            System.out.print("write path here:\n");
            String path=in.nextLine();
            try{
                //we use file stream for file
                FileInputStream fis = new FileInputStream(path);
                //object stream for serializing
                ObjectInputStream oin = new ObjectInputStream(fis);
                //read instance
                hiFiSystem = (HiFiSystem) oin.readObject();
                oin.close();
            }catch(Exception ex){
                //if error occurs we work with new hi-fi system
                System.out.print("load failed: "+ ex.getMessage()+"\ncreating new system\n");
            }
        }
        System.out.print("here you can edit your hi-fi system\n" +
                "write \"fancy\" | \"simple\" than(without space) " +
                "\"headphones\" | \"radio\" | \"speakers\"\n" +
                "to add device to your collection\n" +
                "to stop write something else\n");
        boolean exit = false;
        while (!exit) {
            String deviceName = in.nextLine();
            AudioOutputBuilder audioOutputBuilder = null;
            switch (deviceName) {
                case "fancyheadphones":
                    audioOutputBuilder = new FancyHeadphonesBuilder();
                    break;
                case "fancyradio":
                    audioOutputBuilder = new FancyRadioBuilder();
                    break;
                case "fancyspeakers":
                    audioOutputBuilder = new FancySpeakersBuilder();
                    break;
                case "simpleheadphones":
                    audioOutputBuilder = new SimpleHeadphonesBuilder();
                    break;
                case "simpleradio":
                    audioOutputBuilder = new SimpleRadioBuilder();
                    break;
                case "simplespeakers":
                    audioOutputBuilder = new SimpleSpeakersBuilder();
                    break;
                default:
                    exit = true;
                    break;
            }
            if (audioOutputBuilder != null) {
                hiFiSystem.setAudioOutputBuilder(audioOutputBuilder);
                hiFiSystem.constructAudioOutput();
            }
        }

        AudioOutput[] devices = new AudioOutput[hiFiSystem.constructedObjects.size()];
        for (int i = 0; i < devices.length; i++)
            devices[i] = hiFiSystem.constructedObjects.get(i);
        devices = AudioOutput.sortByPrice(devices);

        System.out.print("your hi-fi system:\n");
        for (int i = 0; i < devices.length; i++)
            System.out.print(devices[i].toString() + "\n");

        System.out.print("save(y/n)?\n");

        //save system
        if(in.nextLine().equals("y")){
            System.out.print("write path here:\n");
            String path=in.nextLine();
            try{
                //we use file stream for file
                FileOutputStream fos = new FileOutputStream(path);
                //object stream for serializing
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                //save instance
                oos.writeObject(hiFiSystem);
                oos.flush();
                oos.close();
            }catch(Exception ex){
                //if error occurs - show exception message
                System.out.print("save failed: "+ ex.getMessage());
            }
        }
    }
}
