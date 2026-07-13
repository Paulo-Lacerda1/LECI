using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmEncomendasMenu : Form
    {
        private readonly int? clienteInicial;

        public FrmEncomendasMenu()
            : this(null)
        {
        }

        public FrmEncomendasMenu(int? cliente)
        {
            clienteInicial = cliente;
            InitializeComponent();
            ConfigurarBotaoOpcao(
                btnPorMedida,
                "Por medida",
                "Criação de peças personalizadas para clientes com medidas."
            );
            ConfigurarBotaoOpcao(
                btnProntoVestir,
                "Pronto a vestir",
                "Venda de peças prontas em stock."
            );
        }

        private void ConfigurarBotaoOpcao(Button button, string titulo, string descricao)
        {
            button.Text = "";
            button.AccessibleName = titulo;
            button.Paint += (sender, e) => DesenharBotaoOpcao(e.Graphics, button, titulo, descricao);
        }

        private void DesenharBotaoOpcao(Graphics graphics, Button button, string titulo, string descricao)
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

        private void BtnPorMedida_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirEncomendasPorMedida(clienteInicial);
        }

        private void BtnProntoVestir_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirProntoVestir(clienteInicial);
        }

        private void BtnVoltar_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.MostrarPainelPrincipal();
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
