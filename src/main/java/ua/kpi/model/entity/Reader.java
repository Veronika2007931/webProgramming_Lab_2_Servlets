package ua.kpi.model.entity;

import java.util.Objects;

public class Reader {
    private Integer id;
    private String fullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id) && Objects.equals(fullName, reader.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    @Override
    public String toString() {
        return "Reader{" + "id=" + id + ", fullName='" + fullName + '\'' + '}';
    }

    public static class Builder {
        private final Reader instance = new Reader();

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public Builder setFullName(String fullName) {
            instance.fullName = fullName;
            return this;
        }

        public Reader build() {
            return instance;
        }
    }
}