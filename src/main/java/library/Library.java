package library;

import book.Book;
import org.jetbrains.annotations.NotNull;
import user.User;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Library {

    private ArrayList<Book> generalBookshelf = new ArrayList<>();
    private ArrayList<User> userDatabase = new ArrayList<>();

    public @NotNull CompletableFuture<Void> addNewUser(@NotNull User newUser) {

        @NotNull CompletableFuture<Void> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            boolean userExists = userDatabase.stream()
                    .anyMatch(user -> newUser.getName().equalsIgnoreCase(user.getName()));

            if (!userExists) {
                userDatabase.add(newUser);
            }
        });
        return future;
    }
}
