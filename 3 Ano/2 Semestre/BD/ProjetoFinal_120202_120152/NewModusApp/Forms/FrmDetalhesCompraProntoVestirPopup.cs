using NewModusApp.Models;
using NewModusApp.Repositories;
using NewModusApp.Utils;
using System;
using System.Data;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmDetalhesCompraProntoVestirPopup : Form
    {
        private readonly ProntoVestirRepository repository = new ProntoVestirRepository();
        private readonly int idCompra;
        private DataTable produtos;

        public bool Alterou { get; private set; }

        public FrmDetalhesCompraProntoVestirPopup(int compra)
        {
            idCompra = compra;
            InitializeComponent();
            CarregarProdutos();
            CarregarDetalhes();
        }

        private void CarregarProdutos()
        {
            try
            {
                produtos = repository.ListarProdutosDetalhados();
                cmbProduto.DataSource = produtos;
                cmbProduto.DisplayMember = produtos.Columns.Contains("Produto") ? "Produto" : "Nome";
                cmbProduto.ValueMember = "ID";
                cmbProduto.SelectedIndex = -1;
                LimparCamposProduto();
                txtPrecoUnitario.Clear();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel carregar os produtos.", ex);
            }
        }

        private void CarregarDetalhes()
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
                MostrarErro("Nao foi possivel carregar os produtos da venda.", ex);
            }
        }

        private void ConfigurarColunasDetalhes()
        {
            string[] ocultas = { "CompraID", "ProdutoID", "Categoria" };
            foreach (string coluna in ocultas)
            {
                if (dgvDetalhes.Columns.Contains(coluna))
                    dgvDetalhes.Columns[coluna].Visible = false;
            }

            if (dgvDetalhes.Columns.Contains("PrecoUnitario"))
            {
                dgvDetalhes.Columns["PrecoUnitario"].HeaderText = "Preco unit.";
                dgvDetalhes.Columns["PrecoUnitario"].DefaultCellStyle.Format = "N2";
            }

            if (dgvDetalhes.Columns.Contains("Produto"))
                dgvDetalhes.Columns["Produto"].HeaderText = "Nome";

            if (dgvDetalhes.Columns.Contains("Subtotal"))
                dgvDetalhes.Columns["Subtotal"].DefaultCellStyle.Format = "N2";
        }

        private void BtnAdicionar_Click(object sender, EventArgs e)
        {
            DetalheCompraProntoVestir detalhe;
            if (!TentarLerDetalhe(out detalhe, false))
                return;

            try
            {
                int idDetalhe = repository.CriarDetalhe(detalhe);
                MessageBox.Show("Produto adicionado com sucesso. ID: " + idDetalhe, "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                Alterou = true;
                LimparCampos();
                CarregarDetalhes();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel adicionar o produto.", ex);
            }
        }

        private void BtnEditar_Click(object sender, EventArgs e)
        {
            DetalheCompraProntoVestir detalhe;
            if (!TentarLerDetalhe(out detalhe, true))
                return;

            try
            {
                repository.AtualizarDetalheCompleto(detalhe);
                MessageBox.Show("Produto atualizado com sucesso.", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                Alterou = true;
                LimparCampos();
                CarregarProdutos();
                CarregarDetalhes();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel atualizar o produto.", ex);
            }
        }

        private void BtnApagar_Click(object sender, EventArgs e)
        {
            int idDetalhe;
            if (!int.TryParse(txtDetalheId.Text, out idDetalhe))
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
                Alterou = true;
                LimparCampos();
                CarregarDetalhes();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel remover o produto.", ex);
            }
        }

        private void BtnConfirmar_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.OK;
            Close();
        }

        private bool TentarLerDetalhe(out DetalheCompraProntoVestir detalhe, bool exigeId)
        {
            detalhe = null;

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

            string nomeProduto = txtProdutoNome.Text.Trim();
            if (string.IsNullOrWhiteSpace(nomeProduto) || nomeProduto.Length > 30)
            {
                MessageBox.Show("O nome do produto e obrigatorio e pode ter no maximo 30 caracteres.", "Valor invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            string tamanho = txtTamanho.Text.Trim();
            if (string.IsNullOrWhiteSpace(tamanho) || tamanho.Length > 10)
            {
                MessageBox.Show("O tamanho e obrigatorio e pode ter no maximo 10 caracteres.", "Valor invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return false;
            }

            string cor = txtCor.Text.Trim();
            if (string.IsNullOrWhiteSpace(cor) || cor.Length > 10)
            {
                MessageBox.Show("A cor e obrigatoria e pode ter no maximo 10 caracteres.", "Valor invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
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
                ProdutoNome = nomeProduto,
                Tamanho = tamanho,
                Cor = cor,
                Quantidade = quantidade,
                PrecoUnitario = preco
            };

            return true;
        }

        private int? ObterValorInteiroCombo(ComboBox combo)
        {
            object valor = combo.SelectedValue;
            if (valor == null || valor == DBNull.Value || valor is DataRowView)
                return null;

            return Convert.ToInt32(valor);
        }

        private void CmbProduto_SelectedIndexChanged(object sender, EventArgs e)
        {
            DataRowView produto = cmbProduto.SelectedItem as DataRowView;
            if (produto == null)
            {
                LimparCamposProduto();
                return;
            }

            txtProdutoNome.Text = Convert.ToString(produto["Nome"]);
            txtTamanho.Text = Convert.ToString(produto["Tamanho"]);
            txtCor.Text = Convert.ToString(produto["Cor"]);

            if (string.IsNullOrWhiteSpace(txtDetalheId.Text) && produtos.Columns.Contains("Preco"))
                txtPrecoUnitario.Text = Convert.ToDecimal(produto["Preco"]).ToString("0.00");
        }

        private void DgvDetalhes_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            DataGridViewRow row = dgvDetalhes.Rows[e.RowIndex];
            txtDetalheId.Text = row.Cells["ID"].Value.ToString();
            cmbProduto.SelectedValue = Convert.ToInt32(row.Cells["ProdutoID"].Value);
            txtProdutoNome.Text = Convert.ToString(row.Cells["Produto"].Value);
            txtTamanho.Text = Convert.ToString(row.Cells["Tamanho"].Value);
            txtCor.Text = Convert.ToString(row.Cells["Cor"].Value);
            txtQuantidade.Text = row.Cells["Quantidade"].Value.ToString();
            txtPrecoUnitario.Text = Convert.ToDecimal(row.Cells["PrecoUnitario"].Value).ToString("0.00");
        }

        private void DgvDetalhes_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvDetalhes.ClearSelection();
        }

        private void LimparCampos()
        {
            txtDetalheId.Clear();
            cmbProduto.SelectedIndex = -1;
            LimparCamposProduto();
            txtQuantidade.Clear();
            txtPrecoUnitario.Clear();
            dgvDetalhes.ClearSelection();
        }

        private void LimparCamposProduto()
        {
            txtProdutoNome.Clear();
            txtTamanho.Clear();
            txtCor.Clear();
        }

        private void MostrarErro(string mensagem, Exception ex)
        {
            MessageBox.Show(mensagem + "\n\n" + ex.Message, "Erro de base de dados", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }
}
