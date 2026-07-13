using System;
using System.Data;
using System.Drawing;
using System.Windows.Forms;
using NewModusApp.Repositories;
using NewModusApp.Utils;

namespace NewModusApp.Forms
{
    public partial class FrmStock : Form
    {
        private readonly TecidoRepository _tecidoRepository;
        private int? _idSelecionado = null;

        // Guardar o índice da última aba aberta na memória da aplicação
        private static int _ultimaAbaSelecionada = 0;

        public FrmStock()
        {
            InitializeComponent();
            _tecidoRepository = new TecidoRepository();
            tabStock.DrawMode = TabDrawMode.OwnerDrawFixed;
            tabStock.DrawItem += tabStock_DrawItem;
        }

        private void FrmStock_Load(object sender, EventArgs e)
        {
            // Restaura a última aba antes de carregar os dados
            tabStock.SelectedIndex = _ultimaAbaSelecionada;

            CarregarGrelha();
        }

        private void tabStock_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Grava o índice sempre que o utilizador muda de separador
            _ultimaAbaSelecionada = tabStock.SelectedIndex;
        }

        private void tabStock_DrawItem(object sender, DrawItemEventArgs e)
        {
            // Obter o contexto da aba atual
            TabControl tabCtrl = (TabControl)sender;
            TabPage pagina = tabCtrl.TabPages[e.Index];
            Rectangle retanguloAba = tabCtrl.GetTabRect(e.Index);

            // Define aqui a fonte que queres APENAS para os botões das abas
            Font fonteAba = new Font("Segoe UI", 11, FontStyle.Bold, GraphicsUnit.Point);

            // Configurar as escovas de cor para o estado normal e selecionado
            Brush pincelFundo;
            Brush pincelTexto;

            if (e.State == DrawItemState.Selected)
            {
                // Cor de fundo e texto para a aba ativa
                pincelFundo = new SolidBrush(Color.White);
                pincelTexto = new SolidBrush(Color.FromArgb(40, 40, 40));
            }
            else
            {
                // Cor de fundo e texto para as abas inactivas
                pincelFundo = new SolidBrush(Color.WhiteSmoke);
                pincelTexto = new SolidBrush(Color.Gray);
            }

            // 1. Pintar o fundo do botão da aba
            e.Graphics.FillRectangle(pincelFundo, retanguloAba);

            // 2. Configurar o alinhamento perfeitamente centrado (Horizontal e Vertical)
            StringFormat alinhamentoCentrado = new StringFormat
            {
                Alignment = StringAlignment.Center,
                LineAlignment = StringAlignment.Center
            };

            // 3. Desenhar o texto centrado com a fonte customizada
            e.Graphics.DrawString(pagina.Text, fonteAba, pincelTexto, retanguloAba, alinhamentoCentrado);

            // 4. Libertar recursos da memória
            alinhamentoCentrado.Dispose();
            fonteAba.Dispose();
            pincelFundo.Dispose();
            pincelTexto.Dispose();
        }

        private void CarregarGrelha()
        {
            try
            {
                DataTable dt = _tecidoRepository.ObterTodos();
                dgvTecidos.DataSource = dt;
                StandardizeColumns();
                dgvTecidos.AplicarEstiloModus();
                dgvTecidos.ClearSelection();
                LimparCampos();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao carregar os tecidos: {ex.Message}", "Erro de Base de Dados", MessageBoxButtons.OK, MessageBoxIcon.Error);
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
                        col.Name = "Id";
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
                        col.HeaderText = "Preço/m"; // O utilizador vê isto
                        break;
                    case "StockTecido":
                    case "quantidade_stock":
                    case "Quantidade":
                        col.Name = "Quantidade";
                        col.Width = 60;
                        col.HeaderText = "Stock";         // O utilizador vê isto
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
                        col.HeaderText = "Fornecedor (ID)";
                        col.Visible = true;
                        break;
                    default:
                        col.Width = 100;
                        break;
                }
            }
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

                txtNome.Text = GetCellValue("Nome");
                txtPreco.Text = GetCellValue("Preco");
                txtQuantidade.Text = GetCellValue("Quantidade");
                txtCodigo.Text = GetCellValue("Codigo");
                txtCor.Text = GetCellValue("Cor");
                txtTipo.Text = GetCellValue("Tipo");
                txtPadrao.Text = GetCellValue("Padrao");
                txtFornecedor.Text = GetCellValue("FornecedorID");
            }
        }

        private void btnAdicionarTecido_Click(object sender, EventArgs e)
        {
            if (!ValidarInputs(out decimal preco, out decimal quantidade)) return;

            try
            {
                _tecidoRepository.InserirTecido(
                    txtNome.Text.Trim(),
                    preco,
                    quantidade,
                    txtCodigo.Text.Trim(),
                    txtCor.Text.Trim(),
                    txtTipo.Text.Trim(),
                    txtPadrao.Text.Trim(),
                    txtFornecedor.Text.Trim()
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

            if (!ValidarInputs(out decimal preco, out decimal quantidade)) return;

            try
            {
                _tecidoRepository.AtualizarTecido(
                    _idSelecionado.Value,
                    txtNome.Text.Trim(),
                    preco,
                    quantidade,
                    txtCodigo.Text.Trim(),
                    txtCor.Text.Trim(),
                    txtTipo.Text.Trim(),
                    txtPadrao.Text.Trim(),
                    txtFornecedor.Text.Trim()
                );

                MessageBox.Show("Tecido atualizado com sucesso!", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                CarregarGrelha();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Erro ao atualizar tecido: {ex.Message}", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private bool ValidarInputs(out decimal preco, out decimal quantidade)
        {
            preco = 0;
            quantidade = 0;

            if (string.IsNullOrWhiteSpace(txtNome.Text))
            {
                VerAviso("O campo Nome é obrigatório.", txtNome);
                return false;
            }

            // Validar o parse decimal independentemente do culture info ("," ou ".")
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
            txtNome.Clear();
            txtPreco.Clear();
            txtQuantidade.Clear();
            txtCodigo.Clear();
            txtCor.Clear();
            txtTipo.Clear();
            txtPadrao.Clear();
            txtFornecedor.Clear();
        }

        private void dgvTecidos_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
