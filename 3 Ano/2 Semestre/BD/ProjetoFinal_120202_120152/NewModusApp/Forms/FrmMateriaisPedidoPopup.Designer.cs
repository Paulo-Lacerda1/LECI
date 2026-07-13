namespace NewModusApp.Forms
{
    partial class FrmMateriaisPedidoPopup
    {
        private System.ComponentModel.IContainer components = null;

        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lblInfo;
        private System.Windows.Forms.CheckBox chkTecido;
        private System.Windows.Forms.ComboBox cmbTecido;
        private System.Windows.Forms.Label lblMetros;
        private System.Windows.Forms.NumericUpDown nudMetros;
        private System.Windows.Forms.CheckBox chkMaterial;
        private System.Windows.Forms.ComboBox cmbMaterial;
        private System.Windows.Forms.Label lblQuantidade;
        private System.Windows.Forms.NumericUpDown nudQuantidade;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.Button btnIgnorar;

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
            this.lblTitulo = new System.Windows.Forms.Label();
            this.lblInfo = new System.Windows.Forms.Label();
            this.chkTecido = new System.Windows.Forms.CheckBox();
            this.cmbTecido = new System.Windows.Forms.ComboBox();
            this.lblMetros = new System.Windows.Forms.Label();
            this.nudMetros = new System.Windows.Forms.NumericUpDown();
            this.chkMaterial = new System.Windows.Forms.CheckBox();
            this.cmbMaterial = new System.Windows.Forms.ComboBox();
            this.lblQuantidade = new System.Windows.Forms.Label();
            this.nudQuantidade = new System.Windows.Forms.NumericUpDown();
            this.btnGuardar = new System.Windows.Forms.Button();
            this.btnIgnorar = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.nudMetros)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudQuantidade)).BeginInit();
            this.SuspendLayout();
            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = false;
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.Location = new System.Drawing.Point(24, 18);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(450, 32);
            this.lblTitulo.TabIndex = 0;
            this.lblTitulo.Text = "Tecido e material usado";
            // 
            // lblInfo
            // 
            this.lblInfo.AutoSize = false;
            this.lblInfo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(80)))), ((int)(((byte)(80)))), ((int)(((byte)(80)))));
            this.lblInfo.Location = new System.Drawing.Point(25, 55);
            this.lblInfo.Name = "lblInfo";
            this.lblInfo.Size = new System.Drawing.Size(460, 42);
            this.lblInfo.TabIndex = 1;
            this.lblInfo.Text = "Podes registar agora o tecido/material usado neste produto ou ignorar e tratar depois nos pendentes.";
            // 
            // chkTecido
            // 
            this.chkTecido.Checked = true;
            this.chkTecido.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkTecido.Location = new System.Drawing.Point(28, 112);
            this.chkTecido.Name = "chkTecido";
            this.chkTecido.Size = new System.Drawing.Size(120, 24);
            this.chkTecido.TabIndex = 2;
            this.chkTecido.Text = "Tecido";
            this.chkTecido.UseVisualStyleBackColor = true;
            this.chkTecido.CheckedChanged += new System.EventHandler(this.ChkTecido_CheckedChanged);
            // 
            // cmbTecido
            // 
            this.cmbTecido.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbTecido.FormattingEnabled = true;
            this.cmbTecido.Location = new System.Drawing.Point(28, 142);
            this.cmbTecido.Name = "cmbTecido";
            this.cmbTecido.Size = new System.Drawing.Size(300, 31);
            this.cmbTecido.TabIndex = 3;
            // 
            // lblMetros
            // 
            this.lblMetros.AutoSize = false;
            this.lblMetros.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblMetros.Location = new System.Drawing.Point(350, 115);
            this.lblMetros.Name = "lblMetros";
            this.lblMetros.Size = new System.Drawing.Size(100, 24);
            this.lblMetros.TabIndex = 4;
            this.lblMetros.Text = "Metros";
            // 
            // nudMetros
            // 
            this.nudMetros.DecimalPlaces = 2;
            this.nudMetros.Location = new System.Drawing.Point(350, 142);
            this.nudMetros.Maximum = new decimal(new int[] { 999, 0, 0, 0 });
            this.nudMetros.Name = "nudMetros";
            this.nudMetros.Size = new System.Drawing.Size(120, 30);
            this.nudMetros.TabIndex = 5;
            this.nudMetros.Value = new decimal(new int[] { 1, 0, 0, 0 });
            // 
            // chkMaterial
            // 
            this.chkMaterial.Checked = true;
            this.chkMaterial.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkMaterial.Location = new System.Drawing.Point(28, 192);
            this.chkMaterial.Name = "chkMaterial";
            this.chkMaterial.Size = new System.Drawing.Size(120, 24);
            this.chkMaterial.TabIndex = 6;
            this.chkMaterial.Text = "Material";
            this.chkMaterial.UseVisualStyleBackColor = true;
            this.chkMaterial.CheckedChanged += new System.EventHandler(this.ChkMaterial_CheckedChanged);
            // 
            // cmbMaterial
            // 
            this.cmbMaterial.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbMaterial.FormattingEnabled = true;
            this.cmbMaterial.Location = new System.Drawing.Point(28, 222);
            this.cmbMaterial.Name = "cmbMaterial";
            this.cmbMaterial.Size = new System.Drawing.Size(300, 31);
            this.cmbMaterial.TabIndex = 7;
            // 
            // lblQuantidade
            // 
            this.lblQuantidade.AutoSize = false;
            this.lblQuantidade.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblQuantidade.Location = new System.Drawing.Point(350, 195);
            this.lblQuantidade.Name = "lblQuantidade";
            this.lblQuantidade.Size = new System.Drawing.Size(120, 24);
            this.lblQuantidade.TabIndex = 8;
            this.lblQuantidade.Text = "Quantidade";
            // 
            // nudQuantidade
            // 
            this.nudQuantidade.Location = new System.Drawing.Point(350, 222);
            this.nudQuantidade.Maximum = new decimal(new int[] { 999, 0, 0, 0 });
            this.nudQuantidade.Name = "nudQuantidade";
            this.nudQuantidade.Size = new System.Drawing.Size(120, 30);
            this.nudQuantidade.TabIndex = 9;
            this.nudQuantidade.Value = new decimal(new int[] { 1, 0, 0, 0 });
            // 
            // btnGuardar
            // 
            this.btnGuardar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnGuardar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnGuardar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(45)))), ((int)(((byte)(45)))), ((int)(((byte)(45)))));
            this.btnGuardar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnGuardar.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnGuardar.ForeColor = System.Drawing.Color.White;
            this.btnGuardar.Location = new System.Drawing.Point(218, 280);
            this.btnGuardar.Name = "btnGuardar";
            this.btnGuardar.Size = new System.Drawing.Size(95, 36);
            this.btnGuardar.TabIndex = 10;
            this.btnGuardar.Text = "Guardar";
            this.btnGuardar.UseVisualStyleBackColor = false;
            this.btnGuardar.Click += new System.EventHandler(this.BtnGuardar_Click);
            // 
            // btnIgnorar
            // 
            this.btnIgnorar.BackColor = System.Drawing.Color.White;
            this.btnIgnorar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnIgnorar.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(205)))), ((int)(((byte)(205)))), ((int)(((byte)(205)))));
            this.btnIgnorar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnIgnorar.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnIgnorar.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(35)))), ((int)(((byte)(35)))), ((int)(((byte)(35)))));
            this.btnIgnorar.Location = new System.Drawing.Point(323, 280);
            this.btnIgnorar.Name = "btnIgnorar";
            this.btnIgnorar.Size = new System.Drawing.Size(155, 36);
            this.btnIgnorar.TabIndex = 11;
            this.btnIgnorar.Text = "Não colocar agora";
            this.btnIgnorar.UseVisualStyleBackColor = false;
            this.btnIgnorar.Click += new System.EventHandler(this.BtnIgnorar_Click);
            // 
            // FrmMateriaisPedidoPopup
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 23F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(520, 335);
            this.Controls.Add(this.btnIgnorar);
            this.Controls.Add(this.btnGuardar);
            this.Controls.Add(this.nudQuantidade);
            this.Controls.Add(this.lblQuantidade);
            this.Controls.Add(this.cmbMaterial);
            this.Controls.Add(this.chkMaterial);
            this.Controls.Add(this.nudMetros);
            this.Controls.Add(this.lblMetros);
            this.Controls.Add(this.cmbTecido);
            this.Controls.Add(this.chkTecido);
            this.Controls.Add(this.lblInfo);
            this.Controls.Add(this.lblTitulo);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "FrmMateriaisPedidoPopup";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Materiais usados";
            ((System.ComponentModel.ISupportInitialize)(this.nudMetros)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudQuantidade)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion
    }
}