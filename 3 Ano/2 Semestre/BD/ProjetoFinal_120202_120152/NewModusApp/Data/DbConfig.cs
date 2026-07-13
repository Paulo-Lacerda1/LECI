using System.Data.SqlClient;

namespace NewModusApp.Data
{
    public static class DbConfig
    {
        // Alterar aqui os dados da ligacao a base de dados.
        public static string Server = ".\\SQLSERVER";
        public static string DatabaseName = "NewModus";

        // true: autenticacao Windows. false: autenticacao SQL Server.
        public static bool UseWindowsAuthentication = true;
        public static string User = "";
        public static string Password = "";

        public static string ConnectionString
        {
            get
            {
                SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder
                {
                    DataSource = Server,
                    InitialCatalog = DatabaseName,
                    TrustServerCertificate = true
                };

                if (UseWindowsAuthentication)
                {
                    builder.IntegratedSecurity = true;
                }
                else
                {
                    builder.UserID = User;
                    builder.Password = Password;
                }

                return builder.ConnectionString;
            }
        }
    }
}
