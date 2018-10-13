package br.com.livraria.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.livraria.dao.DAO;
import br.com.livraria.modelo.Livro;
import br.com.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendaBean {

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();

		ChartSeries vendaSerie = new ChartSeries();

		vendaSerie.setLabel("Vendas");

		List<Venda> vendas = getVenda();

		for (Venda venda : vendas) {

			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		model.addSeries(vendaSerie);

		return model;
	}

	public List<Venda> getVenda() {

		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();
		
		Random random = new Random(1234);

		for (Livro livro : livros) {
		
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));

		}

		return vendas;

	}

}
