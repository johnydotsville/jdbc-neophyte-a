package lev.johnydotsville.Entities;

public class Actor {
    private int id;
    private String firstName;
    private String lastName;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return "actor : { "
                + "id=" + this.id + ", "
                + "fname='" + this.firstName + "', "
                + "lname='" + this.lastName + "'"
                + "}";
    }
}
