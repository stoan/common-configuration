package com.housescent.commonconfiguration.persistence.repositories;

import com.housescent.commonconfiguration.persistence.entities.Property;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

/**
 * Created by Siya Sosibo on 13/02/2017.
 */
@Repository
public interface PropertyRepository extends EntityRepository<Property,Long>{

    List<Property> findByApplication_applicationNameIgnoreCaseAndKeyIgnoreCase(String applicationName, String key);

    List<Property> findByApplication_applicationNameIgnoreCase(String applicationName);
}
