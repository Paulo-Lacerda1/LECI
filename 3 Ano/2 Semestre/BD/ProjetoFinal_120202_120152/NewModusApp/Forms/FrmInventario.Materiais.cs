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
        private readonly MaterialRepository _materialRepository = new MaterialRepository();
        private int? _idSelecionadoMaterial = null;

        private void InicializarControlosMateriais()
        {
            // Os controlos visuais da aba Material vivem no Designer.
            // Este método fica apenas para manter o ponto de inicialização e aplicar estado inicial.
            AtualizarEstadoBotoesMateriais();
        }

        private void CarregarGrelhaMateriais()
        {
            try
            {
                DataTable dt = _materialRepository.ListarMateriais();
                dgvMateriais.DataSource = dt;
                StandardizeColumnsMateriais();
                PreencherCombosMateriais(dt);
                PreencherFiltrosMateriais(dt);
                dgvMateriais.AplicarEstiloModus();
                dgvMateriais.ClearSelection();
                LimparCamposMateriais();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao carregar os materiais: {ex.Message}", "Erro de Base de Dados", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void StandardizeColumnsMateriais()
        {
            if (dgvMateriais.DataSource == null)
                return;

            foreach (DataGridViewColumn col in dgvMateriais.Columns)
            {
                switch (col.Name)
                {
                    case "ID":
                        col.HeaderText = "ID";
                        col.Visible = false;
                        break;
                    case "Nome":
                        col.HeaderText = "Material";
                        col.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
                        break;
                    case "CustoUnitario":
                        col.HeaderText = "Custo Unitário";
                        col.Width = 80;
                        break;
                    case "Stock":
                        col.HeaderText = "Stock";
                        col.Width = 60;
                        break;
                    case "UnidadeMedida":
                        col.HeaderText = "Unidade";
                        col.Width = 70;
                        break;
                    case "Tipo":
                        col.HeaderText = "Tipo";
                        col.Width = 100;
                        break;
                    case "FornecedorID":
                        col.Visible = false;
                        break;
                    case "FornecedorNome":
                        col.HeaderText = "Fornecedor";
                        col.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
                        break;
                }
            }
        }

        private void PreencherCombosMateriais(DataTable dt)
        {
            PreencherComboBoxValores(cmbUnidadeMedida, dt, "UnidadeMedida", "unidade_medida", "Unidade");
            PreencherComboBoxValores(cmbTipoMaterial, dt, "Tipo", "tipo");

            DataTable fornecedores = _materialRepository.ListarFornecedores();
            cmbFornecedorMaterial.DataSource = fornecedores;
            cmbFornecedorMaterial.DisplayMember = "Nome";
            cmbFornecedorMaterial.ValueMember = "ID";
            cmbFornecedorMaterial.SelectedIndex = -1;
        }

        private void PreencherFiltrosMateriais(DataTable dt)
        {
            cmbFiltroUnidadeMedida.Items.Clear();
            cmbFiltroTipoMaterial.Items.Clear();
            clbFiltroFornecedorMaterial.Items.Clear();

            HashSet<string> unidades = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
            HashSet<string> tipos = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
            HashSet<string> fornecedores = new HashSet<string>(StringComparer.OrdinalIgnoreCase);

            foreach (DataRow row in dt.Rows)
            {
                string unidade = ObterValorColuna(row, "UnidadeMedida", "unidade_medida");
                string tipo = ObterValorColuna(row, "Tipo", "tipo");
                string fornecedor = ObterValorColuna(row, "FornecedorNome", "Fornecedor", "FornecedorNome");

                if (!string.IsNullOrWhiteSpace(unidade)) unidades.Add(unidade.Trim());
                if (!string.IsNullOrWhiteSpace(tipo)) tipos.Add(tipo.Trim());
                if (!string.IsNullOrWhiteSpace(fornecedor)) fornecedores.Add(fornecedor.Trim());
            }

            foreach (string unidade in unidades) cmbFiltroUnidadeMedida.Items.Add(unidade);
            foreach (string tipo in tipos) cmbFiltroTipoMaterial.Items.Add(tipo);
            foreach (string fornecedor in fornecedores) clbFiltroFornecedorMaterial.Items.Add(fornecedor);

            cmbFiltroUnidadeMedida.SelectedIndex = -1;
            cmbFiltroTipoMaterial.SelectedIndex = -1;
        }

        private void LimparCamposMateriais()
        {
            _idSelecionadoMaterial = null;
            textBoxIDMaterial.Clear();
            txtNomeMaterial.Clear();
            txtCustoMaterial.Clear();
            txtQuantidadeMaterial.Clear();
            cmbUnidadeMedida.SelectedIndex = -1;
            cmbUnidadeMedida.Text = string.Empty;
            cmbTipoMaterial.SelectedIndex = -1;
            cmbTipoMaterial.Text = string.Empty;
            cmbFornecedorMaterial.SelectedIndex = -1;
            AtualizarEstadoBotoesMateriais();
        }

        private void AtualizarEstadoBotoesMateriais(object sender = null, EventArgs e = null)
        {
            bool temID = int.TryParse(textBoxIDMaterial.Text, out int id) && id > 0;
            bool temObrigatorios = !string.IsNullOrWhiteSpace(txtNomeMaterial.Text?.Trim()) && !string.IsNullOrWhiteSpace(txtCustoMaterial.Text?.Trim());

            if (temID)
            {
                btnAdicionarMaterial.Enabled = false;
                btnAtualizarMaterial.Enabled = true;
            }
            else
            {
                btnAdicionarMaterial.Enabled = temObrigatorios;
                btnAtualizarMaterial.Enabled = false;
            }
        }

        private void dgvMateriais_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0 || dgvMateriais.Rows.Count == 0)
                return;

            DataGridViewRow row = dgvMateriais.Rows[e.RowIndex];

            string GetCellValue(params string[] nomes)
            {
                foreach (string nome in nomes)
                {
                    if (dgvMateriais.Columns.Contains(nome))
                    {
                        object valor = row.Cells[nome].Value;
                        return valor?.ToString() ?? string.Empty;
                    }
                }

                return string.Empty;
            }

            textBoxIDMaterial.Text = GetCellValue("ID");
            txtNomeMaterial.Text = GetCellValue("Nome");
            txtCustoMaterial.Text = GetCellValue("CustoUnitario");
            txtQuantidadeMaterial.Text = GetCellValue("Stock");
            cmbUnidadeMedida.Text = GetCellValue("UnidadeMedida");
            cmbTipoMaterial.Text = GetCellValue("Tipo");

            string fornecedorIdTexto = GetCellValue("FornecedorID");
            if (int.TryParse(fornecedorIdTexto, out int fornecedorId))
            {
                cmbFornecedorMaterial.SelectedValue = fornecedorId;
            }
            else
            {
                cmbFornecedorMaterial.SelectedIndex = -1;
            }

            AtualizarEstadoBotoesMateriais();
        }

        private void btnAdicionarMaterial_Click(object sender, EventArgs e)
        {
            if (!ValidarInputsMateriais(out decimal custoUnitario, out int quantidadeStock, out int fornecedorId))
                return;

            DataTable existentes = _materialRepository.ObterMaterialPorNomeETipo(txtNomeMaterial.Text.Trim(), cmbTipoMaterial.Text.Trim());
            if (existentes.Rows.Count > 0)
            {
                MessageBox.Show("Já existe um material com o mesmo Nome e Tipo. A operação foi cancelada.", "Duplicado", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            try
            {
                _materialRepository.InserirMaterial(
                    txtNomeMaterial.Text.Trim(),
                    custoUnitario,
                    quantidadeStock,
                    cmbUnidadeMedida.Text.Trim(),
                    cmbTipoMaterial.Text.Trim(),
                    fornecedorId
                );

                MessageBox.Show("Material adicionado com sucesso!", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                CarregarGrelhaMateriais();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao adicionar material: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnAtualizarMaterial_Click(object sender, EventArgs e)
        {
            if (!_idSelecionadoMaterial.HasValue)
            {
                MessageBox.Show("Por favor, selecione um material na grelha para atualizar.", "Aviso", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (!ValidarInputsMateriais(out decimal custoUnitario, out int quantidadeStock, out int fornecedorId))
                return;

            try
            {
                _materialRepository.AtualizarMaterial(
                    _idSelecionadoMaterial.Value,
                    txtNomeMaterial.Text.Trim(),
                    custoUnitario,
                    quantidadeStock,
                    cmbUnidadeMedida.Text.Trim(),
                    cmbTipoMaterial.Text.Trim(),
                    fornecedorId
                );

                MessageBox.Show("Material atualizado com sucesso!", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                CarregarGrelhaMateriais();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao atualizar material: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnLimparMaterial_Click(object sender, EventArgs e)
        {
            LimparCamposMateriais();
        }

        private bool ValidarInputsMateriais(out decimal custoUnitario, out int quantidadeStock, out int fornecedorId)
        {
            custoUnitario = 0;
            quantidadeStock = 0;
            fornecedorId = 0;

            if (string.IsNullOrWhiteSpace(txtNomeMaterial.Text))
            {
                MessageBox.Show("O campo Nome é obrigatório.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtNomeMaterial.Focus();
                return false;
            }

            string custoTexto = txtCustoMaterial.Text.Replace('.', ',');
            if (!decimal.TryParse(custoTexto, NumberStyles.Number, CultureInfo.GetCultureInfo("pt-PT"), out custoUnitario))
            {
                MessageBox.Show("Custo inválido. Introduza um valor numérico válido.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtCustoMaterial.Focus();
                return false;
            }

            if (!int.TryParse(txtQuantidadeMaterial.Text, out quantidadeStock))
            {
                MessageBox.Show("Stock inválido. Introduza um número inteiro válido.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtQuantidadeMaterial.Focus();
                return false;
            }

            if (string.IsNullOrWhiteSpace(cmbUnidadeMedida.Text))
            {
                MessageBox.Show("A unidade de medida é obrigatória.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                cmbUnidadeMedida.Focus();
                return false;
            }

            if (string.IsNullOrWhiteSpace(cmbTipoMaterial.Text))
            {
                MessageBox.Show("O tipo é obrigatório.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                cmbTipoMaterial.Focus();
                return false;
            }

            if (cmbFornecedorMaterial.SelectedIndex < 0 || !int.TryParse(Convert.ToString(cmbFornecedorMaterial.SelectedValue), out fornecedorId))
            {
                MessageBox.Show("O fornecedor é obrigatório.", "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                cmbFornecedorMaterial.Focus();
                return false;
            }

            return true;
        }

        private void AplicarFiltrosMateriais()
        {
            if (!(dgvMateriais.DataSource is DataTable dt))
                return;

            string colNome = ResolveColumnName(dt, "Nome");
            string colUnidade = ResolveColumnName(dt, "UnidadeMedida", "unidade_medida");
            string colTipo = ResolveColumnName(dt, "Tipo", "tipo");
            string colFornecedor = ResolveColumnName(dt, "FornecedorNome", "Fornecedor");

            List<string> filtros = new List<string>();

            if (!string.IsNullOrWhiteSpace(txtFiltroNomeMaterial.Text) && !string.IsNullOrWhiteSpace(colNome))
            {
                filtros.Add($"{colNome} LIKE '%{txtFiltroNomeMaterial.Text.Replace("'", "''")}%' ");
            }

            if (cmbFiltroUnidadeMedida.SelectedIndex >= 0 && !string.IsNullOrWhiteSpace(cmbFiltroUnidadeMedida.Text) && !string.IsNullOrWhiteSpace(colUnidade))
            {
                filtros.Add($"{colUnidade} = '{cmbFiltroUnidadeMedida.Text.Replace("'", "''")}'");
            }

            if (cmbFiltroTipoMaterial.SelectedIndex >= 0 && !string.IsNullOrWhiteSpace(cmbFiltroTipoMaterial.Text) && !string.IsNullOrWhiteSpace(colTipo))
            {
                filtros.Add($"{colTipo} = '{cmbFiltroTipoMaterial.Text.Replace("'", "''")}'");
            }

            if (clbFiltroFornecedorMaterial.CheckedItems.Count > 0 && !string.IsNullOrWhiteSpace(colFornecedor))
            {
                List<string> fornecedores = new List<string>();
                foreach (var item in clbFiltroFornecedorMaterial.CheckedItems)
                {
                    fornecedores.Add($"'{item.ToString().Replace("'", "''")}'");
                }

                filtros.Add($"{colFornecedor} IN ({string.Join(", ", fornecedores)})");
            }

            string filtroFinal = string.Join(" AND ", filtros);

            try
            {
                dt.DefaultView.RowFilter = filtroFinal;
            }
            catch (Exception ex)
            {
                dt.DefaultView.RowFilter = string.Empty;
                MessageBox.Show($"Filtro inválido aplicado: {ex.Message}", "Erro de Filtro", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }

        private void btnFiltroFornecedorMaterialDropdown_Click(object sender, EventArgs e)
        {
            AlternarDropdownMaterial(btnFiltroFornecedorMaterialDropdown, panelFiltroFornecedorMaterialDropdown);
        }

        private void btnPesquisarMaterial_Click(object sender, EventArgs e)
        {
            AplicarFiltrosMateriais();
        }

        private void txtFiltroNomeMaterial_TextChanged(object sender, EventArgs e)
        {
        }

        private void cmbFiltroUnidadeMedida_SelectedIndexChanged(object sender, EventArgs e)
        {
        }

        private void cmbFiltroTipoMaterial_SelectedIndexChanged(object sender, EventArgs e)
        {
        }

        private void clbFiltroFornecedorMaterial_ItemCheck(object sender, ItemCheckEventArgs e)
        {
            this.BeginInvoke((MethodInvoker)delegate
            {
                AtualizarTextoBotaoDropdown(clbFiltroFornecedorMaterial, btnFiltroFornecedorMaterialDropdown, "Selecionar fornecedores");
            });
        }

        private void AlternarDropdownMaterial(Control botaoOrigem, Panel painelAtivo)
        {
            bool estavaVisivel = painelAtivo.Visible;
            panelFiltroFornecedorMaterialDropdown.Visible = false;

            if (estavaVisivel)
            {
                return;
            }

            Point pontoNaTela = botaoOrigem.Parent.PointToScreen(new Point(botaoOrigem.Left, botaoOrigem.Bottom + 2));
            painelAtivo.Location = panelDireitaMateriais.PointToClient(pontoNaTela);
            painelAtivo.Width = botaoOrigem.Width;
            painelAtivo.Visible = true;
            painelAtivo.BringToFront();
        }
    }
}
