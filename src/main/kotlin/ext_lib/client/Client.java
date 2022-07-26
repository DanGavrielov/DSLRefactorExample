package ext_lib.client;

import ext_lib.schedule.Schedule;
import ext_lib.job.Job;
import ext_lib.contact_info.ContactInfo;

public class Client {
    private final String name;
    private final int age;
    private final Job job;
    private final ContactInfo contactInfo;
    private final Schedule schedule;

    private Client(String name, int age, Job job, ContactInfo contactInfo, Schedule schedule) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.contactInfo = contactInfo;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Job getJob() {
        return job;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Person{" +
                "\n\tname = '" + name + '\'' +
                ",\n\tage = " + age +
                ",\n\tcompany = " + job +
                ",\n\tcontactInfo = " + contactInfo +
                ",\n\tschedule = " + schedule +
                "\n}";
    }

    public static class Builder {
        private String name;
        private int age;
        private Job job;
        private ContactInfo contactInfo;

        private Schedule schedule;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setJob(Job job) {
            this.job = job;
            return this;
        }

        public Builder setContactInfo(ContactInfo contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        public Builder setSchedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public Client build() {
            return new Client(name, age, job, contactInfo, schedule);
        }
    }
}
