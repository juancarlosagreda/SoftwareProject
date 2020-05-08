public class TeresaResponseProfile {
    String name;
    String lastname;
    String email;
    int gender;
    int employments;
    int hours;

    public TeresaResponseProfile(String name, String lastname, String email, int gender, int employments, int hours) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.employments = employments;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getEmployments() {
        return employments;
    }

    public void setEmployments(int employments) {
        this.employments = employments;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
