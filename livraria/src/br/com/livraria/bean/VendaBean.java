package br.com.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


import br.com.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendaBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	
	@Inject
	EntityManager manager;
	

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		
		ChartSeries vendaSerie1 = new ChartSeries();
		vendaSerie1.setLabel("Vendas 2017");

		List<Venda> vendas = getVenda();
		for (Venda venda : vendas) {

			vendaSerie1.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		

		model.addSeries(vendaSerie1);
		

		return model;
	}

	public List<Venda> getVenda() {

		List<Venda> vendas = this.manager.createQuery("select v from Venda v", Venda.class).getResultList();

		return vendas;

	}

}
