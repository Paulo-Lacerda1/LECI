namespace NewModusApp.Forms
{
    partial class FrmInventario
    {
        private System.ComponentModel.IContainer components = null;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        private void InitializeComponent()
        {
            this.tabStock = new System.Windows.Forms.TabControl();
            this.tabProdutos = new System.Windows.Forms.TabPage();
            this.tabTecidos = new System.Windows.Forms.TabPage();
            this.panelDireitaTecidos = new System.Windows.Forms.Panel();
            this.dgvTecidos = new System.Windows.Forms.DataGridView();
            this.panelPesquisaTecidos = new System.Windows.Forms.Panel();
            this.grpFiltrosTecido = new System.Windows.Forms.GroupBox();
            this.btnPesquisarTecido = new System.Windows.Forms.Button();
            this.btnFiltroPadraoDropdown = new System.Windows.Forms.Button();
            this.lblFiltroPadrao = new System.Windows.Forms.Label();
            this.btnFiltroFornecedorDropdown = new System.Windows.Forms.Button();
            this.lblFiltroFornecedor = new System.Windows.Forms.Label();
            this.btnFiltroTipoDropdown = new System.Windows.Forms.Button();
            this.lblFiltroTipo = new System.Windows.Forms.Label();
            this.btnFiltroCorDropdown = new System.Windows.Forms.Button();
            this.lblFiltroCor = new System.Windows.Forms.Label();
            this.txtFiltroPrecoMax = new System.Windows.Forms.TextBox();
            this.txtFiltroPrecoMin = new System.Windows.Forms.TextBox();
            this.lblFiltroPrecoSeparador = new System.Windows.Forms.Label();
            this.lblFiltroPreco = new System.Windows.Forms.Label();
            this.txtFiltroNome = new System.Windows.Forms.TextBox();
            this.lblFiltroNome = new System.Windows.Forms.Label();
            this.panelFiltroCorDropdown = new System.Windows.Forms.Panel();
            this.clbFiltroCor = new System.Windows.Forms.CheckedListBox();
            this.panelFiltroTipoDropdown = new System.Windows.Forms.Panel();
            this.clbFiltroTipo = new System.Windows.Forms.CheckedListBox();
            this.panelFiltroFornecedorDropdown = new System.Windows.Forms.Panel();
            this.clbFiltroFornecedor = new System.Windows.Forms.CheckedListBox();
            this.panelFiltroPadraoDropdown = new System.Windows.Forms.Panel();
            this.clbFiltroPadrao = new System.Windows.Forms.CheckedListBox();
            this.panelInputsTecidos = new System.Windows.Forms.Panel();
            this.cmbFornecedor = new System.Windows.Forms.ComboBox();
            this.btnAdicionarTecido = new System.Windows.Forms.Button();
            this.cmbTipo = new System.Windows.Forms.ComboBox();
            this.cmbCor = new System.Windows.Forms.ComboBox();
            this.labelIDTecido = new System.Windows.Forms.Label();
            this.textBoxIDtecido = new System.Windows.Forms.TextBox();
            this.lblTituloTecidos = new System.Windows.Forms.Label();
            this.lblNome = new System.Windows.Forms.Label();
            this.txtNome = new System.Windows.Forms.TextBox();
            this.lblPreco = new System.Windows.Forms.Label();
            this.txtPreco = new System.Windows.Forms.TextBox();
            this.lblQuantidade = new System.Windows.Forms.Label();
            this.txtQuantidade = new System.Windows.Forms.TextBox();
            this.lblCodigo = new System.Windows.Forms.Label();
            this.txtCodigo = new System.Windows.Forms.TextBox();
            this.lblCor = new System.Windows.Forms.Label();
            this.lblTipo = new System.Windows.Forms.Label();
            this.lblPadrao = new System.Windows.Forms.Label();
            this.cmbPadrao = new System.Windows.Forms.ComboBox();
            this.lblFornecedor = new System.Windows.Forms.Label();
            this.btnLimparTecido = new System.Windows.Forms.Button();
            this.btnAtualizarTecido = new System.Windows.Forms.Button();
            this.tabMaterial = new System.Windows.Forms.TabPage();
            this.panelDireitaMateriais = new System.Windows.Forms.Panel();
            this.dgvMateriais = new System.Windows.Forms.DataGridView();
            this.panelPesquisaMateriais = new System.Windows.Forms.Panel();
            this.grpFiltrosMateriais = new System.Windows.Forms.GroupBox();
            this.btnPesquisarMaterial = new System.Windows.Forms.Button();
            this.btnFiltroFornecedorMaterialDropdown = new System.Windows.Forms.Button();
            this.lblFiltroFornecedorMaterial = new System.Windows.Forms.Label();
            this.cmbFiltroTipoMaterial = new System.Windows.Forms.ComboBox();
            this.lblFiltroTipoMaterial = new System.Windows.Forms.Label();
            this.cmbFiltroUnidadeMedida = new System.Windows.Forms.ComboBox();
            this.lblFiltroUnidadeMedida = new System.Windows.Forms.Label();
            this.txtFiltroNomeMaterial = new System.Windows.Forms.TextBox();
            this.lblFiltroNomeMaterial = new System.Windows.Forms.Label();
            this.panelFiltroFornecedorMaterialDropdown = new System.Windows.Forms.Panel();
            this.clbFiltroFornecedorMaterial = new System.Windows.Forms.CheckedListBox();
            this.panelInputsMateriais = new System.Windows.Forms.Panel();
            this.btnAtualizarMaterial = new System.Windows.Forms.Button();
            this.btnLimparMaterial = new System.Windows.Forms.Button();
            this.btnAdicionarMaterial = new System.Windows.Forms.Button();
            this.cmbFornecedorMaterial = new System.Windows.Forms.ComboBox();
            this.lblFornecedorMaterial = new System.Windows.Forms.Label();
            this.cmbTipoMaterial = new System.Windows.Forms.ComboBox();
            this.lblTipoMaterial = new System.Windows.Forms.Label();
            this.cmbUnidadeMedida = new System.Windows.Forms.ComboBox();
            this.lblUnidadeMedida = new System.Windows.Forms.Label();
            this.txtQuantidadeMaterial = new System.Windows.Forms.TextBox();
            this.lblQuantidadeMaterial = new System.Windows.Forms.Label();
            this.txtCustoMaterial = new System.Windows.Forms.TextBox();
            this.lblCustoMaterial = new System.Windows.Forms.Label();
            this.txtNomeMaterial = new System.Windows.Forms.TextBox();
            this.lblNomeMaterial = new System.Windows.Forms.Label();
            this.textBoxIDMaterial = new System.Windows.Forms.TextBox();
            this.lblIDMaterial = new System.Windows.Forms.Label();
            this.lblTituloMateriais = new System.Windows.Forms.Label();
            this.vScrollBar1 = new System.Windows.Forms.VScrollBar();
            this.panelDireitaProdutos = new System.Windows.Forms.Panel();
            this.dgvProdutos = new System.Windows.Forms.DataGridView();
            this.panelInputsProdutos = new System.Windows.Forms.Panel();
            this.lblIDProduto = new System.Windows.Forms.Label();
            this.textBoxIDProduto = new System.Windows.Forms.TextBox();
            this.lblCodigoProduto = new System.Windows.Forms.Label();
            this.txtCodigoProduto = new System.Windows.Forms.TextBox();
            this.lblNomeProduto = new System.Windows.Forms.Label();
            this.txtNomeProduto = new System.Windows.Forms.TextBox();
            this.lblTamanhoProduto = new System.Windows.Forms.Label();
            this.cmbTamanhoProduto = new System.Windows.Forms.ComboBox();
            this.lblCorProduto = new System.Windows.Forms.Label();
            this.cmbCorProduto = new System.Windows.Forms.ComboBox();
            this.lblPrecoProduto = new System.Windows.Forms.Label();
            this.txtPrecoProduto = new System.Windows.Forms.TextBox();
            this.lblStockProduto = new System.Windows.Forms.Label();
            this.nudStockProduto = new System.Windows.Forms.NumericUpDown();
            this.lblCategoriaProduto = new System.Windows.Forms.Label();
            this.cmbCategoriaProduto = new System.Windows.Forms.ComboBox();
            this.btnAdicionarProduto = new System.Windows.Forms.Button();
            this.btnAtualizarProduto = new System.Windows.Forms.Button();
            this.btnEliminarProduto = new System.Windows.Forms.Button();
            this.btnLimparProduto = new System.Windows.Forms.Button();
            this.panelPesquisaProdutos = new System.Windows.Forms.Panel();
            this.grpFiltrosProdutos = new System.Windows.Forms.GroupBox();
            this.lblFiltroNomeProduto = new System.Windows.Forms.Label();
            this.txtFiltroNomeProduto = new System.Windows.Forms.TextBox();
            this.lblFiltroPrecoMaxProduto = new System.Windows.Forms.Label();
            this.txtFiltroPrecoMaxProduto = new System.Windows.Forms.TextBox();
            this.btnPesquisarProdutos = new System.Windows.Forms.Button();
            this.lblFiltroTamanhoProduto = new System.Windows.Forms.Label();
            this.btnFiltroTamanhoDropdown = new System.Windows.Forms.Button();
            this.lblFiltroCorProduto = new System.Windows.Forms.Label();
            this.btnFiltroCorProdutoDropdown = new System.Windows.Forms.Button();
            this.lblFiltroCategoriaProduto = new System.Windows.Forms.Label();
            this.btnFiltroCategoriaDropdown = new System.Windows.Forms.Button();
            this.panelFiltroTamanhoDropdown = new System.Windows.Forms.Panel();
            this.clbFiltroTamanho = new System.Windows.Forms.CheckedListBox();
            this.panelFiltroCorProdutoDropdown = new System.Windows.Forms.Panel();
            this.clbFiltroCorProduto = new System.Windows.Forms.CheckedListBox();
            this.panelFiltroCategoriaDropdown = new System.Windows.Forms.Panel();
            this.clbFiltroCategoria = new System.Windows.Forms.CheckedListBox();
            this.tabStock.SuspendLayout();
            this.tabTecidos.SuspendLayout();
            this.panelDireitaTecidos.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvTecidos)).BeginInit();
            this.panelPesquisaTecidos.SuspendLayout();
            this.grpFiltrosTecido.SuspendLayout();
            this.panelFiltroCorDropdown.SuspendLayout();
            this.panelFiltroTipoDropdown.SuspendLayout();
            this.panelFiltroFornecedorDropdown.SuspendLayout();
            this.panelFiltroPadraoDropdown.SuspendLayout();
            this.panelInputsTecidos.SuspendLayout();
            this.tabMaterial.SuspendLayout();
            this.panelDireitaMateriais.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvMateriais)).BeginInit();
            this.panelPesquisaMateriais.SuspendLayout();
            this.grpFiltrosMateriais.SuspendLayout();
            this.panelFiltroFornecedorMaterialDropdown.SuspendLayout();
            this.panelInputsMateriais.SuspendLayout();
            this.tabProdutos.SuspendLayout();
            this.panelDireitaProdutos.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvProdutos)).BeginInit();
            this.panelInputsProdutos.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudStockProduto)).BeginInit();
            this.panelPesquisaProdutos.SuspendLayout();
            this.grpFiltrosProdutos.SuspendLayout();
            this.panelFiltroTamanhoDropdown.SuspendLayout();
            this.panelFiltroCorProdutoDropdown.SuspendLayout();
            this.panelFiltroCategoriaDropdown.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabStock
            // 
            this.tabStock.Controls.Add(this.tabProdutos);
            this.tabStock.Controls.Add(this.tabTecidos);
            this.tabStock.Controls.Add(this.tabMaterial);
            this.tabStock.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabStock.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.tabStock.ItemSize = new System.Drawing.Size(120, 55);
            this.tabStock.Location = new System.Drawing.Point(0, 0);
            this.tabStock.Name = "tabStock";
            this.tabStock.Padding = new System.Drawing.Point(15, 8);
            this.tabStock.SelectedIndex = 0;
            this.tabStock.Size = new System.Drawing.Size(1055, 766);
            this.tabStock.SizeMode = System.Windows.Forms.TabSizeMode.Fixed;
            this.tabStock.TabIndex = 0;
            this.tabStock.SelectedIndexChanged += new System.EventHandler(this.tabStock_SelectedIndexChanged);
            // 
            // tabProdutos
            // 
            this.tabProdutos.BackColor = System.Drawing.Color.WhiteSmoke;
            this.tabProdutos.Controls.Add(this.panelDireitaProdutos);
            this.tabProdutos.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.tabProdutos.Location = new System.Drawing.Point(4, 59);
            this.tabProdutos.Name = "tabProdutos";
            this.tabProdutos.Padding = new System.Windows.Forms.Padding(20);
            this.tabProdutos.Size = new System.Drawing.Size(1047, 703);
            this.tabProdutos.TabIndex = 0;
            this.tabProdutos.Text = "Produtos";
            // 
            // panelDireitaProdutos
            // 
            this.panelDireitaProdutos.BackColor = System.Drawing.Color.White;
            this.panelDireitaProdutos.Controls.Add(this.dgvProdutos);
            this.panelDireitaProdutos.Controls.Add(this.panelFiltroTamanhoDropdown);
            this.panelDireitaProdutos.Controls.Add(this.panelFiltroCorProdutoDropdown);
            this.panelDireitaProdutos.Controls.Add(this.panelFiltroCategoriaDropdown);
            this.panelDireitaProdutos.Controls.Add(this.panelInputsProdutos);
            this.panelDireitaProdutos.Controls.Add(this.panelPesquisaProdutos);
            this.panelDireitaProdutos.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelDireitaProdutos.Location = new System.Drawing.Point(20, 20);
            this.panelDireitaProdutos.Name = "panelDireitaProdutos";
            this.panelDireitaProdutos.Padding = new System.Windows.Forms.Padding(20);
            this.panelDireitaProdutos.Size = new System.Drawing.Size(1007, 663);
            this.panelDireitaProdutos.TabIndex = 0;
            // 
            // dgvProdutos
            // 
            this.dgvProdutos.AllowUserToAddRows = false;
            this.dgvProdutos.AllowUserToDeleteRows = false;
            this.dgvProdutos.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
            this.dgvProdutos.BackgroundColor = System.Drawing.Color.White;
            this.dgvProdutos.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvProdutos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvProdutos.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvProdutos.EnableHeadersVisualStyles = false;
            this.dgvProdutos.Location = new System.Drawing.Point(20, 238);
            this.dgvProdutos.MultiSelect = false;
            this.dgvProdutos.Name = "dgvProdutos";
            this.dgvProdutos.ReadOnly = true;
            this.dgvProdutos.RowHeadersVisible = false;
            this.dgvProdutos.RowHeadersWidth = 51;
            this.dgvProdutos.RowTemplate.Height = 24;
            this.dgvProdutos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvProdutos.Size = new System.Drawing.Size(967, 405);
            this.dgvProdutos.TabIndex = 0;
            this.dgvProdutos.CellFormatting += new System.Windows.Forms.DataGridViewCellFormattingEventHandler(this.dgvProdutos_CellFormatting);
            this.dgvProdutos.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvProdutos_CellClick);
            // 
            // panelFiltroTamanhoDropdown
            // 
            this.panelFiltroTamanhoDropdown.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panelFiltroTamanhoDropdown.Controls.Add(this.clbFiltroTamanho);
            this.panelFiltroTamanhoDropdown.Location = new System.Drawing.Point(30, 265);
            this.panelFiltroTamanhoDropdown.Name = "panelFiltroTamanhoDropdown";
            this.panelFiltroTamanhoDropdown.Size = new System.Drawing.Size(220, 120);
            this.panelFiltroTamanhoDropdown.TabIndex = 1;
            this.panelFiltroTamanhoDropdown.Visible = false;
            // 
            // clbFiltroTamanho
            // 
            this.clbFiltroTamanho.CheckOnClick = true;
            this.clbFiltroTamanho.Dock = System.Windows.Forms.DockStyle.Fill;
            this.clbFiltroTamanho.FormattingEnabled = true;
            this.clbFiltroTamanho.Location = new System.Drawing.Point(0, 0);
            this.clbFiltroTamanho.Name = "clbFiltroTamanho";
            this.clbFiltroTamanho.Size = new System.Drawing.Size(218, 118);
            this.clbFiltroTamanho.TabIndex = 0;
            // 
            // panelFiltroCorProdutoDropdown
            // 
            this.panelFiltroCorProdutoDropdown.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panelFiltroCorProdutoDropdown.Controls.Add(this.clbFiltroCorProduto);
            this.panelFiltroCorProdutoDropdown.Location = new System.Drawing.Point(280, 265);
            this.panelFiltroCorProdutoDropdown.Name = "panelFiltroCorProdutoDropdown";
            this.panelFiltroCorProdutoDropdown.Size = new System.Drawing.Size(220, 120);
            this.panelFiltroCorProdutoDropdown.TabIndex = 2;
            this.panelFiltroCorProdutoDropdown.Visible = false;
            // 
            // clbFiltroCorProduto
            // 
            this.clbFiltroCorProduto.CheckOnClick = true;
            this.clbFiltroCorProduto.Dock = System.Windows.Forms.DockStyle.Fill;
            this.clbFiltroCorProduto.FormattingEnabled = true;
            this.clbFiltroCorProduto.Location = new System.Drawing.Point(0, 0);
            this.clbFiltroCorProduto.Name = "clbFiltroCorProduto";
            this.clbFiltroCorProduto.Size = new System.Drawing.Size(218, 118);
            this.clbFiltroCorProduto.TabIndex = 0;
            // 
            // panelFiltroCategoriaDropdown
            // 
            this.panelFiltroCategoriaDropdown.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panelFiltroCategoriaDropdown.Controls.Add(this.clbFiltroCategoria);
            this.panelFiltroCategoriaDropdown.Location = new System.Drawing.Point(30, 320);
            this.panelFiltroCategoriaDropdown.Name = "panelFiltroCategoriaDropdown";
            this.panelFiltroCategoriaDropdown.Size = new System.Drawing.Size(220, 120);
            this.panelFiltroCategoriaDropdown.TabIndex = 3;
            this.panelFiltroCategoriaDropdown.Visible = false;
            // 
            // clbFiltroCategoria
            // 
            this.clbFiltroCategoria.CheckOnClick = true;
            this.clbFiltroCategoria.Dock = System.Windows.Forms.DockStyle.Fill;
            this.clbFiltroCategoria.FormattingEnabled = true;
            this.clbFiltroCategoria.Location = new System.Drawing.Point(0, 0);
            this.clbFiltroCategoria.Name = "clbFiltroCategoria";
            this.clbFiltroCategoria.Size = new System.Drawing.Size(218, 118);
            this.clbFiltroCategoria.TabIndex = 0;
            // 
            // panelInputsProdutos
            // 
            this.panelInputsProdutos.Controls.Add(this.btnLimparProduto);
            this.panelInputsProdutos.Controls.Add(this.btnEliminarProduto);
            this.panelInputsProdutos.Controls.Add(this.btnAtualizarProduto);
            this.panelInputsProdutos.Controls.Add(this.btnAdicionarProduto);
            this.panelInputsProdutos.Controls.Add(this.cmbCategoriaProduto);
            this.panelInputsProdutos.Controls.Add(this.lblCategoriaProduto);
            this.panelInputsProdutos.Controls.Add(this.nudStockProduto);
            this.panelInputsProdutos.Controls.Add(this.lblStockProduto);
            this.panelInputsProdutos.Controls.Add(this.txtPrecoProduto);
            this.panelInputsProdutos.Controls.Add(this.lblPrecoProduto);
            this.panelInputsProdutos.Controls.Add(this.cmbCorProduto);
            this.panelInputsProdutos.Controls.Add(this.lblCorProduto);
            this.panelInputsProdutos.Controls.Add(this.cmbTamanhoProduto);
            this.panelInputsProdutos.Controls.Add(this.lblTamanhoProduto);
            this.panelInputsProdutos.Controls.Add(this.txtNomeProduto);
            this.panelInputsProdutos.Controls.Add(this.lblNomeProduto);
            this.panelInputsProdutos.Controls.Add(this.txtCodigoProduto);
            this.panelInputsProdutos.Controls.Add(this.lblCodigoProduto);
            this.panelInputsProdutos.Controls.Add(this.textBoxIDProduto);
            this.panelInputsProdutos.Controls.Add(this.lblIDProduto);
            this.panelInputsProdutos.AutoScroll = true;
            this.panelInputsProdutos.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelInputsProdutos.Location = new System.Drawing.Point(20, 238);
            this.panelInputsProdutos.Name = "panelInputsProdutos";
            this.panelInputsProdutos.Padding = new System.Windows.Forms.Padding(10, 6, 10, 6);
            this.panelInputsProdutos.Size = new System.Drawing.Size(967, 90);
            this.panelInputsProdutos.TabIndex = 5;
            // 
            // lblIDProduto
            // 
            this.lblIDProduto.AutoSize = true;
            this.lblIDProduto.Location = new System.Drawing.Point(10, 10);
            this.lblIDProduto.Name = "lblIDProduto";
            this.lblIDProduto.Size = new System.Drawing.Size(20, 17);
            this.lblIDProduto.TabIndex = 0;
            this.lblIDProduto.Text = "ID:";
            // 
            // textBoxIDProduto
            // 
            this.textBoxIDProduto.Location = new System.Drawing.Point(10, 30);
            this.textBoxIDProduto.Name = "textBoxIDProduto";
            this.textBoxIDProduto.ReadOnly = true;
            this.textBoxIDProduto.Size = new System.Drawing.Size(50, 25);
            this.textBoxIDProduto.TabIndex = 1;
            this.textBoxIDProduto.TabStop = false;
            // 
            // lblCodigoProduto
            // 
            this.lblCodigoProduto.AutoSize = true;
            this.lblCodigoProduto.Location = new System.Drawing.Point(70, 10);
            this.lblCodigoProduto.Name = "lblCodigoProduto";
            this.lblCodigoProduto.Size = new System.Drawing.Size(55, 17);
            this.lblCodigoProduto.TabIndex = 2;
            this.lblCodigoProduto.Text = "C\u00f3digo:";
            // 
            // txtCodigoProduto
            // 
            this.txtCodigoProduto.Location = new System.Drawing.Point(70, 30);
            this.txtCodigoProduto.Name = "txtCodigoProduto";
            this.txtCodigoProduto.Size = new System.Drawing.Size(65, 25);
            this.txtCodigoProduto.TabIndex = 3;
            // 
            // lblNomeProduto
            // 
            this.lblNomeProduto.AutoSize = true;
            this.lblNomeProduto.Location = new System.Drawing.Point(145, 10);
            this.lblNomeProduto.Name = "lblNomeProduto";
            this.lblNomeProduto.Size = new System.Drawing.Size(43, 17);
            this.lblNomeProduto.TabIndex = 4;
            this.lblNomeProduto.Text = "Nome:";
            // 
            // txtNomeProduto
            // 
            this.txtNomeProduto.Location = new System.Drawing.Point(145, 30);
            this.txtNomeProduto.Name = "txtNomeProduto";
            this.txtNomeProduto.Size = new System.Drawing.Size(130, 25);
            this.txtNomeProduto.TabIndex = 5;
            // 
            // lblTamanhoProduto
            // 
            this.lblTamanhoProduto.AutoSize = true;
            this.lblTamanhoProduto.Location = new System.Drawing.Point(285, 10);
            this.lblTamanhoProduto.Name = "lblTamanhoProduto";
            this.lblTamanhoProduto.Size = new System.Drawing.Size(67, 17);
            this.lblTamanhoProduto.TabIndex = 6;
            this.lblTamanhoProduto.Text = "Tamanho:";
            // 
            // cmbTamanhoProduto
            // 
            this.cmbTamanhoProduto.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cmbTamanhoProduto.FormattingEnabled = true;
            this.cmbTamanhoProduto.Location = new System.Drawing.Point(285, 30);
            this.cmbTamanhoProduto.Name = "cmbTamanhoProduto";
            this.cmbTamanhoProduto.Size = new System.Drawing.Size(90, 25);
            this.cmbTamanhoProduto.TabIndex = 7;
            // 
            // lblCorProduto
            // 
            this.lblCorProduto.AutoSize = true;
            this.lblCorProduto.Location = new System.Drawing.Point(385, 10);
            this.lblCorProduto.Name = "lblCorProduto";
            this.lblCorProduto.Size = new System.Drawing.Size(27, 17);
            this.lblCorProduto.TabIndex = 8;
            this.lblCorProduto.Text = "Cor:";
            // 
            // cmbCorProduto
            // 
            this.cmbCorProduto.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cmbCorProduto.FormattingEnabled = true;
            this.cmbCorProduto.Location = new System.Drawing.Point(385, 30);
            this.cmbCorProduto.Name = "cmbCorProduto";
            this.cmbCorProduto.Size = new System.Drawing.Size(90, 25);
            this.cmbCorProduto.TabIndex = 9;
            // 
            // lblPrecoProduto
            // 
            this.lblPrecoProduto.AutoSize = true;
            this.lblPrecoProduto.Location = new System.Drawing.Point(485, 10);
            this.lblPrecoProduto.Name = "lblPrecoProduto";
            this.lblPrecoProduto.Size = new System.Drawing.Size(45, 17);
            this.lblPrecoProduto.TabIndex = 10;
            this.lblPrecoProduto.Text = "Pre\u00e7o:";
            // 
            // txtPrecoProduto
            // 
            this.txtPrecoProduto.Location = new System.Drawing.Point(485, 30);
            this.txtPrecoProduto.Name = "txtPrecoProduto";
            this.txtPrecoProduto.Size = new System.Drawing.Size(75, 25);
            this.txtPrecoProduto.TabIndex = 11;
            // 
            // lblStockProduto
            // 
            this.lblStockProduto.AutoSize = true;
            this.lblStockProduto.Location = new System.Drawing.Point(570, 10);
            this.lblStockProduto.Name = "lblStockProduto";
            this.lblStockProduto.Size = new System.Drawing.Size(42, 17);
            this.lblStockProduto.TabIndex = 12;
            this.lblStockProduto.Text = "Stock:";
            // 
            // nudStockProduto
            // 
            this.nudStockProduto.Location = new System.Drawing.Point(570, 30);
            this.nudStockProduto.Maximum = new decimal(new int[] { 9999, 0, 0, 0 });
            this.nudStockProduto.Minimum = new decimal(new int[] { 0, 0, 0, 0 });
            this.nudStockProduto.Name = "nudStockProduto";
            this.nudStockProduto.Size = new System.Drawing.Size(65, 25);
            this.nudStockProduto.TabIndex = 13;
            // 
            // lblCategoriaProduto
            // 
            this.lblCategoriaProduto.AutoSize = true;
            this.lblCategoriaProduto.Location = new System.Drawing.Point(645, 10);
            this.lblCategoriaProduto.Name = "lblCategoriaProduto";
            this.lblCategoriaProduto.Size = new System.Drawing.Size(65, 17);
            this.lblCategoriaProduto.TabIndex = 14;
            this.lblCategoriaProduto.Text = "Categoria:";
            // 
            // cmbCategoriaProduto
            // 
            this.cmbCategoriaProduto.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbCategoriaProduto.FormattingEnabled = true;
            this.cmbCategoriaProduto.Location = new System.Drawing.Point(645, 30);
            this.cmbCategoriaProduto.Name = "cmbCategoriaProduto";
            this.cmbCategoriaProduto.Size = new System.Drawing.Size(130, 25);
            this.cmbCategoriaProduto.TabIndex = 15;
            // 
            // btnAdicionarProduto
            // 
            this.btnAdicionarProduto.Location = new System.Drawing.Point(785, 28);
            this.btnAdicionarProduto.Name = "btnAdicionarProduto";
            this.btnAdicionarProduto.Size = new System.Drawing.Size(52, 28);
            this.btnAdicionarProduto.TabIndex = 16;
            this.btnAdicionarProduto.Text = "+ Add";
            this.btnAdicionarProduto.UseVisualStyleBackColor = true;
            this.btnAdicionarProduto.Click += new System.EventHandler(this.btnAdicionarProduto_Click);
            // 
            // btnAtualizarProduto
            // 
            this.btnAtualizarProduto.Location = new System.Drawing.Point(843, 28);
            this.btnAtualizarProduto.Name = "btnAtualizarProduto";
            this.btnAtualizarProduto.Size = new System.Drawing.Size(75, 28);
            this.btnAtualizarProduto.TabIndex = 17;
            this.btnAtualizarProduto.Text = "Atualizar";
            this.btnAtualizarProduto.UseVisualStyleBackColor = true;
            this.btnAtualizarProduto.Click += new System.EventHandler(this.btnAtualizarProduto_Click);
            // 
            // btnEliminarProduto
            // 
            this.btnEliminarProduto.BackColor = System.Drawing.Color.FromArgb(200, 60, 60);
            this.btnEliminarProduto.ForeColor = System.Drawing.Color.White;
            this.btnEliminarProduto.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnEliminarProduto.Location = new System.Drawing.Point(924, 28);
            this.btnEliminarProduto.Name = "btnEliminarProduto";
            this.btnEliminarProduto.Size = new System.Drawing.Size(72, 28);
            this.btnEliminarProduto.TabIndex = 19;
            this.btnEliminarProduto.Text = "Eliminar";
            this.btnEliminarProduto.UseVisualStyleBackColor = false;
            this.btnEliminarProduto.Click += new System.EventHandler(this.btnEliminarProduto_Click);
            // 
            // btnLimparProduto
            // 
            this.btnLimparProduto.Location = new System.Drawing.Point(1002, 28);
            this.btnLimparProduto.Name = "btnLimparProduto";
            this.btnLimparProduto.Size = new System.Drawing.Size(68, 28);
            this.btnLimparProduto.TabIndex = 20;
            this.btnLimparProduto.Text = "Limpar";
            this.btnLimparProduto.UseVisualStyleBackColor = true;
            this.btnLimparProduto.Click += new System.EventHandler(this.btnLimparProduto_Click);
            // 
            // panelPesquisaProdutos
            // 
            this.panelPesquisaProdutos.Controls.Add(this.grpFiltrosProdutos);
            this.panelPesquisaProdutos.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelPesquisaProdutos.Location = new System.Drawing.Point(20, 20);
            this.panelPesquisaProdutos.Name = "panelPesquisaProdutos";
            this.panelPesquisaProdutos.Size = new System.Drawing.Size(967, 218);
            this.panelPesquisaProdutos.TabIndex = 4;
            // 
            // grpFiltrosProdutos
            // 
            this.grpFiltrosProdutos.Controls.Add(this.btnFiltroCategoriaDropdown);
            this.grpFiltrosProdutos.Controls.Add(this.lblFiltroCategoriaProduto);
            this.grpFiltrosProdutos.Controls.Add(this.btnFiltroCorProdutoDropdown);
            this.grpFiltrosProdutos.Controls.Add(this.lblFiltroCorProduto);
            this.grpFiltrosProdutos.Controls.Add(this.btnFiltroTamanhoDropdown);
            this.grpFiltrosProdutos.Controls.Add(this.lblFiltroTamanhoProduto);
            this.grpFiltrosProdutos.Controls.Add(this.btnPesquisarProdutos);
            this.grpFiltrosProdutos.Controls.Add(this.txtFiltroPrecoMaxProduto);
            this.grpFiltrosProdutos.Controls.Add(this.lblFiltroPrecoMaxProduto);
            this.grpFiltrosProdutos.Controls.Add(this.txtFiltroNomeProduto);
            this.grpFiltrosProdutos.Controls.Add(this.lblFiltroNomeProduto);
            this.grpFiltrosProdutos.Location = new System.Drawing.Point(0, 0);
            this.grpFiltrosProdutos.Name = "grpFiltrosProdutos";
            this.grpFiltrosProdutos.Size = new System.Drawing.Size(530, 200);
            this.grpFiltrosProdutos.TabStop = false;
            this.grpFiltrosProdutos.Text = "Filtros de Pesquisa";
            // 
            // lblFiltroNomeProduto
            // 
            this.lblFiltroNomeProduto.AutoSize = true;
            this.lblFiltroNomeProduto.Location = new System.Drawing.Point(10, 25);
            this.lblFiltroNomeProduto.Name = "lblFiltroNomeProduto";
            this.lblFiltroNomeProduto.Size = new System.Drawing.Size(46, 17);
            this.lblFiltroNomeProduto.TabIndex = 0;
            this.lblFiltroNomeProduto.Text = "Nome:";
            // 
            // txtFiltroNomeProduto
            // 
            this.txtFiltroNomeProduto.Location = new System.Drawing.Point(10, 47);
            this.txtFiltroNomeProduto.Name = "txtFiltroNomeProduto";
            this.txtFiltroNomeProduto.Size = new System.Drawing.Size(120, 25);
            this.txtFiltroNomeProduto.TabIndex = 1;
            // 
            // lblFiltroPrecoMaxProduto
            // 
            this.lblFiltroPrecoMaxProduto.AutoSize = true;
            this.lblFiltroPrecoMaxProduto.Location = new System.Drawing.Point(140, 25);
            this.lblFiltroPrecoMaxProduto.Name = "lblFiltroPrecoMaxProduto";
            this.lblFiltroPrecoMaxProduto.Size = new System.Drawing.Size(75, 17);
            this.lblFiltroPrecoMaxProduto.TabIndex = 2;
            this.lblFiltroPrecoMaxProduto.Text = "Preço máx.:";
            // 
            // txtFiltroPrecoMaxProduto
            // 
            this.txtFiltroPrecoMaxProduto.Location = new System.Drawing.Point(145, 47);
            this.txtFiltroPrecoMaxProduto.Name = "txtFiltroPrecoMaxProduto";
            this.txtFiltroPrecoMaxProduto.Size = new System.Drawing.Size(80, 25);
            this.txtFiltroPrecoMaxProduto.TabIndex = 3;
            // 
            // btnPesquisarProdutos
            // 
            this.btnPesquisarProdutos.Location = new System.Drawing.Point(373, 47);
            this.btnPesquisarProdutos.Name = "btnPesquisarProdutos";
            this.btnPesquisarProdutos.Size = new System.Drawing.Size(107, 28);
            this.btnPesquisarProdutos.TabIndex = 4;
            this.btnPesquisarProdutos.Text = "Pesquisar";
            this.btnPesquisarProdutos.UseVisualStyleBackColor = true;
            this.btnPesquisarProdutos.Click += new System.EventHandler(this.btnPesquisarProdutos_Click);
            // 
            // lblFiltroTamanhoProduto
            // 
            this.lblFiltroTamanhoProduto.AutoSize = true;
            this.lblFiltroTamanhoProduto.Location = new System.Drawing.Point(10, 78);
            this.lblFiltroTamanhoProduto.Name = "lblFiltroTamanhoProduto";
            this.lblFiltroTamanhoProduto.Size = new System.Drawing.Size(67, 17);
            this.lblFiltroTamanhoProduto.TabIndex = 5;
            this.lblFiltroTamanhoProduto.Text = "Tamanho:";
            // 
            // btnFiltroTamanhoDropdown
            // 
            this.btnFiltroTamanhoDropdown.Location = new System.Drawing.Point(10, 100);
            this.btnFiltroTamanhoDropdown.Name = "btnFiltroTamanhoDropdown";
            this.btnFiltroTamanhoDropdown.Size = new System.Drawing.Size(220, 28);
            this.btnFiltroTamanhoDropdown.TabIndex = 6;
            this.btnFiltroTamanhoDropdown.Text = "Selecionar Tamanhos ▼";
            this.btnFiltroTamanhoDropdown.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnFiltroTamanhoDropdown.UseVisualStyleBackColor = true;
            this.btnFiltroTamanhoDropdown.Click += new System.EventHandler(this.btnFiltroTamanhoDropdown_Click);
            // 
            // lblFiltroCorProduto
            // 
            this.lblFiltroCorProduto.AutoSize = true;
            this.lblFiltroCorProduto.Location = new System.Drawing.Point(256, 78);
            this.lblFiltroCorProduto.Name = "lblFiltroCorProduto";
            this.lblFiltroCorProduto.Size = new System.Drawing.Size(27, 17);
            this.lblFiltroCorProduto.TabIndex = 7;
            this.lblFiltroCorProduto.Text = "Cor:";
            // 
            // btnFiltroCorProdutoDropdown
            // 
            this.btnFiltroCorProdutoDropdown.Location = new System.Drawing.Point(260, 100);
            this.btnFiltroCorProdutoDropdown.Name = "btnFiltroCorProdutoDropdown";
            this.btnFiltroCorProdutoDropdown.Size = new System.Drawing.Size(220, 28);
            this.btnFiltroCorProdutoDropdown.TabIndex = 8;
            this.btnFiltroCorProdutoDropdown.Text = "Selecionar Cores ▼";
            this.btnFiltroCorProdutoDropdown.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnFiltroCorProdutoDropdown.UseVisualStyleBackColor = true;
            this.btnFiltroCorProdutoDropdown.Click += new System.EventHandler(this.btnFiltroCorProdutoDropdown_Click);
            // 
            // lblFiltroCategoriaProduto
            // 
            this.lblFiltroCategoriaProduto.AutoSize = true;
            this.lblFiltroCategoriaProduto.Location = new System.Drawing.Point(10, 133);
            this.lblFiltroCategoriaProduto.Name = "lblFiltroCategoriaProduto";
            this.lblFiltroCategoriaProduto.Size = new System.Drawing.Size(65, 17);
            this.lblFiltroCategoriaProduto.TabIndex = 9;
            this.lblFiltroCategoriaProduto.Text = "Categoria:";
            // 
            // btnFiltroCategoriaDropdown
            // 
            this.btnFiltroCategoriaDropdown.Location = new System.Drawing.Point(10, 155);
            this.btnFiltroCategoriaDropdown.Name = "btnFiltroCategoriaDropdown";
            this.btnFiltroCategoriaDropdown.Size = new System.Drawing.Size(220, 28);
            this.btnFiltroCategoriaDropdown.TabIndex = 10;
            this.btnFiltroCategoriaDropdown.Text = "Selecionar Categorias ▼";
            this.btnFiltroCategoriaDropdown.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnFiltroCategoriaDropdown.UseVisualStyleBackColor = true;
            this.btnFiltroCategoriaDropdown.Click += new System.EventHandler(this.btnFiltroCategoriaDropdown_Click);
            // 
            // tabTecidos
            // 
            this.tabTecidos.BackColor = System.Drawing.Color.WhiteSmoke;
            this.tabTecidos.Controls.Add(this.panelDireitaTecidos);
            this.tabTecidos.Controls.Add(this.panelInputsTecidos);
            this.tabTecidos.Location = new System.Drawing.Point(4, 59);
            this.tabTecidos.Name = "tabTecidos";
            this.tabTecidos.Padding = new System.Windows.Forms.Padding(20);
            this.tabTecidos.Size = new System.Drawing.Size(1047, 703);
            this.tabTecidos.TabIndex = 2;
            this.tabTecidos.Text = "Tecidos";
            // 
            // panelDireitaTecidos
            // 
            this.panelDireitaTecidos.BackColor = System.Drawing.Color.White;
            this.panelDireitaTecidos.Controls.Add(this.dgvTecidos);
            this.panelDireitaTecidos.Controls.Add(this.panelPesquisaTecidos);
            this.panelDireitaTecidos.Controls.Add(this.panelFiltroCorDropdown);
            this.panelDireitaTecidos.Controls.Add(this.panelFiltroTipoDropdown);
            this.panelDireitaTecidos.Controls.Add(this.panelFiltroFornecedorDropdown);
            this.panelDireitaTecidos.Controls.Add(this.panelFiltroPadraoDropdown);
            this.panelDireitaTecidos.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelDireitaTecidos.Location = new System.Drawing.Point(388, 20);
            this.panelDireitaTecidos.Name = "panelDireitaTecidos";
            this.panelDireitaTecidos.Padding = new System.Windows.Forms.Padding(20);
            this.panelDireitaTecidos.Size = new System.Drawing.Size(639, 663);
            this.panelDireitaTecidos.TabIndex = 2;
            // 
            // dgvTecidos
            // 
            this.dgvTecidos.AllowUserToAddRows = false;
            this.dgvTecidos.AllowUserToDeleteRows = false;
            this.dgvTecidos.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
            this.dgvTecidos.BackgroundColor = System.Drawing.Color.White;
            this.dgvTecidos.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvTecidos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvTecidos.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvTecidos.EnableHeadersVisualStyles = false;
            this.dgvTecidos.Location = new System.Drawing.Point(20, 238);
            this.dgvTecidos.MultiSelect = false;
            this.dgvTecidos.Name = "dgvTecidos";
            this.dgvTecidos.ReadOnly = true;
            this.dgvTecidos.RowHeadersVisible = false;
            this.dgvTecidos.RowHeadersWidth = 51;
            this.dgvTecidos.RowTemplate.Height = 24;
            this.dgvTecidos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvTecidos.Size = new System.Drawing.Size(599, 405);
            this.dgvTecidos.TabIndex = 0;
            this.dgvTecidos.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvTecidos_CellClick);
            this.dgvTecidos.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvTecidos_CellContentClick);
            // 
            // panelPesquisaTecidos
            // 
            this.panelPesquisaTecidos.Controls.Add(this.grpFiltrosTecido);
            this.panelPesquisaTecidos.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelPesquisaTecidos.Location = new System.Drawing.Point(20, 20);
            this.panelPesquisaTecidos.Name = "panelPesquisaTecidos";
            this.panelPesquisaTecidos.Padding = new System.Windows.Forms.Padding(0, 0, 0, 10);
            this.panelPesquisaTecidos.Size = new System.Drawing.Size(599, 218);
            this.panelPesquisaTecidos.TabIndex = 1;
            // 
            // grpFiltrosTecido
            // 
            this.grpFiltrosTecido.Controls.Add(this.btnPesquisarTecido);
            this.grpFiltrosTecido.Controls.Add(this.btnFiltroPadraoDropdown);
            this.grpFiltrosTecido.Controls.Add(this.lblFiltroPadrao);
            this.grpFiltrosTecido.Controls.Add(this.btnFiltroFornecedorDropdown);
            this.grpFiltrosTecido.Controls.Add(this.lblFiltroFornecedor);
            this.grpFiltrosTecido.Controls.Add(this.btnFiltroTipoDropdown);
            this.grpFiltrosTecido.Controls.Add(this.lblFiltroTipo);
            this.grpFiltrosTecido.Controls.Add(this.btnFiltroCorDropdown);
            this.grpFiltrosTecido.Controls.Add(this.lblFiltroCor);
            this.grpFiltrosTecido.Controls.Add(this.txtFiltroPrecoMax);
            this.grpFiltrosTecido.Controls.Add(this.txtFiltroPrecoMin);
            this.grpFiltrosTecido.Controls.Add(this.lblFiltroPrecoSeparador);
            this.grpFiltrosTecido.Controls.Add(this.lblFiltroPreco);
            this.grpFiltrosTecido.Controls.Add(this.txtFiltroNome);
            this.grpFiltrosTecido.Controls.Add(this.lblFiltroNome);
            this.grpFiltrosTecido.Location = new System.Drawing.Point(20, 14);
            this.grpFiltrosTecido.Name = "grpFiltrosTecido";
            this.grpFiltrosTecido.Size = new System.Drawing.Size(494, 188);
            this.grpFiltrosTecido.TabIndex = 50;
            this.grpFiltrosTecido.TabStop = false;
            this.grpFiltrosTecido.Text = "Filtros de Pesquisa";
            // 
            // btnPesquisarTecido
            // 
            this.btnPesquisarTecido.Location = new System.Drawing.Point(373, 47);
            this.btnPesquisarTecido.Name = "btnPesquisarTecido";
            this.btnPesquisarTecido.Size = new System.Drawing.Size(107, 28);
            this.btnPesquisarTecido.TabIndex = 1;
            this.btnPesquisarTecido.Text = "Pesquisar";
            this.btnPesquisarTecido.UseVisualStyleBackColor = true;
            // 
            // btnFiltroPadraoDropdown
            // 
            this.btnFiltroPadraoDropdown.Location = new System.Drawing.Point(260, 151);
            this.btnFiltroPadraoDropdown.Name = "btnFiltroPadraoDropdown";
            this.btnFiltroPadraoDropdown.Size = new System.Drawing.Size(220, 28);
            this.btnFiltroPadraoDropdown.TabIndex = 14;
            this.btnFiltroPadraoDropdown.Text = "Selecionar padrões ▼";
            this.btnFiltroPadraoDropdown.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnFiltroPadraoDropdown.UseVisualStyleBackColor = true;
            this.btnFiltroPadraoDropdown.Click += new System.EventHandler(this.btnFiltroPadraoDropdown_Click);
            // 
            // lblFiltroPadrao
            // 
            this.lblFiltroPadrao.AutoSize = true;
            this.lblFiltroPadrao.Location = new System.Drawing.Point(260, 129);
            this.lblFiltroPadrao.Name = "lblFiltroPadrao";
            this.lblFiltroPadrao.Size = new System.Drawing.Size(55, 19);
            this.lblFiltroPadrao.TabIndex = 13;
            this.lblFiltroPadrao.Text = "Padrão:";
            // 
            // btnFiltroFornecedorDropdown
            // 
            this.btnFiltroFornecedorDropdown.Location = new System.Drawing.Point(260, 97);
            this.btnFiltroFornecedorDropdown.Name = "btnFiltroFornecedorDropdown";
            this.btnFiltroFornecedorDropdown.Size = new System.Drawing.Size(220, 28);
            this.btnFiltroFornecedorDropdown.TabIndex = 10;
            this.btnFiltroFornecedorDropdown.Text = "Selecionar fornecedores ▼";
            this.btnFiltroFornecedorDropdown.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnFiltroFornecedorDropdown.UseVisualStyleBackColor = true;
            this.btnFiltroFornecedorDropdown.Click += new System.EventHandler(this.btnFiltroFornecedorDropdown_Click);
            // 
            // lblFiltroFornecedor
            // 
            this.lblFiltroFornecedor.AutoSize = true;
            this.lblFiltroFornecedor.Location = new System.Drawing.Point(256, 75);
            this.lblFiltroFornecedor.Name = "lblFiltroFornecedor";
            this.lblFiltroFornecedor.Size = new System.Drawing.Size(81, 19);
            this.lblFiltroFornecedor.TabIndex = 15;
            this.lblFiltroFornecedor.Text = "Fornecedor:";
            // 
            // btnFiltroTipoDropdown
            // 
            this.btnFiltroTipoDropdown.Location = new System.Drawing.Point(10, 151);
            this.btnFiltroTipoDropdown.Name = "btnFiltroTipoDropdown";
            this.btnFiltroTipoDropdown.Size = new System.Drawing.Size(220, 28);
            this.btnFiltroTipoDropdown.TabIndex = 17;
            this.btnFiltroTipoDropdown.Text = "Selecionar tipos ▼";
            this.btnFiltroTipoDropdown.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnFiltroTipoDropdown.UseVisualStyleBackColor = true;
            this.btnFiltroTipoDropdown.Click += new System.EventHandler(this.btnFiltroTipoDropdown_Click);
            // 
            // lblFiltroTipo
            // 
            this.lblFiltroTipo.AutoSize = true;
            this.lblFiltroTipo.Location = new System.Drawing.Point(10, 129);
            this.lblFiltroTipo.Name = "lblFiltroTipo";
            this.lblFiltroTipo.Size = new System.Drawing.Size(38, 19);
            this.lblFiltroTipo.TabIndex = 16;
            this.lblFiltroTipo.Text = "Tipo:";
            // 
            // btnFiltroCorDropdown
            // 
            this.btnFiltroCorDropdown.Location = new System.Drawing.Point(10, 97);
            this.btnFiltroCorDropdown.Name = "btnFiltroCorDropdown";
            this.btnFiltroCorDropdown.Size = new System.Drawing.Size(220, 28);
            this.btnFiltroCorDropdown.TabIndex = 10;
            this.btnFiltroCorDropdown.Text = "Selecionar cores ▼";
            this.btnFiltroCorDropdown.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnFiltroCorDropdown.UseVisualStyleBackColor = true;
            this.btnFiltroCorDropdown.Click += new System.EventHandler(this.btnFiltroCorDropdown_Click);
            // 
            // lblFiltroCor
            // 
            this.lblFiltroCor.AutoSize = true;
            this.lblFiltroCor.Location = new System.Drawing.Point(10, 75);
            this.lblFiltroCor.Name = "lblFiltroCor";
            this.lblFiltroCor.Size = new System.Drawing.Size(34, 19);
            this.lblFiltroCor.TabIndex = 9;
            this.lblFiltroCor.Text = "Cor:";
            // 
            // txtFiltroPrecoMax
            // 
            this.txtFiltroPrecoMax.Location = new System.Drawing.Point(234, 47);
            this.txtFiltroPrecoMax.Name = "txtFiltroPrecoMax";
            this.txtFiltroPrecoMax.Size = new System.Drawing.Size(70, 25);
            this.txtFiltroPrecoMax.TabIndex = 5;
            // 
            // txtFiltroPrecoMin
            // 
            this.txtFiltroPrecoMin.Location = new System.Drawing.Point(145, 47);
            this.txtFiltroPrecoMin.Name = "txtFiltroPrecoMin";
            this.txtFiltroPrecoMin.Size = new System.Drawing.Size(70, 25);
            this.txtFiltroPrecoMin.TabIndex = 3;
            // 
            // lblFiltroPrecoSeparador
            // 
            this.lblFiltroPrecoSeparador.AutoSize = true;
            this.lblFiltroPrecoSeparador.Location = new System.Drawing.Point(217, 50);
            this.lblFiltroPrecoSeparador.Name = "lblFiltroPrecoSeparador";
            this.lblFiltroPrecoSeparador.Size = new System.Drawing.Size(15, 19);
            this.lblFiltroPrecoSeparador.TabIndex = 4;
            this.lblFiltroPrecoSeparador.Text = "-";
            // 
            // lblFiltroPreco
            // 
            this.lblFiltroPreco.AutoSize = true;
            this.lblFiltroPreco.Location = new System.Drawing.Point(145, 25);
            this.lblFiltroPreco.Name = "lblFiltroPreco";
            this.lblFiltroPreco.Size = new System.Drawing.Size(46, 19);
            this.lblFiltroPreco.TabIndex = 2;
            this.lblFiltroPreco.Text = "Preço:";
            // 
            // txtFiltroNome
            // 
            this.txtFiltroNome.Location = new System.Drawing.Point(10, 47);
            this.txtFiltroNome.Name = "txtFiltroNome";
            this.txtFiltroNome.Size = new System.Drawing.Size(120, 25);
            this.txtFiltroNome.TabIndex = 1;
            // 
            // lblFiltroNome
            // 
            this.lblFiltroNome.AutoSize = true;
            this.lblFiltroNome.Location = new System.Drawing.Point(10, 25);
            this.lblFiltroNome.Name = "lblFiltroNome";
            this.lblFiltroNome.Size = new System.Drawing.Size(49, 19);
            this.lblFiltroNome.TabIndex = 0;
            this.lblFiltroNome.Text = "Nome:";
            // 
            // panelFiltroCorDropdown
            // 
            this.panelFiltroCorDropdown.Controls.Add(this.clbFiltroCor);
            this.panelFiltroCorDropdown.Location = new System.Drawing.Point(50, 131);
            this.panelFiltroCorDropdown.Name = "panelFiltroCorDropdown";
            this.panelFiltroCorDropdown.Size = new System.Drawing.Size(220, 84);
            this.panelFiltroCorDropdown.TabIndex = 8;
            this.panelFiltroCorDropdown.Visible = false;
            // 
            // clbFiltroCor
            // 
            this.clbFiltroCor.CheckOnClick = true;
            this.clbFiltroCor.FormattingEnabled = true;
            this.clbFiltroCor.Location = new System.Drawing.Point(0, 0);
            this.clbFiltroCor.Name = "clbFiltroCor";
            this.clbFiltroCor.Size = new System.Drawing.Size(220, 84);
            this.clbFiltroCor.TabIndex = 0;
            // 
            // panelFiltroTipoDropdown
            // 
            this.panelFiltroTipoDropdown.Controls.Add(this.clbFiltroTipo);
            this.panelFiltroTipoDropdown.Location = new System.Drawing.Point(50, 177);
            this.panelFiltroTipoDropdown.Name = "panelFiltroTipoDropdown";
            this.panelFiltroTipoDropdown.Size = new System.Drawing.Size(220, 84);
            this.panelFiltroTipoDropdown.TabIndex = 12;
            this.panelFiltroTipoDropdown.Visible = false;
            // 
            // clbFiltroTipo
            // 
            this.clbFiltroTipo.CheckOnClick = true;
            this.clbFiltroTipo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.clbFiltroTipo.FormattingEnabled = true;
            this.clbFiltroTipo.Location = new System.Drawing.Point(0, 0);
            this.clbFiltroTipo.Name = "clbFiltroTipo";
            this.clbFiltroTipo.Size = new System.Drawing.Size(220, 84);
            this.clbFiltroTipo.TabIndex = 0;
            // 
            // panelFiltroFornecedorDropdown
            // 
            this.panelFiltroFornecedorDropdown.Controls.Add(this.clbFiltroFornecedor);
            this.panelFiltroFornecedorDropdown.Location = new System.Drawing.Point(300, 131);
            this.panelFiltroFornecedorDropdown.Name = "panelFiltroFornecedorDropdown";
            this.panelFiltroFornecedorDropdown.Size = new System.Drawing.Size(220, 84);
            this.panelFiltroFornecedorDropdown.TabIndex = 11;
            this.panelFiltroFornecedorDropdown.Visible = false;
            // 
            // clbFiltroFornecedor
            // 
            this.clbFiltroFornecedor.CheckOnClick = true;
            this.clbFiltroFornecedor.Dock = System.Windows.Forms.DockStyle.Fill;
            this.clbFiltroFornecedor.FormattingEnabled = true;
            this.clbFiltroFornecedor.Location = new System.Drawing.Point(0, 0);
            this.clbFiltroFornecedor.Name = "clbFiltroFornecedor";
            this.clbFiltroFornecedor.Size = new System.Drawing.Size(220, 84);
            this.clbFiltroFornecedor.TabIndex = 0;
            // 
            // panelFiltroPadraoDropdown
            // 
            this.panelFiltroPadraoDropdown.Controls.Add(this.clbFiltroPadrao);
            this.panelFiltroPadraoDropdown.Location = new System.Drawing.Point(300, 177);
            this.panelFiltroPadraoDropdown.Name = "panelFiltroPadraoDropdown";
            this.panelFiltroPadraoDropdown.Size = new System.Drawing.Size(220, 84);
            this.panelFiltroPadraoDropdown.TabIndex = 13;
            this.panelFiltroPadraoDropdown.Visible = false;
            // 
            // clbFiltroPadrao
            // 
            this.clbFiltroPadrao.CheckOnClick = true;
            this.clbFiltroPadrao.Dock = System.Windows.Forms.DockStyle.Fill;
            this.clbFiltroPadrao.FormattingEnabled = true;
            this.clbFiltroPadrao.Location = new System.Drawing.Point(0, 0);
            this.clbFiltroPadrao.Name = "clbFiltroPadrao";
            this.clbFiltroPadrao.Size = new System.Drawing.Size(220, 84);
            this.clbFiltroPadrao.TabIndex = 0;
            // 
            // panelInputsTecidos
            // 
            this.panelInputsTecidos.AutoScroll = true;
            this.panelInputsTecidos.BackColor = System.Drawing.Color.White;
            this.panelInputsTecidos.Controls.Add(this.cmbFornecedor);
            this.panelInputsTecidos.Controls.Add(this.btnAdicionarTecido);
            this.panelInputsTecidos.Controls.Add(this.cmbTipo);
            this.panelInputsTecidos.Controls.Add(this.cmbCor);
            this.panelInputsTecidos.Controls.Add(this.labelIDTecido);
            this.panelInputsTecidos.Controls.Add(this.textBoxIDtecido);
            this.panelInputsTecidos.Controls.Add(this.lblTituloTecidos);
            this.panelInputsTecidos.Controls.Add(this.lblNome);
            this.panelInputsTecidos.Controls.Add(this.txtNome);
            this.panelInputsTecidos.Controls.Add(this.lblPreco);
            this.panelInputsTecidos.Controls.Add(this.txtPreco);
            this.panelInputsTecidos.Controls.Add(this.lblQuantidade);
            this.panelInputsTecidos.Controls.Add(this.txtQuantidade);
            this.panelInputsTecidos.Controls.Add(this.lblCodigo);
            this.panelInputsTecidos.Controls.Add(this.txtCodigo);
            this.panelInputsTecidos.Controls.Add(this.lblCor);
            this.panelInputsTecidos.Controls.Add(this.lblTipo);
            this.panelInputsTecidos.Controls.Add(this.lblPadrao);
            this.panelInputsTecidos.Controls.Add(this.cmbPadrao);
            this.panelInputsTecidos.Controls.Add(this.lblFornecedor);
            this.panelInputsTecidos.Controls.Add(this.btnLimparTecido);
            this.panelInputsTecidos.Controls.Add(this.btnAtualizarTecido);
            this.panelInputsTecidos.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelInputsTecidos.Location = new System.Drawing.Point(20, 20);
            this.panelInputsTecidos.Name = "panelInputsTecidos";
            this.panelInputsTecidos.Padding = new System.Windows.Forms.Padding(20);
            this.panelInputsTecidos.Size = new System.Drawing.Size(368, 663);
            this.panelInputsTecidos.TabIndex = 1;
            // 
            // cmbFornecedor
            // 
            this.cmbFornecedor.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbFornecedor.FormattingEnabled = true;
            this.cmbFornecedor.Location = new System.Drawing.Point(23, 491);
            this.cmbFornecedor.Name = "cmbFornecedor";
            this.cmbFornecedor.Size = new System.Drawing.Size(260, 25);
            this.cmbFornecedor.TabIndex = 27;
            // 
            // btnAdicionarTecido
            // 
            this.btnAdicionarTecido.Location = new System.Drawing.Point(23, 529);
            this.btnAdicionarTecido.Name = "btnAdicionarTecido";
            this.btnAdicionarTecido.Size = new System.Drawing.Size(120, 35);
            this.btnAdicionarTecido.TabIndex = 20;
            this.btnAdicionarTecido.Text = "Adicionar";
            this.btnAdicionarTecido.UseVisualStyleBackColor = true;
            this.btnAdicionarTecido.Click += new System.EventHandler(this.btnAdicionarTecido_Click);
            // 
            // cmbTipo
            // 
            this.cmbTipo.FormattingEnabled = true;
            this.cmbTipo.Location = new System.Drawing.Point(23, 370);
            this.cmbTipo.Name = "cmbTipo";
            this.cmbTipo.Size = new System.Drawing.Size(260, 25);
            this.cmbTipo.TabIndex = 26;
            // 
            // cmbCor
            // 
            this.cmbCor.FormattingEnabled = true;
            this.cmbCor.Location = new System.Drawing.Point(23, 311);
            this.cmbCor.Name = "cmbCor";
            this.cmbCor.Size = new System.Drawing.Size(260, 25);
            this.cmbCor.TabIndex = 25;
            // 
            // labelIDTecido
            // 
            this.labelIDTecido.Location = new System.Drawing.Point(258, 67);
            this.labelIDTecido.Name = "labelIDTecido";
            this.labelIDTecido.Size = new System.Drawing.Size(25, 22);
            this.labelIDTecido.TabIndex = 23;
            this.labelIDTecido.Text = "ID";
            // 
            // textBoxIDtecido
            // 
            this.textBoxIDtecido.Location = new System.Drawing.Point(258, 89);
            this.textBoxIDtecido.Name = "textBoxIDtecido";
            this.textBoxIDtecido.ReadOnly = true;
            this.textBoxIDtecido.Size = new System.Drawing.Size(25, 25);
            this.textBoxIDtecido.TabIndex = 22;
            // 
            // lblTituloTecidos
            // 
            this.lblTituloTecidos.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lblTituloTecidos.Location = new System.Drawing.Point(15, 15);
            this.lblTituloTecidos.Name = "lblTituloTecidos";
            this.lblTituloTecidos.Size = new System.Drawing.Size(260, 40);
            this.lblTituloTecidos.TabIndex = 0;
            this.lblTituloTecidos.Text = "Gestão de Tecidos";
            // 
            // lblNome
            // 
            this.lblNome.Location = new System.Drawing.Point(19, 67);
            this.lblNome.Name = "lblNome";
            this.lblNome.Size = new System.Drawing.Size(229, 22);
            this.lblNome.TabIndex = 3;
            this.lblNome.Text = "Nome";
            // 
            // txtNome
            // 
            this.txtNome.Location = new System.Drawing.Point(23, 89);
            this.txtNome.Name = "txtNome";
            this.txtNome.Size = new System.Drawing.Size(229, 25);
            this.txtNome.TabIndex = 4;
            // 
            // lblPreco
            // 
            this.lblPreco.Location = new System.Drawing.Point(19, 123);
            this.lblPreco.Name = "lblPreco";
            this.lblPreco.Size = new System.Drawing.Size(260, 22);
            this.lblPreco.TabIndex = 5;
            this.lblPreco.Text = "Preço";
            // 
            // txtPreco
            // 
            this.txtPreco.Location = new System.Drawing.Point(23, 144);
            this.txtPreco.Name = "txtPreco";
            this.txtPreco.Size = new System.Drawing.Size(260, 25);
            this.txtPreco.TabIndex = 6;
            // 
            // lblQuantidade
            // 
            this.lblQuantidade.Location = new System.Drawing.Point(19, 177);
            this.lblQuantidade.Name = "lblQuantidade";
            this.lblQuantidade.Size = new System.Drawing.Size(260, 22);
            this.lblQuantidade.TabIndex = 7;
            this.lblQuantidade.Text = "Quantidade";
            // 
            // txtQuantidade
            // 
            this.txtQuantidade.Location = new System.Drawing.Point(23, 199);
            this.txtQuantidade.Name = "txtQuantidade";
            this.txtQuantidade.Size = new System.Drawing.Size(260, 25);
            this.txtQuantidade.TabIndex = 8;
            // 
            // lblCodigo
            // 
            this.lblCodigo.Location = new System.Drawing.Point(19, 233);
            this.lblCodigo.Name = "lblCodigo";
            this.lblCodigo.Size = new System.Drawing.Size(260, 22);
            this.lblCodigo.TabIndex = 9;
            this.lblCodigo.Text = "Código";
            // 
            // txtCodigo
            // 
            this.txtCodigo.Location = new System.Drawing.Point(23, 254);
            this.txtCodigo.Name = "txtCodigo";
            this.txtCodigo.Size = new System.Drawing.Size(260, 25);
            this.txtCodigo.TabIndex = 10;
            // 
            // lblCor
            // 
            this.lblCor.Location = new System.Drawing.Point(19, 287);
            this.lblCor.Name = "lblCor";
            this.lblCor.Size = new System.Drawing.Size(260, 22);
            this.lblCor.TabIndex = 11;
            this.lblCor.Text = "Cor";
            this.lblCor.Click += new System.EventHandler(this.lblCor_Click);
            // 
            // lblTipo
            // 
            this.lblTipo.Location = new System.Drawing.Point(19, 346);
            this.lblTipo.Name = "lblTipo";
            this.lblTipo.Size = new System.Drawing.Size(260, 22);
            this.lblTipo.TabIndex = 13;
            this.lblTipo.Text = "Tipo";
            // 
            // lblPadrao
            // 
            this.lblPadrao.Location = new System.Drawing.Point(19, 406);
            this.lblPadrao.Name = "lblPadrao";
            this.lblPadrao.Size = new System.Drawing.Size(260, 22);
            this.lblPadrao.TabIndex = 15;
            this.lblPadrao.Text = "Padrão";
            // 
            // cmbPadrao
            // 
            this.cmbPadrao.FormattingEnabled = true;
            this.cmbPadrao.Location = new System.Drawing.Point(23, 431);
            this.cmbPadrao.Name = "cmbPadrao";
            this.cmbPadrao.Size = new System.Drawing.Size(260, 25);
            this.cmbPadrao.TabIndex = 16;
            // 
            // lblFornecedor
            // 
            this.lblFornecedor.Location = new System.Drawing.Point(19, 466);
            this.lblFornecedor.Name = "lblFornecedor";
            this.lblFornecedor.Size = new System.Drawing.Size(260, 22);
            this.lblFornecedor.TabIndex = 17;
            this.lblFornecedor.Text = "Fornecedor (ID)";
            // 
            // btnLimparTecido
            // 
            this.btnLimparTecido.Location = new System.Drawing.Point(23, 578);
            this.btnLimparTecido.Name = "btnLimparTecido";
            this.btnLimparTecido.Size = new System.Drawing.Size(120, 35);
            this.btnLimparTecido.TabIndex = 28;
            this.btnLimparTecido.Text = "Limpar";
            this.btnLimparTecido.UseVisualStyleBackColor = true;
            this.btnLimparTecido.Click += new System.EventHandler(this.btnLimparTecido_Click);
            // 
            // btnAtualizarTecido
            // 
            this.btnAtualizarTecido.Location = new System.Drawing.Point(163, 529);
            this.btnAtualizarTecido.Name = "btnAtualizarTecido";
            this.btnAtualizarTecido.Size = new System.Drawing.Size(120, 35);
            this.btnAtualizarTecido.TabIndex = 21;
            this.btnAtualizarTecido.Text = "Atualizar";
            this.btnAtualizarTecido.UseVisualStyleBackColor = true;
            this.btnAtualizarTecido.Click += new System.EventHandler(this.btnAtualizarTecido_Click);
            // 
            // tabMaterial
            // 
            this.tabMaterial.BackColor = System.Drawing.Color.WhiteSmoke;
            this.tabMaterial.Controls.Add(this.panelDireitaMateriais);
            this.tabMaterial.Controls.Add(this.panelInputsMateriais);
            this.tabMaterial.Location = new System.Drawing.Point(4, 59);
            this.tabMaterial.Name = "tabMaterial";
            this.tabMaterial.Padding = new System.Windows.Forms.Padding(20);
            this.tabMaterial.Size = new System.Drawing.Size(1047, 703);
            this.tabMaterial.TabIndex = 3;
            this.tabMaterial.Text = "Material";
            // 
            // panelDireitaMateriais
            // 
            this.panelDireitaMateriais.BackColor = System.Drawing.Color.White;
            this.panelDireitaMateriais.Controls.Add(this.dgvMateriais);
            this.panelDireitaMateriais.Controls.Add(this.panelPesquisaMateriais);
            this.panelDireitaMateriais.Controls.Add(this.panelFiltroFornecedorMaterialDropdown);
            this.panelDireitaMateriais.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelDireitaMateriais.Location = new System.Drawing.Point(388, 20);
            this.panelDireitaMateriais.Name = "panelDireitaMateriais";
            this.panelDireitaMateriais.Padding = new System.Windows.Forms.Padding(20);
            this.panelDireitaMateriais.Size = new System.Drawing.Size(639, 663);
            this.panelDireitaMateriais.TabIndex = 2;
            // 
            // dgvMateriais
            // 
            this.dgvMateriais.AllowUserToAddRows = false;
            this.dgvMateriais.AllowUserToDeleteRows = false;
            this.dgvMateriais.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
            this.dgvMateriais.BackgroundColor = System.Drawing.Color.White;
            this.dgvMateriais.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvMateriais.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvMateriais.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvMateriais.EnableHeadersVisualStyles = false;
            this.dgvMateriais.Location = new System.Drawing.Point(20, 184);
            this.dgvMateriais.MultiSelect = false;
            this.dgvMateriais.Name = "dgvMateriais";
            this.dgvMateriais.ReadOnly = true;
            this.dgvMateriais.RowHeadersVisible = false;
            this.dgvMateriais.RowHeadersWidth = 51;
            this.dgvMateriais.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvMateriais.Size = new System.Drawing.Size(599, 459);
            this.dgvMateriais.TabIndex = 0;
            this.dgvMateriais.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvMateriais_CellClick);
            // 
            // panelPesquisaMateriais
            // 
            this.panelPesquisaMateriais.Controls.Add(this.grpFiltrosMateriais);
            this.panelPesquisaMateriais.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelPesquisaMateriais.Location = new System.Drawing.Point(20, 20);
            this.panelPesquisaMateriais.Name = "panelPesquisaMateriais";
            this.panelPesquisaMateriais.Padding = new System.Windows.Forms.Padding(0, 0, 0, 10);
            this.panelPesquisaMateriais.Size = new System.Drawing.Size(599, 164);
            this.panelPesquisaMateriais.TabIndex = 1;
            // 
            // grpFiltrosMateriais
            // 
            this.grpFiltrosMateriais.Controls.Add(this.btnPesquisarMaterial);
            this.grpFiltrosMateriais.Controls.Add(this.btnFiltroFornecedorMaterialDropdown);
            this.grpFiltrosMateriais.Controls.Add(this.lblFiltroFornecedorMaterial);
            this.grpFiltrosMateriais.Controls.Add(this.cmbFiltroTipoMaterial);
            this.grpFiltrosMateriais.Controls.Add(this.lblFiltroTipoMaterial);
            this.grpFiltrosMateriais.Controls.Add(this.cmbFiltroUnidadeMedida);
            this.grpFiltrosMateriais.Controls.Add(this.lblFiltroUnidadeMedida);
            this.grpFiltrosMateriais.Controls.Add(this.txtFiltroNomeMaterial);
            this.grpFiltrosMateriais.Controls.Add(this.lblFiltroNomeMaterial);
            this.grpFiltrosMateriais.Location = new System.Drawing.Point(20, 14);
            this.grpFiltrosMateriais.Name = "grpFiltrosMateriais";
            this.grpFiltrosMateriais.Size = new System.Drawing.Size(494, 135);
            this.grpFiltrosMateriais.TabIndex = 50;
            this.grpFiltrosMateriais.TabStop = false;
            this.grpFiltrosMateriais.Text = "Filtros de Pesquisa";
            // 
            // btnPesquisarMaterial
            // 
            this.btnPesquisarMaterial.Location = new System.Drawing.Point(410, 47);
            this.btnPesquisarMaterial.Name = "btnPesquisarMaterial";
            this.btnPesquisarMaterial.Size = new System.Drawing.Size(70, 28);
            this.btnPesquisarMaterial.TabIndex = 6;
            this.btnPesquisarMaterial.Text = "Pesquisar";
            this.btnPesquisarMaterial.UseVisualStyleBackColor = true;
            this.btnPesquisarMaterial.Click += new System.EventHandler(this.btnPesquisarMaterial_Click);
            // 
            // btnFiltroFornecedorMaterialDropdown
            // 
            this.btnFiltroFornecedorMaterialDropdown.Location = new System.Drawing.Point(10, 97);
            this.btnFiltroFornecedorMaterialDropdown.Name = "btnFiltroFornecedorMaterialDropdown";
            this.btnFiltroFornecedorMaterialDropdown.Size = new System.Drawing.Size(470, 28);
            this.btnFiltroFornecedorMaterialDropdown.TabIndex = 8;
            this.btnFiltroFornecedorMaterialDropdown.Text = "Selecionar fornecedores ▼";
            this.btnFiltroFornecedorMaterialDropdown.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnFiltroFornecedorMaterialDropdown.UseVisualStyleBackColor = true;
            this.btnFiltroFornecedorMaterialDropdown.Click += new System.EventHandler(this.btnFiltroFornecedorMaterialDropdown_Click);
            // 
            // lblFiltroFornecedorMaterial
            // 
            this.lblFiltroFornecedorMaterial.AutoSize = true;
            this.lblFiltroFornecedorMaterial.Location = new System.Drawing.Point(10, 75);
            this.lblFiltroFornecedorMaterial.Name = "lblFiltroFornecedorMaterial";
            this.lblFiltroFornecedorMaterial.Size = new System.Drawing.Size(81, 19);
            this.lblFiltroFornecedorMaterial.TabIndex = 7;
            this.lblFiltroFornecedorMaterial.Text = "Fornecedor:";
            // 
            // cmbFiltroTipoMaterial
            // 
            this.cmbFiltroTipoMaterial.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbFiltroTipoMaterial.FormattingEnabled = true;
            this.cmbFiltroTipoMaterial.Location = new System.Drawing.Point(280, 47);
            this.cmbFiltroTipoMaterial.Name = "cmbFiltroTipoMaterial";
            this.cmbFiltroTipoMaterial.Size = new System.Drawing.Size(120, 25);
            this.cmbFiltroTipoMaterial.TabIndex = 5;
            this.cmbFiltroTipoMaterial.SelectedIndexChanged += new System.EventHandler(this.cmbFiltroTipoMaterial_SelectedIndexChanged);
            // 
            // lblFiltroTipoMaterial
            // 
            this.lblFiltroTipoMaterial.AutoSize = true;
            this.lblFiltroTipoMaterial.Location = new System.Drawing.Point(280, 25);
            this.lblFiltroTipoMaterial.Name = "lblFiltroTipoMaterial";
            this.lblFiltroTipoMaterial.Size = new System.Drawing.Size(38, 19);
            this.lblFiltroTipoMaterial.TabIndex = 4;
            this.lblFiltroTipoMaterial.Text = "Tipo:";
            // 
            // cmbFiltroUnidadeMedida
            // 
            this.cmbFiltroUnidadeMedida.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbFiltroUnidadeMedida.FormattingEnabled = true;
            this.cmbFiltroUnidadeMedida.Location = new System.Drawing.Point(145, 47);
            this.cmbFiltroUnidadeMedida.Name = "cmbFiltroUnidadeMedida";
            this.cmbFiltroUnidadeMedida.Size = new System.Drawing.Size(120, 25);
            this.cmbFiltroUnidadeMedida.TabIndex = 3;
            this.cmbFiltroUnidadeMedida.SelectedIndexChanged += new System.EventHandler(this.cmbFiltroUnidadeMedida_SelectedIndexChanged);
            // 
            // lblFiltroUnidadeMedida
            // 
            this.lblFiltroUnidadeMedida.AutoSize = true;
            this.lblFiltroUnidadeMedida.Location = new System.Drawing.Point(145, 25);
            this.lblFiltroUnidadeMedida.Name = "lblFiltroUnidadeMedida";
            this.lblFiltroUnidadeMedida.Size = new System.Drawing.Size(113, 19);
            this.lblFiltroUnidadeMedida.TabIndex = 2;
            this.lblFiltroUnidadeMedida.Text = "Unidade Medida:";
            // 
            // txtFiltroNomeMaterial
            // 
            this.txtFiltroNomeMaterial.Location = new System.Drawing.Point(10, 47);
            this.txtFiltroNomeMaterial.Name = "txtFiltroNomeMaterial";
            this.txtFiltroNomeMaterial.Size = new System.Drawing.Size(120, 25);
            this.txtFiltroNomeMaterial.TabIndex = 1;
            this.txtFiltroNomeMaterial.TextChanged += new System.EventHandler(this.txtFiltroNomeMaterial_TextChanged);
            // 
            // lblFiltroNomeMaterial
            // 
            this.lblFiltroNomeMaterial.AutoSize = true;
            this.lblFiltroNomeMaterial.Location = new System.Drawing.Point(10, 25);
            this.lblFiltroNomeMaterial.Name = "lblFiltroNomeMaterial";
            this.lblFiltroNomeMaterial.Size = new System.Drawing.Size(49, 19);
            this.lblFiltroNomeMaterial.TabIndex = 0;
            this.lblFiltroNomeMaterial.Text = "Nome:";
            // 
            // panelFiltroFornecedorMaterialDropdown
            // 
            this.panelFiltroFornecedorMaterialDropdown.Controls.Add(this.clbFiltroFornecedorMaterial);
            this.panelFiltroFornecedorMaterialDropdown.Location = new System.Drawing.Point(10, 127);
            this.panelFiltroFornecedorMaterialDropdown.Name = "panelFiltroFornecedorMaterialDropdown";
            this.panelFiltroFornecedorMaterialDropdown.Size = new System.Drawing.Size(220, 84);
            this.panelFiltroFornecedorMaterialDropdown.TabIndex = 0;
            this.panelFiltroFornecedorMaterialDropdown.Visible = false;
            // 
            // clbFiltroFornecedorMaterial
            // 
            this.clbFiltroFornecedorMaterial.CheckOnClick = true;
            this.clbFiltroFornecedorMaterial.Dock = System.Windows.Forms.DockStyle.Fill;
            this.clbFiltroFornecedorMaterial.FormattingEnabled = true;
            this.clbFiltroFornecedorMaterial.Location = new System.Drawing.Point(0, 0);
            this.clbFiltroFornecedorMaterial.Name = "clbFiltroFornecedorMaterial";
            this.clbFiltroFornecedorMaterial.Size = new System.Drawing.Size(220, 84);
            this.clbFiltroFornecedorMaterial.TabIndex = 0;
            this.clbFiltroFornecedorMaterial.ItemCheck += new System.Windows.Forms.ItemCheckEventHandler(this.clbFiltroFornecedorMaterial_ItemCheck);
            // 
            // panelInputsMateriais
            // 
            this.panelInputsMateriais.AutoScroll = true;
            this.panelInputsMateriais.BackColor = System.Drawing.Color.White;
            this.panelInputsMateriais.Controls.Add(this.btnAtualizarMaterial);
            this.panelInputsMateriais.Controls.Add(this.btnLimparMaterial);
            this.panelInputsMateriais.Controls.Add(this.btnAdicionarMaterial);
            this.panelInputsMateriais.Controls.Add(this.cmbFornecedorMaterial);
            this.panelInputsMateriais.Controls.Add(this.lblFornecedorMaterial);
            this.panelInputsMateriais.Controls.Add(this.cmbTipoMaterial);
            this.panelInputsMateriais.Controls.Add(this.lblTipoMaterial);
            this.panelInputsMateriais.Controls.Add(this.cmbUnidadeMedida);
            this.panelInputsMateriais.Controls.Add(this.lblUnidadeMedida);
            this.panelInputsMateriais.Controls.Add(this.txtQuantidadeMaterial);
            this.panelInputsMateriais.Controls.Add(this.lblQuantidadeMaterial);
            this.panelInputsMateriais.Controls.Add(this.txtCustoMaterial);
            this.panelInputsMateriais.Controls.Add(this.lblCustoMaterial);
            this.panelInputsMateriais.Controls.Add(this.txtNomeMaterial);
            this.panelInputsMateriais.Controls.Add(this.lblNomeMaterial);
            this.panelInputsMateriais.Controls.Add(this.textBoxIDMaterial);
            this.panelInputsMateriais.Controls.Add(this.lblIDMaterial);
            this.panelInputsMateriais.Controls.Add(this.lblTituloMateriais);
            this.panelInputsMateriais.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelInputsMateriais.Location = new System.Drawing.Point(20, 20);
            this.panelInputsMateriais.Name = "panelInputsMateriais";
            this.panelInputsMateriais.Padding = new System.Windows.Forms.Padding(20);
            this.panelInputsMateriais.Size = new System.Drawing.Size(368, 663);
            this.panelInputsMateriais.TabIndex = 1;
            // 
            // btnAtualizarMaterial
            // 
            this.btnAtualizarMaterial.Location = new System.Drawing.Point(163, 410);
            this.btnAtualizarMaterial.Name = "btnAtualizarMaterial";
            this.btnAtualizarMaterial.Size = new System.Drawing.Size(120, 35);
            this.btnAtualizarMaterial.TabIndex = 21;
            this.btnAtualizarMaterial.Text = "Atualizar";
            this.btnAtualizarMaterial.UseVisualStyleBackColor = true;
            this.btnAtualizarMaterial.Click += new System.EventHandler(this.btnAtualizarMaterial_Click);
            // 
            // btnLimparMaterial
            // 
            this.btnLimparMaterial.Location = new System.Drawing.Point(23, 456);
            this.btnLimparMaterial.Name = "btnLimparMaterial";
            this.btnLimparMaterial.Size = new System.Drawing.Size(120, 35);
            this.btnLimparMaterial.TabIndex = 22;
            this.btnLimparMaterial.Text = "Limpar";
            this.btnLimparMaterial.UseVisualStyleBackColor = true;
            this.btnLimparMaterial.Click += new System.EventHandler(this.btnLimparMaterial_Click);
            // 
            // btnAdicionarMaterial
            // 
            this.btnAdicionarMaterial.Location = new System.Drawing.Point(23, 410);
            this.btnAdicionarMaterial.Name = "btnAdicionarMaterial";
            this.btnAdicionarMaterial.Size = new System.Drawing.Size(120, 35);
            this.btnAdicionarMaterial.TabIndex = 20;
            this.btnAdicionarMaterial.Text = "Adicionar";
            this.btnAdicionarMaterial.UseVisualStyleBackColor = true;
            this.btnAdicionarMaterial.Click += new System.EventHandler(this.btnAdicionarMaterial_Click);
            // 
            // cmbFornecedorMaterial
            // 
            this.cmbFornecedorMaterial.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbFornecedorMaterial.FormattingEnabled = true;
            this.cmbFornecedorMaterial.Location = new System.Drawing.Point(23, 369);
            this.cmbFornecedorMaterial.Name = "cmbFornecedorMaterial";
            this.cmbFornecedorMaterial.Size = new System.Drawing.Size(260, 25);
            this.cmbFornecedorMaterial.TabIndex = 14;
            this.cmbFornecedorMaterial.SelectedIndexChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            // 
            // lblFornecedorMaterial
            // 
            this.lblFornecedorMaterial.Location = new System.Drawing.Point(19, 344);
            this.lblFornecedorMaterial.Name = "lblFornecedorMaterial";
            this.lblFornecedorMaterial.Size = new System.Drawing.Size(260, 22);
            this.lblFornecedorMaterial.TabIndex = 13;
            this.lblFornecedorMaterial.Text = "Fornecedor";
            // 
            // cmbTipoMaterial
            // 
            this.cmbTipoMaterial.FormattingEnabled = true;
            this.cmbTipoMaterial.Location = new System.Drawing.Point(23, 311);
            this.cmbTipoMaterial.Name = "cmbTipoMaterial";
            this.cmbTipoMaterial.Size = new System.Drawing.Size(260, 25);
            this.cmbTipoMaterial.TabIndex = 12;
            this.cmbTipoMaterial.SelectedIndexChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            this.cmbTipoMaterial.TextChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            // 
            // lblTipoMaterial
            // 
            this.lblTipoMaterial.Location = new System.Drawing.Point(19, 289);
            this.lblTipoMaterial.Name = "lblTipoMaterial";
            this.lblTipoMaterial.Size = new System.Drawing.Size(260, 22);
            this.lblTipoMaterial.TabIndex = 11;
            this.lblTipoMaterial.Text = "Tipo";
            // 
            // cmbUnidadeMedida
            // 
            this.cmbUnidadeMedida.FormattingEnabled = true;
            this.cmbUnidadeMedida.Location = new System.Drawing.Point(23, 255);
            this.cmbUnidadeMedida.Name = "cmbUnidadeMedida";
            this.cmbUnidadeMedida.Size = new System.Drawing.Size(260, 25);
            this.cmbUnidadeMedida.TabIndex = 10;
            this.cmbUnidadeMedida.SelectedIndexChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            this.cmbUnidadeMedida.TextChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            // 
            // lblUnidadeMedida
            // 
            this.lblUnidadeMedida.Location = new System.Drawing.Point(19, 233);
            this.lblUnidadeMedida.Name = "lblUnidadeMedida";
            this.lblUnidadeMedida.Size = new System.Drawing.Size(260, 22);
            this.lblUnidadeMedida.TabIndex = 9;
            this.lblUnidadeMedida.Text = "Unidade de Medida";
            // 
            // txtQuantidadeMaterial
            // 
            this.txtQuantidadeMaterial.Location = new System.Drawing.Point(23, 199);
            this.txtQuantidadeMaterial.Name = "txtQuantidadeMaterial";
            this.txtQuantidadeMaterial.Size = new System.Drawing.Size(260, 25);
            this.txtQuantidadeMaterial.TabIndex = 8;
            this.txtQuantidadeMaterial.TextChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            // 
            // lblQuantidadeMaterial
            // 
            this.lblQuantidadeMaterial.Location = new System.Drawing.Point(19, 177);
            this.lblQuantidadeMaterial.Name = "lblQuantidadeMaterial";
            this.lblQuantidadeMaterial.Size = new System.Drawing.Size(260, 22);
            this.lblQuantidadeMaterial.TabIndex = 7;
            this.lblQuantidadeMaterial.Text = "Quantidade";
            // 
            // txtCustoMaterial
            // 
            this.txtCustoMaterial.Location = new System.Drawing.Point(23, 144);
            this.txtCustoMaterial.Name = "txtCustoMaterial";
            this.txtCustoMaterial.Size = new System.Drawing.Size(260, 25);
            this.txtCustoMaterial.TabIndex = 6;
            this.txtCustoMaterial.TextChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            // 
            // lblCustoMaterial
            // 
            this.lblCustoMaterial.Location = new System.Drawing.Point(19, 123);
            this.lblCustoMaterial.Name = "lblCustoMaterial";
            this.lblCustoMaterial.Size = new System.Drawing.Size(260, 22);
            this.lblCustoMaterial.TabIndex = 5;
            this.lblCustoMaterial.Text = "Custo Unitário";
            // 
            // txtNomeMaterial
            // 
            this.txtNomeMaterial.Location = new System.Drawing.Point(23, 89);
            this.txtNomeMaterial.Name = "txtNomeMaterial";
            this.txtNomeMaterial.Size = new System.Drawing.Size(229, 25);
            this.txtNomeMaterial.TabIndex = 4;
            this.txtNomeMaterial.TextChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            // 
            // lblNomeMaterial
            // 
            this.lblNomeMaterial.Location = new System.Drawing.Point(19, 67);
            this.lblNomeMaterial.Name = "lblNomeMaterial";
            this.lblNomeMaterial.Size = new System.Drawing.Size(229, 22);
            this.lblNomeMaterial.TabIndex = 3;
            this.lblNomeMaterial.Text = "Nome";
            // 
            // textBoxIDMaterial
            // 
            this.textBoxIDMaterial.Location = new System.Drawing.Point(258, 89);
            this.textBoxIDMaterial.Name = "textBoxIDMaterial";
            this.textBoxIDMaterial.ReadOnly = true;
            this.textBoxIDMaterial.Size = new System.Drawing.Size(25, 25);
            this.textBoxIDMaterial.TabIndex = 22;
            this.textBoxIDMaterial.TextChanged += new System.EventHandler(this.AtualizarEstadoBotoesMateriais);
            // 
            // lblIDMaterial
            // 
            this.lblIDMaterial.Location = new System.Drawing.Point(258, 67);
            this.lblIDMaterial.Name = "lblIDMaterial";
            this.lblIDMaterial.Size = new System.Drawing.Size(25, 22);
            this.lblIDMaterial.TabIndex = 23;
            this.lblIDMaterial.Text = "ID";
            // 
            // lblTituloMateriais
            // 
            this.lblTituloMateriais.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lblTituloMateriais.Location = new System.Drawing.Point(15, 15);
            this.lblTituloMateriais.Name = "lblTituloMateriais";
            this.lblTituloMateriais.Size = new System.Drawing.Size(260, 40);
            this.lblTituloMateriais.TabIndex = 0;
            this.lblTituloMateriais.Text = "Gestão de Materiais";
            // 
            // vScrollBar1
            // 
            this.vScrollBar1.Location = new System.Drawing.Point(0, 0);
            this.vScrollBar1.Name = "vScrollBar1";
            this.vScrollBar1.Size = new System.Drawing.Size(17, 80);
            this.vScrollBar1.TabIndex = 0;
            // 
            // FrmInventario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1055, 766);
            this.Controls.Add(this.tabStock);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.Name = "FrmInventario";
            this.Text = "Gestão de Stock";
            this.Load += new System.EventHandler(this.FrmInventario_Load);
            this.tabStock.ResumeLayout(false);
            this.tabTecidos.ResumeLayout(false);
            this.panelDireitaTecidos.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvTecidos)).EndInit();
            this.panelPesquisaTecidos.ResumeLayout(false);
            this.grpFiltrosTecido.ResumeLayout(false);
            this.grpFiltrosTecido.PerformLayout();
            this.panelFiltroCorDropdown.ResumeLayout(false);
            this.panelFiltroTipoDropdown.ResumeLayout(false);
            this.panelFiltroFornecedorDropdown.ResumeLayout(false);
            this.panelFiltroPadraoDropdown.ResumeLayout(false);
            this.panelInputsTecidos.ResumeLayout(false);
            this.panelInputsTecidos.PerformLayout();
            this.tabMaterial.ResumeLayout(false);
            this.panelDireitaMateriais.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvMateriais)).EndInit();
            this.panelPesquisaMateriais.ResumeLayout(false);
            this.grpFiltrosMateriais.ResumeLayout(false);
            this.grpFiltrosMateriais.PerformLayout();
            this.panelFiltroFornecedorMaterialDropdown.ResumeLayout(false);
            this.panelInputsMateriais.ResumeLayout(false);
            this.panelInputsMateriais.PerformLayout();
            this.tabProdutos.ResumeLayout(false);
            this.panelDireitaProdutos.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvProdutos)).EndInit();
            this.panelInputsProdutos.ResumeLayout(false);
            this.panelInputsProdutos.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudStockProduto)).EndInit();
            this.panelPesquisaProdutos.ResumeLayout(false);
            this.grpFiltrosProdutos.ResumeLayout(false);
            this.grpFiltrosProdutos.PerformLayout();
            this.panelFiltroTamanhoDropdown.ResumeLayout(false);
            this.panelFiltroCorProdutoDropdown.ResumeLayout(false);
            this.panelFiltroCategoriaDropdown.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabStock;
        private System.Windows.Forms.TabPage tabProdutos;
        private System.Windows.Forms.TabPage tabTecidos;
        private System.Windows.Forms.TabPage tabMaterial;

        private System.Windows.Forms.Panel panelDireitaMateriais;
        private System.Windows.Forms.DataGridView dgvMateriais;
        private System.Windows.Forms.Panel panelPesquisaMateriais;
        private System.Windows.Forms.GroupBox grpFiltrosMateriais;
        private System.Windows.Forms.Button btnPesquisarMaterial;
        private System.Windows.Forms.Button btnFiltroFornecedorMaterialDropdown;
        private System.Windows.Forms.Label lblFiltroFornecedorMaterial;
        private System.Windows.Forms.Label lblFiltroTipoMaterial;
        private System.Windows.Forms.ComboBox cmbFiltroTipoMaterial;
        private System.Windows.Forms.ComboBox cmbFiltroUnidadeMedida;
        private System.Windows.Forms.Label lblFiltroUnidadeMedida;
        private System.Windows.Forms.TextBox txtFiltroNomeMaterial;
        private System.Windows.Forms.Label lblFiltroNomeMaterial;
        private System.Windows.Forms.Panel panelFiltroFornecedorMaterialDropdown;
        private System.Windows.Forms.CheckedListBox clbFiltroFornecedorMaterial;
        private System.Windows.Forms.Panel panelInputsMateriais;
        private System.Windows.Forms.ComboBox cmbFornecedorMaterial;
        private System.Windows.Forms.Button btnAdicionarMaterial;
        private System.Windows.Forms.ComboBox cmbTipoMaterial;
        private System.Windows.Forms.ComboBox cmbUnidadeMedida;
        private System.Windows.Forms.Label lblFornecedorMaterial;
        private System.Windows.Forms.Label lblTipoMaterial;
        private System.Windows.Forms.Label lblUnidadeMedida;
        private System.Windows.Forms.TextBox txtQuantidadeMaterial;
        private System.Windows.Forms.Label lblQuantidadeMaterial;
        private System.Windows.Forms.TextBox txtCustoMaterial;
        private System.Windows.Forms.Label lblCustoMaterial;
        private System.Windows.Forms.TextBox txtNomeMaterial;
        private System.Windows.Forms.Label lblNomeMaterial;
        private System.Windows.Forms.TextBox textBoxIDMaterial;
        private System.Windows.Forms.Label lblIDMaterial;
        private System.Windows.Forms.Label lblTituloMateriais;
        private System.Windows.Forms.Button btnLimparMaterial;
        private System.Windows.Forms.Button btnAtualizarMaterial;

        private System.Windows.Forms.Panel panelInputsTecidos;
        private System.Windows.Forms.DataGridView dgvTecidos;

        private System.Windows.Forms.Label lblTituloTecidos;
        private System.Windows.Forms.Label lblNome;
        private System.Windows.Forms.TextBox txtNome;
        private System.Windows.Forms.Label lblPreco;
        private System.Windows.Forms.TextBox txtPreco;
        private System.Windows.Forms.Label lblQuantidade;
        private System.Windows.Forms.TextBox txtQuantidade;
        private System.Windows.Forms.Label lblCodigo;
        private System.Windows.Forms.TextBox txtCodigo;
        private System.Windows.Forms.Label lblCor;
        private System.Windows.Forms.Label lblTipo;
        private System.Windows.Forms.Label lblPadrao;
        private System.Windows.Forms.ComboBox cmbPadrao;
        private System.Windows.Forms.Label lblFornecedor;

        private System.Windows.Forms.Button btnAdicionarTecido;
        private System.Windows.Forms.Button btnAtualizarTecido;
        private System.Windows.Forms.Panel panelDireitaTecidos;
        private System.Windows.Forms.Panel panelPesquisaTecidos;
        private System.Windows.Forms.Button btnPesquisarTecido;
        private System.Windows.Forms.TextBox textBoxIDtecido;
        private System.Windows.Forms.Label labelIDTecido;

        private System.Windows.Forms.GroupBox grpFiltrosTecido;
        private System.Windows.Forms.Label lblFiltroNome;
        private System.Windows.Forms.TextBox txtFiltroNome;
        private System.Windows.Forms.Label lblFiltroPreco;
        private System.Windows.Forms.TextBox txtFiltroPrecoMin;
        private System.Windows.Forms.Label lblFiltroPrecoSeparador;
        private System.Windows.Forms.TextBox txtFiltroPrecoMax;
        private System.Windows.Forms.Label lblFiltroPadrao;
        private System.Windows.Forms.Button btnFiltroPadraoDropdown;
        private System.Windows.Forms.Label lblFiltroCor;
        private System.Windows.Forms.Button btnFiltroCorDropdown;
        private System.Windows.Forms.Panel panelFiltroCorDropdown;
        private System.Windows.Forms.CheckedListBox clbFiltroCor;
        private System.Windows.Forms.Label lblFiltroTipo;
        private System.Windows.Forms.Button btnFiltroTipoDropdown;
        private System.Windows.Forms.Panel panelFiltroTipoDropdown;
        private System.Windows.Forms.CheckedListBox clbFiltroTipo;
        private System.Windows.Forms.Label lblFiltroFornecedor;
        private System.Windows.Forms.Button btnFiltroFornecedorDropdown;
        private System.Windows.Forms.Panel panelFiltroFornecedorDropdown;
        private System.Windows.Forms.CheckedListBox clbFiltroFornecedor;
        private System.Windows.Forms.Panel panelFiltroPadraoDropdown;
        private System.Windows.Forms.CheckedListBox clbFiltroPadrao;
        private System.Windows.Forms.VScrollBar vScrollBar1;
        private System.Windows.Forms.Button btnLimparTecido;
        private System.Windows.Forms.ComboBox cmbFornecedor;
        private System.Windows.Forms.ComboBox cmbTipo;
        private System.Windows.Forms.ComboBox cmbCor;

        private System.Windows.Forms.Panel panelDireitaProdutos;
        private System.Windows.Forms.DataGridView dgvProdutos;
        private System.Windows.Forms.Panel panelInputsProdutos;
        private System.Windows.Forms.Label lblIDProduto;
        private System.Windows.Forms.TextBox textBoxIDProduto;
        private System.Windows.Forms.Label lblCodigoProduto;
        private System.Windows.Forms.TextBox txtCodigoProduto;
        private System.Windows.Forms.Label lblNomeProduto;
        private System.Windows.Forms.TextBox txtNomeProduto;
        private System.Windows.Forms.Label lblTamanhoProduto;
        private System.Windows.Forms.ComboBox cmbTamanhoProduto;
        private System.Windows.Forms.Label lblCorProduto;
        private System.Windows.Forms.ComboBox cmbCorProduto;
        private System.Windows.Forms.Label lblPrecoProduto;
        private System.Windows.Forms.TextBox txtPrecoProduto;
        private System.Windows.Forms.Label lblStockProduto;
        private System.Windows.Forms.NumericUpDown nudStockProduto;
        private System.Windows.Forms.Label lblCategoriaProduto;
        private System.Windows.Forms.ComboBox cmbCategoriaProduto;
        private System.Windows.Forms.Button btnAdicionarProduto;
        private System.Windows.Forms.Button btnAtualizarProduto;
        private System.Windows.Forms.Button btnEliminarProduto;
        private System.Windows.Forms.Button btnLimparProduto;
        private System.Windows.Forms.Panel panelPesquisaProdutos;
        private System.Windows.Forms.GroupBox grpFiltrosProdutos;
        private System.Windows.Forms.Label lblFiltroNomeProduto;
        private System.Windows.Forms.TextBox txtFiltroNomeProduto;
        private System.Windows.Forms.Label lblFiltroPrecoMaxProduto;
        private System.Windows.Forms.TextBox txtFiltroPrecoMaxProduto;
        private System.Windows.Forms.Button btnPesquisarProdutos;
        private System.Windows.Forms.Label lblFiltroTamanhoProduto;
        private System.Windows.Forms.Button btnFiltroTamanhoDropdown;
        private System.Windows.Forms.Label lblFiltroCorProduto;
        private System.Windows.Forms.Button btnFiltroCorProdutoDropdown;
        private System.Windows.Forms.Label lblFiltroCategoriaProduto;
        private System.Windows.Forms.Button btnFiltroCategoriaDropdown;
        private System.Windows.Forms.Panel panelFiltroTamanhoDropdown;
        private System.Windows.Forms.CheckedListBox clbFiltroTamanho;
        private System.Windows.Forms.Panel panelFiltroCorProdutoDropdown;
        private System.Windows.Forms.CheckedListBox clbFiltroCorProduto;
        private System.Windows.Forms.Panel panelFiltroCategoriaDropdown;
        private System.Windows.Forms.CheckedListBox clbFiltroCategoria;
    }
}