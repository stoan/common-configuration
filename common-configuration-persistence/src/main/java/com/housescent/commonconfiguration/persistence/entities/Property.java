package com.housescent.commonconfiguration.persistence.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Siya Sosibo on 13/02/2017.
 */
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"KEY", "VALUE", "APPLICATIONS_ID"}))
public class Property extends BaseEntity implements Serializable {

    @Column(nullable = false, length = 100)
    private String key;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "APPLICATIONS_ID")
    private Application application;

    public Property() {
    }

    public Property(String key, String value, Application application) {
        this.key = key;
        this.value = value;
        this.application = application;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(key, property.key) &&
                Objects.equals(value, property.value) &&
                Objects.equals(application, property.application);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, application);
    }
}
