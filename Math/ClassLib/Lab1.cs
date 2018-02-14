using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClassLib
{
    public class Lab1
    {
        static double[,] Copy(double[,] ar, int n, int m)
        {
            double[,] res = new double[n, m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    res[i, j] = ar[i, j];
            return res;
        }

        static void AxialStep(double[,] a, int n, int m, int r, int s)
        {
            double ars = a[r, s];
            if (ars == 0) return;
            //1
            a[r, s] = 1;
            //2,3
            for (int j = 0; j < m; j++)
                if (j != s)
                    a[r, j] *= -1;

            //4
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                {
                    if (i != r && j != s)
                    {
                        a[i, j] = a[i, j] * ars + a[r, j] * a[i, s];
                    }
                }

            //5
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                {
                    a[i, j] /= ars;
                }
        }

        public static double[,] InvertMatrix(double[,] a, int n)
        {
            double[,] c = Copy(a, n, n);
            for (int i = 0; i < n; i++)
                if (c[i, i] == 0)
                    return null;
                else
                    AxialStep(c, n, n, i, i);

            return c;
        }

        public static int Rank(double[,] a, int n, int m)
        {

            double[,] c = Copy(a, n, m);
            int p = n < m ? n : m;
            int r = 0;
            for (int i = 0; i < p; i++)
                if (c[i, i] == 0)
                    continue;
                else
                {
                    AxialStep(c, n, m, i, i);
                    r++;
                }

            return r;
        }

        public static double[,] MultiplyMatrixes(double[,] op1, double[,] op2, int n, int p, int m)
        {
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
            return res;
        }
    }
}
