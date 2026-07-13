using NewModusApp.Models;
using NewModusApp.Repositories;
using System;
using System.ComponentModel;
using System.Data;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmMateriaisPedidoPopup : Form
    {
        private readonly EncomendasRepository repository = new EncomendasRepository();

        public ItemEncomendaTecido TecidoSelecionado { get; private set; }
        public ItemEncomendaMaterial MaterialSelecionado { get; private set; }
        public bool Ignorado { get; private set; }

        public FrmMateriaisPedidoPopup()
        {
            InitializeComponent();
            Load += FrmMateriaisPedidoPopup_Load;
        }

        private void FrmMateriaisPedidoPopup_Load(object sender, EventArgs e)
        {
            if (LicenseManager.UsageMode == LicenseUsageMode.Designtime)
                return;

            CarregarDados();
        }

        private void CarregarDados()
        {
            try
            {
                DataTable tecidos = repository.ListarTecidosUso();
                cmbTecido.DataSource = tecidos;
                cmbTecido.DisplayMember = "Nome";
                cmbTecido.ValueMember = "ID";
                cmbTecido.SelectedIndex = tecidos.Rows.Count > 0 ? 0 : -1;

                DataTable materiais = repository.ListarMateriaisUso();
                cmbMaterial.DataSource = materiais;
                cmbMaterial.DisplayMember = "Nome";
                cmbMaterial.ValueMember = "ID";
                cmbMaterial.SelectedIndex = materiais.Rows.Count > 0 ? 0 : -1;
            }
            catch (Exception ex)
            {
                MessageBox.Show(
                    "Nao foi possivel carregar tecidos e materiais.\n\n" + ex.Message,
                    "Erro de base de dados",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error
                );
            }
        }

        private void BtnGuardar_Click(object sender, EventArgs e)
        {
            Ignorado = false;
            TecidoSelecionado = null;
            MaterialSelecionado = null;

            if (chkTecido.Checked)
            {
                int? tecido = ObterValorInteiroCombo(cmbTecido);
                if (!tecido.HasValue || nudMetros.Value <= 0)
                {
                    MessageBox.Show("Escolhe o tecido e indica metros superiores a zero.", "Tecido invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }

                decimal precoTecidoAtual;
                try
                {
                    precoTecidoAtual = repository.ObterPrecoAtualTecido(tecido.Value);
                }
                catch (Exception ex)
                {
                    MessageBox.Show(
                        "Nao foi possivel obter o preco atual do tecido.\n\n" + ex.Message,
                        "Erro de base de dados",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Error
                    );
                    return;
                }

                TecidoSelecionado = new ItemEncomendaTecido
                {
                    Tecido = tecido.Value,
                    MetrosUsados = nudMetros.Value,
                    PrecoCobrado = precoTecidoAtual
                };
            }

            if (chkMaterial.Checked)
            {
                int? material = ObterValorInteiroCombo(cmbMaterial);
                if (!material.HasValue || nudQuantidade.Value <= 0)
                {
                    MessageBox.Show("Escolhe o material e indica quantidade superior a zero.", "Material invalido", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }

                decimal precoMaterialAtual;
                try
                {
                    precoMaterialAtual = repository.ObterPrecoAtualMaterial(material.Value);
                }
                catch (Exception ex)
                {
                    MessageBox.Show(
                        "Nao foi possivel obter o custo atual do material.\n\n" + ex.Message,
                        "Erro de base de dados",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Error
                    );
                    return;
                }

                MaterialSelecionado = new ItemEncomendaMaterial
                {
                    Material = material.Value,
                    QuantidadeUsada = Convert.ToInt32(nudQuantidade.Value),
                    PrecoCobrado = precoMaterialAtual
                };
            }

            DialogResult = DialogResult.OK;
            Close();
        }

        private void BtnIgnorar_Click(object sender, EventArgs e)
        {
            Ignorado = true;
            TecidoSelecionado = null;
            MaterialSelecionado = null;
            DialogResult = DialogResult.OK;
            Close();
        }

        private int? ObterValorInteiroCombo(ComboBox combo)
        {
            object valor = combo.SelectedValue;
            if (valor == null || valor == DBNull.Value || valor is DataRowView)
                return null;

            return Convert.ToInt32(valor);
        }

        private void ChkTecido_CheckedChanged(object sender, EventArgs e)
        {
            cmbTecido.Enabled = chkTecido.Checked;
            nudMetros.Enabled = chkTecido.Checked;
        }

        private void ChkMaterial_CheckedChanged(object sender, EventArgs e)
        {
            cmbMaterial.Enabled = chkMaterial.Checked;
            nudQuantidade.Enabled = chkMaterial.Checked;
        }

    }
}
