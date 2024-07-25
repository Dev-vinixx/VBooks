package user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class Model {

    //class variables
    private @NotNull String name;
    private final @NotNull String password;

    //constructor
    public Model(@NotNull String name, @NotNull String password) {
        this.name = name;
        this.password = password;
    }

    //Getter and Setter
    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getPassword() {
        return password;
    }
}