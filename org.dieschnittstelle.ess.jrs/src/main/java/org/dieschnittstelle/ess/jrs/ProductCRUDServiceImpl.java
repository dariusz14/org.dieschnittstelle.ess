package org.dieschnittstelle.ess.jrs;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import java.util.List;

/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	private GenericCRUDExecutor<AbstractProduct> productCRUD;

	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
		// read out the dataAccessor
		this.productCRUD = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");
	}

	@Override
	public AbstractProduct createProduct(
			AbstractProduct prod) {
		return this.productCRUD.createObject(prod);
	}

	@Override
	public List<AbstractProduct> readAllProducts() {
		return this.productCRUD.readAllObjects();
	}

	@Override
	public AbstractProduct updateProduct(long id, AbstractProduct update) {
		AbstractProduct prod = this.productCRUD.readObject(id);
		if(prod == null){
			throw new NotFoundException(String.format("Product %d not found", id));
		}
		prod.setId(update.getId());
		prod.setName(update.getName());
		prod.setPrice(update.getPrice());
		return this.productCRUD.updateObject(prod);
	}

	@Override
	public boolean deleteProduct(long id) {
		AbstractProduct prod = this.productCRUD.readObject(id);
		if(prod == null){
			throw new NotFoundException(String.format("Product %d not found", id));
		}
		return this.productCRUD.deleteObject(id);
	}

	@Override
	public AbstractProduct readProduct(long id) {
		AbstractProduct prod = this.productCRUD.readObject(id);
		if(prod == null){
			throw new NotFoundException(String.format("Product %d not found", id));
		}
		return prod;
	}
}
