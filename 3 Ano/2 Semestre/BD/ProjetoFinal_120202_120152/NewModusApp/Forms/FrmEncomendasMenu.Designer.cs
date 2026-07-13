namespace NewModusApp.Forms
{
    partial class FrmEncomendasMenu
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Panel panelRoot;
        private System.Windows.Forms.Panel panelTopo;
        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Button btnVoltar;
        private System.Windows.Forms.Button btnPorMedida;
        private System.Windows.Forms.Button btnProntoVestir;

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
            this.btnVoltar = new System.Windows.Forms.Button();
            this.btnPorMedida = new System.Windows.Forms.Button();
            this.btnProntoVestir = new System.Windows.Forms.Button();
            this.panelRoot.SuspendLayout();
            this.panelTopo.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelRoot
            // 
            this.panelRoot.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelRoot.Controls.Add(this.btnProntoVestir);
            this.panelRoot.Controls.Add(this.btnPorMedida);
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
            this.panelTopo.Controls.Add(this.lblTitulo);
            this.panelTopo.Controls.Add(this.btnVoltar);
            this.panelTopo.Location = new System.Drawing.Point(30, 25);
            this.panelTopo.Name = "panelTopo";
            this.panelTopo.Size = new System.Drawing.Size(650, 58);
            this.panelTopo.TabIndex = 2;
            // 
            // lblTitulo
            // 
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.lblTitulo.Location = new System.Drawing.Point(54, 0);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(500, 40);
            this.lblTitulo.Text = "Tipo de encomenda";
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
            // btnPorMedida
            // 
            this.btnPorMedida.BackColor = System.Drawing.Color.White;
            this.btnPorMedida.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnPorMedida.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(220, 220, 220);
            this.btnPorMedida.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(235, 242, 250);
            this.btnPorMedida.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 248, 252);
            this.btnPorMedida.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnPorMedida.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold);
            this.btnPorMedida.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnPorMedida.Location = new System.Drawing.Point(30, 95);
            this.btnPorMedida.Name = "btnPorMedida";
            this.btnPorMedida.Padding = new System.Windows.Forms.Padding(24, 0, 20, 0);
            this.btnPorMedida.Size = new System.Drawing.Size(620, 95);
            this.btnPorMedida.TabIndex = 0;
            this.btnPorMedida.Text = "Por medida\r\nCriação de peças personalizadas para clientes com medidas.";
            this.btnPorMedida.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnPorMedida.UseVisualStyleBackColor = false;
            this.btnPorMedida.Click += new System.EventHandler(this.BtnPorMedida_Click);
            // 
            // btnProntoVestir
            // 
            this.btnProntoVestir.BackColor = System.Drawing.Color.White;
            this.btnProntoVestir.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnProntoVestir.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(220, 220, 220);
            this.btnProntoVestir.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(235, 242, 250);
            this.btnProntoVestir.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(245, 248, 252);
            this.btnProntoVestir.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnProntoVestir.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold);
            this.btnProntoVestir.ForeColor = System.Drawing.Color.FromArgb(35, 35, 35);
            this.btnProntoVestir.Location = new System.Drawing.Point(30, 220);
            this.btnProntoVestir.Name = "btnProntoVestir";
            this.btnProntoVestir.Padding = new System.Windows.Forms.Padding(24, 0, 20, 0);
            this.btnProntoVestir.Size = new System.Drawing.Size(620, 95);
            this.btnProntoVestir.TabIndex = 1;
            this.btnProntoVestir.Text = "Pronto a vestir\r\nVenda de peças prontas em stock.";
            this.btnProntoVestir.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnProntoVestir.UseVisualStyleBackColor = false;
            this.btnProntoVestir.Click += new System.EventHandler(this.BtnProntoVestir_Click);
            // 
            // FrmEncomendasMenu
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(900, 600);
            this.Controls.Add(this.panelRoot);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.Name = "FrmEncomendasMenu";
            this.Text = "Encomendas";
            this.panelTopo.ResumeLayout(false);
            this.panelRoot.ResumeLayout(false);
            this.ResumeLayout(false);

        }
    }
}
