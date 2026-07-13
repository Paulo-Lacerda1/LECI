namespace NewModusApp.Models
{
    using System.Collections.Generic;

    public class EncomendaItem
    {
        public EncomendaItem()
        {
            Tecidos = new List<ItemEncomendaTecido>();
            Materiais = new List<ItemEncomendaMaterial>();
        }

        public int IdItemEncomenda { get; set; }
        public int Tamanho { get; set; }
        public decimal Preco { get; set; }
        public string TipoPeca { get; set; }
        public decimal? CustoProducao { get; set; }
        public decimal CustoMaoObra { get; set; }
        public string DescricaoPersonalizacao { get; set; }
        public int PerfilMedida { get; set; }
        public int? Modelo { get; set; }
        public int Encomenda { get; set; }
        public List<ItemEncomendaTecido> Tecidos { get; private set; }
        public List<ItemEncomendaMaterial> Materiais { get; private set; }
    }
}
