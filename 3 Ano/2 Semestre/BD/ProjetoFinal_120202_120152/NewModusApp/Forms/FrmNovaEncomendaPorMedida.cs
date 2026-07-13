using NewModusApp.Models;
using NewModusApp.Repositories;
using NewModusApp.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmNovaEncomendaPorMedida : Form
    {
        private readonly EncomendasRepository encomendasRepository = new EncomendasRepository();
        private readonly BindingList<PedidoItemDraft> itens = new BindingList<PedidoItemDraft>();
        private readonly int? clienteInicial;

        private DataTable clientes;
        private DataTable modelos;

        public FrmNovaEncomendaPorMedida()
            : this(null)
        {
        }

        public FrmNovaEncomendaPorMedida(int? cliente)
        {
            clienteInicial = cliente;
            InitializeComponent();
        }

        private void FrmNovaEncomendaPorMedida_Load(object sender, EventArgs e)
        {
            dgvItens.DataSource = itens;
            CarregarDadosBase();
            MostrarPassoCliente();
        }

        private void CarregarDadosBase()
        {
            try
            {
                clientes = encomendasRepository.ListarClientes();
                cmbCliente.DataSource = clientes;
                cmbCliente.DisplayMember = "Nome";
                cmbCliente.ValueMember = "ID";
                cmbCliente.SelectedIndex = -1;

                modelos = encomendasRepository.ListarModelos();
                DataRow linhaSemModelo = modelos.NewRow();
                linhaSemModelo["ID"] = DBNull.Value;
                linhaSemModelo["Nome"] = "Sem modelo";
                linhaSemModelo["TipoPeca"] = "";
                modelos.Rows.InsertAt(linhaSemModelo, 0);

                cmbModelo.DataSource = modelos;
                cmbModelo.DisplayMember = "Nome";
                cmbModelo.ValueMember = "ID";
                cmbModelo.SelectedIndex = 0;

                if (clienteInicial.HasValue)
                    cmbCliente.SelectedValue = clienteInicial.Value;
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os dados para criar o pedido.", ex);
            }
        }

        private void MostrarPassoCliente()
        {
            lblPasso.Text = "Passo 1 de 2: cliente e data de entrega";
            panelPassoCliente.Visible = true;
            panelPassoProdutos.Visible = false;
        }

        private void MostrarPassoProdutos()
        {
            lblPasso.Text = "Passo 2 de 2: adicionar produtos ao pedido";
            panelPassoCliente.Visible = false;
            panelPassoProdutos.Visible = true;
            CarregarPerfisMedida();
            AtualizarTotal();
        }

        private void BtnContinuar_Click(object sender, EventArgs e)
        {
            int? cliente = ObterClienteSelecionado();
            if (!cliente.HasValue)
            {
                MessageBox.Show(
                    "Escolhe um cliente existente da lista.",
                    "Cliente obrigatorio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                cmbCliente.Focus();
                return;
            }

            if (dtpEntrega.Value.Date < DateTime.Today)
            {
                MessageBox.Show(
                    "A data de entrega nao pode ser anterior a data de hoje.",
                    "Data invalida",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                dtpEntrega.Focus();
                return;
            }

            MostrarPassoProdutos();
        }

        private void BtnAdicionarItem_Click(object sender, EventArgs e)
        {
            int? perfil = ObterValorInteiroCombo(cmbPerfilMedida);
            if (!perfil.HasValue)
            {
                MessageBox.Show(
                    "Escolhe um perfil de medida para este produto.",
                    "Perfil obrigatorio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                cmbPerfilMedida.Focus();
                return;
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
                return;
            }

            if (nudPreco.Value <= 0)
            {
                MessageBox.Show(
                    "Indica um preco superior a zero.",
                    "Preco invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                nudPreco.Focus();
                return;
            }

            decimal precoVenda = nudPreco.Value;
            decimal custoMaoObra = nudCustoMaoObra.Value;

            PedidoItemDraft item = new PedidoItemDraft
            {
                PerfilMedidaId = perfil.Value,
                Perfil = cmbPerfilMedida.Text,
                ModeloId = ObterValorInteiroCombo(cmbModelo),
                Modelo = cmbModelo.Text,
                Tamanho = Convert.ToInt32(nudTamanho.Value),
                Preco = precoVenda,
                CustoMaoObra = custoMaoObra,
                TipoPeca = tipoPeca,
                CustoProducao = null,
                Descricao = txtDescricao.Text.Trim()
            };

            using (FrmMateriaisPedidoPopup popup = new FrmMateriaisPedidoPopup())
            {
                if (popup.ShowDialog(this) != DialogResult.OK)
                    return;

                if (popup.TecidoSelecionado != null)
                    item.Tecido = popup.TecidoSelecionado;

                if (popup.MaterialSelecionado != null)
                    item.Material = popup.MaterialSelecionado;

                item.MateriaisIgnorados = popup.Ignorado;
            }

            nudPreco.Value = precoVenda;
            nudCustoMaoObra.Value = custoMaoObra;
            item.RecalcularCustoProducao();

            itens.Add(item);
            LimparCamposItem();
            AtualizarTotal();
        }

        private void BtnRemoverItem_Click(object sender, EventArgs e)
        {
            if (dgvItens.CurrentRow == null)
                return;

            PedidoItemDraft item = dgvItens.CurrentRow.DataBoundItem as PedidoItemDraft;
            if (item == null)
                return;

            itens.Remove(item);
            AtualizarTotal();
        }

        private void BtnFinalizar_Click(object sender, EventArgs e)
        {
            int? cliente = ObterClienteSelecionado();
            if (!cliente.HasValue)
            {
                MostrarPassoCliente();
                return;
            }

            if (itens.Count == 0)
            {
                MessageBox.Show(
                    "Adiciona pelo menos um produto ao pedido.",
                    "Pedido vazio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return;
            }

            Encomenda encomenda = new Encomenda
            {
                Cliente = cliente.Value,
                DataEncomenda = DateTime.Today,
                DataPrevistaEntrega = dtpEntrega.Value.Date,
                Estado = "Pendente",
                ValorTotal = CalcularTotal()
            };

            List<EncomendaItem> itensParaGuardar = new List<EncomendaItem>();
            foreach (PedidoItemDraft item in itens)
                itensParaGuardar.Add(item.CriarModelo());

            try
            {
                int idEncomenda = encomendasRepository.CriarComItens(encomenda, itensParaGuardar);

                DialogResult resposta = MessageBox.Show(
                    "Pedido criado com sucesso. ID: " + idEncomenda + "\n\nQueres ver a lista de pedidos?",
                    "Sucesso",
                    MessageBoxButtons.YesNo,
                    MessageBoxIcon.Information
                );

                if (resposta == DialogResult.Yes)
                {
                    FrmPrincipal principal = ObterFrmPrincipal();
                    if (principal != null)
                        principal.AbrirPedidosPendentesPorMedida(cliente.Value);
                    return;
                }

                LimparPedido();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel guardar o pedido.", ex);
            }
        }

        private void CarregarPerfisMedida()
        {
            int? cliente = ObterClienteSelecionado();
            if (!cliente.HasValue)
                return;

            try
            {
                DataTable perfis = encomendasRepository.ListarPerfisMedida(cliente.Value);
                cmbPerfilMedida.DataSource = perfis;
                cmbPerfilMedida.DisplayMember = "Perfil";
                cmbPerfilMedida.ValueMember = "ID";
                cmbPerfilMedida.SelectedIndex = perfis.Rows.Count > 0 ? 0 : -1;

                if (perfis.Rows.Count == 0)
                {
                    MessageBox.Show(
                        "Este cliente ainda nao tem perfis de medida. Cria um perfil antes de adicionar produtos.",
                        "Perfil em falta",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Information
                    );
                }
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar os perfis de medida.", ex);
            }
        }

        private int? ObterClienteSelecionado()
        {
            int? valor = ObterValorInteiroCombo(cmbCliente);
            if (valor.HasValue)
                return valor;

            string nome = cmbCliente.Text.Trim();
            if (clientes == null || nome == "")
                return null;

            foreach (DataRow row in clientes.Rows)
            {
                if (string.Equals(Convert.ToString(row["Nome"]), nome, StringComparison.CurrentCultureIgnoreCase))
                {
                    cmbCliente.SelectedValue = Convert.ToInt32(row["ID"]);
                    return Convert.ToInt32(row["ID"]);
                }
            }

            return null;
        }

        private int? ObterValorInteiroCombo(ComboBox comboBox)
        {
            object valor = comboBox.SelectedValue;

            if (valor == null || valor == DBNull.Value || valor is DataRowView)
                return null;

            return Convert.ToInt32(valor);
        }

        private void CmbModelo_SelectedIndexChanged(object sender, EventArgs e)
        {
            DataRowView row = cmbModelo.SelectedItem as DataRowView;
            if (row == null)
                return;

            string tipoPeca = Convert.ToString(row["TipoPeca"]);
            if (tipoPeca.Length > 15)
                tipoPeca = tipoPeca.Substring(0, 15);

            txtTipoPeca.Text = tipoPeca;
        }

        private void DgvItens_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvItens.AplicarEstiloModus();

            string[] ocultas = { "PerfilMedidaId", "ModeloId", "Tecido", "Material", "MateriaisIgnorados" };
            foreach (string coluna in ocultas)
            {
                if (dgvItens.Columns.Contains(coluna))
                    dgvItens.Columns[coluna].Visible = false;
            }

            if (dgvItens.Columns.Contains("Preco"))
                dgvItens.Columns["Preco"].DefaultCellStyle.Format = "N2";

            if (dgvItens.Columns.Contains("CustoMaoObra"))
            {
                dgvItens.Columns["CustoMaoObra"].HeaderText = "Mao de Obra";
                dgvItens.Columns["CustoMaoObra"].DefaultCellStyle.Format = "N2";
            }

            if (dgvItens.Columns.Contains("CustoProducao"))
            {
                dgvItens.Columns["CustoProducao"].HeaderText = "Custo producao";
                dgvItens.Columns["CustoProducao"].DefaultCellStyle.Format = "N2";
            }

            if (dgvItens.Columns.Contains("Materiais"))
            {
                dgvItens.Columns["Materiais"].HeaderText = "Materiais";
                dgvItens.Columns["Materiais"].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter;
                dgvItens.Columns["Materiais"].MinimumWidth = 75;
            }

            dgvItens.ClearSelection();
        }

        private void AtualizarTotal()
        {
            lblTotal.Text = "Total: " + CalcularTotal().ToString("N2");
        }

        private decimal CalcularTotal()
        {
            decimal total = 0;
            foreach (PedidoItemDraft item in itens)
                total += item.Preco;

            return total;
        }

        private void LimparCamposItem()
        {
            cmbPerfilMedida.SelectedIndex = cmbPerfilMedida.Items.Count > 0 ? 0 : -1;
            cmbModelo.SelectedIndex = cmbModelo.Items.Count > 0 ? 0 : -1;
            txtTipoPeca.Clear();
            nudTamanho.Value = 40;
            nudPreco.Value = 0;
            nudCustoMaoObra.Value = 0;
            txtDescricao.Clear();
        }

        private void LimparPedido()
        {
            itens.Clear();
            dtpEntrega.Value = DateTime.Today.AddDays(7);
            if (!clienteInicial.HasValue)
                cmbCliente.SelectedIndex = -1;
            LimparCamposItem();
            AtualizarTotal();
            MostrarPassoCliente();
        }

        private void BtnLimpar_Click(object sender, EventArgs e)
        {
            LimparCamposItem();
        }

        private void BtnVoltar_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirEncomendasPorMedida(clienteInicial);
        }

        private void BtnVoltarProdutos_Click(object sender, EventArgs e)
        {
            MostrarPassoCliente();
        }

        private void BtnSetaVoltar_Click(object sender, EventArgs e)
        {
            if (panelPassoProdutos.Visible)
            {
                MostrarPassoCliente();
                return;
            }

            BtnVoltar_Click(sender, e);
        }

        private void BtnPerfis_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirMedidas(ObterClienteSelecionado());
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

        private class PedidoItemDraft
        {
            public int PerfilMedidaId { get; set; }
            public int? ModeloId { get; set; }
            public string Perfil { get; set; }
            public string Modelo { get; set; }
            public int Tamanho { get; set; }
            public decimal Preco { get; set; }
            public decimal CustoMaoObra { get; set; }
            public string TipoPeca { get; set; }
            public decimal? CustoProducao { get; set; }
            public string Descricao { get; set; }
            public ItemEncomendaTecido Tecido { get; set; }
            public ItemEncomendaMaterial Material { get; set; }
            public bool MateriaisIgnorados { get; set; }
            public string Materiais
            {
                get
                {
                    return Tecido != null || Material != null ? "✓" : "?";
                }
            }

            public EncomendaItem CriarModelo()
            {
                EncomendaItem item = new EncomendaItem
                {
                    PerfilMedida = PerfilMedidaId,
                    Modelo = ModeloId,
                    Tamanho = Tamanho,
                    Preco = Preco,
                    CustoMaoObra = CustoMaoObra,
                    TipoPeca = TipoPeca,
                    CustoProducao = CustoProducao,
                    DescricaoPersonalizacao = Descricao
                };

                if (Tecido != null)
                    item.Tecidos.Add(Tecido);

                if (Material != null)
                    item.Materiais.Add(Material);

                return item;
            }

            public void RecalcularCustoProducao()
            {
                CustoProducao = CalcularCustoProducao();
            }

            public decimal CalcularCustoProducao()
            {
                decimal custo = 0;

                if (Tecido != null)
                {
                    decimal precoCobrado = Tecido.PrecoCobrado ?? 0;
                    custo += Tecido.MetrosUsados * precoCobrado;
                }

                if (Material != null)
                {
                    decimal precoCobrado = Material.PrecoCobrado ?? 0;
                    custo += Material.QuantidadeUsada * precoCobrado;
                }

                return custo;
            }
        }
    }
}
