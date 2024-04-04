# Simple-Web-Based-Email-Program

## Overview
This project aims to implement the basic functionalities of a mail server using design patterns, including the manipulation of mails, attachments, contacts, etc.

## Features
- **Inbox Folder:** All incoming mails are stored here by default. Mails can be categorized by priority.
- **Trash Folder:** Mails are automatically deleted after 30 days.
- **Composing and Drafts:** Users can compose new mails and save them as drafts for later editing and sending.
- **Sent Mails Folder:** Stores copies of sent mails.
- **User Folders:** Users can create, rename, and delete custom folders for organizing mails.
- **Filters:** Users can set up filters to automatically categorize incoming mails based on subject or sender and direct them to specific folders.
- **Searching and Sorting:** Users can search and sort mails based on various attributes such as date, sender, receivers, importance, subject, body, attachments, etc.
- **Adding and Deleting Attachments:** Users can add or remove attachments from mails.
- **Viewing Attachments:** Users can download attachments directly within the application and view it.
- **Adding, Editing, and Deleting Contacts:** Users can manage their contacts by adding new contacts, editing existing ones, and deleting unwanted contacts.
- **Searching and Sorting Contacts:** Users can search for specific contacts and sort them based on different attributes.
- **Contact Information:** Each contact can have a name and one or multiple email addresses.

## Used design patterns
### Builder Design Pattern

Applying the Builder design pattern for both user and mail objects. This pattern encapsulates construction logic within an internal static class called Builder, simplifying object creation via a fluent interface.

### Filter Design Pattern

Individual filter classes such as PriorityFilter, SenderFilter, and SubjectFilter implement a common interface called FilterIF. This interface defines methods for filtering based on multiple criteria, allowing for sequential application of filters.

### Factory Design Pattern

The Criteria Factory is responsible for creating instances of different filter classes based on desired criteria. This encapsulates the logic of determining the appropriate filter class to use based on input criteria.

### Adapter Design Pattern

Implementing the Adapter design pattern for handling save and load operations. This pattern translates data between different formats, enabling the mail server to save and load data in a standardized manner, thus achieving compatibility with various file formats or data representations.

### Strategy Design Pattern

Strategy pattern is used for searching and sorting types. It allows for defining a family of algorithms, encapsulating each one, and making them interchangeable. This provides flexibility in choosing different algorithms for searching and sorting based on requirements.

### Chain of Responsibility

This pattern handles signup processes and navigation to the home page. When a user attempts to log in or sign up, the request passes through a series of checks, each responsible for specific tasks. If one check cannot handle the request, it passes it to the next check in the chain until it's handled appropriately.

# Design decision

- **Loading User Data:** All user data is loaded into the backend when the program opens, ensuring quick access to user information throughout the program's runtime.

- **Item Removal Handling:** When removing items from folders in the mail server, they are not automatically sent to the trash, providing users with control over managing their mailbox content.

- **Filtering with User Search Capability:** Implemented filtering based on two predefined criteria while allowing users the flexibility to search for mails using any criteria they require. This provides a predefined set of filtering options covering common use cases while accommodating user-specific filtering needs.

- **Static Contact Page:** The contact page in our mail server is static, and users cannot send emails directly from email addresses saved in the contact information. This ensures the security and integrity of contact data.

- **Saving Changes after Frontend Modifications:** Any modifications made in the frontend are automatically saved to a file, ensuring that changes persist across sessions and improving data consistency.

## License
- This project is licensed under the MIT License.
