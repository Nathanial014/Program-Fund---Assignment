package models;

public class Owner {

    // fields //
    private String name;
    private String phone;

    public Owner(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // getters and setters //
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            this.phone = "Unknown";
        } else {
            this.phone = phone;
        }
    }

    // toString //
    @Override
    public String toString() {
        return "Owner{name='" + name + "', phone='" + phone + "'}";
    }

    // equals //
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Owner)) return false;

        Owner other = (Owner) obj;
        return this.name.equalsIgnoreCase(other.name)
                && this.phone.equalsIgnoreCase(other.phone);
    }
}
