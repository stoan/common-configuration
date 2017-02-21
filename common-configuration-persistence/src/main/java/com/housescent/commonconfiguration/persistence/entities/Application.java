package com.housescent.commonconfiguration.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Siya Sosibo on 07/02/2017.
 */
@Entity
public class Application extends BaseEntity implements Serializable{

    @Column(nullable = false, unique = true, length = 100)
    private String applicationName;
    private String description;

    public Application() {
    }

    public Application(String applicationName, String description) {
        this.applicationName = applicationName;
        this.description = description;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(applicationName, that.applicationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationName);
    }
}
