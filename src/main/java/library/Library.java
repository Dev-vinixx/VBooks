package library;

import book.Book;
import library.filter.Filter;
import org.jetbrains.annotations.NotNull;
import user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {

    private ArrayList<Book> generalBookshelf = new ArrayList<>();
    private ArrayList<User> userDatabase = new ArrayList<>();

    public final @NotNull CompletableFuture<Void> addNewUser(@NotNull User newUser) {
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
            } catch (IllegalArgumentException exception) {
                future.completeExceptionally(exception);
            }
        });

        return future;
    }

    public final @NotNull CompletableFuture<Void> donateBook(@NotNull String title, @NotNull String author, @NotNull String prologue, @NotNull String gender) {
        @NotNull CompletableFuture<Void> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                boolean bookExists = generalBookshelf.stream().anyMatch(book -> title.equalsIgnoreCase(book.getTitle()));

                if (!bookExists) {
                    generalBookshelf.add(new Book(title, author, prologue, gender));
                    future.complete(null);
                } else {
                    throw new IllegalArgumentException("the book with the title: " + title + " already exists on the shelf.");
                }
            } catch (IllegalArgumentException exception) {
                future.completeExceptionally(exception);
            }
        });

        return future;
    }

    public @NotNull CompletableFuture<ArrayList<Book>> listBook(@NotNull Filter filter, @NotNull String argument, @NotNull User user) {
        @NotNull CompletableFuture<ArrayList<Book>> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                boolean userExists = userDatabase.stream().anyMatch(existingUser -> existingUser.getName().equalsIgnoreCase(user.getName()));

                CompletableFuture<Void> userFuture = userExists ? CompletableFuture.completedFuture(null) : addNewUser(user);

                userFuture.thenRun(() -> {
                    ArrayList<Book> filteredBooks = filterBooks(filter, argument);

                    if (filteredBooks.isEmpty()) {
                        future.completeExceptionally(new IllegalArgumentException("there is no book with the specified " + filter.name().toLowerCase() + ": " + argument));
                    } else {
                        future.complete(filteredBooks);
                    }
                }).exceptionally(ex -> {
                    future.completeExceptionally(ex);
                    return null;
                });

            } catch (Exception exception) {
                future.completeExceptionally(exception);
            }
        });

        return future;
    }

    private ArrayList<Book> filterBooks(@NotNull Filter filter, @NotNull String argument) {
        Map<Filter, Predicate<Book>> filterMap = new HashMap<>();
        filterMap.put(Filter.TITLE, book -> book.getTitle().equals(argument));
        filterMap.put(Filter.AUTHOR, book -> book.getAuthor().equals(argument));
        filterMap.put(Filter.GENDER, book -> book.getGender().equals(argument));

        Predicate<Book> predicate = filterMap.get(filter);
        if (predicate == null) {
            throw new IllegalArgumentException("we don't recognize this filter: " + filter);
        }

        return generalBookshelf.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}