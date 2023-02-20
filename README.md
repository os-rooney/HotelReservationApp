# Hotel Reservation Application (CLI)

## Application Architecture
**User interface (UI)**, include a main menu for the users who want to book a room, and an admin menu for administrative functions.

**Resources** acts as Application Programming Interface (API) to UI.

**Services** communicates with our resources, and each other, to build the business logic necessary to provide feedback to our UI.

**Data models** is used to represent the domain that we're using within the system (e.g., rooms, reservations, and customers).

## Layers
This architecture is how I use layers to support modularization and decoupling. 
For example, If I later decided to change the UI components to a webpage instead of a command-line interface, layering would support this.

---

## User Scenarios
The application provides four user scenarios:
- **Creating a customer account**. The user needs to first create a customer account before they can create a reservation.
- **Searching for rooms**. The app should allow the user to search for available rooms based on provided checkin and checkout dates. If the application has available rooms for the specified date range, a list of the corresponding rooms will be displayed to the user for choosing.
- **Booking a room**. Once the user has chosen a room, the app will allow them to book the room and create a reservation.
- **Viewing reservations**. After booking a room, the app allows customers to view a list of all their reservations.

## Admin Scenarios
The application provides four administrative scenarios:
- Displaying all customers accounts.
- Viewing all of the rooms in the hotel.
- Viewing all of the hotel reservations.
- Adding a room to the hotel application.
