package model;

import java.util.Date;

public class Pendaftaran {
    private int id;
    private String name;
    private String address;
    private String gender;
    private String membershipType;
    private String interests;
    private boolean subscription; 
    private String registrationDate;

    public Pendaftaran(String name2, String address2, String gender2, String membership, String interests2,
            boolean subscribed, Date registrationDate2) {
        //TODO Auto-generated constructor stub
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and Setter for Gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and Setter for Membership Type
    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    // Getter and Setter for Interests (Comma-separated list as a String)
    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    // Getter and Setter for Subscription
    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    // Getter and Setter for Registration Date (formatted as YYYY-MM-DD)
    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isSubscribed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSubscribed'");
    }
}
