# MessageConsumer
Jms Queue Consumer, Consumer Messages, Sends Notifications to Firebase, SMS to mobile devices and Emails to SMTP Server
A module written to send your payload/messages to Mobile Devices (which will show them as notifications)
This module is written to:
- Receive messages/payload from JMS Queues
- Parse this payload to POJO's
- Transform them to a payload that is sendable to Firebase
- Sends that to Firebase
- Firebase sends that payload <b>Mobile Devices registered with firebase</b>, and those devices show those as Noftifications to mobile users.
