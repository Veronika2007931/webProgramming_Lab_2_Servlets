package ua.kpi.model.entity;

import java.util.Objects;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String description;
    private Reader reader; // Прямий зв'язок між об'єктами без Spring анотацій

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(description, book.description) &&
                Objects.equals(reader, book.reader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, reader);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + '\'' + ", author='" + author + '\'' + ", description='"
                + description + '\'' + ", reader=" + reader + '}';
    }

    // Builder для Книги
    public static class Builder {
        private final Book instance = new Book();

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            instance.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            instance.author = author;
            return this;
        }

        public Builder setDescription(String description) {
            instance.description = description;
            return this;
        }

        public Builder setReader(Reader reader) {
            instance.reader = reader;
            return this;
        }

        public Book build() {
            return instance;
        }
    }
}