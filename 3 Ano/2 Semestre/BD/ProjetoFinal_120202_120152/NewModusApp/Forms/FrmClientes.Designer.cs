namespace NewModusApp.Forms
{
    partial class FrmClientes
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.panelBody = new System.Windows.Forms.Panel();
            this.panelDireita = new System.Windows.Forms.Panel();
            this.dgvClientes = new System.Windows.Forms.DataGridView();
            this.panelPesquisa = new System.Windows.Forms.Panel();
            this.btnPesquisar = new System.Windows.Forms.Button();
            this.txtPesquisa = new System.Windows.Forms.TextBox();
            this.panelFormulario = new System.Windows.Forms.Panel();
            this.lblFormTitulo = new System.Windows.Forms.Label();
            this.lblId = new System.Windows.Forms.Label();
            this.txtId = new System.Windows.Forms.TextBox();
            this.lblNome = new System.Windows.Forms.Label();
            this.txtNome = new System.Windows.Forms.TextBox();
            this.lblTelefone = new System.Windows.Forms.Label();
            this.txtTelefone = new System.Windows.Forms.TextBox();
            this.lblEmail = new System.Windows.Forms.Label();
            this.txtEmail = new System.Windows.Forms.TextBox();
            this.btnGuardar = new System.Windows.Forms.Button();
            this.btnEditar = new System.Windows.Forms.Button();
            this.btnApagar = new System.Windows.Forms.Button();
            this.btnLimpar = new System.Windows.Forms.Button();
            this.btnMedidas = new System.Windows.Forms.Button();
            this.btnEncomendas = new System.Windows.Forms.Button();
            this.panelBody.SuspendLayout();
            this.panelDireita.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvClientes)).BeginInit();
            this.panelPesquisa.SuspendLayout();
            this.panelFormulario.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelBody
            // 
            this.panelBody.BackColor = System.Drawing.Color.WhiteSmoke;
            this.panelBody.Controls.Add(this.panelDireita);
            this.panelBody.Controls.Add(this.panelFormulario);
            this.panelBody.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelBody.Location = new System.Drawing.Point(0, 0);
            this.panelBody.Name = "panelBody";
            this.panelBody.Padding = new System.Windows.Forms.Padding(20);
            this.panelBody.Size = new System.Drawing.Size(900, 600);
            this.panelBody.TabIndex = 0;
            // 
            // panelDireita
            // 
            this.panelDireita.BackColor = System.Drawing.Color.White;
            this.panelDireita.Controls.Add(this.dgvClientes);
            this.panelDireita.Controls.Add(this.panelPesquisa);
            this.panelDireita.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelDireita.Location = new System.Drawing.Point(350, 20);
            this.panelDireita.Name = "panelDireita";
            this.panelDireita.Padding = new System.Windows.Forms.Padding(20);
            this.panelDireita.Size = new System.Drawing.Size(530, 560);
            this.panelDireita.TabIndex = 1;
            // 
            // dgvClientes
            // 
            this.dgvClientes.AllowUserToAddRows = false;
            this.dgvClientes.AllowUserToDeleteRows = false;
            this.dgvClientes.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvClientes.BackgroundColor = System.Drawing.Color.White;
            this.dgvClientes.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvClientes.ColumnHeadersHeight = 35;
            this.dgvClientes.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvClientes.EnableHeadersVisualStyles = false;
            this.dgvClientes.Location = new System.Drawing.Point(20, 75);
            this.dgvClientes.MultiSelect = false;
            this.dgvClientes.Name = "dgvClientes";
            this.dgvClientes.ReadOnly = true;
            this.dgvClientes.RowHeadersVisible = false;
            this.dgvClientes.RowHeadersWidth = 51;
            this.dgvClientes.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvClientes.Size = new System.Drawing.Size(490, 465);
            this.dgvClientes.TabIndex = 1;
            this.dgvClientes.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvClientes_CellClick);
            this.dgvClientes.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvClientes_DataBindingComplete);
            // 
            // panelPesquisa
            // 
            this.panelPesquisa.BackColor = System.Drawing.Color.White;
            this.panelPesquisa.Controls.Add(this.btnPesquisar);
            this.panelPesquisa.Controls.Add(this.txtPesquisa);
            this.panelPesquisa.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelPesquisa.Location = new System.Drawing.Point(20, 20);
            this.panelPesquisa.Name = "panelPesquisa";
            this.panelPesquisa.Size = new System.Drawing.Size(490, 55);
            this.panelPesquisa.TabIndex = 0;
            // 
            // btnPesquisar
            // 
            this.btnPesquisar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnPesquisar.Location = new System.Drawing.Point(315, 8);
            this.btnPesquisar.Name = "btnPesquisar";
            this.btnPesquisar.Size = new System.Drawing.Size(110, 32);
            this.btnPesquisar.TabIndex = 1;
            this.btnPesquisar.Text = "Pesquisar";
            this.btnPesquisar.UseVisualStyleBackColor = true;
            this.btnPesquisar.Click += new System.EventHandler(this.BtnPesquisar_Click);
            // 
            // txtPesquisa
            // 
            this.txtPesquisa.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.txtPesquisa.Location = new System.Drawing.Point(0, 10);
            this.txtPesquisa.Name = "txtPesquisa";
            this.txtPesquisa.Size = new System.Drawing.Size(300, 30);
            this.txtPesquisa.TabIndex = 0;
            // 
            // panelFormulario
            // 
            this.panelFormulario.BackColor = System.Drawing.Color.White;
            this.panelFormulario.Controls.Add(this.lblFormTitulo);
            this.panelFormulario.Controls.Add(this.lblId);
            this.panelFormulario.Controls.Add(this.txtId);
            this.panelFormulario.Controls.Add(this.lblNome);
            this.panelFormulario.Controls.Add(this.txtNome);
            this.panelFormulario.Controls.Add(this.lblTelefone);
            this.panelFormulario.Controls.Add(this.txtTelefone);
            this.panelFormulario.Controls.Add(this.lblEmail);
            this.panelFormulario.Controls.Add(this.txtEmail);
            this.panelFormulario.Controls.Add(this.btnGuardar);
            this.panelFormulario.Controls.Add(this.btnEditar);
            this.panelFormulario.Controls.Add(this.btnApagar);
            this.panelFormulario.Controls.Add(this.btnLimpar);
            this.panelFormulario.Controls.Add(this.btnMedidas);
            this.panelFormulario.Controls.Add(this.btnEncomendas);
            this.panelFormulario.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelFormulario.Location = new System.Drawing.Point(20, 20);
            this.panelFormulario.Name = "panelFormulario";
            this.panelFormulario.Padding = new System.Windows.Forms.Padding(20);
            this.panelFormulario.Size = new System.Drawing.Size(330, 560);
            this.panelFormulario.TabIndex = 0;
            // 
            // lblFormTitulo
            // 
            this.lblFormTitulo.Font = new System.Drawing.Font("Segoe UI", 15F, System.Drawing.FontStyle.Bold);
            this.lblFormTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(40)))), ((int)(((byte)(40)))), ((int)(((byte)(40)))));
            this.lblFormTitulo.Location = new System.Drawing.Point(20, 20);
            this.lblFormTitulo.Name = "lblFormTitulo";
            this.lblFormTitulo.Size = new System.Drawing.Size(270, 35);
            this.lblFormTitulo.TabIndex = 0;
            this.lblFormTitulo.Text = "Dados do Cliente";
            // 
            // lblId
            // 
            this.lblId.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblId.Location = new System.Drawing.Point(20, 75);
            this.lblId.Name = "lblId";
            this.lblId.Size = new System.Drawing.Size(260, 22);
            this.lblId.TabIndex = 1;
            this.lblId.Text = "ID Cliente";
            // 
            // txtId
            // 
            this.txtId.Location = new System.Drawing.Point(20, 100);
            this.txtId.Name = "txtId";
            this.txtId.ReadOnly = true;
            this.txtId.Size = new System.Drawing.Size(270, 30);
            this.txtId.TabIndex = 2;
            // 
            // lblNome
            // 
            this.lblNome.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblNome.Location = new System.Drawing.Point(20, 145);
            this.lblNome.Name = "lblNome";
            this.lblNome.Size = new System.Drawing.Size(260, 22);
            this.lblNome.TabIndex = 3;
            this.lblNome.Text = "Nome";
            // 
            // txtNome
            // 
            this.txtNome.Location = new System.Drawing.Point(20, 170);
            this.txtNome.Name = "txtNome";
            this.txtNome.Size = new System.Drawing.Size(270, 30);
            this.txtNome.TabIndex = 4;
            // 
            // lblTelefone
            // 
            this.lblTelefone.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblTelefone.Location = new System.Drawing.Point(20, 215);
            this.lblTelefone.Name = "lblTelefone";
            this.lblTelefone.Size = new System.Drawing.Size(260, 22);
            this.lblTelefone.TabIndex = 5;
            this.lblTelefone.Text = "Telefone";
            // 
            // txtTelefone
            // 
            this.txtTelefone.Location = new System.Drawing.Point(20, 240);
            this.txtTelefone.Name = "txtTelefone";
            this.txtTelefone.Size = new System.Drawing.Size(270, 30);
            this.txtTelefone.TabIndex = 6;
            // 
            // lblEmail
            // 
            this.lblEmail.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblEmail.Location = new System.Drawing.Point(20, 285);
            this.lblEmail.Name = "lblEmail";
            this.lblEmail.Size = new System.Drawing.Size(260, 22);
            this.lblEmail.TabIndex = 7;
            this.lblEmail.Text = "Email";
            // 
            // txtEmail
            // 
            this.txtEmail.Location = new System.Drawing.Point(20, 310);
            this.txtEmail.Name = "txtEmail";
            this.txtEmail.Size = new System.Drawing.Size(270, 30);
            this.txtEmail.TabIndex = 8;
            // 
            // btnGuardar
            // 
            this.btnGuardar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnGuardar.Location = new System.Drawing.Point(20, 370);
            this.btnGuardar.Name = "btnGuardar";
            this.btnGuardar.Size = new System.Drawing.Size(120, 35);
            this.btnGuardar.TabIndex = 9;
            this.btnGuardar.Text = "Guardar";
            this.btnGuardar.UseVisualStyleBackColor = true;
            this.btnGuardar.Click += new System.EventHandler(this.BtnGuardar_Click);
            // 
            // btnEditar
            // 
            this.btnEditar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnEditar.Location = new System.Drawing.Point(170, 370);
            this.btnEditar.Name = "btnEditar";
            this.btnEditar.Size = new System.Drawing.Size(120, 35);
            this.btnEditar.TabIndex = 10;
            this.btnEditar.Text = "Editar";
            this.btnEditar.UseVisualStyleBackColor = true;
            this.btnEditar.Click += new System.EventHandler(this.BtnEditar_Click);
            // 
            // btnApagar
            // 
            this.btnApagar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnApagar.Location = new System.Drawing.Point(20, 420);
            this.btnApagar.Name = "btnApagar";
            this.btnApagar.Size = new System.Drawing.Size(120, 35);
            this.btnApagar.TabIndex = 11;
            this.btnApagar.Text = "Apagar";
            this.btnApagar.UseVisualStyleBackColor = true;
            this.btnApagar.Click += new System.EventHandler(this.BtnApagar_Click);
            // 
            // btnLimpar
            // 
            this.btnLimpar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnLimpar.Location = new System.Drawing.Point(170, 420);
            this.btnLimpar.Name = "btnLimpar";
            this.btnLimpar.Size = new System.Drawing.Size(120, 35);
            this.btnLimpar.TabIndex = 12;
            this.btnLimpar.Text = "Limpar";
            this.btnLimpar.UseVisualStyleBackColor = true;
            this.btnLimpar.Click += new System.EventHandler(this.BtnLimpar_Click);
            // 
            // btnMedidas
            // 
            this.btnMedidas.BackColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnMedidas.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnMedidas.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnMedidas.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(25, 25, 25);
            this.btnMedidas.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(65, 65, 65);
            this.btnMedidas.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnMedidas.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnMedidas.ForeColor = System.Drawing.Color.White;
            this.btnMedidas.Location = new System.Drawing.Point(20, 470);
            this.btnMedidas.Name = "btnMedidas";
            this.btnMedidas.Size = new System.Drawing.Size(120, 35);
            this.btnMedidas.TabIndex = 13;
            this.btnMedidas.Text = "Medidas";
            this.btnMedidas.UseVisualStyleBackColor = false;
            this.btnMedidas.Click += new System.EventHandler(this.BtnMedidas_Click);
            // 
            // btnEncomendas
            // 
            this.btnEncomendas.BackColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnEncomendas.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnEncomendas.FlatAppearance.BorderColor = System.Drawing.Color.FromArgb(45, 45, 45);
            this.btnEncomendas.FlatAppearance.MouseDownBackColor = System.Drawing.Color.FromArgb(25, 25, 25);
            this.btnEncomendas.FlatAppearance.MouseOverBackColor = System.Drawing.Color.FromArgb(65, 65, 65);
            this.btnEncomendas.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnEncomendas.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnEncomendas.ForeColor = System.Drawing.Color.White;
            this.btnEncomendas.Location = new System.Drawing.Point(170, 470);
            this.btnEncomendas.Name = "btnEncomendas";
            this.btnEncomendas.Size = new System.Drawing.Size(120, 35);
            this.btnEncomendas.TabIndex = 14;
            this.btnEncomendas.Text = "Encomendas";
            this.btnEncomendas.UseVisualStyleBackColor = false;
            this.btnEncomendas.Click += new System.EventHandler(this.BtnEncomendas_Click);
            // 
            // FrmClientes
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 23F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(900, 600);
            this.Controls.Add(this.panelBody);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "FrmClientes";
            this.Text = "Clientes";
            this.panelBody.ResumeLayout(false);
            this.panelDireita.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvClientes)).EndInit();
            this.panelPesquisa.ResumeLayout(false);
            this.panelPesquisa.PerformLayout();
            this.panelFormulario.ResumeLayout(false);
            this.panelFormulario.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panelBody;
        private System.Windows.Forms.Panel panelFormulario;
        private System.Windows.Forms.Label lblFormTitulo;
        private System.Windows.Forms.Label lblId;
        private System.Windows.Forms.Label lblNome;
        private System.Windows.Forms.Label lblTelefone;
        private System.Windows.Forms.Label lblEmail;
        private System.Windows.Forms.Panel panelDireita;
        private System.Windows.Forms.Panel panelPesquisa;
        private System.Windows.Forms.TextBox txtPesquisa;
        private System.Windows.Forms.Button btnPesquisar;
        private System.Windows.Forms.DataGridView dgvClientes;
        private System.Windows.Forms.TextBox txtId;
        private System.Windows.Forms.TextBox txtNome;
        private System.Windows.Forms.TextBox txtTelefone;
        private System.Windows.Forms.TextBox txtEmail;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.Button btnEditar;
        private System.Windows.Forms.Button btnApagar;
        private System.Windows.Forms.Button btnLimpar;
        private System.Windows.Forms.Button btnMedidas;
        private System.Windows.Forms.Button btnEncomendas;
    }
}
