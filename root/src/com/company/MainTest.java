package com.company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {

    //testing data:
    //matrix and its ranges
    public static double[][] matrix;
    public static int n,m;

    @BeforeAll
    //random data gen before each test
    public static void GenData(){
        Random f=new Random(LocalDateTime.now().getNano());
        n=f.nextInt(18)+2;
        m=f.nextInt(18)+2;

        matrix=new double[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                matrix[i][j]=f.nextDouble();
    }

    //default constructor
    public MainTest(){

    }

    @Test
    //checking out of bounds for method
    //Main.FindMaxesInRows()
    public void IndexOutOfBounds1(){
        try{
            //hand ranges, greater than n & m
            Main.FindMaxesInRows(matrix,n+1,m+2);
        }
        catch (ArrayIndexOutOfBoundsException ex){
            //if correct exception thrown
            //return, else test did not pass
            return;
        }
        fail("");
    }

    @Test
    //check for negative index
    public void NegativeRange(){
        try{
            //hand negative index
            //in this method will be creation
            //matrix with this ranges
            Main.FindMaxesInRows(matrix,-1,-1);
        }
        //negative index exception
        catch (NegativeArraySizeException ex){
            return;
        }
        fail("");
    }

    @Test
    //out of bounds
    //in sort method(rows)
    public void IndexOutOfBounds2(){
        try{
            Main.BubbleSort(matrix,n+1);
        }
        catch (ArrayIndexOutOfBoundsException ex){
            return;
        }
        fail("");
    }

    @Test
    //out of bounds
    //in sort method(columns)
    public void IndexOutOfBounds3(){
        //for throwing out of bounds exception
        //we create matrix with one column
        //method sort second column
        double value[][]=new double[n][1];
        for(int i=0;i<n;i++)
            value[i][0]=matrix[i][0];
        try{
            Main.BubbleSort(value,n);
        }
        catch (ArrayIndexOutOfBoundsException ex){
            return;
        }
        fail("");
    }

    @Test
    //check for correct max elements
    public void MaxesInRows(){
        double maxes[][]=Main.FindMaxesInRows(matrix,n,m);
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                //compare each element with max
                if(matrix[i][j]>maxes[i][1])
                    fail("");
    }

    @Test
    //check elements order after sort
    public void SortOrder(){
        double maxes[][]=Main.FindMaxesInRows(matrix,n,m);
        Main.BubbleSort(maxes,n);
        for(int i=0;i<n-1;i++)
            //check non descending order for second column elements
            if(maxes[i][1]>maxes[i+1][1])
                fail("");
    }
}
