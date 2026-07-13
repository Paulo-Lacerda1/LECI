using NewModusApp.Data;
using System;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Windows.Forms;
using NewModusApp.Utils;

namespace NewModusApp.Forms
{
    public partial class FrmClientes : Form
    {
        public FrmClientes()
        {
            InitializeComponent();
            CarregarClientes();
        }

        private void DgvClientes_DataBindingComplete(object sender, DataGridViewBindingCompleteEventArgs e)
        {
            dgvClientes.ClearSelection();
        }

        private void CarregarClientes()
        {
            try
            {
                dgvClientes.DataSource = Database.ExecuteDataTable("NM.spListarClientes");
                dgvClientes.AplicarEstiloModus();
                dgvClientes.ClearSelection();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Não foi possível carregar os clientes.", ex);
            }
        }

        private void PesquisarClientes(string texto)
        {
            try
            {
                dgvClientes.DataSource = Database.ExecuteDataTable(
                    "NM.spPesquisarClientes",
                    new SqlParameter("@textoPesquisa", SqlDbType.NVarChar, 255) { Value = texto }
                );
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Não foi possível pesquisar clientes.", ex);
            }
        }

        private int CriarCliente(string nome, string telefone, string email)
        {
            SqlParameter idCliente = new SqlParameter("@id_cliente", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };

            Database.ExecuteNonQuery(
                "NM.spCriarCliente",
                new SqlParameter("@nome", SqlDbType.NVarChar, 40) { Value = nome },
                new SqlParameter("@telefone", SqlDbType.NVarChar, 20) { Value = telefone },
                new SqlParameter("@email", SqlDbType.NVarChar, 255) { Value = email },
                idCliente
            );

            return Convert.ToInt32(idCliente.Value);
        }

        private void AtualizarCliente(int idCliente, string nome, string telefone, string email)
        {
            Database.ExecuteNonQuery(
                "NM.spAtualizarCliente",
                new SqlParameter("@id_cliente", SqlDbType.Int) { Value = idCliente },
                new SqlParameter("@nome", SqlDbType.NVarChar, 40) { Value = nome },
                new SqlParameter("@telefone", SqlDbType.NVarChar, 20) { Value = telefone },
                new SqlParameter("@email", SqlDbType.NVarChar, 255) { Value = email }
            );
        }

        private void EliminarClienteCompleto(int idCliente)
        {
            Database.ExecuteNonQuery(
                "NM.spEliminarClienteCompleto",
                new SqlParameter("@id_cliente", SqlDbType.Int) { Value = idCliente }
            );
        }

        private DataRow ObterResumoEliminacaoCliente(int idCliente)
        {
            DataTable tabela = Database.ExecuteDataTable(
                "NM.spObterResumoEliminacaoCliente",
                new SqlParameter("@id_cliente", SqlDbType.Int) { Value = idCliente }
            );

            return tabela.Rows.Count > 0 ? tabela.Rows[0] : null;
        }

        private void BtnPesquisar_Click(object sender, EventArgs e)
        {
            PesquisarClientes(txtPesquisa.Text.Trim());
        }

