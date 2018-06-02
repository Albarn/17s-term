using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Linq.Expressions;

namespace ClassLib
{
    public class Lab7
    {
        public static int Vald(double[,] a)
        {
            int n = a.GetUpperBound(0) + 1;
            int m = a.GetUpperBound(1) + 1;
            int ans = -1;
            double ansC = -1;

            for(int i = 0; i < n; i++)
            {
                int min = -1;
                double minC = -1;
                for(int j = 0; j < m; j++)
                {
                    if (minC > a[i, j] || min == -1)
                    {
                        min = j;
                        minC = a[i, j];
                    }
                }
                if (ansC < minC || ans == -1)
                {
                    ans = i;
                    ansC = minC;
                }
            }

            return ans;
        }

        public static int Maximax(double[,] a)
        {
            int n = a.GetUpperBound(0) + 1;
            int m = a.GetUpperBound(1) + 1;
            int ans = -1;
            double ansC = -1;

            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < m; j++)
                {
                    if (ansC < a[i, j] || ans == -1)
                    {
                        ans = i;
                        ansC = a[i, j];
                    }
                }
            }

            return ans;
        }

        public static int Gurvic(double[,] a, double y)
        {
            int n = a.GetUpperBound(0) + 1;
            int m = a.GetUpperBound(1) + 1;
            int ans = -1;
            double ansC = -1;

            for (int i = 0; i < n; i++)
            {
                int min = -1;
                double minC = -1;
                int max = -1;
                double maxC = -1;
                for (int j = 0; j < m; j++)
                {
                    if (minC > a[i, j] || min == -1)
                    {
                        min = j;
                        minC = a[i, j];
                    }
                    if (maxC < a[i, j] || max == -1)
                    {
                        max = j;
                        maxC = a[i, j];
                    }
                }

                double s = y * minC + (1 - y) * maxC;
                if (ansC < s || ans == -1)
                {
                    ans = i;
                    ansC = s;
                }
            }

            return ans;
        }

        public static int Sevij(double[,] a)
        {
            int n = a.GetUpperBound(0) + 1;
            int m = a.GetUpperBound(1) + 1;

            double[,] r = GetRisk(a);

            int ans = -1;
            double ansC = -1;
            for (int i = 0; i < n; i++)
            {
                int max = -1;
                double maxC = -1;
                for (int j = 0; j < m; j++)
                {
                    if (maxC < r[i, j] || max == -1)
                    {
                        max = j;
                        maxC = r[i, j];
                    }
                }

                if (ansC > maxC || ans == -1)
                {
                    ans = i;
                    ansC = maxC;
                }
            }

            return ans;
        }

        private static double[,] GetRisk(double[,] a)
        {
            int n = a.GetUpperBound(0) + 1;
            int m = a.GetUpperBound(1) + 1;

            double[,] r = new double[n, m];
            for (int j = 0; j < m; j++)
            {
                int max = -1;
                double maxC = -1;
                for (int i = 0; i < n; i++)
                {
                    if (maxC < a[i, j] || max == -1)
                    {
                        max = i;
                        maxC = a[i, j];
                    }
                }

                for (int i = 0; i < n; i++)
                {
                    r[i, j] = maxC - a[i, j];
                }
            }

            return r;
        }

        public static int Bayes(double[,] a, double[] p)
        {
            int n = a.GetUpperBound(0) + 1;
            int m = a.GetUpperBound(1) + 1;

            int ans = -1;
            double ansC = -1;

            
            for(int i = 0; i < n; i++)
            {
                double c = 0;
                for(int j = 0; j < m; j++)
                {
                    c += p[j] * a[i, j];
                    Console.Write(" " + c);
                }

                if (ansC < c || ans == -1)
                {
                    ans = i;
                    ansC = c;
                }
            }

            return ans;
        }

        public static int Laplace(double[,] a)
        {
            int n = a.GetUpperBound(0) + 1;
            int m = a.GetUpperBound(1) + 1;
            double p = 1.0 / m;

            int ans = -1;
            double ansC = -1;

            for (int i = 0; i < n; i++)
            {
                double c = 0;
                for (int j = 0; j < m; j++)
                {
                    c += p * a[i, j];
                    //Console.Write(" " + c.ToString("F2"));
                }

                if (ansC < c || ans == -1)
                {
                    ans = i;
                    ansC = c;
                }
            }

            return ans;
        }
    }
}
