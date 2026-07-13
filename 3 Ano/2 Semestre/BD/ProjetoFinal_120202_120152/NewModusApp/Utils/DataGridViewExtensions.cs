using System.Drawing;
using System.Windows.Forms;

namespace NewModusApp.Utils
{
    public static class DataGridViewExtensions
    {
        public static void AplicarEstiloModus(this DataGridView dgv)
        {
            if (dgv == null) return;

            // 1. Configurações gerais de comportamento e grelha
            dgv.EnableHeadersVisualStyles = false;
            dgv.BackgroundColor = Color.White;
            dgv.BorderStyle = BorderStyle.None;
            dgv.GridColor = Color.Gainsboro;
            dgv.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dgv.MultiSelect = false;

            // Bloquear redimensionamento manual pelo utilizador
            dgv.AllowUserToResizeColumns = false;
            dgv.AllowUserToResizeRows = false;

            // CRÍTICO: Remove a coluna cinzenta vazia de seletores à esquerda
            dgv.RowHeadersVisible = false;

            // 2. Padronização Absoluta dos Cabeçalhos (Identificadores de Coluna)
            dgv.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.DisableResizing;
            dgv.ColumnHeadersHeight = 45; // Altura idêntica à usada nos restantes ecrãs padrão

            // Estilo visual dos cabeçalhos (Cores limpas alinhadas com a identidade da app)
            dgv.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(245, 245, 245); // Cinza claro limpo
            dgv.ColumnHeadersDefaultCellStyle.ForeColor = Color.FromArgb(40, 40, 40);    // Texto escuro legível
            dgv.ColumnHeadersDefaultCellStyle.Font = new Font("Segoe UI", 10, FontStyle.Bold);
            dgv.ColumnHeadersDefaultCellStyle.SelectionBackColor = Color.FromArgb(245, 245, 245);
            dgv.ColumnHeadersDefaultCellStyle.SelectionForeColor = Color.FromArgb(40, 40, 40);

            // 3. Cor padrão para a linha selecionada (Cinza Claro de Destaque)
            dgv.RowsDefaultCellStyle.SelectionBackColor = Color.FromArgb(240, 240, 240);
            dgv.RowsDefaultCellStyle.SelectionForeColor = Color.Black;

            // 4. Limpar resíduos das colunas para herdarem perfeitamente o estilo da linha
            foreach (DataGridViewColumn col in dgv.Columns)
            {
                col.DefaultCellStyle.SelectionBackColor = Color.Empty;
                col.DefaultCellStyle.SelectionForeColor = Color.Empty;
            }

            // 5. Abordagem dinâmica usando CellFormatting para focar a célula ativa
            dgv.CellFormatting -= Dgv_CellFormatting;
            dgv.CellFormatting += Dgv_CellFormatting;
        }

        private static void Dgv_CellFormatting(object sender, DataGridViewCellFormattingEventArgs e)
        {
            var dgv = (DataGridView)sender;

            // Se a célula a ser desenhada for exatamente a que o utilizador tem o cursor/foco ativo:
            if (dgv.CurrentCell != null &&
                e.RowIndex == dgv.CurrentCell.RowIndex &&
                e.ColumnIndex == dgv.CurrentCell.ColumnIndex &&
                dgv.Rows[e.RowIndex].Selected)
            {
                // Destaque na célula focada (Cinza ligeiramente mais escuro, mantendo contraste)
                e.CellStyle.SelectionBackColor = Color.FromArgb(200, 200, 200);
                e.CellStyle.SelectionForeColor = Color.Black;
            }
        }
    }
}