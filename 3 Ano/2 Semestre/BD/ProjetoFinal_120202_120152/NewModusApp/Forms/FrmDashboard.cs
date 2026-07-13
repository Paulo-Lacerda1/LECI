using NewModusApp.Repositories;
using System;
using System.Data;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmDashboard : Form
    {
        public FrmDashboard()
        {
            InitializeComponent();
        }

        private void FrmDashboard_Load(object sender, EventArgs e)
        {
            dtpInicio.Value = new DateTime(DateTime.Today.Year, DateTime.Today.Month, 1);
            dtpFim.Value = DateTime.Today;
            CarregarDashboard();
        }

        private void AtualizarFaturacao()
        {
            try
            {
                var repo = new DashboardRepository();
                decimal faturacao = chkFiltroDatas.Checked
                    ? repo.ObterFaturacaoPorIntervalo(dtpInicio.Value.Date, dtpFim.Value.Date)
                    : repo.ObterFaturacaoMesAtual();
                lblValorFaturacao.Text = faturacao.ToString("C2");
            }
            catch (Exception ex)
            {
                MessageBox.Show(
                    "Erro ao atualizar a faturação:\n\n" + ex.Message,
                    "Erro",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
            }
        }

        private void ChkFiltroDatas_CheckedChanged(object sender, EventArgs e)
        {
            dtpInicio.Enabled = chkFiltroDatas.Checked;
            dtpFim.Enabled = chkFiltroDatas.Checked;
            AtualizarFaturacao();
        }

        private void DtpInicio_ValueChanged(object sender, EventArgs e)
        {
            AtualizarFaturacao();
        }

        private void DtpFim_ValueChanged(object sender, EventArgs e)
        {
            AtualizarFaturacao();
        }

        private void CarregarDashboard()
        {
            try
            {
                var repo = new DashboardRepository();

                // KPI Cards
                AtualizarFaturacao();

                int encomendasAtivas = repo.ObterEncomendasAtivas();
                lblValorEncomendas.Text = encomendasAtivas.ToString();

                int totalStock = repo.ObterTotalPecasStock();
                lblValorStock.Text = totalStock.ToString();

                // Alertas de stock
                DataTable alertas = repo.ObterAlertasStock();
                dgvAlertasStock.DataSource = alertas;
                if (dgvAlertasStock.Columns.Contains("Nome"))
                    dgvAlertasStock.Columns["Nome"].Width = 180;
                if (dgvAlertasStock.Columns.Contains("Stock"))
                    dgvAlertasStock.Columns["Stock"].Width = 80;

                // Gráfico — receitas reais por categoria
                chartVendas.Series["Vendas"].Points.Clear();
                decimal receitaPV = repo.ObterReceitaProntoVestir();
                decimal receitaPM = repo.ObterReceitaPorMedida();
                chartVendas.Series["Vendas"].Points.AddXY("Pronto-a-Vestir", (double)receitaPV);
                chartVendas.Series["Vendas"].Points.AddXY("Por Medida", (double)receitaPM);
                chartVendas.Series["Vendas"].IsValueShownAsLabel = true;
                chartVendas.Series["Vendas"].LabelFormat = "C2";
            }
            catch (Exception ex)
            {
                MessageBox.Show(
                    "Erro ao carregar o Dashboard:\n\n" + ex.Message,
                    "Erro",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
            }
        }
    }
}
