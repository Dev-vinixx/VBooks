package book;

import org.jetbrains.annotations.NotNull;

public class Book extends Model {

    public Book(@NotNull String title, @NotNull String author, @NotNull String prologue, @NotNull String gender) {
        super(title, author, prologue, gender);
    }
}
