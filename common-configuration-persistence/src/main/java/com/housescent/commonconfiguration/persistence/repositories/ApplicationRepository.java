package com.housescent.commonconfiguration.persistence.repositories;

import com.housescent.commonconfiguration.persistence.entities.Application;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Created by S983620 on 13/02/2017.
 */
@Repository
public interface ApplicationRepository extends EntityRepository<Application,Long> {


}
