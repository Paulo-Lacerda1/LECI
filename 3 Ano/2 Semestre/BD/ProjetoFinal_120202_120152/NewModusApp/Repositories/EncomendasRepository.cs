using NewModusApp.Data;
using NewModusApp.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace NewModusApp.Repositories
{
    public class EncomendasRepository
    {
        public DataTable ListarClientes()
        {
            return Database.ExecuteDataTable("NM.spListarClientes");
        }

        public DataTable ListarModelos()
        {
            return Database.ExecuteDataTable("NM.spListarModelosEncomenda");
        }

        public DataTable ListarPerfisMedida(int? cliente)
        {
            return Database.ExecuteDataTable(
                "NM.spListarPerfisMedidaCombo",
                new SqlParameter("@cliente", SqlDbType.Int) { Value = cliente.HasValue ? (object)cliente.Value : null }
            );
        }

        public DataTable ListarTecidosUso()
        {
            return Database.ExecuteDataTable("NM.spListarTecidosParaEncomenda");
        }

        public DataTable ListarMateriaisUso()
        {
            return Database.ExecuteDataTable("NM.spListarMateriaisParaEncomenda");
        }

        public decimal ObterPrecoAtualTecido(int idTecido)
        {
            using (SqlConnection conn = Database.GetConnection())
            {
                conn.Open();
                return ObterPrecoAtualTecido(conn, null, idTecido);
            }
        }

        public decimal ObterPrecoAtualMaterial(int idMaterial)
        {
            using (SqlConnection conn = Database.GetConnection())
            {
                conn.Open();
                return ObterPrecoAtualMaterial(conn, null, idMaterial);
            }
        }

        public DataTable Pesquisar(int? cliente, string estado, DateTime? dataInicio, DateTime? dataFim)
        {
            return Database.ExecuteDataTable(
                "NM.spPesquisarEncomendas",
                new SqlParameter("@cliente", SqlDbType.Int) { Value = cliente.HasValue ? (object)cliente.Value : null },
                new SqlParameter("@estado", SqlDbType.NVarChar, 15) { Value = string.IsNullOrWhiteSpace(estado) ? null : estado },
                new SqlParameter("@dataInicio", SqlDbType.Date) { Value = dataInicio.HasValue ? (object)dataInicio.Value.Date : null },
                new SqlParameter("@dataFim", SqlDbType.Date) { Value = dataFim.HasValue ? (object)dataFim.Value.Date : null }
            );
        }

        public DataRow ObterResumoEliminacao(int idEncomenda)
        {
            DataTable tabela = Database.ExecuteDataTable(
                "NM.spObterResumoEliminacaoEncomenda",
                new SqlParameter("@id_encomenda", SqlDbType.Int) { Value = idEncomenda }
            );

            return tabela.Rows.Count > 0 ? tabela.Rows[0] : null;
        }

        public DataTable ListarPedidosPendentes(int? cliente)
        {
            return Database.ExecuteDataTable(
                "NM.spListarPedidosPendentes",
                new SqlParameter("@cliente", SqlDbType.Int) { Value = cliente.HasValue ? (object)cliente.Value : DBNull.Value }
            );
        }

        public DataTable ListarHistorico(int? cliente)
        {
            return Database.ExecuteDataTable(
                "NM.spListarHistoricoEncomendas",
                new SqlParameter("@cliente", SqlDbType.Int) { Value = cliente.HasValue ? (object)cliente.Value : DBNull.Value }
            );
        }

        public void MarcarComoPronta(int idEncomenda)
        {
            AtualizarEstado(idEncomenda, "Pronta", true, false);
        }

        public void MarcarComoEntregue(int idEncomenda)
        {
            AtualizarEstado(idEncomenda, "Entregue", true, true);
        }

        public void MarcarComoEmProducao(int idEncomenda)
        {
            AtualizarEstado(idEncomenda, "Em Produção", false, false);
        }

        public void MarcarComoCancelada(int idEncomenda)
        {
            AtualizarEstado(idEncomenda, "Cancelada", false, false);
        }

        private void AtualizarEstado(int idEncomenda, string estado, bool definirDataPronto, bool definirDataEntrega)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarEstadoEncomenda",
                new SqlParameter("@id_encomenda",       SqlDbType.Int)     { Value = idEncomenda },
                new SqlParameter("@estado",             SqlDbType.NVarChar, 15) { Value = estado },
                new SqlParameter("@definirDataPronto",  SqlDbType.Bit)     { Value = definirDataPronto },
                new SqlParameter("@definirDataEntrega", SqlDbType.Bit)     { Value = definirDataEntrega }
            );
        }

        public int Criar(Encomenda encomenda)
        {
            SqlParameter idEncomenda = new SqlParameter("@id_encomenda", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };

            Database.ExecuteNonQuery(
                "NM.spCriarEncomenda",
                new SqlParameter("@data_encomenda", SqlDbType.Date) { Value = encomenda.DataEncomenda.Date },
                new SqlParameter("@data_prevista_entrega", SqlDbType.Date) { Value = encomenda.DataPrevistaEntrega.HasValue ? (object)encomenda.DataPrevistaEntrega.Value.Date : null },
                new SqlParameter("@estado", SqlDbType.NVarChar, 15) { Value = encomenda.Estado },
                new SqlParameter("@data_pronto", SqlDbType.Date) { Value = encomenda.DataPronto.HasValue ? (object)encomenda.DataPronto.Value.Date : null },
                new SqlParameter("@data_real_entrega", SqlDbType.Date) { Value = encomenda.DataRealEntrega.HasValue ? (object)encomenda.DataRealEntrega.Value.Date : null },
                new SqlParameter("@valor_total", SqlDbType.Decimal) { Precision = 10, Scale = 2, Value = encomenda.ValorTotal },
                new SqlParameter("@cliente", SqlDbType.Int) { Value = encomenda.Cliente },
                idEncomenda
            );

            return Convert.ToInt32(idEncomenda.Value);
        }

        public int CriarComItens(Encomenda encomenda, IList<EncomendaItem> itens)
        {
            if (itens == null || itens.Count == 0)
                throw new InvalidOperationException("Adiciona pelo menos um produto ao pedido.");

            using (SqlConnection conn = Database.GetConnection())
            {
                conn.Open();

                using (SqlTransaction transaction = conn.BeginTransaction())
                {
                    try
                    {
                        int idEncomenda = CriarEncomendaTransacao(conn, transaction, encomenda);

                        foreach (EncomendaItem item in itens)
                        {
                            item.Encomenda = idEncomenda;
                            int idItem = CriarItemTransacao(conn, transaction, item);
                            GuardarTecidosItem(conn, transaction, idItem, item.Tecidos);
                            GuardarMateriaisItem(conn, transaction, idItem, item.Materiais);
                        }

                        transaction.Commit();
                        return idEncomenda;
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

        private int CriarEncomendaTransacao(SqlConnection conn, SqlTransaction transaction, Encomenda encomenda)
        {
            using (SqlCommand cmd = new SqlCommand("NM.spCriarEncomenda", conn, transaction))
            {
                cmd.CommandType = CommandType.StoredProcedure;

                SqlParameter idEncomenda = new SqlParameter("@id_encomenda", SqlDbType.Int)
                {
                    Direction = ParameterDirection.Output
                };

                cmd.Parameters.Add(new SqlParameter("@data_encomenda", SqlDbType.Date) { Value = encomenda.DataEncomenda.Date });
                cmd.Parameters.Add(new SqlParameter("@data_prevista_entrega", SqlDbType.Date) { Value = ObterValorDb(encomenda.DataPrevistaEntrega) });
                cmd.Parameters.Add(new SqlParameter("@estado", SqlDbType.NVarChar, 15) { Value = encomenda.Estado });
                cmd.Parameters.Add(new SqlParameter("@data_pronto", SqlDbType.Date) { Value = ObterValorDb(encomenda.DataPronto) });
                cmd.Parameters.Add(new SqlParameter("@data_real_entrega", SqlDbType.Date) { Value = ObterValorDb(encomenda.DataRealEntrega) });
                cmd.Parameters.Add(new SqlParameter("@valor_total", SqlDbType.Decimal) { Precision = 10, Scale = 2, Value = encomenda.ValorTotal });
                cmd.Parameters.Add(new SqlParameter("@cliente", SqlDbType.Int) { Value = encomenda.Cliente });
                cmd.Parameters.Add(idEncomenda);

                cmd.ExecuteNonQuery();
                return Convert.ToInt32(idEncomenda.Value);
            }
        }

        private int CriarItemTransacao(SqlConnection conn, SqlTransaction transaction, EncomendaItem item)
        {
            using (SqlCommand cmd = new SqlCommand("NM.spCriarItemEncomenda", conn, transaction))
            {
                cmd.CommandType = CommandType.StoredProcedure;

                SqlParameter idItem = new SqlParameter("@id_item_encomenda", SqlDbType.Int)
                {
                    Direction = ParameterDirection.Output
                };

                cmd.Parameters.Add(new SqlParameter("@tamanho", SqlDbType.Int) { Value = item.Tamanho });
                cmd.Parameters.Add(new SqlParameter("@preco", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = item.Preco });
                cmd.Parameters.Add(new SqlParameter("@custo_mao_obra", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = item.CustoMaoObra });
                cmd.Parameters.Add(new SqlParameter("@tipo_peca", SqlDbType.NVarChar, 15) { Value = item.TipoPeca });
                cmd.Parameters.Add(new SqlParameter("@descricao_personalizacao", SqlDbType.NVarChar, 50) { Value = ObterTextoDb(item.DescricaoPersonalizacao) });
                cmd.Parameters.Add(new SqlParameter("@perfil_medida", SqlDbType.Int) { Value = item.PerfilMedida });
                cmd.Parameters.Add(new SqlParameter("@modelo", SqlDbType.Int) { Value = item.Modelo.HasValue ? (object)item.Modelo.Value : DBNull.Value });
                cmd.Parameters.Add(new SqlParameter("@encomenda", SqlDbType.Int) { Value = item.Encomenda });
                cmd.Parameters.Add(idItem);

                cmd.ExecuteNonQuery();
                return Convert.ToInt32(idItem.Value);
            }
        }

        private void GuardarTecidosItem(SqlConnection conn, SqlTransaction transaction, int idItem, IEnumerable<ItemEncomendaTecido> tecidos)
        {
            if (tecidos == null)
                return;

            foreach (ItemEncomendaTecido tecido in tecidos)
            {
                decimal precoCobrado = tecido.PrecoCobrado.HasValue
                    ? tecido.PrecoCobrado.Value
                    : ObterPrecoAtualTecido(conn, transaction, tecido.Tecido);

                using (SqlCommand cmd = new SqlCommand("NM.spGuardarItemEncTecido", conn, transaction))
                {
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add(new SqlParameter("@tecido", SqlDbType.Int) { Value = tecido.Tecido });
                    cmd.Parameters.Add(new SqlParameter("@item_encomenda", SqlDbType.Int) { Value = idItem });
                    cmd.Parameters.Add(new SqlParameter("@metros_usados", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = tecido.MetrosUsados });
                    cmd.Parameters.Add(new SqlParameter("@preco_cobrado", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = precoCobrado });
                    cmd.ExecuteNonQuery();
                }
            }
        }

        private void GuardarMateriaisItem(SqlConnection conn, SqlTransaction transaction, int idItem, IEnumerable<ItemEncomendaMaterial> materiais)
        {
            if (materiais == null)
                return;

            foreach (ItemEncomendaMaterial material in materiais)
            {
                decimal precoCobrado = material.PrecoCobrado.HasValue
                    ? material.PrecoCobrado.Value
                    : ObterPrecoAtualMaterial(conn, transaction, material.Material);

                using (SqlCommand cmd = new SqlCommand("NM.spGuardarItemEncMaterial", conn, transaction))
                {
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add(new SqlParameter("@material", SqlDbType.Int) { Value = material.Material });
                    cmd.Parameters.Add(new SqlParameter("@item_encomenda", SqlDbType.Int) { Value = idItem });
                    cmd.Parameters.Add(new SqlParameter("@quantidade_usada", SqlDbType.Int) { Value = material.QuantidadeUsada });
                    cmd.Parameters.Add(new SqlParameter("@preco_cobrado", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = precoCobrado });
                    cmd.ExecuteNonQuery();
                }
            }
        }

        private object ObterValorDb(DateTime? valor)
        {
            return valor.HasValue ? (object)valor.Value.Date : DBNull.Value;
        }

        private object ObterValorDb(decimal? valor)
        {
            return valor.HasValue ? (object)valor.Value : DBNull.Value;
        }

        private object ObterTextoDb(string valor)
        {
            return string.IsNullOrWhiteSpace(valor) ? (object)DBNull.Value : valor.Trim();
        }

        public void Atualizar(Encomenda encomenda)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarEncomenda",
                new SqlParameter("@id_encomenda", SqlDbType.Int) { Value = encomenda.IdEncomenda },
                new SqlParameter("@data_encomenda", SqlDbType.Date) { Value = encomenda.DataEncomenda.Date },
                new SqlParameter("@data_prevista_entrega", SqlDbType.Date) { Value = encomenda.DataPrevistaEntrega.HasValue ? (object)encomenda.DataPrevistaEntrega.Value.Date : null },
                new SqlParameter("@estado", SqlDbType.NVarChar, 15) { Value = encomenda.Estado },
                new SqlParameter("@data_pronto", SqlDbType.Date) { Value = encomenda.DataPronto.HasValue ? (object)encomenda.DataPronto.Value.Date : null },
                new SqlParameter("@data_real_entrega", SqlDbType.Date) { Value = encomenda.DataRealEntrega.HasValue ? (object)encomenda.DataRealEntrega.Value.Date : null },
                new SqlParameter("@valor_total", SqlDbType.Decimal) { Precision = 10, Scale = 2, Value = encomenda.ValorTotal },
                new SqlParameter("@cliente", SqlDbType.Int) { Value = encomenda.Cliente }
            );
        }

        public void EliminarCompleta(int idEncomenda)
        {
            Database.ExecuteNonQuery(
                "NM.spEliminarEncomendaCompleta",
                new SqlParameter("@id_encomenda", SqlDbType.Int) { Value = idEncomenda }
            );
        }

        public DataTable ListarItens(int idEncomenda)
        {
            return Database.ExecuteDataTable(
                "NM.spListarItensEncomenda",
                new SqlParameter("@id_encomenda", SqlDbType.Int) { Value = idEncomenda }
            );
        }

        public DataTable ListarItensComUso(int idEncomenda)
        {
            using (SqlConnection conn = Database.GetConnection())
            using (SqlCommand cmd = new SqlCommand(
                @"SELECT I.ID, I.EncomendaID, I.PerfilID, I.Perfil, I.ModeloID, I.Modelo,
                         I.Tamanho, I.Preco, I.TipoPeca, I.CustoProducao, I.Descricao,
                         CASE
                            WHEN EXISTS (SELECT 1 FROM NM.ItemEnc_Tecido IT WHERE IT.item_encomenda = I.ID)
                              OR EXISTS (SELECT 1 FROM NM.ItemEnc_Material IM WHERE IM.item_encomenda = I.ID)
                                THEN N'✓'
                            ELSE '?'
                         END AS Materiais
                  FROM NM.vwItensEncomenda I
                  WHERE I.EncomendaID = @id_encomenda
                  ORDER BY I.ID", conn))
            using (SqlDataAdapter adapter = new SqlDataAdapter(cmd))
            {
                cmd.Parameters.Add(new SqlParameter("@id_encomenda", SqlDbType.Int) { Value = idEncomenda });
                DataTable tabela = new DataTable();
                adapter.Fill(tabela);
                return tabela;
            }
        }

        public void AdicionarUsoItem(int idItemEncomenda, ItemEncomendaTecido tecido, ItemEncomendaMaterial material)
        {
            using (SqlConnection conn = Database.GetConnection())
            {
                conn.Open();

                using (SqlTransaction transaction = conn.BeginTransaction())
                {
                    try
                    {
                        if (tecido != null)
                            GuardarOuAtualizarTecidoItem(conn, transaction, idItemEncomenda, tecido);

                        if (material != null)
                            GuardarOuAtualizarMaterialItem(conn, transaction, idItemEncomenda, material);

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

        public DataTable ListarUsoItem(int idItemEncomenda)
        {
            using (SqlConnection conn = Database.GetConnection())
            using (SqlCommand cmd = new SqlCommand(
                @"SELECT 'Tecido' AS Tipo,
                         T.nome AS Nome,
                         CAST(IT.metros_usados AS DECIMAL(10,2)) AS Quantidade,
                         'm' AS Unidade
                  FROM NM.ItemEnc_Tecido IT
                  INNER JOIN NM.Tecido T ON IT.tecido = T.id_tecido
                  WHERE IT.item_encomenda = @id_item
                  UNION ALL
                  SELECT 'Material' AS Tipo,
                         M.nome AS Nome,
                         CAST(IM.quantidade_usada AS DECIMAL(10,2)) AS Quantidade,
                         M.unidade_medida AS Unidade
                  FROM NM.ItemEnc_Material IM
                  INNER JOIN NM.Material M ON IM.material = M.id_material
                  WHERE IM.item_encomenda = @id_item
                  ORDER BY Tipo, Nome", conn))
            using (SqlDataAdapter adapter = new SqlDataAdapter(cmd))
            {
                cmd.Parameters.Add(new SqlParameter("@id_item", SqlDbType.Int) { Value = idItemEncomenda });
                DataTable tabela = new DataTable();
                adapter.Fill(tabela);
                return tabela;
            }
        }

        private void GuardarOuAtualizarTecidoItem(SqlConnection conn, SqlTransaction transaction, int idItem, ItemEncomendaTecido tecido)
        {
            decimal precoCobrado = tecido.PrecoCobrado.HasValue
                ? tecido.PrecoCobrado.Value
                : ObterPrecoAtualTecido(conn, transaction, tecido.Tecido);

            using (SqlCommand cmd = new SqlCommand("NM.spGuardarItemEncTecido", conn, transaction))
            {
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add(new SqlParameter("@tecido", SqlDbType.Int) { Value = tecido.Tecido });
                cmd.Parameters.Add(new SqlParameter("@item_encomenda", SqlDbType.Int) { Value = idItem });
                cmd.Parameters.Add(new SqlParameter("@metros_usados", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = tecido.MetrosUsados });
                cmd.Parameters.Add(new SqlParameter("@preco_cobrado", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = precoCobrado });
                cmd.ExecuteNonQuery();
            }
        }

        private void GuardarOuAtualizarMaterialItem(SqlConnection conn, SqlTransaction transaction, int idItem, ItemEncomendaMaterial material)
        {
            decimal precoCobrado = material.PrecoCobrado.HasValue
                ? material.PrecoCobrado.Value
                : ObterPrecoAtualMaterial(conn, transaction, material.Material);

            using (SqlCommand cmd = new SqlCommand("NM.spGuardarItemEncMaterial", conn, transaction))
            {
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add(new SqlParameter("@material", SqlDbType.Int) { Value = material.Material });
                cmd.Parameters.Add(new SqlParameter("@item_encomenda", SqlDbType.Int) { Value = idItem });
                cmd.Parameters.Add(new SqlParameter("@quantidade_usada", SqlDbType.Int) { Value = material.QuantidadeUsada });
                cmd.Parameters.Add(new SqlParameter("@preco_cobrado", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = precoCobrado });
                cmd.ExecuteNonQuery();
            }
        }

        private decimal ObterPrecoAtualTecido(SqlConnection conn, SqlTransaction transaction, int idTecido)
        {
            using (SqlCommand cmd = new SqlCommand("NM.spObterPrecoAtualTecido", conn, transaction))
            {
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add(new SqlParameter("@id_tecido", SqlDbType.Int) { Value = idTecido });
                DataTable dt = new DataTable();
                using (SqlDataAdapter adapter = new SqlDataAdapter(cmd))
                    adapter.Fill(dt);
                if (dt.Rows.Count == 0 || dt.Rows[0]["Preco"] == DBNull.Value)
                    throw new InvalidOperationException("Tecido nao encontrado para calcular preco cobrado.");
                return Convert.ToDecimal(dt.Rows[0]["Preco"]);
            }
        }

        private decimal ObterPrecoAtualMaterial(SqlConnection conn, SqlTransaction transaction, int idMaterial)
        {
            using (SqlCommand cmd = new SqlCommand("NM.spObterPrecoAtualMaterial", conn, transaction))
            {
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add(new SqlParameter("@id_material", SqlDbType.Int) { Value = idMaterial });
                DataTable dt = new DataTable();
                using (SqlDataAdapter adapter = new SqlDataAdapter(cmd))
                    adapter.Fill(dt);
                if (dt.Rows.Count == 0 || dt.Rows[0]["Preco"] == DBNull.Value)
                    throw new InvalidOperationException("Material nao encontrado para calcular preco cobrado.");
                return Convert.ToDecimal(dt.Rows[0]["Preco"]);
            }
        }

        public int CriarItem(EncomendaItem item)
        {
            SqlParameter idItem = new SqlParameter("@id_item_encomenda", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };

            Database.ExecuteNonQuery(
                "NM.spCriarItemEncomenda",
                new SqlParameter("@tamanho", SqlDbType.Int) { Value = item.Tamanho },
                new SqlParameter("@preco", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = item.Preco },
                new SqlParameter("@custo_mao_obra", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = item.CustoMaoObra },
                new SqlParameter("@tipo_peca", SqlDbType.NVarChar, 15) { Value = item.TipoPeca },
                new SqlParameter("@descricao_personalizacao", SqlDbType.NVarChar, 50) { Value = string.IsNullOrWhiteSpace(item.DescricaoPersonalizacao) ? null : item.DescricaoPersonalizacao },
                new SqlParameter("@perfil_medida", SqlDbType.Int) { Value = item.PerfilMedida },
                new SqlParameter("@modelo", SqlDbType.Int) { Value = item.Modelo.HasValue ? (object)item.Modelo.Value : null },
                new SqlParameter("@encomenda", SqlDbType.Int) { Value = item.Encomenda },
                idItem
            );

            return Convert.ToInt32(idItem.Value);
        }

        public void AtualizarItem(EncomendaItem item)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarItemEncomenda",
                new SqlParameter("@id_item_encomenda", SqlDbType.Int) { Value = item.IdItemEncomenda },
                new SqlParameter("@tamanho", SqlDbType.Int) { Value = item.Tamanho },
                new SqlParameter("@preco", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = item.Preco },
                new SqlParameter("@custo_mao_obra", SqlDbType.Decimal) { Precision = 6, Scale = 2, Value = item.CustoMaoObra },
                new SqlParameter("@tipo_peca", SqlDbType.NVarChar, 15) { Value = item.TipoPeca },
                new SqlParameter("@descricao_personalizacao", SqlDbType.NVarChar, 50) { Value = string.IsNullOrWhiteSpace(item.DescricaoPersonalizacao) ? null : item.DescricaoPersonalizacao },
                new SqlParameter("@perfil_medida", SqlDbType.Int) { Value = item.PerfilMedida },
                new SqlParameter("@modelo", SqlDbType.Int) { Value = item.Modelo.HasValue ? (object)item.Modelo.Value : null }
            );
        }

        public void EliminarItem(int idItemEncomenda)
        {
            Database.ExecuteNonQuery(
                "NM.spEliminarItemEncomenda",
                new SqlParameter("@id_item_encomenda", SqlDbType.Int) { Value = idItemEncomenda }
            );
        }
    }
}
