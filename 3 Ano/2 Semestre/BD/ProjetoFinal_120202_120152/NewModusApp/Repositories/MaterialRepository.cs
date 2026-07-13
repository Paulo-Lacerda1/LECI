using NewModusApp.Data;
using System.Data;
using System.Data.SqlClient;

namespace NewModusApp.Repositories
{
    public class MaterialRepository
    {
        public DataTable ListarMateriais()
        {
            return Database.ExecuteDataTable("NM.spListarMateriais");
        }

        public DataTable ListarFornecedores()
        {
            return Database.ExecuteDataTable("NM.spListarFornecedores");
        }

        public DataTable ObterMaterialPorNomeETipo(string nome, string tipo)
        {
            return Database.ExecuteDataTable(
                "NM.spObterMaterialPorNomeETipo",
                new SqlParameter("@nome", SqlDbType.VarChar, 30) { Value = nome },
                new SqlParameter("@tipo", SqlDbType.VarChar, 15) { Value = tipo }
            );
        }

        public void InserirMaterial(string nome, decimal custoUnitario, int quantidadeStock, string unidadeMedida, string tipo, int fornecedor)
        {
            Database.ExecuteNonQuery(
                "NM.spInserirMaterial",
                new SqlParameter("@nome",             SqlDbType.VarChar, 30)  { Value = nome },
                new SqlParameter("@custo_unitario",   SqlDbType.Decimal)      { Precision = 6, Scale = 2, Value = custoUnitario },
                new SqlParameter("@quantidade_stock", SqlDbType.Int)          { Value = quantidadeStock },
                new SqlParameter("@unidade_medida",   SqlDbType.VarChar, 15)  { Value = unidadeMedida },
                new SqlParameter("@tipo",             SqlDbType.VarChar, 15)  { Value = tipo },
                new SqlParameter("@fornecedor",       SqlDbType.Int)          { Value = fornecedor }
            );
        }

        public void AtualizarMaterial(int idMaterial, string nome, decimal custoUnitario, int quantidadeStock, string unidadeMedida, string tipo, int fornecedor)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarMaterial",
                new SqlParameter("@id_material",      SqlDbType.Int)          { Value = idMaterial },
                new SqlParameter("@nome",             SqlDbType.VarChar, 30)  { Value = nome },
                new SqlParameter("@custo_unitario",   SqlDbType.Decimal)      { Precision = 6, Scale = 2, Value = custoUnitario },
                new SqlParameter("@quantidade_stock", SqlDbType.Int)          { Value = quantidadeStock },
                new SqlParameter("@unidade_medida",   SqlDbType.VarChar, 15)  { Value = unidadeMedida },
                new SqlParameter("@tipo",             SqlDbType.VarChar, 15)  { Value = tipo },
                new SqlParameter("@fornecedor",       SqlDbType.Int)          { Value = fornecedor }
            );
        }
    }
}
