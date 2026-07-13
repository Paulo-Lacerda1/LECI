using System;
using System.Data;
using System.Data.SqlClient;
using System.Windows.Forms;

namespace NewModusApp.Data
{
    public static class Database
    {
        public static SqlConnection GetConnection()
        {
            return new SqlConnection(DbConfig.ConnectionString);
        }

        public static bool TestarLigacao()
        {
            try
            {
                using (SqlConnection conn = GetConnection())
                {
                    conn.Open();
                    return true;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(
                    "Erro ao ligar à base de dados:\n\n" + ex.Message,
                    "Erro de ligação",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error
                );

                return false;
            }
        }

        public static DataTable ExecuteDataTable(string storedProcedure, params SqlParameter[] parameters)
        {
            using (SqlConnection conn = GetConnection())
            using (SqlCommand cmd = CreateStoredProcedureCommand(storedProcedure, conn, parameters))
            using (SqlDataAdapter adapter = new SqlDataAdapter(cmd))
            {
                DataTable tabela = new DataTable();
                adapter.Fill(tabela);
                return tabela;
            }
        }

        public static int ExecuteNonQuery(string storedProcedure, params SqlParameter[] parameters)
        {
            using (SqlConnection conn = GetConnection())
            using (SqlCommand cmd = CreateStoredProcedureCommand(storedProcedure, conn, parameters))
            {
                conn.Open();
                return cmd.ExecuteNonQuery();
            }
        }

        public static object ExecuteScalar(string storedProcedure, params SqlParameter[] parameters)
        {
            using (SqlConnection conn = GetConnection())
            using (SqlCommand cmd = CreateStoredProcedureCommand(storedProcedure, conn, parameters))
            {
                conn.Open();
                return cmd.ExecuteScalar();
            }
        }

        private static SqlCommand CreateStoredProcedureCommand(string storedProcedure, SqlConnection conn, SqlParameter[] parameters)
        {
            SqlCommand cmd = new SqlCommand(storedProcedure, conn)
            {
                CommandType = CommandType.StoredProcedure
            };

            if (parameters == null)
                return cmd;

            foreach (SqlParameter parameter in parameters)
            {
                if ((parameter.Direction == ParameterDirection.Input || parameter.Direction == ParameterDirection.InputOutput)
                    && parameter.Value == null)
                {
                    parameter.Value = DBNull.Value;
                }

                cmd.Parameters.Add(parameter);
            }

            return cmd;
        }
    }
}
