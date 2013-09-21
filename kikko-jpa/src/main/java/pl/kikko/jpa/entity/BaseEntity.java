package pl.kikko.jpa.entity;

import pl.kikko.patterns.builder.AbstractBuildableBuilder;
import pl.kikko.patterns.builder.Buildable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Buildable {

    @Id
    @GeneratedValue
    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public static abstract class BaseEntityBuilder<TYPE extends BaseEntity, BUILDER extends BaseEntityBuilder<TYPE, BUILDER>> extends AbstractBuildableBuilder<TYPE, BUILDER> {
        public BUILDER id(Serializable id) {
            buildable.setId(id);
            return builder;
        }
    }

}
