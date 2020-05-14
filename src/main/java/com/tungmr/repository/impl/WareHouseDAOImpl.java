package com.tungmr.repository.impl;

import com.tungmr.entity.ProductEntity;
import com.tungmr.entity.WareHouseEntity;
import com.tungmr.repository.WareHouseDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WareHouseDAOImpl implements WareHouseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public WareHouseEntity createWareHouseForProduct(Long productId, Long quantity) {
        WareHouseEntity entity = new WareHouseEntity();
        entity.setProduct(new ProductEntity());
        entity.getProduct().setProductId(productId);
        entity.setQuantity(quantity);
        Long wareHouseId = (Long) sessionFactory.getCurrentSession().save(entity);
        WareHouseEntity res = findWareHouseById(wareHouseId);
        return res;
    }

    @Override
    public WareHouseEntity findWareHouseByProductId(Long productId) {
        String sql = "FROM WareHouseEntity WHERE product.productId =:productId";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("productId", productId);
        List<WareHouseEntity> rs = (List<WareHouseEntity>) query.list();
        WareHouseEntity entity = null;
        if (rs.size() > 0)
            entity = rs.get(0);
        return entity;
    }

    @Override
    public WareHouseEntity findWareHouseById(Long wareHouseId) {
        return (WareHouseEntity) sessionFactory.getCurrentSession().get(WareHouseEntity.class, wareHouseId);
    }

    @Override
    public WareHouseEntity updateWareHouse(WareHouseEntity wareHouseEntity) {
        WareHouseEntity rs = (WareHouseEntity) sessionFactory.getCurrentSession().merge(wareHouseEntity);
        return rs;
    }
}
