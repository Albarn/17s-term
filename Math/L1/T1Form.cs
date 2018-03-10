using ClassLib;
using System;
using System.Windows.Forms;

namespace L1
{
    public partial class T1Form : Form
    {
        int n = 1;
        
        public T1Form()
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

            Lab1.BuildTable(aDataGridView, n, n);
            Lab1.BuildTable(cDataGridView, n, n);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //чтение матрицы А из таблицы
            double[,] A = new double[n, n];

            //перебираем все ячейки
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    //если ячейка не заполнена, присваиваем ноль
                    if (aDataGridView[j, i].Value == null)
                        A[i, j] = 0;
                    else
                    {
                        double.TryParse(aDataGridView[j, i].Value.ToString(), out A[i, j]);
                    }
                }

            //обращаем матрицу и выводим ее на форму
            A = Lab1.InvertMatrix(A, n);
            if(A==null)
            {
                MessageBox.Show("осевой элемент равен нулю\r\n" +
                    "обратную матрицу вычислить нельзя\r\n" +
                    "заполните главную диагональ");
                return;
            }
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    cDataGridView[j, i].Value = A[i, j].ToString();
                }
        }
    }
}
