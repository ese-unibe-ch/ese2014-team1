package ch.unibe.ese.team1.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.team1.model.Message;

public interface MessageDao extends CrudRepository<Message, Long> {

}