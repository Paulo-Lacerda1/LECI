namespace NewModusApp.Forms
{
    partial class FrmProntoVestir
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Panel panelBody;
        private System.Windows.Forms.Panel panelFormulario;
        private System.Windows.Forms.Panel panelDireita;
        private System.Windows.Forms.Panel panelPesquisa;
        private System.Windows.Forms.Panel panelAcoesDetalhes;
        private System.Windows.Forms.Panel panelDetalhes;
        private System.Windows.Forms.Label lblFormTitulo;
        private System.Windows.Forms.Label lblId;
        private System.Windows.Forms.Label lblCliente;
        private System.Windows.Forms.Label lblDataCompra;
        private System.Windows.Forms.Label lblMetodoPagamento;
        private System.Windows.Forms.Label lblValorTotal;
        private System.Windows.Forms.Label lblFiltroCliente;
        private System.Windows.Forms.Label lblFiltroPagamento;
        private System.Windows.Forms.Label lblFiltroInicio;
        private System.Windows.Forms.Label lblFiltroFim;
        private System.Windows.Forms.Label lblTituloDetalhes;
        private System.Windows.Forms.Label lblDetalheId;
        private System.Windows.Forms.Label lblProduto;
        private System.Windows.Forms.Label lblQuantidade;
        private System.Windows.Forms.Label lblPrecoUnitario;
        private System.Windows.Forms.DataGridView dgvCompras;
        private System.Windows.Forms.DataGridView dgvDetalhes;
        private System.Windows.Forms.TextBox txtId;
        private System.Windows.Forms.ComboBox cmbCliente;
        private System.Windows.Forms.DateTimePicker dtpDataCompra;
        private System.Windows.Forms.ComboBox cmbMetodoPagamento;
        private System.Windows.Forms.TextBox txtValorTotal;
        private System.Windows.Forms.ComboBox cmbFiltroCliente;
        private System.Windows.Forms.ComboBox cmbFiltroMetodoPagamento;
        private System.Windows.Forms.CheckBox chkFiltrarData;
        private System.Windows.Forms.DateTimePicker dtpFiltroInicio;
        private System.Windows.Forms.DateTimePicker dtpFiltroFim;
        private System.Windows.Forms.TextBox txtDetalheId;
        private System.Windows.Forms.ComboBox cmbProduto;
        private System.Windows.Forms.TextBox txtQuantidade;
        private System.Windows.Forms.TextBox txtPrecoUnitario;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.Button btnEditar;
        private System.Windows.Forms.Button btnApagar;
        private System.Windows.Forms.Button btnLimpar;
        private System.Windows.Forms.Button btnVoltar;
        private System.Windows.Forms.Button btnPesquisar;
        private System.Windows.Forms.Button btnVerDetalhes;
        private System.Windows.Forms.Button btnDetalheGuardar;
        private System.Windows.Forms.Button btnDetalheEditar;
        private System.Windows.Forms.Button btnDetalheApagar;

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
            this.lblDataCompra = new System.Windows.Forms.Label();
            this.dtpDataCompra = new System.Windows.Forms.DateTimePicker();
            this.lblMetodoPagamento = new System.Windows.Forms.Label();
            this.cmbMetodoPagamento = new System.Windows.Forms.ComboBox();
            this.lblValorTotal = new System.Windows.Forms.Label();
            this.txtValorTotal = new System.Windows.Forms.TextBox();
            this.btnGuardar = new System.Windows.Forms.Button();
            this.btnEditar = new System.Windows.Forms.Button();
            this.btnApagar = new System.Windows.Forms.Button();
            this.btnLimpar = new System.Windows.Forms.Button();
            this.btnVoltar = new System.Windows.Forms.Button();
            this.panelDireita = new System.Windows.Forms.Panel();
            this.panelPesquisa = new System.Windows.Forms.Panel();
            this.lblFiltroCliente = new System.Windows.Forms.Label();
            this.lblFiltroPagamento = new System.Windows.Forms.Label();
            this.lblFiltroInicio = new System.Windows.Forms.Label();
            this.lblFiltroFim = new System.Windows.Forms.Label();
            this.cmbFiltroCliente = new System.Windows.Forms.ComboBox();
            this.cmbFiltroMetodoPagamento = new System.Windows.Forms.ComboBox();
            this.chkFiltrarData = new System.Windows.Forms.CheckBox();
            this.dtpFiltroInicio = new System.Windows.Forms.DateTimePicker();
            this.dtpFiltroFim = new System.Windows.Forms.DateTimePicker();
            this.btnPesquisar = new System.Windows.Forms.Button();
            this.panelAcoesDetalhes = new System.Windows.Forms.Panel();
            this.btnVerDetalhes = new System.Windows.Forms.Button();
            this.panelDetalhes = new System.Windows.Forms.Panel();
            this.lblTituloDetalhes = new System.Windows.Forms.Label();
            this.lblDetalheId = new System.Windows.Forms.Label();
            this.txtDetalheId = new System.Windows.Forms.TextBox();
            this.lblProduto = new System.Windows.Forms.Label();
            this.cmbProduto = new System.Windows.Forms.ComboBox();
            this.lblQuantidade = new System.Windows.Forms.Label();
            this.txtQuantidade = new System.Windows.Forms.TextBox();
            this.lblPrecoUnitario = new System.Windows.Forms.Label();
            this.txtPrecoUnitario = new System.Windows.Forms.TextBox();
            this.btnDetalheGuardar = new System.Windows.Forms.Button();
            this.btnDetalheEditar = new System.Windows.Forms.Button();
            this.btnDetalheApagar = new System.Windows.Forms.Button();
            this.dgvDetalhes = new System.Windows.Forms.DataGridView();
            this.dgvCompras = new System.Windows.Forms.DataGridView();
            this.panelBody.SuspendLayout();
            this.panelFormulario.SuspendLayout();
            this.panelDireita.SuspendLayout();
            this.panelPesquisa.SuspendLayout();
            this.panelDetalhes.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvDetalhes)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvCompras)).BeginInit();
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
            this.panelFormulario.Controls.Add(this.btnVoltar);
            this.panelFormulario.Controls.Add(this.btnLimpar);
            this.panelFormulario.Controls.Add(this.btnApagar);
            this.panelFormulario.Controls.Add(this.btnEditar);
            this.panelFormulario.Controls.Add(this.btnGuardar);
            this.panelFormulario.Controls.Add(this.txtValorTotal);
            this.panelFormulario.Controls.Add(this.lblValorTotal);
            this.panelFormulario.Controls.Add(this.cmbMetodoPagamento);
            this.panelFormulario.Controls.Add(this.lblMetodoPagamento);
            this.panelFormulario.Controls.Add(this.dtpDataCompra);
            this.panelFormulario.Controls.Add(this.lblDataCompra);
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
            this.lblFormTitulo.Location = new System.Drawing.Point(62, 20);
            this.lblFormTitulo.Name = "lblFormTitulo";
            this.lblFormTitulo.Size = new System.Drawing.Size(258, 35);
            this.lblFormTitulo.Text = "Venda Pronto a Vestir";
            // 
            // lblId
            // 
            this.lblId.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblId.Location = new System.Drawing.Point(20, 75);
            this.lblId.Name = "lblId";
            this.lblId.Size = new System.Drawing.Size(300, 20);
            this.lblId.Text = "ID Compra";
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
            // 
            // lblDataCompra
            // 
            this.lblDataCompra.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblDataCompra.Location = new System.Drawing.Point(20, 203);
            this.lblDataCompra.Name = "lblDataCompra";
            this.lblDataCompra.Size = new System.Drawing.Size(300, 20);
            this.lblDataCompra.Text = "Data da compra";
            // 
            // dtpDataCompra
            // 
            this.dtpDataCompra.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpDataCompra.Location = new System.Drawing.Point(20, 225);
            this.dtpDataCompra.Name = "dtpDataCompra";
            this.dtpDataCompra.Size = new System.Drawing.Size(300, 30);
            this.dtpDataCompra.TabIndex = 2;
            // 
            // lblMetodoPagamento
            // 
            this.lblMetodoPagamento.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblMetodoPagamento.Location = new System.Drawing.Point(20, 267);
            this.lblMetodoPagamento.Name = "lblMetodoPagamento";
            this.lblMetodoPagamento.Size = new System.Drawing.Size(300, 20);
            this.lblMetodoPagamento.Text = "Metodo de pagamento";
            // 
            // cmbMetodoPagamento
            // 
            this.cmbMetodoPagamento.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbMetodoPagamento.FormattingEnabled = true;
            this.cmbMetodoPagamento.Location = new System.Drawing.Point(20, 289);
            this.cmbMetodoPagamento.Name = "cmbMetodoPagamento";
            this.cmbMetodoPagamento.Size = new System.Drawing.Size(300, 31);
            this.cmbMetodoPagamento.TabIndex = 3;
            // 
            // lblValorTotal
            // 
            this.lblValorTotal.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblValorTotal.Location = new System.Drawing.Point(20, 331);
            this.lblValorTotal.Name = "lblValorTotal";
            this.lblValorTotal.Size = new System.Drawing.Size(300, 20);
            this.lblValorTotal.Text = "Valor total";
            // 
            // txtValorTotal
            // 
            this.txtValorTotal.Location = new System.Drawing.Point(20, 353);
            this.txtValorTotal.Name = "txtValorTotal";
            this.txtValorTotal.ReadOnly = true;
            this.txtValorTotal.Size = new System.Drawing.Size(300, 30);
            this.txtValorTotal.TabIndex = 4;
            this.txtValorTotal.Text = "0,00";
            // 
            // btnGuardar
            // 
            this.btnGuardar.Location = new System.Drawing.Point(20, 403);
            this.btnGuardar.Name = "btnGuardar";
            this.btnGuardar.Size = new System.Drawing.Size(130, 34);
            this.btnGuardar.Text = "Guardar";
            this.btnGuardar.UseVisualStyleBackColor = true;
            this.btnGuardar.Click += new System.EventHandler(this.BtnGuardar_Click);
            // 
            // btnEditar
            // 
            this.btnEditar.Location = new System.Drawing.Point(180, 403);
            this.btnEditar.Name = "btnEditar";
            this.btnEditar.Size = new System.Drawing.Size(130, 34);
            this.btnEditar.Text = "Editar";
            this.btnEditar.UseVisualStyleBackColor = true;
            this.btnEditar.Click += new System.EventHandler(this.BtnEditar_Click);
            // 
            // btnApagar
            // 
            this.btnApagar.Location = new System.Drawing.Point(20, 448);
            this.btnApagar.Name = "btnApagar";
            this.btnApagar.Size = new System.Drawing.Size(130, 34);
            this.btnApagar.Text = "Apagar";
            this.btnApagar.UseVisualStyleBackColor = true;
            this.btnApagar.Click += new System.EventHandler(this.BtnApagar_Click);
            // 
            // btnLimpar
            // 
            this.btnLimpar.Location = new System.Drawing.Point(180, 448);
            this.btnLimpar.Name = "btnLimpar";
            this.btnLimpar.Size = new System.Drawing.Size(130, 34);
            this.btnLimpar.Text = "Limpar";
            this.btnLimpar.UseVisualStyleBackColor = true;
            this.btnLimpar.Click += new System.EventHandler(this.BtnLimpar_Click);
            // 
            // btnVoltar
            // 
            this.btnVoltar.BackColor = System.Drawing.Color.White;
            this.btnVoltar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVoltar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVoltar.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.btnVoltar.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnVoltar.Location = new System.Drawing.Point(20, 16);
            this.btnVoltar.Name = "btnVoltar";
            this.btnVoltar.Size = new System.Drawing.Size(36, 38);
            this.btnVoltar.Text = "<";
            this.btnVoltar.UseVisualStyleBackColor = false;
            this.btnVoltar.FlatAppearance.BorderSize = 0;
            this.btnVoltar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 245, 245);
            this.btnVoltar.Click += new System.EventHandler(this.BtnVoltar_Click);
            // 
            // panelDireita
            // 
            this.panelDireita.BackColor = System.Drawing.Color.White;
            this.panelDireita.Controls.Add(this.dgvCompras);
            this.panelDireita.Controls.Add(this.panelAcoesDetalhes);
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
            this.panelPesquisa.Controls.Add(this.cmbFiltroMetodoPagamento);
            this.panelPesquisa.Controls.Add(this.lblFiltroPagamento);
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
            // lblFiltroPagamento
            // 
            this.lblFiltroPagamento.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblFiltroPagamento.Location = new System.Drawing.Point(270, 0);
            this.lblFiltroPagamento.Name = "lblFiltroPagamento";
            this.lblFiltroPagamento.Size = new System.Drawing.Size(120, 20);
            this.lblFiltroPagamento.Text = "Pagamento";
            // 
            // cmbFiltroMetodoPagamento
            // 
            this.cmbFiltroMetodoPagamento.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbFiltroMetodoPagamento.FormattingEnabled = true;
            this.cmbFiltroMetodoPagamento.Location = new System.Drawing.Point(270, 24);
            this.cmbFiltroMetodoPagamento.Name = "cmbFiltroMetodoPagamento";
            this.cmbFiltroMetodoPagamento.Size = new System.Drawing.Size(160, 31);
            this.cmbFiltroMetodoPagamento.TabIndex = 1;
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
            // panelAcoesDetalhes
            // 
            this.panelAcoesDetalhes.BackColor = System.Drawing.Color.White;
            this.panelAcoesDetalhes.Controls.Add(this.btnVerDetalhes);
            this.panelAcoesDetalhes.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panelAcoesDetalhes.Location = new System.Drawing.Point(20, 538);
            this.panelAcoesDetalhes.Name = "panelAcoesDetalhes";
            this.panelAcoesDetalhes.Size = new System.Drawing.Size(570, 52);
            this.panelAcoesDetalhes.TabIndex = 2;
            // 
            // btnVerDetalhes
            // 
            this.btnVerDetalhes.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnVerDetalhes.BackColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnVerDetalhes.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVerDetalhes.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVerDetalhes.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnVerDetalhes.ForeColor = System.Drawing.Color.White;
            this.btnVerDetalhes.Location = new System.Drawing.Point(430, 10);
            this.btnVerDetalhes.Name = "btnVerDetalhes";
            this.btnVerDetalhes.Size = new System.Drawing.Size(140, 34);
            this.btnVerDetalhes.Text = "Ver detalhes";
            this.btnVerDetalhes.UseVisualStyleBackColor = false;
            this.btnVerDetalhes.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnVerDetalhes.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(25, 25, 25);
            this.btnVerDetalhes.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(65, 65, 65);
            this.btnVerDetalhes.Click += new System.EventHandler(this.BtnVerDetalhes_Click);
            // 
            // panelDetalhes
            // 
            this.panelDetalhes.BackColor = System.Drawing.Color.White;
            this.panelDetalhes.Controls.Add(this.dgvDetalhes);
            this.panelDetalhes.Controls.Add(this.btnDetalheApagar);
            this.panelDetalhes.Controls.Add(this.btnDetalheEditar);
            this.panelDetalhes.Controls.Add(this.btnDetalheGuardar);
            this.panelDetalhes.Controls.Add(this.txtPrecoUnitario);
            this.panelDetalhes.Controls.Add(this.lblPrecoUnitario);
            this.panelDetalhes.Controls.Add(this.txtQuantidade);
            this.panelDetalhes.Controls.Add(this.lblQuantidade);
            this.panelDetalhes.Controls.Add(this.cmbProduto);
            this.panelDetalhes.Controls.Add(this.lblProduto);
            this.panelDetalhes.Controls.Add(this.txtDetalheId);
            this.panelDetalhes.Controls.Add(this.lblDetalheId);
            this.panelDetalhes.Controls.Add(this.lblTituloDetalhes);
            this.panelDetalhes.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panelDetalhes.Location = new System.Drawing.Point(20, 355);
            this.panelDetalhes.Name = "panelDetalhes";
            this.panelDetalhes.Size = new System.Drawing.Size(570, 235);
            this.panelDetalhes.TabIndex = 2;
            // 
            // lblTituloDetalhes
            // 
            this.lblTituloDetalhes.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold);
            this.lblTituloDetalhes.ForeColor = System.Drawing.Color.FromArgb(40, 40, 40);
            this.lblTituloDetalhes.Location = new System.Drawing.Point(0, 0);
            this.lblTituloDetalhes.Name = "lblTituloDetalhes";
            this.lblTituloDetalhes.Size = new System.Drawing.Size(250, 28);
            this.lblTituloDetalhes.Text = "Produtos da venda";
            // 
            // lblDetalheId
            // 
            this.lblDetalheId.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblDetalheId.Location = new System.Drawing.Point(0, 30);
            this.lblDetalheId.Name = "lblDetalheId";
            this.lblDetalheId.Size = new System.Drawing.Size(55, 18);
            this.lblDetalheId.Text = "ID";
            // 
            // txtDetalheId
            // 
            this.txtDetalheId.Location = new System.Drawing.Point(0, 52);
            this.txtDetalheId.Name = "txtDetalheId";
            this.txtDetalheId.ReadOnly = true;
            this.txtDetalheId.Size = new System.Drawing.Size(55, 30);
            this.txtDetalheId.TabIndex = 0;
            // 
            // lblProduto
            // 
            this.lblProduto.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblProduto.Location = new System.Drawing.Point(65, 30);
            this.lblProduto.Name = "lblProduto";
            this.lblProduto.Size = new System.Drawing.Size(260, 18);
            this.lblProduto.Text = "Produto";
            // 
            // cmbProduto
            // 
            this.cmbProduto.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbProduto.FormattingEnabled = true;
            this.cmbProduto.Location = new System.Drawing.Point(65, 52);
            this.cmbProduto.Name = "cmbProduto";
            this.cmbProduto.Size = new System.Drawing.Size(260, 31);
            this.cmbProduto.TabIndex = 1;
            // 
            // lblQuantidade
            // 
            this.lblQuantidade.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblQuantidade.Location = new System.Drawing.Point(335, 30);
            this.lblQuantidade.Name = "lblQuantidade";
            this.lblQuantidade.Size = new System.Drawing.Size(70, 18);
            this.lblQuantidade.Text = "Qtd";
            // 
            // txtQuantidade
            // 
            this.txtQuantidade.Location = new System.Drawing.Point(335, 52);
            this.txtQuantidade.Name = "txtQuantidade";
            this.txtQuantidade.Size = new System.Drawing.Size(70, 30);
            this.txtQuantidade.TabIndex = 2;
            // 
            // lblPrecoUnitario
            // 
            this.lblPrecoUnitario.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblPrecoUnitario.Location = new System.Drawing.Point(415, 30);
            this.lblPrecoUnitario.Name = "lblPrecoUnitario";
            this.lblPrecoUnitario.Size = new System.Drawing.Size(90, 18);
            this.lblPrecoUnitario.Text = "Preco";
            // 
            // txtPrecoUnitario
            // 
            this.txtPrecoUnitario.Location = new System.Drawing.Point(415, 52);
            this.txtPrecoUnitario.Name = "txtPrecoUnitario";
            this.txtPrecoUnitario.Size = new System.Drawing.Size(90, 30);
            this.txtPrecoUnitario.TabIndex = 3;
            // 
            // btnDetalheGuardar
            // 
            this.btnDetalheGuardar.Location = new System.Drawing.Point(0, 88);
            this.btnDetalheGuardar.Name = "btnDetalheGuardar";
            this.btnDetalheGuardar.Size = new System.Drawing.Size(100, 30);
            this.btnDetalheGuardar.Text = "Adicionar";
            this.btnDetalheGuardar.UseVisualStyleBackColor = true;
            this.btnDetalheGuardar.Click += new System.EventHandler(this.BtnDetalheGuardar_Click);
            // 
            // btnDetalheEditar
            // 
            this.btnDetalheEditar.Location = new System.Drawing.Point(110, 88);
            this.btnDetalheEditar.Name = "btnDetalheEditar";
            this.btnDetalheEditar.Size = new System.Drawing.Size(80, 30);
            this.btnDetalheEditar.Text = "Editar";
            this.btnDetalheEditar.UseVisualStyleBackColor = true;
            this.btnDetalheEditar.Click += new System.EventHandler(this.BtnDetalheEditar_Click);
            // 
            // btnDetalheApagar
            // 
            this.btnDetalheApagar.Location = new System.Drawing.Point(195, 88);
            this.btnDetalheApagar.Name = "btnDetalheApagar";
            this.btnDetalheApagar.Size = new System.Drawing.Size(80, 30);
            this.btnDetalheApagar.Text = "Apagar";
            this.btnDetalheApagar.UseVisualStyleBackColor = true;
            this.btnDetalheApagar.Click += new System.EventHandler(this.BtnDetalheApagar_Click);
            // 
            // dgvDetalhes
            // 
            this.dgvDetalhes.AllowUserToAddRows = false;
            this.dgvDetalhes.AllowUserToDeleteRows = false;
            this.dgvDetalhes.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Top)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.dgvDetalhes.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvDetalhes.BackgroundColor = System.Drawing.Color.White;
            this.dgvDetalhes.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvDetalhes.ColumnHeadersHeight = 30;
            this.dgvDetalhes.EnableHeadersVisualStyles = false;
            this.dgvDetalhes.Location = new System.Drawing.Point(0, 128);
            this.dgvDetalhes.MultiSelect = false;
            this.dgvDetalhes.Name = "dgvDetalhes";
            this.dgvDetalhes.ReadOnly = true;
            this.dgvDetalhes.RowHeadersVisible = false;
            this.dgvDetalhes.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvDetalhes.Size = new System.Drawing.Size(900, 80);
            this.dgvDetalhes.TabIndex = 4;
            this.dgvDetalhes.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvDetalhes_CellClick);
            this.dgvDetalhes.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvDetalhes_DataBindingComplete);
            // 
            // dgvCompras
            // 
            this.dgvCompras.AllowUserToAddRows = false;
            this.dgvCompras.AllowUserToDeleteRows = false;
            this.dgvCompras.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvCompras.BackgroundColor = System.Drawing.Color.White;
            this.dgvCompras.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvCompras.ColumnHeadersHeight = 35;
            this.dgvCompras.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvCompras.EnableHeadersVisualStyles = false;
            this.dgvCompras.Location = new System.Drawing.Point(20, 140);
            this.dgvCompras.MultiSelect = false;
            this.dgvCompras.Name = "dgvCompras";
            this.dgvCompras.ReadOnly = true;
            this.dgvCompras.RowHeadersVisible = false;
            this.dgvCompras.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvCompras.Size = new System.Drawing.Size(570, 398);
            this.dgvCompras.TabIndex = 1;
            this.dgvCompras.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvCompras_CellClick);
            this.dgvCompras.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvCompras_DataBindingComplete);
            // 
            // FrmProntoVestir
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1000, 650);
            this.Controls.Add(this.panelBody);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.MinimumSize = new System.Drawing.Size(900, 600);
            this.Name = "FrmProntoVestir";
            this.Text = "Pronto a Vestir";
            this.panelBody.ResumeLayout(false);
            this.panelFormulario.ResumeLayout(false);
            this.panelFormulario.PerformLayout();
            this.panelDireita.ResumeLayout(false);
            this.panelPesquisa.ResumeLayout(false);
            this.panelDetalhes.ResumeLayout(false);
            this.panelDetalhes.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvDetalhes)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvCompras)).EndInit();
            this.ResumeLayout(false);

        }
    }
}
