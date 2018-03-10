using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;

namespace ClassLib
{
    public class Lab1
    {
        public static void SaveLog(List<string> log)
        {
            OpenFileDialog openFileDialog1 = new OpenFileDialog();
            openFileDialog1.CheckFileExists = false;
            openFileDialog1.ShowDialog();
            StreamWriter writer = new StreamWriter(
                new FileStream(openFileDialog1.FileName, FileMode.OpenOrCreate));
            try
            {
                foreach (string line in log)
                    writer.WriteLine(line);
            }
            finally
            {
                writer.Close();
            }
        }

        /// <summary>
        /// копирование матриц
        /// </summary>
        /// <param name="ar">матрица</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        /// <returns>копия матрицы</returns>
        static double[,] Copy(double[,] ar, int n, int m)
        {
            //перебираем строки и столбцы
            //и переносим в копию матрицы
            double[,] res = new double[n, m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    res[i, j] = ar[i, j];
            return res;
        }

        /// <summary>
        /// осевой шаг
        /// </summary>
        /// <param name="a">матрица</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        /// <param name="r">разрешающая строка</param>
        /// <param name="s">разрешающий столбец</param>
        /// <param name="log">лог вычислений</param>
        public static void AxialStep(double[,] a, int n, int m, int r, int s, List<string> log=null)
        {
            //заголовок лога
            if (log != null)
            {
                log.Add($"осевой шаг[{r},{s}]");
            }

            //запоминаем разрешающий элемент
            double ars = a[r, s];
            if (ars == 0) return;
            //1 установка разрешающего элемента
            a[r, s] = 1;
            //2,3 взятие разр строки по отрицательному знаку
            for (int j = 0; j < m; j++)
                if (j != s)
                    a[r, j] *= -1;

            //4 вычисление остальных элементов по формуле
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                {
                    if (i != r && j != s)
                    {
                        a[i, j] = a[i, j] * ars + a[r, j] * a[i, s];
                    }
                }

            //5 деление всех элементов на разр элемент
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                {
                    a[i, j] /= ars;
                }


            //запись р-та в лог
            for (int i = 0; i < n; i++)
            {
                log.Add("");
                for (int j = 0; j < m; j++)
                {
                    log[log.Count - 1] += a[i, j].ToString("F2") + "\t";
                }
            }
        }

        /// <summary>
        /// обращение матрицы
        /// </summary>
        /// <param name="a">матрица</param>
        /// <param name="n">размер матрицы</param>
        /// <param name="log">лог вычислений</param>
        /// <returns>обратная матрица</returns>
        public static double[,] InvertMatrix(double[,] a, int n, List<string> log=null)
        {
            //заголовок лога и исходное значение
            if (log != null)
            {
                log.Add("обращение матрицы:");
                for (int i = 0; i < n; i++)
                {
                    log.Add("");
                    for (int j = 0; j < n; j++)
                    {
                        log[log.Count - 1] += a[i, j].ToString("F2") + "\t";
                    }
                }
            }

            //обращается и возвращается копия исходной матрицы
            double[,] c = Copy(a, n, n);
            for (int i = 0; i < n; i++)
            {
                if (c[i, i] == 0)
                    return null;
                else
                    AxialStep(c, n, n, i, i, log);
            }

            return c;
        }

        /// <summary>
        /// вычисление ранга матрицы
        /// </summary>
        /// <param name="a">матрица</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        /// <returns>ранг</returns>
        public static int Rank(double[,] a, int n, int m)
        {
            //метод модифицирует операнд,
            //поэтому используем копию
            double[,] c = Copy(a, n, m);

            //минимум из кол-ва строк и столбцов
            int p = n < m ? n : m;
            int r = 0;
            for (int i = 0; i < p; i++)
                //считаем кол-во удачных шагов
                if (c[i, i] == 0)
                    continue;
                else
                {
                    AxialStep(c, n, m, i, i);
                    r++;
                }

            return r;
        }

        /// <summary>
        /// перемножение матриц
        /// </summary>
        /// <param name="op1">матрица слева</param>
        /// <param name="op2">матрица справа</param>
        /// <param name="n">кол-во строк левой матрицы</param>
        /// <param name="p">
        /// кол-во столбцов левой матрицы и 
        /// кол-во строк правой матрицы</param>
        /// <param name="m">кол-во столбцов правой матрицы
        /// </param>
        /// <param name="log">лог вычислений</param>
        /// <returns>р-т операции</returns>
        public static double[,] MultiplyMatrixes(double[,] op1, double[,] op2, int n, int p, int m, List<string> log=null)
        {
            //заголовок и исходное значение операндов
            if (log != null)
            {
                log.Add("перемножение матриц:");
                log.Add("матрица 1:");
                for (int i = 0; i < n; i++)
                {
                    log.Add("");
                    for (int j = 0; j < p; j++)
                    {
                        log[log.Count - 1] += op1[i, j].ToString("F2") + "\t";
                    }
                }
                log.Add("матрица 2:");
                for (int i = 0; i < p; i++)
                {
                    log.Add("");
                    for (int j = 0; j < m; j++)
                    {
                        log[log.Count - 1] += op2[i, j].ToString("F2") + "\t";
                    }
                }
            }

            //умножаем по правилу строка на столбец
            double[,] res = new double[n, m];
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < m; j++)
                {
                    res[i, j] = 0;
                    for (int k = 0; k < p; k++)
                        res[i, j] += op1[i, k] * op2[k, j];
                }
            }

