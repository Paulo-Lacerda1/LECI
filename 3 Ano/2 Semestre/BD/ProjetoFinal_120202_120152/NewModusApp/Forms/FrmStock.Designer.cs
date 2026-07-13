namespace NewModusApp.Forms
{
    partial class FrmStock
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
            this.lblPesquisarTecido = new System.Windows.Forms.Label();
            this.btnPesquisarTecido = new System.Windows.Forms.Button();
            this.txtPesquisaTecido = new System.Windows.Forms.TextBox();
            this.panelInputsTecidos = new System.Windows.Forms.Panel();
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
            this.txtCor = new System.Windows.Forms.TextBox();
            this.lblTipo = new System.Windows.Forms.Label();
            this.txtTipo = new System.Windows.Forms.TextBox();
            this.lblPadrao = new System.Windows.Forms.Label();
            this.txtPadrao = new System.Windows.Forms.TextBox();
            this.lblFornecedor = new System.Windows.Forms.Label();
            this.txtFornecedor = new System.Windows.Forms.TextBox();
            this.btnAdicionarTecido = new System.Windows.Forms.Button();
            this.btnAtualizarTecido = new System.Windows.Forms.Button();
            this.tabMaterial = new System.Windows.Forms.TabPage();
            this.tabStock.SuspendLayout();
            this.tabTecidos.SuspendLayout();
            this.panelDireitaTecidos.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvTecidos)).BeginInit();
            this.panelPesquisaTecidos.SuspendLayout();
            this.panelInputsTecidos.SuspendLayout();
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
            this.tabStock.Size = new System.Drawing.Size(1055, 681);
            this.tabStock.SizeMode = System.Windows.Forms.TabSizeMode.Fixed;
            this.tabStock.TabIndex = 0;
            this.tabStock.SelectedIndexChanged += new System.EventHandler(this.tabStock_SelectedIndexChanged);
            // 
            // tabProdutos
            // 
            this.tabProdutos.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.tabProdutos.Location = new System.Drawing.Point(4, 59);
            this.tabProdutos.Name = "tabProdutos";
            this.tabProdutos.Size = new System.Drawing.Size(1047, 618);
            this.tabProdutos.TabIndex = 0;
            this.tabProdutos.Text = "Produtos";
            // 
            // tabTecidos
            // 
            this.tabTecidos.BackColor = System.Drawing.Color.WhiteSmoke;
            this.tabTecidos.Controls.Add(this.panelDireitaTecidos);
            this.tabTecidos.Controls.Add(this.panelInputsTecidos);
            this.tabTecidos.Location = new System.Drawing.Point(4, 59);
            this.tabTecidos.Name = "tabTecidos";
            this.tabTecidos.Padding = new System.Windows.Forms.Padding(20);
            this.tabTecidos.Size = new System.Drawing.Size(1047, 618);
            this.tabTecidos.TabIndex = 2;
            this.tabTecidos.Text = "Tecidos";
            // 
            // panelDireitaTecidos
            // 
            this.panelDireitaTecidos.BackColor = System.Drawing.Color.White;
            this.panelDireitaTecidos.Controls.Add(this.dgvTecidos);
            this.panelDireitaTecidos.Controls.Add(this.panelPesquisaTecidos);
            this.panelDireitaTecidos.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelDireitaTecidos.Location = new System.Drawing.Point(388, 20);
            this.panelDireitaTecidos.Name = "panelDireitaTecidos";
            this.panelDireitaTecidos.Padding = new System.Windows.Forms.Padding(20);
            this.panelDireitaTecidos.Size = new System.Drawing.Size(639, 578);
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
            this.dgvTecidos.Location = new System.Drawing.Point(20, 144);
            this.dgvTecidos.MultiSelect = false;
            this.dgvTecidos.Name = "dgvTecidos";
            this.dgvTecidos.ReadOnly = true;
            this.dgvTecidos.RowHeadersVisible = false;
            this.dgvTecidos.RowHeadersWidth = 51;
            this.dgvTecidos.RowTemplate.Height = 24;
            this.dgvTecidos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvTecidos.Size = new System.Drawing.Size(599, 414);
            this.dgvTecidos.TabIndex = 0;
            this.dgvTecidos.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvTecidos_CellClick);
            this.dgvTecidos.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvTecidos_CellContentClick);
            // 
            // panelPesquisaTecidos
            // 
            this.panelPesquisaTecidos.Controls.Add(this.lblPesquisarTecido);
            this.panelPesquisaTecidos.Controls.Add(this.btnPesquisarTecido);
            this.panelPesquisaTecidos.Controls.Add(this.txtPesquisaTecido);
            this.panelPesquisaTecidos.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelPesquisaTecidos.Location = new System.Drawing.Point(20, 20);
            this.panelPesquisaTecidos.Name = "panelPesquisaTecidos";
            this.panelPesquisaTecidos.Padding = new System.Windows.Forms.Padding(0, 0, 0, 10);
            this.panelPesquisaTecidos.Size = new System.Drawing.Size(599, 124);
            this.panelPesquisaTecidos.TabIndex = 1;
            // 
            // lblPesquisarTecido
            // 
            this.lblPesquisarTecido.AutoSize = true;
            this.lblPesquisarTecido.Location = new System.Drawing.Point(16, 7);
            this.lblPesquisarTecido.Name = "lblPesquisarTecido";
            this.lblPesquisarTecido.Size = new System.Drawing.Size(139, 23);
            this.lblPesquisarTecido.TabIndex = 2;
            this.lblPesquisarTecido.Text = "Nome do Tecido:";
            // 
            // btnPesquisarTecido
            // 
            this.btnPesquisarTecido.Location = new System.Drawing.Point(20, 75);
            this.btnPesquisarTecido.Name = "btnPesquisarTecido";
            this.btnPesquisarTecido.Size = new System.Drawing.Size(118, 38);
            this.btnPesquisarTecido.TabIndex = 1;
            this.btnPesquisarTecido.Text = "Pesquisar";
            this.btnPesquisarTecido.UseVisualStyleBackColor = true;
            // 
            // txtPesquisaTecido
            // 
            this.txtPesquisaTecido.Location = new System.Drawing.Point(20, 39);
            this.txtPesquisaTecido.Name = "txtPesquisaTecido";
            this.txtPesquisaTecido.Size = new System.Drawing.Size(200, 30);
            this.txtPesquisaTecido.TabIndex = 0;
            // 
            // panelInputsTecidos
            // 
            this.panelInputsTecidos.BackColor = System.Drawing.Color.White;
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
            this.panelInputsTecidos.Controls.Add(this.txtCor);
            this.panelInputsTecidos.Controls.Add(this.lblTipo);
            this.panelInputsTecidos.Controls.Add(this.txtTipo);
            this.panelInputsTecidos.Controls.Add(this.lblPadrao);
            this.panelInputsTecidos.Controls.Add(this.txtPadrao);
            this.panelInputsTecidos.Controls.Add(this.lblFornecedor);
            this.panelInputsTecidos.Controls.Add(this.txtFornecedor);
            this.panelInputsTecidos.Controls.Add(this.btnAdicionarTecido);
            this.panelInputsTecidos.Controls.Add(this.btnAtualizarTecido);
            this.panelInputsTecidos.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelInputsTecidos.Location = new System.Drawing.Point(20, 20);
            this.panelInputsTecidos.Name = "panelInputsTecidos";
            this.panelInputsTecidos.Padding = new System.Windows.Forms.Padding(20);
            this.panelInputsTecidos.Size = new System.Drawing.Size(368, 578);
            this.panelInputsTecidos.TabIndex = 1;
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
            this.lblNome.Location = new System.Drawing.Point(23, 67);
            this.lblNome.Name = "lblNome";
            this.lblNome.Size = new System.Drawing.Size(260, 22);
            this.lblNome.TabIndex = 3;
            this.lblNome.Text = "Nome";
            // 
            // txtNome
            // 
            this.txtNome.Location = new System.Drawing.Point(23, 89);
            this.txtNome.Name = "txtNome";
            this.txtNome.Size = new System.Drawing.Size(260, 30);
            this.txtNome.TabIndex = 4;
            // 
            // lblPreco
            // 
            this.lblPreco.Location = new System.Drawing.Point(23, 122);
            this.lblPreco.Name = "lblPreco";
            this.lblPreco.Size = new System.Drawing.Size(260, 22);
            this.lblPreco.TabIndex = 5;
            this.lblPreco.Text = "Preço";
            // 
            // txtPreco
            // 
            this.txtPreco.Location = new System.Drawing.Point(23, 144);
            this.txtPreco.Name = "txtPreco";
            this.txtPreco.Size = new System.Drawing.Size(260, 30);
            this.txtPreco.TabIndex = 6;
            // 
            // lblQuantidade
            // 
            this.lblQuantidade.Location = new System.Drawing.Point(23, 177);
            this.lblQuantidade.Name = "lblQuantidade";
            this.lblQuantidade.Size = new System.Drawing.Size(260, 22);
            this.lblQuantidade.TabIndex = 7;
            this.lblQuantidade.Text = "Quantidade";
            // 
            // txtQuantidade
            // 
            this.txtQuantidade.Location = new System.Drawing.Point(23, 199);
            this.txtQuantidade.Name = "txtQuantidade";
            this.txtQuantidade.Size = new System.Drawing.Size(260, 30);
            this.txtQuantidade.TabIndex = 8;
            // 
            // lblCodigo
            // 
            this.lblCodigo.Location = new System.Drawing.Point(23, 232);
            this.lblCodigo.Name = "lblCodigo";
            this.lblCodigo.Size = new System.Drawing.Size(260, 22);
            this.lblCodigo.TabIndex = 9;
            this.lblCodigo.Text = "Código";
            // 
            // txtCodigo
            // 
            this.txtCodigo.Location = new System.Drawing.Point(23, 254);
            this.txtCodigo.Name = "txtCodigo";
            this.txtCodigo.Size = new System.Drawing.Size(260, 30);
            this.txtCodigo.TabIndex = 10;
            // 
            // lblCor
            // 
            this.lblCor.Location = new System.Drawing.Point(23, 287);
            this.lblCor.Name = "lblCor";
            this.lblCor.Size = new System.Drawing.Size(260, 22);
            this.lblCor.TabIndex = 11;
            this.lblCor.Text = "Cor";
            // 
            // txtCor
            // 
            this.txtCor.Location = new System.Drawing.Point(23, 309);
            this.txtCor.Name = "txtCor";
            this.txtCor.Size = new System.Drawing.Size(260, 30);
            this.txtCor.TabIndex = 12;
            // 
            // lblTipo
            // 
            this.lblTipo.Location = new System.Drawing.Point(23, 342);
            this.lblTipo.Name = "lblTipo";
            this.lblTipo.Size = new System.Drawing.Size(260, 22);
            this.lblTipo.TabIndex = 13;
            this.lblTipo.Text = "Tipo";
            // 
            // txtTipo
            // 
            this.txtTipo.Location = new System.Drawing.Point(23, 364);
            this.txtTipo.Name = "txtTipo";
            this.txtTipo.Size = new System.Drawing.Size(260, 30);
            this.txtTipo.TabIndex = 14;
            // 
            // lblPadrao
            // 
            this.lblPadrao.Location = new System.Drawing.Point(23, 397);
            this.lblPadrao.Name = "lblPadrao";
            this.lblPadrao.Size = new System.Drawing.Size(260, 22);
            this.lblPadrao.TabIndex = 15;
            this.lblPadrao.Text = "Padrão";
            // 
            // txtPadrao
            // 
            this.txtPadrao.Location = new System.Drawing.Point(23, 419);
            this.txtPadrao.Name = "txtPadrao";
            this.txtPadrao.Size = new System.Drawing.Size(260, 30);
            this.txtPadrao.TabIndex = 16;
            // 
            // lblFornecedor
            // 
            this.lblFornecedor.Location = new System.Drawing.Point(23, 452);
            this.lblFornecedor.Name = "lblFornecedor";
            this.lblFornecedor.Size = new System.Drawing.Size(260, 22);
            this.lblFornecedor.TabIndex = 17;
            this.lblFornecedor.Text = "Fornecedor (ID)";
            // 
            // txtFornecedor
            // 
            this.txtFornecedor.Location = new System.Drawing.Point(23, 474);
            this.txtFornecedor.Name = "txtFornecedor";
            this.txtFornecedor.Size = new System.Drawing.Size(260, 30);
            this.txtFornecedor.TabIndex = 18;
            // 
            // btnAdicionarTecido
            // 
            this.btnAdicionarTecido.Location = new System.Drawing.Point(27, 520);
            this.btnAdicionarTecido.Name = "btnAdicionarTecido";
            this.btnAdicionarTecido.Size = new System.Drawing.Size(120, 35);
            this.btnAdicionarTecido.TabIndex = 20;
            this.btnAdicionarTecido.Text = "Adicionar";
            this.btnAdicionarTecido.UseVisualStyleBackColor = true;
            this.btnAdicionarTecido.Click += new System.EventHandler(this.btnAdicionarTecido_Click);
            // 
            // btnAtualizarTecido
            // 
            this.btnAtualizarTecido.Location = new System.Drawing.Point(163, 520);
            this.btnAtualizarTecido.Name = "btnAtualizarTecido";
            this.btnAtualizarTecido.Size = new System.Drawing.Size(120, 35);
            this.btnAtualizarTecido.TabIndex = 21;
            this.btnAtualizarTecido.Text = "Atualizar";
            this.btnAtualizarTecido.UseVisualStyleBackColor = true;
            this.btnAtualizarTecido.Click += new System.EventHandler(this.btnAtualizarTecido_Click);
            // 
            // tabMaterial
            // 
            this.tabMaterial.Location = new System.Drawing.Point(4, 59);
            this.tabMaterial.Name = "tabMaterial";
            this.tabMaterial.Size = new System.Drawing.Size(1047, 618);
            this.tabMaterial.TabIndex = 3;
            this.tabMaterial.Text = "Material";
            // 
            // FrmStock
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 23F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1055, 681);
            this.Controls.Add(this.tabStock);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.Name = "FrmStock";
            this.Text = "Gestão de Stock";
            this.Load += new System.EventHandler(this.FrmStock_Load);
            this.tabStock.ResumeLayout(false);
            this.tabTecidos.ResumeLayout(false);
            this.panelDireitaTecidos.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvTecidos)).EndInit();
            this.panelPesquisaTecidos.ResumeLayout(false);
            this.panelPesquisaTecidos.PerformLayout();
            this.panelInputsTecidos.ResumeLayout(false);
            this.panelInputsTecidos.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabStock;
        private System.Windows.Forms.TabPage tabProdutos;
        private System.Windows.Forms.TabPage tabTecidos;
        private System.Windows.Forms.TabPage tabMaterial;

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
        private System.Windows.Forms.TextBox txtCor;
        private System.Windows.Forms.Label lblTipo;
        private System.Windows.Forms.TextBox txtTipo;
        private System.Windows.Forms.Label lblPadrao;
        private System.Windows.Forms.TextBox txtPadrao;
        private System.Windows.Forms.Label lblFornecedor;
        private System.Windows.Forms.TextBox txtFornecedor;

        private System.Windows.Forms.Button btnAdicionarTecido;
        private System.Windows.Forms.Button btnAtualizarTecido;
        private System.Windows.Forms.Panel panelDireitaTecidos;
        private System.Windows.Forms.Panel panelPesquisaTecidos;
        private System.Windows.Forms.Button btnPesquisarTecido;
        private System.Windows.Forms.TextBox txtPesquisaTecido;
        private System.Windows.Forms.Label lblPesquisarTecido;
    }
}