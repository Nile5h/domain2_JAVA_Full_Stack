# Contact Management System (Jakarta EE 10)

[![Java Version]](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Jakarta EE]](https://jakarta.ee/)
[![Maven]](https://maven.apache.org/)

A robust, session-based web application for managing personal contacts. Built with modern Jakarta EE 10 specifications, this project demonstrates clean MVC separation, server-side validation, and thread-safe in-memory data management.

## Features

- **Contact Lifecycle**: Create and view contacts in a dynamic listing.
- **Server-Side Validation**: Robust regex-based validation for emails and 10-digit phone numbers.
- **Thread-Safe Storage**: Uses `CopyOnWriteArrayList` to ensure data integrity during concurrent session access.
- **PRG Pattern**: Implements the Post-Redirect-Get design pattern to prevent duplicate form submissions on page refreshes.
- **Sanitized Inputs**: Automatic trimming and basic sanitization of user-provided data.

## Tech Stack

- **Language**: Java 21
- **Specification**: Jakarta EE 10 (Servlet 6.0, JSTL 3.0)
- **Build Tool**: Maven 3.x
- **View Engine**: JSP with JSTL
- **Container Support**: Apache Tomcat 10.1+ (Targeted)

## Prerequisites

Ensure you have the following installed:
- **Java Development Kit (JDK) 21**
- **Apache Maven 3.9+**
- **Apache Tomcat 10.1.x** (Required for Jakarta EE 10 compatibility)

## Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/task2.git
   cd task2
   ```

2. **Build the WAR file:**
   ```bash
   mvn clean package
   ```
   This will generate `task2.war` in the `target/` directory.

## How to Run

### Deployment to Tomcat
1. Copy the generated `target/task2.war` file.
2. Paste it into your Tomcat installation's `webapps/` folder.
3. Start Tomcat using `bin/startup.sh` (Linux/macOS) or `bin/startup.bat` (Windows).

### Accessing the Application
Once the server is running, access the following endpoints:
- **Contact List**: `http://localhost:8080/task2/contacts`
- **Add Contact Form**: `http://localhost:8080/task2/contacts/add`

## Validation Rules

The application enforces the following rules during contact creation:
| Field | Rule |
| :--- | :--- |
| **Name** | Required, 2-50 characters. |
| **Email** | Required, must follow standard email format. |
| **Phone** | Optional, but must be exactly 10 digits if provided. |

## Project Structure

```text
task2/
├── src/main/java/com/task/
│   ├── controller/    # Servlet controllers (Routing & Logic)
│   └── model/         # Contact POJO
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── views/     # JSPs (contact-list, contact-form)
│   │   └── web.xml    # Deployment Descriptor
└── pom.xml            # Maven Configuration
```

## Contributing

1. Fork the Project.
2. Create your Feature Branch (`git checkout -b feature/NewFeature`).
3. Commit your Changes (`git commit -m 'Add NewFeature'`).
4. Push to the Branch (`git push origin feature/NewFeature`).
5. Open a Pull Request.

## License

Distributed under the MIT License.
