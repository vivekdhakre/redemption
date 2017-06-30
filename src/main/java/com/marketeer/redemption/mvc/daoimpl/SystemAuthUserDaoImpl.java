package com.marketeer.redemption.mvc.daoimpl;

import com.marketeer.redemption.mvc.dao.SystemAuthUserDao;
import com.marketeer.redemption.mvc.entity.AdvertUserDo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Vivek on 27/4/17.
 */

@Repository
public class SystemAuthUserDaoImpl implements SystemAuthUserDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Object[] getByAdvertId(long advertId) {

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("firstName"));
        projectionList.add(Projections.property("lastName"));
        projectionList.add(Projections.property("sau.emailAddress"));
        projectionList.add(Projections.property("sau.phoneNumber"));


        return (Object[]) factory.getCurrentSession().createCriteria(AdvertUserDo.class)
                .add(Restrictions.eq("userPkey", advertId))
                .createAlias("systemAuthUserDo", "sau")
                .setProjection(projectionList).uniqueResult();
    }
}
