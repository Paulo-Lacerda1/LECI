using NewModusApp.Models;
using NewModusApp.Repositories;
using System;
using System.Data;
using System.Drawing;
using System.Windows.Forms;
using NewModusApp.Utils;

namespace NewModusApp.Forms
{
    public partial class FrmProntoVestir : Form
    {
        private readonly ProntoVestirRepository repository = new ProntoVestirRepository();
        private readonly int? clienteInicial;

        public FrmProntoVestir()
            : this(null)
        {
        }

        public FrmProntoVestir(int? cliente)
        {
            clienteInicial = cliente;

            InitializeComponent();
            CarregarClientes();
            CarregarMetodosPagamento();
            CarregarProdutos();
            ConfigurarFiltroData();
            AplicarClienteInicial();
            CarregarCompras();
        }

        private void ConfigurarFiltroData()
        {
            dtpFiltroInicio.Enabled = true;
            dtpFiltroFim.Enabled = true;
            dtpFiltroInicio.ValueChanged += DtpFiltroData_ValueChanged;
            dtpFiltroFim.ValueChanged += DtpFiltroData_ValueChanged;
        }
        private void CarregarClientes()
        {
            try
            {
                DataTable clientes = repository.ListarClientes();

                DataTable clientesCompra = clientes.Copy();
                DataRow anonimo = clientesCompra.NewRow();
                anonimo["ID"] = DBNull.Value;
                anonimo["Nome"] = "Cliente ocasional";
                clientesCompra.Rows.InsertAt(anonimo, 0);
                cmbCliente.DataSource = clientesCompra;
                cmbCliente.DisplayMember = "Nome";
                cmbCliente.ValueMember = "ID";
                cmbCliente.SelectedIndex = 0;

                DataTable clientesFiltro = clientes.Copy();
                DataRow todos = clientesFiltro.NewRow();
                todos["ID"] = DBNull.Value;
                todos["Nome"] = "Todos os clientes";
                clientesFiltro.Rows.InsertAt(todos, 0);
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

        private void CarregarMetodosPagamento()
        {
            string[] metodos = { "Cartão", "MBWay", "Dinheiro", "Transferência" };
            cmbMetodoPagamento.Items.Clear();
            cmbMetodoPagamento.Items.AddRange(metodos);
            cmbMetodoPagamento.SelectedIndex = 0;

            cmbFiltroMetodoPagamento.Items.Clear();
            cmbFiltroMetodoPagamento.Items.Add("Todos");
            cmbFiltroMetodoPagamento.Items.AddRange(metodos);
            cmbFiltroMetodoPagamento.SelectedIndex = 0;
        }

        private void CarregarProdutos()
        {
            try
            {
                DataTable produtos = repository.ListarProdutos();
                cmbProduto.DataSource = produtos;
                cmbProduto.DisplayMember = "Produto";
                cmbProduto.ValueMember = "ID";
                cmbProduto.SelectedIndex = -1;
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os produtos.", ex);
            }
        }

        private void AplicarClienteInicial()
        {
            if (!clienteInicial.HasValue)
                return;

            cmbCliente.SelectedValue = clienteInicial.Value;
            cmbFiltroCliente.SelectedValue = clienteInicial.Value;
        }

        private void CarregarCompras()
        {
            try
            {
                int? cliente = ObterValorInteiroCombo(cmbFiltroCliente);
                string metodo = cmbFiltroMetodoPagamento.SelectedIndex <= 0 ? null : cmbFiltroMetodoPagamento.Text;
                DateTime? inicio = chkFiltrarData.Checked ? (DateTime?)dtpFiltroInicio.Value.Date : null;
                DateTime? fim = chkFiltrarData.Checked ? (DateTime?)dtpFiltroFim.Value.Date : null;

                if (inicio.HasValue && fim.HasValue && inicio.Value > fim.Value)
                {
                    MessageBox.Show("A data inicial nao pode ser superior a data final.", "Filtro invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }

                dgvCompras.DataSource = repository.Pesquisar(cliente, metodo, inicio, fim);
                ConfigurarColunasCompras();
                dgvCompras.AplicarEstiloModus();
                dgvCompras.ClearSelection();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar as vendas pronto a vestir.", ex);
            }
        }

        private void ConfigurarColunasCompras()
        {
            if (dgvCompras.Columns.Contains("ClienteID"))
                dgvCompras.Columns["ClienteID"].Visible = false;
            if (dgvCompras.Columns.Contains("Data"))
                dgvCompras.Columns["Data"].DefaultCellStyle.Format = "dd/MM/yyyy";
            if (dgvCompras.Columns.Contains("ValorTotal"))
                dgvCompras.Columns["ValorTotal"].DefaultCellStyle.Format = "N2";
        }

        private void CarregarDetalhes(int idCompra)
        {
            try
            {
                dgvDetalhes.DataSource = repository.ListarDetalhes(idCompra);
                ConfigurarColunasDetalhes();
                dgvDetalhes.AplicarEstiloModus();
                dgvDetalhes.ClearSelection();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os produtos da venda.", ex);
            }
        }

        private void ConfigurarColunasDetalhes()
        {
            string[] ocultas = { "CompraID", "ProdutoID" };
            foreach (string coluna in ocultas)
            {
                if (dgvDetalhes.Columns.Contains(coluna))
                    dgvDetalhes.Columns[coluna].Visible = false;
            }
            if (dgvDetalhes.Columns.Contains("PrecoUnitario"))
                dgvDetalhes.Columns["PrecoUnitario"].DefaultCellStyle.Format = "N2";
            if (dgvDetalhes.Columns.Contains("Subtotal"))
                dgvDetalhes.Columns["Subtotal"].DefaultCellStyle.Format = "N2";
        }

        private int? ObterValorInteiroCombo(ComboBox combo)
        {
            object valor = combo.SelectedValue;
            if (valor == null || valor == DBNull.Value || valor is DataRowView)
                return null;
            return Convert.ToInt32(valor);
        }

        private void ChkFiltrarData_CheckedChanged(object sender, EventArgs e)
        {
            dtpFiltroInicio.Enabled = true;
            dtpFiltroFim.Enabled = true;
        }

        private void DtpFiltroData_ValueChanged(object sender, EventArgs e)
        {
            chkFiltrarData.Checked = true;
        }

        private void BtnPesquisar_Click(object sender, EventArgs e)
        {
            CarregarCompras();
        }

        private void BtnVerDetalhes_Click(object sender, EventArgs e)
        {
            int idCompra;
            if (!int.TryParse(txtId.Text, out idCompra))
            {
                MessageBox.Show("Seleciona uma venda primeiro.", "Venda nao selecionada", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            using (FrmDetalhesCompraProntoVestirPopup popup = new FrmDetalhesCompraProntoVestirPopup(idCompra))
            {
                popup.ShowDialog(this);

                if (popup.Alterou)
                {
                    CarregarCompras();
                    SelecionarCompraNaGrelha(idCompra);
                }
            }
        }

        private void BtnVoltar_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirProntoVestir(clienteInicial);
        }

        private void BtnGuardar_Click(object sender, EventArgs e)
        {
            CompraProntoVestir compra;
            if (!TentarLerCompra(out compra, false))
                return;

            try
            {
                int idCompra = repository.Criar(compra);
                MessageBox.Show("Venda criada com sucesso. ID: " + idCompra, "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                LimparCampos();
                CarregarCompras();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel criar a venda.", ex);
            }
        }

        private void BtnEditar_Click(object sender, EventArgs e)
        {
            CompraProntoVestir compra;
            if (!TentarLerCompra(out compra, true))
                return;

            try
            {
                repository.Atualizar(compra);
                MessageBox.Show("Venda atualizada com sucesso.", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                CarregarCompras();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel atualizar a venda.", ex);
            }
        }

        private void BtnApagar_Click(object sender, EventArgs e)
        {
            int idCompra;
            if (!int.TryParse(txtId.Text, out idCompra))
            {
                MessageBox.Show("Seleciona uma venda primeiro.", "Venda nao selecionada", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                DataRow resumo = repository.ObterResumoEliminacao(idCompra);
                int detalhes = resumo == null ? 0 : Convert.ToInt32(resumo["Detalhes"]);
                int ajustes = resumo == null ? 0 : Convert.ToInt32(resumo["Ajustes"]);

                DialogResult resposta = MessageBox.Show(
                    "Tens a certeza que queres apagar esta venda?\n\nProdutos: " + detalhes + "\nAjustes: " + ajustes,
                    "Confirmar eliminacao",
                    MessageBoxButtons.YesNo,
                    MessageBoxIcon.Warning
                );

                if (resposta != DialogResult.Yes)
                    return;

                repository.EliminarCompleta(idCompra);
                MessageBox.Show("Venda apagada com sucesso.", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                LimparCampos();
                CarregarCompras();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel apagar a venda.", ex);
            }
        }

        private void BtnLimpar_Click(object sender, EventArgs e)
        {
            LimparCampos();
        }

        private bool TentarLerCompra(out CompraProntoVestir compra, bool exigeId)
        {
            compra = null;
            int idCompra = 0;
            if (exigeId && !int.TryParse(txtId.Text, out idCompra))
            {
                MessageBox.Show("Seleciona uma venda primeiro.", "Venda nao selecionada", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            if (cmbMetodoPagamento.SelectedIndex < 0)
            {
                MessageBox.Show("Seleciona o metodo de pagamento.", "Campo obrigatorio", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            compra = new CompraProntoVestir
            {
                IdCompra = idCompra,
                DataCompra = dtpDataCompra.Value.Date,
                MetodoPagamento = cmbMetodoPagamento.Text,
                Cliente = ObterValorInteiroCombo(cmbCliente)
            };

            return true;
        }

        private void DgvCompras_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            DataGridViewRow row = dgvCompras.Rows[e.RowIndex];
            PreencherCamposCompra(row);
        }

        private void PreencherCamposCompra(DataGridViewRow row)
        {
            txtId.Text = row.Cells["ID"].Value.ToString();
            dtpDataCompra.Value = Convert.ToDateTime(row.Cells["Data"].Value);
            cmbMetodoPagamento.Text = row.Cells["MetodoPagamento"].Value.ToString();
            txtValorTotal.Text = Convert.ToDecimal(row.Cells["ValorTotal"].Value).ToString("0.00");

            if (row.Cells["ClienteID"].Value == DBNull.Value)
                cmbCliente.SelectedIndex = 0;
            else
                cmbCliente.SelectedValue = Convert.ToInt32(row.Cells["ClienteID"].Value);
        }

        private void SelecionarCompraNaGrelha(int idCompra)
        {
            foreach (DataGridViewRow row in dgvCompras.Rows)
            {
                if (Convert.ToInt32(row.Cells["ID"].Value) != idCompra)
                    continue;

                dgvCompras.ClearSelection();
                row.Selected = true;
                dgvCompras.CurrentCell = row.Cells["ID"];
                PreencherCamposCompra(row);
                return;
            }
        }

        private void DgvCompras_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvCompras.ClearSelection();
        }

        private void BtnDetalheGuardar_Click(object sender, EventArgs e)
        {
            DetalheCompraProntoVestir detalhe;
            if (!TentarLerDetalhe(out detalhe, false))
                return;

            try
            {
                int idDetalhe = repository.CriarDetalhe(detalhe);
                MessageBox.Show("Produto adicionado com sucesso. ID: " + idDetalhe, "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                LimparCamposDetalhe();
                CarregarDetalhes(detalhe.Compra);
                CarregarCompras();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel adicionar o produto.", ex);
            }
        }

        private void BtnDetalheEditar_Click(object sender, EventArgs e)
        {
            DetalheCompraProntoVestir detalhe;
            if (!TentarLerDetalhe(out detalhe, true))
                return;

            try
            {
                repository.AtualizarDetalhe(detalhe);
                MessageBox.Show("Produto atualizado com sucesso.", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                LimparCamposDetalhe();
                CarregarDetalhes(detalhe.Compra);
                CarregarCompras();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel atualizar o produto.", ex);
            }
        }

        private void BtnDetalheApagar_Click(object sender, EventArgs e)
        {
            int idDetalhe;
            int idCompra;
            if (!int.TryParse(txtDetalheId.Text, out idDetalhe) || !int.TryParse(txtId.Text, out idCompra))
            {
                MessageBox.Show("Seleciona um produto primeiro.", "Produto nao selecionado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            DialogResult resposta = MessageBox.Show("Tens a certeza que queres apagar este produto da venda?", "Confirmar eliminacao", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (resposta != DialogResult.Yes)
                return;

            try
            {
                repository.EliminarDetalhe(idDetalhe);
                MessageBox.Show("Produto removido com sucesso.", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                LimparCamposDetalhe();
                CarregarDetalhes(idCompra);
                CarregarCompras();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel remover o produto.", ex);
            }
        }

        private bool TentarLerDetalhe(out DetalheCompraProntoVestir detalhe, bool exigeId)
        {
            detalhe = null;
            int idCompra;
            if (!int.TryParse(txtId.Text, out idCompra))
            {
                MessageBox.Show("Seleciona ou guarda uma venda antes de adicionar produtos.", "Venda nao selecionada", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            int idDetalhe = 0;
            if (exigeId && !int.TryParse(txtDetalheId.Text, out idDetalhe))
            {
                MessageBox.Show("Seleciona um produto primeiro.", "Produto nao selecionado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            int? produto = ObterValorInteiroCombo(cmbProduto);
            if (!produto.HasValue)
            {
                MessageBox.Show("Seleciona o produto.", "Produto obrigatorio", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            int quantidade;
            if (!int.TryParse(txtQuantidade.Text.Trim(), out quantidade) || quantidade <= 0)
            {
                MessageBox.Show("A quantidade deve ser um inteiro positivo.", "Valor invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            decimal preco;
            if (!decimal.TryParse(txtPrecoUnitario.Text.Trim(), out preco) || preco < 0)
            {
                MessageBox.Show("O preco unitario deve ser numerico e nao pode ser negativo.", "Valor invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            detalhe = new DetalheCompraProntoVestir
            {
                IdDetalhes = idDetalhe,
                Compra = idCompra,
                ProdutoPronto = produto.Value,
                Quantidade = quantidade,
                PrecoUnitario = preco
            };

            return true;
        }

        private void DgvDetalhes_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            DataGridViewRow row = dgvDetalhes.Rows[e.RowIndex];
            txtDetalheId.Text = row.Cells["ID"].Value.ToString();
            cmbProduto.SelectedValue = Convert.ToInt32(row.Cells["ProdutoID"].Value);
            txtQuantidade.Text = row.Cells["Quantidade"].Value.ToString();
            txtPrecoUnitario.Text = Convert.ToDecimal(row.Cells["PrecoUnitario"].Value).ToString("0.00");
        }

        private void DgvDetalhes_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvDetalhes.ClearSelection();
        }

        private void LimparCampos()
        {
            txtId.Clear();
            cmbCliente.SelectedIndex = clienteInicial.HasValue ? cmbCliente.SelectedIndex : 0;
            if (clienteInicial.HasValue)
                cmbCliente.SelectedValue = clienteInicial.Value;
            dtpDataCompra.Value = DateTime.Today;
            cmbMetodoPagamento.SelectedIndex = 0;
            txtValorTotal.Text = "0,00";
            dgvCompras.ClearSelection();
            dgvDetalhes.DataSource = null;
            LimparCamposDetalhe();
        }

        private void LimparCamposDetalhe()
        {
            txtDetalheId.Clear();
            cmbProduto.SelectedIndex = -1;
            txtQuantidade.Clear();
            txtPrecoUnitario.Clear();
            if (dgvDetalhes != null)
                dgvDetalhes.ClearSelection();
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
            MessageBox.Show(mensagem + "\n\n" + ex.Message, "Erro de base de dados", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }
}
