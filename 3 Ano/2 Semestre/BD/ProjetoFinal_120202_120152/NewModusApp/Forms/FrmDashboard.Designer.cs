namespace NewModusApp.Forms
{
    partial class FrmDashboard
    {
        private System.ComponentModel.IContainer components = null;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        private void InitializeComponent()
        {
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();

            this.tableLayoutCards = new System.Windows.Forms.TableLayoutPanel();
            this.panelCardFaturacao = new System.Windows.Forms.Panel();
            this.lblTituloFaturacao = new System.Windows.Forms.Label();
            this.lblValorFaturacao = new System.Windows.Forms.Label();
            this.panelCardEncomendas = new System.Windows.Forms.Panel();
            this.lblTituloEncomendas = new System.Windows.Forms.Label();
            this.lblValorEncomendas = new System.Windows.Forms.Label();
            this.panelCardStock = new System.Windows.Forms.Panel();
            this.lblTituloStock = new System.Windows.Forms.Label();
            this.lblValorStock = new System.Windows.Forms.Label();
            this.panelFiltroDatas = new System.Windows.Forms.Panel();
            this.flowFiltroDatas = new System.Windows.Forms.FlowLayoutPanel();
            this.chkFiltroDatas = new System.Windows.Forms.CheckBox();
            this.lblDe = new System.Windows.Forms.Label();
            this.dtpInicio = new System.Windows.Forms.DateTimePicker();
            this.lblAte = new System.Windows.Forms.Label();
            this.dtpFim = new System.Windows.Forms.DateTimePicker();
            this.tableLayoutInferior = new System.Windows.Forms.TableLayoutPanel();
            this.panelAlertas = new System.Windows.Forms.Panel();
            this.lblTituloAlertas = new System.Windows.Forms.Label();
            this.dgvAlertasStock = new System.Windows.Forms.DataGridView();
            this.panelGrafico = new System.Windows.Forms.Panel();
            this.lblTituloGrafico = new System.Windows.Forms.Label();
            this.chartVendas = new System.Windows.Forms.DataVisualization.Charting.Chart();

            this.tableLayoutCards.SuspendLayout();
            this.panelCardFaturacao.SuspendLayout();
            this.panelCardEncomendas.SuspendLayout();
            this.panelCardStock.SuspendLayout();
            this.panelFiltroDatas.SuspendLayout();
            this.flowFiltroDatas.SuspendLayout();
            this.tableLayoutInferior.SuspendLayout();
            this.panelAlertas.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvAlertasStock)).BeginInit();
            this.panelGrafico.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.chartVendas)).BeginInit();
            this.SuspendLayout();

            // 
            // tableLayoutCards
            // 
            this.tableLayoutCards.BackColor = System.Drawing.Color.White;
            this.tableLayoutCards.ColumnCount = 3;
            this.tableLayoutCards.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33F));
            this.tableLayoutCards.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33F));
            this.tableLayoutCards.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.34F));
            this.tableLayoutCards.Controls.Add(this.panelCardFaturacao, 0, 0);
            this.tableLayoutCards.Controls.Add(this.panelCardEncomendas, 1, 0);
            this.tableLayoutCards.Controls.Add(this.panelCardStock, 2, 0);
            this.tableLayoutCards.Dock = System.Windows.Forms.DockStyle.Top;
            this.tableLayoutCards.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutCards.Name = "tableLayoutCards";
            this.tableLayoutCards.Padding = new System.Windows.Forms.Padding(10);
            this.tableLayoutCards.RowCount = 1;
            this.tableLayoutCards.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutCards.Size = new System.Drawing.Size(800, 130);
            this.tableLayoutCards.TabIndex = 0;
            // 
            // panelCardFaturacao
            // 
            this.panelCardFaturacao.BackColor = System.Drawing.Color.FromArgb(212, 237, 218);
            this.panelCardFaturacao.Controls.Add(this.lblValorFaturacao);
            this.panelCardFaturacao.Controls.Add(this.lblTituloFaturacao);
            this.panelCardFaturacao.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelCardFaturacao.Margin = new System.Windows.Forms.Padding(5);
            this.panelCardFaturacao.Name = "panelCardFaturacao";
            this.panelCardFaturacao.Padding = new System.Windows.Forms.Padding(12);
            this.panelCardFaturacao.TabIndex = 0;
            // 
            // lblTituloFaturacao
            // 
            this.lblTituloFaturacao.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblTituloFaturacao.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblTituloFaturacao.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblTituloFaturacao.Name = "lblTituloFaturacao";
            this.lblTituloFaturacao.Size = new System.Drawing.Size(200, 22);
            this.lblTituloFaturacao.TabIndex = 0;
            this.lblTituloFaturacao.Text = "Faturação do Mês";
            this.lblTituloFaturacao.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblValorFaturacao
            // 
            this.lblValorFaturacao.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lblValorFaturacao.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblValorFaturacao.ForeColor = System.Drawing.Color.FromArgb(30, 100, 50);
            this.lblValorFaturacao.Name = "lblValorFaturacao";
            this.lblValorFaturacao.TabIndex = 1;
            this.lblValorFaturacao.Text = "0,00 €";
            this.lblValorFaturacao.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelCardEncomendas
            // 
            this.panelCardEncomendas.BackColor = System.Drawing.Color.FromArgb(204, 229, 255);
            this.panelCardEncomendas.Controls.Add(this.lblValorEncomendas);
            this.panelCardEncomendas.Controls.Add(this.lblTituloEncomendas);
            this.panelCardEncomendas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelCardEncomendas.Margin = new System.Windows.Forms.Padding(5);
            this.panelCardEncomendas.Name = "panelCardEncomendas";
            this.panelCardEncomendas.Padding = new System.Windows.Forms.Padding(12);
            this.panelCardEncomendas.TabIndex = 1;
            // 
            // lblTituloEncomendas
            // 
            this.lblTituloEncomendas.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblTituloEncomendas.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblTituloEncomendas.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblTituloEncomendas.Name = "lblTituloEncomendas";
            this.lblTituloEncomendas.Size = new System.Drawing.Size(200, 22);
            this.lblTituloEncomendas.TabIndex = 0;
            this.lblTituloEncomendas.Text = "Encomendas Ativas";
            this.lblTituloEncomendas.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblValorEncomendas
            // 
            this.lblValorEncomendas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lblValorEncomendas.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblValorEncomendas.ForeColor = System.Drawing.Color.FromArgb(0, 70, 140);
            this.lblValorEncomendas.Name = "lblValorEncomendas";
            this.lblValorEncomendas.TabIndex = 1;
            this.lblValorEncomendas.Text = "0";
            this.lblValorEncomendas.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelCardStock
            // 
            this.panelCardStock.BackColor = System.Drawing.Color.FromArgb(255, 217, 217);
            this.panelCardStock.Controls.Add(this.lblValorStock);
            this.panelCardStock.Controls.Add(this.lblTituloStock);
            this.panelCardStock.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelCardStock.Margin = new System.Windows.Forms.Padding(5);
            this.panelCardStock.Name = "panelCardStock";
            this.panelCardStock.Padding = new System.Windows.Forms.Padding(12);
            this.panelCardStock.TabIndex = 2;
            // 
            // lblTituloStock
            // 
            this.lblTituloStock.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblTituloStock.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblTituloStock.ForeColor = System.Drawing.Color.FromArgb(60, 60, 60);
            this.lblTituloStock.Name = "lblTituloStock";
            this.lblTituloStock.Size = new System.Drawing.Size(200, 22);
            this.lblTituloStock.TabIndex = 0;
            this.lblTituloStock.Text = "Peças em Stock";
            this.lblTituloStock.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblValorStock
            // 
            this.lblValorStock.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lblValorStock.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblValorStock.ForeColor = System.Drawing.Color.FromArgb(150, 30, 30);
            this.lblValorStock.Name = "lblValorStock";
            this.lblValorStock.TabIndex = 1;
            this.lblValorStock.Text = "0";
            this.lblValorStock.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelFiltroDatas
            // 
            this.panelFiltroDatas.BackColor = System.Drawing.Color.FromArgb(245, 245, 245);
            this.panelFiltroDatas.Controls.Add(this.flowFiltroDatas);
            this.panelFiltroDatas.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelFiltroDatas.Name = "panelFiltroDatas";
            this.panelFiltroDatas.Padding = new System.Windows.Forms.Padding(10, 0, 10, 0);
            this.panelFiltroDatas.Size = new System.Drawing.Size(800, 36);
            this.panelFiltroDatas.TabIndex = 3;
            // 
            // flowFiltroDatas
            // 
            this.flowFiltroDatas.Controls.Add(this.chkFiltroDatas);
            this.flowFiltroDatas.Controls.Add(this.lblDe);
            this.flowFiltroDatas.Controls.Add(this.dtpInicio);
            this.flowFiltroDatas.Controls.Add(this.lblAte);
            this.flowFiltroDatas.Controls.Add(this.dtpFim);
            this.flowFiltroDatas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flowFiltroDatas.FlowDirection = System.Windows.Forms.FlowDirection.LeftToRight;
            this.flowFiltroDatas.Name = "flowFiltroDatas";
            this.flowFiltroDatas.WrapContents = false;
            this.flowFiltroDatas.TabIndex = 0;
            // 
            // chkFiltroDatas
            // 
            this.chkFiltroDatas.AutoSize = true;
            this.chkFiltroDatas.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.chkFiltroDatas.Margin = new System.Windows.Forms.Padding(0, 8, 8, 0);
            this.chkFiltroDatas.Name = "chkFiltroDatas";
            this.chkFiltroDatas.TabIndex = 0;
            this.chkFiltroDatas.Text = "Intervalo Personalizado";
            this.chkFiltroDatas.CheckedChanged += new System.EventHandler(this.ChkFiltroDatas_CheckedChanged);
            // 
            // lblDe
            // 
            this.lblDe.AutoSize = true;
            this.lblDe.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblDe.Margin = new System.Windows.Forms.Padding(0, 9, 4, 0);
            this.lblDe.Name = "lblDe";
            this.lblDe.TabIndex = 1;
            this.lblDe.Text = "De:";
            // 
            // dtpInicio
            // 
            this.dtpInicio.Enabled = false;
            this.dtpInicio.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpInicio.Margin = new System.Windows.Forms.Padding(0, 6, 8, 0);
            this.dtpInicio.Name = "dtpInicio";
            this.dtpInicio.Size = new System.Drawing.Size(110, 22);
            this.dtpInicio.TabIndex = 2;
            this.dtpInicio.ValueChanged += new System.EventHandler(this.DtpInicio_ValueChanged);
            // 
            // lblAte
            // 
            this.lblAte.AutoSize = true;
            this.lblAte.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblAte.Margin = new System.Windows.Forms.Padding(0, 9, 4, 0);
            this.lblAte.Name = "lblAte";
            this.lblAte.TabIndex = 3;
            this.lblAte.Text = "Até:";
            // 
            // dtpFim
            // 
            this.dtpFim.Enabled = false;
            this.dtpFim.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpFim.Margin = new System.Windows.Forms.Padding(0, 6, 0, 0);
            this.dtpFim.Name = "dtpFim";
            this.dtpFim.Size = new System.Drawing.Size(110, 22);
            this.dtpFim.TabIndex = 4;
            this.dtpFim.ValueChanged += new System.EventHandler(this.DtpFim_ValueChanged);
            // 
            // tableLayoutInferior
            // 
            this.tableLayoutInferior.ColumnCount = 2;
            this.tableLayoutInferior.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutInferior.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutInferior.Controls.Add(this.panelAlertas, 0, 0);
            this.tableLayoutInferior.Controls.Add(this.panelGrafico, 1, 0);
            this.tableLayoutInferior.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutInferior.Name = "tableLayoutInferior";
            this.tableLayoutInferior.Padding = new System.Windows.Forms.Padding(10);
            this.tableLayoutInferior.RowCount = 1;
            this.tableLayoutInferior.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutInferior.TabIndex = 1;
            // 
            // panelAlertas
            // 
            this.panelAlertas.Controls.Add(this.dgvAlertasStock);
            this.panelAlertas.Controls.Add(this.lblTituloAlertas);
            this.panelAlertas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelAlertas.Margin = new System.Windows.Forms.Padding(0, 0, 5, 0);
            this.panelAlertas.Name = "panelAlertas";
            this.panelAlertas.Padding = new System.Windows.Forms.Padding(0, 0, 8, 0);
            this.panelAlertas.TabIndex = 0;
            // 
            // lblTituloAlertas
            // 
            this.lblTituloAlertas.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblTituloAlertas.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblTituloAlertas.ForeColor = System.Drawing.Color.FromArgb(40, 40, 40);
            this.lblTituloAlertas.Name = "lblTituloAlertas";
            this.lblTituloAlertas.Size = new System.Drawing.Size(320, 30);
            this.lblTituloAlertas.TabIndex = 0;
            this.lblTituloAlertas.Text = "⚠  Alertas de Stock";
            // 
            // dgvAlertasStock
            // 
            this.dgvAlertasStock.AllowUserToAddRows = false;
            this.dgvAlertasStock.AllowUserToDeleteRows = false;
            this.dgvAlertasStock.BackgroundColor = System.Drawing.Color.White;
            this.dgvAlertasStock.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.dgvAlertasStock.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvAlertasStock.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvAlertasStock.Name = "dgvAlertasStock";
            this.dgvAlertasStock.ReadOnly = true;
            this.dgvAlertasStock.RowHeadersVisible = false;
            this.dgvAlertasStock.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvAlertasStock.TabIndex = 1;
            // 
            // panelGrafico
            // 
            this.panelGrafico.Controls.Add(this.chartVendas);
            this.panelGrafico.Controls.Add(this.lblTituloGrafico);
            this.panelGrafico.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelGrafico.Name = "panelGrafico";
            this.panelGrafico.Padding = new System.Windows.Forms.Padding(10, 0, 0, 0);
            this.panelGrafico.TabIndex = 1;
            // 
            // lblTituloGrafico
            // 
            this.lblTituloGrafico.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblTituloGrafico.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblTituloGrafico.ForeColor = System.Drawing.Color.FromArgb(40, 40, 40);
            this.lblTituloGrafico.Name = "lblTituloGrafico";
            this.lblTituloGrafico.Size = new System.Drawing.Size(400, 30);
            this.lblTituloGrafico.TabIndex = 0;
            this.lblTituloGrafico.Text = "Vendas por Categoria";
            // 
            // chartVendas
            // 
            chartArea1.Name = "ChartArea1";
            chartArea1.BackColor = System.Drawing.Color.White;
            chartArea1.AxisX.MajorGrid.Enabled = false;
            chartArea1.AxisX.LineColor = System.Drawing.Color.FromArgb(200, 200, 200);
            chartArea1.AxisX.LabelStyle.Font = new System.Drawing.Font("Segoe UI", 9F);
            chartArea1.AxisY.MajorGrid.LineColor = System.Drawing.Color.FromArgb(220, 220, 220);
            chartArea1.AxisY.LineColor = System.Drawing.Color.FromArgb(200, 200, 200);
            chartArea1.AxisY.LabelStyle.Font = new System.Drawing.Font("Segoe UI", 9F);
            chartArea1.InnerPlotPosition.Auto = false;
            chartArea1.InnerPlotPosition.X = 8F;
            chartArea1.InnerPlotPosition.Y = 5F;
            chartArea1.InnerPlotPosition.Width = 87F;
            chartArea1.InnerPlotPosition.Height = 85F;
            this.chartVendas.ChartAreas.Add(chartArea1);
            series1.ChartArea = "ChartArea1";
            series1.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Column;
            series1.Name = "Vendas";
            series1.Color = System.Drawing.Color.FromArgb(70, 130, 180);
            this.chartVendas.Series.Add(series1);
            this.chartVendas.BackColor = System.Drawing.Color.White;
            this.chartVendas.BorderlineColor = System.Drawing.Color.Transparent;
            this.chartVendas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.chartVendas.Name = "chartVendas";
            this.chartVendas.Padding = new System.Windows.Forms.Padding(0);
            this.chartVendas.TabIndex = 1;
            // 
            // FrmDashboard
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(800, 566);
            this.Controls.Add(this.tableLayoutInferior);
            this.Controls.Add(this.panelFiltroDatas);
            this.Controls.Add(this.tableLayoutCards);
            this.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.Name = "FrmDashboard";
            this.Text = "Dashboard";
            this.Load += new System.EventHandler(this.FrmDashboard_Load);

            this.tableLayoutCards.ResumeLayout(false);
            this.panelCardFaturacao.ResumeLayout(false);
            this.panelCardEncomendas.ResumeLayout(false);
            this.panelCardStock.ResumeLayout(false);
            this.panelFiltroDatas.ResumeLayout(false);
            this.flowFiltroDatas.ResumeLayout(false);
            this.flowFiltroDatas.PerformLayout();
            this.tableLayoutInferior.ResumeLayout(false);
            this.panelAlertas.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvAlertasStock)).EndInit();
            this.panelGrafico.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.chartVendas)).EndInit();
            this.ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutCards;
        private System.Windows.Forms.Panel panelCardFaturacao;
        private System.Windows.Forms.Label lblTituloFaturacao;
        private System.Windows.Forms.Label lblValorFaturacao;
        private System.Windows.Forms.Panel panelCardEncomendas;
        private System.Windows.Forms.Label lblTituloEncomendas;
        private System.Windows.Forms.Label lblValorEncomendas;
        private System.Windows.Forms.Panel panelCardStock;
        private System.Windows.Forms.Label lblTituloStock;
        private System.Windows.Forms.Label lblValorStock;
        private System.Windows.Forms.TableLayoutPanel tableLayoutInferior;
        private System.Windows.Forms.Panel panelAlertas;
        private System.Windows.Forms.Label lblTituloAlertas;
        private System.Windows.Forms.DataGridView dgvAlertasStock;
        private System.Windows.Forms.Panel panelGrafico;
        private System.Windows.Forms.Label lblTituloGrafico;
        private System.Windows.Forms.DataVisualization.Charting.Chart chartVendas;
        private System.Windows.Forms.Panel panelFiltroDatas;
        private System.Windows.Forms.FlowLayoutPanel flowFiltroDatas;
        private System.Windows.Forms.CheckBox chkFiltroDatas;
        private System.Windows.Forms.Label lblDe;
        private System.Windows.Forms.DateTimePicker dtpInicio;
        private System.Windows.Forms.Label lblAte;
        private System.Windows.Forms.DateTimePicker dtpFim;
    }
}