package org.baeldung.um.service;

import org.baeldung.common.persistence.service.IService;
import org.baeldung.um.model.User;

public interface IUserService extends IService<User> {

    User getCurrentUser();

}
