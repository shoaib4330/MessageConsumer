# JMS Consumer with Email, Notification, Text Messaging Clients
A tomcat web application.
This is a reusable module that does the following:
- Listens to a configured JMS Queue (ActiveMQ Artemis, in this case)
- Has consumers for the following purposes:
   - For sending Notifications to Firebase (Reads Queue, Creates Notification Object, Sends to Firebase)
   - For sending eMails to SMTP server (Reads Queue, Creates Mail Object, sends mail SMTP server)
   - For sending SMS to Mobile devices (Reads Queue, Create SMS Object, sends sms)

### Intention/Purpose of this project
Very often server side requires sending **notifications, SMS and Emails** to client's mobile devices or emails. I have created this project for my future use, and for anyone else, so that, writing from scratch is not required.

It can also serve as example and snippet, for someone looking to writer *JMS Consumer using Spring*.

### Components
- A JmsListener, listening to a queue called **Notifications**
  - It uses a client that converts messages read from *Notifications* into a payload sendable to Google Firebase.
- A JmsListener, listening to a queue called **Mails**
  - It uses *Mailing Client/Component/Code* to send emails to configured *SMTP* server
- A JmsListener, listening to a queue called **SMS**
  - It uses *SMS Gateway Module/Component* to send text messages to mobile devices

### Status
This project is not generic in nature, you will need to change the *POJO's* and *Data Classes* to your need. I'll keep working on it to make more easily usable and generic.

### Dependencies
- Spring Data JPA
- Spring JMS
   - JMS Template
   - JMS Listener
- Spring Boot Starter Artemis
- Artemis JMS Client

This module is written to be resused within multiple project that need to send Notifications to Mobiles devices, Sms to Mobile devices, or sending emails to SMTP server.
