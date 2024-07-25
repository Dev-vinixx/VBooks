import library.Library;
import library.filter.Filter;
import org.jetbrains.annotations.NotNull;
import user.User;

public class Main {
    public static void main(String[] args) {
        @NotNull User vinis = new User("Vinis", "hello world");

        @NotNull Library library = new Library();

        library.donateBook("player number two", "vins", "Just an history of a fps game.", "Fantasy");

        library.listBook(Filter.TITLE, "player number two", vinis).join().stream().forEach(book -> System.out.println(book.getPrologue()));
    }
}