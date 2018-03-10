using ClassLib;
using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;

namespace L1
{
    public partial class T4Form : Form
    {
        int n = 1;
        
        public T4Form()
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

        List<string> log = new List<string>();
        private void button1_Click(object sender, EventArgs e)
        {
            log = new List<string>();
            log.Add("решение системы вторым способом");
            //чтение матрицы А из таблицы
            double[,] A = new double[n, n + 1];

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
            for (int i = 0; i < n; i++)
            {
                //если ячейка не заполнена, присваиваем ноль
                if (cDataGridView[0, i].Value == null)
                    A[i, n] = 0;
                else
                {
                    double.TryParse(cDataGridView[0, i].Value.ToString(), out A[i, n]);
                }
            }

            //запись исходной системы в лог
            for (int i = 0; i < n; i++)
            {
                log.Add("");
                for (int j = 0; j < n + 1; j++)
                {
                    log[log.Count - 1] += A[i, j].ToString("F2") + "\t";
                }
            }

            //осевые шаги на главной диагонали системы
            for (int i = 0; i < n; i++)
            {
                if(A[i,i]==0)
                {
                    MessageBox.Show("осевой элемент равен нулю\r\n" +
                    "обратную матрицу вычислить нельзя\r\n" +
                    "заполните главную диагональ");
                    return;
                }
                Lab1.AxialStep(A, n, n + 1, i, i,log);
            }
            
            //вывод р-та
            for (int i = 0; i < n; i++)
                xDataGridView[0, i].Value = -A[i, n];
        }

        //запись лога в файл
        private void button2_Click(object sender, EventArgs e)
        {
            Lab1.SaveLog(log);
        }
    }
}
