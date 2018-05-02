import audioOutputs.*;
import builders.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;
/*
task:
1 hi-fi system thread
2 radio thread
* thread1 and thread2 should not work in one time
* radio should start and stop randomly
 */
public class Main {
    public static void main(String[] args) {
        //inputOutputWork();
        multithreadedWork();
    }

    //radio and hi-fi system threads demonstration
    private static void multithreadedWork() {
        //scanner for input
        Scanner in = new Scanner(System.in);

        //get time for testing threads from user
        System.out.print("time for hi-fi system & radio work(in seconds):\n");
        int secondsToLive = 0;
        while (secondsToLive == 0) {
            try {

                //time should be int and positive
                secondsToLive = in.nextInt();
                if (secondsToLive <= 0) {
                    secondsToLive = 0;
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("time should be positive\n");
            } catch (Exception e) {
                System.out.print("time should be integer\n");

                //if time is NaN we need to recover out scanner
                in = new Scanner(System.in);
            }
        }

        //synchronized object
        RadioState radioState = new RadioState();

        //objects and their threads
        Radio radio = new Radio(radioState);
        HiFiSystem hiFiSystem = new HiFiSystem(radioState);
        Thread radioThread = radio.getRadioThread();
        Thread hifiThread = hiFiSystem.getHifiThread();
        radio.setHifiThread(hifiThread);
        hiFiSystem.setRadioThread(radioThread);

        //random variable for start/stop radio thread
        Random f = new Random(LocalDateTime.now().getSecond());

        //set threads daemon for correct program exit
        hifiThread.setDaemon(true);
        radioThread.setDaemon(true);

        //start threads
        hifiThread.start();
        radioThread.start();
        System.out.print("threads started\n");

        //each second we print radio and hi-fi states
        for ( int i=0; i<secondsToLive;i++ ) {
            System.out.print("\n"+(i+1)+"\tradio:" + (radioThread.getState()));
            System.out.print("\thi-fi:" + (hifiThread.getState()));

            //turn on/off radio randomly
            if(f.nextDouble()>0.7){
                radioState.isWorking=!radioState.isWorking;
            }

            //pause
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    //hi-fi system serialization
    private static void inputOutputWork() {
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
