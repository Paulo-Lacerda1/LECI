using NewModusApp.Data;
using System;
using System.Data;
using System.Data.SqlClient;

namespace NewModusApp.Repositories
{
    public class DashboardRepository
    {
        public decimal ObterFaturacaoMesAtual()
        {
            object result = Database.ExecuteScalar("NM.spObterFaturacaoMesAtual");
            if (result == null || result == DBNull.Value) return 0m;
            return Convert.ToDecimal(result);
        }

        public int ObterEncomendasAtivas()
        {
            object result = Database.ExecuteScalar("NM.spObterEncomendasAtivas");
            if (result == null || result == DBNull.Value) return 0;
            return Convert.ToInt32(result);
        }

        public int ObterTotalPecasStock()
        {
            object result = Database.ExecuteScalar("NM.spObterTotalPecasStock");
            if (result == null || result == DBNull.Value) return 0;
            return Convert.ToInt32(result);
        }

        public DataTable ObterAlertasStock()
        {
            return Database.ExecuteDataTable("NM.spObterAlertasStock");
        }

        public decimal ObterReceitaProntoVestir()
        {
            object result = Database.ExecuteScalar("NM.spObterReceitaProntoVestir");
            if (result == null || result == DBNull.Value) return 0m;
            return Convert.ToDecimal(result);
        }

        public decimal ObterReceitaPorMedida()
        {
            object result = Database.ExecuteScalar("NM.spObterReceitaPorMedida");
            if (result == null || result == DBNull.Value) return 0m;
            return Convert.ToDecimal(result);
        }

        public decimal ObterFaturacaoPorIntervalo(DateTime inicio, DateTime fim)
        {
            var parameters = new[]
            {
                new SqlParameter("@DataInicio", System.Data.SqlDbType.Date) { Value = inicio },
                new SqlParameter("@DataFim",    System.Data.SqlDbType.Date) { Value = fim }
            };
            object result = Database.ExecuteScalar("NM.spObterFaturacaoPorIntervalo", parameters);
            if (result == null || result == DBNull.Value) return 0m;
            return Convert.ToDecimal(result);
        }
    }
}
