# Household Management System 🏠

A Spring Boot + Thymeleaf web application to help households manage their regular purchases, daily item entries, and expenditure reports.  
Users can upload their **profile picture** and **cover image**, manage settings, and track expenses over time.

---

## 📌 Features
- **User Authentication** (Sign Up / Sign In)
- **Dashboard** with:
  - Profile Image & Cover Photo
  - Side menu for navigation
- **Add Item** page to store product name, price per unit, and image
- **Items** page to record daily usage
- **Expenditure Report** to view spending over a time range
- **Settings** page to update profile image, cover photo, and email
- Enquiry form on homepage that sends email to admin

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot
- **Frontend:** HTML, CSS, Thymeleaf
- **Database:** MySQL
- **Other:** Java, Maven

---


---

---

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/household-management.git

2.Open the project in your IDE (Eclipse, IntelliJ, etc.)

3.Create a MySQL database and update application.properties:

   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
4.Build and run the project:

  mvn clean install
  mvn spring-boot:run

5.Open the application in a browser:

  http://localhost:8080

  













