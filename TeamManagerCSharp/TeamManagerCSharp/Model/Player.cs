using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TeamManagerCSharp.Model
{
    /*
     * Játékos model egy játékos adatainak tárolására.
     */
    public class Player
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public string Position { get; set; }
        public int BirthYear { get; set; }
        public string TeamName { get; set; }
    }
}
