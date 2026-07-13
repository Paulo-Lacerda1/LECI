using NewModusApp.Models;
using NewModusApp.Repositories;
using System;
using System.Data;
using System.Drawing;
using System.Windows.Forms;
using NewModusApp.Utils;

namespace NewModusApp.Forms
{
    public partial class FrmEncomendas : Form
    {
        private readonly EncomendasRepository encomendasRepository = new EncomendasRepository();
        private readonly int? clienteInicial;

        public FrmEncomendas()
            : this(null)
        {
        }

        public FrmEncomendas(int? cliente)
        {
            clienteInicial = cliente;

            InitializeComponent();
            cmbEstado.SelectedIndexChanged += CmbEstado_SelectedIndexChanged;
            CarregarClientes();
            CarregarEstados();
            CarregarModelos();
            CarregarPerfisMedida(null);
            AplicarClienteInicial();
            CarregarEncomendas();
            AtualizarEstadoBotoes();
        }
        private void CarregarClientes()
        {
            try
            {
                DataTable clientes = encomendasRepository.ListarClientes();
                cmbCliente.DataSource = clientes.Copy();
                cmbCliente.DisplayMember = "Nome";
                cmbCliente.ValueMember = "ID";
                cmbCliente.SelectedIndex = -1;

                DataTable clientesFiltro = clientes.Copy();
                DataRow linhaTodos = clientesFiltro.NewRow();
                linhaTodos["ID"] = DBNull.Value;
                linhaTodos["Nome"] = "Todos os clientes";
                clientesFiltro.Rows.InsertAt(linhaTodos, 0);

                cmbFiltroCliente.DataSource = clientesFiltro;
                cmbFiltroCliente.DisplayMember = "Nome";
                cmbFiltroCliente.ValueMember = "ID";
                cmbFiltroCliente.SelectedIndex = 0;
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os clientes.", ex);
            }
        }

        private void CarregarEstados()
        {
            string[] estados = { "Pendente", "Em Produção", "Pronta", "Entregue", "Cancelada" };
            cmbEstado.Items.Clear();
            cmbEstado.Items.AddRange(estados);
            cmbEstado.SelectedIndex = -1;

            cmbFiltroEstado.Items.Clear();
            cmbFiltroEstado.Items.Add("Todos");
            cmbFiltroEstado.Items.AddRange(estados);
            cmbFiltroEstado.SelectedIndex = 0;
        }

        private void CarregarModelos()
        {
            try
            {
                DataTable modelos = encomendasRepository.ListarModelos();
                DataRow linhaSemModelo = modelos.NewRow();
                linhaSemModelo["ID"] = DBNull.Value;
                linhaSemModelo["Nome"] = "Sem modelo";
                modelos.Rows.InsertAt(linhaSemModelo, 0);

                cmbModelo.DataSource = modelos;
                cmbModelo.DisplayMember = "Nome";
                cmbModelo.ValueMember = "ID";
                cmbModelo.SelectedIndex = 0;
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os modelos.", ex);
            }
        }

        private void CarregarPerfisMedida(int? cliente)
        {
            try
            {
                DataTable perfis = encomendasRepository.ListarPerfisMedida(cliente);
                cmbPerfilMedida.DataSource = perfis;
                cmbPerfilMedida.DisplayMember = "Perfil";
                cmbPerfilMedida.ValueMember = "ID";
                cmbPerfilMedida.SelectedIndex = -1;
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os perfis de medida.", ex);
            }
        }

        private void AplicarClienteInicial()
        {
            if (!clienteInicial.HasValue)
                return;

            cmbCliente.SelectedValue = clienteInicial.Value;
            cmbFiltroCliente.SelectedValue = clienteInicial.Value;
        }

