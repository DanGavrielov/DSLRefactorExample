package dsl

fun main(args: Array<String>) {
    val client = createClient {
        name = "Dan Gavrielov"
        age = 32

        job = createJob {
            companyName = "Gini-Apps"
            title = "Android Developer"
        }

        contactInfo = createContactInfo {
            emailAddress = "dang@gini-apps.com"
            phoneNumber = "050-4403090"
        }

        schedule = createSchedule {
            addEvent {
                name = "Jam Session"
                dateTime = 26 July 2022 at "12:00"
            }
        }
    }
    println(client)
}