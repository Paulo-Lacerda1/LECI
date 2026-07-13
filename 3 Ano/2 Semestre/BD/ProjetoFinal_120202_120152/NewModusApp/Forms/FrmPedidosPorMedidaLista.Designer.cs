using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    partial class FrmPedidosPorMedidaLista
    {
        private System.ComponentModel.IContainer components = null;
        private Panel panelRoot;
        private Panel panelTopo;
        private Panel panelAcoes;
        private Panel panelConteudo;
        private Label lblTitulo;
        private Label lblItens;
        private Button btnVoltar;
        private Button btnEmProducao;
        private Button btnConcluida;
        private Button btnEntregue;
        private Button btnCancelar;
        private Button btnAdicionarMateriais;
        private Button btnVerMateriais;
        private DataGridView dgvPedidos;
        private DataGridView dgvItens;

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
            this.panelConteudo = new System.Windows.Forms.Panel();
            this.dgvPedidos = new System.Windows.Forms.DataGridView();
            this.lblItens = new System.Windows.Forms.Label();
            this.dgvItens = new System.Windows.Forms.DataGridView();
            this.panelAcoes = new System.Windows.Forms.Panel();
            this.btnVerMateriais = new System.Windows.Forms.Button();
            this.btnAdicionarMateriais = new System.Windows.Forms.Button();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.btnEntregue = new System.Windows.Forms.Button();
            this.btnConcluida = new System.Windows.Forms.Button();
            this.btnEmProducao = new System.Windows.Forms.Button();
            this.panelTopo = new System.Windows.Forms.Panel();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.btnVoltar = new System.Windows.Forms.Button();
            this.panelRoot.SuspendLayout();
            this.panelConteudo.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvPedidos)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvItens)).BeginInit();
            this.panelAcoes.SuspendLayout();
            this.panelTopo.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelRoot
            // 
            this.panelRoot.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelRoot.Controls.Add(this.panelConteudo);
            this.panelRoot.Controls.Add(this.panelAcoes);
            this.panelRoot.Controls.Add(this.panelTopo);
            this.panelRoot.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelRoot.Location = new System.Drawing.Point(0, 0);
            this.panelRoot.Name = "panelRoot";
            this.panelRoot.Padding = new System.Windows.Forms.Padding(20);
            this.panelRoot.Size = new System.Drawing.Size(1000, 650);
            this.panelRoot.TabIndex = 0;
            // 
            // panelConteudo
            // 
            this.panelConteudo.BackColor = System.Drawing.Color.White;
            this.panelConteudo.Controls.Add(this.dgvPedidos);
            this.panelConteudo.Controls.Add(this.lblItens);
            this.panelConteudo.Controls.Add(this.dgvItens);
            this.panelConteudo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelConteudo.Location = new System.Drawing.Point(20, 148);
            this.panelConteudo.Name = "panelConteudo";
            this.panelConteudo.Padding = new System.Windows.Forms.Padding(20);
            this.panelConteudo.Size = new System.Drawing.Size(960, 482);
            this.panelConteudo.TabIndex = 2;
            // 
            // dgvPedidos
            // 
            this.dgvPedidos.AllowUserToAddRows = false;
            this.dgvPedidos.AllowUserToDeleteRows = false;
            this.dgvPedidos.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvPedidos.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvPedidos.Location = new System.Drawing.Point(20, 20);
            this.dgvPedidos.MultiSelect = false;
            this.dgvPedidos.Name = "dgvPedidos";
            this.dgvPedidos.ReadOnly = true;
            this.dgvPedidos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvPedidos.Size = new System.Drawing.Size(920, 220);
            this.dgvPedidos.TabIndex = 0;
            this.dgvPedidos.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvPedidos_CellClick);
            this.dgvPedidos.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvPedidos_DataBindingComplete);
            // 
            // lblItens
            // 
            this.lblItens.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.lblItens.Font = new System.Drawing.Font("Segoe UI", 11F, System.Drawing.FontStyle.Bold);
            this.lblItens.Location = new System.Drawing.Point(20, 240);
            this.lblItens.Name = "lblItens";
            this.lblItens.Size = new System.Drawing.Size(920, 32);
            this.lblItens.TabIndex = 1;
            this.lblItens.Text = "Produtos do pedido selecionado";
            // 
            // dgvItens
            // 
            this.dgvItens.AllowUserToAddRows = false;
            this.dgvItens.AllowUserToDeleteRows = false;
            this.dgvItens.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvItens.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.dgvItens.Location = new System.Drawing.Point(20, 272);
            this.dgvItens.MultiSelect = false;
            this.dgvItens.Name = "dgvItens";
            this.dgvItens.ReadOnly = true;
            this.dgvItens.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvItens.Size = new System.Drawing.Size(920, 190);
            this.dgvItens.TabIndex = 2;
            this.dgvItens.CellDoubleClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvItens_CellDoubleClick);
            this.dgvItens.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvItens_DataBindingComplete);
            // 
            // panelAcoes
            // 
            this.panelAcoes.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelAcoes.Controls.Add(this.btnVerMateriais);
            this.panelAcoes.Controls.Add(this.btnAdicionarMateriais);
            this.panelAcoes.Controls.Add(this.btnCancelar);
            this.panelAcoes.Controls.Add(this.btnEntregue);
            this.panelAcoes.Controls.Add(this.btnConcluida);
            this.panelAcoes.Controls.Add(this.btnEmProducao);
            this.panelAcoes.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelAcoes.Location = new System.Drawing.Point(20, 78);
            this.panelAcoes.Name = "panelAcoes";
            this.panelAcoes.Size = new System.Drawing.Size(960, 70);
            this.panelAcoes.TabIndex = 1;
            // 
            // btnVerMateriais
            // 
            this.btnVerMateriais.BackColor = System.Drawing.Color.White;
            this.btnVerMateriais.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVerMateriais.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(205)))), ((int)(((byte)(205)))), ((int)(((byte)(205)))));
            this.btnVerMateriais.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(230)))), ((int)(((byte)(230)))));
            this.btnVerMateriais.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnVerMateriais.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVerMateriais.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnVerMateriais.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnVerMateriais.Location = new System.Drawing.Point(787, 14);
            this.btnVerMateriais.Name = "btnVerMateriais";
            this.btnVerMateriais.Size = new System.Drawing.Size(130, 36);
            this.btnVerMateriais.TabIndex = 5;
            this.btnVerMateriais.Text = "Ver materiais";
            this.btnVerMateriais.UseVisualStyleBackColor = false;
            this.btnVerMateriais.Click += new System.EventHandler(this.BtnVerMateriais_Click);
            // 
            // btnAdicionarMateriais
            // 
            this.btnAdicionarMateriais.BackColor = System.Drawing.Color.White;
            this.btnAdicionarMateriais.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnAdicionarMateriais.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(205)))), ((int)(((byte)(205)))), ((int)(((byte)(205)))));
            this.btnAdicionarMateriais.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(230)))), ((int)(((byte)(230)))));
            this.btnAdicionarMateriais.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnAdicionarMateriais.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAdicionarMateriais.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnAdicionarMateriais.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnAdicionarMateriais.Location = new System.Drawing.Point(624, 14);
            this.btnAdicionarMateriais.Name = "btnAdicionarMateriais";
            this.btnAdicionarMateriais.Size = new System.Drawing.Size(155, 36);
            this.btnAdicionarMateriais.TabIndex = 4;
            this.btnAdicionarMateriais.Text = "Adicionar materiais";
            this.btnAdicionarMateriais.UseVisualStyleBackColor = false;
            this.btnAdicionarMateriais.Click += new System.EventHandler(this.BtnAdicionarMateriais_Click);
            // 
            // btnCancelar
            // 
            this.btnCancelar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(180)))), ((int)(((byte)(30)))), ((int)(((byte)(30)))));
            this.btnCancelar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnCancelar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(140)))), ((int)(((byte)(20)))), ((int)(((byte)(20)))));
            this.btnCancelar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(120)))), ((int)(((byte)(10)))), ((int)(((byte)(10)))));
            this.btnCancelar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(200)))), ((int)(((byte)(50)))), ((int)(((byte)(50)))));
            this.btnCancelar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnCancelar.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnCancelar.ForeColor = System.Drawing.Color.White;
            this.btnCancelar.Location = new System.Drawing.Point(474, 14);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(130, 36);
            this.btnCancelar.TabIndex = 3;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.UseVisualStyleBackColor = false;
            this.btnCancelar.Click += new System.EventHandler(this.BtnCancelar_Click);
            // 
            // btnEntregue
            // 
            this.btnEntregue.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnEntregue.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnEntregue.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnEntregue.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(25)))), ((int)(((byte)(25)))), ((int)(((byte)(25)))));
            this.btnEntregue.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(65)))), ((int)(((byte)(65)))), ((int)(((byte)(65)))));
            this.btnEntregue.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnEntregue.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnEntregue.ForeColor = System.Drawing.Color.White;
            this.btnEntregue.Location = new System.Drawing.Point(326, 14);
            this.btnEntregue.Name = "btnEntregue";
            this.btnEntregue.Size = new System.Drawing.Size(140, 36);
            this.btnEntregue.TabIndex = 2;
            this.btnEntregue.Text = "Marcar Entregue";
            this.btnEntregue.UseVisualStyleBackColor = false;
            this.btnEntregue.Click += new System.EventHandler(this.BtnEntregue_Click);
            // 
            // btnConcluida
            // 
            this.btnConcluida.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnConcluida.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnConcluida.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnConcluida.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(25)))), ((int)(((byte)(25)))), ((int)(((byte)(25)))));
            this.btnConcluida.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(65)))), ((int)(((byte)(65)))), ((int)(((byte)(65)))));
            this.btnConcluida.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnConcluida.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnConcluida.ForeColor = System.Drawing.Color.White;
            this.btnConcluida.Location = new System.Drawing.Point(163, 14);
            this.btnConcluida.Name = "btnConcluida";
            this.btnConcluida.Size = new System.Drawing.Size(155, 36);
            this.btnConcluida.TabIndex = 1;
            this.btnConcluida.Text = "Marcar Concluida";
            this.btnConcluida.UseVisualStyleBackColor = false;
            this.btnConcluida.Click += new System.EventHandler(this.BtnConcluida_Click);
            // 
            // btnEmProducao
            // 
            this.btnEmProducao.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(210)))), ((int)(((byte)(105)))), ((int)(((byte)(30)))));
            this.btnEmProducao.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnEmProducao.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(180)))), ((int)(((byte)(80)))), ((int)(((byte)(10)))));
            this.btnEmProducao.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(160)))), ((int)(((byte)(70)))), ((int)(((byte)(0)))));
            this.btnEmProducao.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(120)))), ((int)(((byte)(40)))));
            this.btnEmProducao.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnEmProducao.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnEmProducao.ForeColor = System.Drawing.Color.White;
            this.btnEmProducao.Location = new System.Drawing.Point(0, 14);
            this.btnEmProducao.Name = "btnEmProducao";
            this.btnEmProducao.Size = new System.Drawing.Size(155, 36);
            this.btnEmProducao.TabIndex = 0;
            this.btnEmProducao.Text = "Iniciar Producao";
            this.btnEmProducao.UseVisualStyleBackColor = false;
            this.btnEmProducao.Click += new System.EventHandler(this.BtnEmProducao_Click);
            // 
            // panelTopo
            // 
            this.panelTopo.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelTopo.Controls.Add(this.lblTitulo);
            this.panelTopo.Controls.Add(this.btnVoltar);
            this.panelTopo.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelTopo.Location = new System.Drawing.Point(20, 20);
            this.panelTopo.Name = "panelTopo";
            this.panelTopo.Size = new System.Drawing.Size(960, 58);
            this.panelTopo.TabIndex = 0;
            // 
            // lblTitulo
            // 
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.lblTitulo.Location = new System.Drawing.Point(54, 0);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(650, 42);
            this.lblTitulo.TabIndex = 1;
            this.lblTitulo.Text = "Pedidos pendentes";
            // 
            // btnVoltar
            // 
            this.btnVoltar.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btnVoltar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVoltar.FlatAppearance.BorderSize = 0;
            this.btnVoltar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(235)))), ((int)(((byte)(235)))), ((int)(((byte)(235)))));
            this.btnVoltar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVoltar.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.btnVoltar.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnVoltar.Location = new System.Drawing.Point(0, 0);
            this.btnVoltar.Name = "btnVoltar";
            this.btnVoltar.Size = new System.Drawing.Size(48, 42);
            this.btnVoltar.TabIndex = 0;
            this.btnVoltar.Text = "<";
            this.btnVoltar.UseVisualStyleBackColor = false;
            this.btnVoltar.Click += new System.EventHandler(this.BtnVoltar_Click);
            // 
            // FrmPedidosPorMedidaLista
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1000, 650);
            this.Controls.Add(this.panelRoot);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.Name = "FrmPedidosPorMedidaLista";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Pedidos pendentes";
            this.Load += new System.EventHandler(this.FrmPedidosPorMedidaLista_Load);
            this.panelRoot.ResumeLayout(false);
            this.panelConteudo.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvPedidos)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvItens)).EndInit();
            this.panelAcoes.ResumeLayout(false);
            this.panelTopo.ResumeLayout(false);
            this.ResumeLayout(false);

        }
    }
}