        private void CarregarEncomendas()
        {
            try
            {
                int? clienteFiltro = ObterClienteFiltro();
                string estadoFiltro = cmbFiltroEstado.SelectedIndex <= 0 ? null : cmbFiltroEstado.Text;
                DateTime? dataInicio = chkFiltrarData.Checked ? (DateTime?)dtpFiltroInicio.Value.Date : null;
                DateTime? dataFim = chkFiltrarData.Checked ? (DateTime?)dtpFiltroFim.Value.Date : null;

                if (dataInicio.HasValue && dataFim.HasValue && dataInicio.Value > dataFim.Value)
                {
                    MessageBox.Show(
                        "A data inicial nao pode ser superior a data final.",
                        "Filtro invalido",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Warning
                    );
                    return;
                }

                dgvEncomendas.DataSource = encomendasRepository.Pesquisar(clienteFiltro, estadoFiltro, dataInicio, dataFim);
                ConfigurarColunasGrid();
                dgvEncomendas.AplicarEstiloModus();
                dgvEncomendas.ClearSelection();
                AtualizarEstadoBotoes();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar as encomendas.", ex);
            }
        }

        private void ConfigurarColunasGrid()
        {
            if (dgvEncomendas.Columns.Contains("ClienteID"))
                dgvEncomendas.Columns["ClienteID"].Visible = false;

            FormatarColunaData("Data");
            FormatarColunaData("DataPrevista");
            FormatarColunaData("DataPronto");
            FormatarColunaData("DataEntrega");

            if (dgvEncomendas.Columns.Contains("ValorTotal"))
                dgvEncomendas.Columns["ValorTotal"].DefaultCellStyle.Format = "N2";

            if (dgvEncomendas.Columns.Contains("TotalItens"))
                dgvEncomendas.Columns["TotalItens"].DefaultCellStyle.Format = "N2";
        }

        private void FormatarColunaData(string coluna)
        {
            if (dgvEncomendas.Columns.Contains(coluna))
                dgvEncomendas.Columns[coluna].DefaultCellStyle.Format = "dd/MM/yyyy";
        }

        private int? ObterClienteFiltro()
        {
            return ObterValorInteiroCombo(cmbFiltroCliente);
        }

        private int? ObterClienteSelecionado()
        {
            return ObterValorInteiroCombo(cmbCliente);
        }

        private int? ObterValorInteiroCombo(ComboBox comboBox)
        {
            object valor = comboBox.SelectedValue;

            if (valor == null || valor == DBNull.Value || valor is DataRowView)
                return null;

            return Convert.ToInt32(valor);
        }

        private string ObterEstadoSelecionadoNaGrelha()
        {
            if (dgvEncomendas.CurrentRow == null || !dgvEncomendas.Columns.Contains("Estado"))
                return null;

            object valor = dgvEncomendas.CurrentRow.Cells["Estado"].Value;
            if (valor == null || valor == DBNull.Value)
                return null;

            return Convert.ToString(valor);
        }

        private bool PodeTransitarEstado(string estadoAtual, string estadoDestino)
        {
            if (string.IsNullOrWhiteSpace(estadoAtual) || string.IsNullOrWhiteSpace(estadoDestino))
                return false;

            if (string.Equals(estadoAtual, estadoDestino, StringComparison.OrdinalIgnoreCase))
                return true;

            switch (estadoAtual)
            {
                case "Pendente":
                    return estadoDestino == "Em Produção" || estadoDestino == "Cancelada";
                case "Em Produção":
                    return estadoDestino == "Pronta" || estadoDestino == "Cancelada";
                case "Pronta":
                    return estadoDestino == "Entregue";
                case "Entregue":
                case "Cancelada":
                    return false;
                default:
                    return false;
            }
        }

        private void AtualizarEstadoBotoes()
        {
            string estadoAtual = ObterEstadoSelecionadoNaGrelha();

            if (string.IsNullOrWhiteSpace(estadoAtual))
            {
                btnEditar.Enabled = false;
                return;
            }

            btnEditar.Enabled = PodeTransitarEstado(estadoAtual, cmbEstado.Text);
        }

        private void CmbCliente_SelectedIndexChanged(object sender, EventArgs e)
        {
            CarregarPerfisMedida(ObterClienteSelecionado());
        }

        private void CmbEstado_SelectedIndexChanged(object sender, EventArgs e)
        {
            AtualizarEstadoBotoes();
        }

        private void ChkFiltrarData_CheckedChanged(object sender, EventArgs e)
        {
            dtpFiltroInicio.Enabled = chkFiltrarData.Checked;
            dtpFiltroFim.Enabled = chkFiltrarData.Checked;
        }

