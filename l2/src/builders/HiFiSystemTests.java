package builders;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class HiFiSystemTests {

    @Test
    public void getAudioOutput_NullBuilder_NullException(){
        HiFiSystem hiFiSystem=new HiFiSystem();
        try {
            hiFiSystem.getAudioOutput();
        }
        catch (NullPointerException ex){
            return;
        }

        fail("");
    }

    @Test
    public void constructAudioOutput_NullBuilder_NullException(){
        HiFiSystem hiFiSystem=new HiFiSystem();
        try {
            hiFiSystem.constructAudioOutput();
        }
        catch (NullPointerException ex){
            return;
        }

        fail("");
    }

    @Test
    public void constructAudioOutput_NeverCalled_ZeroConstructedObjects(){
        HiFiSystem hiFiSystem=new HiFiSystem();
        Assert.assertEquals(0,hiFiSystem.constructedObjects.size());

    }

    @Test
    public void constructAudioOutput_CalledSeveralTimes_CheckCountOfConstructedObjects(){
        HiFiSystem hiFiSystem=new HiFiSystem();
        hiFiSystem.setAudioOutputBuilder(new SimpleRadioBuilder());
        int count=7;
        for(int i=0;i<count;i++)
            hiFiSystem.constructAudioOutput();
        Assert.assertEquals(count,hiFiSystem.constructedObjects.size());

    }
}
