using NewModusApp.Models;
using NewModusApp.Repositories;
using NewModusApp.Utils;
using System;
using System.Data;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmMedidas : Form
    {
        private readonly MedidasRepository medidasRepository = new MedidasRepository();
        private readonly int? clienteInicial;

        public FrmMedidas()
            : this(null)
        {
        }

        public FrmMedidas(int? cliente)
        {
            clienteInicial = cliente;

            InitializeComponent();
            CarregarClientes();
            AplicarClienteInicial();
            CarregarMedidas();
        }
        private void CarregarClientes()
        {
            try
            {
                DataTable clientes = medidasRepository.ListarClientes();
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

        private void AplicarClienteInicial()
        {
            if (!clienteInicial.HasValue)
                return;

            cmbCliente.SelectedValue = clienteInicial.Value;
            cmbFiltroCliente.SelectedValue = clienteInicial.Value;
        }

        private void CarregarMedidas()
        {
            try
            {
                int? clienteFiltro = ObterClienteFiltro();
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

                dgvMedidas.DataSource = medidasRepository.Pesquisar(clienteFiltro, dataInicio, dataFim);
                ConfigurarColunasGrid();
                dgvMedidas.AplicarEstiloModus();
                dgvMedidas.ClearSelection();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel carregar as medidas.", ex);
            }
        }

        private void ConfigurarColunasGrid()
        {
            if (dgvMedidas.Columns.Contains("ClienteID"))
                dgvMedidas.Columns["ClienteID"].Visible = false;

            if (dgvMedidas.Columns.Contains("Data"))
                dgvMedidas.Columns["Data"].DefaultCellStyle.Format = "dd/MM/yyyy";
        }

        private int? ObterClienteFiltro()
        {
            if (cmbFiltroCliente.SelectedValue == null || cmbFiltroCliente.SelectedValue == DBNull.Value)
                return null;

            return Convert.ToInt32(cmbFiltroCliente.SelectedValue);
        }

        private void ChkFiltrarData_CheckedChanged(object sender, EventArgs e)
        {
            dtpFiltroInicio.Enabled = chkFiltrarData.Checked;
            dtpFiltroFim.Enabled = chkFiltrarData.Checked;
        }

        private void BtnPesquisar_Click(object sender, EventArgs e)
        {
            CarregarMedidas();
        }

        private void BtnGuardar_Click(object sender, EventArgs e)
        {
            Medida medida;
            if (!TentarLerMedida(out medida, false))
                return;

            try
            {
                int idPerfil = medidasRepository.Criar(medida);

                MessageBox.Show(
                    "Medida inserida com sucesso. ID: " + idPerfil,
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarMedidas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel inserir a medida.", ex);
            }
        }

        private void BtnEditar_Click(object sender, EventArgs e)
        {
            Medida medida;
            if (!TentarLerMedida(out medida, true))
                return;

            try
            {
                medidasRepository.Atualizar(medida);

                MessageBox.Show(
                    "Medida atualizada com sucesso.",
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarMedidas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel atualizar a medida.", ex);
            }
        }

        private void BtnApagar_Click(object sender, EventArgs e)
        {
            int idPerfil;
            if (!int.TryParse(txtId.Text, out idPerfil))
            {
                MessageBox.Show(
                    "Seleciona uma medida primeiro.",
                    "Medida nao selecionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return;
            }

            DialogResult resposta = MessageBox.Show(
                "Tens a certeza que queres apagar esta medida?",
                "Confirmar eliminacao",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning
            );

            if (resposta != DialogResult.Yes)
                return;

            try
            {
                medidasRepository.Eliminar(idPerfil);

                MessageBox.Show(
                    "Medida apagada com sucesso.",
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarMedidas();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Nao foi possivel apagar a medida.", ex);
            }
        }

        private void BtnLimpar_Click(object sender, EventArgs e)
        {
            LimparCampos();
        }

        private bool TentarLerMedida(out Medida medida, bool exigeId)
        {
            medida = null;

            int idPerfil = 0;
            if (exigeId && !int.TryParse(txtId.Text, out idPerfil))
            {
                MessageBox.Show(
                    "Seleciona uma medida primeiro.",
                    "Medida nao selecionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            if (cmbCliente.SelectedValue == null || cmbCliente.SelectedValue == DBNull.Value)
            {
                MessageBox.Show(
                    "Seleciona o cliente.",
                    "Cliente obrigatorio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            string nomePerfil = txtNomePerfil.Text.Trim();
            if (nomePerfil == "")
            {
                MessageBox.Show(
                    "Preenche o nome do perfil.",
                    "Campo obrigatorio",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return false;
            }

            int braco;
            int costas;
            int peito;
            int cinta;
            int anca;

            if (!TentarLerInteiroNaoNegativo(txtBraco, "braco", out braco)
                || !TentarLerInteiroNaoNegativo(txtCostas, "costas", out costas)
                || !TentarLerInteiroNaoNegativo(txtPeito, "peito", out peito)
                || !TentarLerInteiroNaoNegativo(txtCinta, "cinta", out cinta)
                || !TentarLerInteiroNaoNegativo(txtAnca, "anca", out anca))
            {
                return false;
            }

            medida = new Medida
            {
                IdPerfil = idPerfil,
                NomePerfil = nomePerfil,
                Cliente = Convert.ToInt32(cmbCliente.SelectedValue),
                DataAtualizacao = dtpData.Value.Date,
                Braco = braco,
                Costas = costas,
                Peito = peito,
                Cinta = cinta,
                Anca = anca
            };

            return true;
        }

        private bool TentarLerInteiroNaoNegativo(TextBox textBox, string campo, out int valor)
        {
            valor = 0;

            if (!int.TryParse(textBox.Text.Trim(), out valor))
            {
                MessageBox.Show(
                    "O campo " + campo + " deve ser um numero inteiro.",
                    "Valor invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                textBox.Focus();
                return false;
            }

            if (valor < 0)
            {
                MessageBox.Show(
                    "O campo " + campo + " nao pode ser negativo.",
                    "Valor invalido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                textBox.Focus();
                return false;
            }

            return true;
        }

        private void DgvMedidas_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            DataGridViewRow row = dgvMedidas.Rows[e.RowIndex];

            txtId.Text = row.Cells["ID"].Value.ToString();
            txtNomePerfil.Text = row.Cells["Perfil"].Value.ToString();
            cmbCliente.SelectedValue = Convert.ToInt32(row.Cells["ClienteID"].Value);
            dtpData.Value = Convert.ToDateTime(row.Cells["Data"].Value);
            txtBraco.Text = row.Cells["Braco"].Value.ToString();
            txtCostas.Text = row.Cells["Costas"].Value.ToString();
            txtPeito.Text = row.Cells["Peito"].Value.ToString();
            txtCinta.Text = row.Cells["Cinta"].Value.ToString();
            txtAnca.Text = row.Cells["Anca"].Value.ToString();
        }

        private void DgvMedidas_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvMedidas.ClearSelection();
        }

        private void LimparCampos()
        {
            txtId.Clear();
            txtNomePerfil.Clear();
            cmbCliente.SelectedIndex = -1;
            dtpData.Value = DateTime.Today;
            txtBraco.Clear();
            txtCostas.Clear();
            txtPeito.Clear();
            txtCinta.Clear();
            txtAnca.Clear();

            if (clienteInicial.HasValue)
                cmbCliente.SelectedValue = clienteInicial.Value;

            dgvMedidas.ClearSelection();
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

        private void lblCliente_Click(object sender, EventArgs e)
        {

        }
    }
}
