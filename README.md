# 🏠 Hostel Complaint Management System (HCMS)

A desktop-based application designed to streamline hostel complaint handling across student, admin, and worker roles. Built using JavaFX and SQLite, with integrated DevOps pipelines for automated build, test, and deployment.

---

## 🚀 Features

- 📝 Students can submit and track complaints
- 🛠️ Admins assign tasks and monitor resolutions
- 👷 Workers update status via dedicated dashboard
- 🔄 Real-time updates and notifications
- 🧪 Unit-tested with JUnit
- ⚙️ CI/CD via GitHub Actions, Jenkins, and Docker

---

## 🧱 Tech Stack

| Layer         | Technology             |
|---------------|------------------------|
| Frontend      | JavaFX (FXML)          |
| Backend       | Core Java              |
| Database      | SQLite                 |
| Build Tool    | Maven                  |
| DevOps        | GitHub Actions, Jenkins, Docker |

---

## 📦 Modules

- **Student Portal**: Complaint submission and tracking
- **Admin Panel**: Task assignment and status updates
- **Worker Dashboard**: View and update assigned complaints
- **DevOps Integration**: Multibranch pipeline with CI/CD
- **Authentication**: Role-based access control

---

## ⚙️ Setup Instructions

1. Install Java 17, Maven, and SQLite.
2. Clone the repo:
   ```bash
   git clone https://github.com/Sowmiya05-SK/Naan-mudhalvan--Devops.git
3. It will clone all branches and you run each branch using the below cmd.
4. Run Maven to build and launch:
   ```bash
     mvn clean install
     mvn javafx:run
5. After running then change the branch using checkout cmd.
   ```bash
      git checkout sowmiya
6. Repeat the step 4 for each branch
7. The database (hcms.db) initializes automatically on first run.

---
## 📊 DevOps Pipeline

- Triggered on push/pull via GitHub webhook
- Jenkins multibranch pipeline detects dev, test, prod
- Stages: Build → Test → Package → Deploy
- Docker used for containerized deployment

---
## 📫 Contact & Contributors
**Developed by**:
- NIVETHITHA S
- RAKAVI R
- RASIGA M
- SOWMIYA SK

Government College of Engineering, Erode

October 2025
