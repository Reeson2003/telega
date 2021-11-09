package ru.reeson2003.telega.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class State {

    @Id
    private String id;

    private Object data;

    public <T> T getData() {
        return (T) data;
    }

}
