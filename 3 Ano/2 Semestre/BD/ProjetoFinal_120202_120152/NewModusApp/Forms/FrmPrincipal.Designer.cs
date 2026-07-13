namespace NewModusApp.Forms
{
    partial class FrmPrincipal
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Panel panelMain;
        private System.Windows.Forms.Panel panelMenu;
        private System.Windows.Forms.Panel panelTopo;
        private System.Windows.Forms.Panel panelConteudo;
        private System.Windows.Forms.PictureBox picLogo;
        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lblMensagem;
        private System.Windows.Forms.Button btnDashboard;
        private System.Windows.Forms.Button btnClientes;
        private System.Windows.Forms.Button btnMedidas;
        private System.Windows.Forms.Button btnEncomendas;
        private System.Windows.Forms.Button btnLojaStock;
        private System.Windows.Forms.Button btnAtualizar;
        private System.Windows.Forms.Button btnSair;

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
            this.panelMain = new System.Windows.Forms.Panel();
            this.panelMenu = new System.Windows.Forms.Panel();
            this.picLogo = new System.Windows.Forms.PictureBox();
            this.btnDashboard = new System.Windows.Forms.Button();
            this.btnClientes = new System.Windows.Forms.Button();
            this.btnMedidas = new System.Windows.Forms.Button();
            this.btnEncomendas = new System.Windows.Forms.Button();
            this.btnLojaStock = new System.Windows.Forms.Button();
            this.btnAtualizar = new System.Windows.Forms.Button();
            this.btnSair = new System.Windows.Forms.Button();
            this.panelConteudo = new System.Windows.Forms.Panel();
            this.lblMensagem = new System.Windows.Forms.Label();
            this.panelTopo = new System.Windows.Forms.Panel();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.panelMain.SuspendLayout();
            this.panelMenu.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picLogo)).BeginInit();
            this.panelConteudo.SuspendLayout();
            this.panelTopo.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelMain
            // 
            this.panelMain.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelMain.Location = new System.Drawing.Point(0, 0);
            this.panelMain.Name = "panelMain";
            this.panelMain.Size = new System.Drawing.Size(1000, 650);
            this.panelMain.TabIndex = 0;
            // 
            // panelMenu
            // 
            this.panelMenu.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.panelMenu.Controls.Add(this.btnSair);
            this.panelMenu.Controls.Add(this.btnAtualizar);
            this.panelMenu.Controls.Add(this.btnLojaStock);
            this.panelMenu.Controls.Add(this.btnEncomendas);
            this.panelMenu.Controls.Add(this.btnMedidas);
            this.panelMenu.Controls.Add(this.btnClientes);
            this.panelMenu.Controls.Add(this.btnDashboard);
            this.panelMenu.Controls.Add(this.picLogo);
            this.panelMenu.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelMenu.Location = new System.Drawing.Point(0, 0);
            this.panelMenu.Name = "panelMenu";
            this.panelMenu.Size = new System.Drawing.Size(220, 650);
            this.panelMenu.TabIndex = 0;
            // 
            // picLogo
            // 
            this.picLogo.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.picLogo.Image = global::NewModusApp.Properties.Resources.logo;
            this.picLogo.Location = new System.Drawing.Point(25, 20);
            this.picLogo.Name = "picLogo";
            this.picLogo.Size = new System.Drawing.Size(170, 90);
            this.picLogo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picLogo.TabIndex = 0;
            this.picLogo.TabStop = false;
            // 
            // btnDashboard
            // 
            this.btnDashboard.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnDashboard.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnDashboard.FlatAppearance.BorderSize = 0;
            this.btnDashboard.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(70, 70, 70);
            this.btnDashboard.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(55, 55, 55);
            this.btnDashboard.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnDashboard.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btnDashboard.ForeColor = System.Drawing.Color.White;
            this.btnDashboard.Location = new System.Drawing.Point(0, 140);
            this.btnDashboard.Name = "btnDashboard";
            this.btnDashboard.Padding = new System.Windows.Forms.Padding(25, 0, 0, 0);
            this.btnDashboard.Size = new System.Drawing.Size(220, 50);
            this.btnDashboard.TabIndex = 7;
            this.btnDashboard.Text = "Dashboard";
            this.btnDashboard.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnDashboard.UseVisualStyleBackColor = false;
            this.btnDashboard.Click += new System.EventHandler(this.BtnDashboard_Click);
            // 
            // btnClientes
            // 
            this.btnClientes.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnClientes.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnClientes.FlatAppearance.BorderSize = 0;
            this.btnClientes.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(70, 70, 70);
            this.btnClientes.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(55, 55, 55);
            this.btnClientes.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnClientes.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btnClientes.ForeColor = System.Drawing.Color.White;
            this.btnClientes.Location = new System.Drawing.Point(0, 195);
            this.btnClientes.Name = "btnClientes";
            this.btnClientes.Padding = new System.Windows.Forms.Padding(25, 0, 0, 0);
            this.btnClientes.Size = new System.Drawing.Size(220, 50);
            this.btnClientes.TabIndex = 1;
            this.btnClientes.Text = "Clientes";
            this.btnClientes.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnClientes.UseVisualStyleBackColor = false;
            this.btnClientes.Click += new System.EventHandler(this.BtnClientes_Click);
            // 
            // btnMedidas
            // 
            this.btnMedidas.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnMedidas.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnMedidas.FlatAppearance.BorderSize = 0;
            this.btnMedidas.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(70, 70, 70);
            this.btnMedidas.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(55, 55, 55);
            this.btnMedidas.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnMedidas.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btnMedidas.ForeColor = System.Drawing.Color.White;
            this.btnMedidas.Location = new System.Drawing.Point(0, 250);
            this.btnMedidas.Name = "btnMedidas";
            this.btnMedidas.Padding = new System.Windows.Forms.Padding(25, 0, 0, 0);
            this.btnMedidas.Size = new System.Drawing.Size(220, 50);
            this.btnMedidas.TabIndex = 2;
            this.btnMedidas.Text = "Medidas";
            this.btnMedidas.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnMedidas.UseVisualStyleBackColor = false;
            this.btnMedidas.Click += new System.EventHandler(this.BtnMedidas_Click);
            // 
            // btnEncomendas
            // 
            this.btnEncomendas.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnEncomendas.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnEncomendas.FlatAppearance.BorderSize = 0;
            this.btnEncomendas.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(70, 70, 70);
            this.btnEncomendas.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(55, 55, 55);
            this.btnEncomendas.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnEncomendas.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btnEncomendas.ForeColor = System.Drawing.Color.White;
            this.btnEncomendas.Location = new System.Drawing.Point(0, 305);
            this.btnEncomendas.Name = "btnEncomendas";
            this.btnEncomendas.Padding = new System.Windows.Forms.Padding(25, 0, 0, 0);
            this.btnEncomendas.Size = new System.Drawing.Size(220, 50);
            this.btnEncomendas.TabIndex = 3;
            this.btnEncomendas.Text = "Encomendas";
            this.btnEncomendas.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnEncomendas.UseVisualStyleBackColor = false;
            this.btnEncomendas.Click += new System.EventHandler(this.BtnEncomendas_Click);
            // 
            // btnLojaStock
            // 
            this.btnLojaStock.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnLojaStock.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnLojaStock.FlatAppearance.BorderSize = 0;
            this.btnLojaStock.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(70, 70, 70);
            this.btnLojaStock.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(55, 55, 55);
            this.btnLojaStock.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnLojaStock.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btnLojaStock.ForeColor = System.Drawing.Color.White;
            this.btnLojaStock.Location = new System.Drawing.Point(0, 360);
            this.btnLojaStock.Name = "btnLojaStock";
            this.btnLojaStock.Padding = new System.Windows.Forms.Padding(25, 0, 0, 0);
            this.btnLojaStock.Size = new System.Drawing.Size(220, 50);
            this.btnLojaStock.TabIndex = 4;
            this.btnLojaStock.Text = "Inventário";
            this.btnLojaStock.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnLojaStock.UseVisualStyleBackColor = false;
            this.btnLojaStock.Click += new System.EventHandler(this.BtnLojaStock_Click);
            // 
            // btnAtualizar
            // 
            this.btnAtualizar.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnAtualizar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnAtualizar.FlatAppearance.BorderSize = 0;
            this.btnAtualizar.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(70, 70, 70);
            this.btnAtualizar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(55, 55, 55);
            this.btnAtualizar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAtualizar.Font = new System.Drawing.Font("Segoe UI", 14F);
            this.btnAtualizar.ForeColor = System.Drawing.Color.White;
            this.btnAtualizar.Location = new System.Drawing.Point(0, 540);
            this.btnAtualizar.Name = "btnAtualizar";
            this.btnAtualizar.Padding = new System.Windows.Forms.Padding(25, 0, 0, 0);
            this.btnAtualizar.Size = new System.Drawing.Size(220, 50);
            this.btnAtualizar.TabIndex = 5;
            this.btnAtualizar.Text = "↻  Atualizar";
            this.btnAtualizar.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnAtualizar.UseVisualStyleBackColor = false;
            this.btnAtualizar.Click += new System.EventHandler(this.BtnAtualizar_Click);
            // 
            // btnSair
            // 
            this.btnSair.BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnSair.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnSair.FlatAppearance.BorderSize = 0;
            this.btnSair.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(70, 70, 70);
            this.btnSair.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(55, 55, 55);
            this.btnSair.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSair.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btnSair.ForeColor = System.Drawing.Color.White;
            this.btnSair.Location = new System.Drawing.Point(0, 595);
            this.btnSair.Name = "btnSair";
            this.btnSair.Padding = new System.Windows.Forms.Padding(25, 0, 0, 0);
            this.btnSair.Size = new System.Drawing.Size(220, 50);
            this.btnSair.TabIndex = 6;
            this.btnSair.Text = "Sair";
            this.btnSair.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnSair.UseVisualStyleBackColor = false;
            this.btnSair.Click += new System.EventHandler(this.BtnSair_Click);
            // 
            // panelConteudo
            // 
            this.panelConteudo.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelConteudo.Controls.Add(this.lblMensagem);
            this.panelConteudo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelConteudo.Location = new System.Drawing.Point(220, 70);
            this.panelConteudo.Name = "panelConteudo";
            this.panelConteudo.Padding = new System.Windows.Forms.Padding(20);
            this.panelConteudo.Size = new System.Drawing.Size(780, 580);
            this.panelConteudo.TabIndex = 1;
            // 
            // lblMensagem
            // 
            this.lblMensagem.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lblMensagem.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblMensagem.ForeColor = System.Drawing.Color.FromArgb(80, 80, 80);
            this.lblMensagem.Location = new System.Drawing.Point(20, 20);
            this.lblMensagem.Name = "lblMensagem";
            this.lblMensagem.Size = new System.Drawing.Size(740, 540);
            this.lblMensagem.TabIndex = 0;
            this.lblMensagem.Text = "Ligação à base de dados estabelecida com sucesso.";
            this.lblMensagem.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelTopo
            // 
            this.panelTopo.BackColor = System.Drawing.Color.White;
            this.panelTopo.Controls.Add(this.lblTitulo);
            this.panelTopo.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelTopo.Location = new System.Drawing.Point(220, 0);
            this.panelTopo.Name = "panelTopo";
            this.panelTopo.Size = new System.Drawing.Size(780, 70);
            this.panelTopo.TabIndex = 2;
            // 
            // lblTitulo
            // 
            this.lblTitulo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(40, 40, 40);
            this.lblTitulo.Location = new System.Drawing.Point(0, 0);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Padding = new System.Windows.Forms.Padding(25, 0, 0, 0);
            this.lblTitulo.Size = new System.Drawing.Size(780, 70);
            this.lblTitulo.TabIndex = 0;
            this.lblTitulo.Text = "Painel Principal";
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // FrmPrincipal
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1000, 650);
            this.Controls.Add(this.panelConteudo);
            this.Controls.Add(this.panelTopo);
            this.Controls.Add(this.panelMenu);
            this.Controls.Add(this.panelMain);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.MinimumSize = new System.Drawing.Size(1000, 650);
            this.Name = "FrmPrincipal";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "NewModus - Gestão";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.panelMain.ResumeLayout(false);
            this.panelMenu.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picLogo)).EndInit();
            this.panelConteudo.ResumeLayout(false);
            this.panelTopo.ResumeLayout(false);
            this.ResumeLayout(false);

        }
    }
}
