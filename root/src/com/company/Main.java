package com.company;

//подключение пр-ва имен со сканером
import java.util.Scanner;

public class Main {

    //нахождение максимальных элементов строк матрицы
    public  static double[][] FindMaxesInRows(double[][] matrix,int n,int m) {
        double res[][] = new double[n][2];
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

    //сортировка матрицы по второму столбцу
    public  static  double[][] BubbleSort(double[][] matrix, int n){
        for(int i=0; i < n; i++) 			// i - номер прохода
        {
            for(int j = n-1; j > i; j-- ) 	// внутренний цикл прохода
            {
                //сравнение и обмен
                if ( matrix[j-1][1] > matrix[j][1] ) {

                    //обмен, меняем пару, а не один элемент
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
        //создание сканера для чтения чисел
        Scanner in = new Scanner(System.in);

        //чтение размеров матрицы
        System.out.print("введите размеры матрицы:\n");
        int n=in.nextInt(),m=in.nextInt();

        //создание матрицы, ввод элементов
        double nums[][]=new double[n][m];
        for(int i=0;i<n;i++)
        {
            //чтение строки
            for(int j=0;j<m;j++)
            {
                //запись в исходную матрицу
                nums[i][j]=in.nextDouble();
            }
        }

        //максимальные элементы строк матрицы nums
        //по ним произойдет сортировка строк первой матрицы
        //матрица содержит номер строки и максимальный элемент
        double maxElements[][]=FindMaxesInRows(nums,n,m);

        //сортировка пузырьком матрицы maxElements
        maxElements=BubbleSort(maxElements,n);

        //новая строка для вывода результата
        System.out.print("\n");

        //заполняем результат в ссответствии с отсортированным массивом maxElements
        double res[][]=new double[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                //по столбцам элементы остаяются
                //по строкам, в соответствии с maxElements
                res[i][j]=nums[(int)maxElements[i][0]][j];

                //выводим элемент
                System.out.print(res[i][j]+" ");
            }

            //новая строка
            System.out.print("\n");
        }
    }

    //основной метод программы
    //Вариант 24. Задана вещественная  матрица размером m x n.
    //Упорядочить ее строки по неубыванию их наибольших элементов.
    public static void main(String[] args) {
        CommonWork();
    }
}
