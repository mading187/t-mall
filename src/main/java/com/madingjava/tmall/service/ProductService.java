package com.madingjava.tmall.service;

import com.madingjava.tmall.dao.ProductDAO;
import com.madingjava.tmall.pojo.Category;
import com.madingjava.tmall.pojo.Product;
import com.madingjava.tmall.util.Page4Navigator;
import com.madingjava.tmall.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames="products")
public class ProductService  {

	@Autowired ProductDAO productDAO;
	@Autowired ProductImageService productImageService;
	@Autowired CategoryService categoryService;
	@Autowired OrderItemService orderItemService;
	@Autowired ReviewService reviewService;

	@CacheEvict(allEntries=true)
	public void add(Product bean) {
		productDAO.save(bean);
	}

	@CacheEvict(allEntries=true)
	public void delete(int id) {
		productDAO.delete(id);
	}

	@Cacheable(key="'products-one-'+ #p0")
	public Product get(int id) {
		return productDAO.findOne(id);
	}

	@CacheEvict(allEntries=true)
	public void update(Product bean) {
		productDAO.save(bean);
	}

	@Cacheable(key="'products-cid-'+#p0+'-page-'+#p1 + '-' + #p2 ")
	public Page4Navigator<Product> list(int cid, int start, int size,int navigatePages) {
		Category category = categoryService.get(cid);
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<Product> pageFromJPA =productDAO.findByCategory(category,pageable);
		return new Page4Navigator<>(pageFromJPA,navigatePages);
	}

	public void fill(List<Category> categorys) {
		for (Category category : categorys) {
			fill(category);
		}
	}

	@Cacheable(key="'products-cid-'+ #p0.id")
	public List<Product> listByCategory(Category category){
		return productDAO.findByCategoryOrderById(category);
	}

	public void fill(Category category) {
		ProductService productService = SpringContextUtil.getBean(ProductService.class);
		List<Product> products = productService.listByCategory(category);
		productImageService.setFirstProdutImages(products);
		category.setProducts(products);
	}

	public void fillByRow(List<Category> categorys) {
		int productNumberEachRow = 8;
		for (Category category : categorys) {
			List<Product> products =  category.getProducts();
			List<List<Product>> productsByRow =  new ArrayList<>();
			for (int i = 0; i < products.size(); i+=productNumberEachRow) {
				int size = i+productNumberEachRow;
				size= size>products.size()?products.size():size;
				List<Product> productsOfEachRow =products.subList(i, size);
				productsByRow.add(productsOfEachRow);
			}
			category.setProductsByRow(productsByRow);
		}
	}

	public void setSaleAndReviewNumber(Product product) {
		int saleCount = orderItemService.getSaleCount(product);
		product.setSaleCount(saleCount);

		int reviewCount = reviewService.getCount(product);
		product.setReviewCount(reviewCount);

	}

	public void setSaleAndReviewNumber(List<Product> products) {
		for (Product product : products)
			setSaleAndReviewNumber(product);
	}

	public List<Product> search(String keyword, int start, int size) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		List<Product> products =productDAO.findByNameLike("%"+keyword+"%",pageable);
		return products;
	}

}