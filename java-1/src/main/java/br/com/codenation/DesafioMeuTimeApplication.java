package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private List<Time> listaDeTimes = new ArrayList<>();
	private List<Jogador> listaDeJogadores = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if(buscarTime(id) != null) throw new IdentificadorUtilizadoException();
		Time novoTime = new Time(id,nome,dataCriacao,corUniformePrincipal,corUniformeSecundario);
		this.listaDeTimes.add(novoTime);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (buscarJogador(id) != null) throw new IdentificadorUtilizadoException();
		if (buscarTime(idTime) == null) throw new TimeNaoEncontradoException();
		Jogador novoJogador = new Jogador(id,idTime,nome, dataNascimento, nivelHabilidade, salario);
		this.listaDeJogadores.add(novoJogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);
		if(jogador == null) throw new JogadorNaoEncontradoException();

		Time time = buscarTime(jogador.getIdTime());
		if(time == null) throw new TimeNaoEncontradoException();

		time.setIdCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = buscarTime(idTime);
		if (time == null) throw new TimeNaoEncontradoException();
		if (time.getIdCapitao() == null) throw new CapitaoNaoInformadoException();
		return time.getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);
		if (jogador == null) throw new JogadorNaoEncontradoException();
		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = buscarTime(idTime);
		if (time == null) throw new TimeNaoEncontradoException();
		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if(buscarTime(idTime) == null) throw new TimeNaoEncontradoException();

		return listaDeJogadores.stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.map(j -> j.getId())
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if(buscarTime(idTime) == null) throw new TimeNaoEncontradoException();

		return listaDeJogadores.stream()
				.filter(x -> x.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getNivelHabilidade))
				.map(Jogador::getId)
				.get();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if(buscarTime(idTime) == null) throw new TimeNaoEncontradoException();

		return listaDeJogadores.stream()
				.filter(x -> x.getIdTime().equals(idTime))
				.min(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Comparator.comparing(Jogador::getId)))
				.map(Jogador::getId)
				.get();

	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
	    return listaDeTimes.stream().map(t -> t.getId()).sorted().collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if(buscarTime(idTime) == null) throw new TimeNaoEncontradoException();

		return listaDeJogadores.stream()
				.filter(x -> x.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getSalario))
				.map(Jogador::getId)
				.get();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = buscarJogador(idJogador);
		if (jogador == null) throw new JogadorNaoEncontradoException();
		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	//not working
	public List<Long> buscarTopJogadores(Integer top) {
		return listaDeJogadores.stream()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
				.limit(top)
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time casa = buscarTime(timeDaCasa);
		Time fora = buscarTime(timeDeFora);

		if (casa == null || fora == null) throw new TimeNaoEncontradoException();
		if(casa.getCorUniformePrincipal().equals(fora.getCorUniformePrincipal())) return fora.getCorUniformeSecundario();
		return fora.getCorUniformePrincipal();
	}

	public Time buscarTime(Long idTime) {
		return listaDeTimes.stream().filter(t -> t.getId().equals(idTime)).findFirst().orElse(null);
	}

	public Jogador buscarJogador(Long idJogador) {
		return listaDeJogadores.stream().filter(t -> t.getId().equals(idJogador)).findFirst().orElse(null);
	}

}
