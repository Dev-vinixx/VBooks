package user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class User extends Model{

    public User(@NotNull String name, @Range(from = 1, to = Long.MAX_VALUE) int id) {
        super(name, id);
    }
}
