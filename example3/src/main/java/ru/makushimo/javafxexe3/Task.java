package ru.makushimo.javafxexe3;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "done")
public class Task {

    private int id;
    private String name;
    private boolean done;

}
