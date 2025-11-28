# Capstone-Student-Record-Management-System
A modular Java application that manages student records with persistent storage, exception handling, multithreading (loading simulation), and the Java Collections Framework.

**Capstone Student Record Management System** is a Java console application that demonstrates practical application of core software engineering concepts:

- **Object-Oriented Programming** (abstraction, inheritance, interfaces)  
- **Persistent storage** using file handling (`students.txt`)  
- **Java Collections Framework** (`ArrayList`, `HashMap`)  
- **Sorting** via `Comparator` (by marks)  
- **Displaying data** using `Iterator`  
- **Random file access** via `RandomAccessFile`  
- **File attributes** using the `File` class  
- **Multithreading** (loader simulation) and **exception handling**


---

##  Features

- **Load** students from `students.txt` at startup  
- **Add** new students (roll no, name, email, course, marks)  
- **View** all students (with `Iterator`)  
- **Search** students by name (case-insensitive)  
- **Delete** students by name  
- **Sort** students by marks (descending) using a `Comparator`  
- **Save** updated records back to `students.txt` on exit  
- **Show file attributes** (`exists`, `size`, `readable`, `writable`, `lastModified`)  
- **Random read** of file content using `RandomAccessFile`  
- **Loader simulation** (multithreading) used for long operations (if implemented in manager)  
- **Exception handling** for numeric parsing and I/O errors
