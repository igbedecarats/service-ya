package app.services.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.EqualsAndHashCode
import org.apache.commons.lang3.Validate
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

import javax.persistence.*

@Entity
@Table(name = "service_category")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(includes='name')
class ServiceCategory {

    @Id
    @GeneratedValue
    private long id

    @Column(name = "name", nullable = false, unique = true)
    private String name

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category")
    private Set<ServiceSubCategory> subCategories = new LinkedHashSet<>()

    ServiceCategory(final String name) {
        this.name = name
    }

    ServiceCategory() {}

    void addSubCategories(final Set<ServiceSubCategory> subCategories) {
        Validate.notNull(subCategories, "The Sub Categories cannot be null")
        this.subCategories.addAll(subCategories)
    }

    long getId() {
        id
    }

    String getName() {
        name
    }

    Set<ServiceSubCategory> getSubCategories() {
        subCategories
    }

}
