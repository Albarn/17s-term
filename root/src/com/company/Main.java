package com.company;
import java.util.Scanner;

public class Main {

    //find max elements in matrix rows
    //returns array row index - max element
    public  static double[][]
    FindMaxesInRows(double[][] matrix,int n,int m)
    throws HighRangeException, NegativeRangeException{
        double res[][] = new double[n][2];
        if(n<0)throw  new NegativeRangeException("row");
        else if(m<0) throw  new NegativeRangeException("column");
        try {
            double a=matrix[n-1][0];
        }
        catch(Exception ex) {
            throw new HighRangeException("column");
        }
        try {
            double a=matrix[0][m-1];
        }
        catch(Exception ex) {
            throw new HighRangeException("row");
        }
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] > matrix[i][max])
                    max = j;
            }
            res[i][0] = i;
            res[i][1] = matrix[i][max];
        }
        return res;
    }

    //sorting matrix by second column
    public  static  double[][] BubbleSort(double[][] matrix, int n)
    throws HighRangeException, MissingSecondColumnException{
        try {
            double a=matrix[n-1][0];
        }
        catch (Exception ex){
            throw new HighRangeException("row");
        }
        try {
            double a=matrix[0][1];
        }
        catch (Exception ex){
            throw new MissingSecondColumnException();
        }
        for(int i=0; i < n; i++) 			// i - out cycle
        {
            for(int j = n-1; j > i; j-- ) 	// inner cycle
            {
                //compare & exchange
                if ( matrix[j-1][1] > matrix[j][1] ) {

                    //exchange, change pair, not a single element
                    double z=matrix[j-1][1];
                    matrix[j-1][1]=matrix[j][1];
                    matrix[j][1]=z;

                    z=matrix[j-1][0];
                    matrix[j-1][0]=matrix[j][0];
                    matrix[j][0]=z;
                }
            }
        }
        return matrix;
    }

    public static void CommonWork(){
        //creating scanner for reading numbers
        Scanner in = new Scanner(System.in);

        //matrix range reading
        System.out.print("write matrix ranges:\n");
        int n=0,m=0;

        while(true) {
            try {
                //read integer
                //if user write non digit
                //or negative number
                //we print exception message and try to read it again
                try {
                    n = in.nextInt();
                }catch (Exception ex){
                    throw new NonIntegerRangeException("row");
                }
                if (n >= 0)
                    break;
                else
                    throw new NegativeRangeException("row");
            } catch (Exception ex) {
                System.out.print(ex.getMessage());
                in = new Scanner(System.in);
            }
        }
        while(true){
            try{
                //reading m, same as n
                try {
                    m = in.nextInt();
                }catch (Exception ex){
                    throw new NonIntegerRangeException("column");
                }
                if (m >= 0)
                    break;
                else
                    throw new NegativeRangeException("column");
            }catch (Exception ex){
                System.out.print(ex.getMessage());
                in = new Scanner(System.in);
            }
        }
        //creating matrix, reading elements
        double nums[][]=new double[n][m];
        for(int i=0;i<n;i++)
        {
            //reading row
            for(int j=0;j<m;j++)
            {
                //writing to matrix
                while(true){
                    //read double
                    //if user write something wrong(like non digit)
                    //we print exception message and try to read it again
                    try{
                        try{
                        nums[i][j]=in.nextDouble();
                        }catch (Exception ex){
                            throw new NotRealException();
                        }
                        break;
                    }catch (Exception ex){
                        System.out.print(ex.getMessage());
                        in = new Scanner(System.in);
                    }
                }
            }
        }

        //max elements in nums rows
        //it will be sorting by max elements
        //matrix contents row number and max element
        double maxElements[][]=null;
        try{
            maxElements=FindMaxesInRows(nums,n,m);
        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
            return;
        }

        try {

            //bubble sort maxElements
            maxElements=BubbleSort(maxElements,n);
        }
        catch (Exception ex)
        {

            System.out.print(ex.getMessage());
            return;
        }

        //new line for result
        System.out.print("\n");

        //fill result by maxElements
        double res[][]=new double[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                res[i][j]=nums[(int)maxElements[i][0]][j];

                //print element
                System.out.print(res[i][j]+" ");
            }

            //new line for new row
            System.out.print("\n");
        }
    }

    //main program method
    //variant 24. its double matrix m x n
    //sort its rows by max elements
    public static void main(String[] args) {
        CommonWork();
    }
}
