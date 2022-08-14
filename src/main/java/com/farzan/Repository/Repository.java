package com.farzan.Repository;

import com.farzan.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<User, Long>
{

}
