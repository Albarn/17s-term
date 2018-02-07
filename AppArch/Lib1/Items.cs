using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib1
{
    public class Items : List<Item>
    {
        public Items()
        {

        }
    }

    public class Item
    {
        public int ID { get; set; }
        public string Info { get; set; }

        public Item()
        {

        }
    }
}
