using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmEncomendasPorMedidaMenu : Form
    {
        private readonly int? clienteInicial;

        public FrmEncomendasPorMedidaMenu()
            : this(null)
        {
        }

        public FrmEncomendasPorMedidaMenu(int? cliente)
        {
            clienteInicial = cliente;
            InitializeComponent();
        }

        private void BtnNovoPedido_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirNovoPedidoPorMedida(clienteInicial);
        }

        private void BtnPedidosPendentes_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirPedidosPendentesPorMedida(clienteInicial);
        }

        private void BtnHistorico_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirHistoricoPedidosPorMedida(clienteInicial);
        }

        private void BtnVoltar_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirEncomendas(clienteInicial);
        }

        private void BtnNovoPedido_Paint(object sender, PaintEventArgs e)
        {
            DesenharBotaoMenu(e.Graphics, btnNovoPedido, "Novo Pedido", "Fluxo guiado para escolher cliente, data de entrega e adicionar varios produtos ao pedido.");
        }

        private void BtnVerPedidos_Paint(object sender, PaintEventArgs e)
        {
            DesenharBotaoMenu(e.Graphics, btnVerPedidos, "Pedidos Pendentes", "Pedidos ainda em aberto, com opcao para marcar como concluida ou entregue.");
        }

        private void BtnHistorico_Paint(object sender, PaintEventArgs e)
        {
            DesenharBotaoMenu(e.Graphics, btnHistorico, "Ver historico", "Pedidos ja entregues, usados como historico final.");
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

        private void MostrarErro(string mensagem, Exception ex)
        {
            MessageBox.Show(mensagem + "\n\n" + ex.Message, "Erro de base de dados", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }
}
