package audioOutputs;

import builders.HiFiSystem;
import builders.SimpleRadioBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class RadioTests {

    //price in ukrainian hryvnia
    @Parameter(value = 0)
    public double price;
    //device power in W
    @Parameter(value = 1)
    public double power;
    //device weight in grammes
    @Parameter(value = 2)
    public double weight;

    @Parameter(value = 3)
    public boolean expectedResult;

    @Parameter(value = 4)
    public String valueType;

    @Parameters(name = "{4}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                //low values
                {0,0,0,false,"low values"},
                //high values
                {2000000,1000,5000,false,"high values"},
                //normal values
                {10000,300,2000,true,"normal values"}
        });
    }

    //factory method for making headphones object
    private Radio MakeRadio(){

        //make builder with director
        SimpleRadioBuilder builder=new SimpleRadioBuilder();
        HiFiSystem hiFiSystem=new HiFiSystem();
        hiFiSystem.setAudioOutputBuilder(builder);

        //construct and returning object
        hiFiSystem.constructAudioOutput();
        return (Radio) hiFiSystem.getAudioOutput();
    }

    //tests for setters
    //sets parameters and checks them

    @Test
    public void setPrice_RangeValue_ChecksThis(){
        Radio radio= MakeRadio();
        radio.setPrice(price);
        Assert.assertEquals(expectedResult,price==radio.getPrice());
    }

    @Test
    public void setPower_RangeValue_ChecksThis(){
        Radio radio= MakeRadio();
        radio.setPower(power);
        Assert.assertEquals(expectedResult,power==radio.getPower());
    }

    @Test
    public void setWeight_RangeValue_ChecksThis(){
        Radio radio= MakeRadio();
        radio.setWeight(weight);
        Assert.assertEquals(expectedResult,weight==radio.getWeight());
    }
}