        private void BtnPesquisar_Click(object sender, EventArgs e)
        {
            CarregarEncomendas();
        }

        private void BtnGuardar_Click(object sender, EventArgs e)
        {
            Encomenda encomenda;
            if (!TentarLerEncomenda(out encomenda, false))
                return;

            try
            {
                int idEncomenda = encomendasRepository.Criar(encomenda);

                MessageBox.Show(
                    "Encomenda inserida com sucesso. ID: " + idEncomenda,
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarEncomendas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel inserir a encomenda.", ex);
            }
        }

        private void BtnEditar_Click(object sender, EventArgs e)
        {
            Encomenda encomenda;
            if (!TentarLerEncomenda(out encomenda, true))
                return;

            try
            {
                encomendasRepository.Atualizar(encomenda);

                MessageBox.Show(
                    "Encomenda atualizada com sucesso.",
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarEncomendas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel atualizar a encomenda.", ex);
            }
        }

        private void BtnApagar_Click(object sender, EventArgs e)
        {
            int idEncomenda;
            if (!int.TryParse(txtId.Text, out idEncomenda))
            {
                MessageBox.Show(
                    "Seleciona uma encomenda primeiro.",
                    "Encomenda nao selecionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return;
            }

            try
            {
                DataRow resumo = encomendasRepository.ObterResumoEliminacao(idEncomenda);
                int totalItens = resumo == null ? 0 : Convert.ToInt32(resumo["Itens"]);
                int totalPagamentos = resumo == null ? 0 : Convert.ToInt32(resumo["Pagamentos"]);
                int totalAjustes = resumo == null ? 0 : Convert.ToInt32(resumo["Ajustes"]);

                string mensagem =
                    "Tens a certeza que queres apagar esta encomenda?\n\n" +
                    "Dados associados encontrados:\n" +
                    "- Itens: " + totalItens + "\n" +
                    "- Pagamentos: " + totalPagamentos + "\n" +
                    "- Ajustes: " + totalAjustes + "\n\n" +
                    "Se continuares, estes dados tambem serao apagados.";

                DialogResult resposta = MessageBox.Show(
                    mensagem,
                    "Confirmar eliminacao",
                    MessageBoxButtons.YesNo,
                    MessageBoxIcon.Warning
                );

                if (resposta != DialogResult.Yes)
                    return;

                encomendasRepository.EliminarCompleta(idEncomenda);

                MessageBox.Show(
                    "Encomenda e dados associados apagados com sucesso.",
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarEncomendas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel apagar a encomenda.", ex);
            }
        }

        private void BtnLimpar_Click(object sender, EventArgs e)
        {
            LimparCampos();
        }

        private void BtnItemGuardar_Click(object sender, EventArgs e)
        {
            EncomendaItem item;
            if (!TentarLerItem(out item, false))
                return;

            try
            {
                int idItem = encomendasRepository.CriarItem(item);

                MessageBox.Show(
                    "Item inserido com sucesso. ID: " + idItem,
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCamposItem();
                CarregarItensEncomenda(item.Encomenda);
                CarregarEncomendas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel inserir o item da encomenda.", ex);
            }
        }

        private void BtnItemEditar_Click(object sender, EventArgs e)
        {
            EncomendaItem item;
            if (!TentarLerItem(out item, true))
                return;

            try
            {
                encomendasRepository.AtualizarItem(item);

                MessageBox.Show(
                    "Item atualizado com sucesso.",
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCamposItem();
                CarregarItensEncomenda(item.Encomenda);
                CarregarEncomendas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel atualizar o item da encomenda.", ex);
            }
        }

        private void BtnItemApagar_Click(object sender, EventArgs e)
        {
            int idItem;
            int idEncomenda;
            if (!int.TryParse(txtItemId.Text, out idItem) || !int.TryParse(txtId.Text, out idEncomenda))
            {
                MessageBox.Show(
                    "Seleciona um item primeiro.",
                    "Item nao selecionado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return;
            }

            DialogResult resposta = MessageBox.Show(
                "Tens a certeza que queres apagar este item da encomenda?",
                "Confirmar eliminacao",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning
            );

            if (resposta != DialogResult.Yes)
                return;

            try
            {
                encomendasRepository.EliminarItem(idItem);

                MessageBox.Show(
                    "Item apagado com sucesso.",
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCamposItem();
                CarregarItensEncomenda(idEncomenda);
                CarregarEncomendas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel apagar o item da encomenda.", ex);
            }
        }

        private bool TentarLerEncomenda(out Encomenda encomenda, bool exigeId)
        {
            encomenda = null;

            int idEncomenda = 0;
            if (exigeId && !int.TryParse(txtId.Text, out idEncomenda))
            {
                MessageBox.Show(
                    "Seleciona uma encomenda primeiro.",
                    "Encomenda nao selecionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            int? clienteSelecionado = ObterValorInteiroCombo(cmbCliente);
            if (!clienteSelecionado.HasValue)
            {
                MessageBox.Show(
                    "Seleciona o cliente.",
                    "Cliente obrigatorio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            if (cmbEstado.SelectedIndex < 0)
            {
                MessageBox.Show(
                    "Seleciona o estado.",
                    "Estado obrigatorio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            decimal valorTotal;
            if (!decimal.TryParse(txtValorTotal.Text.Trim(), out valorTotal))
            {
                MessageBox.Show(
                    "O valor total deve ser numerico.",
                    "Valor invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                txtValorTotal.Focus();
                return false;
            }

            if (valorTotal < 0)
            {
                MessageBox.Show(
                    "O valor total nao pode ser negativo.",
                    "Valor invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                txtValorTotal.Focus();
                return false;
            }

            if (chkDataPronto.Checked && dtpDataPronto.Value.Date < dtpDataEncomenda.Value.Date)
            {
                MessageBox.Show(
                    "A data pronto nao pode ser anterior a data da encomenda.",
                    "Data invalida",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                dtpDataPronto.Focus();
                return false;
            }

            if (chkDataRealEntrega.Checked && dtpDataRealEntrega.Value.Date < dtpDataEncomenda.Value.Date)
            {
                MessageBox.Show(
                    "A data real de entrega nao pode ser anterior a data da encomenda.",
                    "Data invalida",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                dtpDataRealEntrega.Focus();
                return false;
            }

            encomenda = new Encomenda
            {
                IdEncomenda = idEncomenda,
                Cliente = clienteSelecionado.Value,
                DataEncomenda = dtpDataEncomenda.Value.Date,
                DataPrevistaEntrega = chkDataPrevista.Checked ? (DateTime?)dtpDataPrevista.Value.Date : null,
                Estado = cmbEstado.Text,
                DataPronto = chkDataPronto.Checked ? (DateTime?)dtpDataPronto.Value.Date : null,
                DataRealEntrega = chkDataRealEntrega.Checked ? (DateTime?)dtpDataRealEntrega.Value.Date : null,
                ValorTotal = valorTotal
            };

            return true;
        }

        private bool TentarLerItem(out EncomendaItem item, bool exigeId)
        {
            item = null;

            int idEncomenda;
            if (!int.TryParse(txtId.Text, out idEncomenda))
            {
                MessageBox.Show(
                    "Seleciona ou guarda uma encomenda antes de adicionar itens.",
                    "Encomenda nao selecionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            int idItem = 0;
            if (exigeId && !int.TryParse(txtItemId.Text, out idItem))
            {
                MessageBox.Show(
                    "Seleciona um item primeiro.",
                    "Item nao selecionado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            int? perfilSelecionado = ObterValorInteiroCombo(cmbPerfilMedida);
            if (!perfilSelecionado.HasValue)
            {
                MessageBox.Show(
                    "Seleciona o perfil de medida.",
                    "Perfil obrigatorio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            int tamanho;
            if (!int.TryParse(txtTamanho.Text.Trim(), out tamanho) || tamanho <= 0)
            {
                MessageBox.Show(
                    "O tamanho deve ser um numero inteiro positivo.",
                    "Valor invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                txtTamanho.Focus();
                return false;
            }

            decimal preco;
            if (!decimal.TryParse(txtPrecoItem.Text.Trim(), out preco) || preco < 0)
            {
                MessageBox.Show(
                    "O preco deve ser numerico e nao pode ser negativo.",
                    "Valor invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                txtPrecoItem.Focus();
                return false;
            }

            string tipoPeca = txtTipoPeca.Text.Trim();
            if (tipoPeca == "")
            {
                MessageBox.Show(
                    "Preenche o tipo de peca.",
                    "Campo obrigatorio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                txtTipoPeca.Focus();
                return false;
            }

            if (tipoPeca.Length > 15)
            {
                MessageBox.Show(
                    "O tipo de peca nao pode ter mais de 15 caracteres.",
                    "Valor invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                txtTipoPeca.Focus();
                return false;
            }

            decimal custo;
            decimal? custoProducao = null;
            if (txtCustoProducao.Text.Trim() != "")
            {
                if (!decimal.TryParse(txtCustoProducao.Text.Trim(), out custo) || custo < 0)
                {
                    MessageBox.Show(
                        "O custo de producao deve ser numerico e nao pode ser negativo.",
                        "Valor invalido",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Warning
                    );
                    txtCustoProducao.Focus();
                    return false;
                }

                custoProducao = custo;
            }

            string descricao = txtDescricaoPersonalizacao.Text.Trim();
            if (descricao.Length > 50)
            {
                MessageBox.Show(
                    "A descricao nao pode ter mais de 50 caracteres.",
                    "Valor invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                txtDescricaoPersonalizacao.Focus();
                return false;
            }

            int? modelo = null;
            int? modeloSelecionado = ObterValorInteiroCombo(cmbModelo);
            if (modeloSelecionado.HasValue)
                modelo = modeloSelecionado.Value;

            item = new EncomendaItem
            {
                IdItemEncomenda = idItem,
                Encomenda = idEncomenda,
                PerfilMedida = perfilSelecionado.Value,
                Modelo = modelo,
                Tamanho = tamanho,
                Preco = preco,
                TipoPeca = tipoPeca,
                CustoProducao = custoProducao,
                DescricaoPersonalizacao = descricao
            };

            return true;
        }

        private void DgvEncomendas_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            DataGridViewRow row = dgvEncomendas.Rows[e.RowIndex];

            txtId.Text = row.Cells["ID"].Value.ToString();
            cmbCliente.SelectedValue = Convert.ToInt32(row.Cells["ClienteID"].Value);
            dtpDataEncomenda.Value = Convert.ToDateTime(row.Cells["Data"].Value);
            cmbEstado.Text = row.Cells["Estado"].Value.ToString();
            txtValorTotal.Text = Convert.ToDecimal(row.Cells["ValorTotal"].Value).ToString("0.00");

            DefinirDataOpcional(chkDataPrevista, dtpDataPrevista, row.Cells["DataPrevista"].Value);
            DefinirDataOpcional(chkDataPronto, dtpDataPronto, row.Cells["DataPronto"].Value);
            DefinirDataOpcional(chkDataRealEntrega, dtpDataRealEntrega, row.Cells["DataEntrega"].Value);

            int idEncomenda = Convert.ToInt32(row.Cells["ID"].Value);
            int idCliente = Convert.ToInt32(row.Cells["ClienteID"].Value);
            CarregarPerfisMedida(idCliente);
            CarregarItensEncomenda(idEncomenda);
            LimparCamposItem();
            AtualizarEstadoBotoes();
        }

        private void DefinirDataOpcional(CheckBox checkBox, DateTimePicker dateTimePicker, object valor)
        {
            if (valor == null || valor == DBNull.Value)
            {
                checkBox.Checked = false;
                dateTimePicker.Value = DateTime.Today;
                return;
            }

            checkBox.Checked = true;
            dateTimePicker.Value = Convert.ToDateTime(valor);
        }

        private void DgvEncomendas_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvEncomendas.ClearSelection();
            AtualizarEstadoBotoes();
        }

        private void CarregarItensEncomenda(int idEncomenda)
        {
            try
            {
                dgvItens.DataSource = encomendasRepository.ListarItens(idEncomenda);
                ConfigurarColunasItens();
                dgvItens.AplicarEstiloModus();
                dgvItens.ClearSelection();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os itens da encomenda.", ex);
            }
        }

        private void ConfigurarColunasItens()
        {
            string[] colunasOcultas = { "EncomendaID", "PerfilID", "ModeloID" };
            foreach (string coluna in colunasOcultas)
            {
                if (dgvItens.Columns.Contains(coluna))
                    dgvItens.Columns[coluna].Visible = false;
            }

            if (dgvItens.Columns.Contains("Preco"))
                dgvItens.Columns["Preco"].DefaultCellStyle.Format = "N2";

            if (dgvItens.Columns.Contains("CustoProducao"))
                dgvItens.Columns["CustoProducao"].DefaultCellStyle.Format = "N2";
        }

        private void DgvItens_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            DataGridViewRow row = dgvItens.Rows[e.RowIndex];

            txtItemId.Text = row.Cells["ID"].Value.ToString();
            cmbPerfilMedida.SelectedValue = Convert.ToInt32(row.Cells["PerfilID"].Value);

            if (row.Cells["ModeloID"].Value == null || row.Cells["ModeloID"].Value == DBNull.Value)
                cmbModelo.SelectedIndex = 0;
            else
                cmbModelo.SelectedValue = Convert.ToInt32(row.Cells["ModeloID"].Value);

            txtTamanho.Text = row.Cells["Tamanho"].Value.ToString();
            txtPrecoItem.Text = Convert.ToDecimal(row.Cells["Preco"].Value).ToString("0.00");
            txtTipoPeca.Text = row.Cells["TipoPeca"].Value.ToString();
            txtCustoProducao.Text = row.Cells["CustoProducao"].Value == DBNull.Value
                ? ""
                : Convert.ToDecimal(row.Cells["CustoProducao"].Value).ToString("0.00");
            txtDescricaoPersonalizacao.Text = row.Cells["Descricao"].Value == DBNull.Value
                ? ""
                : row.Cells["Descricao"].Value.ToString();
        }

        private void DgvItens_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvItens.ClearSelection();
        }

        private void LimparCampos()
        {
            txtId.Clear();
            cmbCliente.SelectedIndex = -1;
            dtpDataEncomenda.Value = DateTime.Today;
            cmbEstado.SelectedIndex = -1;
            txtValorTotal.Text = "0,00";
            chkDataPrevista.Checked = false;
            dtpDataPrevista.Value = DateTime.Today;
            chkDataPronto.Checked = false;
            dtpDataPronto.Value = DateTime.Today;
            chkDataRealEntrega.Checked = false;
            dtpDataRealEntrega.Value = DateTime.Today;

            if (clienteInicial.HasValue)
                cmbCliente.SelectedValue = clienteInicial.Value;

            dgvEncomendas.ClearSelection();
            dgvItens.DataSource = null;
            LimparCamposItem();
            AtualizarEstadoBotoes();
        }

        private void LimparCamposItem()
        {
            txtItemId.Clear();
            cmbPerfilMedida.SelectedIndex = -1;
            cmbModelo.SelectedIndex = cmbModelo.Items.Count > 0 ? 0 : -1;
            txtTamanho.Clear();
            txtPrecoItem.Clear();
            txtTipoPeca.Clear();
            txtCustoProducao.Clear();
            txtDescricaoPersonalizacao.Clear();

            if (dgvItens != null)
                dgvItens.ClearSelection();
        }

        private void BtnVoltar_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirEncomendasPorMedida(clienteInicial);
        }

        private FrmPrincipal ObterFrmPrincipal()
        {
            Control atual = Parent;

            while (atual != null)
            {
                FrmPrincipal principal = atual as FrmPrincipal;
                if (principal != null)
                    return principal;

                atual = atual.Parent;
            }

            return null;
        }

        private void MostrarErroBaseDados(string mensagem, Exception ex)
        {
            MessageBox.Show(
                mensagem + "\n\n" + ex.Message,
                "Erro de base de dados",
                MessageBoxButtons.OK,
                MessageBoxIcon.Error
            );
        }
    }
}
