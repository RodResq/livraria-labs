package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {
	
	public BarChartModel getVendasModel() {

	    BarChartModel model = new BarChartModel();

	    ChartSeries vendaSerie = new ChartSeries();
	    vendaSerie.setLabel("Vendas 2021");
	    
	    List<Venda> vendas = getVendas();
	    for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
	    
	    model.addSeries(vendaSerie);

	    return model;
	}

	
	public List<Venda> getVendas() {
		
		List<Venda> vendas = new ArrayList<Venda>();
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		
		Random random = new Random(1234);
		
		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}
		
		return vendas;
		
	}
}
