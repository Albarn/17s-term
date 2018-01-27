using System;

namespace LabComponents
{
    /// <summary>
    /// аргументы события пересечения граничного значения
    /// </summary>
    public class TrackBarEventArgs : EventArgs
    {
        public int marginValue;
        public TrackBarEventArgs(int value)
        {
            marginValue = value;
        }
    }

    /// <summary>
    /// делегат события пересечения граничного значения
    /// </summary>
    public delegate void 
        TrackBarEventHandler(object sender, TrackBarEventArgs e);
}
