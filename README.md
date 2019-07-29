# MessageConsumer
This is a reusable module that does the following:
- Listens to a configured JMS Queue (ActiveMQ Artemis, in this case)
- Has consumers for the following purpose:
   - For sending Notifications to Firebase (Reads Queue, Creates Notification Object, Sends to Firebase)
   - For sending eMails to SMTP server (Reads Queue, Creates Mail Object, sends mail SMTP server)
   - FOr sending SMS to Mobile devices (Reads Queue, Create SMS Object, sends sms)
   
This module is written to be resused within multiple project that need to send Notifications to Mobiles devices, Sms to Mobile devices, or sending emails to SMTP server.