            //р-т операции в лог
            log.Add("результат:");
            for (int i = 0; i < n; i++)
            {
                log.Add("");
                for (int j = 0; j < m; j++)
                {
                    log[log.Count - 1] += res[i, j].ToString("F2") + "\t";
                }
            }
            return res;
        }

        /// <summary>
        /// обрезка матрицы
        /// </summary>
        /// <param name="a">матрица</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        /// <param name="r">удаляемая строка</param>
        /// <param name="s">удаляемый столбец</param>
        /// <returns>обрезанная матрица</returns>
        public static double[,] CutMatrix(double[,] a,int n,int m,int r,int s)
        {
            //новая матрица на размер меньше
            double[,] res = new double[n - 1, m - 1];

            for(int i=0;i<n;i++)
            {
                //пропускаем удаляемую строку
                if (i == r) continue;
                for(int j=0;j<m;j++)
                {
                    //пропускаем удаляемый столбец
                    if (j == s) continue;

                    //индексы в новой матрице
                    //если они за удаляемой строкой/столбцом
                    //смещаем на один вверх/влево
                    int x = i, y = j;
                    if (x > r) x--;
                    if (y > s) y--;
                    res[x, y] = a[i, j];
                }
            }

            return res;
        }

        /// <summary>
        /// построить таблицу для системы
        /// </summary>
        /// <param name="view">таблица</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        public static void BuildTable(DataGridView view, int n, int m)
        {
            //очистка и установка размера
            view.Rows.Clear();
            view.Columns.Clear();
            view.RowHeadersWidth = 50;
            for (int i = 0; i < m; i++)
            {
                //добавляем колонки и подписываем их
                view.Columns.Add("a" + i, (i + 1).ToString());

                //устанавливаем ширину по умолчанию
                view.Columns[i].Width = 40;
            }
            for (int i = 0; i < n; i++)
            {
                //добавляем и подписываем строки
                view.Rows.Add();
                view.Rows[i].HeaderCell.Value = (i + 1).ToString();
            }
        }

        /// <summary>
        /// построить вектор для системы
        /// </summary>
        /// <param name="view">таблица</param>
        /// <param name="n">количество строк</param>
        /// <param name="name">имя вектора</param>
        public static void BuildVector(DataGridView view, int n,string name)
        {
            //очистка, установка размера/имени
            view.Rows.Clear();
            view.Columns.Clear();
            view.RowHeadersWidth = 50;
            view.Columns.Add("c", name);
            view.Columns[0].Width = 40;
            
            //добавление строк
            for (int i = 0; i < n; i++)
            {
                view.Rows.Add();
                view.Rows[i].HeaderCell.Value = (i + 1).ToString();
            }
        }
    }
}
