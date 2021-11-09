package ru.reeson2003.telega.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.reeson2003.telega.data.entity.State;

public interface StateRepository extends MongoRepository<State, String> {
}
