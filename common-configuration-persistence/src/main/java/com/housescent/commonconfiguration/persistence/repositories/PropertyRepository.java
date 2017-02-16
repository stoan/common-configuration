package com.housescent.commonconfiguration.persistence.repositories;

import com.housescent.commonconfiguration.persistence.entities.Property;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Created by S983620 - Siya Sosibo on 13/02/2017.
 */
@Repository
public interface PropertyRepository extends EntityRepository<Property,Long>{
}
