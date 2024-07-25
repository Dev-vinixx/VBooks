package user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class User extends Model{

    public User(@NotNull String name, @NotNull String password) {
        super(name, password);
    }
}