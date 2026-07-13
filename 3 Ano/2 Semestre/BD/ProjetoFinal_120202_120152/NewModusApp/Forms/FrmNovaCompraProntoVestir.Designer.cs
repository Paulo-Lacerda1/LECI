namespace NewModusApp.Forms
{
    partial class FrmNovaCompraProntoVestir
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Panel panelRoot;
        private System.Windows.Forms.Panel panelTopo;
        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lblSubtitulo;
        private System.Windows.Forms.Button btnVoltar;
        private System.Windows.Forms.Panel panelForm;
        private System.Windows.Forms.Label lblCliente;
        private System.Windows.Forms.ComboBox cmbCliente;
        private System.Windows.Forms.Label lblPagamento;
        private System.Windows.Forms.ComboBox cmbMetodoPagamento;
        private System.Windows.Forms.Label lblTipoProduto;
        private System.Windows.Forms.ComboBox cmbTipoProduto;
        private System.Windows.Forms.Label lblTamanho;
        private System.Windows.Forms.ComboBox cmbTamanho;
        private System.Windows.Forms.Label lblQuantidade;
        private System.Windows.Forms.NumericUpDown nudQuantidade;
        private System.Windows.Forms.Label lblPreco;
        private System.Windows.Forms.NumericUpDown nudPreco;
        private System.Windows.Forms.Button btnAdicionar;
        private System.Windows.Forms.Button btnLimpar;
        private System.Windows.Forms.Panel panelCarrinho;
        private System.Windows.Forms.Label lblCarrinho;
        private System.Windows.Forms.Panel panelAcoes;
        private System.Windows.Forms.Label lblTotal;
        private System.Windows.Forms.Button btnRemover;
        private System.Windows.Forms.Button btnFinalizar;
        private System.Windows.Forms.DataGridView dgvItens;

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
            this.panelRoot = new System.Windows.Forms.Panel();
            this.panelTopo = new System.Windows.Forms.Panel();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.lblSubtitulo = new System.Windows.Forms.Label();
            this.btnVoltar = new System.Windows.Forms.Button();
            this.panelForm = new System.Windows.Forms.Panel();
            this.lblCliente = new System.Windows.Forms.Label();
            this.cmbCliente = new System.Windows.Forms.ComboBox();
            this.lblPagamento = new System.Windows.Forms.Label();
            this.cmbMetodoPagamento = new System.Windows.Forms.ComboBox();
            this.lblTipoProduto = new System.Windows.Forms.Label();
            this.cmbTipoProduto = new System.Windows.Forms.ComboBox();
            this.lblTamanho = new System.Windows.Forms.Label();
            this.cmbTamanho = new System.Windows.Forms.ComboBox();
            this.lblQuantidade = new System.Windows.Forms.Label();
            this.nudQuantidade = new System.Windows.Forms.NumericUpDown();
            this.lblPreco = new System.Windows.Forms.Label();
            this.nudPreco = new System.Windows.Forms.NumericUpDown();
            this.btnAdicionar = new System.Windows.Forms.Button();
            this.btnLimpar = new System.Windows.Forms.Button();
            this.panelCarrinho = new System.Windows.Forms.Panel();
            this.lblCarrinho = new System.Windows.Forms.Label();
            this.panelAcoes = new System.Windows.Forms.Panel();
            this.lblTotal = new System.Windows.Forms.Label();
            this.btnRemover = new System.Windows.Forms.Button();
            this.btnFinalizar = new System.Windows.Forms.Button();
            this.dgvItens = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.nudQuantidade)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudPreco)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvItens)).BeginInit();
            this.panelRoot.SuspendLayout();
            this.panelTopo.SuspendLayout();
            this.panelForm.SuspendLayout();
            this.panelCarrinho.SuspendLayout();
            this.panelAcoes.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelRoot
            // 
            this.panelRoot.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelRoot.Controls.Add(this.panelCarrinho);
            this.panelRoot.Controls.Add(this.panelForm);
            this.panelRoot.Controls.Add(this.panelTopo);
            this.panelRoot.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelRoot.Location = new System.Drawing.Point(0, 0);
            this.panelRoot.Name = "panelRoot";
            this.panelRoot.Padding = new System.Windows.Forms.Padding(20);
            this.panelRoot.Size = new System.Drawing.Size(1000, 650);
            this.panelRoot.TabIndex = 0;
            // 
            // panelTopo
            // 
            this.panelTopo.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelTopo.Controls.Add(this.lblSubtitulo);
            this.panelTopo.Controls.Add(this.lblTitulo);
            this.panelTopo.Controls.Add(this.btnVoltar);
            this.panelTopo.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelTopo.Location = new System.Drawing.Point(20, 20);
            this.panelTopo.Name = "panelTopo";
            this.panelTopo.Size = new System.Drawing.Size(960, 82);
            this.panelTopo.TabIndex = 0;
            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = false;
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.lblTitulo.Location = new System.Drawing.Point(54, 0);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(640, 42);
            this.lblTitulo.TabIndex = 1;
            this.lblTitulo.Text = "Nova compra";
            // 
            // lblSubtitulo
            // 
            this.lblSubtitulo.AutoSize = false;
            this.lblSubtitulo.ForeColor = System.Drawing.Color.FromArgb(90, 90, 90);
            this.lblSubtitulo.Location = new System.Drawing.Point(56, 44);
            this.lblSubtitulo.Name = "lblSubtitulo";
            this.lblSubtitulo.Size = new System.Drawing.Size(720, 32);
            this.lblSubtitulo.TabIndex = 2;
            this.lblSubtitulo.Text = "Escolhe os dados da venda e adiciona produtos antes de finalizar.";
            // 
            // btnVoltar
            // 
            this.btnVoltar.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btnVoltar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVoltar.FlatAppearance.BorderSize = 0;
            this.btnVoltar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(235, 235, 235);
            this.btnVoltar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVoltar.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.btnVoltar.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnVoltar.Location = new System.Drawing.Point(0, 0);
            this.btnVoltar.Name = "btnVoltar";
            this.btnVoltar.Size = new System.Drawing.Size(48, 42);
            this.btnVoltar.TabIndex = 0;
            this.btnVoltar.Text = "<";
            this.btnVoltar.UseVisualStyleBackColor = false;
            this.btnVoltar.Click += new System.EventHandler(this.BtnVoltar_Click);
            // 
            // panelForm
            // 
            this.panelForm.AutoScroll = true;
            this.panelForm.BackColor = System.Drawing.Color.White;
            this.panelForm.Controls.Add(this.btnLimpar);
            this.panelForm.Controls.Add(this.btnAdicionar);
            this.panelForm.Controls.Add(this.nudPreco);
            this.panelForm.Controls.Add(this.lblPreco);
            this.panelForm.Controls.Add(this.nudQuantidade);
            this.panelForm.Controls.Add(this.lblQuantidade);
            this.panelForm.Controls.Add(this.cmbTamanho);
            this.panelForm.Controls.Add(this.lblTamanho);
            this.panelForm.Controls.Add(this.cmbTipoProduto);
            this.panelForm.Controls.Add(this.lblTipoProduto);
            this.panelForm.Controls.Add(this.cmbMetodoPagamento);
            this.panelForm.Controls.Add(this.lblPagamento);
            this.panelForm.Controls.Add(this.cmbCliente);
            this.panelForm.Controls.Add(this.lblCliente);
            this.panelForm.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelForm.Location = new System.Drawing.Point(20, 102);
            this.panelForm.Name = "panelForm";
            this.panelForm.Padding = new System.Windows.Forms.Padding(24);
            this.panelForm.Size = new System.Drawing.Size(360, 528);
            this.panelForm.TabIndex = 1;
            // 
            // lblCliente
            // 
            this.lblCliente.AutoSize = false;
            this.lblCliente.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblCliente.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblCliente.Location = new System.Drawing.Point(24, 24);
            this.lblCliente.Name = "lblCliente";
            this.lblCliente.Size = new System.Drawing.Size(180, 24);
            this.lblCliente.TabIndex = 0;
            this.lblCliente.Text = "Cliente";
            // 
            // cmbCliente
            // 
            this.cmbCliente.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbCliente.FormattingEnabled = true;
            this.cmbCliente.Location = new System.Drawing.Point(24, 55);
            this.cmbCliente.Name = "cmbCliente";
            this.cmbCliente.Size = new System.Drawing.Size(300, 31);
            this.cmbCliente.TabIndex = 1;
            // 
            // lblPagamento
            // 
            this.lblPagamento.AutoSize = false;
            this.lblPagamento.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblPagamento.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblPagamento.Location = new System.Drawing.Point(24, 95);
            this.lblPagamento.Name = "lblPagamento";
            this.lblPagamento.Size = new System.Drawing.Size(220, 24);
            this.lblPagamento.TabIndex = 2;
            this.lblPagamento.Text = "Metodo de pagamento";
            // 
            // cmbMetodoPagamento
            // 
            this.cmbMetodoPagamento.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbMetodoPagamento.FormattingEnabled = true;
            this.cmbMetodoPagamento.Location = new System.Drawing.Point(24, 126);
            this.cmbMetodoPagamento.Name = "cmbMetodoPagamento";
            this.cmbMetodoPagamento.Size = new System.Drawing.Size(300, 31);
            this.cmbMetodoPagamento.TabIndex = 3;
            // 
            // lblTipoProduto
            // 
            this.lblTipoProduto.AutoSize = false;
            this.lblTipoProduto.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblTipoProduto.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblTipoProduto.Location = new System.Drawing.Point(24, 180);
            this.lblTipoProduto.Name = "lblTipoProduto";
            this.lblTipoProduto.Size = new System.Drawing.Size(180, 24);
            this.lblTipoProduto.TabIndex = 4;
            this.lblTipoProduto.Text = "Tipo de produto";
            // 
            // cmbTipoProduto
            // 
            this.cmbTipoProduto.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbTipoProduto.FormattingEnabled = true;
            this.cmbTipoProduto.Location = new System.Drawing.Point(24, 211);
            this.cmbTipoProduto.Name = "cmbTipoProduto";
            this.cmbTipoProduto.Size = new System.Drawing.Size(300, 31);
            this.cmbTipoProduto.TabIndex = 5;
            this.cmbTipoProduto.SelectedIndexChanged += new System.EventHandler(this.CmbTipoProduto_SelectedIndexChanged);
            // 
            // lblTamanho
            // 
            this.lblTamanho.AutoSize = false;
            this.lblTamanho.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblTamanho.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblTamanho.Location = new System.Drawing.Point(24, 251);
            this.lblTamanho.Name = "lblTamanho";
            this.lblTamanho.Size = new System.Drawing.Size(130, 24);
            this.lblTamanho.TabIndex = 6;
            this.lblTamanho.Text = "Tamanho";
            // 
            // cmbTamanho
            // 
            this.cmbTamanho.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbTamanho.FormattingEnabled = true;
            this.cmbTamanho.Location = new System.Drawing.Point(24, 282);
            this.cmbTamanho.Name = "cmbTamanho";
            this.cmbTamanho.Size = new System.Drawing.Size(120, 31);
            this.cmbTamanho.TabIndex = 7;
            this.cmbTamanho.SelectedIndexChanged += new System.EventHandler(this.CmbTamanho_SelectedIndexChanged);
            // 
            // lblQuantidade
            // 
            this.lblQuantidade.AutoSize = false;
            this.lblQuantidade.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblQuantidade.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblQuantidade.Location = new System.Drawing.Point(174, 251);
            this.lblQuantidade.Name = "lblQuantidade";
            this.lblQuantidade.Size = new System.Drawing.Size(130, 24);
            this.lblQuantidade.TabIndex = 8;
            this.lblQuantidade.Text = "Quantidade";
            // 
            // nudQuantidade
            // 
            this.nudQuantidade.Location = new System.Drawing.Point(174, 282);
            this.nudQuantidade.Maximum = new decimal(new int[] { 999, 0, 0, 0 });
            this.nudQuantidade.Minimum = new decimal(new int[] { 1, 0, 0, 0 });
            this.nudQuantidade.Name = "nudQuantidade";
            this.nudQuantidade.Size = new System.Drawing.Size(150, 30);
            this.nudQuantidade.TabIndex = 9;
            this.nudQuantidade.Value = new decimal(new int[] { 1, 0, 0, 0 });
            // 
            // lblPreco
            // 
            this.lblPreco.AutoSize = false;
            this.lblPreco.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblPreco.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblPreco.Location = new System.Drawing.Point(24, 322);
            this.lblPreco.Name = "lblPreco";
            this.lblPreco.Size = new System.Drawing.Size(150, 24);
            this.lblPreco.TabIndex = 10;
            this.lblPreco.Text = "Preco unitario";
            // 
            // nudPreco
            // 
            this.nudPreco.DecimalPlaces = 2;
            this.nudPreco.Location = new System.Drawing.Point(24, 353);
            this.nudPreco.Maximum = new decimal(new int[] { 9999, 0, 0, 0 });
            this.nudPreco.Name = "nudPreco";
            this.nudPreco.Size = new System.Drawing.Size(150, 30);
            this.nudPreco.TabIndex = 11;
            this.nudPreco.ThousandsSeparator = true;
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
            this.btnAdicionar.Location = new System.Drawing.Point(24, 411);
            this.btnAdicionar.Name = "btnAdicionar";
            this.btnAdicionar.Size = new System.Drawing.Size(190, 36);
            this.btnAdicionar.TabIndex = 12;
            this.btnAdicionar.Text = "Adicionar produto";
            this.btnAdicionar.UseVisualStyleBackColor = false;
            this.btnAdicionar.Click += new System.EventHandler(this.BtnAdicionar_Click);
            // 
            // btnLimpar
            // 
            this.btnLimpar.BackColor = System.Drawing.Color.White;
            this.btnLimpar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnLimpar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(205, 205, 205);
            this.btnLimpar.FlatAppearance.BorderSize = 1;
            this.btnLimpar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(230, 230, 230);
            this.btnLimpar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 245, 245);
            this.btnLimpar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnLimpar.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnLimpar.Location = new System.Drawing.Point(224, 411);
            this.btnLimpar.Name = "btnLimpar";
            this.btnLimpar.Size = new System.Drawing.Size(100, 36);
            this.btnLimpar.TabIndex = 13;
            this.btnLimpar.Text = "Limpar";
            this.btnLimpar.UseVisualStyleBackColor = false;
            this.btnLimpar.Click += new System.EventHandler(this.BtnLimpar_Click);
            // 
            // panelCarrinho
            // 
            this.panelCarrinho.BackColor = System.Drawing.Color.White;
            this.panelCarrinho.Controls.Add(this.dgvItens);
            this.panelCarrinho.Controls.Add(this.panelAcoes);
            this.panelCarrinho.Controls.Add(this.lblCarrinho);
            this.panelCarrinho.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelCarrinho.Location = new System.Drawing.Point(380, 102);
            this.panelCarrinho.Name = "panelCarrinho";
            this.panelCarrinho.Padding = new System.Windows.Forms.Padding(20);
            this.panelCarrinho.Size = new System.Drawing.Size(580, 528);
            this.panelCarrinho.TabIndex = 2;
            // 
            // lblCarrinho
            // 
            this.lblCarrinho.AutoSize = false;
            this.lblCarrinho.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblCarrinho.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold);
            this.lblCarrinho.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.lblCarrinho.Height = 35;
            this.lblCarrinho.Location = new System.Drawing.Point(20, 20);
            this.lblCarrinho.Name = "lblCarrinho";
            this.lblCarrinho.Size = new System.Drawing.Size(540, 35);
            this.lblCarrinho.TabIndex = 0;
            this.lblCarrinho.Text = "Produtos da compra";
            // 
            // panelAcoes
            // 
            this.panelAcoes.BackColor = System.Drawing.Color.White;
            this.panelAcoes.Controls.Add(this.btnFinalizar);
            this.panelAcoes.Controls.Add(this.btnRemover);
            this.panelAcoes.Controls.Add(this.lblTotal);
            this.panelAcoes.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panelAcoes.Location = new System.Drawing.Point(20, 436);
            this.panelAcoes.Name = "panelAcoes";
            this.panelAcoes.Size = new System.Drawing.Size(540, 72);
            this.panelAcoes.TabIndex = 1;
            // 
            // lblTotal
            // 
            this.lblTotal.AutoSize = false;
            this.lblTotal.Font = new System.Drawing.Font("Segoe UI", 13F, System.Drawing.FontStyle.Bold);
            this.lblTotal.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.lblTotal.Location = new System.Drawing.Point(0, 19);
            this.lblTotal.Name = "lblTotal";
            this.lblTotal.Size = new System.Drawing.Size(230, 32);
            this.lblTotal.TabIndex = 0;
            this.lblTotal.Text = "Total: 0,00";
            // 
            // btnRemover
            // 
            this.btnRemover.BackColor = System.Drawing.Color.White;
            this.btnRemover.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnRemover.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(205, 205, 205);
            this.btnRemover.FlatAppearance.BorderSize = 1;
            this.btnRemover.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(230, 230, 230);
            this.btnRemover.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 245, 245);
            this.btnRemover.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnRemover.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnRemover.Location = new System.Drawing.Point(350, 18);
            this.btnRemover.Name = "btnRemover";
            this.btnRemover.Size = new System.Drawing.Size(105, 36);
            this.btnRemover.TabIndex = 1;
            this.btnRemover.Text = "Remover";
            this.btnRemover.UseVisualStyleBackColor = false;
            this.btnRemover.Click += new System.EventHandler(this.BtnRemover_Click);
            // 
            // btnFinalizar
            // 
            this.btnFinalizar.BackColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnFinalizar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnFinalizar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnFinalizar.FlatAppearance.BorderSize = 1;
            this.btnFinalizar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(25, 25, 25);
            this.btnFinalizar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(65, 65, 65);
            this.btnFinalizar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnFinalizar.ForeColor = System.Drawing.Color.White;
            this.btnFinalizar.Location = new System.Drawing.Point(465, 18);
            this.btnFinalizar.Name = "btnFinalizar";
            this.btnFinalizar.Size = new System.Drawing.Size(160, 36);
            this.btnFinalizar.TabIndex = 2;
            this.btnFinalizar.Text = "Finalizar compra";
            this.btnFinalizar.UseVisualStyleBackColor = false;
            this.btnFinalizar.Click += new System.EventHandler(this.BtnFinalizar_Click);
            // 
            // dgvItens
            // 
            this.dgvItens.AllowUserToAddRows = false;
            this.dgvItens.AllowUserToDeleteRows = false;
            this.dgvItens.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvItens.DataSource = null;
            this.dgvItens.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvItens.Location = new System.Drawing.Point(20, 55);
            this.dgvItens.MultiSelect = false;
            this.dgvItens.Name = "dgvItens";
            this.dgvItens.ReadOnly = true;
            this.dgvItens.RowHeadersVisible = false;
            this.dgvItens.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvItens.Size = new System.Drawing.Size(540, 453);
            this.dgvItens.TabIndex = 2;
            this.dgvItens.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvItens_DataBindingComplete);
            // 
            // FrmNovaCompraProntoVestir
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1000, 650);
            this.Controls.Add(this.panelRoot);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.Name = "FrmNovaCompraProntoVestir";
            this.Text = "Nova Compra Pronto a Vestir";
            ((System.ComponentModel.ISupportInitialize)(this.nudQuantidade)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudPreco)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvItens)).EndInit();
            this.panelAcoes.ResumeLayout(false);
            this.panelCarrinho.ResumeLayout(false);
            this.panelForm.ResumeLayout(false);
            this.panelRoot.ResumeLayout(false);
            this.panelTopo.ResumeLayout(false);
            this.ResumeLayout(false);
        }
    }
}
