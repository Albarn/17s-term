using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace RailwayTrips.Data
{
    public class Tickets : List<Ticket>
    {
        public Tickets() { }
    }

    public class Ticket
    {
        private static int Count = 0;

        [DisplayName("Номер билета")] public int TicketNumber { get; set; }
        [DisplayName("Дата поездки")] public DateTime TripDate { get; set; }
        [DisplayName("Номер поезда")] public int TrainNumber { get; set; }
        [DisplayName("FK Check")]
        public string FK
        {
            get
            {
                return TrainNumber.ToString() + "#" + TripDate.ToString();
            }
            set
            {
                string trainNumber = value.Substring(0, value.IndexOf('#'));
                try
                {
                    TrainNumber = int.Parse(trainNumber);
                }
                catch { }

                string date = value.Substring(value.IndexOf('#') + 1);
                try
                {
                    TripDate = DateTime.Parse(date);
                }
                catch { }
            }
        }
        [DisplayName("Фамилия")] public string LastName { get; set; }
        [DisplayName("Отчество")] public string MiddleName { get; set; }
        [DisplayName("Имя")] public string FirstName { get; set; }
        string carriegeType;
        [DisplayName("Тип вагона")]
        public string CarriegeType
        {
            get
            {
                return carriegeType;
            }
            set
            {
                switch (value)
                {
                    case "К":
                    case "П":
                    case "О": carriegeType = value; break;
                }
            }
        }
        [DisplayName("Цена")] public double Price { get; set; }

        public Ticket()
        {
            TicketNumber = Count++;
            LastName = FirstName = MiddleName = "<Name>";
            CarriegeType = "К";
            Price = 1;
        }

        public override string ToString()
        {
            return string.Format("{0}/{1} {2}, {3} grn. {4} {5}.{6}.",
                TicketNumber,
                TrainNumber,
                TripDate.Day+"."+TripDate.Month+"."+TripDate.Year,
                Price,
                LastName,
                FirstName,
                MiddleName
            );
        }
    }
}
