using NewModusApp.Repositories;
using NewModusApp.Utils;
using System;
using System.Data;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    public partial class FrmPedidosPorMedidaLista : Form
    {
        private readonly EncomendasRepository repository = new EncomendasRepository();
        private readonly int? clienteInicial;
        private readonly bool historico;

        public FrmPedidosPorMedidaLista(int? cliente, bool mostrarHistorico)
        {
            clienteInicial = cliente;
            historico = mostrarHistorico;
            InitializeComponent();
        }

        private void FrmPedidosPorMedidaLista_Load(object sender, EventArgs e)
        {
            CarregarPedidos();
        }

        private void CarregarPedidos()
        {
            try
            {
                dgvPedidos.DataSource = historico
                    ? repository.ListarHistorico(clienteInicial)
                    : repository.ListarPedidosPendentes(clienteInicial);

                ConfigurarColunasPedidos();
                dgvPedidos.AplicarEstiloModus();
                dgvPedidos.ClearSelection();
                dgvItens.DataSource = null;
                AtualizarEstadoBotoes();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel carregar os pedidos.", ex);
            }
        }

        private void ConfigurarColunasPedidos()
        {
            if (dgvPedidos.Columns.Contains("ClienteID"))
                dgvPedidos.Columns["ClienteID"].Visible = false;

            string[] datas = { "Data", "DataPrevista", "DataPronto", "DataEntrega" };
            foreach (string data in datas)
            {
                if (dgvPedidos.Columns.Contains(data))
                    dgvPedidos.Columns[data].DefaultCellStyle.Format = "dd/MM/yyyy";
            }

            if (dgvPedidos.Columns.Contains("ValorTotal"))
                dgvPedidos.Columns["ValorTotal"].DefaultCellStyle.Format = "N2";

            if (dgvPedidos.Columns.Contains("TotalItens"))
                dgvPedidos.Columns["TotalItens"].DefaultCellStyle.Format = "N2";
        }

        private void CarregarItensSelecionados()
        {
            int? idPedido = ObterPedidoSelecionado();
            if (!idPedido.HasValue)
                return;

            try
            {
                dgvItens.DataSource = historico
                    ? repository.ListarItens(idPedido.Value)
                    : repository.ListarItensComUso(idPedido.Value);
                ConfigurarColunasItens();
                dgvItens.AplicarEstiloModus();
                dgvItens.ClearSelection();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel carregar os produtos do pedido.", ex);
            }
        }

        private void ConfigurarColunasItens()
        {
            string[] ocultas = { "EncomendaID", "PerfilID", "ModeloID" };
            foreach (string coluna in ocultas)
            {
                if (dgvItens.Columns.Contains(coluna))
                    dgvItens.Columns[coluna].Visible = false;
            }

            if (dgvItens.Columns.Contains("Preco"))
                dgvItens.Columns["Preco"].DefaultCellStyle.Format = "N2";

            if (dgvItens.Columns.Contains("CustoProducao"))
                dgvItens.Columns["CustoProducao"].DefaultCellStyle.Format = "N2";

            if (dgvItens.Columns.Contains("Materiais"))
            {
                dgvItens.Columns["Materiais"].HeaderText = "Materiais";
                dgvItens.Columns["Materiais"].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter;
                dgvItens.Columns["Materiais"].MinimumWidth = 75;
            }
        }

        private int? ObterPedidoSelecionado()
        {
            if (dgvPedidos.CurrentRow == null)
                return null;

            return Convert.ToInt32(dgvPedidos.CurrentRow.Cells["ID"].Value);
        }

        private string ObterEstadoPedidoSelecionado()
        {
            if (dgvPedidos.CurrentRow == null || !dgvPedidos.Columns.Contains("Estado"))
                return null;

            object valor = dgvPedidos.CurrentRow.Cells["Estado"].Value;
            if (valor == null || valor == DBNull.Value)
                return null;

            return Convert.ToString(valor);
        }

        private bool PodeTransitar(string estadoAtual, string estadoDestino)
        {
            if (string.IsNullOrWhiteSpace(estadoAtual) || string.IsNullOrWhiteSpace(estadoDestino))
                return false;

            if (string.Equals(estadoAtual, estadoDestino, StringComparison.OrdinalIgnoreCase))
                return true;

            switch (estadoAtual)
            {
                case "Pendente":
                    return estadoDestino == "Em Produção" || estadoDestino == "Cancelada";
                case "Em Produção":
                    return estadoDestino == "Pronta" || estadoDestino == "Cancelada";
                case "Pronta":
                    return estadoDestino == "Entregue";
                case "Entregue":
                case "Cancelada":
                    return false;
                default:
                    return false;
            }
        }

        private void AtualizarEstadoBotoes()
        {
            if (historico)
                return;

            string estado = ObterEstadoPedidoSelecionado();

            btnEmProducao.Enabled = (estado == "Pendente");
            btnConcluida.Enabled  = (estado == "Em Produção");
            btnEntregue.Enabled   = (estado == "Pronta");
            btnCancelar.Enabled   = (estado == "Pendente" || estado == "Em Produção");
        }

        private int? ObterItemSelecionado()
        {
            if (dgvItens.CurrentRow == null)
                return null;

            return Convert.ToInt32(dgvItens.CurrentRow.Cells["ID"].Value);
        }

        private void MarcarSelecionado(bool entregue)
        {
            int? idPedido = ObterPedidoSelecionado();
            if (!idPedido.HasValue)
            {
                MessageBox.Show("Seleciona um pedido primeiro.", "Pedido nao selecionado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                if (entregue)
                    repository.MarcarComoEntregue(idPedido.Value);
                else
                    repository.MarcarComoPronta(idPedido.Value);

                CarregarPedidos();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel atualizar o estado do pedido.", ex);
            }
        }

        private void DgvPedidos_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            CarregarItensSelecionados();
            AtualizarEstadoBotoes();
        }

        private void DgvPedidos_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvPedidos.ClearSelection();
            AtualizarEstadoBotoes();
        }

        private void DgvItens_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvItens.ClearSelection();
        }

        private void BtnEmProducao_Click(object sender, EventArgs e)
        {
            int? idPedido = ObterPedidoSelecionado();
            if (!idPedido.HasValue)
            {
                MessageBox.Show("Seleciona um pedido primeiro.", "Pedido nao selecionado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            DialogResult confirma = MessageBox.Show(
                "Tem a certeza que deseja iniciar a producao?\nOs materiais serao descontados do stock.",
                "Iniciar Producao",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Question);

            if (confirma != DialogResult.Yes)
                return;

            try
            {
                repository.MarcarComoEmProducao(idPedido.Value);
                CarregarPedidos();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel atualizar o estado do pedido.", ex);
            }
        }

        private void BtnCancelar_Click(object sender, EventArgs e)
        {
            int? idPedido = ObterPedidoSelecionado();
            if (!idPedido.HasValue)
            {
                MessageBox.Show("Seleciona um pedido primeiro.", "Pedido nao selecionado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            DialogResult confirma = MessageBox.Show(
                "Tem a certeza que deseja cancelar este pedido? Esta acao nao pode ser desfeita.",
                "Cancelar Pedido",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning);

            if (confirma != DialogResult.Yes)
                return;

            try
            {
                repository.MarcarComoCancelada(idPedido.Value);
                CarregarPedidos();
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel cancelar o pedido.", ex);
            }
        }

        private void BtnConcluida_Click(object sender, EventArgs e)
        {
            MarcarSelecionado(false);
        }

        private void BtnEntregue_Click(object sender, EventArgs e)
        {
            MarcarSelecionado(true);
        }

        private void BtnAdicionarMateriais_Click(object sender, EventArgs e)
        {
            int? idItem = ObterItemSelecionado();
            if (!idItem.HasValue)
            {
                MessageBox.Show("Seleciona primeiro um produto do pedido na grelha de baixo.", "Produto nao selecionado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            using (FrmMateriaisPedidoPopup popup = new FrmMateriaisPedidoPopup())
            {
                if (popup.ShowDialog(this) != DialogResult.OK || popup.Ignorado)
                    return;

                try
                {
                    repository.AdicionarUsoItem(idItem.Value, popup.TecidoSelecionado, popup.MaterialSelecionado);
                    CarregarItensSelecionados();
                }
                catch (Exception ex)
                {
                    MostrarErro("Nao foi possivel guardar os materiais do produto.", ex);
                }
            }
        }

        private void BtnVerMateriais_Click(object sender, EventArgs e)
        {
            MostrarMateriaisSelecionados();
        }

        private void DgvItens_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            if (dgvItens.Columns[e.ColumnIndex].Name == "Materiais")
                MostrarMateriaisSelecionados();
        }

        private void MostrarMateriaisSelecionados()
        {
            int? idItem = ObterItemSelecionado();
            if (!idItem.HasValue)
            {
                MessageBox.Show("Seleciona primeiro um produto do pedido na grelha de baixo.", "Produto nao selecionado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                DataTable materiais = repository.ListarUsoItem(idItem.Value);
                using (FrmVerMateriaisPedidoPopup popup = new FrmVerMateriaisPedidoPopup(materiais))
                    popup.ShowDialog(this);
            }
            catch (Exception ex)
            {
                MostrarErro("Nao foi possivel carregar os materiais usados.", ex);
            }
        }

        private void BtnVoltar_Click(object sender, EventArgs e)
        {
            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
                principal.AbrirEncomendasPorMedida(clienteInicial);
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
