package br.com.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.livraria.dao.DAO;
import br.com.livraria.modelo.Livro;
import br.com.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendaBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		
		ChartSeries vendaSerie1 = new ChartSeries();
		vendaSerie1.setLabel("Vendas 2017");

		List<Venda> vendas = getVenda(1234);
		for (Venda venda : vendas) {

			vendaSerie1.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		ChartSeries vendaSerie2 = new ChartSeries();
		vendaSerie2.setLabel("Vendas 2018");
		
		
		
		vendas = getVenda(4321);
		for (Venda venda : vendas) {

			vendaSerie2.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		model.addSeries(vendaSerie1);
		model.addSeries(vendaSerie2);

		return model;
	}

	public List<Venda> getVenda(long seed) {

		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(seed);

		for (Livro livro : livros) {

			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));

		}

		return vendas;

	}

}
