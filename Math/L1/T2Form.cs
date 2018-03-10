using ClassLib;
using System;
using System.Windows.Forms;

namespace L1
{
    public partial class T2Form : Form
    {
        int n = 1;
        int m = 1;
        
        public T2Form()
        {
            InitializeComponent();
            numericUpDown1_ValueChanged(this, new EventArgs());
        }

        /// <summary>
        /// изменение числа в numericUpDown1
        /// </summary>
        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {

            //создание новых таблиц при изменении размера в поле
            n = (int)numericUpDown1.Value;
            m = (int)numericUpDown2.Value;

            Lab1.BuildTable(aDataGridView, n, m);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //чтение матрицы А из таблицы
            double[,] A = new double[n, m];

            //перебираем все ячейки
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                {
                    //если ячейка не заполнена, присваиваем ноль
                    if (aDataGridView[j, i].Value == null)
                        A[i, j] = 0;
                    else
                    {
                        double.TryParse(aDataGridView[j, i].Value.ToString(), out A[i, j]);
                    }
                }

            rankLabel.Text = Lab1.Rank(A, n,m).ToString();
        }
    }
}
