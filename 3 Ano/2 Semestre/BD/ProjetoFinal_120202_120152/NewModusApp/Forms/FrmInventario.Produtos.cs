using NewModusApp.Repositories;
using NewModusApp.Utils;
using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmInventario
    {
        private readonly ProntoVestirRepository _prontoVestirRepository = new ProntoVestirRepository();

        private void CarregarGrelhaProdutos()
        {
            try
            {
                DataTable dt = _prontoVestirRepository.ListarProdutosProntos();
                dgvProdutos.DataSource = dt;
                StandardizeColumnsProdutos();
                PreencherFiltrosProdutos(dt);
                PreencherCombosProdutos(dt);
                dgvProdutos.AplicarEstiloModus();
                dgvProdutos.ClearSelection();
                LimparCamposProduto();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao carregar os produtos: {ex.Message}", "Erro de Base de Dados", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void StandardizeColumnsProdutos()
        {
            if (dgvProdutos.DataSource == null)
                return;

            foreach (DataGridViewColumn col in dgvProdutos.Columns)
            {
                switch (col.Name)
                {
                    case "ID":
                        col.Visible = false;
                        break;
                    case "Codigo":
                        col.HeaderText = "Código";
                        col.Width = 70;
                        break;
                    case "Nome":
                        col.HeaderText = "Nome";
                        col.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
                        break;
                    case "Tamanho":
                        col.HeaderText = "Tamanho";
                        col.Width = 80;
                        break;
                    case "Cor":
                        col.HeaderText = "Cor";
                        col.Width = 90;
                        break;
                    case "Preco":
                        col.HeaderText = "Preço";
                        col.Width = 80;
                        col.DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleRight;
                        break;
                    case "Stock":
                        col.HeaderText = "Stock";
                        col.Width = 65;
                        col.DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter;
                        break;
                    case "Categoria":
                        col.HeaderText = "Categoria";
                        col.Width = 120;
                        break;
                }
            }
        }

        private void PreencherFiltrosProdutos(DataTable dt)
        {
            clbFiltroTamanho.Items.Clear();
            clbFiltroCorProduto.Items.Clear();
            clbFiltroCategoria.Items.Clear();

            HashSet<string> tamanhos = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
            HashSet<string> cores = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
            HashSet<string> categorias = new HashSet<string>(StringComparer.OrdinalIgnoreCase);

            foreach (DataRow row in dt.Rows)
            {
                string tamanho = ObterValorColuna(row, "Tamanho");
                string cor = ObterValorColuna(row, "Cor");
                string categoria = ObterValorColuna(row, "Categoria");

                if (!string.IsNullOrWhiteSpace(tamanho)) tamanhos.Add(tamanho.Trim());
                if (!string.IsNullOrWhiteSpace(cor)) cores.Add(cor.Trim());
                if (!string.IsNullOrWhiteSpace(categoria)) categorias.Add(categoria.Trim());
            }

            foreach (string t in tamanhos) clbFiltroTamanho.Items.Add(t);
            foreach (string c in cores) clbFiltroCorProduto.Items.Add(c);
            foreach (string cat in categorias) clbFiltroCategoria.Items.Add(cat);
        }

        private void AplicarFiltrosProdutos()
        {
            if (!(dgvProdutos.DataSource is DataTable dt))
                return;

            string colNome = ResolveColumnName(dt, "Nome");
            string colPreco = ResolveColumnName(dt, "Preco");
            string colTamanho = ResolveColumnName(dt, "Tamanho");
            string colCor = ResolveColumnName(dt, "Cor");
            string colCategoria = ResolveColumnName(dt, "Categoria");

            List<string> filtros = new List<string>();

            if (!string.IsNullOrWhiteSpace(txtFiltroNomeProduto.Text) && !string.IsNullOrWhiteSpace(colNome))
            {
                filtros.Add($"{colNome} LIKE '%{txtFiltroNomeProduto.Text.Replace("'", "''")}%'");
            }

            if (!string.IsNullOrWhiteSpace(txtFiltroPrecoMaxProduto.Text)
                && decimal.TryParse(txtFiltroPrecoMaxProduto.Text, NumberStyles.Any, CultureInfo.InvariantCulture, out decimal precoMax)
                && !string.IsNullOrWhiteSpace(colPreco))
            {
                filtros.Add($"{colPreco} <= {precoMax.ToString(CultureInfo.InvariantCulture)}");
            }

            if (clbFiltroTamanho.CheckedItems.Count > 0 && !string.IsNullOrWhiteSpace(colTamanho))
            {
                List<string> selecionados = new List<string>();
                foreach (var item in clbFiltroTamanho.CheckedItems)
                    selecionados.Add($"'{item.ToString().Replace("'", "''")}'");
                filtros.Add($"{colTamanho} IN ({string.Join(", ", selecionados)})");
            }

            if (clbFiltroCorProduto.CheckedItems.Count > 0 && !string.IsNullOrWhiteSpace(colCor))
            {
                List<string> selecionados = new List<string>();
                foreach (var item in clbFiltroCorProduto.CheckedItems)
                    selecionados.Add($"'{item.ToString().Replace("'", "''")}'");
                filtros.Add($"{colCor} IN ({string.Join(", ", selecionados)})");
            }

            if (clbFiltroCategoria.CheckedItems.Count > 0 && !string.IsNullOrWhiteSpace(colCategoria))
            {
                List<string> selecionados = new List<string>();
                foreach (var item in clbFiltroCategoria.CheckedItems)
                    selecionados.Add($"'{item.ToString().Replace("'", "''")}'");
                filtros.Add($"{colCategoria} IN ({string.Join(", ", selecionados)})");
            }

            string filtroFinal = string.Join(" AND ", filtros);

            try
            {
                dt.DefaultView.RowFilter = filtroFinal;
            }
            catch (Exception ex)
            {
                dt.DefaultView.RowFilter = string.Empty;
                MessageBox.Show($"Filtro inválido: {ex.Message}", "Erro de Filtro", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }

        private void dgvProdutos_CellFormatting(object sender, DataGridViewCellFormattingEventArgs e)
        {
            if (e.RowIndex < 0 || dgvProdutos.Rows[e.RowIndex].IsNewRow)
                return;

            if (!dgvProdutos.Columns.Contains("Stock"))
                return;

            object stockVal = dgvProdutos.Rows[e.RowIndex].Cells["Stock"].Value;
            if (stockVal == null || stockVal == DBNull.Value)
                return;

            if (!int.TryParse(stockVal.ToString(), out int stock))
                return;

            // Colorir toda a linha
            if (stock == 0)
                e.CellStyle.BackColor = Color.MistyRose;
            else if (stock <= 3)
                e.CellStyle.BackColor = Color.LightYellow;

            // Símbolo de aviso e tooltip apenas na célula de Stock
            if (dgvProdutos.Columns[e.ColumnIndex].Name == "Stock" && stock <= 3)
            {
                string tooltip = stock == 0 ? "Sem Stock" : "Stock baixo";
                dgvProdutos.Rows[e.RowIndex].Cells[e.ColumnIndex].ToolTipText = tooltip;
                e.Value = $"⚠ {stock}";
                e.FormattingApplied = true;
            }
        }

        private void btnFiltroTamanhoDropdown_Click(object sender, EventArgs e)
        {
            AlternarDropdownProdutos(btnFiltroTamanhoDropdown, panelFiltroTamanhoDropdown);
        }

        private void btnFiltroCorProdutoDropdown_Click(object sender, EventArgs e)
        {
            AlternarDropdownProdutos(btnFiltroCorProdutoDropdown, panelFiltroCorProdutoDropdown);
        }

        private void btnFiltroCategoriaDropdown_Click(object sender, EventArgs e)
        {
            AlternarDropdownProdutos(btnFiltroCategoriaDropdown, panelFiltroCategoriaDropdown);
        }

        private void clbFiltroTamanho_ItemCheck(object sender, ItemCheckEventArgs e)
        {
            BeginInvoke((MethodInvoker)delegate
            {
                AtualizarTextoBotaoDropdown(clbFiltroTamanho, btnFiltroTamanhoDropdown, "Selecionar Tamanhos");
            });
        }

        private void clbFiltroCorProduto_ItemCheck(object sender, ItemCheckEventArgs e)
        {
            BeginInvoke((MethodInvoker)delegate
            {
                AtualizarTextoBotaoDropdown(clbFiltroCorProduto, btnFiltroCorProdutoDropdown, "Selecionar Cores");
            });
        }

        private void clbFiltroCategoria_ItemCheck(object sender, ItemCheckEventArgs e)
        {
            BeginInvoke((MethodInvoker)delegate
            {
                AtualizarTextoBotaoDropdown(clbFiltroCategoria, btnFiltroCategoriaDropdown, "Selecionar Categorias");
            });
        }

        private void txtFiltroNomeProduto_TextChanged(object sender, EventArgs e)
        {
        }

        private void txtFiltroPrecoMaxProduto_TextChanged(object sender, EventArgs e)
        {
        }

        private void btnPesquisarProdutos_Click(object sender, EventArgs e)
        {
            AplicarFiltrosProdutos();
        }

        private void AlternarDropdownProdutos(Control botaoOrigem, Panel painelAtivo)
        {
            bool estavaVisivel = painelAtivo.Visible;

            panelFiltroTamanhoDropdown.Visible = false;
            panelFiltroCorProdutoDropdown.Visible = false;
            panelFiltroCategoriaDropdown.Visible = false;

            if (estavaVisivel)
                return;

            Point pontoNaTela = botaoOrigem.Parent.PointToScreen(new Point(botaoOrigem.Left, botaoOrigem.Bottom + 2));
            painelAtivo.Location = panelDireitaProdutos.PointToClient(pontoNaTela);
            painelAtivo.Width = botaoOrigem.Width;
            painelAtivo.Visible = true;
            painelAtivo.BringToFront();
        }

        private void PreencherCombosProdutos(DataTable dt)
        {
            // Categorias da BD
            cmbCategoriaProduto.DataSource = null;
            cmbCategoriaProduto.Items.Clear();
            try
            {
                DataTable dtCat = _prontoVestirRepository.ListarCategorias();
                cmbCategoriaProduto.DisplayMember = "nome_categoria";
                cmbCategoriaProduto.ValueMember = "id_categoria_produto";
                cmbCategoriaProduto.DataSource = dtCat;
                cmbCategoriaProduto.SelectedIndex = -1;
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao carregar categorias: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }

            // Tamanhos e cores únicos da grelha para sugestões nos combos
            HashSet<string> tamanhos = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
            HashSet<string> cores = new HashSet<string>(StringComparer.OrdinalIgnoreCase);

            foreach (DataRow row in dt.Rows)
            {
                string tamanho = ObterValorColuna(row, "Tamanho");
                string cor = ObterValorColuna(row, "Cor");
                if (!string.IsNullOrWhiteSpace(tamanho)) tamanhos.Add(tamanho.Trim());
                if (!string.IsNullOrWhiteSpace(cor)) cores.Add(cor.Trim());
            }

            cmbTamanhoProduto.Items.Clear();
            foreach (string t in tamanhos) cmbTamanhoProduto.Items.Add(t);

            cmbCorProduto.Items.Clear();
            foreach (string c in cores) cmbCorProduto.Items.Add(c);
        }

        private void LimparCamposProduto()
        {
            textBoxIDProduto.Text = string.Empty;
            txtCodigoProduto.Text = string.Empty;
            txtNomeProduto.Text = string.Empty;
            cmbTamanhoProduto.Text = string.Empty;
            cmbCorProduto.Text = string.Empty;
            txtPrecoProduto.Text = string.Empty;
            nudStockProduto.Value = 0;
            cmbCategoriaProduto.SelectedIndex = -1;
            AtualizarEstadoBotoesProdutos();
        }

        private void AtualizarEstadoBotoesProdutos()
        {
            bool temId = !string.IsNullOrWhiteSpace(textBoxIDProduto.Text);
            btnAdicionarProduto.Enabled = !temId;
            btnAtualizarProduto.Enabled = temId;
            btnEliminarProduto.Enabled = temId;
        }

        private void dgvProdutos_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            DataGridViewRow row = dgvProdutos.Rows[e.RowIndex];

            textBoxIDProduto.Text = row.Cells["ID"].Value?.ToString() ?? string.Empty;
            txtCodigoProduto.Text = row.Cells["Codigo"].Value?.ToString() ?? string.Empty;
            txtNomeProduto.Text = row.Cells["Nome"].Value?.ToString() ?? string.Empty;
            cmbTamanhoProduto.Text = row.Cells["Tamanho"].Value?.ToString() ?? string.Empty;
            cmbCorProduto.Text = row.Cells["Cor"].Value?.ToString() ?? string.Empty;
            txtPrecoProduto.Text = row.Cells["Preco"].Value?.ToString() ?? string.Empty;

            if (row.Cells["Stock"].Value != null && int.TryParse(row.Cells["Stock"].Value.ToString(), out int stockVal))
                nudStockProduto.Value = stockVal;
            else
                nudStockProduto.Value = 0;

            // Selecionar categoria pelo nome da grelha
            string categoriaNome = row.Cells["Categoria"].Value?.ToString();
            if (!string.IsNullOrWhiteSpace(categoriaNome) && cmbCategoriaProduto.DataSource is DataTable dtCat)
            {
                foreach (DataRow catRow in dtCat.Rows)
                {
                    if (string.Equals(catRow["nome_categoria"].ToString(), categoriaNome, StringComparison.OrdinalIgnoreCase))
                    {
                        cmbCategoriaProduto.SelectedValue = catRow["id_categoria_produto"];
                        break;
                    }
                }
            }

            AtualizarEstadoBotoesProdutos();
        }

        private void btnLimparProduto_Click(object sender, EventArgs e)
        {
            LimparCamposProduto();
        }

        private void btnEliminarProduto_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(textBoxIDProduto.Text, out int id))
                return;

            string nomeProduto = txtNomeProduto.Text.Trim();

            // Verificar bloqueios antes de mostrar a confirmação
            try
            {
                string bloqueio = _prontoVestirRepository.VerificarBloqueiosEliminacaoProduto(id);
                if (bloqueio != null)
                {
                    MessageBox.Show(bloqueio, "Eliminação Bloqueada",
                        MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao verificar dependências do produto: {ex.Message}",
                    "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            DialogResult confirmacao = MessageBox.Show(
                $"Tem a certeza que deseja eliminar o produto \"{nomeProduto}\"?\n\nEsta ação não pode ser revertida.",
                "Confirmar Eliminação",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning,
                MessageBoxDefaultButton.Button2);

            if (confirmacao != DialogResult.Yes)
                return;

            try
            {
                _prontoVestirRepository.EliminarProduto(id);
                CarregarGrelhaProdutos();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao eliminar produto: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnAdicionarProduto_Click(object sender, EventArgs e)
        {
            if (!ValidarCamposProduto(out int codigo, out string nome, out string tamanho, out string cor,
                out decimal preco, out int idCategoria))
                return;

            try
            {
                DataTable existe = _prontoVestirRepository.ObterProdutoPorCodigo(codigo);
                if (existe.Rows.Count > 0)
                {
                    MessageBox.Show($"Já existe um produto com o código {codigo}.", "Código Duplicado",
                        MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    txtCodigoProduto.Focus();
                    return;
                }

                _prontoVestirRepository.InserirProduto(codigo, nome, tamanho, cor, preco,
                    (int)nudStockProduto.Value, idCategoria);
                CarregarGrelhaProdutos();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao adicionar produto: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnAtualizarProduto_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(textBoxIDProduto.Text, out int id))
            {
                MessageBox.Show("Selecione um produto na grelha para atualizar.", "Aviso",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (!ValidarCamposProduto(out int codigo, out string nome, out string tamanho, out string cor,
                out decimal preco, out int idCategoria))
                return;

            DialogResult confirmacao = MessageBox.Show(
                "Tem a certeza que deseja alterar os dados deste produto (incluindo o stock)?",
                "Confirmação de Atualização",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning);

            if (confirmacao != DialogResult.Yes)
                return;

            try
            {
                _prontoVestirRepository.AtualizarProduto(id, codigo, nome, tamanho, cor, preco,
                    (int)nudStockProduto.Value, idCategoria);
                CarregarGrelhaProdutos();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao atualizar produto: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private bool ValidarCamposProduto(out int codigo, out string nome, out string tamanho,
            out string cor, out decimal preco, out int idCategoria)
        {
            codigo = 0; nome = null; tamanho = null; cor = null; preco = 0; idCategoria = 0;

            if (!int.TryParse(txtCodigoProduto.Text.Trim(), out codigo) || codigo <= 0)
            {
                MessageBox.Show("Insira um código numérico válido.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtCodigoProduto.Focus();
                return false;
            }

            nome = txtNomeProduto.Text.Trim();
            if (string.IsNullOrWhiteSpace(nome))
            {
                MessageBox.Show("O nome é obrigatório.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtNomeProduto.Focus();
                return false;
            }

            tamanho = cmbTamanhoProduto.Text.Trim();
            if (string.IsNullOrWhiteSpace(tamanho))
            {
                MessageBox.Show("O tamanho é obrigatório.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                cmbTamanhoProduto.Focus();
                return false;
            }

            cor = cmbCorProduto.Text.Trim();
            if (string.IsNullOrWhiteSpace(cor))
            {
                MessageBox.Show("A cor é obrigatória.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                cmbCorProduto.Focus();
                return false;
            }

            if (!decimal.TryParse(txtPrecoProduto.Text.Trim().Replace(',', '.'),
                NumberStyles.Any, CultureInfo.InvariantCulture, out preco) || preco < 0)
            {
                MessageBox.Show("Insira um preço válido.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtPrecoProduto.Focus();
                return false;
            }

            if (cmbCategoriaProduto.SelectedValue == null)
            {
                MessageBox.Show("Selecione uma categoria.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                cmbCategoriaProduto.Focus();
                return false;
            }

            idCategoria = Convert.ToInt32(cmbCategoriaProduto.SelectedValue);
            return true;
        }
    }
}
