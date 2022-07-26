package dsl

import ext_lib.client.Client
import ext_lib.contact_info.ContactInfo
import ext_lib.job.Job
import ext_lib.schedule.Event
import ext_lib.schedule.Schedule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

infix fun Int.July(year: Int): LocalDate = LocalDate.of(year, Month.JULY, this)

infix fun LocalDate.at(hour: String): LocalDateTime =
    LocalDateTime.of(
        year, month, dayOfMonth,
        hour.split(":").first().toInt(),
        hour.split(":").last().toInt()
    )

fun Schedule.addEvent(builder: Event.() -> Unit) {
    val event = Event().apply(builder)
    addEvent(event)
}

fun createSchedule(
    builder: Schedule.() -> Unit
): Schedule = Schedule().apply(builder)

fun createClient(
    builder: Client.Builder.() -> Unit
): Client = Client.Builder().apply(builder).build()

fun createJob(
    builder: Job.Builder.() -> Unit
): Job = Job.Builder().apply(builder).build()

fun createContactInfo(
    builder: ContactInfo.Builder.() -> Unit
): ContactInfo = ContactInfo.Builder().apply(builder).build()