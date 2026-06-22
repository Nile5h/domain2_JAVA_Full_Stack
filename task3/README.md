Contact Management System
![Java Version] ![Jakarta EE] ![Maven]

A robust, session-based web application for managing personal contacts. Built with modern Jakarta EE 10 specifications, this project demonstrates clean MVC separation, server-side validation, and thread-safe in-memory data management.

Features
Contact Lifecycle: Create and view contacts in a dynamic listing.
Server-Side Validation: Robust regex-based validation for emails and 10-digit phone numbers.
Thread-Safe Storage: Uses CopyOnWriteArrayList to ensure data integrity during concurrent session access.
PRG Pattern: Implements the Post-Redirect-Get design pattern to prevent duplicate form submissions on page refreshes.
Sanitized Inputs: Automatic trimming and basic sanitization of user-provided data.
Tech Stack
Language: Java 21
Specification: Jakarta EE 10 (Servlet 6.0, JSTL 3.0)
Build Tool: Maven 3.x
View Engine: JSP with JSTL
Container Support: Apache Tomcat 10.1+ (Targeted)
Prerequisites
Ensure you have the following installed:

Java Development Kit (JDK) 21
Apache Maven 3.9+
Apache Tomcat 10.1.x (Required for Jakarta EE 10 compatibility)
Setup & Installation
Clone the repository:
bash
git clone https://github.com/your-username/task3.git
cd task3
Build the project:
bash
mvn clean install
Start the application:
bash
mvn tomcat7:run
Access the application: Open your web browser and navigate to http://localhost:8080/contacts to access the contact management system.
File Structure
The project structure is as follows:

task3/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── task/
│   │   │           └── controller/
│   │   │               └── ContactServlet.java
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       └── contact-list.jsp
└── README.md
src/main/java/com/task/controller/ContactServlet.java: The main servlet class responsible for handling HTTP requests and responses.
src/main/webapp/WEB-INF/web.xml: The web application configuration file.
src/main/webapp/contact-list.jsp: The JSP file for displaying the contact list.
Contributing
Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a pull request.