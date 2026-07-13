namespace NewModusApp.Forms
{
    partial class FrmEncomendas
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Panel panelBody;
        private System.Windows.Forms.Panel panelFormulario;
        private System.Windows.Forms.Panel panelDireita;
        private System.Windows.Forms.Panel panelPesquisa;
        private System.Windows.Forms.Panel panelItens;
        private System.Windows.Forms.Label lblFormTitulo;
        private System.Windows.Forms.Label lblId;
        private System.Windows.Forms.Label lblCliente;
        private System.Windows.Forms.Label lblData;
        private System.Windows.Forms.Label lblEstado;
        private System.Windows.Forms.Label lblValorTotal;
        private System.Windows.Forms.Label lblFiltroCliente;
        private System.Windows.Forms.Label lblFiltroEstado;
        private System.Windows.Forms.Label lblFiltroInicio;
        private System.Windows.Forms.Label lblFiltroFim;
        private System.Windows.Forms.Label lblTituloItens;
        private System.Windows.Forms.Label lblItemId;
        private System.Windows.Forms.Label lblPerfilMedida;
        private System.Windows.Forms.Label lblModelo;
        private System.Windows.Forms.Label lblTamanho;
        private System.Windows.Forms.Label lblPrecoItem;
        private System.Windows.Forms.Label lblTipoPeca;
        private System.Windows.Forms.Label lblCustoProducao;
        private System.Windows.Forms.Label lblDescricaoPersonalizacao;
        private System.Windows.Forms.DataGridView dgvEncomendas;
        private System.Windows.Forms.DataGridView dgvItens;
        private System.Windows.Forms.TextBox txtId;
        private System.Windows.Forms.ComboBox cmbCliente;
        private System.Windows.Forms.DateTimePicker dtpDataEncomenda;
        private System.Windows.Forms.ComboBox cmbEstado;
        private System.Windows.Forms.TextBox txtValorTotal;
        private System.Windows.Forms.CheckBox chkDataPrevista;
        private System.Windows.Forms.DateTimePicker dtpDataPrevista;
        private System.Windows.Forms.CheckBox chkDataPronto;
        private System.Windows.Forms.DateTimePicker dtpDataPronto;
        private System.Windows.Forms.CheckBox chkDataRealEntrega;
        private System.Windows.Forms.DateTimePicker dtpDataRealEntrega;
        private System.Windows.Forms.ComboBox cmbFiltroCliente;
        private System.Windows.Forms.ComboBox cmbFiltroEstado;
        private System.Windows.Forms.CheckBox chkFiltrarData;
        private System.Windows.Forms.DateTimePicker dtpFiltroInicio;
        private System.Windows.Forms.DateTimePicker dtpFiltroFim;
        private System.Windows.Forms.TextBox txtItemId;
        private System.Windows.Forms.ComboBox cmbPerfilMedida;
        private System.Windows.Forms.ComboBox cmbModelo;
        private System.Windows.Forms.TextBox txtTamanho;
        private System.Windows.Forms.TextBox txtPrecoItem;
        private System.Windows.Forms.TextBox txtTipoPeca;
        private System.Windows.Forms.TextBox txtCustoProducao;
        private System.Windows.Forms.TextBox txtDescricaoPersonalizacao;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.Button btnEditar;
        private System.Windows.Forms.Button btnApagar;
        private System.Windows.Forms.Button btnLimpar;
        private System.Windows.Forms.Button btnVoltar;
        private System.Windows.Forms.Button btnPesquisar;
        private System.Windows.Forms.Button btnItemGuardar;
        private System.Windows.Forms.Button btnItemEditar;
        private System.Windows.Forms.Button btnItemApagar;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }

            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.panelBody = new System.Windows.Forms.Panel();
            this.panelFormulario = new System.Windows.Forms.Panel();
            this.lblFormTitulo = new System.Windows.Forms.Label();
            this.lblId = new System.Windows.Forms.Label();
            this.txtId = new System.Windows.Forms.TextBox();
            this.lblCliente = new System.Windows.Forms.Label();
            this.cmbCliente = new System.Windows.Forms.ComboBox();
            this.lblData = new System.Windows.Forms.Label();
            this.dtpDataEncomenda = new System.Windows.Forms.DateTimePicker();
            this.lblEstado = new System.Windows.Forms.Label();
            this.cmbEstado = new System.Windows.Forms.ComboBox();
            this.lblValorTotal = new System.Windows.Forms.Label();
            this.txtValorTotal = new System.Windows.Forms.TextBox();
            this.chkDataPrevista = new System.Windows.Forms.CheckBox();
            this.dtpDataPrevista = new System.Windows.Forms.DateTimePicker();
            this.chkDataPronto = new System.Windows.Forms.CheckBox();
            this.dtpDataPronto = new System.Windows.Forms.DateTimePicker();
            this.chkDataRealEntrega = new System.Windows.Forms.CheckBox();
            this.dtpDataRealEntrega = new System.Windows.Forms.DateTimePicker();
            this.btnGuardar = new System.Windows.Forms.Button();
            this.btnEditar = new System.Windows.Forms.Button();
            this.btnApagar = new System.Windows.Forms.Button();
            this.btnLimpar = new System.Windows.Forms.Button();
            this.btnVoltar = new System.Windows.Forms.Button();
            this.panelDireita = new System.Windows.Forms.Panel();
            this.panelPesquisa = new System.Windows.Forms.Panel();
            this.lblFiltroCliente = new System.Windows.Forms.Label();
            this.lblFiltroEstado = new System.Windows.Forms.Label();
            this.lblFiltroInicio = new System.Windows.Forms.Label();
            this.lblFiltroFim = new System.Windows.Forms.Label();
            this.cmbFiltroCliente = new System.Windows.Forms.ComboBox();
            this.cmbFiltroEstado = new System.Windows.Forms.ComboBox();
            this.chkFiltrarData = new System.Windows.Forms.CheckBox();
            this.dtpFiltroInicio = new System.Windows.Forms.DateTimePicker();
            this.dtpFiltroFim = new System.Windows.Forms.DateTimePicker();
            this.btnPesquisar = new System.Windows.Forms.Button();
            this.panelItens = new System.Windows.Forms.Panel();
            this.lblTituloItens = new System.Windows.Forms.Label();
            this.lblItemId = new System.Windows.Forms.Label();
            this.txtItemId = new System.Windows.Forms.TextBox();
            this.lblPerfilMedida = new System.Windows.Forms.Label();
            this.cmbPerfilMedida = new System.Windows.Forms.ComboBox();
            this.lblModelo = new System.Windows.Forms.Label();
            this.cmbModelo = new System.Windows.Forms.ComboBox();
            this.lblTamanho = new System.Windows.Forms.Label();
            this.txtTamanho = new System.Windows.Forms.TextBox();
            this.lblPrecoItem = new System.Windows.Forms.Label();
            this.txtPrecoItem = new System.Windows.Forms.TextBox();
            this.lblTipoPeca = new System.Windows.Forms.Label();
            this.txtTipoPeca = new System.Windows.Forms.TextBox();
            this.lblCustoProducao = new System.Windows.Forms.Label();
            this.txtCustoProducao = new System.Windows.Forms.TextBox();
            this.lblDescricaoPersonalizacao = new System.Windows.Forms.Label();
            this.txtDescricaoPersonalizacao = new System.Windows.Forms.TextBox();
            this.btnItemGuardar = new System.Windows.Forms.Button();
            this.btnItemEditar = new System.Windows.Forms.Button();
            this.btnItemApagar = new System.Windows.Forms.Button();
            this.dgvItens = new System.Windows.Forms.DataGridView();
            this.dgvEncomendas = new System.Windows.Forms.DataGridView();
            this.panelBody.SuspendLayout();
            this.panelFormulario.SuspendLayout();
            this.panelDireita.SuspendLayout();
            this.panelPesquisa.SuspendLayout();
            this.panelItens.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvItens)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvEncomendas)).BeginInit();
            this.SuspendLayout();
            // 
            // panelBody
            // 
            this.panelBody.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelBody.Controls.Add(this.panelDireita);
            this.panelBody.Controls.Add(this.panelFormulario);
            this.panelBody.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelBody.Location = new System.Drawing.Point(0, 0);
            this.panelBody.Name = "panelBody";
            this.panelBody.Padding = new System.Windows.Forms.Padding(20);
            this.panelBody.Size = new System.Drawing.Size(1000, 650);
            this.panelBody.TabIndex = 0;
            // 
            // panelFormulario
            // 
            this.panelFormulario.BackColor = System.Drawing.Color.White;
            this.panelFormulario.Controls.Add(this.btnLimpar);
            this.panelFormulario.Controls.Add(this.btnApagar);
            this.panelFormulario.Controls.Add(this.btnEditar);
            this.panelFormulario.Controls.Add(this.btnGuardar);
            this.panelFormulario.Controls.Add(this.btnVoltar);
            this.panelFormulario.Controls.Add(this.dtpDataRealEntrega);
            this.panelFormulario.Controls.Add(this.chkDataRealEntrega);
            this.panelFormulario.Controls.Add(this.dtpDataPronto);
            this.panelFormulario.Controls.Add(this.chkDataPronto);
            this.panelFormulario.Controls.Add(this.dtpDataPrevista);
            this.panelFormulario.Controls.Add(this.chkDataPrevista);
            this.panelFormulario.Controls.Add(this.txtValorTotal);
            this.panelFormulario.Controls.Add(this.lblValorTotal);
            this.panelFormulario.Controls.Add(this.cmbEstado);
            this.panelFormulario.Controls.Add(this.lblEstado);
            this.panelFormulario.Controls.Add(this.dtpDataEncomenda);
            this.panelFormulario.Controls.Add(this.lblData);
            this.panelFormulario.Controls.Add(this.cmbCliente);
            this.panelFormulario.Controls.Add(this.lblCliente);
            this.panelFormulario.Controls.Add(this.txtId);
            this.panelFormulario.Controls.Add(this.lblId);
            this.panelFormulario.Controls.Add(this.lblFormTitulo);
            this.panelFormulario.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelFormulario.Location = new System.Drawing.Point(20, 20);
            this.panelFormulario.Name = "panelFormulario";
            this.panelFormulario.Padding = new System.Windows.Forms.Padding(20);
            this.panelFormulario.AutoScroll = true;
            this.panelFormulario.Size = new System.Drawing.Size(350, 610);
            this.panelFormulario.TabIndex = 0;
            // 
            // lblFormTitulo
            // 
            this.lblFormTitulo.Font = new System.Drawing.Font("Segoe UI", 15F, System.Drawing.FontStyle.Bold);
            this.lblFormTitulo.ForeColor = System.Drawing.Color.FromArgb(40, 40, 40);
            this.lblFormTitulo.Location = new System.Drawing.Point(66, 20);
            this.lblFormTitulo.Name = "lblFormTitulo";
            this.lblFormTitulo.Size = new System.Drawing.Size(254, 35);
            this.lblFormTitulo.Text = "Dados da Encomenda";
            // 
            // btnVoltar
            // 
            this.btnVoltar.BackColor = System.Drawing.Color.White;
            this.btnVoltar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVoltar.FlatAppearance.BorderSize = 0;
            this.btnVoltar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 245, 245);
            this.btnVoltar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVoltar.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.btnVoltar.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnVoltar.Location = new System.Drawing.Point(20, 16);
            this.btnVoltar.Name = "btnVoltar";
            this.btnVoltar.Size = new System.Drawing.Size(36, 38);
            this.btnVoltar.Text = "<";
            this.btnVoltar.UseVisualStyleBackColor = false;
            this.btnVoltar.Click += new System.EventHandler(this.BtnVoltar_Click);
            // 
            // lblId
            // 
            this.lblId.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblId.Location = new System.Drawing.Point(20, 75);
            this.lblId.Name = "lblId";
            this.lblId.Size = new System.Drawing.Size(300, 20);
            this.lblId.Text = "ID Encomenda";
            // 
            // txtId
            // 
            this.txtId.Location = new System.Drawing.Point(20, 97);
            this.txtId.Name = "txtId";
            this.txtId.ReadOnly = true;
            this.txtId.Size = new System.Drawing.Size(300, 30);
            this.txtId.TabIndex = 0;
            // 
            // lblCliente
            // 
            this.lblCliente.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblCliente.Location = new System.Drawing.Point(20, 139);
            this.lblCliente.Name = "lblCliente";
            this.lblCliente.Size = new System.Drawing.Size(300, 20);
            this.lblCliente.Text = "Cliente";
            // 
            // cmbCliente
            // 
            this.cmbCliente.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbCliente.FormattingEnabled = true;
            this.cmbCliente.Location = new System.Drawing.Point(20, 161);
            this.cmbCliente.Name = "cmbCliente";
            this.cmbCliente.Size = new System.Drawing.Size(300, 31);
            this.cmbCliente.TabIndex = 1;
            this.cmbCliente.SelectedIndexChanged += new System.EventHandler(this.CmbCliente_SelectedIndexChanged);
            // 
            // lblData
            // 
            this.lblData.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblData.Location = new System.Drawing.Point(20, 203);
            this.lblData.Name = "lblData";
            this.lblData.Size = new System.Drawing.Size(300, 20);
            this.lblData.Text = "Data da encomenda";
            // 
            // dtpDataEncomenda
            // 
            this.dtpDataEncomenda.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpDataEncomenda.Location = new System.Drawing.Point(20, 225);
            this.dtpDataEncomenda.Name = "dtpDataEncomenda";
            this.dtpDataEncomenda.Size = new System.Drawing.Size(300, 30);
            this.dtpDataEncomenda.TabIndex = 2;
            // 
            // lblEstado
            // 
            this.lblEstado.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblEstado.Location = new System.Drawing.Point(20, 267);
            this.lblEstado.Name = "lblEstado";
            this.lblEstado.Size = new System.Drawing.Size(300, 20);
            this.lblEstado.Text = "Estado";
            // 
            // cmbEstado
            // 
            this.cmbEstado.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbEstado.FormattingEnabled = true;
            this.cmbEstado.Location = new System.Drawing.Point(20, 289);
            this.cmbEstado.Name = "cmbEstado";
            this.cmbEstado.Size = new System.Drawing.Size(300, 31);
            this.cmbEstado.TabIndex = 3;
            // 
            // lblValorTotal
            // 
            this.lblValorTotal.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblValorTotal.Location = new System.Drawing.Point(20, 331);
            this.lblValorTotal.Name = "lblValorTotal";
            this.lblValorTotal.Size = new System.Drawing.Size(300, 20);
            this.lblValorTotal.Text = "Valor total (€)";
            // 
            // txtValorTotal
            // 
            this.txtValorTotal.Location = new System.Drawing.Point(20, 353);
            this.txtValorTotal.Name = "txtValorTotal";
            this.txtValorTotal.Size = new System.Drawing.Size(300, 30);
            this.txtValorTotal.TabIndex = 4;
            // 
            // chkDataPrevista
            // 
            this.chkDataPrevista.Location = new System.Drawing.Point(20, 395);
            this.chkDataPrevista.Name = "chkDataPrevista";
            this.chkDataPrevista.Size = new System.Drawing.Size(300, 24);
            this.chkDataPrevista.Text = "Data prevista de entrega";
            this.chkDataPrevista.UseVisualStyleBackColor = true;
            // 
            // dtpDataPrevista
            // 
            this.dtpDataPrevista.Enabled = false;
            this.dtpDataPrevista.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpDataPrevista.Location = new System.Drawing.Point(20, 421);
            this.dtpDataPrevista.Name = "dtpDataPrevista";
            this.dtpDataPrevista.Size = new System.Drawing.Size(300, 30);
            this.dtpDataPrevista.TabIndex = 5;
            // 
            // chkDataPronto
            // 
            this.chkDataPronto.Location = new System.Drawing.Point(20, 463);
            this.chkDataPronto.Name = "chkDataPronto";
            this.chkDataPronto.Size = new System.Drawing.Size(300, 24);
            this.chkDataPronto.Text = "Data pronto";
            this.chkDataPronto.UseVisualStyleBackColor = true;
            // 
            // dtpDataPronto
            // 
            this.dtpDataPronto.Enabled = false;
            this.dtpDataPronto.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpDataPronto.Location = new System.Drawing.Point(20, 489);
            this.dtpDataPronto.Name = "dtpDataPronto";
            this.dtpDataPronto.Size = new System.Drawing.Size(300, 30);
            this.dtpDataPronto.TabIndex = 6;
            // 
            // chkDataRealEntrega
            // 
            this.chkDataRealEntrega.Location = new System.Drawing.Point(20, 531);
            this.chkDataRealEntrega.Name = "chkDataRealEntrega";
            this.chkDataRealEntrega.Size = new System.Drawing.Size(300, 24);
            this.chkDataRealEntrega.Text = "Data real de entrega";
            this.chkDataRealEntrega.UseVisualStyleBackColor = true;
            // 
            // dtpDataRealEntrega
            // 
            this.dtpDataRealEntrega.Enabled = false;
            this.dtpDataRealEntrega.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpDataRealEntrega.Location = new System.Drawing.Point(20, 557);
            this.dtpDataRealEntrega.Name = "dtpDataRealEntrega";
            this.dtpDataRealEntrega.Size = new System.Drawing.Size(300, 30);
            this.dtpDataRealEntrega.TabIndex = 7;
            // 
            // btnGuardar
            // 
            this.btnGuardar.Location = new System.Drawing.Point(20, 599);
            this.btnGuardar.Name = "btnGuardar";
            this.btnGuardar.Size = new System.Drawing.Size(130, 34);
            this.btnGuardar.Text = "Guardar";
            this.btnGuardar.UseVisualStyleBackColor = true;
            this.btnGuardar.Click += new System.EventHandler(this.BtnGuardar_Click);
            // 
            // btnEditar
            // 
            this.btnEditar.Location = new System.Drawing.Point(180, 599);
            this.btnEditar.Name = "btnEditar";
            this.btnEditar.Size = new System.Drawing.Size(130, 34);
            this.btnEditar.Text = "Editar";
            this.btnEditar.UseVisualStyleBackColor = true;
            this.btnEditar.Click += new System.EventHandler(this.BtnEditar_Click);
            // 
            // btnApagar
            // 
            this.btnApagar.Location = new System.Drawing.Point(20, 644);
            this.btnApagar.Name = "btnApagar";
            this.btnApagar.Size = new System.Drawing.Size(130, 34);
            this.btnApagar.Text = "Apagar";
            this.btnApagar.UseVisualStyleBackColor = true;
            this.btnApagar.Click += new System.EventHandler(this.BtnApagar_Click);
            // 
            // btnLimpar
            // 
            this.btnLimpar.Location = new System.Drawing.Point(180, 644);
            this.btnLimpar.Name = "btnLimpar";
            this.btnLimpar.Size = new System.Drawing.Size(130, 34);
            this.btnLimpar.Text = "Limpar";
            this.btnLimpar.UseVisualStyleBackColor = true;
            this.btnLimpar.Click += new System.EventHandler(this.BtnLimpar_Click);
            // 
            // panelDireita
            // 
            this.panelDireita.BackColor = System.Drawing.Color.White;
            this.panelDireita.Controls.Add(this.dgvEncomendas);
            this.panelDireita.Controls.Add(this.panelItens);
            this.panelDireita.Controls.Add(this.panelPesquisa);
            this.panelDireita.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelDireita.Location = new System.Drawing.Point(370, 20);
            this.panelDireita.Name = "panelDireita";
            this.panelDireita.Padding = new System.Windows.Forms.Padding(20);
            this.panelDireita.Size = new System.Drawing.Size(610, 610);
            this.panelDireita.TabIndex = 1;
            // 
            // panelPesquisa
            // 
            this.panelPesquisa.BackColor = System.Drawing.Color.White;
            this.panelPesquisa.Controls.Add(this.btnPesquisar);
            this.panelPesquisa.Controls.Add(this.dtpFiltroFim);
            this.panelPesquisa.Controls.Add(this.lblFiltroFim);
            this.panelPesquisa.Controls.Add(this.dtpFiltroInicio);
            this.panelPesquisa.Controls.Add(this.lblFiltroInicio);
            this.panelPesquisa.Controls.Add(this.chkFiltrarData);
            this.panelPesquisa.Controls.Add(this.cmbFiltroEstado);
            this.panelPesquisa.Controls.Add(this.lblFiltroEstado);
            this.panelPesquisa.Controls.Add(this.cmbFiltroCliente);
            this.panelPesquisa.Controls.Add(this.lblFiltroCliente);
            this.panelPesquisa.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelPesquisa.Location = new System.Drawing.Point(20, 20);
            this.panelPesquisa.Name = "panelPesquisa";
            this.panelPesquisa.Size = new System.Drawing.Size(570, 120);
            this.panelPesquisa.TabIndex = 0;
            // 
            // lblFiltroCliente
            // 
            this.lblFiltroCliente.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblFiltroCliente.Location = new System.Drawing.Point(0, 0);
            this.lblFiltroCliente.Name = "lblFiltroCliente";
            this.lblFiltroCliente.Size = new System.Drawing.Size(120, 20);
            this.lblFiltroCliente.Text = "Cliente";
            // 
            // cmbFiltroCliente
            // 
            this.cmbFiltroCliente.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbFiltroCliente.FormattingEnabled = true;
            this.cmbFiltroCliente.Location = new System.Drawing.Point(0, 24);
            this.cmbFiltroCliente.Name = "cmbFiltroCliente";
            this.cmbFiltroCliente.Size = new System.Drawing.Size(260, 31);
            this.cmbFiltroCliente.TabIndex = 0;
            // 
            // lblFiltroEstado
            // 
            this.lblFiltroEstado.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblFiltroEstado.Location = new System.Drawing.Point(270, 0);
            this.lblFiltroEstado.Name = "lblFiltroEstado";
            this.lblFiltroEstado.Size = new System.Drawing.Size(120, 20);
            this.lblFiltroEstado.Text = "Estado";
            // 
            // cmbFiltroEstado
            // 
            this.cmbFiltroEstado.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbFiltroEstado.FormattingEnabled = true;
            this.cmbFiltroEstado.Location = new System.Drawing.Point(270, 24);
            this.cmbFiltroEstado.Name = "cmbFiltroEstado";
            this.cmbFiltroEstado.Size = new System.Drawing.Size(150, 31);
            this.cmbFiltroEstado.TabIndex = 1;
            // 
            // chkFiltrarData
            // 
            this.chkFiltrarData.Location = new System.Drawing.Point(0, 59);
            this.chkFiltrarData.Name = "chkFiltrarData";
            this.chkFiltrarData.Size = new System.Drawing.Size(180, 25);
            this.chkFiltrarData.Text = "Filtrar por data";
            this.chkFiltrarData.UseVisualStyleBackColor = true;
            this.chkFiltrarData.CheckedChanged += new System.EventHandler(this.ChkFiltrarData_CheckedChanged);
            // 
            // lblFiltroInicio
            // 
            this.lblFiltroInicio.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblFiltroInicio.Location = new System.Drawing.Point(0, 64);
            this.lblFiltroInicio.Name = "lblFiltroInicio";
            this.lblFiltroInicio.Size = new System.Drawing.Size(120, 20);
            this.lblFiltroInicio.Text = "Inicio";
            // 
            // dtpFiltroInicio
            // 
            this.dtpFiltroInicio.Enabled = false;
            this.dtpFiltroInicio.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpFiltroInicio.Location = new System.Drawing.Point(0, 86);
            this.dtpFiltroInicio.Name = "dtpFiltroInicio";
            this.dtpFiltroInicio.Size = new System.Drawing.Size(120, 30);
            this.dtpFiltroInicio.TabIndex = 2;
            // 
            // lblFiltroFim
            // 
            this.lblFiltroFim.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblFiltroFim.Location = new System.Drawing.Point(130, 64);
            this.lblFiltroFim.Name = "lblFiltroFim";
            this.lblFiltroFim.Size = new System.Drawing.Size(120, 20);
            this.lblFiltroFim.Text = "Fim";
            // 
            // dtpFiltroFim
            // 
            this.dtpFiltroFim.Enabled = false;
            this.dtpFiltroFim.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpFiltroFim.Location = new System.Drawing.Point(130, 86);
            this.dtpFiltroFim.Name = "dtpFiltroFim";
            this.dtpFiltroFim.Size = new System.Drawing.Size(120, 30);
            this.dtpFiltroFim.TabIndex = 3;
            // 
            // btnPesquisar
            // 
            this.btnPesquisar.Location = new System.Drawing.Point(270, 85);
            this.btnPesquisar.Name = "btnPesquisar";
            this.btnPesquisar.Size = new System.Drawing.Size(90, 32);
            this.btnPesquisar.Text = "Pesquisar";
            this.btnPesquisar.UseVisualStyleBackColor = true;
            this.btnPesquisar.Click += new System.EventHandler(this.BtnPesquisar_Click);
            // 
            // panelItens
            // 
            this.panelItens.BackColor = System.Drawing.Color.White;
            this.panelItens.Controls.Add(this.dgvItens);
            this.panelItens.Controls.Add(this.btnItemApagar);
            this.panelItens.Controls.Add(this.btnItemEditar);
            this.panelItens.Controls.Add(this.btnItemGuardar);
            this.panelItens.Controls.Add(this.txtDescricaoPersonalizacao);
            this.panelItens.Controls.Add(this.lblDescricaoPersonalizacao);
            this.panelItens.Controls.Add(this.txtCustoProducao);
            this.panelItens.Controls.Add(this.lblCustoProducao);
            this.panelItens.Controls.Add(this.txtTipoPeca);
            this.panelItens.Controls.Add(this.lblTipoPeca);
            this.panelItens.Controls.Add(this.txtPrecoItem);
            this.panelItens.Controls.Add(this.lblPrecoItem);
            this.panelItens.Controls.Add(this.txtTamanho);
            this.panelItens.Controls.Add(this.lblTamanho);
            this.panelItens.Controls.Add(this.cmbModelo);
            this.panelItens.Controls.Add(this.lblModelo);
            this.panelItens.Controls.Add(this.cmbPerfilMedida);
            this.panelItens.Controls.Add(this.lblPerfilMedida);
            this.panelItens.Controls.Add(this.txtItemId);
            this.panelItens.Controls.Add(this.lblItemId);
            this.panelItens.Controls.Add(this.lblTituloItens);
            this.panelItens.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panelItens.Location = new System.Drawing.Point(20, 375);
            this.panelItens.Name = "panelItens";
            this.panelItens.AutoScroll = true;
            this.panelItens.Size = new System.Drawing.Size(570, 215);
            this.panelItens.TabIndex = 2;
            // 
            // lblTituloItens
            // 
            this.lblTituloItens.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold);
            this.lblTituloItens.ForeColor = System.Drawing.Color.FromArgb(40, 40, 40);
            this.lblTituloItens.Location = new System.Drawing.Point(0, 0);
            this.lblTituloItens.Name = "lblTituloItens";
            this.lblTituloItens.Size = new System.Drawing.Size(250, 28);
            this.lblTituloItens.Text = "Itens da encomenda";
            // 
            // lblItemId
            // 
            this.lblItemId.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblItemId.Location = new System.Drawing.Point(0, 30);
            this.lblItemId.Name = "lblItemId";
            this.lblItemId.Size = new System.Drawing.Size(55, 18);
            this.lblItemId.Text = "ID";
            // 
            // txtItemId
            // 
            this.txtItemId.Location = new System.Drawing.Point(0, 52);
            this.txtItemId.Name = "txtItemId";
            this.txtItemId.ReadOnly = true;
            this.txtItemId.Size = new System.Drawing.Size(55, 30);
            this.txtItemId.TabIndex = 0;
            // 
            // lblPerfilMedida
            // 
            this.lblPerfilMedida.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblPerfilMedida.Location = new System.Drawing.Point(65, 30);
            this.lblPerfilMedida.Name = "lblPerfilMedida";
            this.lblPerfilMedida.Size = new System.Drawing.Size(210, 18);
            this.lblPerfilMedida.Text = "Perfil";
            // 
            // cmbPerfilMedida
            // 
            this.cmbPerfilMedida.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbPerfilMedida.FormattingEnabled = true;
            this.cmbPerfilMedida.Location = new System.Drawing.Point(65, 52);
            this.cmbPerfilMedida.Name = "cmbPerfilMedida";
            this.cmbPerfilMedida.Size = new System.Drawing.Size(210, 31);
            this.cmbPerfilMedida.TabIndex = 1;
            // 
            // lblModelo
            // 
            this.lblModelo.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblModelo.Location = new System.Drawing.Point(285, 30);
            this.lblModelo.Name = "lblModelo";
            this.lblModelo.Size = new System.Drawing.Size(190, 18);
            this.lblModelo.Text = "Modelo";
            // 
            // cmbModelo
            // 
            this.cmbModelo.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbModelo.FormattingEnabled = true;
            this.cmbModelo.Location = new System.Drawing.Point(285, 52);
            this.cmbModelo.Name = "cmbModelo";
            this.cmbModelo.Size = new System.Drawing.Size(190, 31);
            this.cmbModelo.TabIndex = 2;
            // 
            // lblTamanho
            // 
            this.lblTamanho.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblTamanho.Location = new System.Drawing.Point(485, 30);
            this.lblTamanho.Name = "lblTamanho";
            this.lblTamanho.Size = new System.Drawing.Size(70, 18);
            this.lblTamanho.Text = "Tamanho";
            // 
            // txtTamanho
            // 
            this.txtTamanho.Location = new System.Drawing.Point(485, 52);
            this.txtTamanho.Name = "txtTamanho";
            this.txtTamanho.Size = new System.Drawing.Size(70, 30);
            this.txtTamanho.TabIndex = 3;
            // 
            // lblPrecoItem
            // 
            this.lblPrecoItem.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblPrecoItem.Location = new System.Drawing.Point(565, 30);
            this.lblPrecoItem.Name = "lblPrecoItem";
            this.lblPrecoItem.Size = new System.Drawing.Size(80, 18);
            this.lblPrecoItem.Text = "Preco";
            // 
            // txtPrecoItem
            // 
            this.txtPrecoItem.Location = new System.Drawing.Point(565, 52);
            this.txtPrecoItem.Name = "txtPrecoItem";
            this.txtPrecoItem.Size = new System.Drawing.Size(80, 30);
            this.txtPrecoItem.TabIndex = 4;
            // 
            // lblTipoPeca
            // 
            this.lblTipoPeca.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblTipoPeca.Location = new System.Drawing.Point(655, 30);
            this.lblTipoPeca.Name = "lblTipoPeca";
            this.lblTipoPeca.Size = new System.Drawing.Size(90, 18);
            this.lblTipoPeca.Text = "Tipo";
            // 
            // txtTipoPeca
            // 
            this.txtTipoPeca.Location = new System.Drawing.Point(655, 52);
            this.txtTipoPeca.Name = "txtTipoPeca";
            this.txtTipoPeca.Size = new System.Drawing.Size(90, 30);
            this.txtTipoPeca.TabIndex = 5;
            // 
            // lblCustoProducao
            // 
            this.lblCustoProducao.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblCustoProducao.Location = new System.Drawing.Point(755, 30);
            this.lblCustoProducao.Name = "lblCustoProducao";
            this.lblCustoProducao.Size = new System.Drawing.Size(80, 18);
            this.lblCustoProducao.Text = "Custo";
            // 
            // txtCustoProducao
            // 
            this.txtCustoProducao.Location = new System.Drawing.Point(755, 52);
            this.txtCustoProducao.Name = "txtCustoProducao";
            this.txtCustoProducao.Size = new System.Drawing.Size(80, 30);
            this.txtCustoProducao.TabIndex = 6;
            // 
            // lblDescricaoPersonalizacao
            // 
            this.lblDescricaoPersonalizacao.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblDescricaoPersonalizacao.Location = new System.Drawing.Point(0, 80);
            this.lblDescricaoPersonalizacao.Name = "lblDescricaoPersonalizacao";
            this.lblDescricaoPersonalizacao.Size = new System.Drawing.Size(275, 18);
            this.lblDescricaoPersonalizacao.Text = "Descricao";
            // 
            // txtDescricaoPersonalizacao
            // 
            this.txtDescricaoPersonalizacao.Location = new System.Drawing.Point(0, 102);
            this.txtDescricaoPersonalizacao.Name = "txtDescricaoPersonalizacao";
            this.txtDescricaoPersonalizacao.Size = new System.Drawing.Size(275, 30);
            this.txtDescricaoPersonalizacao.TabIndex = 7;
            // 
            // btnItemGuardar
            // 
            this.btnItemGuardar.Location = new System.Drawing.Point(285, 101);
            this.btnItemGuardar.Name = "btnItemGuardar";
            this.btnItemGuardar.Size = new System.Drawing.Size(100, 30);
            this.btnItemGuardar.Text = "Adicionar";
            this.btnItemGuardar.UseVisualStyleBackColor = true;
            this.btnItemGuardar.Click += new System.EventHandler(this.BtnItemGuardar_Click);
            // 
            // btnItemEditar
            // 
            this.btnItemEditar.Location = new System.Drawing.Point(395, 101);
            this.btnItemEditar.Name = "btnItemEditar";
            this.btnItemEditar.Size = new System.Drawing.Size(80, 30);
            this.btnItemEditar.Text = "Editar";
            this.btnItemEditar.UseVisualStyleBackColor = true;
            this.btnItemEditar.Click += new System.EventHandler(this.BtnItemEditar_Click);
            // 
            // btnItemApagar
            // 
            this.btnItemApagar.Location = new System.Drawing.Point(480, 101);
            this.btnItemApagar.Name = "btnItemApagar";
            this.btnItemApagar.Size = new System.Drawing.Size(80, 30);
            this.btnItemApagar.Text = "Apagar";
            this.btnItemApagar.UseVisualStyleBackColor = true;
            this.btnItemApagar.Click += new System.EventHandler(this.BtnItemApagar_Click);
            // 
            // dgvItens
            // 
            this.dgvItens.AllowUserToAddRows = false;
            this.dgvItens.AllowUserToDeleteRows = false;
            this.dgvItens.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Top)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.dgvItens.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvItens.BackgroundColor = System.Drawing.Color.White;
            this.dgvItens.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvItens.ColumnHeadersHeight = 30;
            this.dgvItens.EnableHeadersVisualStyles = false;
            this.dgvItens.Location = new System.Drawing.Point(0, 140);
            this.dgvItens.MultiSelect = false;
            this.dgvItens.Name = "dgvItens";
            this.dgvItens.ReadOnly = true;
            this.dgvItens.RowHeadersVisible = false;
            this.dgvItens.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvItens.Size = new System.Drawing.Size(900, 80);
            this.dgvItens.TabIndex = 8;
            this.dgvItens.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvItens_CellClick);
            this.dgvItens.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvItens_DataBindingComplete);
            // 
            // dgvEncomendas
            // 
            this.dgvEncomendas.AllowUserToAddRows = false;
            this.dgvEncomendas.AllowUserToDeleteRows = false;
            this.dgvEncomendas.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvEncomendas.BackgroundColor = System.Drawing.Color.White;
            this.dgvEncomendas.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvEncomendas.ColumnHeadersHeight = 35;
            this.dgvEncomendas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvEncomendas.EnableHeadersVisualStyles = false;
            this.dgvEncomendas.Location = new System.Drawing.Point(20, 140);
            this.dgvEncomendas.MultiSelect = false;
            this.dgvEncomendas.Name = "dgvEncomendas";
            this.dgvEncomendas.ReadOnly = true;
            this.dgvEncomendas.RowHeadersVisible = false;
            this.dgvEncomendas.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvEncomendas.Size = new System.Drawing.Size(570, 235);
            this.dgvEncomendas.TabIndex = 1;
            this.dgvEncomendas.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvEncomendas_CellClick);
            this.dgvEncomendas.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvEncomendas_DataBindingComplete);
            // 
            // FrmEncomendas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1000, 650);
            this.Controls.Add(this.panelBody);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.MinimumSize = new System.Drawing.Size(900, 600);
            this.Name = "FrmEncomendas";
            this.Text = "Encomendas";
            this.panelBody.ResumeLayout(false);
            this.panelFormulario.ResumeLayout(false);
            this.panelFormulario.PerformLayout();
            this.panelDireita.ResumeLayout(false);
            this.panelPesquisa.ResumeLayout(false);
            this.panelItens.ResumeLayout(false);
            this.panelItens.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvItens)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvEncomendas)).EndInit();
            this.ResumeLayout(false);

        }
    }
}
