package audioOutputs;

import builders.HiFiSystem;
import builders.SimpleHeadphonesBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class HeadphonesTests {

    //price in ukrainian hryvnia
    @Parameter(value = 0)
    public double price;
    //device power in W
    @Parameter(value = 1)
    public double power;
    //device weight in grammes
    @Parameter(value = 2)
    public double weight;

    //cable length in metres
    @Parameter(value = 3)
    public double length;
    //playback frequency in Hz
    @Parameter(value = 4)
    public double minFrequency;
    @Parameter(value = 5)
    public double maxFrequency;

    @Parameter(value = 6)
    public boolean expectedResult;

    @Parameter(value = 7)
    public String valueType;

    @Parameters(name = "{7}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                //low values
                {0,0,0,0,0,0,false,"low values"},
                //high values
                {150000,2,700,4,30000,30000,false,"high values"},
                //normal values
                {1000,0.4,300,2,30,10000,true,"normal values"}
        });
    }

    //factory method for making headphones object
    private Headphones MakeHeadPhones(){

        //make builder with director
        SimpleHeadphonesBuilder builder=new SimpleHeadphonesBuilder();
        HiFiSystem hiFiSystem=new HiFiSystem();
        hiFiSystem.setAudioOutputBuilder(builder);

        //construct and returning object
        hiFiSystem.constructAudioOutput();
        return (Headphones)hiFiSystem.getAudioOutput();
    }

    //tests for setters
    //sets parameters and checks them

    @Test
    public void setPrice_RangeValue_ChecksThis(){
        Headphones headphones=MakeHeadPhones();
        headphones.setPrice(price);
        Assert.assertEquals(expectedResult,price==headphones.getPrice());
    }

    @Test
    public void setPower_RangeValue_ChecksThis(){
        Headphones headphones=MakeHeadPhones();
        headphones.setPower(power);
        Assert.assertEquals(expectedResult,power==headphones.getPower());
    }

    @Test
    public void setWeight_RangeValue_ChecksThis(){
        Headphones headphones=MakeHeadPhones();
        headphones.setWeight(weight);
        Assert.assertEquals(expectedResult,weight==headphones.getWeight());
    }

    @Test
    public void setLength_RangeValue_ChecksThis(){
        Headphones headphones=MakeHeadPhones();
        headphones.setLength(length);
        Assert.assertEquals(expectedResult,length==headphones.getLength());
    }

    @Test
    public void setMaxFrequency_RangeValue_ChecksThis(){
        Headphones headphones=MakeHeadPhones();
        headphones.setMaxFrequency(maxFrequency);
        Assert.assertEquals(expectedResult,maxFrequency==headphones.getMaxFrequency());
    }

    @Test
    public void setMinFrequency_RangeValue_ChecksThis(){
        Headphones headphones=MakeHeadPhones();
        headphones.setMinFrequency(minFrequency);
        Assert.assertEquals(expectedResult,minFrequency==headphones.getMinFrequency());
    }
}
