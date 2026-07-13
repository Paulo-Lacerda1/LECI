using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    partial class FrmEncomendasPorMedidaMenu
    {
        private System.ComponentModel.IContainer components = null;
        private Panel panelRoot;
        private Panel panelTopo;
        private Label lblTitulo;
        private Label lblSubtitulo;
        private Button btnVoltar;
        private Button btnNovoPedido;
        private Button btnVerPedidos;
        private Button btnHistorico;

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
            this.lblSubtitulo = new System.Windows.Forms.Label();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.btnVoltar = new System.Windows.Forms.Button();
            this.btnNovoPedido = new System.Windows.Forms.Button();
            this.btnVerPedidos = new System.Windows.Forms.Button();
            this.btnHistorico = new System.Windows.Forms.Button();
            this.panelRoot.SuspendLayout();
            this.panelTopo.SuspendLayout();
            this.SuspendLayout();
            // 
            // FrmEncomendasPorMedidaMenu
            // 
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(900, 600);
            this.Controls.Add(this.panelRoot);
            this.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Name = "FrmEncomendasPorMedidaMenu";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Pedidos por medida";
            // 
            // panelRoot
            // 
            this.panelRoot.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelRoot.Controls.Add(this.btnHistorico);
            this.panelRoot.Controls.Add(this.btnVerPedidos);
            this.panelRoot.Controls.Add(this.btnNovoPedido);
            this.panelRoot.Controls.Add(this.panelTopo);
            this.panelRoot.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelRoot.Location = new System.Drawing.Point(0, 0);
            this.panelRoot.Name = "panelRoot";
            this.panelRoot.Padding = new System.Windows.Forms.Padding(30);
            this.panelRoot.Size = new System.Drawing.Size(900, 600);
            this.panelRoot.TabIndex = 0;
            // 
            // panelTopo
            // 
            this.panelTopo.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelTopo.Controls.Add(this.lblSubtitulo);
            this.panelTopo.Controls.Add(this.lblTitulo);
            this.panelTopo.Controls.Add(this.btnVoltar);
            this.panelTopo.Location = new System.Drawing.Point(30, 25);
            this.panelTopo.Name = "panelTopo";
            this.panelTopo.Size = new System.Drawing.Size(720, 78);
            this.panelTopo.TabIndex = 0;
            // 
            // btnVoltar
            // 
            this.btnVoltar.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btnVoltar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVoltar.FlatAppearance.BorderSize = 0;
            this.btnVoltar.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(235, 235, 235);
            this.btnVoltar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVoltar.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.btnVoltar.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnVoltar.Location = new System.Drawing.Point(0, 0);
            this.btnVoltar.Name = "btnVoltar";
            this.btnVoltar.Size = new System.Drawing.Size(48, 42);
            this.btnVoltar.TabIndex = 0;
            this.btnVoltar.Text = "<";
            this.btnVoltar.UseVisualStyleBackColor = false;
            this.btnVoltar.Click += new System.EventHandler(this.BtnVoltar_Click);
            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = false;
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.lblTitulo.Location = new System.Drawing.Point(54, 0);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(650, 42);
            this.lblTitulo.TabIndex = 1;
            this.lblTitulo.Text = "Pedidos por medida";
            // 
            // lblSubtitulo
            // 
            this.lblSubtitulo.AutoSize = false;
            this.lblSubtitulo.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.lblSubtitulo.ForeColor = System.Drawing.Color.FromArgb(90, 90, 90);
            this.lblSubtitulo.Location = new System.Drawing.Point(56, 44);
            this.lblSubtitulo.Name = "lblSubtitulo";
            this.lblSubtitulo.Size = new System.Drawing.Size(720, 32);
            this.lblSubtitulo.TabIndex = 2;
            this.lblSubtitulo.Text = "Cria pedidos, acompanha pendentes e consulta o historico de entregues.";
            // 
            // btnNovoPedido
            // 
            this.btnNovoPedido.BackColor = System.Drawing.Color.White;
            this.btnNovoPedido.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnNovoPedido.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(220, 220, 220);
            this.btnNovoPedido.FlatAppearance.BorderSize = 1;
            this.btnNovoPedido.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(235, 242, 250);
            this.btnNovoPedido.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 248, 252);
            this.btnNovoPedido.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnNovoPedido.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.btnNovoPedido.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnNovoPedido.Location = new System.Drawing.Point(30, 125);
            this.btnNovoPedido.Name = "btnNovoPedido";
            this.btnNovoPedido.Padding = new System.Windows.Forms.Padding(24, 0, 20, 0);
            this.btnNovoPedido.Size = new System.Drawing.Size(680, 95);
            this.btnNovoPedido.TabIndex = 1;
            this.btnNovoPedido.Text = "";
            this.btnNovoPedido.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnNovoPedido.UseVisualStyleBackColor = false;
            this.btnNovoPedido.Click += new System.EventHandler(this.BtnNovoPedido_Click);
            this.btnNovoPedido.Paint += new System.Windows.Forms.PaintEventHandler(this.BtnNovoPedido_Paint);
            // 
            // btnVerPedidos
            // 
            this.btnVerPedidos.BackColor = System.Drawing.Color.White;
            this.btnVerPedidos.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVerPedidos.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(220, 220, 220);
            this.btnVerPedidos.FlatAppearance.BorderSize = 1;
            this.btnVerPedidos.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(235, 242, 250);
            this.btnVerPedidos.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 248, 252);
            this.btnVerPedidos.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVerPedidos.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.btnVerPedidos.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnVerPedidos.Location = new System.Drawing.Point(30, 250);
            this.btnVerPedidos.Name = "btnVerPedidos";
            this.btnVerPedidos.Padding = new System.Windows.Forms.Padding(24, 0, 20, 0);
            this.btnVerPedidos.Size = new System.Drawing.Size(680, 95);
            this.btnVerPedidos.TabIndex = 2;
            this.btnVerPedidos.Text = "";
            this.btnVerPedidos.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnVerPedidos.UseVisualStyleBackColor = false;
            this.btnVerPedidos.Click += new System.EventHandler(this.BtnPedidosPendentes_Click);
            this.btnVerPedidos.Paint += new System.Windows.Forms.PaintEventHandler(this.BtnVerPedidos_Paint);
            // 
            // btnHistorico
            // 
            this.btnHistorico.BackColor = System.Drawing.Color.White;
            this.btnHistorico.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnHistorico.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(220, 220, 220);
            this.btnHistorico.FlatAppearance.BorderSize = 1;
            this.btnHistorico.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(235, 242, 250);
            this.btnHistorico.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 248, 252);
            this.btnHistorico.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnHistorico.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.btnHistorico.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnHistorico.Location = new System.Drawing.Point(30, 375);
            this.btnHistorico.Name = "btnHistorico";
            this.btnHistorico.Padding = new System.Windows.Forms.Padding(24, 0, 20, 0);
            this.btnHistorico.Size = new System.Drawing.Size(680, 95);
            this.btnHistorico.TabIndex = 3;
            this.btnHistorico.Text = "";
            this.btnHistorico.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnHistorico.UseVisualStyleBackColor = false;
            this.btnHistorico.Click += new System.EventHandler(this.BtnHistorico_Click);
            this.btnHistorico.Paint += new System.Windows.Forms.PaintEventHandler(this.BtnHistorico_Paint);
            // 
            // FrmEncomendasPorMedidaMenu
            // 
            this.panelTopo.ResumeLayout(false);
            this.panelRoot.ResumeLayout(false);
            this.ResumeLayout(false);
        }
    }
}
