using NewModusApp.Repositories;
using NewModusApp.Utils;
using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmInventario
    {
        private void CarregarGrelha()
        {
            try
            {
                DataTable dt = _tecidoRepository.ObterTodos();
                dgvTecidos.DataSource = dt;
                StandardizeColumns();
                RemoverIdFornecedorDaGrelha(dt);
                PreencherCombosTecidos(dt);
                PreencherFiltrosDropdown(dt);
                dgvTecidos.AplicarEstiloModus();
                dgvTecidos.ClearSelection();
                LimparCampos();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao carregar os tecidos: {ex.Message}", "Erro de Base de Dados", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void dgvTecidos_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            if (dgvTecidos.Columns.Contains("ID"))
                dgvTecidos.Columns["ID"].Visible = false;

            if (dgvTecidos.Columns.Contains("id_tecido"))
                dgvTecidos.Columns["id_tecido"].Visible = false;
        }

        // Metodo auxiliar para extrair os valores unicos e colocar nas caixas de selecao
        private void PreencherFiltrosDropdown(DataTable dt)
        {
            // Limpa os itens antigos para nao duplicar se recarregar a pagina
            clbFiltroCor.Items.Clear();
            clbFiltroTipo.Items.Clear();
            clbFiltroFornecedor.Items.Clear();
            clbFiltroPadrao.Items.Clear();

            // HashSet garante que nao guardamos cores ou fornecedores repetidos
            HashSet<string> coresUnicas = new HashSet<string>();
            HashSet<string> tiposUnicos = new HashSet<string>();
            HashSet<string> fornecedoresUnicos = new HashSet<string>();
            HashSet<string> padroesUnicos = new HashSet<string>();

            foreach (DataRow row in dt.Rows)
            {
                // Defesa contra valores nulos na base de dados
                if (row.Table.Columns.Contains("CorTecido") && row["CorTecido"] != DBNull.Value)
                {
                    coresUnicas.Add(row["CorTecido"].ToString());
                }

                // Aceita colunas diferentes dependendo da view / stored proc: Tipo ou TipoTecido
                string tipoValor = ObterValorColuna(row, "TipoTecido", "Tipo", "tipo");
                if (!string.IsNullOrWhiteSpace(tipoValor))
                {
                    tiposUnicos.Add(tipoValor);
                }

                if (row.Table.Columns.Contains("FornecedorExibicao") && row["FornecedorExibicao"] != DBNull.Value)
                {
                    fornecedoresUnicos.Add(row["FornecedorExibicao"].ToString());
                }

                string padraoValor = ObterValorColuna(row, "PadraoTecido", "Padrao", "padrao");
                if (!string.IsNullOrWhiteSpace(padraoValor))
                {
                    padroesUnicos.Add(padraoValor);
                }
            }

            // Passa os valores unicos recolhidos para os controlos visuais
            foreach (string cor in coresUnicas)
            {
                clbFiltroCor.Items.Add(cor);
            }

            foreach (string tipo in tiposUnicos)
            {
                clbFiltroTipo.Items.Add(tipo);
            }

            foreach (string fornecedor in fornecedoresUnicos)
            {
                clbFiltroFornecedor.Items.Add(fornecedor);
            }

            foreach (string padrao in padroesUnicos)
            {
                clbFiltroPadrao.Items.Add(padrao);
            }
        }

        private void StandardizeColumns()
        {
            if (dgvTecidos.DataSource == null) return;

            foreach (DataGridViewColumn col in dgvTecidos.Columns)
            {
                string original = col.Name;
                switch (original)
                {
                    case "id_tecido":
                    case "id_Tecido":
                    case "ID":
                    case "Id":
                    case "id":
                        col.Name = "ID";
                        col.HeaderText = "ID";
                        col.Visible = false;
                        break;
                    case "Tecido":
                    case "Nome":
                    case "nome":
                        col.Name = "Nome";
                        col.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
                        col.HeaderText = "Tecido";
                        break;
                    case "PrecoPorMetro":
                    case "preco_metro":
                    case "Preco":
                        col.Name = "Preco";
                        col.Width = 80;
                        col.HeaderText = "Preço/m";
                        break;
                    case "StockTecido":
                    case "quantidade_stock":
                    case "Quantidade":
                        col.Name = "Quantidade";
                        col.Width = 60;
                        col.HeaderText = "Stock";
                        col.DefaultCellStyle.Format = "N2";
                        break;
                    case "CodigoTecido":
                    case "codigo":
                    case "Codigo":
                        col.Name = "Codigo";
                        col.Width = 60;
                        col.HeaderText = "Código";
                        break;
                    case "CorTecido":
                    case "cor":
                    case "Cor":
                        col.Name = "Cor";
                        col.Width = 50;
                        col.HeaderText = "Cor";
                        break;
                    case "TipoTecido":
                    case "tipo":
                    case "Tipo":
                        col.Name = "Tipo";
                        col.HeaderText = "Tipo";
                        break;
                    case "PadraoTecido":
                    case "padrao":
                    case "Padrao":
                        col.Name = "Padrao";
                        col.Width = 70;
                        col.HeaderText = "Padrão";
                        break;
                    case "FornecedorID":
                        col.Name = "FornecedorID";
                        col.Visible = false;
                        break;
                    case "FornecedorExibicao":
                        col.Name = "FornecedorExibicao";
                        col.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
                        col.HeaderText = "Fornecedor";
                        col.Visible = true;
                        break;
                    default:
                        col.Width = 100;
                        break;
                }
            }

            if (dgvTecidos.Columns.Contains("ID"))
                dgvTecidos.Columns["ID"].Visible = false;

            if (dgvTecidos.Columns.Contains("id_tecido"))
                dgvTecidos.Columns["id_tecido"].Visible = false;
        }

        private void dgvTecidos_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = dgvTecidos.Rows[e.RowIndex];

                object idVal = null;
                if (dgvTecidos.Columns.Contains("Id"))
                {
                    var idx = dgvTecidos.Columns["Id"].Index;
                    idVal = row.Cells[idx].Value;
                }
                else if (row.Cells.Count > 0)
                {
                    idVal = row.Cells[0].Value;
                }

                if (idVal != null && int.TryParse(idVal.ToString(), out int parsedId))
                    _idSelecionado = parsedId;
                else
                    _idSelecionado = null;

                string GetCellValue(string name)
                {
                    if (dgvTecidos.Columns.Contains(name))
                    {
                        var idx = dgvTecidos.Columns[name].Index;
                        var v = row.Cells[idx].Value;
                        return v?.ToString() ?? string.Empty;
                    }
                    return string.Empty;
                }

                if (GetCellValue("ID") != null)
                {
                    textBoxIDtecido.Text = GetCellValue("ID");
                    txtNome.Text = GetCellValue("Nome");
                    txtPreco.Text = GetCellValue("Preco");
                    txtQuantidade.Text = GetCellValue("Quantidade");
                    txtCodigo.Text = GetCellValue("Codigo");
                    cmbCor.Text = GetCellValue("Cor");
                    cmbTipo.Text = GetCellValue("Tipo");
                    cmbPadrao.Text = GetCellValue("Padrao");

                    if (int.TryParse(GetCellValue("FornecedorID"), out int fornecedorId))
                        cmbFornecedor.SelectedValue = fornecedorId;
                    else
                        cmbFornecedor.SelectedIndex = -1;

                    AtualizarEstadoBotoesTecidos();
                }
            }
        }

        private void btnAdicionarTecido_Click(object sender, EventArgs e)
        {
            if (!ValidarInputs(out decimal preco, out decimal quantidade, out int codigo)) return;

            DataTable tecidoExistente = _tecidoRepository.ObterTecidoPorCodigo(codigo);
            if (tecidoExistente.Rows.Count > 0)
            {
                MessageBox.Show($"Operação Bloqueada: Já existe um tecido registado com o código '{codigo}'. Códigos duplicados não são permitidos.",
                                "Conflito de Integridade", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (cmbFornecedor.SelectedIndex < 0)
            {
                VerAviso("O campo Fornecedor é obrigatório.", cmbFornecedor);
                return;
            }

            try
            {
                _tecidoRepository.InserirTecido(
                    txtNome.Text.Trim(),
                    preco,
                    quantidade,
                    codigo.ToString(),
                    cmbCor.Text.Trim(),
                    cmbTipo.Text.Trim(),
                    cmbPadrao.Text.Trim(),
                    cmbFornecedor.SelectedValue?.ToString() ?? string.Empty
                );

                MessageBox.Show("Tecido adicionado com sucesso!", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                CarregarGrelha();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao adicionar tecido: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnAtualizarTecido_Click(object sender, EventArgs e)
        {
            if (_idSelecionado == null)
            {
                MessageBox.Show("Por favor, selecione um tecido na grelha para atualizar.", "Aviso", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (!ValidarInputs(out decimal preco, out decimal quantidade, out int codigo)) return;

            if (cmbFornecedor.SelectedIndex < 0)
            {
                VerAviso("O campo Fornecedor é obrigatório.", cmbFornecedor);
                return;
            }

            try
            {
                _tecidoRepository.AtualizarTecido(
                    _idSelecionado.Value,
                    txtNome.Text.Trim(),
                    preco,
                    quantidade,
                    codigo.ToString(),
                    cmbCor.Text.Trim(),
                    cmbTipo.Text.Trim(),
                    cmbPadrao.Text.Trim(),
                    cmbFornecedor.SelectedValue?.ToString() ?? string.Empty
                );

                MessageBox.Show("Tecido atualizado com sucesso!", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                CarregarGrelha();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao atualizar tecido: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private bool ValidarInputs(out decimal preco, out decimal quantidade, out int codigo)
        {
            preco = 0;
            quantidade = 0;
            codigo = 0;

            if (string.IsNullOrWhiteSpace(txtNome.Text))
            {
                VerAviso("O campo Nome é obrigatório.", txtNome);
                return false;
            }

            string precoTexto = txtPreco.Text.Replace('.', ',');
            if (!decimal.TryParse(precoTexto, out preco))
            {
                VerAviso("Preço inválido. Introduza um valor numérico válido.", txtPreco);
                return false;
            }

            string quantidadeTexto = txtQuantidade.Text.Replace('.', ',');
            if (!decimal.TryParse(quantidadeTexto, out quantidade))
            {
                VerAviso("Quantidade inválida. Introduza um valor numérico válido.", txtQuantidade);
                return false;
            }

            if (!int.TryParse(txtCodigo.Text, out codigo))
            {
                VerAviso("Código inválido. Introduza um valor inteiro válido.", txtCodigo);
                return false;
            }

            return true;
        }

        private void VerAviso(string mensagem, Control controloOrigem)
        {
            MessageBox.Show(mensagem, "Validação", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            controloOrigem.Focus();
        }

        private void LimparCampos()
        {
            _idSelecionado = null;
            textBoxIDtecido.Clear();
            txtNome.Clear();
            txtPreco.Clear();
            txtQuantidade.Clear();
            txtCodigo.Clear();
            cmbCor.SelectedIndex = -1;
            cmbCor.Text = string.Empty;
            cmbTipo.SelectedIndex = -1;
            cmbTipo.Text = string.Empty;
            cmbPadrao.SelectedIndex = -1;
            cmbPadrao.Text = string.Empty;
            cmbFornecedor.SelectedIndex = -1;
            cmbFornecedor.Text = string.Empty;
            AtualizarEstadoBotoesTecidos();
        }

        private void PreencherCombosTecidos(DataTable dt)
        {
            PreencherComboBoxValores(cmbCor, dt, "CorTecido", "Cor", "cor");
            PreencherComboBoxValores(cmbTipo, dt, "TipoTecido", "Tipo", "tipo");
            PreencherComboBoxValores(cmbPadrao, dt, "PadraoTecido", "Padrao", "padrao");

            DataTable dtFornecedores = _tecidoRepository.ListarFornecedores();
            if (!dtFornecedores.Columns.Contains("FornecedorCombo"))
            {
                dtFornecedores.Columns.Add("FornecedorCombo", typeof(string));
            }

            foreach (DataRow row in dtFornecedores.Rows)
            {
                row["FornecedorCombo"] = $"{row["Nome"]} ({row["ID"]})";
            }

            cmbFornecedor.DataSource = dtFornecedores;
            cmbFornecedor.DisplayMember = "FornecedorCombo";
            cmbFornecedor.ValueMember = "ID";
            cmbFornecedor.SelectedIndex = -1;
        }

        private void RemoverIdFornecedorDaGrelha(DataTable dt)
        {
            if (dt == null || !dt.Columns.Contains("FornecedorExibicao"))
                return;

            foreach (DataRow row in dt.Rows)
            {
                string valor = row["FornecedorExibicao"]?.ToString() ?? string.Empty;
                int indiceParenteses = valor.LastIndexOf(" (");
                if (indiceParenteses > 0)
                {
                    row["FornecedorExibicao"] = valor.Substring(0, indiceParenteses).Trim();
                }
            }
        }

        private void PreencherComboBoxValores(ComboBox comboBox, DataTable dt, params string[] nomesColuna)
        {
            comboBox.Items.Clear();

            HashSet<string> valores = new HashSet<string>(StringComparer.OrdinalIgnoreCase);

            foreach (DataRow row in dt.Rows)
            {
                string valor = ObterValorColuna(row, nomesColuna);
                if (!string.IsNullOrWhiteSpace(valor))
                    valores.Add(valor.Trim());
            }

            foreach (string valor in valores)
                comboBox.Items.Add(valor);
        }

        private void AtualizarEstadoBotoesTecidos(object sender = null, EventArgs e = null)
        {
            bool temID = int.TryParse(textBoxIDtecido.Text, out int parsedId) && parsedId > 0;
            bool temDadosObrigatorios = !string.IsNullOrWhiteSpace(txtNome.Text?.Trim()) && !string.IsNullOrWhiteSpace(txtCodigo.Text?.Trim());

            if (temID)
            {
                btnAdicionarTecido.Enabled = false;
                btnAtualizarTecido.Enabled = true;
            }
            else
            {
                btnAdicionarTecido.Enabled = temDadosObrigatorios;
                btnAtualizarTecido.Enabled = false;
            }
        }

        private void btnLimparTecido_Click(object sender, EventArgs e)
        {
            LimparCampos();
        }

        private void CarregarFiltrosTecidos(DataTable dt)
        {
            PopularFiltroDropdown(clbFiltroCor, dt, "Cor", "cor");
            PopularFiltroDropdown(clbFiltroFornecedor, dt, "FornecedorExibicao", "FornecedorID", "fornecedor");
        }

        private void PopularFiltroDropdown(CheckedListBox checkedListBox, DataTable dt, params string[] nomesColuna)
        {
            checkedListBox.Items.Clear();

            HashSet<string> valores = new HashSet<string>(StringComparer.OrdinalIgnoreCase);

            foreach (DataRow row in dt.Rows)
            {
                string valor = ObterValorColuna(row, nomesColuna);
                if (!string.IsNullOrWhiteSpace(valor))
                    valores.Add(valor.Trim());
            }

            foreach (string valor in valores)
                checkedListBox.Items.Add(valor);
        }

        private string ObterValorColuna(DataRow row, params string[] nomesColuna)
        {
            foreach (string nomeColuna in nomesColuna)
            {
                if (row.Table.Columns.Contains(nomeColuna))
                {
                    object valor = row[nomeColuna];
                    if (valor != DBNull.Value && valor != null)
                        return valor.ToString();
                }
            }

            return string.Empty;
        }

        private void btnFiltroCorDropdown_Click(object sender, EventArgs e)
        {
            AlternarDropdown(btnFiltroCorDropdown, panelFiltroCorDropdown, panelFiltroFornecedorDropdown);
        }

        private void btnFiltroTipoDropdown_Click(object sender, EventArgs e)
        {
            AlternarDropdown(btnFiltroTipoDropdown, panelFiltroTipoDropdown, panelFiltroCorDropdown);
        }

        private void btnFiltroFornecedorDropdown_Click(object sender, EventArgs e)
        {
            AlternarDropdown(btnFiltroFornecedorDropdown, panelFiltroFornecedorDropdown, panelFiltroCorDropdown);
        }

        private void btnFiltroPadraoDropdown_Click(object sender, EventArgs e)
        {
            AlternarDropdown(btnFiltroPadraoDropdown, panelFiltroPadraoDropdown, panelFiltroFornecedorDropdown);
        }

        private void AlternarDropdown(Control botaoOrigem, Panel painelAtivo, Panel outroPainel)
        {
            bool estavaVisivel = painelAtivo.Visible;

            if (estavaVisivel)
            {
                painelAtivo.Visible = false;
                return;
            }

            panelFiltroCorDropdown.Visible = false;
            panelFiltroTipoDropdown.Visible = false;
            panelFiltroFornecedorDropdown.Visible = false;
            panelFiltroPadraoDropdown.Visible = false;

            Point pontoNaTela = botaoOrigem.Parent.PointToScreen(new Point(botaoOrigem.Left, botaoOrigem.Bottom + 2));
            painelAtivo.Location = panelDireitaTecidos.PointToClient(pontoNaTela);
            painelAtivo.Width = botaoOrigem.Width;

            painelAtivo.Visible = true;
            painelAtivo.BringToFront();
        }

        private void dgvTecidos_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
        }

        private void AtualizarTextoBotaoDropdown(CheckedListBox clb, Button btn, string textoBase)
        {
            int count = clb.CheckedItems.Count;
            if (count == 0)
            {
                btn.Text = $"{textoBase} ▼";
            }
            else
            {
                btn.Text = $"{textoBase} ({count}) ▼";
            }
        }

        private void clbFiltroCor_ItemCheck(object sender, ItemCheckEventArgs e)
        {
            this.BeginInvoke((MethodInvoker)delegate {
                AtualizarTextoBotaoDropdown(clbFiltroCor, btnFiltroCorDropdown, "Selecionar Cores");
                AplicarFiltrosTecidos();
            });
        }

        private void clbFiltroTipo_ItemCheck(object sender, ItemCheckEventArgs e)
        {
            this.BeginInvoke((MethodInvoker)delegate {
                AtualizarTextoBotaoDropdown(clbFiltroTipo, btnFiltroTipoDropdown, "Selecionar Tipos");
                AplicarFiltrosTecidos();
            });
        }

        private void clbFiltroFornecedor_ItemCheck(object sender, ItemCheckEventArgs e)
        {
            this.BeginInvoke((MethodInvoker)delegate {
                AtualizarTextoBotaoDropdown(clbFiltroFornecedor, btnFiltroFornecedorDropdown, "Selecionar Fornecedor");
                AplicarFiltrosTecidos();
            });
        }

        private void clbFiltroPadrao_ItemCheck(object sender, ItemCheckEventArgs e)
        {
            this.BeginInvoke((MethodInvoker)delegate {
                AtualizarTextoBotaoDropdown(clbFiltroPadrao, btnFiltroPadraoDropdown, "Selecionar padrões");
                AplicarFiltrosTecidos();
            });
        }

        private void AplicarFiltrosTecidos()
        {
            if (!(dgvTecidos.DataSource is DataTable dt)) return;

            string colTecido = ResolveColumnName(dt, "Tecido", "Nome", "nome");
            string colPreco = ResolveColumnName(dt, "PrecoPorMetro", "preco_metro", "Preco", "PrecoPorMetro");
            string colCor = ResolveColumnName(dt, "CorTecido", "cor", "Cor");
            string colTipo = ResolveColumnName(dt, "Tipo", "TipoTecido", "tipo");
            string colPadrao = ResolveColumnName(dt, "PadraoTecido", "Padrao", "padrao");
            string colFornecedor = ResolveColumnName(dt, "FornecedorExibicao", "Fornecedor", "FornecedorID");

            List<string> filtros = new List<string>();

            if (!string.IsNullOrWhiteSpace(txtFiltroNome.Text) && !string.IsNullOrWhiteSpace(colTecido))
            {
                filtros.Add($"{colTecido} LIKE '%{txtFiltroNome.Text.Replace("'", "''")}%'");
            }

            if (!string.IsNullOrWhiteSpace(txtFiltroPrecoMin.Text) && !string.IsNullOrWhiteSpace(colPreco))
            {
                if (decimal.TryParse(txtFiltroPrecoMin.Text.Replace('.', ','), out decimal min))
                {
                    filtros.Add($"{colPreco} >= {min.ToString(System.Globalization.CultureInfo.InvariantCulture)}");
                }
            }

            if (!string.IsNullOrWhiteSpace(txtFiltroPrecoMax.Text) && !string.IsNullOrWhiteSpace(colPreco))
            {
                if (decimal.TryParse(txtFiltroPrecoMax.Text.Replace('.', ','), out decimal max))
                {
                    filtros.Add($"{colPreco} <= {max.ToString(System.Globalization.CultureInfo.InvariantCulture)}");
                }
            }

            if (clbFiltroCor.CheckedItems.Count > 0 && !string.IsNullOrWhiteSpace(colCor))
            {
                List<string> coresSelecionadas = new List<string>();
                foreach (var item in clbFiltroCor.CheckedItems)
                {
                    coresSelecionadas.Add($"'{item.ToString().Replace("'", "''")}'");
                }
                filtros.Add($"{colCor} IN ({string.Join(", ", coresSelecionadas)})");
            }

            if (clbFiltroTipo.CheckedItems.Count > 0 && !string.IsNullOrWhiteSpace(colTipo))
            {
                List<string> tiposSelecionados = new List<string>();
                foreach (var item in clbFiltroTipo.CheckedItems)
                {
                    tiposSelecionados.Add($"'{item.ToString().Replace("'", "''")}'");
                }
                filtros.Add($"{colTipo} IN ({string.Join(", ", tiposSelecionados)})");
            }

            if (clbFiltroPadrao.CheckedItems.Count > 0 && !string.IsNullOrWhiteSpace(colPadrao))
            {
                List<string> padroesSelecionados = new List<string>();
                foreach (var item in clbFiltroPadrao.CheckedItems)
                {
                    padroesSelecionados.Add($"'{item.ToString().Replace("'", "''")}'");
                }
                filtros.Add($"{colPadrao} IN ({string.Join(", ", padroesSelecionados)})");
            }

            if (clbFiltroFornecedor.CheckedItems.Count > 0 && !string.IsNullOrWhiteSpace(colFornecedor))
            {
                List<string> fornecedores = new List<string>();
                foreach (var item in clbFiltroFornecedor.CheckedItems)
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

        private string ResolveColumnName(DataTable dt, params string[] candidates)
        {
            foreach (string c in candidates)
            {
                if (dt.Columns.Contains(c)) return c;
            }
            return string.Empty;
        }

        private void btnPesquisarTecido_Click(object sender, EventArgs e)
        {
            AplicarFiltrosTecidos();
        }

        private void vScrollBar1_Scroll(object sender, ScrollEventArgs e)
        {
        }

        private void lblCor_Click(object sender, EventArgs e)
        {
        }
    }
}
