package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class EditBookDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private String isbn;
    private String name;
    private String author;

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String ISBN) {
        this.isbn = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EditBookDTO that = (EditBookDTO) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(isbn, that.isbn)
                .append(name, that.name)
                .append(author, that.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(isbn)
                .append(name)
                .append(author)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "EditBookDTO{" +
                "id='" + id + '\'' +
                "ISBN='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

