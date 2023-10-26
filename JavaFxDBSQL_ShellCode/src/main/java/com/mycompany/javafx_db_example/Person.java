package com.mycompany.javafx_db_example;

public class Person {

    private String name;

    private String email;

    private String phone;

    private String address;

    private String password;




    public Person() {
    }

    public Person( String name, String email, String phone, String address, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    /**
     * @return Returns the <b>name</b> set by the user.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name This method will take the <b>name</b> that is being set by the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the <b>email</b> set by the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email This method will take the <b>email</b> that is being set by the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNum) {
        this.phone = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

