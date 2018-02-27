using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace RailwayTrips.Data
{
    public class Trains : List<Train>
    {
        public Trains(){ }
    }

    public class Train
    {
        private static int Count = 0;

        [DisplayName("Номер поезда")] public int TrainNumber { get; set; }
        [DisplayName("Место отправления")] public string DeparturePlace { get; set; }
        [DisplayName("Место следования")] public string ArrivalPlace { get; set; }
        [DisplayName("Время отправления")] public DateTime DepartureTime { get; set; }
        [DisplayName("Время прибытия")] public DateTime ArrivalTime { get; set; }

        public Train()
        {
            TrainNumber = Count++;
            DeparturePlace = ArrivalPlace = "<Place>";
            DepartureTime = ArrivalTime = DateTime.Now;
        }

        public override string ToString()
        {
            return string.Format("{0}, {1}-{2}, {3}-{4}",
                TrainNumber,
                DeparturePlace,
                ArrivalPlace,
                DepartureTime.Hour+":"+DepartureTime.Minute,
                ArrivalTime.Hour + ":" + ArrivalTime.Minute
            );
        }
    }
}
