---
layout: home
permalink: index.html

# Please update this with your repository name and title
repository-name: e19-co225-sharedspaces
title: SharedSpaces
---

[comment]: # "This is the standard layout for the project, but you can clean this and use your own template"

# SharedSpaces

---

# Department Space Management System - _**SharedSpaces**_
***

## Project Team
<a name="project_team"></a>
### Group - 1
* E/19/094 - Mansitha Eashwara
* E/19/129 - Kanishka Gunawardana
* E/19/372 - Kaushitha Silva
* E/19/408 - Sanduni Ubayasiri
  
## Table Of Contents


1. [Overview](#overview)
2. [Problem](#problem)
3. [Proposed Solution](#solution)
4. [UI Design](#ui_design)
5. [Technology Infrastructure](#tech)
6. [Data Flow](#data_flow)
7. [Back-End End Points](#backend)
8. [Features and Functionalities](#features)
9. [Links](#links)




## Overview

<a name="overview"></a>
A system to manage  the common spaces like labs , conference rooms , discussion  rooms and a lunch room in the department.  Lecturers and  instructors should have access to  book the common  spaces if they are available. As an example , Let’s say the lecturer  wants to book Computer Lab 01 but it is already booked in the particular time slot.  Then the lecturer can add him/her to the waiting list.  Whenever the previous reservation is canceled, the lecturer will get a notification about the cancellation with a request to confirm the reservation. Then the lecturer just has to confirm or cancel the reservation.



## Problem

<a name="problem"></a>
Efficiently manage reservations in shared spaces such as labs, lecture rooms, conference rooms, etc. Unorganized reservations result in conflicts, multiple bookings, and difficulty tracking reservations. The unavailability of a responsible person to have administrative privileges over the booking may result in unauthorized changes and cancellations.



## Proposed Solution
<a name="solution"></a>
Develop a reservation management system with a proper user hierarchy to efficiently resolve scheduling conflicts. 
### Proposed Prototype
![](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/prototype.jpeg)



## UI Design
<a name="ui_design"></a>
![ui home](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/ui%20design.jpeg)


## Technology Infrastructure
<a name="tech"></a>
**For the design of the layouts, these technologies were used.**

* Spring Boot
* React
* MySQL

![tech_stack](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/tech.PNG)




## Data Flow
<a name="data_flow"></a>
![data flow](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/data.PNG)


## Back-End End Points
<a name="backend"></a>
### **waiting-controller**


* POST
 /waiting


* DELETE
 /waiting


* GET
 /waiting/user


* GET
 /waiting/slot


* GET
/waiting/responsible

### **reservation-controller**


* GET
/reservation


* POST
/reservation


* DELETE
/reservation


* GET
/reservation/user


* GET
/reservation/responsible

### **log-controller**


* POST
/log

### **authentication-controller**


* POST
/auth/refresh-token


* POST
/auth/authenticate


### **space-controller**


* GET
/space

### **responsible-person-controller**


* GET
/responsible

***

![](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/swagger1.jpeg)

***


![](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/swagger2.jpeg)

## Features And Functionalities
<a name="features"></a>

### Features

#### Basic Features
* A user can reserve any space in the department by specifying a responsible person using a drop-down option.  
* A reservation can only be canceled by the user who made the reservation or by the responsible person.
* Users are able to check the reservation history of space for a range within 30 days into the past and the future. 
* If a user wants to reserve a space already reserved, they are added to the waiting list for that reservation (if the time overlaps). 
* If a reservation is canceled, the next on the waiting list is notified with a request to confirm the reservation. 

#### Additional Features
* Detailed descriptions about available spaces(capacity, available facilities, etc.)
* Users should be able to choose options according to their requirements(availability of labs, lecture rooms in a specific time slot, availability of a particular space in a specific day etc.)
* Provide suggestions about the available options when the requirements are provided.

### Home page

The home page of our department's space management system website displays a list of available spaces that can be reserved. Users can then proceed to the calendar section on the same page, where time slots are displayed for making reservations. To make a reservation, the user selects the desired space and specifies the date and time for the reservation. Once the reservation is confirmed, it is displayed in the corresponding time slot on the calendar. To access the reservation system, users are required to log in using their Google account credentials. This helps to ensure the security of the system and protect user information.

![home](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/home1.PNG)

Additionally, users can filter the available spaces by capacity and facilities, such as air conditioning (AC), projector and more.

![filer](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/filter.jpeg)


### Manage Reservations Page


The "Manage Reservations" page on our department's space management system website provides users with information about confirmed reservations and waiting lists, along with their corresponding details. Additionally, users are able to cancel reservations from this page if needed. This page serves as a central hub for reservation management, allowing users to easily view and manage their reservations in one place.

![manage res](https://github.com/cepdnaclk/e19-co225-sharedspaces/blob/main/docs/images/manage_res.jpeg)

## Links
<a name="links"></a>
* [Project Repository](https://github.com/cepdnaclk/e19-co225-sharedspaces)
* [Figma Prototype](https://www.figma.com/file/JciRD62qAJmoYZUwjaGisJ/Shared-Spaces?type=design&node-id=11277%3A4087&t=2Esa2U2EBHtakZ2U-1)
* [Department of Computer Engineering](https://www.ce.pdn.ac.lk)


[//]: # (Please refer this to learn more about Markdown syntax)
[//]: # (https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
