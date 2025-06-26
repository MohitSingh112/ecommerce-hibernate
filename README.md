# 🛒 Hibernate E-Commerce Product Catalog

A Hibernate-only Java project for managing a product catalog, including Products, Categories, Vendors, and Reviews. Built using core Hibernate ORM features — no Spring or external frameworks.

---

## 🚀 Features

- ✅ Full CRUD operations for:
  - Products
  - Categories
  - Vendors
  - Reviews
- ✅ DAO Layer using Hibernate Session
- ✅ Relational Mapping with:
  - `@ManyToOne`
  - `@OneToMany`
  - Cascade & Fetch strategies
- ✅ Lazy Initialization Handling
- ✅ HibernateUtil for centralized SessionFactory management
- ✅ Clean separation of model and persistence logic
- ✅ `.gitignore` and `.properties.example` for safe open-source sharing

---

## 🛠️ Technologies Used

- Java 17+
- Hibernate ORM
- MySQL (or any JDBC-compatible DB)
- SLF4J (for logging)
- Maven

---

## ⚙️ Getting Started

### 🔧 1. Clone the repository

```bash
git clone https://github.com/MohitSingh112/ecommerce-hibernate.git
cd ecommerce-hibernate
