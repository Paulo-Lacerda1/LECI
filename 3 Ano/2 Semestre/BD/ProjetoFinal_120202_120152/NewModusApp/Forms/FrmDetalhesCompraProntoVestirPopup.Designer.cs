namespace NewModusApp.Forms
{
    partial class FrmDetalhesCompraProntoVestirPopup
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Panel panelRoot;
        private System.Windows.Forms.Panel panelTopo;
        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Button btnVoltarTopo;
        private System.Windows.Forms.Panel panelForm;
        private System.Windows.Forms.Label lblDetalheId;
        private System.Windows.Forms.TextBox txtDetalheId;
        private System.Windows.Forms.Label lblProduto;
        private System.Windows.Forms.ComboBox cmbProduto;
        private System.Windows.Forms.Label lblProdutoNome;
        private System.Windows.Forms.TextBox txtProdutoNome;
        private System.Windows.Forms.Label lblTamanho;
        private System.Windows.Forms.TextBox txtTamanho;
        private System.Windows.Forms.Label lblCor;
        private System.Windows.Forms.TextBox txtCor;
        private System.Windows.Forms.Label lblQuantidade;
        private System.Windows.Forms.TextBox txtQuantidade;
        private System.Windows.Forms.Label lblPreco;
        private System.Windows.Forms.TextBox txtPrecoUnitario;
        private System.Windows.Forms.Button btnAdicionar;
        private System.Windows.Forms.Button btnEditar;
        private System.Windows.Forms.Button btnApagar;
        private System.Windows.Forms.Panel panelAcoes;
        private System.Windows.Forms.Button btnConfirmar;
        private System.Windows.Forms.DataGridView dgvDetalhes;

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
            this.panelRoot = new System.Windows.Forms.Panel();
            this.panelTopo = new System.Windows.Forms.Panel();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.btnVoltarTopo = new System.Windows.Forms.Button();
            this.panelForm = new System.Windows.Forms.Panel();
            this.lblDetalheId = new System.Windows.Forms.Label();
            this.txtDetalheId = new System.Windows.Forms.TextBox();
            this.lblProduto = new System.Windows.Forms.Label();
            this.cmbProduto = new System.Windows.Forms.ComboBox();
            this.lblProdutoNome = new System.Windows.Forms.Label();
            this.txtProdutoNome = new System.Windows.Forms.TextBox();
            this.lblTamanho = new System.Windows.Forms.Label();
            this.txtTamanho = new System.Windows.Forms.TextBox();
            this.lblCor = new System.Windows.Forms.Label();
            this.txtCor = new System.Windows.Forms.TextBox();
            this.lblQuantidade = new System.Windows.Forms.Label();
            this.txtQuantidade = new System.Windows.Forms.TextBox();
            this.lblPreco = new System.Windows.Forms.Label();
            this.txtPrecoUnitario = new System.Windows.Forms.TextBox();
            this.btnAdicionar = new System.Windows.Forms.Button();
            this.btnEditar = new System.Windows.Forms.Button();
            this.btnApagar = new System.Windows.Forms.Button();
            this.panelAcoes = new System.Windows.Forms.Panel();
            this.btnConfirmar = new System.Windows.Forms.Button();
            this.dgvDetalhes = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.dgvDetalhes)).BeginInit();
            this.panelRoot.SuspendLayout();
            this.panelTopo.SuspendLayout();
            this.panelForm.SuspendLayout();
            this.panelAcoes.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelRoot
            // 
            this.panelRoot.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelRoot.Controls.Add(this.dgvDetalhes);
            this.panelRoot.Controls.Add(this.panelAcoes);
            this.panelRoot.Controls.Add(this.panelForm);
            this.panelRoot.Controls.Add(this.panelTopo);
            this.panelRoot.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelRoot.Location = new System.Drawing.Point(0, 0);
            this.panelRoot.Name = "panelRoot";
            this.panelRoot.Padding = new System.Windows.Forms.Padding(20);
            this.panelRoot.Size = new System.Drawing.Size(1180, 560);
            this.panelRoot.TabIndex = 0;
            // 
            // panelTopo
            // 
            this.panelTopo.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelTopo.Controls.Add(this.lblTitulo);
            this.panelTopo.Controls.Add(this.btnVoltarTopo);
            this.panelTopo.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelTopo.Location = new System.Drawing.Point(20, 20);
            this.panelTopo.Name = "panelTopo";
            this.panelTopo.Size = new System.Drawing.Size(1140, 52);
            this.panelTopo.TabIndex = 0;
            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = false;
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.lblTitulo.Location = new System.Drawing.Point(54, 3);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(500, 42);
            this.lblTitulo.TabIndex = 1;
            this.lblTitulo.Text = "Produtos da venda";
            // 
            // btnVoltarTopo
            // 
            this.btnVoltarTopo.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btnVoltarTopo.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVoltarTopo.FlatAppearance.BorderSize = 0;
            this.btnVoltarTopo.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(235, 235, 235);
            this.btnVoltarTopo.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVoltarTopo.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.btnVoltarTopo.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnVoltarTopo.Location = new System.Drawing.Point(0, 5);
            this.btnVoltarTopo.Name = "btnVoltarTopo";
            this.btnVoltarTopo.Size = new System.Drawing.Size(48, 42);
            this.btnVoltarTopo.TabIndex = 0;
            this.btnVoltarTopo.Text = "<";
            this.btnVoltarTopo.UseVisualStyleBackColor = false;
            this.btnVoltarTopo.Click += new System.EventHandler(this.BtnConfirmar_Click);
            // 
            // panelForm
            // 
            this.panelForm.BackColor = System.Drawing.Color.White;
            this.panelForm.Controls.Add(this.btnApagar);
            this.panelForm.Controls.Add(this.btnEditar);
            this.panelForm.Controls.Add(this.btnAdicionar);
            this.panelForm.Controls.Add(this.txtPrecoUnitario);
            this.panelForm.Controls.Add(this.lblPreco);
            this.panelForm.Controls.Add(this.txtQuantidade);
            this.panelForm.Controls.Add(this.lblQuantidade);
            this.panelForm.Controls.Add(this.txtCor);
            this.panelForm.Controls.Add(this.lblCor);
            this.panelForm.Controls.Add(this.txtTamanho);
            this.panelForm.Controls.Add(this.lblTamanho);
            this.panelForm.Controls.Add(this.txtProdutoNome);
            this.panelForm.Controls.Add(this.lblProdutoNome);
            this.panelForm.Controls.Add(this.cmbProduto);
            this.panelForm.Controls.Add(this.lblProduto);
            this.panelForm.Controls.Add(this.txtDetalheId);
            this.panelForm.Controls.Add(this.lblDetalheId);
            this.panelForm.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelForm.Location = new System.Drawing.Point(20, 72);
            this.panelForm.Name = "panelForm";
            this.panelForm.Padding = new System.Windows.Forms.Padding(16);
            this.panelForm.Size = new System.Drawing.Size(1140, 96);
            this.panelForm.TabIndex = 1;
            // 
            // lblDetalheId
            // 
            this.lblDetalheId.AutoSize = false;
            this.lblDetalheId.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblDetalheId.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblDetalheId.Location = new System.Drawing.Point(16, 16);
            this.lblDetalheId.Name = "lblDetalheId";
            this.lblDetalheId.Size = new System.Drawing.Size(50, 22);
            this.lblDetalheId.TabIndex = 0;
            this.lblDetalheId.Text = "ID";
            // 
            // txtDetalheId
            // 
            this.txtDetalheId.Location = new System.Drawing.Point(16, 42);
            this.txtDetalheId.Name = "txtDetalheId";
            this.txtDetalheId.ReadOnly = true;
            this.txtDetalheId.Size = new System.Drawing.Size(50, 30);
            this.txtDetalheId.TabIndex = 1;
            // 
            // lblProduto
            // 
            this.lblProduto.AutoSize = false;
            this.lblProduto.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblProduto.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblProduto.Location = new System.Drawing.Point(76, 16);
            this.lblProduto.Name = "lblProduto";
            this.lblProduto.Size = new System.Drawing.Size(190, 22);
            this.lblProduto.TabIndex = 2;
            this.lblProduto.Text = "Produto";
            // 
            // cmbProduto
            // 
            this.cmbProduto.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbProduto.FormattingEnabled = true;
            this.cmbProduto.Location = new System.Drawing.Point(76, 42);
            this.cmbProduto.Name = "cmbProduto";
            this.cmbProduto.Size = new System.Drawing.Size(190, 31);
            this.cmbProduto.TabIndex = 3;
            this.cmbProduto.SelectedIndexChanged += new System.EventHandler(this.CmbProduto_SelectedIndexChanged);
            // 
            // lblProdutoNome
            // 
            this.lblProdutoNome.AutoSize = false;
            this.lblProdutoNome.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblProdutoNome.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblProdutoNome.Location = new System.Drawing.Point(276, 16);
            this.lblProdutoNome.Name = "lblProdutoNome";
            this.lblProdutoNome.Size = new System.Drawing.Size(150, 22);
            this.lblProdutoNome.TabIndex = 4;
            this.lblProdutoNome.Text = "Nome";
            // 
            // txtProdutoNome
            // 
            this.txtProdutoNome.Location = new System.Drawing.Point(276, 42);
            this.txtProdutoNome.Name = "txtProdutoNome";
            this.txtProdutoNome.Size = new System.Drawing.Size(150, 30);
            this.txtProdutoNome.TabIndex = 5;
            // 
            // lblTamanho
            // 
            this.lblTamanho.AutoSize = false;
            this.lblTamanho.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblTamanho.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblTamanho.Location = new System.Drawing.Point(436, 16);
            this.lblTamanho.Name = "lblTamanho";
            this.lblTamanho.Size = new System.Drawing.Size(80, 22);
            this.lblTamanho.TabIndex = 6;
            this.lblTamanho.Text = "Tamanho";
            // 
            // txtTamanho
            // 
            this.txtTamanho.Location = new System.Drawing.Point(436, 42);
            this.txtTamanho.Name = "txtTamanho";
            this.txtTamanho.Size = new System.Drawing.Size(80, 30);
            this.txtTamanho.TabIndex = 7;
            // 
            // lblCor
            // 
            this.lblCor.AutoSize = false;
            this.lblCor.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblCor.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblCor.Location = new System.Drawing.Point(526, 16);
            this.lblCor.Name = "lblCor";
            this.lblCor.Size = new System.Drawing.Size(90, 22);
            this.lblCor.TabIndex = 8;
            this.lblCor.Text = "Cor";
            // 
            // txtCor
            // 
            this.txtCor.Location = new System.Drawing.Point(526, 42);
            this.txtCor.Name = "txtCor";
            this.txtCor.Size = new System.Drawing.Size(90, 30);
            this.txtCor.TabIndex = 9;
            // 
            // lblQuantidade
            // 
            this.lblQuantidade.AutoSize = false;
            this.lblQuantidade.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblQuantidade.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblQuantidade.Location = new System.Drawing.Point(626, 16);
            this.lblQuantidade.Name = "lblQuantidade";
            this.lblQuantidade.Size = new System.Drawing.Size(60, 22);
            this.lblQuantidade.TabIndex = 10;
            this.lblQuantidade.Text = "Qtd";
            // 
            // txtQuantidade
            // 
            this.txtQuantidade.Location = new System.Drawing.Point(626, 42);
            this.txtQuantidade.Name = "txtQuantidade";
            this.txtQuantidade.Size = new System.Drawing.Size(60, 30);
            this.txtQuantidade.TabIndex = 11;
            // 
            // lblPreco
            // 
            this.lblPreco.AutoSize = false;
            this.lblPreco.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblPreco.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblPreco.Location = new System.Drawing.Point(696, 16);
            this.lblPreco.Name = "lblPreco";
            this.lblPreco.Size = new System.Drawing.Size(95, 22);
            this.lblPreco.TabIndex = 12;
            this.lblPreco.Text = "Preco unit.";
            // 
            // txtPrecoUnitario
            // 
            this.txtPrecoUnitario.Location = new System.Drawing.Point(696, 42);
            this.txtPrecoUnitario.Name = "txtPrecoUnitario";
            this.txtPrecoUnitario.Size = new System.Drawing.Size(95, 30);
            this.txtPrecoUnitario.TabIndex = 13;
            // 
            // btnAdicionar
            // 
            this.btnAdicionar.BackColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnAdicionar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnAdicionar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnAdicionar.FlatAppearance.BorderSize = 1;
            this.btnAdicionar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(25, 25, 25);
            this.btnAdicionar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(65, 65, 65);
            this.btnAdicionar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAdicionar.ForeColor = System.Drawing.Color.White;
            this.btnAdicionar.Location = new System.Drawing.Point(806, 40);
            this.btnAdicionar.Name = "btnAdicionar";
            this.btnAdicionar.Size = new System.Drawing.Size(105, 34);
            this.btnAdicionar.TabIndex = 14;
            this.btnAdicionar.Text = "Adicionar";
            this.btnAdicionar.UseVisualStyleBackColor = false;
            this.btnAdicionar.Click += new System.EventHandler(this.BtnAdicionar_Click);
            // 
            // btnEditar
            // 
            this.btnEditar.BackColor = System.Drawing.Color.White;
            this.btnEditar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnEditar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(205, 205, 205);
            this.btnEditar.FlatAppearance.BorderSize = 1;
            this.btnEditar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(230, 230, 230);
            this.btnEditar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 245, 245);
            this.btnEditar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnEditar.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnEditar.Location = new System.Drawing.Point(921, 40);
            this.btnEditar.Name = "btnEditar";
            this.btnEditar.Size = new System.Drawing.Size(90, 34);
            this.btnEditar.TabIndex = 15;
            this.btnEditar.Text = "Editar";
            this.btnEditar.UseVisualStyleBackColor = false;
            this.btnEditar.Click += new System.EventHandler(this.BtnEditar_Click);
            // 
            // btnApagar
            // 
            this.btnApagar.BackColor = System.Drawing.Color.White;
            this.btnApagar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnApagar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(205, 205, 205);
            this.btnApagar.FlatAppearance.BorderSize = 1;
            this.btnApagar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(230, 230, 230);
            this.btnApagar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 245, 245);
            this.btnApagar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnApagar.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnApagar.Location = new System.Drawing.Point(1021, 40);
            this.btnApagar.Name = "btnApagar";
            this.btnApagar.Size = new System.Drawing.Size(90, 34);
            this.btnApagar.TabIndex = 16;
            this.btnApagar.Text = "Apagar";
            this.btnApagar.UseVisualStyleBackColor = false;
            this.btnApagar.Click += new System.EventHandler(this.BtnApagar_Click);
            // 
            // panelAcoes
            // 
            this.panelAcoes.BackColor = System.Drawing.Color.White;
            this.panelAcoes.Controls.Add(this.btnConfirmar);
            this.panelAcoes.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panelAcoes.Location = new System.Drawing.Point(20, 482);
            this.panelAcoes.Name = "panelAcoes";
            this.panelAcoes.Size = new System.Drawing.Size(1140, 58);
            this.panelAcoes.TabIndex = 2;
            // 
            // btnConfirmar
            // 
            this.btnConfirmar.BackColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnConfirmar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnConfirmar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnConfirmar.FlatAppearance.BorderSize = 1;
            this.btnConfirmar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(25, 25, 25);
            this.btnConfirmar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(65, 65, 65);
            this.btnConfirmar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnConfirmar.ForeColor = System.Drawing.Color.White;
            this.btnConfirmar.Location = new System.Drawing.Point(1000, 12);
            this.btnConfirmar.Name = "btnConfirmar";
            this.btnConfirmar.Size = new System.Drawing.Size(120, 34);
            this.btnConfirmar.TabIndex = 0;
            this.btnConfirmar.Text = "Voltar";
            this.btnConfirmar.UseVisualStyleBackColor = false;
            this.btnConfirmar.Click += new System.EventHandler(this.BtnConfirmar_Click);
            // 
            // dgvDetalhes
            // 
            this.dgvDetalhes.AllowUserToAddRows = false;
            this.dgvDetalhes.AllowUserToDeleteRows = false;
            this.dgvDetalhes.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvDetalhes.BackgroundColor = System.Drawing.Color.White;
            this.dgvDetalhes.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvDetalhes.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvDetalhes.Location = new System.Drawing.Point(20, 168);
            this.dgvDetalhes.MultiSelect = false;
            this.dgvDetalhes.Name = "dgvDetalhes";
            this.dgvDetalhes.ReadOnly = true;
            this.dgvDetalhes.RowHeadersVisible = false;
            this.dgvDetalhes.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvDetalhes.Size = new System.Drawing.Size(1140, 314);
            this.dgvDetalhes.TabIndex = 3;
            this.dgvDetalhes.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvDetalhes_CellClick);
            this.dgvDetalhes.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvDetalhes_DataBindingComplete);
            // 
            // FrmDetalhesCompraProntoVestirPopup
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1180, 560);
            this.Controls.Add(this.panelRoot);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.MinimumSize = new System.Drawing.Size(1040, 500);
            this.Name = "FrmDetalhesCompraProntoVestirPopup";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Produtos da venda";
            ((System.ComponentModel.ISupportInitialize)(this.dgvDetalhes)).EndInit();
            this.panelAcoes.ResumeLayout(false);
            this.panelForm.ResumeLayout(false);
            this.panelRoot.ResumeLayout(false);
            this.panelTopo.ResumeLayout(false);
            this.ResumeLayout(false);
        }
    }
}