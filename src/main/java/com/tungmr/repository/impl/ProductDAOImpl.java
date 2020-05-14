package com.tungmr.repository.impl;

import com.tungmr.entity.ProductEntity;
import com.tungmr.repository.ProductDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<ProductEntity> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductEntity.class);
        return criteria.list();
    }

    public ProductEntity getProductById(Long productId) {
        return (ProductEntity) sessionFactory.getCurrentSession().get(ProductEntity.class, productId);
    }

    public ProductEntity updateProduct(ProductEntity entity) {
        return (ProductEntity) sessionFactory.getCurrentSession().merge(entity);
    }

    public ProductEntity createProduct(ProductEntity entity) {
        ProductEntity res;
        Long productId = (Long) sessionFactory.getCurrentSession().save(entity);
        res = getProductById(productId);
        return res;
    }

    @Override
    public ProductEntity checkProductExisted(String productName, String categoryName, String supplierName) {
        String sql = "FROM ProductEntity WHERE productName =:productName AND category.categoryName =:categoryName AND supplier.supplierName =:supplierName";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("productName", productName);
        query.setParameter("categoryName", categoryName);
        query.setParameter("supplierName", supplierName);
        List<ProductEntity> rs = (List<ProductEntity>) query.list();
        ProductEntity entity = null;
        if (rs.size() > 0)
            entity = rs.get(0);
        return entity;
    }
}
