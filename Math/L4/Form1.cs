using ClassLib;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace L4
{
    public partial class Form1 : Form
    {
        const int n = 4, m = 6;

        public Form1()
        {
            InitializeComponent();

            //строим таблицу для системы
            dataGridView1.Rows.Add(n);
            for (int i = 0; i < n - 1; i++)
                dataGridView1.Rows[i].HeaderCell.Value = (i + 1).ToString();
            dataGridView1.Rows[n - 1].HeaderCell.Value = "Z";
        }

        /// <summary>
        /// лог вычислений
        /// </summary>
        List<string> log = new List<string>();

        //сохранение лога в файл
        private void saveLogButton_Click(object sender, EventArgs e)
        {
            Lab1.SaveLog(log);
        }

        //заполнение таблицы по выбору пользователя
        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
            double[,] a = null;
            List<char> list = new List<char>();
            //заполнение по лекционному примеру
            if (radioButton1.Checked)
            {
                a = new double[,]
                {
                    {1,-2,1,-2,-1,-6 },
                    {-1,-4,-3,-2,-1,-9},
                    {-1,-2,0,-2,1,-2 },
                    {2,2,1,1,1,0 }
                };
                list = new List<char>(
                    new char[]
                    {
                        '0','0','0'
                    });
            }

            //заполнение по варианту из постановки задачи
            else
            {
                a = new double[,]
                {
                    {-1,-4,-3,-2,-1,9 },
                    {1,-2,1,-2,-1,6},
                    {-1,-2,0,-2,1,2 },
                    {1,-4,5,9,-2,0 }
                };
                list = new List<char>(
                    new char[]
                    {
                        '0','y','y'
                    });
            }

            //запись в таблицу
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    dataGridView1[j, i].Value = a[i, j].ToString();
            for (int i = 0; i < n - 1; i++)
                dataGridView1[m, i].Value = list[i].ToString();
        }

        //поиск решения
        private void findSolutionButton_Click(object sender, EventArgs e)
        {
            //чтение системы из таблицы
            double[,] a = new double[n, m];
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                {
                    //если клетка пуста, присваиваем ноль
                    if (dataGridView1[j, i].Value == null)
                    {
                        a[i, j] = 0;
                    }
                    else
                    {
                        double.TryParse(
                            dataGridView1[j, i].Value.ToString(), out a[i, j]);
                        a[i, j] *= -1;
                    }
                }

            //заголовки
            List<string> xs = new List<string>(
                new string[]
                {
                    "x1","x2","x3","x4","x5"
                });
            List<string> ys = new List<string>();
            for (int i = 0; i < n - 1; i++)
                ys.Add(dataGridView1[m, i].Value.ToString());
            int localN = n, localM = m;
            //try
            //{
                Lab3.RemoveZeroRows(ref a,ref localN,ref localM, xs, ys, log);
                Lab2.FindBasicSolution(a, localN, localM, xs, ys, log);
                Lab2.FindOptimalSolution(a, localN, localM, xs, ys, log);
            //}
            //catch(Exception ex)
            //{
            //    MessageBox.Show(ex.Message);
            //    resLabel.Text = "";
            //    return;
            //}

            //выводим максимум
            //и коэффициенты при х
            resLabel.Text = $"z = {a[localN - 1, localM - 1]}";
            for(int i=0;i<ys.Count;i++)
            {
                if (ys[i][0] == 'x')
                    resLabel.Text += $"\n{ys[i]}:{a[i, localM - 1]}";
            }
        }
    }
}
