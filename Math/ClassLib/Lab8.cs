using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClassLib
{
    public class Lab8
    {
        public double[,] SolveTransportTask(
            double[,] sp,
            List<double> po,
            List<double> pn)
        {
            throw new NotImplementedException();
        }

        public double[,] FindOptimalSolution(
            double[,] sp,
            double[,] a)
        {
            int n = sp.GetUpperBound(0) + 1;
            int m = sp.GetUpperBound(1) + 1;

            //потенциалы поставщиков и заказчиков
            Dictionary<int, double> uo = new Dictionary<int, double>();
            Dictionary<int, double> un = new Dictionary<int, double>();

            //первый потенциал
            uo.Add(0, 0);

            //поиск потенциалов
            do
            {
                for(int i = 0; i < n; i++)
                {
                    for(int j = 0; j < m; j++)
                    {

                        //если ячейка базисная
                        if (a[i, j] != 0)
                        {
                            //если известен потенциал поставщика,
                            //но не заказчика
                            if (uo.ContainsKey(i) && 
                                !un.ContainsKey(j))
                            {
                                //вычисляем неизвестное
                                un.Add(j, sp[i, j] - uo[i]);
                            }

                            //наоборот
                            else if (un.ContainsKey(j) && 
                                !uo.ContainsKey(i))
                            {
                                uo.Add(i, sp[i, j] - un[j]);
                            }
                        }
                    }
                }

                //пока все потенциалы не будут найдены
            } while (uo.Count != n || un.Count != m);

            //ищем плохую точку
            //где косвенная стоимость больше прямой
            int badi = -1;
            int badj = -1;
            double badC = -1;
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < m; j++)
                {
                    if (a[i, j] == 0)
                    {
                        //находим разницу
                        double diff = uo[i] + un[j] - sp[i, j];

                        //сравниваем с максимальной на данный момент
                        if (diff > badC || badi == -1 || badj == -1)
                        {
                            badi = i;
                            badj = j;
                            badC = diff;
                        }
                    }
                }
            }

            //если плохая точка есть
            if (badC > 0)
            {

                //цикл не построен
                bool bad = true;

                //вторая точка, для цикла
                int i = 0, j = 0;
                for (; i < n; i++)
                {
                    for (; j < m; j++)
                    {
                        //если для i,j,badi,badj
                        //есть все точки цикла
                        if (a[i, badj] != 0 &&
                            a[badi, j] != 0 &&
                            a[i, j] != 0)
                        {

                            //цикл построен
                            bad = false;
                            break;
                        }
                    }

                    //выходим, если цикл построен
                    if (!bad)
                    {
                        break;
                    }
                }

                //цикл невозможно построить
                if(bad)
                {
                    throw new InvalidOperationException();
                }

                //пересчет отправок
                double val = Math.Min(a[badi, j], a[i, badj]);
                a[badi, badj] += val;
                a[i, j] += val;
                a[badi, j] -= val;
                a[i, badj] -= val;

                //пробуем еще раз
                return FindOptimalSolution(sp, a);
            }

            return a;
        }

        public double[,] NorthWestAngle(
            double[,] sp,
            List<double> po,
            List<double> pn)
        {
            int n = sp.GetUpperBound(0) + 1;
            int m = sp.GetUpperBound(1) + 1;

            double[,] ans = new double[n, m];
            for (int i = 0, j = 0; i < n && j < m;)
            {
                ans[i, j] = Math.Min(po[i], pn[j]);
                po[i] -= ans[i, j];
                pn[j] -= ans[i, j];

                if (po[i] > 0)
                {
                    j++;
                }
                else
                {
                    if (pn[j] > 0)
                    {
                        i++;
                    }
                    else
                    {
                        i++;
                        j++;
                    }
                }
            }

            return ans;
        }

        public double[,] MinCost(
            double[,] sp,
            List<double> po,
            List<double> pn)
        {
            int n = sp.GetUpperBound(0) + 1;
            int m = sp.GetUpperBound(1) + 1;

            double[,] ans = new double[n, m];
            
            do
            {
                int mini = -1;
                int minj = -1;
                double min = -1;
                for (int i = 0; i < n; i++)
                {
                    if (po[i] == 0)
                    {
                        continue;
                    }

                    for (int j = 0; j < m; j++)
                    {
                        if (pn[j] == 0)
                        {
                            continue;
                        }

                        if (min > sp[i, j] || mini == -1 || minj == -1)
                        {
                            mini = i;
                            minj = j;
                            min = sp[i, j];
                        }
                    }
                }

                ans[mini, minj] = Math.Min(po[mini], pn[minj]);

                po[mini] -= ans[mini, minj];
                pn[minj] -= ans[mini, minj];
            } while (
            po.Aggregate((d1, d2) => d1 + d2) > 0&&
            pn.Aggregate((d1, d2) => d1 + d2) > 0);

            return ans;
        }
    }
}
