public class Book {
    private String id;
    private String title;
    private String author;
    private boolean issued;

    public Book(String id, String title, String author, boolean issued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = issued;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + issued;
    }

    public static Book fromString(String line) {
        String[] parts = line.split(",");
        return new Book(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
    }
}
