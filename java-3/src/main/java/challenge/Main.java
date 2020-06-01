package challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public List<List<String>> records = getDados();
	public List<String> colunas = records.get(0);

	public List<List<String>> getDados() {
		List<List<String>> records = new ArrayList<>();
		try {
			FileReader file = new FileReader("src/main/resources/data.csv");
			BufferedReader reader = new BufferedReader(file);
			String line;
			while((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				records.add(Arrays.asList(values));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return records;
	}

	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		return records.stream().skip(1).collect(Collectors.groupingBy(j ->  j.get(colunas.indexOf("nationality")))).size();
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		return records.stream().skip(1).filter(j -> !j.get(colunas.indexOf("club")).isEmpty())
				.collect(Collectors.groupingBy(j ->  j.get(colunas.indexOf("club")))).size();
	}

	// Liste o nome completo (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		return records.stream().map(j -> j.get(colunas.indexOf("full_name"))).skip(1).limit(20).collect(Collectors.toList());
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		List<String> jogadoresTop10 =  records.stream().skip(1)
				.filter(j -> !j.get(colunas.indexOf("eur_release_clause")).isEmpty())
				.sorted(Comparator.comparing(j -> Double.parseDouble(j.get(colunas.indexOf("eur_release_clause")))))
				.map(j -> j.get(colunas.indexOf("full_name")))
				.collect(Collectors.toList());
		Collections.reverse(jogadoresTop10);
		return jogadoresTop10.stream().limit(10).collect(Collectors.toList());

	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		return records.stream()
				.skip(1)
				.filter(j -> !j.get(colunas.indexOf("birth_date")).isEmpty())
				.sorted(Comparator.comparing(j -> LocalDate.parse(j.get(colunas.indexOf("birth_date")))))
				.map(j -> j.get(colunas.indexOf("full_name"))).limit(10)
				.collect(Collectors.toList());
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		return records.stream().skip(1)
				.collect(Collectors.groupingBy(j -> Integer.parseInt(j.get(colunas.indexOf("age"))), Collectors.reducing(0, e -> 1, Integer::sum)));

	}

}
