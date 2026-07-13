using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    partial class FrmProntoVestirMenu
    {
        private System.ComponentModel.IContainer components = null;
        private Panel panelRoot;
        private Panel panelTopo;
        private Label lblTitulo;
        private Label lblSubtitulo;
        private Button btnVoltar;
        private Button btnNovaCompra;
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
            this.btnNovaCompra = new System.Windows.Forms.Button();
            this.btnHistorico = new System.Windows.Forms.Button();
            this.panelRoot.SuspendLayout();
            this.panelTopo.SuspendLayout();
            this.SuspendLayout();
            // 
            // FrmProntoVestirMenu
            // 
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(900, 600);
            this.Controls.Add(this.panelRoot);
            this.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Name = "FrmProntoVestirMenu";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Pronto a Vestir";
            // 
            // panelRoot
            // 
            this.panelRoot.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelRoot.Controls.Add(this.btnHistorico);
            this.panelRoot.Controls.Add(this.btnNovaCompra);
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
            this.lblTitulo.Text = "Pronto a vestir";
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
            this.lblSubtitulo.Text = "Regista uma nova compra com varios produtos ou consulta o historico de vendas.";
            // 
            // btnNovaCompra
            // 
            this.btnNovaCompra.BackColor = System.Drawing.Color.White;
            this.btnNovaCompra.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnNovaCompra.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(220, 220, 220);
            this.btnNovaCompra.FlatAppearance.BorderSize = 1;
            this.btnNovaCompra.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(235, 242, 250);
            this.btnNovaCompra.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 248, 252);
            this.btnNovaCompra.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnNovaCompra.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.btnNovaCompra.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnNovaCompra.Location = new System.Drawing.Point(30, 125);
            this.btnNovaCompra.Name = "btnNovaCompra";
            this.btnNovaCompra.Padding = new System.Windows.Forms.Padding(24, 0, 20, 0);
            this.btnNovaCompra.Size = new System.Drawing.Size(680, 95);
            this.btnNovaCompra.TabIndex = 1;
            this.btnNovaCompra.Text = "";
            this.btnNovaCompra.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnNovaCompra.UseVisualStyleBackColor = false;
            this.btnNovaCompra.Click += new System.EventHandler(this.BtnNovaCompra_Click);
            this.btnNovaCompra.Paint += new System.Windows.Forms.PaintEventHandler(this.BtnNovaCompra_Paint);
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
            this.btnHistorico.Location = new System.Drawing.Point(30, 250);
            this.btnHistorico.Name = "btnHistorico";
            this.btnHistorico.Padding = new System.Windows.Forms.Padding(24, 0, 20, 0);
            this.btnHistorico.Size = new System.Drawing.Size(680, 95);
            this.btnHistorico.TabIndex = 2;
            this.btnHistorico.Text = "";
            this.btnHistorico.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnHistorico.UseVisualStyleBackColor = false;
            this.btnHistorico.Click += new System.EventHandler(this.BtnHistorico_Click);
            this.btnHistorico.Paint += new System.Windows.Forms.PaintEventHandler(this.BtnHistorico_Paint);
            // 
            // FrmProntoVestirMenu
            // 
            this.panelTopo.ResumeLayout(false);
            this.panelRoot.ResumeLayout(false);
            this.ResumeLayout(false);
        }
    }
}
