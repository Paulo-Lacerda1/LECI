using NewModusApp.Utils;
using System.Data;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmVerMateriaisPedidoPopup : Form
    {
        private readonly DataTable dados;

        public FrmVerMateriaisPedidoPopup()
            : this(null)
        {
        }

        public FrmVerMateriaisPedidoPopup(DataTable materiais)
        {
            dados = materiais;
            InitializeComponent();
            dgv.DataSource = dados;
            lblVazio.Visible = dados == null || dados.Rows.Count == 0;
            dgv.AplicarEstiloModus();

            if (dgv.Columns.Contains("Quantidade"))
                dgv.Columns["Quantidade"].DefaultCellStyle.Format = "N2";
        }
    }
}
