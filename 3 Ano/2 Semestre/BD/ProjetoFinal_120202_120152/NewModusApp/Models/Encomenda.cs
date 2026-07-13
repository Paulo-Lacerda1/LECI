using System;

namespace NewModusApp.Models
{
    public class Encomenda
    {
        public int IdEncomenda { get; set; }
        public DateTime DataEncomenda { get; set; }
        public DateTime? DataPrevistaEntrega { get; set; }
        public string Estado { get; set; }
        public DateTime? DataPronto { get; set; }
        public DateTime? DataRealEntrega { get; set; }
        public decimal ValorTotal { get; set; }
        public int Cliente { get; set; }
    }
}
