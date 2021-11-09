package ru.reeson2003.telega.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.reeson2003.telega.data.entity.ScriptSource;

public interface ScriptSourceRepository extends MongoRepository<ScriptSource, String> {
}
