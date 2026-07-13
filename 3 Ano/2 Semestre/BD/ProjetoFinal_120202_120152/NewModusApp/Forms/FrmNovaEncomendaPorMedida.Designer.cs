using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    partial class FrmNovaEncomendaPorMedida
    {
        private System.ComponentModel.IContainer components = null;
        private Panel panelRoot;
        private Panel panelTopo;
        private Panel panelPassoCliente;
        private Panel panelPassoProdutos;
        private Panel panelForm;
        private Panel panelCarrinho;
        private Panel panelAcoes;
        private Button btnSetaVoltar;
        private Label lblTitulo;
        private Label lblPasso;
        private Label lblCliente;
        private Label lblEntrega;
        private Label lblInfo;
        private Label lblPerfil;
        private Label lblModelo;
        private Label lblTipoPeca;
        private Label lblTamanho;
        private Label lblPreco;
        private Label lblCustoMaoObra;
        private Label lblDescricao;
        private Label lblCarrinho;
        private ComboBox cmbCliente;
        private DateTimePicker dtpEntrega;
        private Button btnContinuar;
        private Button btnVoltarCliente;
        private ComboBox cmbPerfilMedida;
        private ComboBox cmbModelo;
        private TextBox txtTipoPeca;
        private NumericUpDown nudTamanho;
        private NumericUpDown nudPreco;
        private NumericUpDown nudCustoMaoObra;
        private TextBox txtDescricao;
        private Button btnAdicionarItem;
        private Button btnLimpar;
        private Button btnPerfis;
        private DataGridView dgvItens;
        private Label lblTotal;
        private Button btnVoltarProdutos;
        private Button btnRemoverItem;
        private Button btnFinalizar;

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
            this.panelPassoProdutos = new System.Windows.Forms.Panel();
            this.panelCarrinho = new System.Windows.Forms.Panel();
            this.dgvItens = new System.Windows.Forms.DataGridView();
            this.panelAcoes = new System.Windows.Forms.Panel();
            this.btnFinalizar = new System.Windows.Forms.Button();
            this.btnRemoverItem = new System.Windows.Forms.Button();
            this.btnVoltarProdutos = new System.Windows.Forms.Button();
            this.lblTotal = new System.Windows.Forms.Label();
            this.lblCarrinho = new System.Windows.Forms.Label();
            this.panelForm = new System.Windows.Forms.Panel();
            this.btnPerfis = new System.Windows.Forms.Button();
            this.btnLimpar = new System.Windows.Forms.Button();
            this.btnAdicionarItem = new System.Windows.Forms.Button();
            this.txtDescricao = new System.Windows.Forms.TextBox();
            this.lblDescricao = new System.Windows.Forms.Label();
            this.nudPreco = new System.Windows.Forms.NumericUpDown();
            this.nudCustoMaoObra = new System.Windows.Forms.NumericUpDown();
            this.lblPreco = new System.Windows.Forms.Label();
            this.lblCustoMaoObra = new System.Windows.Forms.Label();
            this.nudTamanho = new System.Windows.Forms.NumericUpDown();
            this.lblTamanho = new System.Windows.Forms.Label();
            this.txtTipoPeca = new System.Windows.Forms.TextBox();
            this.lblTipoPeca = new System.Windows.Forms.Label();
            this.cmbModelo = new System.Windows.Forms.ComboBox();
            this.lblModelo = new System.Windows.Forms.Label();
            this.cmbPerfilMedida = new System.Windows.Forms.ComboBox();
            this.lblPerfil = new System.Windows.Forms.Label();
            this.panelPassoCliente = new System.Windows.Forms.Panel();
            this.lblInfo = new System.Windows.Forms.Label();
            this.btnVoltarCliente = new System.Windows.Forms.Button();
            this.btnContinuar = new System.Windows.Forms.Button();
            this.dtpEntrega = new System.Windows.Forms.DateTimePicker();
            this.lblEntrega = new System.Windows.Forms.Label();
            this.cmbCliente = new System.Windows.Forms.ComboBox();
            this.lblCliente = new System.Windows.Forms.Label();
            this.panelTopo = new System.Windows.Forms.Panel();
            this.lblPasso = new System.Windows.Forms.Label();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.btnSetaVoltar = new System.Windows.Forms.Button();
            this.panelRoot.SuspendLayout();
            this.panelPassoProdutos.SuspendLayout();
            this.panelCarrinho.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvItens)).BeginInit();
            this.panelAcoes.SuspendLayout();
            this.panelForm.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudPreco)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudCustoMaoObra)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudTamanho)).BeginInit();
            this.panelPassoCliente.SuspendLayout();
            this.panelTopo.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelRoot
            // 
            this.panelRoot.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelRoot.Controls.Add(this.panelPassoProdutos);
            this.panelRoot.Controls.Add(this.panelPassoCliente);
            this.panelRoot.Controls.Add(this.panelTopo);
            this.panelRoot.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelRoot.Location = new System.Drawing.Point(0, 0);
            this.panelRoot.Name = "panelRoot";
            this.panelRoot.Padding = new System.Windows.Forms.Padding(20);
            this.panelRoot.Size = new System.Drawing.Size(1000, 650);
            this.panelRoot.TabIndex = 0;
            // 
            // panelPassoProdutos
            // 
            this.panelPassoProdutos.BackColor = System.Drawing.Color.White;
            this.panelPassoProdutos.Controls.Add(this.panelCarrinho);
            this.panelPassoProdutos.Controls.Add(this.panelForm);
            this.panelPassoProdutos.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelPassoProdutos.Location = new System.Drawing.Point(20, 110);
            this.panelPassoProdutos.Name = "panelPassoProdutos";
            this.panelPassoProdutos.Size = new System.Drawing.Size(960, 520);
            this.panelPassoProdutos.TabIndex = 2;
            this.panelPassoProdutos.Visible = false;
            // 
            // panelCarrinho
            // 
            this.panelCarrinho.BackColor = System.Drawing.Color.White;
            this.panelCarrinho.Controls.Add(this.dgvItens);
            this.panelCarrinho.Controls.Add(this.panelAcoes);
            this.panelCarrinho.Controls.Add(this.lblCarrinho);
            this.panelCarrinho.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelCarrinho.Location = new System.Drawing.Point(360, 0);
            this.panelCarrinho.Name = "panelCarrinho";
            this.panelCarrinho.Padding = new System.Windows.Forms.Padding(20);
            this.panelCarrinho.Size = new System.Drawing.Size(600, 520);
            this.panelCarrinho.TabIndex = 1;
            // 
            // dgvItens
            // 
            this.dgvItens.AllowUserToAddRows = false;
            this.dgvItens.AllowUserToDeleteRows = false;
            this.dgvItens.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvItens.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvItens.Location = new System.Drawing.Point(20, 55);
            this.dgvItens.MultiSelect = false;
            this.dgvItens.Name = "dgvItens";
            this.dgvItens.ReadOnly = true;
            this.dgvItens.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvItens.Size = new System.Drawing.Size(560, 373);
            this.dgvItens.TabIndex = 2;
            this.dgvItens.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvItens_DataBindingComplete);
            // 
            // panelAcoes
            // 
            this.panelAcoes.BackColor = System.Drawing.Color.White;
            this.panelAcoes.Controls.Add(this.btnFinalizar);
            this.panelAcoes.Controls.Add(this.btnRemoverItem);
            this.panelAcoes.Controls.Add(this.btnVoltarProdutos);
            this.panelAcoes.Controls.Add(this.lblTotal);
            this.panelAcoes.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panelAcoes.Location = new System.Drawing.Point(20, 428);
            this.panelAcoes.Name = "panelAcoes";
            this.panelAcoes.Size = new System.Drawing.Size(560, 72);
            this.panelAcoes.TabIndex = 1;
            // 
            // btnFinalizar
            // 
            this.btnFinalizar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnFinalizar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnFinalizar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnFinalizar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(25)))), ((int)(((byte)(25)))), ((int)(((byte)(25)))));
            this.btnFinalizar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(65)))), ((int)(((byte)(65)))), ((int)(((byte)(65)))));
            this.btnFinalizar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnFinalizar.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnFinalizar.ForeColor = System.Drawing.Color.White;
            this.btnFinalizar.Location = new System.Drawing.Point(465, 18);
            this.btnFinalizar.Name = "btnFinalizar";
            this.btnFinalizar.Size = new System.Drawing.Size(160, 36);
            this.btnFinalizar.TabIndex = 3;
            this.btnFinalizar.Text = "Finalizar pedido";
            this.btnFinalizar.UseVisualStyleBackColor = false;
            this.btnFinalizar.Click += new System.EventHandler(this.BtnFinalizar_Click);
            // 
            // btnRemoverItem
            // 
            this.btnRemoverItem.BackColor = System.Drawing.Color.White;
            this.btnRemoverItem.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnRemoverItem.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(205)))), ((int)(((byte)(205)))), ((int)(((byte)(205)))));
            this.btnRemoverItem.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(230)))), ((int)(((byte)(230)))));
            this.btnRemoverItem.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnRemoverItem.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnRemoverItem.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnRemoverItem.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnRemoverItem.Location = new System.Drawing.Point(350, 18);
            this.btnRemoverItem.Name = "btnRemoverItem";
            this.btnRemoverItem.Size = new System.Drawing.Size(105, 36);
            this.btnRemoverItem.TabIndex = 2;
            this.btnRemoverItem.Text = "Remover";
            this.btnRemoverItem.UseVisualStyleBackColor = false;
            this.btnRemoverItem.Click += new System.EventHandler(this.BtnRemoverItem_Click);
            // 
            // btnVoltarProdutos
            // 
            this.btnVoltarProdutos.BackColor = System.Drawing.Color.White;
            this.btnVoltarProdutos.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVoltarProdutos.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(205)))), ((int)(((byte)(205)))), ((int)(((byte)(205)))));
            this.btnVoltarProdutos.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(230)))), ((int)(((byte)(230)))));
            this.btnVoltarProdutos.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnVoltarProdutos.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVoltarProdutos.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnVoltarProdutos.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnVoltarProdutos.Location = new System.Drawing.Point(245, 18);
            this.btnVoltarProdutos.Name = "btnVoltarProdutos";
            this.btnVoltarProdutos.Size = new System.Drawing.Size(95, 36);
            this.btnVoltarProdutos.TabIndex = 1;
            this.btnVoltarProdutos.Text = "Voltar";
            this.btnVoltarProdutos.UseVisualStyleBackColor = false;
            this.btnVoltarProdutos.Click += new System.EventHandler(this.BtnVoltarProdutos_Click);
            // 
            // lblTotal
            // 
            this.lblTotal.Font = new System.Drawing.Font("Segoe UI", 13F, System.Drawing.FontStyle.Bold);
            this.lblTotal.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.lblTotal.Location = new System.Drawing.Point(0, 19);
            this.lblTotal.Name = "lblTotal";
            this.lblTotal.Size = new System.Drawing.Size(230, 32);
            this.lblTotal.TabIndex = 0;
            this.lblTotal.Text = "Total: 0,00";
            // 
            // lblCarrinho
            // 
            this.lblCarrinho.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblCarrinho.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold);
            this.lblCarrinho.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.lblCarrinho.Location = new System.Drawing.Point(20, 20);
            this.lblCarrinho.Name = "lblCarrinho";
            this.lblCarrinho.Size = new System.Drawing.Size(560, 35);
            this.lblCarrinho.TabIndex = 0;
            this.lblCarrinho.Text = "Produtos do pedido";
            // 
            // panelForm
            // 
            this.panelForm.AutoScroll = true;
            this.panelForm.BackColor = System.Drawing.Color.White;
            this.panelForm.Controls.Add(this.btnPerfis);
            this.panelForm.Controls.Add(this.btnLimpar);
            this.panelForm.Controls.Add(this.btnAdicionarItem);
            this.panelForm.Controls.Add(this.txtDescricao);
            this.panelForm.Controls.Add(this.lblDescricao);
            this.panelForm.Controls.Add(this.nudCustoMaoObra);
            this.panelForm.Controls.Add(this.lblCustoMaoObra);
            this.panelForm.Controls.Add(this.nudPreco);
            this.panelForm.Controls.Add(this.lblPreco);
            this.panelForm.Controls.Add(this.nudTamanho);
            this.panelForm.Controls.Add(this.lblTamanho);
            this.panelForm.Controls.Add(this.txtTipoPeca);
            this.panelForm.Controls.Add(this.lblTipoPeca);
            this.panelForm.Controls.Add(this.cmbModelo);
            this.panelForm.Controls.Add(this.lblModelo);
            this.panelForm.Controls.Add(this.cmbPerfilMedida);
            this.panelForm.Controls.Add(this.lblPerfil);
            this.panelForm.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelForm.Location = new System.Drawing.Point(0, 0);
            this.panelForm.Name = "panelForm";
            this.panelForm.Padding = new System.Windows.Forms.Padding(24);
            this.panelForm.Size = new System.Drawing.Size(360, 520);
            this.panelForm.TabIndex = 0;
            // 
            // btnPerfis
            // 
            this.btnPerfis.BackColor = System.Drawing.Color.White;
            this.btnPerfis.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnPerfis.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(205)))), ((int)(((byte)(205)))), ((int)(((byte)(205)))));
            this.btnPerfis.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(230)))), ((int)(((byte)(230)))));
            this.btnPerfis.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnPerfis.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnPerfis.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnPerfis.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnPerfis.Location = new System.Drawing.Point(24, 531);
            this.btnPerfis.Name = "btnPerfis";
            this.btnPerfis.Size = new System.Drawing.Size(140, 36);
            this.btnPerfis.TabIndex = 16;
            this.btnPerfis.Text = "Abrir Perfis";
            this.btnPerfis.UseVisualStyleBackColor = false;
            this.btnPerfis.Click += new System.EventHandler(this.BtnPerfis_Click);
            // 
            // btnLimpar
            // 
            this.btnLimpar.BackColor = System.Drawing.Color.White;
            this.btnLimpar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnLimpar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(205)))), ((int)(((byte)(205)))), ((int)(((byte)(205)))));
            this.btnLimpar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(230)))), ((int)(((byte)(230)))));
            this.btnLimpar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnLimpar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnLimpar.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnLimpar.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnLimpar.Location = new System.Drawing.Point(224, 481);
            this.btnLimpar.Name = "btnLimpar";
            this.btnLimpar.Size = new System.Drawing.Size(100, 36);
            this.btnLimpar.TabIndex = 15;
            this.btnLimpar.Text = "Limpar";
            this.btnLimpar.UseVisualStyleBackColor = false;
            this.btnLimpar.Click += new System.EventHandler(this.BtnLimpar_Click);
            // 
            // btnAdicionarItem
            // 
            this.btnAdicionarItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnAdicionarItem.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnAdicionarItem.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnAdicionarItem.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(25)))), ((int)(((byte)(25)))), ((int)(((byte)(25)))));
            this.btnAdicionarItem.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(65)))), ((int)(((byte)(65)))), ((int)(((byte)(65)))));
            this.btnAdicionarItem.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAdicionarItem.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnAdicionarItem.ForeColor = System.Drawing.Color.White;
            this.btnAdicionarItem.Location = new System.Drawing.Point(24, 481);
            this.btnAdicionarItem.Name = "btnAdicionarItem";
            this.btnAdicionarItem.Size = new System.Drawing.Size(190, 36);
            this.btnAdicionarItem.TabIndex = 14;
            this.btnAdicionarItem.Text = "Adicionar produto";
            this.btnAdicionarItem.UseVisualStyleBackColor = false;
            this.btnAdicionarItem.Click += new System.EventHandler(this.BtnAdicionarItem_Click);
            // 
            // txtDescricao
            // 
            this.txtDescricao.Location = new System.Drawing.Point(24, 426);
            this.txtDescricao.MaxLength = 50;
            this.txtDescricao.Name = "txtDescricao";
            this.txtDescricao.Size = new System.Drawing.Size(300, 25);
            this.txtDescricao.TabIndex = 13;
            // 
            // lblDescricao
            // 
            this.lblDescricao.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblDescricao.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblDescricao.Location = new System.Drawing.Point(24, 395);
            this.lblDescricao.Name = "lblDescricao";
            this.lblDescricao.Size = new System.Drawing.Size(180, 24);
            this.lblDescricao.TabIndex = 12;
            this.lblDescricao.Text = "Personalizacao";
            // 
            // nudPreco
            // 
            this.nudPreco.DecimalPlaces = 2;
            this.nudPreco.Location = new System.Drawing.Point(174, 268);
            this.nudPreco.Maximum = new decimal(new int[] {
            9999,
            0,
            0,
            0});
            this.nudPreco.Name = "nudPreco";
            this.nudPreco.Size = new System.Drawing.Size(150, 25);
            this.nudPreco.TabIndex = 9;
            this.nudPreco.ThousandsSeparator = true;
            // 
            // lblPreco
            // 
            this.lblPreco.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblPreco.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblPreco.Location = new System.Drawing.Point(174, 237);
            this.lblPreco.Name = "lblPreco";
            this.lblPreco.Size = new System.Drawing.Size(130, 24);
            this.lblPreco.TabIndex = 8;
            this.lblPreco.Text = "Preco de Venda";
            // 
            // nudCustoMaoObra
            // 
            this.nudCustoMaoObra.DecimalPlaces = 2;
            this.nudCustoMaoObra.Location = new System.Drawing.Point(174, 338);
            this.nudCustoMaoObra.Maximum = new decimal(new int[] {
            9999,
            0,
            0,
            0});
            this.nudCustoMaoObra.Name = "nudCustoMaoObra";
            this.nudCustoMaoObra.Size = new System.Drawing.Size(150, 25);
            this.nudCustoMaoObra.TabIndex = 17;
            this.nudCustoMaoObra.ThousandsSeparator = true;
            // 
            // lblCustoMaoObra
            // 
            this.lblCustoMaoObra.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblCustoMaoObra.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblCustoMaoObra.Location = new System.Drawing.Point(174, 307);
            this.lblCustoMaoObra.Name = "lblCustoMaoObra";
            this.lblCustoMaoObra.Size = new System.Drawing.Size(150, 24);
            this.lblCustoMaoObra.TabIndex = 18;
            this.lblCustoMaoObra.Text = "Mao de Obra";
            // 
            // nudTamanho
            // 
            this.nudTamanho.Location = new System.Drawing.Point(24, 268);
            this.nudTamanho.Maximum = new decimal(new int[] {
            999,
            0,
            0,
            0});
            this.nudTamanho.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.nudTamanho.Name = "nudTamanho";
            this.nudTamanho.Size = new System.Drawing.Size(120, 25);
            this.nudTamanho.TabIndex = 7;
            this.nudTamanho.ThousandsSeparator = true;
            this.nudTamanho.Value = new decimal(new int[] {
            40,
            0,
            0,
            0});
            // 
            // lblTamanho
            // 
            this.lblTamanho.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblTamanho.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblTamanho.Location = new System.Drawing.Point(24, 237);
            this.lblTamanho.Name = "lblTamanho";
            this.lblTamanho.Size = new System.Drawing.Size(130, 24);
            this.lblTamanho.TabIndex = 6;
            this.lblTamanho.Text = "Tamanho";
            // 
            // txtTipoPeca
            // 
            this.txtTipoPeca.Location = new System.Drawing.Point(24, 197);
            this.txtTipoPeca.MaxLength = 15;
            this.txtTipoPeca.Name = "txtTipoPeca";
            this.txtTipoPeca.Size = new System.Drawing.Size(300, 25);
            this.txtTipoPeca.TabIndex = 5;
            // 
            // lblTipoPeca
            // 
            this.lblTipoPeca.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblTipoPeca.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblTipoPeca.Location = new System.Drawing.Point(24, 166);
            this.lblTipoPeca.Name = "lblTipoPeca";
            this.lblTipoPeca.Size = new System.Drawing.Size(180, 24);
            this.lblTipoPeca.TabIndex = 4;
            this.lblTipoPeca.Text = "Tipo de peca";
            // 
            // cmbModelo
            // 
            this.cmbModelo.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbModelo.FormattingEnabled = true;
            this.cmbModelo.Location = new System.Drawing.Point(24, 126);
            this.cmbModelo.Name = "cmbModelo";
            this.cmbModelo.Size = new System.Drawing.Size(300, 25);
            this.cmbModelo.TabIndex = 3;
            this.cmbModelo.SelectedIndexChanged += new System.EventHandler(this.CmbModelo_SelectedIndexChanged);
            // 
            // lblModelo
            // 
            this.lblModelo.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblModelo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblModelo.Location = new System.Drawing.Point(24, 95);
            this.lblModelo.Name = "lblModelo";
            this.lblModelo.Size = new System.Drawing.Size(180, 24);
            this.lblModelo.TabIndex = 2;
            this.lblModelo.Text = "Modelo";
            // 
            // cmbPerfilMedida
            // 
            this.cmbPerfilMedida.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbPerfilMedida.FormattingEnabled = true;
            this.cmbPerfilMedida.Location = new System.Drawing.Point(24, 55);
            this.cmbPerfilMedida.Name = "cmbPerfilMedida";
            this.cmbPerfilMedida.Size = new System.Drawing.Size(300, 25);
            this.cmbPerfilMedida.TabIndex = 1;
            // 
            // lblPerfil
            // 
            this.lblPerfil.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblPerfil.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblPerfil.Location = new System.Drawing.Point(24, 24);
            this.lblPerfil.Name = "lblPerfil";
            this.lblPerfil.Size = new System.Drawing.Size(180, 24);
            this.lblPerfil.TabIndex = 0;
            this.lblPerfil.Text = "Perfil de medida";
            // 
            // panelPassoCliente
            // 
            this.panelPassoCliente.BackColor = System.Drawing.Color.White;
            this.panelPassoCliente.Controls.Add(this.lblInfo);
            this.panelPassoCliente.Controls.Add(this.btnVoltarCliente);
            this.panelPassoCliente.Controls.Add(this.btnContinuar);
            this.panelPassoCliente.Controls.Add(this.dtpEntrega);
            this.panelPassoCliente.Controls.Add(this.lblEntrega);
            this.panelPassoCliente.Controls.Add(this.cmbCliente);
            this.panelPassoCliente.Controls.Add(this.lblCliente);
            this.panelPassoCliente.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelPassoCliente.Location = new System.Drawing.Point(20, 110);
            this.panelPassoCliente.Name = "panelPassoCliente";
            this.panelPassoCliente.Padding = new System.Windows.Forms.Padding(30);
            this.panelPassoCliente.Size = new System.Drawing.Size(960, 520);
            this.panelPassoCliente.TabIndex = 1;
            // 
            // lblInfo
            // 
            this.lblInfo.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblInfo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(90)))), ((int)(((byte)(90)))), ((int)(((byte)(90)))));
            this.lblInfo.Location = new System.Drawing.Point(30, 285);
            this.lblInfo.Name = "lblInfo";
            this.lblInfo.Size = new System.Drawing.Size(610, 70);
            this.lblInfo.TabIndex = 6;
            this.lblInfo.Text = "Escreve o nome e escolhe um cliente existente da lista. Depois podes adicionar to" +
    "das as pecas do pedido antes de gravar.";
            // 
            // btnVoltarCliente
            // 
            this.btnVoltarCliente.BackColor = System.Drawing.Color.White;
            this.btnVoltarCliente.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVoltarCliente.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(205)))), ((int)(((byte)(205)))), ((int)(((byte)(205)))));
            this.btnVoltarCliente.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(230)))), ((int)(((byte)(230)))));
            this.btnVoltarCliente.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnVoltarCliente.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVoltarCliente.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnVoltarCliente.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnVoltarCliente.Location = new System.Drawing.Point(205, 220);
            this.btnVoltarCliente.Name = "btnVoltarCliente";
            this.btnVoltarCliente.Size = new System.Drawing.Size(120, 36);
            this.btnVoltarCliente.TabIndex = 5;
            this.btnVoltarCliente.Text = "Voltar";
            this.btnVoltarCliente.UseVisualStyleBackColor = false;
            this.btnVoltarCliente.Click += new System.EventHandler(this.BtnVoltar_Click);
            // 
            // btnContinuar
            // 
            this.btnContinuar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnContinuar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnContinuar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnContinuar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(25)))), ((int)(((byte)(25)))), ((int)(((byte)(25)))));
            this.btnContinuar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(65)))), ((int)(((byte)(65)))), ((int)(((byte)(65)))));
            this.btnContinuar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnContinuar.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnContinuar.ForeColor = System.Drawing.Color.White;
            this.btnContinuar.Location = new System.Drawing.Point(30, 220);
            this.btnContinuar.Name = "btnContinuar";
            this.btnContinuar.Size = new System.Drawing.Size(160, 36);
            this.btnContinuar.TabIndex = 4;
            this.btnContinuar.Text = "Continuar";
            this.btnContinuar.UseVisualStyleBackColor = false;
            this.btnContinuar.Click += new System.EventHandler(this.BtnContinuar_Click);
            // 
            // dtpEntrega
            // 
            this.dtpEntrega.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpEntrega.Location = new System.Drawing.Point(30, 155);
            this.dtpEntrega.Name = "dtpEntrega";
            this.dtpEntrega.Size = new System.Drawing.Size(180, 25);
            this.dtpEntrega.TabIndex = 3;
            this.dtpEntrega.Value = new System.DateTime(2026, 5, 31, 0, 0, 0, 0);
            // 
            // lblEntrega
            // 
            this.lblEntrega.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblEntrega.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblEntrega.Location = new System.Drawing.Point(30, 120);
            this.lblEntrega.Name = "lblEntrega";
            this.lblEntrega.Size = new System.Drawing.Size(180, 24);
            this.lblEntrega.TabIndex = 2;
            this.lblEntrega.Text = "Data de entrega";
            // 
            // cmbCliente
            // 
            this.cmbCliente.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.SuggestAppend;
            this.cmbCliente.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.ListItems;
            this.cmbCliente.FormattingEnabled = true;
            this.cmbCliente.Location = new System.Drawing.Point(30, 70);
            this.cmbCliente.Name = "cmbCliente";
            this.cmbCliente.Size = new System.Drawing.Size(430, 25);
            this.cmbCliente.TabIndex = 1;
            // 
            // lblCliente
            // 
            this.lblCliente.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblCliente.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblCliente.Location = new System.Drawing.Point(30, 35);
            this.lblCliente.Name = "lblCliente";
            this.lblCliente.Size = new System.Drawing.Size(180, 24);
            this.lblCliente.TabIndex = 0;
            this.lblCliente.Text = "Nome do cliente";
            // 
            // panelTopo
            // 
            this.panelTopo.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelTopo.Controls.Add(this.lblPasso);
            this.panelTopo.Controls.Add(this.lblTitulo);
            this.panelTopo.Controls.Add(this.btnSetaVoltar);
            this.panelTopo.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelTopo.Location = new System.Drawing.Point(20, 20);
            this.panelTopo.Name = "panelTopo";
            this.panelTopo.Size = new System.Drawing.Size(960, 90);
            this.panelTopo.TabIndex = 0;
            // 
            // lblPasso
            // 
            this.lblPasso.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblPasso.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(90)))), ((int)(((byte)(90)))), ((int)(((byte)(90)))));
            this.lblPasso.Location = new System.Drawing.Point(56, 44);
            this.lblPasso.Name = "lblPasso";
            this.lblPasso.Size = new System.Drawing.Size(640, 32);
            this.lblPasso.TabIndex = 2;
            // 
            // lblTitulo
            // 
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.lblTitulo.Location = new System.Drawing.Point(54, 0);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(640, 42);
            this.lblTitulo.TabIndex = 1;
            this.lblTitulo.Text = "Novo Pedido";
            // 
            // btnSetaVoltar
            // 
            this.btnSetaVoltar.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btnSetaVoltar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnSetaVoltar.FlatAppearance.BorderSize = 0;
            this.btnSetaVoltar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(225)))), ((int)(((byte)(225)))), ((int)(((byte)(225)))));
            this.btnSetaVoltar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(235)))), ((int)(((byte)(235)))), ((int)(((byte)(235)))));
            this.btnSetaVoltar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSetaVoltar.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.btnSetaVoltar.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnSetaVoltar.Location = new System.Drawing.Point(0, 8);
            this.btnSetaVoltar.Name = "btnSetaVoltar";
            this.btnSetaVoltar.Size = new System.Drawing.Size(48, 42);
            this.btnSetaVoltar.TabIndex = 0;
            this.btnSetaVoltar.Text = "<";
            this.btnSetaVoltar.UseVisualStyleBackColor = false;
            this.btnSetaVoltar.Click += new System.EventHandler(this.BtnSetaVoltar_Click);
            // 
            // FrmNovaEncomendaPorMedida
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1000, 650);
            this.Controls.Add(this.panelRoot);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.Name = "FrmNovaEncomendaPorMedida";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Novo Pedido por Medida";
            this.Load += new System.EventHandler(this.FrmNovaEncomendaPorMedida_Load);
            this.panelRoot.ResumeLayout(false);
            this.panelPassoProdutos.ResumeLayout(false);
            this.panelCarrinho.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvItens)).EndInit();
            this.panelAcoes.ResumeLayout(false);
            this.panelForm.ResumeLayout(false);
            this.panelForm.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudPreco)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudCustoMaoObra)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudTamanho)).EndInit();
            this.panelPassoCliente.ResumeLayout(false);
            this.panelTopo.ResumeLayout(false);
            this.ResumeLayout(false);

        }
    }
}
