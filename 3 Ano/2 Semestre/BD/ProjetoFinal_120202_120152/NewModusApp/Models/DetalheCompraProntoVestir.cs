namespace NewModusApp.Models
{
    public class DetalheCompraProntoVestir
    {
        public int IdDetalhes { get; set; }
        public int Quantidade { get; set; }
        public decimal PrecoUnitario { get; set; }
        public int Compra { get; set; }
        public int ProdutoPronto { get; set; }
        public string ProdutoNome { get; set; }
        public string Tamanho { get; set; }
        public string Cor { get; set; }
    }
}
