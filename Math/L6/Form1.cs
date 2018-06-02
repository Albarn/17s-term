using ClassLib;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace L6
{
    public partial class Form1 : Form
    {
        //матрица, ее размер
        double[,] A = new double[size, size];
        const int size = 3;

        //шапки таблиц и лог вычислений
        List<string> top = new List<string>(
            new string[]
            {
                "1","2","3"
            });
        List<string> left = new List<string>(
            new string[]
            {
                "1","2","3"
            });
        List<string> log=new List<string>();

        public Form1()
        {
            InitializeComponent();

            //пишем матрицу в таблицу
            Lab6.WriteMatrix(dataGridView1, A,top,left);
        }

        //решение системы
        private void solveButton_Click(object sender, EventArgs e)
        {
            //очищаем лог
            log.Clear();

            //читаем матрицу из таблицы и получаем седловую точку
            A = Lab6.ReadMatrix(dataGridView1, out top, out left);
            GoodPoint point=Lab6.FindGoodPoint(A, top, left, log);

            //если она есть, выводим ее
            if (false&&point != null)
            {
                ansLabel.Text = point.ToString();
            }

            //иначе, выводим смешанные стратегии,
            //также проигрываем партии и записыввем их влог
            else
            {
                var ans = Lab6.FindStrategies(A, log);

                //стратегии игрока 1
                ansLabel.Text = "p1:";
                foreach(Strategy s in ans[0])
                {
                    ansLabel.Text += "\n" + s.ToString();
                }

                //игрока 2
                ansLabel.Text += "\np2:";
                foreach (Strategy s in ans[1])
                {
                    ansLabel.Text += "\n" + s.ToString();
                }

                Lab6.PlayGame(A, ans[0], ans[1], log);
            }
            
        }

        //сохраняем лог в файл
        private void saveButton_Click(object sender, EventArgs e)
        {
            Lab1.SaveLog(log);
        }
    }
}
