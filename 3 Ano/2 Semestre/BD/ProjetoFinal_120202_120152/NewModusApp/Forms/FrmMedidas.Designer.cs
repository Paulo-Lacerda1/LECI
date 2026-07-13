namespace NewModusApp.Forms
{
    partial class FrmMedidas
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Panel panelBody;
        private System.Windows.Forms.Panel panelFormulario;
        private System.Windows.Forms.Panel panelDireita;
        private System.Windows.Forms.Panel panelPesquisa;
        private System.Windows.Forms.Label lblFormTitulo;
        private System.Windows.Forms.Label lblInicio;
        private System.Windows.Forms.Label lblFim;
        private System.Windows.Forms.Label lblId;
        private System.Windows.Forms.Label lblNomePerfil;
        private System.Windows.Forms.Label lblCliente;
        private System.Windows.Forms.Label lblData;
        private System.Windows.Forms.Label lblBraco;
        private System.Windows.Forms.Label lblCostas;
        private System.Windows.Forms.Label lblPeito;
        private System.Windows.Forms.Label lblCinta;
        private System.Windows.Forms.Label lblAnca;
        private System.Windows.Forms.DataGridView dgvMedidas;
        private System.Windows.Forms.TextBox txtId;
        private System.Windows.Forms.TextBox txtNomePerfil;
        private System.Windows.Forms.ComboBox cmbCliente;
        private System.Windows.Forms.DateTimePicker dtpData;
        private System.Windows.Forms.TextBox txtBraco;
        private System.Windows.Forms.TextBox txtCostas;
        private System.Windows.Forms.TextBox txtPeito;
        private System.Windows.Forms.TextBox txtCinta;
        private System.Windows.Forms.TextBox txtAnca;
        private System.Windows.Forms.ComboBox cmbFiltroCliente;
        private System.Windows.Forms.CheckBox chkFiltrarData;
        private System.Windows.Forms.DateTimePicker dtpFiltroInicio;
        private System.Windows.Forms.DateTimePicker dtpFiltroFim;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.Button btnEditar;
        private System.Windows.Forms.Button btnApagar;
        private System.Windows.Forms.Button btnLimpar;
        private System.Windows.Forms.Button btnPesquisar;

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
            this.panelBody = new System.Windows.Forms.Panel();
            this.panelDireita = new System.Windows.Forms.Panel();
            this.dgvMedidas = new System.Windows.Forms.DataGridView();
            this.panelPesquisa = new System.Windows.Forms.Panel();
            this.btnPesquisar = new System.Windows.Forms.Button();
            this.dtpFiltroFim = new System.Windows.Forms.DateTimePicker();
            this.lblFim = new System.Windows.Forms.Label();
            this.dtpFiltroInicio = new System.Windows.Forms.DateTimePicker();
            this.lblInicio = new System.Windows.Forms.Label();
            this.chkFiltrarData = new System.Windows.Forms.CheckBox();
            this.cmbFiltroCliente = new System.Windows.Forms.ComboBox();
            this.panelFormulario = new System.Windows.Forms.Panel();
            this.btnLimpar = new System.Windows.Forms.Button();
            this.btnApagar = new System.Windows.Forms.Button();
            this.btnEditar = new System.Windows.Forms.Button();
            this.btnGuardar = new System.Windows.Forms.Button();
            this.txtAnca = new System.Windows.Forms.TextBox();
            this.lblAnca = new System.Windows.Forms.Label();
            this.txtCinta = new System.Windows.Forms.TextBox();
            this.lblCinta = new System.Windows.Forms.Label();
            this.txtPeito = new System.Windows.Forms.TextBox();
            this.lblPeito = new System.Windows.Forms.Label();
            this.txtCostas = new System.Windows.Forms.TextBox();
            this.lblCostas = new System.Windows.Forms.Label();
            this.txtBraco = new System.Windows.Forms.TextBox();
            this.lblBraco = new System.Windows.Forms.Label();
            this.dtpData = new System.Windows.Forms.DateTimePicker();
            this.lblData = new System.Windows.Forms.Label();
            this.cmbCliente = new System.Windows.Forms.ComboBox();
            this.lblCliente = new System.Windows.Forms.Label();
            this.txtNomePerfil = new System.Windows.Forms.TextBox();
            this.lblNomePerfil = new System.Windows.Forms.Label();
            this.txtId = new System.Windows.Forms.TextBox();
            this.lblId = new System.Windows.Forms.Label();
            this.lblFormTitulo = new System.Windows.Forms.Label();
            this.panelBody.SuspendLayout();
            this.panelDireita.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvMedidas)).BeginInit();
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
            this.panelBody.Size = new System.Drawing.Size(1083, 650);
            this.panelBody.TabIndex = 0;
            // 
            // panelDireita
            // 
            this.panelDireita.BackColor = System.Drawing.Color.White;
            this.panelDireita.Controls.Add(this.dgvMedidas);
            this.panelDireita.Controls.Add(this.panelPesquisa);
            this.panelDireita.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelDireita.Location = new System.Drawing.Point(370, 20);
            this.panelDireita.Name = "panelDireita";
            this.panelDireita.Padding = new System.Windows.Forms.Padding(20);
            this.panelDireita.Size = new System.Drawing.Size(693, 610);
            this.panelDireita.TabIndex = 1;
            // 
            // dgvMedidas
            // 
            this.dgvMedidas.AllowUserToAddRows = false;
            this.dgvMedidas.AllowUserToDeleteRows = false;
            this.dgvMedidas.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvMedidas.BackgroundColor = System.Drawing.Color.White;
            this.dgvMedidas.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvMedidas.ColumnHeadersHeight = 35;
            this.dgvMedidas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvMedidas.EnableHeadersVisualStyles = false;
            this.dgvMedidas.Location = new System.Drawing.Point(20, 165);
            this.dgvMedidas.MultiSelect = false;
            this.dgvMedidas.Name = "dgvMedidas";
            this.dgvMedidas.ReadOnly = true;
            this.dgvMedidas.RowHeadersVisible = false;
            this.dgvMedidas.RowHeadersWidth = 51;
            this.dgvMedidas.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvMedidas.Size = new System.Drawing.Size(653, 425);
            this.dgvMedidas.TabIndex = 1;
            this.dgvMedidas.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DgvMedidas_CellClick);
            this.dgvMedidas.DataBindingComplete += new System.Windows.Forms.DataGridViewBindingCompleteEventHandler(this.DgvMedidas_DataBindingComplete);
            // 
            // panelPesquisa
            // 
            this.panelPesquisa.BackColor = System.Drawing.Color.White;
            this.panelPesquisa.Controls.Add(this.btnPesquisar);
            this.panelPesquisa.Controls.Add(this.dtpFiltroFim);
            this.panelPesquisa.Controls.Add(this.lblFim);
            this.panelPesquisa.Controls.Add(this.dtpFiltroInicio);
            this.panelPesquisa.Controls.Add(this.lblInicio);
            this.panelPesquisa.Controls.Add(this.chkFiltrarData);
            this.panelPesquisa.Controls.Add(this.cmbFiltroCliente);
            this.panelPesquisa.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelPesquisa.Location = new System.Drawing.Point(20, 20);
            this.panelPesquisa.Name = "panelPesquisa";
            this.panelPesquisa.Size = new System.Drawing.Size(653, 145);
            this.panelPesquisa.TabIndex = 0;
            // 
            // btnPesquisar
            // 
            this.btnPesquisar.Location = new System.Drawing.Point(260, 105);
            this.btnPesquisar.Name = "btnPesquisar";
            this.btnPesquisar.Size = new System.Drawing.Size(90, 32);
            this.btnPesquisar.TabIndex = 0;
            this.btnPesquisar.Text = "Pesquisar";
            this.btnPesquisar.UseVisualStyleBackColor = true;
            this.btnPesquisar.Click += new System.EventHandler(this.BtnPesquisar_Click);
            // 
            // dtpFiltroFim
            // 
            this.dtpFiltroFim.Enabled = false;
            this.dtpFiltroFim.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpFiltroFim.Location = new System.Drawing.Point(130, 106);
            this.dtpFiltroFim.Name = "dtpFiltroFim";
            this.dtpFiltroFim.Size = new System.Drawing.Size(120, 30);
            this.dtpFiltroFim.TabIndex = 2;
            // 
            // lblFim
            // 
            this.lblFim.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblFim.Location = new System.Drawing.Point(130, 84);
            this.lblFim.Name = "lblFim";
            this.lblFim.Size = new System.Drawing.Size(120, 20);
            this.lblFim.TabIndex = 3;
            this.lblFim.Text = "Fim";
            // 
            // dtpFiltroInicio
            // 
            this.dtpFiltroInicio.Enabled = false;
            this.dtpFiltroInicio.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpFiltroInicio.Location = new System.Drawing.Point(0, 106);
            this.dtpFiltroInicio.Name = "dtpFiltroInicio";
            this.dtpFiltroInicio.Size = new System.Drawing.Size(120, 30);
            this.dtpFiltroInicio.TabIndex = 1;
            // 
            // lblInicio
            // 
            this.lblInicio.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblInicio.Location = new System.Drawing.Point(0, 84);
            this.lblInicio.Name = "lblInicio";
            this.lblInicio.Size = new System.Drawing.Size(120, 20);
            this.lblInicio.TabIndex = 4;
            this.lblInicio.Text = "Inicio";
            // 
            // chkFiltrarData
            // 
            this.chkFiltrarData.Location = new System.Drawing.Point(0, 59);
            this.chkFiltrarData.Name = "chkFiltrarData";
            this.chkFiltrarData.Size = new System.Drawing.Size(180, 25);
            this.chkFiltrarData.TabIndex = 5;
            this.chkFiltrarData.Text = "Filtrar por data";
            this.chkFiltrarData.UseVisualStyleBackColor = true;
            this.chkFiltrarData.CheckedChanged += new System.EventHandler(this.ChkFiltrarData_CheckedChanged);
            // 
            // cmbFiltroCliente
            // 
            this.cmbFiltroCliente.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbFiltroCliente.FormattingEnabled = true;
            this.cmbFiltroCliente.Location = new System.Drawing.Point(0, 24);
            this.cmbFiltroCliente.Name = "cmbFiltroCliente";
            this.cmbFiltroCliente.Size = new System.Drawing.Size(260, 31);
            this.cmbFiltroCliente.TabIndex = 0;
            // 
            // panelFormulario
            // 
            this.panelFormulario.AutoScroll = true;
            this.panelFormulario.BackColor = System.Drawing.Color.White;
            this.panelFormulario.Controls.Add(this.btnLimpar);
            this.panelFormulario.Controls.Add(this.btnApagar);
            this.panelFormulario.Controls.Add(this.btnEditar);
            this.panelFormulario.Controls.Add(this.btnGuardar);
            this.panelFormulario.Controls.Add(this.txtAnca);
            this.panelFormulario.Controls.Add(this.lblAnca);
            this.panelFormulario.Controls.Add(this.txtCinta);
            this.panelFormulario.Controls.Add(this.lblCinta);
            this.panelFormulario.Controls.Add(this.txtPeito);
            this.panelFormulario.Controls.Add(this.lblPeito);
            this.panelFormulario.Controls.Add(this.txtCostas);
            this.panelFormulario.Controls.Add(this.lblCostas);
            this.panelFormulario.Controls.Add(this.txtBraco);
            this.panelFormulario.Controls.Add(this.lblBraco);
            this.panelFormulario.Controls.Add(this.dtpData);
            this.panelFormulario.Controls.Add(this.lblData);
            this.panelFormulario.Controls.Add(this.cmbCliente);
            this.panelFormulario.Controls.Add(this.lblCliente);
            this.panelFormulario.Controls.Add(this.txtNomePerfil);
            this.panelFormulario.Controls.Add(this.lblNomePerfil);
            this.panelFormulario.Controls.Add(this.txtId);
            this.panelFormulario.Controls.Add(this.lblId);
            this.panelFormulario.Controls.Add(this.lblFormTitulo);
            this.panelFormulario.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelFormulario.Location = new System.Drawing.Point(20, 20);
            this.panelFormulario.Name = "panelFormulario";
            this.panelFormulario.Padding = new System.Windows.Forms.Padding(20);
            this.panelFormulario.Size = new System.Drawing.Size(350, 610);
            this.panelFormulario.TabIndex = 0;
            // 
            // btnLimpar
            // 
            this.btnLimpar.Location = new System.Drawing.Point(180, 696);
            this.btnLimpar.Name = "btnLimpar";
            this.btnLimpar.Size = new System.Drawing.Size(130, 34);
            this.btnLimpar.TabIndex = 4;
            this.btnLimpar.Text = "Limpar";
            this.btnLimpar.UseVisualStyleBackColor = true;
            this.btnLimpar.Click += new System.EventHandler(this.BtnLimpar_Click);
            // 
            // btnApagar
            // 
            this.btnApagar.Location = new System.Drawing.Point(20, 696);
            this.btnApagar.Name = "btnApagar";
            this.btnApagar.Size = new System.Drawing.Size(130, 34);
            this.btnApagar.TabIndex = 3;
            this.btnApagar.Text = "Apagar";
            this.btnApagar.UseVisualStyleBackColor = true;
            this.btnApagar.Click += new System.EventHandler(this.BtnApagar_Click);
            // 
            // btnEditar
            // 
            this.btnEditar.Location = new System.Drawing.Point(180, 651);
            this.btnEditar.Name = "btnEditar";
            this.btnEditar.Size = new System.Drawing.Size(130, 34);
            this.btnEditar.TabIndex = 2;
            this.btnEditar.Text = "Editar";
            this.btnEditar.UseVisualStyleBackColor = true;
            this.btnEditar.Click += new System.EventHandler(this.BtnEditar_Click);
            // 
            // btnGuardar
            // 
            this.btnGuardar.Location = new System.Drawing.Point(20, 651);
            this.btnGuardar.Name = "btnGuardar";
            this.btnGuardar.Size = new System.Drawing.Size(130, 34);
            this.btnGuardar.TabIndex = 1;
            this.btnGuardar.Text = "Guardar";
            this.btnGuardar.UseVisualStyleBackColor = true;
            this.btnGuardar.Click += new System.EventHandler(this.BtnGuardar_Click);
            // 
            // txtAnca
            // 
            this.txtAnca.Location = new System.Drawing.Point(20, 609);
            this.txtAnca.Name = "txtAnca";
            this.txtAnca.Size = new System.Drawing.Size(300, 30);
            this.txtAnca.TabIndex = 8;
            // 
            // lblAnca
            // 
            this.lblAnca.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblAnca.Location = new System.Drawing.Point(20, 587);
            this.lblAnca.Name = "lblAnca";
            this.lblAnca.Size = new System.Drawing.Size(300, 20);
            this.lblAnca.TabIndex = 9;
            this.lblAnca.Text = "Anca";
            // 
            // txtCinta
            // 
            this.txtCinta.Location = new System.Drawing.Point(20, 545);
            this.txtCinta.Name = "txtCinta";
            this.txtCinta.Size = new System.Drawing.Size(300, 30);
            this.txtCinta.TabIndex = 7;
            // 
            // lblCinta
            // 
            this.lblCinta.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblCinta.Location = new System.Drawing.Point(20, 523);
            this.lblCinta.Name = "lblCinta";
            this.lblCinta.Size = new System.Drawing.Size(300, 20);
            this.lblCinta.TabIndex = 10;
            this.lblCinta.Text = "Cinta";
            // 
            // txtPeito
            // 
            this.txtPeito.Location = new System.Drawing.Point(20, 481);
            this.txtPeito.Name = "txtPeito";
            this.txtPeito.Size = new System.Drawing.Size(300, 30);
            this.txtPeito.TabIndex = 6;
            // 
            // lblPeito
            // 
            this.lblPeito.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblPeito.Location = new System.Drawing.Point(20, 459);
            this.lblPeito.Name = "lblPeito";
            this.lblPeito.Size = new System.Drawing.Size(300, 20);
            this.lblPeito.TabIndex = 11;
            this.lblPeito.Text = "Peito";
            // 
            // txtCostas
            // 
            this.txtCostas.Location = new System.Drawing.Point(20, 417);
            this.txtCostas.Name = "txtCostas";
            this.txtCostas.Size = new System.Drawing.Size(300, 30);
            this.txtCostas.TabIndex = 5;
            // 
            // lblCostas
            // 
            this.lblCostas.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblCostas.Location = new System.Drawing.Point(20, 395);
            this.lblCostas.Name = "lblCostas";
            this.lblCostas.Size = new System.Drawing.Size(300, 20);
            this.lblCostas.TabIndex = 12;
            this.lblCostas.Text = "Costas";
            // 
            // txtBraco
            // 
            this.txtBraco.Location = new System.Drawing.Point(20, 353);
            this.txtBraco.Name = "txtBraco";
            this.txtBraco.Size = new System.Drawing.Size(300, 30);
            this.txtBraco.TabIndex = 4;
            // 
            // lblBraco
            // 
            this.lblBraco.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblBraco.Location = new System.Drawing.Point(20, 331);
            this.lblBraco.Name = "lblBraco";
            this.lblBraco.Size = new System.Drawing.Size(300, 20);
            this.lblBraco.TabIndex = 13;
            this.lblBraco.Text = "Braco";
            // 
            // dtpData
            // 
            this.dtpData.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpData.Location = new System.Drawing.Point(20, 289);
            this.dtpData.Name = "dtpData";
            this.dtpData.Size = new System.Drawing.Size(300, 30);
            this.dtpData.TabIndex = 3;
            // 
            // lblData
            // 
            this.lblData.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblData.Location = new System.Drawing.Point(20, 267);
            this.lblData.Name = "lblData";
            this.lblData.Size = new System.Drawing.Size(300, 20);
            this.lblData.TabIndex = 14;
            this.lblData.Text = "Data da medicao";
            // 
            // cmbCliente
            // 
            this.cmbCliente.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbCliente.FormattingEnabled = true;
            this.cmbCliente.Location = new System.Drawing.Point(20, 225);
            this.cmbCliente.Name = "cmbCliente";
            this.cmbCliente.Size = new System.Drawing.Size(300, 31);
            this.cmbCliente.TabIndex = 2;
            // 
            // lblCliente
            // 
            this.lblCliente.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblCliente.Location = new System.Drawing.Point(20, 203);
            this.lblCliente.Name = "lblCliente";
            this.lblCliente.Size = new System.Drawing.Size(300, 20);
            this.lblCliente.TabIndex = 18;
            this.lblCliente.Text = "Nome do Cliente";
            // 
            // txtNomePerfil
            // 
            this.txtNomePerfil.Location = new System.Drawing.Point(20, 161);
            this.txtNomePerfil.Name = "txtNomePerfil";
            this.txtNomePerfil.Size = new System.Drawing.Size(300, 30);
            this.txtNomePerfil.TabIndex = 1;
            // 
            // lblNomePerfil
            // 
            this.lblNomePerfil.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblNomePerfil.Location = new System.Drawing.Point(20, 139);
            this.lblNomePerfil.Name = "lblNomePerfil";
            this.lblNomePerfil.Size = new System.Drawing.Size(300, 20);
            this.lblNomePerfil.TabIndex = 15;
            this.lblNomePerfil.Text = "Nome do perfil";
            // 
            // txtId
            // 
            this.txtId.Location = new System.Drawing.Point(20, 97);
            this.txtId.Name = "txtId";
            this.txtId.ReadOnly = true;
            this.txtId.Size = new System.Drawing.Size(300, 30);
            this.txtId.TabIndex = 0;
            // 
            // lblId
            // 
            this.lblId.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(60)))), ((int)(((byte)(60)))), ((int)(((byte)(60)))));
            this.lblId.Location = new System.Drawing.Point(20, 75);
            this.lblId.Name = "lblId";
            this.lblId.Size = new System.Drawing.Size(300, 20);
            this.lblId.TabIndex = 16;
            this.lblId.Text = "ID Perfil";
            // 
            // lblFormTitulo
            // 
            this.lblFormTitulo.Font = new System.Drawing.Font("Segoe UI", 15F, System.Drawing.FontStyle.Bold);
            this.lblFormTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(40)))), ((int)(((byte)(40)))), ((int)(((byte)(40)))));
            this.lblFormTitulo.Location = new System.Drawing.Point(20, 20);
            this.lblFormTitulo.Name = "lblFormTitulo";
            this.lblFormTitulo.Size = new System.Drawing.Size(290, 35);
            this.lblFormTitulo.TabIndex = 17;
            this.lblFormTitulo.Text = "Dados da Medida";
            // 
            // FrmMedidas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 23F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1083, 650);
            this.Controls.Add(this.panelBody);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.MinimumSize = new System.Drawing.Size(900, 600);
            this.Name = "FrmMedidas";
            this.Text = "Perfis de Medida";
            this.panelBody.ResumeLayout(false);
            this.panelDireita.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvMedidas)).EndInit();
            this.panelPesquisa.ResumeLayout(false);
            this.panelFormulario.ResumeLayout(false);
            this.panelFormulario.PerformLayout();
            this.ResumeLayout(false);

        }
    }
}
