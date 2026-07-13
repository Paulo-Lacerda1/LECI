using NewModusApp.Data;
using NewModusApp.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace NewModusApp.Repositories
{
    public class ProntoVestirRepository
    {
        public DataTable ListarClientes()
        {
            return Database.ExecuteDataTable("NM.spListarClientes");
        }

        public DataTable ListarProdutos()
        {
            return Database.ExecuteDataTable("NM.spListarProdutosProntosCombo");
        }

        public DataTable ListarProdutosProntos()
        {
            return Database.ExecuteDataTable("NM.spListarProdutosProntos");
        }

        public DataTable ListarProdutosDetalhados()
        {
            return Database.ExecuteDataTable("NM.spListarProdutosDetalhados");
        }

        public DataTable Pesquisar(int? cliente, string metodoPagamento, DateTime? dataInicio, DateTime? dataFim)
        {
            return Database.ExecuteDataTable(
                "NM.spPesquisarComprasProntoVestir",
                new SqlParameter("@cliente", SqlDbType.Int) { Value = cliente.HasValue ? (object)cliente.Value : null },
                new SqlParameter("@metodo_pagamento", SqlDbType.NVarChar, 20) { Value = string.IsNullOrWhiteSpace(metodoPagamento) ? null : metodoPagamento },
                new SqlParameter("@dataInicio", SqlDbType.Date) { Value = dataInicio.HasValue ? (object)dataInicio.Value.Date : null },
                new SqlParameter("@dataFim", SqlDbType.Date) { Value = dataFim.HasValue ? (object)dataFim.Value.Date : null }
            );
        }

        public DataRow ObterResumoEliminacao(int idCompra)
        {
            DataTable tabela = Database.ExecuteDataTable(
                "NM.spObterResumoEliminacaoCompraProntoVestir",
                new SqlParameter("@id_compra", SqlDbType.Int) { Value = idCompra }
            );

            return tabela.Rows.Count > 0 ? tabela.Rows[0] : null;
        }

        public int Criar(CompraProntoVestir compra)
        {
            SqlParameter idCompra = new SqlParameter("@id_compra", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };

            Database.ExecuteNonQuery(
                "NM.spCriarCompraProntoVestir",
                new SqlParameter("@data_compra", SqlDbType.Date) { Value = compra.DataCompra.Date },
                new SqlParameter("@metodo_pagamento", SqlDbType.NVarChar, 20) { Value = compra.MetodoPagamento },
                new SqlParameter("@cliente", SqlDbType.Int) { Value = compra.Cliente.HasValue ? (object)compra.Cliente.Value : null },
                idCompra
            );

            return Convert.ToInt32(idCompra.Value);
        }

        public int CriarComDetalhes(CompraProntoVestir compra, IList<DetalheCompraProntoVestir> detalhes)
        {
            if (detalhes == null || detalhes.Count == 0)
                throw new InvalidOperationException("Adiciona pelo menos um produto a compra.");

            using (SqlConnection conn = Database.GetConnection())
            {
                conn.Open();

                using (SqlTransaction transaction = conn.BeginTransaction())
                {
                    try
                    {
                        int idCompra = CriarCompraTransacao(conn, transaction, compra);

                        foreach (DetalheCompraProntoVestir detalhe in detalhes)
                        {
                            detalhe.Compra = idCompra;
                            CriarDetalheTransacao(conn, transaction, detalhe);
                        }

                        transaction.Commit();
                        return idCompra;
                    }
                    catch
                    {
                        try
                        {
                            transaction.Rollback();
                        }
                        catch (InvalidOperationException)
                        {
                        }

                        throw;
                    }
                }
            }
        }

        private int CriarCompraTransacao(SqlConnection conn, SqlTransaction transaction, CompraProntoVestir compra)
        {
            using (SqlCommand cmd = new SqlCommand("NM.spCriarCompraProntoVestir", conn, transaction))
            {
                cmd.CommandType = CommandType.StoredProcedure;

                SqlParameter idCompra = new SqlParameter("@id_compra", SqlDbType.Int)
                {
                    Direction = ParameterDirection.Output
                };

                cmd.Parameters.Add(new SqlParameter("@data_compra", SqlDbType.Date) { Value = compra.DataCompra.Date });
                cmd.Parameters.Add(new SqlParameter("@metodo_pagamento", SqlDbType.NVarChar, 20) { Value = compra.MetodoPagamento });
                cmd.Parameters.Add(new SqlParameter("@cliente", SqlDbType.Int) { Value = compra.Cliente.HasValue ? (object)compra.Cliente.Value : DBNull.Value });
                cmd.Parameters.Add(idCompra);

                cmd.ExecuteNonQuery();
                return Convert.ToInt32(idCompra.Value);
            }
        }

        private void CriarDetalheTransacao(SqlConnection conn, SqlTransaction transaction, DetalheCompraProntoVestir detalhe)
        {
            using (SqlCommand cmd = new SqlCommand("NM.spCriarDetalheCompraProntoVestir", conn, transaction))
            {
                cmd.CommandType = CommandType.StoredProcedure;

                SqlParameter idDetalhe = new SqlParameter("@id_detalhes", SqlDbType.Int)
                {
                    Direction = ParameterDirection.Output
                };

                cmd.Parameters.Add(new SqlParameter("@quantidade", SqlDbType.Int) { Value = detalhe.Quantidade });
                cmd.Parameters.Add(new SqlParameter("@preco_unitario", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = detalhe.PrecoUnitario });
                cmd.Parameters.Add(new SqlParameter("@compra", SqlDbType.Int) { Value = detalhe.Compra });
                cmd.Parameters.Add(new SqlParameter("@produto_pronto", SqlDbType.Int) { Value = detalhe.ProdutoPronto });
                cmd.Parameters.Add(idDetalhe);

                cmd.ExecuteNonQuery();
            }
        }

        public void Atualizar(CompraProntoVestir compra)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarCompraProntoVestir",
                new SqlParameter("@id_compra", SqlDbType.Int) { Value = compra.IdCompra },
                new SqlParameter("@data_compra", SqlDbType.Date) { Value = compra.DataCompra.Date },
                new SqlParameter("@metodo_pagamento", SqlDbType.NVarChar, 20) { Value = compra.MetodoPagamento },
                new SqlParameter("@cliente", SqlDbType.Int) { Value = compra.Cliente.HasValue ? (object)compra.Cliente.Value : null }
            );
        }

        public void EliminarCompleta(int idCompra)
        {
            Database.ExecuteNonQuery(
                "NM.spEliminarCompraProntoVestirCompleta",
                new SqlParameter("@id_compra", SqlDbType.Int) { Value = idCompra }
            );
        }

        public DataTable ListarDetalhes(int idCompra)
        {
            return Database.ExecuteDataTable(
                "NM.spListarDetalhesCompraProntoVestir",
                new SqlParameter("@id_compra", SqlDbType.Int) { Value = idCompra }
            );
        }

        public int CriarDetalhe(DetalheCompraProntoVestir detalhe)
        {
            SqlParameter idDetalhe = new SqlParameter("@id_detalhes", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };

            Database.ExecuteNonQuery(
                "NM.spCriarDetalheCompraProntoVestir",
                new SqlParameter("@quantidade", SqlDbType.Int) { Value = detalhe.Quantidade },
                new SqlParameter("@preco_unitario", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = detalhe.PrecoUnitario },
                new SqlParameter("@compra", SqlDbType.Int) { Value = detalhe.Compra },
                new SqlParameter("@produto_pronto", SqlDbType.Int) { Value = detalhe.ProdutoPronto },
                idDetalhe
            );

            return Convert.ToInt32(idDetalhe.Value);
        }

        public void AtualizarDetalhe(DetalheCompraProntoVestir detalhe)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarDetalheCompraProntoVestir",
                new SqlParameter("@id_detalhes", SqlDbType.Int) { Value = detalhe.IdDetalhes },
                new SqlParameter("@quantidade", SqlDbType.Int) { Value = detalhe.Quantidade },
                new SqlParameter("@preco_unitario", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = detalhe.PrecoUnitario },
                new SqlParameter("@produto_pronto", SqlDbType.Int) { Value = detalhe.ProdutoPronto }
            );
        }

        public void AtualizarDetalheCompleto(DetalheCompraProntoVestir detalhe)
        {
            using (SqlConnection conn = Database.GetConnection())
            {
                conn.Open();

                using (SqlTransaction transaction = conn.BeginTransaction())
                {
                    try
                    {
                        using (SqlCommand cmd = new SqlCommand("NM.spAtualizarDetalheCompraProntoVestir", conn, transaction))
                        {
                            cmd.CommandType = CommandType.StoredProcedure;
                            cmd.Parameters.Add(new SqlParameter("@id_detalhes", SqlDbType.Int) { Value = detalhe.IdDetalhes });
                            cmd.Parameters.Add(new SqlParameter("@quantidade", SqlDbType.Int) { Value = detalhe.Quantidade });
                            cmd.Parameters.Add(new SqlParameter("@preco_unitario", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = detalhe.PrecoUnitario });
                            cmd.Parameters.Add(new SqlParameter("@produto_pronto", SqlDbType.Int) { Value = detalhe.ProdutoPronto });
                            cmd.ExecuteNonQuery();
                        }

                        using (SqlCommand cmd = new SqlCommand("NM.spAtualizarProdutoProntoBasico", conn, transaction))
                        {
                            cmd.CommandType = CommandType.StoredProcedure;
                            cmd.Parameters.Add(new SqlParameter("@produto", SqlDbType.Int) { Value = detalhe.ProdutoPronto });
                            cmd.Parameters.Add(new SqlParameter("@nome", SqlDbType.VarChar, 30) { Value = detalhe.ProdutoNome });
                            cmd.Parameters.Add(new SqlParameter("@tamanho", SqlDbType.VarChar, 10) { Value = detalhe.Tamanho });
                            cmd.Parameters.Add(new SqlParameter("@cor", SqlDbType.VarChar, 10) { Value = detalhe.Cor });
                            cmd.ExecuteNonQuery();
                        }

                        transaction.Commit();
                    }
                    catch
                    {
                        try
                        {
                            transaction.Rollback();
                        }
                        catch (InvalidOperationException)
                        {
                        }

                        throw;
                    }
                }
            }
        }

        public void EliminarDetalhe(int idDetalhe)
        {
            Database.ExecuteNonQuery(
                "NM.spEliminarDetalheCompraProntoVestir",
                new SqlParameter("@id_detalhes", SqlDbType.Int) { Value = idDetalhe }
            );
        }

        public DataTable ListarCategorias()
        {
            return Database.ExecuteDataTable("NM.spListarCategoriasProduto");
        }

        public DataTable ObterProdutoPorCodigo(int codigo)
        {
            return Database.ExecuteDataTable(
                "NM.spObterProdutoPorCodigo",
                new SqlParameter("@codigo", SqlDbType.Int) { Value = codigo }
            );
        }

        public string VerificarBloqueiosEliminacaoProduto(int id)
        {
            DataTable dt = Database.ExecuteDataTable(
                "NM.spVerificarBloqueiosProduto",
                new SqlParameter("@id", SqlDbType.Int) { Value = id }
            );
            int vendas = Convert.ToInt32(dt.Rows[0]["Vendas"]);
            if (vendas > 0)
                return $"Este produto não pode ser eliminado porque tem {vendas} registo(s) de venda associado(s).";
            return null;
        }

        public void EliminarProduto(int id)
        {
            Database.ExecuteNonQuery(
                "NM.spEliminarProduto",
                new SqlParameter("@id", SqlDbType.Int) { Value = id }
            );
        }

        public void InserirProduto(int codigo, string nome, string tamanho, string cor, decimal preco, int stock, int idCategoria)
        {
            Database.ExecuteNonQuery(
                "NM.spInserirProduto",
                new SqlParameter("@codigo",      SqlDbType.Int)              { Value = codigo },
                new SqlParameter("@nome",        SqlDbType.VarChar, 30)      { Value = nome },
                new SqlParameter("@tamanho",     SqlDbType.VarChar, 10)      { Value = tamanho },
                new SqlParameter("@cor",         SqlDbType.VarChar, 10)      { Value = cor },
                new SqlParameter("@preco",       SqlDbType.Decimal)          { Precision = 6, Scale = 2, Value = preco },
                new SqlParameter("@stock",       SqlDbType.Int)              { Value = stock },
                new SqlParameter("@idCategoria", SqlDbType.Int)              { Value = idCategoria }
            );
        }

        public void AtualizarProduto(int id, int codigo, string nome, string tamanho, string cor, decimal preco, int stock, int idCategoria)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarProduto",
                new SqlParameter("@id",          SqlDbType.Int)              { Value = id },
                new SqlParameter("@codigo",      SqlDbType.Int)              { Value = codigo },
                new SqlParameter("@nome",        SqlDbType.VarChar, 30)      { Value = nome },
                new SqlParameter("@tamanho",     SqlDbType.VarChar, 10)      { Value = tamanho },
                new SqlParameter("@cor",         SqlDbType.VarChar, 10)      { Value = cor },
                new SqlParameter("@preco",       SqlDbType.Decimal)          { Precision = 6, Scale = 2, Value = preco },
                new SqlParameter("@stock",       SqlDbType.Int)              { Value = stock },
                new SqlParameter("@idCategoria", SqlDbType.Int)              { Value = idCategoria }
            );
        }
    }
}
