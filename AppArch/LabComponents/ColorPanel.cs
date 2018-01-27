using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace LabComponents
{

    /// <summary>
    /// цветная панель
    /// </summary>
    [ToolboxBitmap(@"G:\repos\17s-term\AppArch\LabComponents\ColorPanel.bmp")]
    public partial class ColorPanel : UserControl
    {
        public ColorPanel()
        {
            InitializeComponent();

            //присоединение обработчика к 
            //событию пересечения граничного значения
            MarginCrossed += ColorPanel_MarginCrossed;
        }

        /// <summary>
        /// пересечение граничного значения
        /// </summary>
        private void ColorPanel_MarginCrossed(object sender, TrackBarEventArgs e)
        {
            //номер цвета
            int color = e.marginValue;

            //если ползунок не превысил никакое из значений(-1)
            //или в редакторе неправильно задана коллекция, то
            //смещаем цвет в пределы массива
            if (color < 0) color = 0;
            if (color >= colors.Length) color = colors.Length - 1;

            //устанавливаем цвет
            panel1.BackColor = colors[color];
        }

        /// <summary>
        /// граничные значения trackBar
        /// </summary>
        [EditorBrowsable(EditorBrowsableState.Always)]
        [Category("Misc")]
        public int[] marginValues { get; set; }

        /// <summary>
        /// цвета Panel
        /// </summary>
        [EditorBrowsable(EditorBrowsableState.Always)]
        [Category("Misc")]
        public Color[] colors { get; set; }

        /// <summary>
        /// максимальное значение на TrackBar
        /// </summary>
        [EditorBrowsable(EditorBrowsableState.Always)]
        [Category("Misc")]
        public int Max
        {
            get
            {
                return trackBar1.Maximum;
            }
            set
            {
                trackBar1.Maximum = value;
            }
        }

        /// <summary>
        /// минимальное значение на TrackBar
        /// </summary>
        [EditorBrowsable(EditorBrowsableState.Always)]
        [Category("Misc")]
        public int Min
        {
            get
            {
                return trackBar1.Minimum;
            }
            set
            {
                trackBar1.Minimum = value;
            }
        }

        /// <summary>
        /// старое значение, которое превысил ползунок
        /// </summary>
        int oldColor = -1;

        /// <summary>
        /// пересечение граничного значения
        /// </summary>
        public event TrackBarEventHandler MarginCrossed;

        /// <summary>
        /// перемещение ползунка
        /// </summary>
        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            //если граничных значений нет
            //ничего не делаем
            if (marginValues == null) return;

            //по умолчанию ползунок не превысил
            //никакое из значаний
            int newColor = -1;

            //поиск максимального значения,
            //которое превысил ползунок
            for (int i = 0; i < marginValues.Length; i++)
                if (trackBar1.Value >= marginValues[i])
                    if (newColor != -1)
                    {
                        if (marginValues[newColor] < marginValues[i])
                            newColor = i;
                    }
                    else
                        newColor = i;

            //если данное значение на равно старому,
            //вызываем событие
            if (newColor != oldColor)
                MarginCrossed(sender,
                    new TrackBarEventArgs(newColor));

            //устанавливаем новый цвет как старый
            oldColor = newColor;
        }
    }
}
