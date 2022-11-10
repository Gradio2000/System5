package com.example.system5.repository;

import com.example.system5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByDeleted(Boolean deleted);
    Optional<User> findByLogin(String login);
    boolean existsUserByLoginAndDeleted(String login, Boolean deleted);
    @Query(nativeQuery = true,
            value = "SELECT empl_id FROM sys_com_empl WHERE comm_id = :commId")
    List<Integer> getEmplUserIdList(int commId);
}
