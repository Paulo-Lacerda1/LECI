using System;
using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Forms
{
    partial class FrmVerMateriaisPedidoPopup
    {
        private System.ComponentModel.IContainer components = null;
        private Label lblTitulo;
        private DataGridView dgv;
        private Label lblVazio;
        private Button btnFechar;

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
            this.lblTitulo = new System.Windows.Forms.Label();
            this.dgv = new System.Windows.Forms.DataGridView();
            this.lblVazio = new System.Windows.Forms.Label();
            this.btnFechar = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgv)).BeginInit();
            this.SuspendLayout();
            // 
            // FrmVerMateriaisPedidoPopup
            // 
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(560, 360);
            this.Controls.Add(this.btnFechar);
            this.Controls.Add(this.lblVazio);
            this.Controls.Add(this.dgv);
            this.Controls.Add(this.lblTitulo);
            this.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "FrmVerMateriaisPedidoPopup";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Materiais usados";
            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = false;
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.lblTitulo.Location = new System.Drawing.Point(20, 16);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(500, 32);
            this.lblTitulo.TabIndex = 0;
            this.lblTitulo.Text = "Materiais usados neste produto";
            // 
            // dgv
            // 
            this.dgv.AllowUserToAddRows = false;
            this.dgv.AllowUserToDeleteRows = false;
            this.dgv.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgv.Location = new System.Drawing.Point(20, 62);
            this.dgv.MultiSelect = false;
            this.dgv.Name = "dgv";
            this.dgv.ReadOnly = true;
            this.dgv.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgv.Size = new System.Drawing.Size(520, 230);
            this.dgv.TabIndex = 1;
            this.dgv.DataSource = null;
            // 
            // lblVazio
            // 
            this.lblVazio.AutoSize = false;
            this.lblVazio.ForeColor = System.Drawing.Color.FromArgb(90, 90, 90);
            this.lblVazio.Location = new System.Drawing.Point(20, 142);
            this.lblVazio.Name = "lblVazio";
            this.lblVazio.Size = new System.Drawing.Size(520, 30);
            this.lblVazio.TabIndex = 2;
            this.lblVazio.Text = "Ainda nao existem tecidos ou materiais registados.";
            this.lblVazio.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.lblVazio.Visible = true;
            // 
            // btnFechar
            // 
            this.btnFechar.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.btnFechar.Location = new System.Drawing.Point(425, 310);
            this.btnFechar.Name = "btnFechar";
            this.btnFechar.Size = new System.Drawing.Size(115, 34);
            this.btnFechar.TabIndex = 3;
            this.btnFechar.Text = "Fechar";
            this.btnFechar.UseVisualStyleBackColor = true;
            // 
            // FrmVerMateriaisPedidoPopup
            // 
            this.ResumeLayout(false);
        }
    }
}
