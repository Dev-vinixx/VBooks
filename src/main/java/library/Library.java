package library;

import book.Book;
import org.jetbrains.annotations.NotNull;
import user.User;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class Library {

    private ArrayList<Book> generalBookshelf = new ArrayList<>();
    private ArrayList<User> userDatabase = new ArrayList<>();

    public @NotNull CompletableFuture<Void> addNewUser(@NotNull User newUser) {

        @NotNull CompletableFuture<Void> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {

            try {
                boolean userExists = userDatabase.stream().anyMatch(user -> newUser.getName().equalsIgnoreCase(user.getName()));

                if (!userExists) {

                    userDatabase.add(newUser);

                    future.complete(null);
                } else {
                    throw new IllegalArgumentException("there is already a user registered with the same name: " + newUser.getName());
                }

            } catch(IllegalArgumentException exception) {

                future.completeExceptionally(exception);
            }
        });
        return future;
    }

    public @NotNull CompletableFuture<Void> donateBook(@NotNull String title, @NotNull String author, @NotNull String prologue, @NotNull String gender) {

        @NotNull CompletableFuture<Void> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
               boolean bookExists = generalBookshelf.stream().anyMatch(book -> title.equalsIgnoreCase(book.getTitle()));

               if (!bookExists) {
                   generalBookshelf.add(new Book(title, author, prologue, gender));

                   future.complete(null);

               } else {

                   throw new IllegalArgumentException("the book with the title: " + title + ". already exists on the shelf.");
               }

            } catch (IllegalArgumentException exeption) {
                future.completeExceptionally(exeption);
            }
        });

        return future;
    }
}