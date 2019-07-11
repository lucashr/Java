package br.com.treinaweb.java.streams;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		
		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(new Empregado(1, "Joao", 2000, "Producao"));
		empregados.add(new Empregado(2, "Jose", 1999, "Controladoria"));
		empregados.add(new Empregado(3, "Rafael", 3000, "Suporte"));
		empregados.add(new Empregado(4, "Silvio", 2800, "Auxiliar administrativo"));
		
		System.out.println("** Funcionarios que começam com J **");
		Stream<Empregado> stream = empregados.stream().filter(emp -> {
			System.out.println("** Invocando o filter **");
			return emp.getNome().startsWith("J");
		});
		System.out.println("A conversao para lista sera invocado");
		List<Empregado> empregadosComJ = stream.collect(Collectors.toList());
		
//		List<Empregado> empregadosComJ = empregados.stream()
//				.filter(emp -> emp.getNome().startsWith("j"))
//				.collect(Collectors.toList());
		empregadosComJ.stream().forEach((emp) -> System.out.println(emp.getNome()));
		OptionalDouble menorSalario = empregadosComJ.stream().mapToDouble((emp) -> emp.getSalario()).min();
		if(menorSalario.isPresent()) {
			System.out.println("Menor salario: R$ " + menorSalario.getAsDouble()	);
		}
		
		DoubleSummaryStatistics sumario =  empregados.stream().collect(Collectors.summarizingDouble(Empregado::getSalario));
		System.out.println("Estatisticas dos salarios: ");
		System.out.println("maior salario: R$ " + sumario.getMax());
		System.out.println("Menor salario: R$ " + sumario.getMin());
		System.out.println("Salario medio: R$ " + sumario.getAverage());
		System.out.println("Folha salarial: R$ " + sumario.getSum());
		List<String> nomesEmpregados = empregados.stream().map(emp -> emp.getNome()).collect(Collectors.toList());
		System.out.println("** Nomes dos empregados: ");
		nomesEmpregados.forEach(System.out::println);
		//Utilizando reduce para juntar duas Strings
		String nomesSeparadosPorVirgula = empregados.stream().map(Empregado::getNome)
				.reduce("Nomes dos empregados:", (n1, n2) -> n1 + ", " + n2);
		System.out.println(nomesSeparadosPorVirgula);
		
		
//		empregados.stream().forEach(emp -> {
//			System.out.println(emp.getNome());
//		});
//		
//		double salarioTotal = empregados.stream().mapToDouble(emp -> emp.getSalario()).sum();
//		System.out.println("Salario total R$: " + salarioTotal);
//		
//		Mensageiro mensageiro = (mensagem) -> 
//			System.out.println("Mensagem da expressao lambida: " + mensagem);
//		mensageiro.emitirMensagem("TreinaWeb");
//		// Consumer
//		// Entra um parametro e nao retorna a nada
//		System.out.println("Execucao do consumer ");
//		Consumer<Empregado> consumer = (emp) -> {
//			System.out.println(emp.getNome() + ", R$ " + emp.getSalario());
//		};
//		consumer.accept(new Empregado(10, "TreinaWeb", 1000, "Educacao"));
//		// Function
//		System.out.println("Execucao da function: ");
//		Function<Empregado, Double> function = (emp) -> emp.getSalario() * 10;
//		double novoSalario = function.apply(new Empregado(1000, "", 1, ""));
//		System.out.println(novoSalario);
//		// BinaryOperator
//		System.out.println("BinaryOperator: ");
//		BinaryOperator<Empregado> binaryOperator = (emp1, emp2) -> 
//		new Empregado(-1, emp1.getNome() + emp2.getNome(), emp1.getSalario() + emp2.getSalario(), "Junçao");
//		Empregado novoEmpregado = binaryOperator.apply(new Empregado(0, "Treina", 1000, ""), 
//				new Empregado(0, "web", 10000, ""));
//		System.out.println(novoEmpregado.getNome() + ", R$" + novoEmpregado.getSalario());
//		// Predicate
//		// Retorna um booleano
//		System.out.println("Execucao do predicate: ");
//		Predicate<Empregado> predicate = (emp) -> emp.getNome().endsWith("Web");
//		Boolean terminaComWeb = predicate.test(new Empregado(0, "treinaWeb", 0, ""));
//		System.out.println(terminaComWeb);
//		// Supplier
//		System.out.println("Execucao do Supplier: ");
//		Supplier<Empregado> supplier = () -> new Empregado(new Random().nextInt(), "TreinaWeb", 0, "");
//		Empregado emp1 = supplier.get();
//		System.out.println(emp1.getId());
//		Empregado emp2 = supplier.get();
//		System.out.println(emp2.getId());
		
	}

}
