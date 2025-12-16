import React, { useState } from 'react';
import '../styles/Avaliacao.css';
import logo from '../assets/logo_transparente.png';
import { FaStar, FaStarHalfAlt, FaRegStar } from 'react-icons/fa';
import logoThompson from '../assets/logo_thompson.png';


const avaliacoes = [
  { nome: 'João Santos', comentario: 'Serviço excelente!', nota: 5 },
  { nome: 'Maria Ferreira', comentario: 'Atendimento rápido e eficiente.', nota: 5 },
  { nome: 'Carlos Lima', comentario: 'Recomendo vivamente!', nota: 5 },
  { nome: 'Ana Costa', comentario: 'Bom, mas podia ser melhor.', nota: 4 },
  { nome: 'Rui Silva', comentario: 'Muito simpáticos e profissionais.', nota: 5 },
  { nome: 'Paula Marques', comentario: 'Adorei a limpeza.', nota: 5 },
  { nome: 'André Lourenço', comentario: 'Poderia ter sido mais rápido.', nota: 4 },
  { nome: 'Bruno Almeida', comentario: 'Atendimento impecável!', nota: 5 },
  { nome: 'Inês Moreira', comentario: 'Serviço razoável, pode melhorar.', nota: 4 },
  { nome: 'Miguel Pinto', comentario: 'Rapidez e qualidade, recomendo.', nota: 5 },
  { nome: 'Helena Duarte', comentario: 'Nada a apontar, voltarei de certeza.', nota: 5 },
  { nome: 'Tiago Fonseca', comentario: 'Demorou mais do que o previsto.', nota: 3 }, // compensada
  { nome: 'Sofia Ramos', comentario: 'Profissionais muito competentes.', nota: 5 },
  { nome: 'Pedro Martins', comentario: 'Bom serviço pelo preço pago.', nota: 5 },
  { nome: 'Catarina Neves', comentario: 'Superou minhas expectativas!', nota: 5 },
  { nome: 'Diana Cardoso', comentario: 'Atendimento perfeito, 5 estrelas!', nota: 5 },
  { nome: 'João Miguel', comentario: 'Tinha mais expectativas do serviço', nota: 3 }, // compensada
  { nome: 'Ricardo Teixeira', comentario: 'Satisfeito com o resultado.', nota: 4 },
  { nome: 'Beatriz Gomes', comentario: 'Não tenho queixas, muito bom.', nota: 5 },
  { nome: 'Hugo Fernandes', comentario: 'Não gostei do atendimento.', nota: 2 } // avaliação com nota 2
];



const total = avaliacoes.length;
const media = Number(
  (avaliacoes.reduce((acc, curr) => acc + curr.nota, 0) / total).toFixed(1)
);


const contarNotas = (n) => avaliacoes.filter((a) => a.nota === n).length;

const Estrelas = ({ valor }) => {
  const estrelas = [];

  for (let i = 1; i <= 5; i++) {
    if (valor >= i) {
      estrelas.push(<FaStar key={i} />);
    } else if (valor >= i - 0.5) {
      estrelas.push(<FaStarHalfAlt key={i} />);
    } else {
      estrelas.push(<FaRegStar key={i} />);
    }
  }

  return <div className="estrelas">{estrelas}</div>;
};

const Avaliacao = () => {
  const [mostrarTodas, setMostrarTodas] = useState(false);
  const avaliacoesVisiveis = mostrarTodas ? avaliacoes : avaliacoes.slice(0, 10);

  return (
  <div className="pagina-avaliacoes">
    <div className="avaliacoes-wrapper">
      <header className="topo">
        <img src={logo} alt="Logo AutoHub" className="logo" />
        <div className="titulos">
          <h1>Avaliações dos Clientes</h1>
          <h3>Thompson Car Services</h3>
        </div>
        <img src={logoThompson} alt="Logo Thompson Car Services" className="logo-thompson" />
      </header>

      <main className="conteudo">
        <section className="estatisticas">
          <div className="media">
            <div className="media-valor-estrelas">
              <h2>{media}</h2>
              <Estrelas valor={parseFloat(media)} />
            </div>
            <p>Média Geral</p>
          </div>

          <div className="barra-avaliacoes">
            {[5, 4, 3, 2, 1].map((n) => (
              <div className="linha" key={n}>
                <span>{n} ⭐</span>
                <div className="barra">
                  <div
                    className="preenchido"
                    style={{ width: `${(contarNotas(n) / total) * 100}%` }}
                  ></div>
                </div>
                <span>{contarNotas(n)}</span>
              </div>
            ))}
          </div>
        </section>

        <section className="lista">
          <h2>Avaliações Recentes</h2>
          <div className="cartoes">
            {avaliacoesVisiveis.map((a, i) => (
              <div key={i} className="cartao">
                <div className="nota">{a.nota} ⭐</div>
                <div className="conteudo-cartao">
                  <strong>{a.nome}</strong>
                  <p>{a.comentario}</p>
                </div>
              </div>
            ))}
          </div>
          <button
            className="botao-ver-mais"
            onClick={() => setMostrarTodas(!mostrarTodas)}
          >
            {mostrarTodas ? 'Ver menos' : 'Ver mais'}
          </button>
        </section>
      </main>
    </div>
  </div>
);
}
export default Avaliacao;
