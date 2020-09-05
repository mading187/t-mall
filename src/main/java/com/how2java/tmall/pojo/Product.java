package com.how2java.tmall.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
/**
@JsonIgnoreProperties(ignoreUnknown = true)，将这个注解写在类上之后，就会忽略类中不存在的字段。
这个注解还可以指定要忽略的字段，例如@JsonIgnoreProperties({ “password”, “secretKey” })
 */
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
/**
@Document注解标明实体是elasticsearch种的Document,其属性可以标明属于的索引和类型----对应数据库中的数据库名和表名,
其中type不预先创建也可以,没预先创建的它会自动创建一个与实体相匹配的type

 */
@Document(indexName = "tmall_springboot",type = "product")
public class Product {
	@Id
	/**
	 * JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.
	 TABLE：使用一个特定的数据库表格来保存主键。
	 SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
	 IDENTITY：主键由数据库自动生成（主要是自动增长型）
	 AUTO：主键由程序控制。
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//对应相应的字段
	@Column(name = "id")
	int id;

	@ManyToOne
	/**
	 * 定义外键在本表中的字段名
	 */
	@JoinColumn(name="cid")
	private Category category;

	//如果既没有指明 关联到哪个Column,又没有明确要用@Transient忽略，那么就会自动关联到表对应的同名字段
	private String name;
	private String subTitle;
	private float originalPrice;
	private float promotePrice;
	private int stock;
	private Date createDate;


	/**
	 * @transient 就是在给某个javabean上需要添加个属性，
	 * 但是这个属性你又不希望给存到数据库中去，仅仅是做个临时变量，用一下。不修改已经存在数据库的数据的数据结构。
	 */
	@Transient
	private ProductImage firstProductImage;
	@Transient
	private List<ProductImage> productSingleImages;
	@Transient
	private List<ProductImage> productDetailImages;
	@Transient
	private int reviewCount;
	@Transient
	private int saleCount;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public float getPromotePrice() {
		return promotePrice;
	}
	public void setPromotePrice(float promotePrice) {
		this.promotePrice = promotePrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public ProductImage getFirstProductImage() {
		return firstProductImage;
	}
	public void setFirstProductImage(ProductImage firstProductImage) {
		this.firstProductImage = firstProductImage;
	}
	public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}
	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}
	public List<ProductImage> getProductDetailImages() {
		return productDetailImages;
	}
	public void setProductDetailImages(List<ProductImage> productDetailImages) {
		this.productDetailImages = productDetailImages;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", name=" + name + ", subTitle=" + subTitle
				+ ", originalPrice=" + originalPrice + ", promotePrice=" + promotePrice + ", stock=" + stock
				+ ", createDate=" + createDate + ", firstProductImage=" + firstProductImage + ", reviewCount="
				+ reviewCount + ", saleCount=" + saleCount + "]";
	}



}
