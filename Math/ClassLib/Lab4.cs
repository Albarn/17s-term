using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClassLib
{
    public class Lab4
    {
        /// <summary>
        /// добавление ограничения в систему
        /// </summary>
        /// <param name="a">система</param>
        /// <param name="n">кол-во строк</param>
        /// <param name="m">кол-во столбцов</param>
        /// <param name="xs">шапка сверху</param>
        /// <param name="ys">шапка слева</param>
        /// <param name="log">лог вычислений</param>
        /// <returns>истина, если ограничение добавлени</returns>
        public static bool AddConstraint(ref double[,] a, ref int n, ref int m, List<string> xs, List<string> ys, List<string> log = null)
        {
            List<int> currentSolution = new List<int>();
            for(int i=0;i<ys.Count;i++)
            {
                if (ys[i].StartsWith("x")&&AfterDot(a[i,m-1])>0.0001)
                {
                    currentSolution.Add(i);
                }
            }

            if (currentSolution.Count == 0)
                return false;

            log.Add("добавление ограничения для системы:");
            Lab2.WriteSystemInLog(a, n, m, xs, ys, log);

            //находим строку, где у Х максимальная дробная часть
            int max = currentSolution[0];
            foreach(int i in currentSolution)
            {
                if (AfterDot(a[i, m - 1]) > AfterDot(a[max,m-1]))
                {
                    max = i;
                }
            }

            double maxValue = AfterDot(a[max, m - 1]);
            log.Add("элемент с максимальной дробной частью: " + ys[max]+": "+maxValue.ToString("F2"));

            //расширяем шапку слева
            ys.Add("s" + max);

            //система, с местом для нового ограничения
            double[,] newA = new double[n+1, m];

            //заполняем до строки Z
            for (int i = 0; i < n - 1; i++)
                for (int j = 0; j < m; j++)
                    newA[i, j] = a[i, j];

            //пишем строку Z последней
            for (int j = 0; j < m; j++)
                newA[n, j] = a[n - 1, j];

            //a = newA;

            log.Add("ограничение:");
            string constraint = ys[n-1]+"=";
            for (int j = 0; j < m - 1; j++)
            {
                newA[n - 1, j] = -AfterDot(a[max, j]);
                constraint += newA[n - 1, j].ToString("F2");
                constraint += $"({xs[j]})";
            }
            newA[n - 1, m - 1] = -maxValue;
            constraint += newA[n - 1, m - 1].ToString("F2");
            constraint += ">=0";
            log.Add(constraint);
            log.Add("");
            n++;
            a = newA;
            return true;
        }

        private static double AfterDot(double a)
        {
            return Math.Abs(a - ((int)a));
        }
    }
}
