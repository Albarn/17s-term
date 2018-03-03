using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Globalization;

namespace RailwayTrips.Data
{
    /// <summary>
    /// ticket collection
    /// </summary>
    public class Tickets : List<Ticket>
    {
        public Tickets() { }
    }

    /// <summary>
    /// railway ticket
    /// </summary>
    public class Ticket
    {
        /// <summary>
        /// count of created objects(for primary key)
        /// </summary>
        private static int Count = 0;

        /// <summary>
        /// primary key
        /// </summary>
        [DisplayName("Номер билета")] public int TicketNumber { get; set; }

        /// <summary>
        /// part of foreign key
        /// </summary>
        [DisplayName("Дата поездки")] public DateTime TripDate { get; set; }

        /// <summary>
        /// part of foreign key
        /// </summary>
        [DisplayName("Номер поезда")] public int TrainNumber { get; set; }

        /// <summary>
        /// fk string contents trainNumber#tripDate
        /// </summary>
        [DisplayName("FK Check")]
        public string FK
        {
            get
            {
                return TrainNumber.ToString() + "#" + TripDate.ToString("dd.MM.yy");
            }
            set
            {
                //if we got exception, we dont set values
                string number = value.Substring(0, value.IndexOf('#'));
                string date = value.Substring(value.IndexOf('#') + 1);
                try
                {
                    //use additional vars for
                    //safe setting
                    //before exception appears
                    //in number/date parsing
                    //we dont set any of fields
                    int trainNumber;
                    DateTime tripDate;
                    trainNumber = int.Parse(number);
                    if (!DateTime.TryParseExact(date, "dd.MM.yy", null,
                                   DateTimeStyles.None, out tripDate))
                        throw new Exception();

                    TrainNumber = trainNumber;
                    TripDate = tripDate;
                }
                catch {
                    //we set both values or any
                    return;
                }
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

        double price;

        [DisplayName("Цена")] public double Price { get
            {
                return price;
            }
            set
            {
                if (value >= 0)
                    price = value;
            }
        }

        public Ticket()
        {
            //init pk with count
            TicketNumber = Count++;

            //other fields with default values
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
