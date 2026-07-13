using NewModusApp.Data;
using NewModusApp.Models;
using System;
using System.Data;
using System.Data.SqlClient;

namespace NewModusApp.Repositories
{
    public class MedidasRepository
    {
        public DataTable ListarClientes()
        {
            return Database.ExecuteDataTable("NM.spListarClientes");
        }

        public DataTable Pesquisar(int? cliente, DateTime? dataInicio, DateTime? dataFim)
        {
            return Database.ExecuteDataTable(
                "NM.spPesquisarPerfisMedida",
                new SqlParameter("@cliente", SqlDbType.Int) { Value = cliente.HasValue ? (object)cliente.Value : null },
                new SqlParameter("@dataInicio", SqlDbType.Date) { Value = dataInicio.HasValue ? (object)dataInicio.Value.Date : null },
                new SqlParameter("@dataFim", SqlDbType.Date) { Value = dataFim.HasValue ? (object)dataFim.Value.Date : null }
            );
        }

        public int Criar(Medida medida)
        {
            SqlParameter idPerfil = new SqlParameter("@id_perfil", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };

            Database.ExecuteNonQuery(
                "NM.spCriarPerfilMedida",
                new SqlParameter("@nome_perfil", SqlDbType.NVarChar, 40) { Value = medida.NomePerfil },
                new SqlParameter("@braco", SqlDbType.Int) { Value = medida.Braco },
                new SqlParameter("@costas", SqlDbType.Int) { Value = medida.Costas },
                new SqlParameter("@peito", SqlDbType.Int) { Value = medida.Peito },
                new SqlParameter("@cinta", SqlDbType.Int) { Value = medida.Cinta },
                new SqlParameter("@anca", SqlDbType.Int) { Value = medida.Anca },
                new SqlParameter("@data_atualizacao", SqlDbType.Date) { Value = medida.DataAtualizacao.Date },
                new SqlParameter("@cliente", SqlDbType.Int) { Value = medida.Cliente },
                idPerfil
            );

            return Convert.ToInt32(idPerfil.Value);
        }

        public void Atualizar(Medida medida)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarPerfilMedida",
                new SqlParameter("@id_perfil", SqlDbType.Int) { Value = medida.IdPerfil },
                new SqlParameter("@nome_perfil", SqlDbType.NVarChar, 40) { Value = medida.NomePerfil },
                new SqlParameter("@braco", SqlDbType.Int) { Value = medida.Braco },
                new SqlParameter("@costas", SqlDbType.Int) { Value = medida.Costas },
                new SqlParameter("@peito", SqlDbType.Int) { Value = medida.Peito },
                new SqlParameter("@cinta", SqlDbType.Int) { Value = medida.Cinta },
                new SqlParameter("@anca", SqlDbType.Int) { Value = medida.Anca },
                new SqlParameter("@data_atualizacao", SqlDbType.Date) { Value = medida.DataAtualizacao.Date },
                new SqlParameter("@cliente", SqlDbType.Int) { Value = medida.Cliente }
            );
        }

        public void Eliminar(int idPerfil)
        {
            Database.ExecuteNonQuery(
                "NM.spEliminarPerfilMedida",
                new SqlParameter("@id_perfil", SqlDbType.Int) { Value = idPerfil }
            );
        }
    }
}
