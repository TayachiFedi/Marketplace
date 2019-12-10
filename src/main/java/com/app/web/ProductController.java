package com.app.web;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.SortByCountOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ProductRepository;
import com.app.entities.Product;

@RestController
@RequestMapping("/restproduit")
@CrossOrigin

public class ProductController {
	@Autowired
	ProductRepository PR;
	
	@Autowired
	private MongoTemplate PR1 ;

	@GetMapping("/all")
	 public Iterable<Product> Afficherproduit() {
        return PR.findAll();
    }

	@RequestMapping("/findall/{page}")
	public Page<Product> afficherproduit(@PathVariable("page") int page)  {
		return(PR.findAll(PageRequest.of(page,  3)));
	}
	
	@RequestMapping("/findall/{page}/{size}")
	public Page<Product> afficher(@PathVariable("size") int size)  {
		return(PR.findAll(PageRequest.of(size, 1)));
	}
	
	@GetMapping("/findD/{designation}")
	public Iterable<Product> afficherD(@PathVariable("designation") String designation)  {
		return(PR.findBydesignation(designation));
	}
	
	@GetMapping("/findMc")
	public Iterable<Product> afficherMc(String des)  {
		return(PR.findBydesignationContaining(des));
	}
	
	@PostMapping("/Add")
	public Product Addproduct(@RequestBody Product p)
	{
	   return PR.save(p);	
	}
	
	@PutMapping("/update/{Id}")
	public Product upd(@PathVariable("Id") String Id,@RequestBody Product p )
	{
		p.setId(Id);
		return PR.save(p);
	}
	
	public List<Product> PmaxPrice(){
		int b;
		
		Query Q1= new Query();
		Q1.with(new Sort((Sort.Direction.DESC),"price"));
		Q1.limit(1);
		
		double P=PR1.findOne(Q1,Product.class).getPrice();
		Query Q2=new Query();
		
		Q2.addCriteria(Criteria.where("Price").is(P));
	    return PR1.find(Q2,Product.class);
		
		
	}
	
	public List<Product> getProductsbetween(float
			price1, float price2){

			Query query = new Query();

			query.addCriteria(Criteria.where("Price").lt(price2).gt(price1));;

			return PR1.find(query, Product.class);
	    }

}
