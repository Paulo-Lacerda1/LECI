using System;

namespace NewModusApp.Models
{
    public class Medida
    {
        public int IdPerfil { get; set; }
        public string NomePerfil { get; set; }
        public int Braco { get; set; }
        public int Costas { get; set; }
        public int Peito { get; set; }
        public int Cinta { get; set; }
        public int Anca { get; set; }
        public DateTime DataAtualizacao { get; set; }
        public int Cliente { get; set; }
    }
}
