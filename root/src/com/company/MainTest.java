package com.company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {

    //данные для тестирования:
    //матрица и ее размеры
    public static double[][] matrix;
    public static int n,m;

    @BeforeAll
    //генерация случайных данных перед каждым тестом
    public static void GenData(){
        Random f=new Random(LocalDateTime.now().getNano());
        n=f.nextInt(20);
        m=f.nextInt(20);

        matrix=new double[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                matrix[i][j]=f.nextDouble();
    }

    //конструктор по умолчанию
    public MainTest(){

    }

    @Test
    //проверка выхода за диапазон для метода
    //Main.FindMaxesInRows()
    public void IndexOutOfBounds1(){
        try{
            //задаем размеры, большие исходных
            Main.FindMaxesInRows(matrix,n+1,m+2);
        }
        catch (ArrayIndexOutOfBoundsException ex){
            //если сгенерировано правильное исключение
            //выходим, иначе тест не пройден
            return;
        }
        fail("");
    }

    @Test
    //проверка на отрицательный индекс
    public void NegativeRange(){
        try{
            //задаем отрицательные размеры
            //в данном методе будет попытка создать
            //массив с такими размерами
            Main.FindMaxesInRows(matrix,-1,-1);
        }
        //исключение отрицательного размера массива
        catch (NegativeArraySizeException ex){
            return;
        }
        fail("");
    }

    @Test
    //выход за диапазон
    //в методе сортировки(по строкам)
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
    //выход за диапазон
    //в методе сортировки(по столбцам)
    public void IndexOutOfBounds3(){
        //для выхода за границы создадим массив
        //из одного столбца(массив сортируется по второму столбцу)
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
    //проверка правильности нахождения
    //максимальных значений в строках матрицы
    public void MaxesInRows(){
        double maxes[][]=Main.FindMaxesInRows(matrix,n,m);
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                //сравниваем каждый элемент строки с максимальным
                if(matrix[i][j]>maxes[i][1])
                    fail("");
    }

    @Test
    //проверка порядка элементов после сортировки
    public void SortOrder(){
        double maxes[][]=Main.FindMaxesInRows(matrix,n,m);
        Main.BubbleSort(maxes,n);
        for(int i=0;i<n-1;i++)
            //проверка неубывания элементов второго столбца
            if(maxes[i][1]>maxes[i+1][1])
                fail("");
    }
}
