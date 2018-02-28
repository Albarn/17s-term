﻿using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace RailwayTrips.Data
{
    public class Trips : List<Trip>
    {
        public Trips() { }
    }

    public class Trip
    {

        [DisplayName("Дата поездки")] public DateTime TripDate { get; set; }
        [DisplayName("Номер поезда")] public int TrainNumber { get; set; }
        [DisplayName("PK Check")] public string PK
        {
            get
            {
                return TrainNumber.ToString()+"#"+TripDate.ToString();
            }

        }
        [DisplayName("Число купейных мест")] public int CompartmentPlaces { get; set; }
        [DisplayName("Число плацкартных мест")] public int ReservedPlaces { get; set; }
        [DisplayName("Число общих мест")] public int CommonPlaces { get; set; }

        public Trip()
        {
            TripDate = DateTime.Now;
            CompartmentPlaces = ReservedPlaces = CommonPlaces = 0;
        }

        public override string ToString()
        {
            return string.Format("{0}, {1} купе {2}, плацкарт {3}, общие{4}",
                TrainNumber,
                TripDate.Day+"."+TripDate.Month+"."+TripDate.Year,
                CompartmentPlaces,
                ReservedPlaces,
                CommonPlaces
            );
        }
    }
}
