using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace ClassLib
{
    /// <summary>
    /// седловая точка
    /// </summary>
    public class GoodPoint
    {
        public Strategy Player1 { get; set; }
        public Strategy Player2 { get; set; }
        public override string ToString()
        {
            return Player1.ToString()+"\n"+Player2.ToString();
        }
    }

    /// <summary>
    /// стратегия игрока
    /// </summary>
    public class Strategy
    {
        /// <summary>
        /// номер стратегии(индекс строки/столбца)
        /// </summary>
        public int Number { get; set; }

        /// <summary>
        /// вероятность выбора стратегии
        /// </summary>
        public double Probability { get; set; }

        public override string ToString()
        {
            return Number + " " + Probability.ToString("F2");
        }
    }

    public class Lab6
    {
        /// <summary>
        /// запись матрицы в таблицу
        /// </summary>
        /// <param name="grid">таблица</param>
        /// <param name="a">матрица</param>
        /// <param name="top">шапка сверху</param>
        /// <param name="left">шапка снизу</param>
        public static void WriteMatrix(
            DataGridView grid,
            double[,] a, 
            List<string> top=null,
            List<string> left= null)
        {
            //установка начальных параметров таблицы
            if (top == null) top = new List<string>();
            if (left == null) left = new List<string>();
            int n = a.GetUpperBound(0)+1;
            int m = a.GetUpperBound(1)+1;

            //очистка предыдущего состояния
            grid.Rows.Clear();
            grid.Columns.Clear();

            //заполнение колонок
            for (int j = 0; j < m; j++)
            {
                grid
                    .Columns
                    .Add("col" + j, top.Count <=j ? " " : top[j]);
                grid
                    .Columns[j]
                    .Width = 30;
            }

            //заполнение строк
            grid.Rows.Add(n);
            for(int i = 0; i < n; i++)
            {
                grid
                    .Rows[i]
                    .HeaderCell.Value = left.Count <=i ? " " : left[i];
            }

            //вставка матрицы
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < m; j++)
                {
                    grid[j, i].Value = a[i, j];
                }
            }
        }

        /// <summary>
        /// чтение матрицы из таблицы
        /// </summary>
        /// <param name="grid">таблица</param>
        /// <param name="top">шапка сверху</param>
        /// <param name="left">шапка снизу</param>
        /// <returns>матрица</returns>
        public static double[,] ReadMatrix(
            DataGridView grid,
            out List<string> top,
            out List<string> left)
        {
            //установка начальных параметров
            top = new List<string>();
            left = new List<string>();
            double[,] a = new double[
                grid.Rows.Count-1, 
                grid.Columns.Count];

            //чтение данных в ячейках
            for(int i = 0; i < grid.Rows.Count-1; i++)
            {
                left.Add(grid.Rows[i].HeaderCell.Value.ToString());
                for (int j = 0; j < grid.Columns.Count; j++)
                {
                    if (top.Count < grid.Rows.Count)
                    {
                        top.Add(grid
                            .Rows[j]
                            .HeaderCell
                            .Value
                            .ToString());
                    }

                    double.TryParse(
                        grid[j, i].Value.ToString(),
                        out a[i, j]);
                }
            }
            return a;
        }

        /// <summary>
        /// поиск седловой точки
        /// </summary>
        /// <param name="a"></param>
        /// <param name="top"></param>
        /// <param name="left"></param>
        /// <param name="log"></param>
        /// <returns></returns>
        public static GoodPoint FindGoodPoint
            (double[,] a, List<string> top, List<string> left, List<string> log)
        {
            //вычисление размера матрицы
            //и внесение заголовка в лог
            int n = a.GetUpperBound(0)+1;
            int m = a.GetUpperBound(1)+1;
            log.Add("поиск седловой точки для системы:");
            Lab2.WriteSystemInLog(a, n, m, top, left, log,"3","");
            int k = -1;
            int mink = -1;

            //поиск наибольшего гарантированного выиграша
            for (int i = 0; i < n; i++)
            {
                int min = -1;
                for (int j = 0; j < m; j++)
                {
                    if (min == -1 || a[i, j] < a[i, min])
                    {
                        min = j;
                    }
                }

                if (k == -1 || a[i, min] > a[k, mink])
                {
                    k = i;
                    mink = min;
                }
            }
            log.Add("наибольший гарантированный выиграш А в точке");
            log.Add($"[{k}:{mink}]:{a[k, mink]}");

            //поиск наименьшего гарантированного проиграша
            int l = -1;
            int maxl = -1;
            for(int j = 0; j < m; j++)
            {
                int max=-1;
                for(int i = 0; i < n; i++)
                {
                    if (max == -1 || a[i, j] > a[i, max])
                    {
                        max = i;
                    }
                }
                if (l == -1 || a[max, j] < a[maxl, l])
                {
                    l = j;
                    maxl = max;
                }
            }
            log.Add("наименьший гарантированный проигрыш B в точке");
            log.Add($"[{maxl}:{l}]:{a[maxl, l]}");

            if (k == maxl && l == mink)
            {
                log.Add("седловая точка найдена");
                return new GoodPoint()
                {
                    Player1 = new Strategy() { Number = k, Probability = 1 },
                    Player2 = new Strategy() { Number = l, Probability = 1 }
                };
            }
            //если седловая точка не найдена, возвращаем ноль
            else
            {
                return null;
            }
        }

        /// <summary>
        /// поиск смешанных стратегий
        /// </summary>
        /// <param name="a">система</param>
        /// <param name="log">лог</param>
        /// <returns>коллекция стратегий для каждого игрока</returns>
        public static List<Strategy>[] FindStrategies
            (double[,] a, List<string> log)
        {

            //установка начальных параметров
            List<Strategy> player1 = new List<Strategy>();
            List<Strategy> player2 = new List<Strategy>();
            List<Strategy>[] ans = new List<Strategy>[]
            {
                player1,player2
            };

            int n = a.GetUpperBound(0) + 1;
            int m = a.GetUpperBound(1) + 1;

            List<string> left = new List<string>();
            List<string> top = new List<string>();

            log.Add("поиск смешанных стратегий для системы:");

            for (int i = 0; i < n; i++)
            {
                left.Add($"y{i}|u{i}");
            }
            for(int j = 0; j < m; j++)
            {
                top.Add($"-x{j}|v{j}");
            }
            
            n++;
            m++;

            //добавление колонки справа и строки снизу
            //для преобразования матричной игры в задачу ЛП
            double[,] sys = new double[n, m];
            for(int i = 0; i < n - 1; i++)
            {
                sys[i, m - 1] = 1;
                for(int j = 0; j < m - 1; j++)
                {
                    sys[n - 1, j] = -1;
                    sys[i, j] = a[i, j];
                }
            }
            sys[n - 1, m - 1] = 0;

            //находим решение
            Lab2.FindBasicSolution(sys, n, m, top, left, log);
            Lab2.FindOptimalSolution(sys, n, m, top, left, log);

            //находим цену игры
            double price = sys[n - 1, m - 1];

            //приводим систему в нормальный вид
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < m; j++)
                {
                    sys[i, j] /= price;
                }
            }

            //составляем списки стратегий
            for(int i = 0; i < n-1; i++)
            {
                if (left[i].StartsWith("x"))
                    player2.Add(new Strategy()
                    {
                        Number = int.Parse(left[i][1].ToString()),
                        Probability = sys[i, m - 1]
                    });
            }
            for (int j = 0; j < m - 1; j++)
            {
                if (top[j].StartsWith("y"))
                    player1.Add(new Strategy()
                    {
                        Number = int.Parse(top[j][1].ToString()),
                        Probability = sys[n - 1, j]
                    });
            }
            return ans;
        }

        /// <summary>
        /// проигрывание матричной игры
        /// </summary>
        /// <param name="sys">система</param>
        /// <param name="player1">стратегии игрока 1</param>
        /// <param name="player2">стратегии игрока 2</param>
        /// <param name="log">лог вычислений</param>
        public static void PlayGame(
            double[,] sys,
            List<Strategy> player1, 
            List<Strategy> player2, List<string> log)
        {
            //упорядочиваем стратегии в списках по их номерам
            player1.Sort((p1, p2) => p1.Number.CompareTo(p2.Number));
            player2.Sort((p1, p2) => p1.Number.CompareTo(p2.Number));
            Random f = new Random();

            //количество партий
            int playCount = 100;

            //начальное значение кумулятивного выиграша
            double cumC = 0;

            //заголовок таблицы
            log.Add("№\tR(A)\tS(A)\tR(B)\tS(B)\tC(A)\tCumC(A)\tAvC(A)");
            for (int i = 0; i < playCount; i++)
            {
                //номер партии
                string row = i + "\t";

                //случайное число игрока 1
                double rA = f.Next(0, 101) / 100.0;
                row += rA + "\t";

                //случайная стратегия игрока 2
                int sA = 0;
                foreach(Strategy s in player1)
                {
                    rA -= s.Probability;
                    if (rA <= 0)
                    {
                        sA = s.Number;
                        break;
                    }
                }
                row += sA + "\t";

                //игрок 2
                double rB = f.Next(0, 101) / 100.0;
                row += rB + "\t";

                int sB = 0;
                foreach(Strategy s in player2)
                {
                    rB -= s.Probability;
                    if (rB <= 0)
                    {
                        sB = s.Number;
                        break;
                    }
                }
                row += sB + "\t";
                
                //размер выиграша 1, проиграша 2
                double c = sys[sA, sB];
                row += c + "\t";

                //сумма на данный момент
                cumC += c;
                row += cumC + "\t";

                //средний выиграш
                double avC = cumC / (i + 1.0);
                row += avC.ToString("F2");

                //добавляем партию в таблицу
                log.Add(row);
            }
        }
    }
}
