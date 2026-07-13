using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Windows.Forms;
using NewModusApp.Repositories;
using NewModusApp.Utils;

namespace NewModusApp.Forms
{
    public partial class FrmInventario : Form
    {
        private readonly TecidoRepository _tecidoRepository;
        private int? _idSelecionado = null;

        // Guardar o índice da última aba aberta na memória da aplicação
        private static int _ultimaAbaSelecionada = 0;

        public FrmInventario()
        {
            InitializeComponent();
            _tecidoRepository = new TecidoRepository();
            dgvTecidos.DataBindingComplete += dgvTecidos_DataBindingComplete;
            InicializarControlosMateriais();
            panelFiltroCorDropdown.Visible = false;
            panelFiltroTipoDropdown.Visible = false;
            panelFiltroFornecedorDropdown.Visible = false;
            panelFiltroPadraoDropdown.Visible = false;
            tabStock.DrawMode = TabDrawMode.OwnerDrawFixed;
            tabStock.DrawItem += tabStock_DrawItem;
            clbFiltroCor.ItemCheck += new ItemCheckEventHandler(this.clbFiltroCor_ItemCheck);
            clbFiltroTipo.ItemCheck += new ItemCheckEventHandler(this.clbFiltroTipo_ItemCheck);
            clbFiltroFornecedor.ItemCheck += new ItemCheckEventHandler(this.clbFiltroFornecedor_ItemCheck);
            clbFiltroPadrao.ItemCheck += new ItemCheckEventHandler(this.clbFiltroPadrao_ItemCheck);
            btnPesquisarTecido.Click += new EventHandler(this.btnPesquisarTecido_Click);
            txtNome.TextChanged += AtualizarEstadoBotoesTecidos;
            txtCodigo.TextChanged += AtualizarEstadoBotoesTecidos;
            textBoxIDtecido.TextChanged += AtualizarEstadoBotoesTecidos;
            cmbCor.TextChanged += AtualizarEstadoBotoesTecidos;
            cmbTipo.TextChanged += AtualizarEstadoBotoesTecidos;
            cmbFornecedor.SelectedIndexChanged += AtualizarEstadoBotoesTecidos;
            AtualizarEstadoBotoesTecidos();
        }

        private void FrmInventario_Load(object sender, EventArgs e)
        {
            // Restaura a última aba antes de carregar os dados
            tabStock.SelectedIndex = _ultimaAbaSelecionada;

            CarregarGrelha();
            CarregarGrelhaMateriais();
            CarregarGrelhaProdutos();
        }

        private void tabStock_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Grava o índice sempre que o utilizador muda de separador
            _ultimaAbaSelecionada = tabStock.SelectedIndex;

            if (tabStock.SelectedIndex == 0)
            {
                CarregarGrelhaProdutos();
            }
            else if (tabStock.SelectedIndex == 2)
            {
                CarregarGrelhaMateriais();
            }
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
    }
}
