import java.util.Scanner;
 
public class lab1{
 public static void main(String args[]){
	 Scanner in = new Scanner(System.in);
	 //чтение размеров матрицы
  System.out.print("введите размеры матрицы:\n");
  int n=in.nextInt(),m=in.nextInt();
  
  //создание матрицы, ввод элементов
  double nums[][]=new double[n][m];
  //максимальные элементы строк матрицы nums
  //по ним произойдет сортировка строк первой матрицы
  double maxElements[][]=new double[n][2];
  for(int i=0;i<n;i++)
  {
	  double max=0;
	  for(int j=0;j<m;j++)
	  {
		  double val=in.nextDouble();
		  if(j==0) max=val;
		  else if(max<val)max=val;
		  nums[i][j]=val;
	  }
	  System.out.print("\n");
	  //запись в матрицу максимальных элементов
	  maxElements[i][0]=i;
	  maxElements[i][1]=max;
  }
  
  //сортировка пузырьком матрицы maxElements
  for(int i=0; i < n; i++) {            // i - номер прохода
    for(int j = n-1; j > i; j-- ) {     // внутренний цикл прохода
      if ( maxElements[j-1][1] > maxElements[j][1] ) {
		  //обмен
		  double z=maxElements[j-1][1];
		  maxElements[j-1][1]=maxElements[j][1];
		  maxElements[j][1]=z;
		  
		  z=maxElements[j-1][0];
		  maxElements[j-1][0]=maxElements[j][0];
		  maxElements[j][0]=z;
	  }
	}
  }
  System.out.print("\n");
  //заполняем результат в ссответствии с отсортированным массивом maxElements
  double res[][]=new double[n][m];
  for(int i=0;i<n;i++){
	  for(int j=0;j<m;j++)
	  {
		  res[i][j]=nums[(int)maxElements[i][0]][j];
		  System.out.print(res[i][j]+" ");
	  }
	  System.out.print("\n");
  }
 }
}