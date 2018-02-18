package audioOutputs;

import builders.HiFiSystem;
import builders.SimpleSpeakersBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class SpeakersTests {

    //price in ukrainian hryvnia
    @Parameter(value = 0)
    public double price;
    //device power in W
    @Parameter(value = 1)
    public double power;
    //device weight in grammes
    @Parameter(value = 2)
    public double weight;

    //playback frequency in Hz
    @Parameter(value = 3)
    public double minFrequency;
    @Parameter(value = 4)
    public double maxFrequency;

    @Parameter(value = 5)
    public boolean expectedResult;

    @Parameter(value = 6)
    public String valueType;

    @Parameters(name = "{6}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                //low values
                {0,0,0,0,0,false,"low values"},
                //high values
                {6000000,2000,11000,70000,70000,false,"high values"},
                //normal values
                {10000,300,2000,30,10000,true,"normal values"}
        });
    }

    //factory method for making headphones object
    private Speakers MakeHeadPhones(){

        //make builder with director
        SimpleSpeakersBuilder builder=new SimpleSpeakersBuilder();
        HiFiSystem hiFiSystem=new HiFiSystem();
        hiFiSystem.setAudioOutputBuilder(builder);

        //construct and returning object
        hiFiSystem.constructAudioOutput();
        return (Speakers) hiFiSystem.getAudioOutput();
    }

    //tests for setters
    //sets parameters and checks them

    @Test
    public void setPrice_RangeValue_ChecksThis(){
        Speakers speakers=MakeHeadPhones();
        speakers.setPrice(price);
        Assert.assertEquals(expectedResult,price==speakers.getPrice());
    }

    @Test
    public void setPower_RangeValue_ChecksThis(){
        Speakers speakers=MakeHeadPhones();
        speakers.setPower(power);
        Assert.assertEquals(expectedResult,power==speakers.getPower());
    }

    @Test
    public void setWeight_RangeValue_ChecksThis(){
        Speakers speakers=MakeHeadPhones();
        speakers.setWeight(weight);
        Assert.assertEquals(expectedResult,weight==speakers.getWeight());
    }

    @Test
    public void setMaxFrequency_RangeValue_ChecksThis(){
        Speakers speakers=MakeHeadPhones();
        speakers.setMaxFrequency(maxFrequency);
        Assert.assertEquals(expectedResult,maxFrequency==speakers.getMaxFrequency());
    }

    @Test
    public void setMinFrequency_RangeValue_ChecksThis(){
        Speakers speakers=MakeHeadPhones();
        speakers.setMinFrequency(minFrequency);
        Assert.assertEquals(expectedResult,minFrequency==speakers.getMinFrequency());
    }
}
