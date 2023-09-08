package Entity;

import com.github.javafaker.Faker;

public class Customer {
    Faker faker = new Faker();
    int id;
    String first_name;
    String last_name;
    String full_name;
    String address;
    String email;
    String birthday;
    String zipcode;
    String country;

    public Customer() {
        this.id = faker.number().numberBetween(1,1000);
        this.first_name = faker.name().firstName();
        this.last_name = faker.name().lastName();
        this.full_name = this.first_name + ' ' + this.last_name;
        this.address = faker.address().fullAddress();
        this.email = this.setEmail();
        this.birthday = faker.date().birthday().toString();
        this.zipcode = faker.address().zipCode();
        this.country = faker.address().country();
    }

    private String setEmail() {
        return this.getFirst_name() + this.getLast_name()
                + '@' + faker.internet().emailAddress().
                split("@")[1];
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
