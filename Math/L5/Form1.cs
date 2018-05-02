using ClassLib;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace L5
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
                dataGridView1.Rows[i].HeaderCell.Value = $"y{(i + 1)}|u{i+1}";
            dataGridView1.Rows[n - 1].HeaderCell.Value = "Z|1";
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

            //заполнение по лекционному примеру
            if (radioButton1.Checked)
            {
                a = new double[,]
                {
                    {1,0,-3,2,-1,3 },
                    {-1,1,0,-1,-1,3},
                    {1,3,-1,-1,1,-2 },
                    {1,-1,0,0,-1,0 }
                };
                radioButton3.Checked = radioButton4.Checked = 
                    radioButton5.Checked = radioButton6.Checked = false;
            }

            //заполнение по варианту из постановки задачи
            else
            {
                a = new double[,]
                {
                    {-1,-4,-3,-2,-1,9 },
                    {1,-2,1,-2,-1,6},
                    {-1,-2,0,-2,1,2 },
                    {1,-4,5,-9,-2,0 }
                };
            }

            //запись в таблицу
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    dataGridView1[j, i].Value = a[i, j].ToString();
        }

        //отрицание неравенства по выбору пользователя
        private void radioButton4_CheckedChanged(object sender, EventArgs e)
        {
            radioButton1.Checked = false;
            radioButton2.Checked = true;

            //исходная таблица по варианту 24
            double[,] a = new double[,]
                {
                    {-1,-4,-3,-2,-1,9 },
                    {1,-2,1,-2,-1,6},
                    {-1,-2,0,-2,1,2 },
                    {1,-4,5,-9,-2,0 }
                };

            //если выбрано отрицание
            if (!radioButton6.Checked)
            {
                //выбираем строку
                int p;
                if (radioButton5.Checked)
                    p = 2;
                else if (radioButton4.Checked)
                    p = 1;
                else
                    p = 0;
                //и берем ее по знаку
                for (int j = 0; j < m; j++)
                    a[p, j] *= -1;
            }

            //запись изменений в таблицу
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    dataGridView1[j, i].Value = a[i, j].ToString();
        }

        //поиск решения
        private void findSolutionButton_Click(object sender, EventArgs e)
        {
            log.Clear();
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
                        if (j != m - 1)
                            a[i, j] *= -1;
                    }
                }

            //заголовки
            List<string> xs = new List<string>(
                new string[]
                {
                    "-x1|v1","-x2|v2","-x3|v3","-x4|v4","-x5|v5"
                });
            List<string> ys = new List<string>(
                new string[]
                {
                    "y1|u1","y2|u2","y3|u3"
                });
            try
            {
                Lab2.FindBasicSolution(a, n, m, xs, ys, log);
                Lab2.FindOptimalSolution(a, n, m, xs, ys, log);
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
                resLabel.Text = "";
                return;
            }

            //выводим максимум
            //и коэффициенты при х
            resLabel.Text = $"z|w = {a[n - 1, m - 1]}";
            for(int i=0;i<ys.Count;i++)
            {
                if (ys[i][0] == 'x')
                    resLabel.Text += $"\n{ys[i].Split('|')[0]}:{a[i, m - 1].ToString("F2")}";
            }
            for(int j=0;j<xs.Count;j++)
            {
                if (xs[j].StartsWith("-x"))
                {
                    resLabel.Text += $"\n{xs[j].Split('|')[1]}:{a[n - 1, j].ToString("F2")}";
                }
            }
        }
    }
}
