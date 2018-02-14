import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {

    String[] names = new String[]{
            "x-ray fish",
            "beaked whale",
            "seahorse",
            "marlin",
            "dolphin",
            "horse",
            "kingfisher",
            "panthera hybrid",
            "pigeon",
            "galliwasp",
            "krill",
            "donkey",
            "scorpion",
            "guppy",
            "golden retriever",
            "smelt",
            "ant",
            "quokka",
            "guanaco",
            "basilisk",
            "barracuda",
    };

    public MainTest() {

    }

    @Test
    public  void  HeadphonesLengthOutOfBounds(){
        Headphones hp=new Headphones("");
        double value=-1;
        hp.SetLength(value);
        assertNotEquals(value,hp.GetLength());
        value=10;
        hp.SetLength(value);
        assertNotEquals(value,hp.GetLength());
    }

    @Test
    public void HeadphonesFrequencyOutOfBounds(){
        Headphones hp=new Headphones("");
        double value=10000000;
        hp.SetMaxFrequency(value);
        assertNotEquals(value,hp.GetMaxFrequency());
        value=-10;
        hp.SetMaxFrequency(value);
        assertNotEquals(value,hp.GetMaxFrequency());

        value=10000000;
        hp.SetMinFrequency(value);
        assertNotEquals(value,hp.GetMinFrequency());
        value=-10;
        hp.SetMinFrequency(value);
        assertNotEquals(value,hp.GetMinFrequency());

        hp.SetMaxFrequency(500);
        hp.SetMinFrequency(300);
        value=600;
        hp.SetMinFrequency(value);
        assertNotEquals(value,hp.GetMinFrequency());

        value=200;
        hp.SetMaxFrequency(value);
        assertNotEquals(value,hp.GetMaxFrequency());
    }

    @Test
    public void MaxPrice(){
        Random f = new Random(LocalDateTime.now().getNano());

        int n=f.nextInt(20)+4;
        AudioOutput[] data=GenArray(n);
        n*=3;
        AudioOutput max=AudioOutput.MaxPrice(data);
        for (int i=0;i<n;i++)
            if(data[i].GetPrice()>max.GetPrice())
                fail("");
    }

    @Test
    public void MinPrice(){
        Random f = new Random(LocalDateTime.now().getNano());

        int n=f.nextInt(20)+4;
        AudioOutput[] data=GenArray(n);
        n*=3;
        AudioOutput min=AudioOutput.MinPrice(data);
        for (int i=0;i<n;i++)
            if(data[i].GetPrice()<min.GetPrice())
                fail("");
    }

    @Test
    public void SortByNameOrder(){
        Random f = new Random(LocalDateTime.now().getNano());

        int n=f.nextInt(20)+4;
        AudioOutput[] data=GenArray(n);
        n*=3;
        AudioOutput.SortByName(data);
        for (int i=0;i<n-1;i++)
            if(data[i].name.compareTo(data[i+1].name)>0)
                fail("");
    }

    @Test
    public void SortByPriceOrder(){
        Random f = new Random(LocalDateTime.now().getNano());

        int n=f.nextInt(20)+4;
        AudioOutput[] data=GenArray(n);
        n*=3;
        AudioOutput.SortByPrice(data);
        for (int i=0;i<n-1;i++)
            if(data[i].GetPrice()>data[i+1].GetPrice())
                fail("");
    }

    AudioOutput[] GenArray(int n) {
        AudioOutput[] res = new AudioOutput[n * 3];
        Random f = new Random(LocalDateTime.now().getNano());
        int i = 0;
        for (; i < n; i++) {
            res[i] = new Headphones(names[f.nextInt(20)]);
            res[i].SetPrice(f.nextInt(10000) + 500);
        }
        for (; i < 2 * n; i++) {
            res[i] = new Speakers(names[f.nextInt(20)]);
            res[i].SetPrice(f.nextInt(10000) + 500);
        }
        for (; i < 3 * n; i++) {
            res[i] = new Radio(names[f.nextInt(20)]);
            res[i].SetPrice(f.nextInt(10000) + 500);
        }
        return res;
    }
}
