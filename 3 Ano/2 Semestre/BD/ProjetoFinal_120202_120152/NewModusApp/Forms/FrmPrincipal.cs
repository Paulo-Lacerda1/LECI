using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmPrincipal : Form
    {
        private Func<Form> formularioAtualFactory;

        public FrmPrincipal()
        {
            InitializeComponent();
            BtnDashboard_Click(null, EventArgs.Empty);
        }

        private void BtnDashboard_Click(object sender, EventArgs e)
        {
            lblTitulo.Text = "Dashboard";
            AbrirFormulario(() => new FrmDashboard());
        }

        private void BtnClientes_Click(object sender, EventArgs e)
        {
            lblTitulo.Text = "Clientes";
            AbrirFormulario(() => new FrmClientes());
        }

        private void BtnMedidas_Click(object sender, EventArgs e)
        {
            AbrirMedidas(null);
        }

        public void AbrirMedidas(int? idCliente)
        {
            lblTitulo.Text = "Perfis de Medida";
            AbrirFormulario(() => new FrmMedidas(idCliente));
        }

        private void BtnEncomendas_Click(object sender, EventArgs e)
        {
            AbrirEncomendas(null);
        }

        public void AbrirEncomendas(int? idCliente)
        {
            lblTitulo.Text = "Encomendas";
            AbrirFormulario(() => new FrmEncomendasMenu(idCliente));
        }

        public void AbrirEncomendasPorMedida(int? idCliente)
        {
            lblTitulo.Text = "Encomendas por Medida";
            AbrirFormulario(() => new FrmEncomendasPorMedidaMenu(idCliente));
        }

        public void AbrirNovoPedidoPorMedida(int? idCliente)
        {
            lblTitulo.Text = "Novo Pedido por Medida";
            AbrirFormulario(() => new FrmNovaEncomendaPorMedida(idCliente));
        }

        public void AbrirListaPedidosPorMedida(int? idCliente)
        {
            lblTitulo.Text = "Pedidos por Medida";
            AbrirFormulario(() => new FrmEncomendas(idCliente));
        }

        public void AbrirPedidosPendentesPorMedida(int? idCliente)
        {
            lblTitulo.Text = "Pedidos Pendentes";
            AbrirFormulario(() => new FrmPedidosPorMedidaLista(idCliente, false));
        }

        public void AbrirHistoricoPedidosPorMedida(int? idCliente)
        {
            lblTitulo.Text = "Historico de Pedidos";
            AbrirFormulario(() => new FrmPedidosPorMedidaLista(idCliente, true));
        }

        public void AbrirProntoVestir(int? idCliente)
        {
            lblTitulo.Text = "Pronto a Vestir";
            AbrirFormulario(() => new FrmProntoVestirMenu(idCliente));
        }

        public void AbrirNovaCompraProntoVestir(int? idCliente)
        {
            lblTitulo.Text = "Nova Compra Pronto a Vestir";
            AbrirFormulario(() => new FrmNovaCompraProntoVestir(idCliente));
        }

        public void AbrirHistoricoProntoVestir(int? idCliente)
        {
            lblTitulo.Text = "Historico Pronto a Vestir";
            AbrirFormulario(() => new FrmProntoVestir(idCliente));
        }

        private void BtnLojaStock_Click(object sender, EventArgs e)
        {
            lblTitulo.Text = "Inventário";
            AbrirFormulario(() => new FrmInventario());
        }

        private void BtnAtualizar_Click(object sender, EventArgs e)
        {
            if (formularioAtualFactory == null)
                return;

            AbrirFormulario(formularioAtualFactory);
        }

        private void BtnSair_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        public void MostrarPainelPrincipal()
        {
            formularioAtualFactory = null;
            lblTitulo.Text = "Painel Principal";
            MostrarMensagem("Ligação à base de dados estabelecida com sucesso.");
        }

        private void AbrirFormulario(Func<Form> formFactory)
        {
            formularioAtualFactory = formFactory;
            Form form = formFactory();

            panelConteudo.Controls.Clear();

            form.TopLevel = false;
            form.FormBorderStyle = FormBorderStyle.None;
            form.Dock = DockStyle.Fill;

            panelConteudo.Controls.Add(form);
            form.Show();
        }

        private void MostrarMensagem(string texto)
        {
            panelConteudo.Controls.Clear();
            lblMensagem.Text = texto;
            panelConteudo.Controls.Add(lblMensagem);
        }
    }
}
