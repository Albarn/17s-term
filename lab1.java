//����������� ��-�� ���� �� ��������
import java.util.Scanner;
 
 //����� ���������
public class lab1{
	
	//�������� ����� ���������
	//������� 24. ������ ������������  ������� �������� m x n. 
	//����������� �� ������ �� ���������� �� ���������� ���������.
	public static void main(String args[]){
		
		//�������� ������� ��� ������ �����
		Scanner in = new Scanner(System.in);
		
		//������ �������� �������
		System.out.print("������� ������� �������:\n");
		int n=in.nextInt(),m=in.nextInt();
		
		//�������� �������, ���� ���������
		double nums[][]=new double[n][m];
		//������������ �������� ����� ������� nums
		//�� ��� ���������� ���������� ����� ������ �������
		//������� �������� ����� ������ � ������������ �������
		double maxElements[][]=new double[n][2];
		for(int i=0;i<n;i++)
		{
			
			//�� ����� ������ �������
			//��������� ������������ �������
			double max=0;
			
			//������ ������
			for(int j=0;j<m;j++)
			{
				
				//��������� ������������� ��������
				double val=in.nextDouble();
				if(j==0) max=val;
				else if(max<val)max=val;
				
				//������ � �������� �������
				nums[i][j]=val;
			}
			
			//������� �� ����� ������ ��� ������ ��������� ������
			System.out.print("\n");
			//������ � ������� ������������� ��������
			//������ ��� ����������� ������
			maxElements[i][0]=i;
			maxElements[i][1]=max;
		}
		
		
		//���������� ��������� ������� maxElements
		for(int i=0; i < n; i++) 			// i - ����� �������
		{            
			for(int j = n-1; j > i; j-- ) 	// ���������� ���� �������
			{     
				//��������� � �����
				if ( maxElements[j-1][1] > maxElements[j][1] ) {
					
					//�����, ������ ����, � �� ���� �������
					double z=maxElements[j-1][1];
					maxElements[j-1][1]=maxElements[j][1];
					maxElements[j][1]=z;
			  
					z=maxElements[j-1][0];
					maxElements[j-1][0]=maxElements[j][0];
					maxElements[j][0]=z;
				}
			}
		}
		
		//����� ������ ��� ������ ����������
		System.out.print("\n");
		
		//��������� ��������� � ������������ � ��������������� �������� maxElements
		double res[][]=new double[n][m];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				//�� �������� �������� ���������
				//�� �������, � ������������ � maxElements
				res[i][j]=nums[(int)maxElements[i][0]][j];
				
				//������� �������
				System.out.print(res[i][j]+" ");
			}
			
			//����� ������
			System.out.print("\n");
		}
	}
}