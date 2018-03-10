using ClassLib;
using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;

namespace L1
{
    public partial class T5Form : Form
    {
        int n = 1;

        public T5Form()
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
            log.Add("решение системы третьим способом");
            //чтение матрицы А из таблицы
            double[,] A = new double[n, n + 1];

            //перебираем все ячейки
            for (int i = 0; i < n; i++)
            {
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
            for(int i=0;i<n;i++)
            {
                log.Add("");
                for(int j = 0; j < n + 1; j++)
                {
                    log[log.Count - 1] += A[i, j].ToString("F2") + "\t";
                }
            }

            //решение третьим способом
            List<List<double>> list = new List<List<double>>();
            for (int i = 0; i < n; i++)
            {
                if (A[0, 0] == 0)
                {
                    MessageBox.Show("осевой элемент равен нулю\r\n" +
                    "обратную матрицу вычислить нельзя\r\n" +
                    "заполните главную диагональ");
                    return;
                }

                //делаем осевой шаг
                Lab1.AxialStep(A, n - i, n + 1 - i, 0, 0,log);

                //запоминаем зависимость
                List<double> li = new List<double>();
                for (int j = 1; j < n + 1-i; j++)
                    li.Add(-A[0,j]);

                //добавляем зависимость в список
                list.Add(li);

                //обрезаем матрицу
                A = Lab1.CutMatrix(A, n - i, n + 1 - i, 0, 0);
                
            }

            //выводим зависимости в лог
            log.Add("зависимости:");
            for(int i = 0; i < list.Count; i++)
            {
                //имя переменной
                log.Add("x" + (list.Count - i) + ":");

                //правая часть выражения
                log[log.Count - 1] += list[i][list[i].Count - 1];
                //минус левая
                for(int j = 0; j<list[i].Count-1; j++)
                {

                    log[log.Count - 1] +=
                        //имя переменной
                        " - x" + (list[i].Count-j-1) +
                        //коэффициент
                        " * "+ list[i][j].ToString("F2");
                }
            }

            //вычисление и запись ответа в лог
            log.Add("ответ:");
            double[] X = new double[n];
            for (int i = n - 1; i >= 0; i--)
            {
                //значение из правой части выражения
                X[i] = list[i][list[i].Count - 1];
                for (int j = n - 1, k=list[i].Count-2; j > i&&k>=0; j--,k--)
                {
                    //отнимаем произведение коэффициента и значения х
                    X[i] -= X[j] * list[i][k];
                }
                log.Add(X[i].ToString("F2"));
            }

            //вывод р-та на форму
            for (int i = 0; i < n; i++)
                xDataGridView[0, i].Value = X[i];
        }

        //запись лога в файл
        private void button2_Click(object sender, EventArgs e)
        {
            Lab1.SaveLog(log);
        }
    }
}
