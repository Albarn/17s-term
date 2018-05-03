using System;
using System.Collections.Generic;

namespace ClassLib
{
    public class Lab2
    {
        public static void WriteSystemInLog(double[,] a, int n, int m, List<string> xs, List<string> ys, List<string> log, string leftFunc = "z", string upFunc = "1")
        {
            //заголовок с шапкой xs
            log.Add("\\\t");
            foreach (string x in xs)
                log[log.Count - 1] += x + "\t";
            log[log.Count - 1] += upFunc;

            //матрица
            for (int i = 0; i < n; i++)
            {
                //шапка слева
                if (i != n - 1)
                    log.Add(ys[i] + "\t");
                else
                    log.Add(leftFunc + "\t");
                for (int j = 0; j < m; j++)
                {
                    log[log.Count - 1] += a[i, j].ToString("F2") + "\t";
                }
            }
        }

        /// <summary>
        /// поиск оптимального решения
        /// </summary>
        /// <param name="a">система</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        /// <param name="xs">шапка сверху</param>
        /// <param name="ys">шапка слева</param>
        /// <param name="log">лог вычислений</param>
        public static void FindOptimalSolution(double[,] a, int n, int m, List<string> xs, List<string> ys, List<string> log = null)
        {
            //запись в лог исходной системы
            if (log != null)
            {
                //заголовок с шапкой xs
                log.Add("поиск оптимального решения для системы:");
                WriteSystemInLog(a, n, m, xs, ys, log);
            }

            //поиск отрицательного элемента в
            //z строке
            int s = -1;
            for (int j = 0; j < m - 1; j++)
                if (a[n - 1, j] < 0)
                {
                    s = j;
                    break;
                }

            if (s == -1)
            {
                log.Add("в строке z нет отрицательных элементов");
                log.Add("решение найдено");
                return;
            }

            //поиск МНО в разрешающем столбце
            int r = -1;
            for (int i = 0; i < n - 1; i++)
            {
                double dev = a[i, m - 1] / a[i, s];
                if (dev >= 0)
                {
                    if (r == -1)
                        r = i;
                    else
                    {
                        if (dev < a[r, m - 1] / a[r, s])
                            r = i;
                    }
                }
            }
            if (r == -1)
                throw new InvalidOperationException(
                    "функция цели не ограничена сверху");

            //меняем имена в шапках
            string z = xs[s];
            xs[s] = ys[r];
            ys[r] = z;

            //если влево уходит х, убираем у него минус
            if (ys[r].Contains("-x"))
            {
                ys[r]=ys[r].Replace("-x", "x");
            }
            //если х уходит вверх, добавляем минус
            if (xs[s].Contains("x"))
            {
                xs[s]=xs[s].Replace("x", "-x");
            }

            //мжи, записываем в лог результат
            ModifiedAxialStep(a, n, m, r, s);
            if (log != null)
            {
                //заголовок с шапкой xs
                log.Add($"процедура МЖИ[{r},{s}]:");
                //WriteSystemInLog(a, n, m, xs, ys, log);
            }

            //пробуем еще раз
            FindOptimalSolution(a, n, m,xs,ys, log);
        }

        /// <summary>
        /// поиск оптимального решения
        /// </summary>
        /// <param name="a">система</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        /// <param name="xs">шапка сверху</param>
        /// <param name="ys">шапка слева</param>
        /// <param name="log">лог вычислений</param>
        public static void FindBasicSolution(double[,] a, int n, int m, List<string> xs, List<string> ys, List<string> log = null)
        {
            //запись исходной системы в лог
            if (log != null)
            {
                log.Add("поиск опорного решения для системы:");
                WriteSystemInLog(a, n, m, xs, ys, log);
            }
            //1 находим строку с отрицательным
            //элементом в единичном столбце
            int r = -1;
            for (int i = 0; i < n - 1; i++)
            {
                if (a[i, m - 1] < 0)
                {
                    r = i;
                    break;
                }
            }
            if (log != null)
                log.Add(
                    "строка с отрицательным значением в единичном столбце:" + r);
            if (r == -1) return;

            //2 находим столбец с отрицательным элементом в
            //строке r
            int s = -1;
            for (int j = 0; j < m - 1; j++)
                if (a[r, j] < 0)
                {
                    s = j;
                    break;
                }

            if (s == -1)
                throw new InvalidOperationException("система противоречива");

            //3 поиск разрешающей строки
            //через МНО
            r = -1;
            for (int i = 0; i < n - 1; i++)
            {
                double dev = a[i, m - 1] / a[i, s];
                if (dev >= 0)
                {
                    if (r == -1)
                        r = i;
                    else
                    {
                        if (dev < a[r, m - 1] / a[r, s])
                            r = i;
                    }
                }
            }

            //4 процедура МЖИ
            string z = xs[s];
            xs[s] = ys[r];
            ys[r] = z;

            //если влево уходит х, убираем у него минус
            if (ys[r].Contains("-x"))
            {
                ys[r]=ys[r].Replace("-x", "x");
            }
            //если х уходит вверх, добавляем минус
            if (xs[s].Contains("x"))
            {
                xs[s]=xs[s].Replace("x", "-x");
            }
            ModifiedAxialStep(a, n, m, r, s);
            if (log != null)
            {
                log.Add($"процедура МЖИ[{r},{s}]:");
                //WriteSystemInLog(a, n, m, xs, ys, log);
            }
            FindBasicSolution(a, n, m,xs,ys, log);
        }

        /// <summary>
        /// мжи
        /// </summary>
        /// <param name="a">матрица</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        /// <param name="r">разрешающая строка</param>
        /// <param name="s">разрешающий столбец</param>
        /// <param name="log">лог вычислений</param>
        public static void ModifiedAxialStep(double[,] a, int n, int m, int r, int s)
        {
            //запоминаем разрешающий элемент
            double ars = a[r, s];
            if (ars == 0) return;
            //1 установка разрешающего элемента
            a[r, s] = 1;
            //2,3 взятие разр столбца по отрицательному знаку
            for (int i = 0; i < n; i++)
                if (i != r)
                    a[i, s] *= -1;

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
        }
    }
}
