using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClassLib;

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

            //чистим соержимое
            aDataGridView.Rows.Clear();
            aDataGridView.Columns.Clear();

            //устанавливаем длину заголовка строк
            //она должна быть больше
            aDataGridView.RowHeadersWidth = 50;
            for (int i = 0; i < m; i++)
            {
                //добавляем колонки и подписываем их
                aDataGridView.Columns.Add("a" + i, (i + 1).ToString());

                //устанавливаем ширину по умолчанию
                aDataGridView.Columns[i].Width = 40;

            }
            for(int j = 0; j < n; j++)
            {

                //добавляем и подписываем строки
                aDataGridView.Rows.Add();
                aDataGridView.Rows[j].HeaderCell.Value = (j + 1).ToString();
            }
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
