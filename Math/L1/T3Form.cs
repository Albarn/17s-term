using ClassLib;
using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;

namespace L1
{
    public partial class T3Form : Form
    {
        int n = 1;
        
        public T3Form()
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
            Lab1.BuildVector(cDataGridView, n, "B");
            Lab1.BuildVector(xDataGridView, n, "X");
        }

        List<string> log=new List<string>();
        private void button1_Click(object sender, EventArgs e)
        {

            log = new List<string>();
            log.Add("решение системы первым способом");
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

            //обратная матрица
            A = Lab1.InvertMatrix(A, n,log);
            if (A == null)
            {
                MessageBox.Show("осевой элемент равен нулю\r\n" +
                    "обратную матрицу вычислить нельзя\r\n" +
                    "заполните главную диагональ");
                return;
            }

            //вектор В
            double[,] B = new double[n, 1];
            for (int i = 0; i < n; i++)
            {
                if (cDataGridView[0, i].Value != null)
                {
                    double.TryParse(cDataGridView[0, i].Value.ToString(), out B[i, 0]);
                }
                else
                {
                    B[i, 0] = 0;
                }
            }

            //получаем результат перемножением обратной матрицы и вектора В
            double[,] X = Lab1.MultiplyMatrixes(A, B, n, n, 1,log);
            for (int i = 0; i < n; i++)
                xDataGridView[0, i].Value = X[i, 0];
        }

        //запись лога в файл
        private void button2_Click(object sender, EventArgs e)
        {
            Lab1.SaveLog(log);
        }
    }
}
