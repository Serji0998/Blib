# 📚 Blib: Distributed Library Management System
> A robust, Client-Server Java application for managing library operations, built with high-performance networking and a relational SQL backend.

---

## 🖥 System Architecture
Blib is built on a **Client-Server model** using **TCP/IP Socket Programming**. This allows the library database to be hosted centrally while librarians and students interact via remote desktop clients.



## 🌟 Key Features

### 📖 For Subscribers (Readers)
* **Search & Discover:** Full catalog search for books.
* **Borrowing System:** Real-time borrowing and automated return tracking.
* **Reservation Queue:** Order books that are currently checked out.
* **Personal History:** View a complete log of previously borrowed books.

### 👩‍💼 For Librarians (Admin)
* **Inventory Management:** Add and track physical book copies.
* **Member Management:** Create and manage subscriber profiles and contact info.
* **Manual Overrides:** Manual book returns and reservation extensions.
* **Administrative Insights:** Access detailed subscriber data and borrowing statistics.

---

## 🛠 Tech Stack
* **Language:** Java
* **Database:** MySQL (Relational Database Management)
* **Networking:** Java Sockets (TCP/IP)
* **UI:** JavaFx

---

## 🏗 Database Schema
The system utilizes a relational database to maintain data integrity across:
* **Books & Copies:** Tracking ISBN, title, and availability.
* **Subscribers:** Managing membership levels and contact details.
* **Transactions:** Logging all borrow/return events for auditing.



---

## 🚀 Getting Started

### Prerequisites
* **Java Runtime Environment (JRE):** Version 8 or higher.
* **MySQL Server:** Running locally or on a remote host.
* **MySQL Connector/J:** Java Database Connectivity (JDBC) driver.

### Setup Instructions
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Serji0998/Blib.git](https://github.com/Serji0998/Blib.git)

## 🎓 Academic Context: Mid-Semester Project
This project was developed during my **Software Engineering degree (Semester 6)**. The primary focus was transitioning from individual coding to a professional, team-based development environment.

### 🎯 Key Learning Objectives:

* **Collaborative Development:** Worked within a coordinated team to simulate a real-world engineering environment, focusing on communication and shared codebase management.
* **SDLC Mastery:** Gained hands-on experience across the full **Software Development Life Cycle**, including:
    * **Requirements & Design:** Planning system architecture before implementation.
    * **Standardized Development:** Writing clean, modular code to ensure seamless integration with teammates' work.
    * **Testing & QA:** Implementing rigorous testing to identify bugs and ensure a stable, "user-ready" product.
    * **Deployment:** Preparing and delivering a functional application for evaluation.
* **Version Control & Integration:** Focused on the technical challenges of merging different modules and understanding how individual components fit into a larger, complex system.


  
# GUI:
![MainPage](https://github.com/user-attachments/assets/b647202c-9fe4-4673-afb7-88197f120c64)

![LibrarianPage](https://github.com/user-attachments/assets/1dd2f641-364d-4b63-a804-bdf2cab32f8f)

![BookDetails2](https://github.com/user-attachments/assets/e7e8e683-a4e3-46f0-81ec-dd9a93b8591e)

![settings](https://github.com/user-attachments/assets/bbedb4e7-83b7-4730-8053-afd829fd51dd)
