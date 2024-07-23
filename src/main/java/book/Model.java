package book;

import org.jetbrains.annotations.NotNull;

public abstract class Model {

    //class variables
    private @NotNull String title;
    private @NotNull String author;
    private @NotNull String prologue;
    private @NotNull String gender;

    //constructor
    public Model(@NotNull String title, @NotNull String author, @NotNull String prologue, @NotNull String gender) {
        this.title = title;
        this.author = author;
        this.prologue = prologue;
        this.gender = gender;
    }

    //Getter and Setter
    public @NotNull String getPrologue() {
        return prologue;
    }

    public void setPrologue(@NotNull String prologue) {
        this.prologue = prologue;
    }

    public @NotNull String getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull String author) {
        this.author = author;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public @NotNull String getGender() {
        return gender;
    }

    public void setGender(@NotNull String gender) {
        this.gender = gender;
    }
}