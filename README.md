# Mersad: Smart Attendance System

> Managing university attendance shouldn't take half the lecture. Mersad makes tracking student attendance fast, reliable, and hassle-free using smart QR code simulation. Designed to help universities keep accurate records while letting instructors focus on what matters most: teaching.

## About the Project
Mersad is a Java-based Object-Oriented Programming (OOP) project that simulates an attendance tracking system. It allows instructors to seamlessly manage students, course materials, and daily attendance records. By streamlining the traditional roll-call process, it offers a practical, modern solution for educational institutions.

## Project Structure
Here is an overview of the file system hierarchy and main directories in the project:

```text
mersad/
├── src/
│   └── main/
│       ├── java/
│       │   └── haga_talga/
│       │       ├── app/          - Application entry point
│       │       ├── model/        - Core project classes
│       │       ├── page/         - UI and page components
│       │       ├── service/      - Hardware integration, file I/O, and external services
│       │       └── util/         - Utility and helper classes
│       └── resources/            - Data storage
│           ├── attendanceExcle/  - Exported .xlsx attendance files
│           ├── CourseStudent/    - .json attendance files
│           ├── courses.json      - Courses data
│           └── doctors.json      - Doctors data
├── pom.xml                       - Maven project configuration
├── LICENSE
├── .gitignore
└── README.md
```

## Prerequisites
Before you start, make sure you have the following installed on your machine:
- **Java** (JDK 17 or higher)
- **Maven** (If you plan to build or run the project from source)

## How to Download
You can get a copy of the project on your local machine using one of the following methods:

### Option 1: Forking & Cloning (Recommended)
- Go to the project repository page.
- Click the **Fork** button in the top right corner to create a copy in your GitHub account.
- Clone your forked repository:
  ```bash
  git clone <your-forked-repository-url>
  cd mersad
  ```

### Option 2: Direct Clone
- If you don't need to fork, simply clone the repository directly:
  ```bash
  git clone <repository-url>
  cd mersad
  ```
- *(Alternatively, you can download the project as a ZIP file from the repository page and extract it).*

## Running the Application

### Option 1: Running via the .jar file
If you just want to run the application quickly without building it yourself:
- Ensure **Java** is installed.
- Locate the compiled `.jar` file in the project folder (usually inside the `target/` directory).
- Open your terminal or command prompt.
- Run the following command:
  ```bash
  java -jar target/mersad.jar
  ```

### Option 2: Building and Running using Maven
If you prefer to compile and run the project from source:

#### Building the project
- Ensure both **Java** and **Maven** are installed.
- Open your terminal in the root directory of the project.
- Build the project with Maven using:
  ```bash
  mvn clean install
  ```
- *(`mvn compile` will just compile the source code, while `mvn install` compiles, tests, and packages the application into a `.jar` file in your `target/` folder).*

#### Running the project
- Once built, you can easily run the application using Maven's exec plugin:
  ```bash
  mvn exec:java
  ```

---

## Acknowledgments
A very special thanks to the incredible faculty members who made this project possible:

- **Dr. Sara Shehab**  
  *Programming-2 OOP Instructor* - Thank you for your guidance throughout the course.

- **Eng. Abdallah Hemly, Eng. Israa Maged, & Eng. Nader Gamal**  
  *Course Teaching Assistants* - We appreciate your continuous support, feedback, and help during the labs.

- **Dr. Ibrahim Selim**  
  *Faculty Dean* - Thank you for fostering an excellent academic environment that encourages practical, hands-on learning.
