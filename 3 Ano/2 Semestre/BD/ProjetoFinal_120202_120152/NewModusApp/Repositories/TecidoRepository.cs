using NewModusApp.Data;
using System.Data;
using System.Data.SqlClient;

namespace NewModusApp.Repositories
{
    public class TecidoRepository
    {
        public DataTable ObterTodos()
        {
            // A classe Database subjacente já espera APENAS o nome da string (e internamente assume CommandType.StoredProcedure)
            return Database.ExecuteDataTable("NM.spListarTecidos");
        }

        public DataTable ListarFornecedores()
        {
            return Database.ExecuteDataTable("NM.spListarFornecedores");
        }

        public DataTable ObterTecidoPorCodigo(int codigo)
        {
            return Database.ExecuteDataTable(
                "NM.spObterTecidoPorCodigo",
                new SqlParameter("@codigo", SqlDbType.Int) { Value = codigo }
            );
        }

        public void InserirTecido(string nome, decimal preco, decimal quantidade, string codigo, string cor, string tipo, string padrao, string fornecedor)
        {
            SqlParameter[] parametros = new SqlParameter[]
            {
                new SqlParameter("@nome", nome),
                new SqlParameter("@preco_metro", preco),
                new SqlParameter("@quantidade_stock", quantidade),
                new SqlParameter("@codigo", codigo),
                new SqlParameter("@cor", cor),
                new SqlParameter("@tipo", tipo),
                new SqlParameter("@padrao", padrao),
                new SqlParameter("@id_fornecedor", fornecedor)
            };

            Database.ExecuteNonQuery("NM.spInserirTecido", parametros);
        }

        public void AtualizarTecido(int id, string nome, decimal preco, decimal quantidade, string codigo, string cor, string tipo, string padrao, string fornecedor)
        {
            SqlParameter[] parametros = new SqlParameter[]
            {
                new SqlParameter("@id_tecido", id),
                new SqlParameter("@nome", nome),
                new SqlParameter("@preco_metro", preco),
                new SqlParameter("@quantidade_stock", quantidade),
                new SqlParameter("@codigo", codigo),
                new SqlParameter("@cor", cor),
                new SqlParameter("@tipo", tipo),
                new SqlParameter("@padrao", padrao),
                new SqlParameter("@id_fornecedor", fornecedor)
            };

            Database.ExecuteNonQuery("NM.spAtualizarTecido", parametros);
        }
    }
}
