//подключение пр-ва имен со сканером
import java.util.Scanner;
 
 //класс программы
public class lab1{
	
	//основной метод программы
	//Вариант 24. Задана вещественная  матрица размером m x n. 
	//Упорядочить ее строки по неубыванию их наибольших элементов.
	public static void main(String args[]){
		
		//создание сканера для чтения чисел
		Scanner in = new Scanner(System.in);
		
		//чтение размеров матрицы
		System.out.print("введите размеры матрицы:\n");
		int n=in.nextInt(),m=in.nextInt();
		
		//создание матрицы, ввод элементов
		double nums[][]=new double[n][m];
		//максимальные элементы строк матрицы nums
		//по ним произойдет сортировка строк первой матрицы
		//матрица содержит номер строки и максимальный элемент
		double maxElements[][]=new double[n][2];
		for(int i=0;i<n;i++)
		{
			
			//во время чтения попутно
			//вычисляем максимальный элемент
			double max=0;
			
			//чтение строки
			for(int j=0;j<m;j++)
			{
				
				//сравнение максимального элемента
				double val=in.nextDouble();
				if(j==0) max=val;
				else if(max<val)max=val;
				
				//запись в исходную матрицу
				nums[i][j]=val;
			}
			
			//переход на новую строку для чтения следующей строки
			System.out.print("\n");
			//запись в матрицу максимального элемента
			//только что прочитанной строки
			maxElements[i][0]=i;
			maxElements[i][1]=max;
		}
		
		
		//сортировка пузырьком матрицы maxElements
		for(int i=0; i < n; i++) 			// i - номер прохода
		{            
			for(int j = n-1; j > i; j-- ) 	// внутренний цикл прохода
			{     
				//сравнение и обмен
				if ( maxElements[j-1][1] > maxElements[j][1] ) {
					
					//обмен, меняем пару, а не один элемент
					double z=maxElements[j-1][1];
					maxElements[j-1][1]=maxElements[j][1];
					maxElements[j][1]=z;
			  
					z=maxElements[j-1][0];
					maxElements[j-1][0]=maxElements[j][0];
					maxElements[j][0]=z;
				}
			}
		}
		
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
}