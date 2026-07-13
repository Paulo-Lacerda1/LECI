using System;

namespace NewModusApp.Models
{
    public class CompraProntoVestir
    {
        public int IdCompra { get; set; }
        public DateTime DataCompra { get; set; }
        public decimal ValorTotal { get; set; }
        public string MetodoPagamento { get; set; }
        public int? Cliente { get; set; }
    }
}
