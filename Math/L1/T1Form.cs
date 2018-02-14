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

            //чистим соержимое
            aDataGridView.Rows.Clear();
            aDataGridView.Columns.Clear();
            cDataGridView.Rows.Clear();
            cDataGridView.Columns.Clear();

            //устанавливаем длину заголовка строк
            //она должна быть больше
            aDataGridView.RowHeadersWidth = 50;
            cDataGridView.RowHeadersWidth = 50;
            for (int i = 0; i < n; i++)
            {
                //добавляем колонки и подписываем их
                aDataGridView.Columns.Add("a" + i, (i + 1).ToString());
                cDataGridView.Columns.Add("c" + i, (i + 1).ToString());

                //устанавливаем ширину по умолчанию
                aDataGridView.Columns[i].Width = 40;
                cDataGridView.Columns[i].Width = 40;

                //добавляем и подписываем строки
                aDataGridView.Rows.Add();
                aDataGridView.Rows[i].HeaderCell.Value = (i + 1).ToString();
                cDataGridView.Rows.Add();
                cDataGridView.Rows[i].HeaderCell.Value = (i + 1).ToString();
            }
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
