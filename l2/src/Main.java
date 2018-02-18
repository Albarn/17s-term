import audioOutputs.AudioOutput;
import builders.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("welcome to audio-output systems\n" +
                "here you can create your hi-fi system\n" +
                "write \"fancy\" | \"simple\" than(without space) " +
                "\"headphones\" | \"radio\" | \"speakers\"\n" +
                "to add device to your collection\n" +
                "to stop write something else\n");
        HiFiSystem hiFiSystem = new HiFiSystem();
        Scanner in = new Scanner(System.in);
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
    }
}
