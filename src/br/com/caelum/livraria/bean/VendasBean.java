package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	LivroDao livroDao;
	
	public BarChartModel getVendasModel() {

	    BarChartModel model = new BarChartModel();
	    model.setAnimate(true);
	    	
	    
	    ChartSeries vendaSerie = new ChartSeries();
	    vendaSerie.setLabel("Vendas 2020");
	    
	    ChartSeries vendaSerie2019 = new ChartSeries();
	    vendaSerie2019.setLabel("Vendas 2019");
	    
	    List<Venda> vendas = getVendas(1234);
	    for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
	    
	    
	    List<Venda> vendas2019 = getVendas(4321);
	    for (Venda venda2019 : vendas2019) {
			vendaSerie2019.set(venda2019.getLivro().getTitulo(), venda2019.getQuantidade());
		}
	    
	    model.addSeries(vendaSerie);
	    model.addSeries(vendaSerie2019);

	    return model;
	}

	
	public List<Venda> getVendas(long seed) {
		
		List<Venda> vendas = new ArrayList<Venda>();
		List<Livro> livros = this.livroDao.listaTodos();
		
		Random random = new Random(seed);
		
		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}
		
		return vendas;
		
	}
}
