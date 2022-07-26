package ext_lib.contact_info;

public class ContactInfo {
    private final String phoneNumber;
    private final String emailAddress;

    private ContactInfo(String phoneNumber, String emailAddress) {
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public static class Builder {
        private String phoneNumber;
        private String emailAddress;

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public ContactInfo build() {
            return new ContactInfo(phoneNumber, emailAddress);
        }
    }
}
