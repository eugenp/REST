package org.baeldung.um.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.baeldung.um.model.Principal;
import org.baeldung.um.model.Role;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Principal.class)
public abstract class Principal_ {

    public static volatile SingularAttribute<Principal, Long> id;
    public static volatile SetAttribute<Principal, Role> roles;
    public static volatile SingularAttribute<Principal, String> name;
    public static volatile SingularAttribute<Principal, String> password;
    public static volatile SingularAttribute<Principal, Boolean> locked;

}
