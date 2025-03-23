import java.util.Objects;
import java.util.UUID;

class User {
    private final String id;
    private String name;
    private String memberId;
    private String contactNumber;
    private UserRole role;

    public User(String name, String memberId, String contactNumber, UserRole role) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.memberId = memberId;
        this.contactNumber = contactNumber;
        this.role = role;
    }

    public User(String id, String name, String memberId, String contactNumber, UserRole role) {
        this.id = id;
        this.name = name;
        this.memberId = memberId;
        this.contactNumber = contactNumber;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", memberId='" + memberId + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", role=" + role +
                '}';
    }
}