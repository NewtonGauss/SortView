package gui;

import org.apache.commons.cli.*;

import gui.Arreglo.Orden;
import ordenamientos.Burbujeo;
import ordenamientos.Estrategia;
import ordenamientos.Insercion;
import ordenamientos.MergeSort;
import ordenamientos.QuickSort;
import ordenamientos.Seleccion;
import ordenamientos.ShellSort;

public class OpcionesParser {
	private int cantEl;
	private Orden condicionInicial;
	private int sleep;
	private Estrategia estrategia;
	Options options = new Options();

	public OpcionesParser(String[] opciones) {
		CommandLine cmd = inicializarParser(opciones);
		cantEl = Integer.parseInt(cmd.getOptionValue("cant"));
		condicionInicial = Orden.stringToOrden(cmd.getOptionValue("inicial"));
		sleep = Integer.parseInt(cmd.getOptionValue("sleep"));
		inicializarEstrategia(cmd.getOptionValue("estrategia"));
	}

	private void inicializarEstrategia(String estrategiaString) {
		switch (estrategiaString) {
		case "burbujeo":
			estrategia = new Burbujeo();
			break;
		case "merge":
			estrategia = new MergeSort();
			break;
		case "seleccion":
			estrategia = new Seleccion();
			break;
		case "shellsort":
			estrategia = new ShellSort();
			break;
		case "insercion":
			estrategia = new Insercion();
			break;
		case "quicksort":
		default:
			estrategia = new QuickSort();
			break;
		}
	}

	private CommandLine inicializarParser(String[] opciones) {
		addOpciones();
		return parsearArgumentos(opciones);
	}

	private void addOpciones() {
		addOpcionReqConArgumento("c", "cant",
				"cantidad inicial de elementos en el arreglo");
		addOpcionReqConArgumento("i", "inicial", "condicion inicial del arreglo: ordenado, casi, aleatorio, inverso");
		addOpcionReqConArgumento("s", "sleep", "tiempo entre operaciones");
		addOpcionReqConArgumento("e", "estrategia",
				"algoritmo de ordenamiento a usar: burbujeo, merge, quicksort, shellsort, seleccion o insercion");
	}
	
	private void addOpcionReqConArgumento(String optCorta, String optLarga,
			String desc) {
		Option estrategiaOption = new Option(optCorta, optLarga, true, desc);
		estrategiaOption.setRequired(true);
		options.addOption(estrategiaOption);
	}

	private CommandLine parsearArgumentos(String[] opciones) {
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formateador = new HelpFormatter();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, opciones);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formateador.printHelp("visualizador de argumentos", options);
			System.exit(1);
		}
		return cmd;
	}

	public int getCantEl() {
		return cantEl;
	}
	
	public Estrategia getEstrategia() {
		return estrategia;
	}
	
	public Orden getCondicionInicial() {
		return condicionInicial;
	}
	
	public int getSleep() {
		return sleep;
	}

}
