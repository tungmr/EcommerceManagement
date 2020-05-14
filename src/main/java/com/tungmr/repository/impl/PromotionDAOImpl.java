package com.tungmr.repository.impl;

import com.tungmr.entity.PromotionEntity;
import com.tungmr.entity.PromotionEntity;
import com.tungmr.repository.PromotionDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PromotionDAOImpl implements PromotionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<PromotionEntity> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PromotionEntity.class);
        return criteria.list();
    }

    public PromotionEntity getPromotionById(Long promotionId) {
        return (PromotionEntity) sessionFactory.getCurrentSession().get(PromotionEntity.class, promotionId);
    }

    public PromotionEntity updatePromotion(PromotionEntity entity) {
        return (PromotionEntity) sessionFactory.getCurrentSession().merge(entity);
    }

    public PromotionEntity createPromotion(PromotionEntity entity) {
        PromotionEntity res;
        Long promotionId = (Long) sessionFactory.getCurrentSession().save(entity);
        res = getPromotionById(promotionId);
        return res;
    }

    @Override
    public PromotionEntity checkPromotionExisted(String promotionName, Long productId) {
        String sql = "FROM PromotionEntity WHERE promotionName =:promotionName AND product.productId =:productId";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("promotionName", promotionName);
        query.setParameter("productId", productId);
        List<PromotionEntity> rs = (List<PromotionEntity>) query.list();
        PromotionEntity entity = null;
        if (rs.size() > 0)
            entity = rs.get(0);
        return entity;
    }

    @Override
    public PromotionEntity getPromotionByProductId(Long productId, Long promotionId) {
        String sql = "FROM PromotionEntity WHERE promotionId =:promotionId AND product.productId =:productId";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("promotionId", promotionId);
        query.setParameter("productId", productId);
        List<PromotionEntity> rs = (List<PromotionEntity>) query.list();
        PromotionEntity entity = null;
        if (rs.size() > 0)
            entity = rs.get(0);
        return entity;
    }
}
