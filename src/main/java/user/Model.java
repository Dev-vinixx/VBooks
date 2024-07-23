package user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class Model {

    //class variables
    private @NotNull String name;
    private final @Range(from = 1, to = Long.MAX_VALUE) int id;

    //constructor
    public Model(@NotNull String name, @Range(from = 1, to = Long.MAX_VALUE) int id) {
        this.name = name;
        this.id = id;
    }

    //Getter and Setter
    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Range(from = 1, to = Long.MAX_VALUE)
    public int getId() {
        return id;
    }
}