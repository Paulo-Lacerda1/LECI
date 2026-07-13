using NewModusApp.Models;
using NewModusApp.Repositories;
using NewModusApp.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmNovaCompraProntoVestir : Form
    {
        private readonly ProntoVestirRepository repository = new ProntoVestirRepository();
        private readonly BindingList<CompraItemDraft> itens = new BindingList<CompraItemDraft>();
        private readonly int? clienteInicial;

        private DataTable clientes;
        private DataTable produtos;

        public FrmNovaCompraProntoVestir()
            : this(null)
        {
        }

        public FrmNovaCompraProntoVestir(int? cliente)
        {
            clienteInicial = cliente;
            InitializeComponent();
            dgvItens.DataSource = itens;
            Load += FrmNovaCompraProntoVestir_Load;
        }

        private void FrmNovaCompraProntoVestir_Load(object sender, EventArgs e)
        {
            CarregarDadosBase();
            AtualizarTotal();
        }

        private void CarregarDadosBase()
        {
            try
            {
                clientes = repository.ListarClientes();
                DataTable clientesCompra = clientes.Copy();
                DataRow anonimo = clientesCompra.NewRow();
                anonimo["ID"] = DBNull.Value;
                anonimo["Nome"] = "Cliente ocasional";
                clientesCompra.Rows.InsertAt(anonimo, 0);

                cmbCliente.DataSource = clientesCompra;
                cmbCliente.DisplayMember = "Nome";
                cmbCliente.ValueMember = "ID";
                cmbCliente.SelectedIndex = 0;

                if (clienteInicial.HasValue)
                    cmbCliente.SelectedValue = clienteInicial.Value;

                cmbMetodoPagamento.Items.Clear();
                cmbMetodoPagamento.Items.AddRange(new object[] { "Cartão", "MBWay", "Dinheiro", "Transferência" });
                cmbMetodoPagamento.SelectedIndex = 0;

                produtos = repository.ListarProdutosDetalhados();
                CarregarTiposProduto();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os dados da compra.", ex);
            }
        }

        private void BtnAdicionar_Click(object sender, EventArgs e)
        {
            int? produto = ObterValorInteiroCombo(cmbTamanho);
            if (!produto.HasValue)
            {
                MessageBox.Show("Seleciona o produto.", "Produto obrigatorio", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                cmbTipoProduto.Focus();
                return;
            }

            if (nudPreco.Value <= 0)
            {
                MessageBox.Show("Indica um preco unitario superior a zero.", "Preco invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                nudPreco.Focus();
                return;
            }

            DataRowView rowSelecionada = ObterProdutoSelecionado();
            int stockDisponivel = Convert.ToInt32(rowSelecionada["Stock"]);
            int quantidadePedida = Convert.ToInt32(nudQuantidade.Value);

            int qtdNoCarrinho = 0;
            foreach (var itemDraft in itens)
            {
                if (itemDraft.ProdutoId == produto.Value)
                    qtdNoCarrinho += itemDraft.Quantidade;
            }

            if (qtdNoCarrinho + quantidadePedida > stockDisponivel)
            {
                MessageBox.Show($"Stock insuficiente! Apenas restam {stockDisponivel} unidades deste produto (e tu já tens {qtdNoCarrinho} no carrinho).",
                                "Aviso de Stock", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            itens.Add(new CompraItemDraft
            {
                ProdutoId = produto.Value,
                Produto = cmbTipoProduto.Text,
                Tamanho = cmbTamanho.Text,
                Quantidade = Convert.ToInt32(nudQuantidade.Value),
                PrecoUnitario = nudPreco.Value
            });

            LimparProduto();
            AtualizarTotal();
        }

        private void BtnRemover_Click(object sender, EventArgs e)
        {
            if (dgvItens.CurrentRow == null)
                return;

            CompraItemDraft item = dgvItens.CurrentRow.DataBoundItem as CompraItemDraft;
            if (item == null)
                return;

            itens.Remove(item);
            AtualizarTotal();
        }

        private void BtnFinalizar_Click(object sender, EventArgs e)
        {
            if (cmbMetodoPagamento.SelectedIndex < 0)
            {
                MessageBox.Show("Seleciona o metodo de pagamento.", "Campo obrigatorio", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (itens.Count == 0)
            {
                MessageBox.Show("Adiciona pelo menos um produto a compra.", "Compra vazia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            CompraProntoVestir compra = new CompraProntoVestir
            {
                DataCompra = DateTime.Today,
                MetodoPagamento = cmbMetodoPagamento.Text,
                Cliente = ObterValorInteiroCombo(cmbCliente),
                ValorTotal = CalcularTotal()
            };

            List<DetalheCompraProntoVestir> detalhes = new List<DetalheCompraProntoVestir>();
            foreach (CompraItemDraft item in itens)
                detalhes.Add(item.CriarModelo());

            try
            {
                int idCompra = repository.CriarComDetalhes(compra, detalhes);

                DialogResult resposta = MessageBox.Show(
                    "Compra registada com sucesso. ID: " + idCompra + "\n\nQueres ver o historico?",
                    "Sucesso",
                    MessageBoxButtons.YesNo,
                    MessageBoxIcon.Information
                );

                if (resposta == DialogResult.Yes)
                {
                    FrmPrincipal principal = ObterFrmPrincipal();
                    if (principal != null)
                        principal.AbrirHistoricoProntoVestir(compra.Cliente);
                    return;
                }

                LimparCompra();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel registar a compra.", ex);
            }
        }

        private void CarregarTiposProduto()
        {
            DataTable tipos = new DataTable();
            tipos.Columns.Add("Nome", typeof(string));

            HashSet<string> nomes = new HashSet<string>(StringComparer.CurrentCultureIgnoreCase);
            foreach (DataRow row in produtos.Rows)
            {
                string nome = Convert.ToString(row["Nome"]);
                if (nome != "" && nomes.Add(nome))
                    tipos.Rows.Add(nome);
            }

            cmbTipoProduto.DataSource = tipos;
            cmbTipoProduto.DisplayMember = "Nome";
            cmbTipoProduto.ValueMember = "Nome";
            cmbTipoProduto.SelectedIndex = tipos.Rows.Count > 0 ? 0 : -1;
            CarregarTamanhosProduto();
        }

        private void CarregarTamanhosProduto()
        {
            DataTable tamanhos = new DataTable();
            tamanhos.Columns.Add("ID", typeof(int));
            tamanhos.Columns.Add("Tamanho", typeof(string));
            tamanhos.Columns.Add("Preco", typeof(decimal));
            tamanhos.Columns.Add("Stock", typeof(int));

            string tipoProduto = Convert.ToString(cmbTipoProduto.SelectedValue);
            if (cmbTipoProduto.SelectedValue is DataRowView)
                tipoProduto = "";

            foreach (DataRow row in produtos.Rows)
            {
                if (!string.Equals(Convert.ToString(row["Nome"]), tipoProduto, StringComparison.CurrentCultureIgnoreCase))
                    continue;

                tamanhos.Rows.Add(
                    Convert.ToInt32(row["ID"]),
                    Convert.ToString(row["Tamanho"]),
                    Convert.ToDecimal(row["Preco"]),
                    Convert.ToInt32(row["Stock"])
                );
            }

            cmbTamanho.DataSource = tamanhos;
            cmbTamanho.DisplayMember = "Tamanho";
            cmbTamanho.ValueMember = "ID";
            cmbTamanho.SelectedIndex = tamanhos.Rows.Count > 0 ? 0 : -1;
            AtualizarPrecoProdutoSelecionado();
        }

        private DataRowView ObterProdutoSelecionado()
        {
            return cmbTamanho.SelectedItem as DataRowView;
        }

        private void CmbTipoProduto_SelectedIndexChanged(object sender, EventArgs e)
        {
            CarregarTamanhosProduto();
        }

        private void CmbTamanho_SelectedIndexChanged(object sender, EventArgs e)
        {
            AtualizarPrecoProdutoSelecionado();
        }

        private void AtualizarPrecoProdutoSelecionado()
        {
            DataRowView row = ObterProdutoSelecionado();
            if (row == null)
                return;

            nudPreco.Value = Convert.ToDecimal(row["Preco"]);

            // Feature QoL: Ajustar máximo da caixa de quantidade ao stock
            int stockDisponivel = Convert.ToInt32(row["Stock"]);
            if (stockDisponivel > 0)
            {
                nudQuantidade.Maximum = stockDisponivel; // O WinForms ajusta o Value nativamente
                nudQuantidade.Enabled = true;
            }
            else
            {
                nudQuantidade.Maximum = 1;
                nudQuantidade.Value = 0;
                nudQuantidade.Enabled = false; // Bloqueia o controlo se não houver stock
            }
        }

        private int? ObterValorInteiroCombo(ComboBox combo)
        {
            object valor = combo.SelectedValue;
            if (valor == null || valor == DBNull.Value || valor is DataRowView)
                return null;

            return Convert.ToInt32(valor);
        }

        private void DgvItens_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvItens.AplicarEstiloModus();

            if (dgvItens.Columns.Contains("ProdutoId"))
                dgvItens.Columns["ProdutoId"].Visible = false;

            if (dgvItens.Columns.Contains("PrecoUnitario"))
                dgvItens.Columns["PrecoUnitario"].DefaultCellStyle.Format = "N2";

            if (dgvItens.Columns.Contains("Subtotal"))
                dgvItens.Columns["Subtotal"].DefaultCellStyle.Format = "N2";

            dgvItens.ClearSelection();
        }

        private void AtualizarTotal()
        {
            lblTotal.Text = "Total: " + CalcularTotal().ToString("N2");
        }

        private decimal CalcularTotal()
        {
            decimal total = 0;
            foreach (CompraItemDraft item in itens)
                total += item.Subtotal;

            return total;
        }

        private void LimparProduto()
        {
            cmbTipoProduto.SelectedIndex = cmbTipoProduto.Items.Count > 0 ? 0 : -1;
            cmbTamanho.SelectedIndex = cmbTamanho.Items.Count > 0 ? 0 : -1;
            nudQuantidade.Value = 1;
        }

        private void LimparCompra()
        {
            itens.Clear();
            cmbMetodoPagamento.SelectedIndex = 0;
            if (clienteInicial.HasValue)
                cmbCliente.SelectedValue = clienteInicial.Value;
            else
                cmbCliente.SelectedIndex = 0;
            LimparProduto();
            AtualizarTotal();
        }

        private void BtnLimpar_Click(object sender, EventArgs e)
        {
            LimparProduto();
        }

        private void BtnVoltar_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirProntoVestir(clienteInicial);
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

        private class CompraItemDraft
        {
            public int ProdutoId { get; set; }
            public string Produto { get; set; }
            public string Tamanho { get; set; }
            public int Quantidade { get; set; }
            public decimal PrecoUnitario { get; set; }
            public decimal Subtotal
            {
                get { return Quantidade * PrecoUnitario; }
            }

            public DetalheCompraProntoVestir CriarModelo()
            {
                return new DetalheCompraProntoVestir
                {
                    ProdutoPronto = ProdutoId,
                    Quantidade = Quantidade,
                    PrecoUnitario = PrecoUnitario
                };
            }
        }
    }
}
