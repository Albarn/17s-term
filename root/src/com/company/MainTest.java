package com.company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {

    //testing data:
    //matrix and its ranges
    public static double[][] matrix;
    public static int n,m;

    @BeforeEach
    //random data gen before each test
    public void GenData(){
        Random f=new Random();
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
    public void FindMaxesInRows_IndexesOutOfBounds_ThrowsException(){
        try{
            //hand ranges, greater than n & m
            Main.FindMaxesInRows(matrix,n+1,m+2);
        }
        catch (HighRangeException ex){
            //if correct exception thrown
            //return, else test did not pass
            return;
        }
        catch (Exception ex){
            fail("");
        }
        fail("");
    }

    @Test
    //check for negative index
    public void FindMaxesInRows_NegativeRange_ThrowsException(){
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
        catch (Exception ex){
            fail("");
        }
        fail("");
    }


    @Test
    //check for correct max elements
    public void FindMaxesInRows_NormalMatrix_ResultElementsShouldBeMax(){
        try {
            double maxes[][] = Main.FindMaxesInRows(matrix, n, m);
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    //compare each element with max
                    if (matrix[i][j] > maxes[i][1])
                        fail("");
        }
        catch (Exception ex){
            fail("");
        }
    }

    @Test
    //out of bounds
    //in sort method(rows)
    public void BubbleSort_IndexOfRowsOutOfBounds_ThrowsException(){
        try{
            Main.BubbleSort(matrix,n+1);
        }
        catch (HighRangeException ex){
            return;
        }
        catch(MissingSecondColumnException ex){

        }
        fail("");
    }

    @Test
    //out of bounds
    //in sort method(columns)
    public void BubbleSort_IndexOfColumnsOutOfBounds_ThrowsException(){
        //for throwing out of bounds exception
        //we create matrix with one column
        //method sort second column
        double value[][]=new double[n][1];
        for(int i=0;i<n;i++)
            value[i][0]=matrix[i][0];
        try{
            Main.BubbleSort(value,n);
        }
        catch (HighRangeException ex){

        }
        catch (MissingSecondColumnException ex){
            return;
        }
        fail("");
    }


    @Test
    //check elements order after sort
    public void BubbleSort_NormalMatrix_SortOrderShouldBeAscending(){
        try {
            double maxes[][] = Main.FindMaxesInRows(matrix, n, m);
            Main.BubbleSort(maxes, n);
            for (int i = 0; i < n - 1; i++)
                //check non descending order for second column elements
                if (maxes[i][1] > maxes[i + 1][1])
                    fail("");
        }
        catch (Exception ex){
            fail("");
        }
    }
}
