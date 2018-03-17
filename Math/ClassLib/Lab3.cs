using System;
using System.Collections.Generic;

namespace ClassLib
{
    public class Lab3
    {
        public static double[,] RemoveColumn(double[,] a,int n,int m,int s)
        {
            //новая матрица на размер меньше
            double[,] res = new double[n, m - 1];

            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < m; j++)
                {
                    //пропускаем удаляемый столбец
                    if (j == s) continue;

                    //индексы в новой матрице
                    //если они за удаляемым столбцом
                    //смещаем на один влево
                    int y = j;
                    if (y > s) y--;
                    res[i, y] = a[i, j];
                }
            }

            return res;
        }

        public static void RemoveZeroRows(ref double[,] a, ref int n,ref int m, List<string> xs, List<string> ys, List<string> log = null)
        {
            log.Add("удаление 0-строк из системы:");
            Lab2.WriteSystemInLog(a, n, m, xs, ys, log);

            int r = -1;
            for (int i = 0; i < ys.Count; i++)
                if (ys[i] == "0")
                {
                    r = i;
                    break;
                }

            int zeroRow = r;
            if(r==-1)
            {
                log.Add("ноль строк нет");
                return;
            }

            int s = -1;
            for(int j = 0; j < m-1; j++)
            {
                if (a[r, j] > 0)
                {
                    s = j;
                    break;
                }
            }

            if (s == -1)
                throw new Exception("no positive values in zero-row");

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
            if (r == -1)
                throw new InvalidOperationException(
                    "cant find non-negative relation");

            // меняем имена в шапках
            string z = xs[s];
            xs[s] = ys[r];
            ys[r] = z;

            //мжи, записываем в лог результат
            Lab2.ModifiedAxialStep(a, n, m, r, s);
            if (log != null)
            {
                //заголовок с шапкой xs
                log.Add($"процедура МЖИ[{r},{s}]:");
                Lab2.WriteSystemInLog(a, n, m, xs, ys, log);
            }

            if (r == zeroRow)
            {
                a = RemoveColumn(a, n, m, s);
                xs.RemoveAt(s);
                m--;
            }
            RemoveZeroRows(ref a,ref n,ref m, xs, ys, log);
        }
    }
}
