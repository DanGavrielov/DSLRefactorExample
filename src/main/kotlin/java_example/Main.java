package java_example;

import ext_lib.client.Client;
import ext_lib.contact_info.ContactInfo;
import ext_lib.job.Job;
import ext_lib.schedule.Event;
import ext_lib.schedule.Schedule;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Client.Builder clientBuilder = new Client.Builder();
        clientBuilder.setName("Dan Gavrielov");
        clientBuilder.setAge(32);

        Job.Builder jobBuilder = new Job.Builder();
        jobBuilder.setCompanyName("Gini-Apps");
        jobBuilder.setTitle("Android Developer");
        clientBuilder.setJob(jobBuilder.build());

        ContactInfo.Builder contactInfoBuilder = new ContactInfo.Builder();
        contactInfoBuilder.setEmailAddress("dang@gini-apps.com");
        contactInfoBuilder.setPhoneNumber("050-4403090");
        clientBuilder.setContactInfo(contactInfoBuilder.build());

        Schedule schedule = new Schedule();
        Event jamSession = new Event("Jam Session");
        jamSession.setDateTime(
                LocalDateTime.of(2022, Month.JULY, 26, 12, 0)
        );
        schedule.addEvent(jamSession);
        clientBuilder.setSchedule(schedule);

        Client client = clientBuilder.build();
        System.out.println(client);
    }
}