        private void BtnGuardar_Click(object sender, EventArgs e)
        {
            string nome = txtNome.Text.Trim();
            string telefone = txtTelefone.Text.Trim();
            string email = txtEmail.Text.Trim();

            if (nome == "" || telefone == "" || email == "")
            {
                MessageBox.Show(
                    "Preenche todos os campos obrigatórios.",
                    "Campos em falta",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );

                return;
            }

            try
            {
                int idCliente = CriarCliente(nome, telefone, email);

                MessageBox.Show(
                    "Cliente inserido com sucesso. ID: " + idCliente,
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarClientes();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Não foi possível inserir o cliente.", ex);
            }
        }

        private void BtnEditar_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
            {
                MessageBox.Show(
                    "Seleciona um cliente primeiro.",
                    "Cliente não selecionado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );

                return;
            }

            int idCliente;
            if (!int.TryParse(txtId.Text, out idCliente))
            {
                MessageBox.Show(
                    "O ID do cliente selecionado não é válido.",
                    "ID inválido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );

                return;
            }

            string nome = txtNome.Text.Trim();
            string telefone = txtTelefone.Text.Trim();
            string email = txtEmail.Text.Trim();

            if (nome == "" || telefone == "" || email == "")
            {
                MessageBox.Show(
                    "Preenche todos os campos obrigatórios.",
                    "Campos em falta",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );

                return;
            }
            try
            {
                AtualizarCliente(idCliente, nome, telefone, email);

                MessageBox.Show(
                    "Cliente atualizado com sucesso.",
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarClientes();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Não foi possível atualizar o cliente.", ex);
            }
        }

        private void BtnApagar_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
            {
                MessageBox.Show(
                    "Seleciona um cliente primeiro.",
                    "Cliente não selecionado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );

                return;
            }

            int idCliente;
            if (!int.TryParse(txtId.Text, out idCliente))
            {
                MessageBox.Show(
                    "O ID do cliente selecionado não é válido.",
                    "ID inválido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );

                return;
            }

            try
            {
                DataRow resumo = ObterResumoEliminacaoCliente(idCliente);
                int totalPerfis = resumo == null ? 0 : Convert.ToInt32(resumo["PerfisMedida"]);
                int totalEncomendas = resumo == null ? 0 : Convert.ToInt32(resumo["Encomendas"]);
                int totalCompras = resumo == null ? 0 : Convert.ToInt32(resumo["Compras"]);
                int totalAjustesEncomenda = resumo == null ? 0 : Convert.ToInt32(resumo["AjustesEncomenda"]);
                int totalAjustesCompra = resumo == null ? 0 : Convert.ToInt32(resumo["AjustesCompra"]);

                string mensagem =
                    $"Tens a certeza que queres apagar este cliente?\n\n" +
                    $"Dados associados encontrados:\n" +
                    $"- Perfis de medida: {totalPerfis}\n" +
                    $"- Encomendas: {totalEncomendas}\n" +
                    $"- Compras: {totalCompras}\n" +
                    $"- Ajustes de encomendas: {totalAjustesEncomenda}\n" +
                    $"- Ajustes de compras: {totalAjustesCompra}\n\n" +
                    "Se continuares, estes dados também serão apagados.";

                DialogResult resposta = MessageBox.Show(
                    mensagem,
                    "Confirmar eliminação",
                    MessageBoxButtons.YesNo,
                    MessageBoxIcon.Warning
                );

                if (resposta != DialogResult.Yes)
                    return;

                EliminarClienteCompleto(idCliente);

                MessageBox.Show(
                    "Cliente e dados associados apagados com sucesso.",
                    "Sucesso",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                LimparCampos();
                CarregarClientes();
            }
            catch (Exception ex)
            {
                MostrarErroBaseDados("Não foi possível apagar o cliente.", ex);
            }
        }

        private void BtnLimpar_Click(object sender, EventArgs e)
        {
            LimparCampos();
        }

        private void BtnMedidas_Click(object sender, EventArgs e)
        {
            int idCliente;
            if (!int.TryParse(txtId.Text, out idCliente))
            {
                MessageBox.Show(
                    "Seleciona um cliente primeiro.",
                    "Cliente nao selecionado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );

                return;
            }

            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
            {
                principal.AbrirMedidas(idCliente);
            }
        }

        private void BtnEncomendas_Click(object sender, EventArgs e)
        {
            int idCliente;
            if (!int.TryParse(txtId.Text, out idCliente))
            {
                MessageBox.Show(
                    "Seleciona um cliente primeiro.",
                    "Cliente nao selecionado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );

                return;
            }

            FrmPrincipal principal = ObterFrmPrincipal();
            if (principal != null)
            {
                principal.AbrirEncomendas(idCliente);
            }
        }

        private FrmPrincipal ObterFrmPrincipal()
        {
            Control atual = Parent;

            while (atual != null)
            {
                FrmPrincipal principal = atual as FrmPrincipal;
                if (principal != null)
                    return principal;

                atual = atual.Parent;
            }

            return null;
        }

        private void DgvClientes_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0)
                return;

            DataGridViewRow row = dgvClientes.Rows[e.RowIndex];

            txtId.Text = row.Cells["ID"].Value.ToString();
            txtNome.Text = row.Cells["Nome"].Value.ToString();
            txtTelefone.Text = row.Cells["Telefone"].Value.ToString();
            txtEmail.Text = row.Cells["Email"].Value.ToString();
        }

        private void LimparCampos()
        {
            txtId.Clear();
            txtNome.Clear();
            txtTelefone.Clear();
            txtEmail.Clear();

            dgvClientes.ClearSelection();
        }

        private void MostrarErroBaseDados(string mensagem, Exception ex)
        {
            MessageBox.Show(
                mensagem + "\n\n" + ex.Message,
                "Erro de base de dados",
                MessageBoxButtons.OK,
                MessageBoxIcon.Error
            );
        }

    }
}
