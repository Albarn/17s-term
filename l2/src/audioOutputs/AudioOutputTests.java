package audioOutputs;

import audioOutputs.AudioOutput;
import audioOutputs.Headphones;
import org.junit.jupiter.api.Test;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.fail;

public class AudioOutputTests {
    private String[] names=new String[]{
            "tarsier",
            "bonobo",
            "boa",
            "moth",
            "orca",
            "ant",
            "lizard",
            "lemur",
            "mastodon",
            "parakeet"
    };

    //generate array of 10 headphones
    //for sort, search audioOutputs.AudioOutput methods
    private AudioOutput[] genArray(){
        //length of array
        int size=10;
        AudioOutput[] arrayforTest=new AudioOutput[size];

        //random variable for random price
        Random f=new Random();
        for(int i=0;i<size;i++)
        {
            arrayforTest[i]=new Headphones();
            //get name from names array
            arrayforTest[i].name=names[i];
            arrayforTest[i].setPrice(f.nextInt(1000)+100);
        }

        return arrayforTest;
    }

    @Test
    public void sortByPrice_CommonArray_AscendingOrder(){

        //get array than sort it by price,
        //than look to order
        AudioOutput[] audioOutputs=genArray();
        audioOutputs=AudioOutput.sortByPrice(audioOutputs);
        for(int i=0;i<audioOutputs.length-1;i++)
            if(audioOutputs[i].getPrice()>audioOutputs[i+1].getPrice())
                fail("");
    }

    @Test
    public void sortByName_CommonArray_AscendingOrder(){
        //get array than sort it by name,
        //than look to order
        AudioOutput[] audioOutputs=genArray();
        audioOutputs=AudioOutput.sortByName(audioOutputs);
        for(int i=0;i<audioOutputs.length-1;i++)
            if(audioOutputs[i].name.compareTo(audioOutputs[i+1].name)>0)
                fail("");
    }

    @Test
    public void maxPrice_CommonArray_NoDevicesWithHigherPrice(){
        //get array and search max element
        //then check it
        AudioOutput[] audioOutputs=genArray();
        AudioOutput maxPriceDevice=AudioOutput.maxPrice(audioOutputs);
        for(int i=0;i<audioOutputs.length;i++)
            if(audioOutputs[i].getPrice()>maxPriceDevice.getPrice())
                fail("");
    }

    @Test
    public void minPrice_CommonArray_NoDevicesWithLowerPrice(){
        //get array and search min element
        //then check it
        AudioOutput[] audioOutputs=genArray();
        AudioOutput minPriceDevice=AudioOutput.minPrice(audioOutputs);
        for(int i=0;i<audioOutputs.length;i++)
            if(audioOutputs[i].getPrice()<minPriceDevice.getPrice())
                fail("");
    }

    @Test
    public void maxPrice_EmptyArray_Null(){
        //create empty array
        //method have to return null
        AudioOutput[] audioOutputs=new AudioOutput[0];
        AudioOutput maxPriceDevice=AudioOutput.maxPrice(audioOutputs);
        if(maxPriceDevice!=null)
            fail("");
    }
    @Test
    public void minPrice_EmptyArray_Null(){
        //create empty array
        //method have to return null
        AudioOutput[] audioOutputs=new AudioOutput[0];
        AudioOutput minPriceDevice=AudioOutput.minPrice(audioOutputs);
        if(minPriceDevice!=null)
            fail("");
    }

}
