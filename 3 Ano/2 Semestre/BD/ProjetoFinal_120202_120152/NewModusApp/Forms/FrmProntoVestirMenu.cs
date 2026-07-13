using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmProntoVestirMenu : Form
    {
        private readonly int? clienteInicial;

        public FrmProntoVestirMenu()
            : this(null)
        {
        }

        public FrmProntoVestirMenu(int? cliente)
        {
            clienteInicial = cliente;
            InitializeComponent();
        }

        private void BtnNovaCompra_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirNovaCompraProntoVestir(clienteInicial);
        }

        private void BtnHistorico_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirHistoricoProntoVestir(clienteInicial);
        }

        private void BtnVoltar_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirEncomendas(clienteInicial);
        }

        private void BtnNovaCompra_Paint(object sender, PaintEventArgs e)
        {
            DesenharBotaoMenu(e.Graphics, btnNovaCompra, "Nova compra", "Registo rapido de uma venda, com cliente opcional, metodo de pagamento e produtos.");
        }

        private void BtnHistorico_Paint(object sender, PaintEventArgs e)
        {
            DesenharBotaoMenu(e.Graphics, btnHistorico, "Historico", "Consulta, pesquisa e gestao das compras de pronto a vestir ja registadas.");
        }

        private void DesenharBotaoMenu(Graphics graphics, Button button, string titulo, string descricao)
        {
            Rectangle tituloRect = new Rectangle(24, 18, button.Width - 48, 28);
            Rectangle descricaoRect = new Rectangle(24, 50, button.Width - 48, 26);

            using (Font tituloFont = new Font("Segoe UI", 15F, FontStyle.Bold))
            using (Font descricaoFont = new Font("Segoe UI", 10F, FontStyle.Regular))
            {
                TextRenderer.DrawText(
                    graphics,
                    titulo,
                    tituloFont,
                    tituloRect,
                    button.ForeColor,
                    TextFormatFlags.Left | TextFormatFlags.VerticalCenter | TextFormatFlags.EndEllipsis
                );

                TextRenderer.DrawText(
                    graphics,
                    descricao,
                    descricaoFont,
                    descricaoRect,
                    Color.FromArgb(75, 75, 75),
                    TextFormatFlags.Left | TextFormatFlags.VerticalCenter | TextFormatFlags.EndEllipsis
                );
            }
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
    }
}
