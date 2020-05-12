package models;

public class News {
    private String title;
    private String content;
    private String type;
    private String author;
    private int id;
    private int departmentId;

    public News(String title, String content, String type, String author, int departmentId) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.author = author;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (departmentId != news.departmentId) return false;
        if (!title.equals(news.title)) return false;
        if (!content.equals(news.content)) return false;
        if (!type.equals(news.type)) return false;
        return author.equals(news.author);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + id;
        result = 31 * result + departmentId;
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

