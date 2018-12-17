package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CreateBookDTO {
    private String ISBN;
    private String name;
    private String author;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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

        CreateBookDTO that = (CreateBookDTO) o;

        return new EqualsBuilder()
                .append(ISBN, that.ISBN)
                .append(name, that.name)
                .append(author, that.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(ISBN)
                .append(name)
                .append(author)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CreateBookDTO{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
