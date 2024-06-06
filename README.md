# CodeAlpha_Hotel_Reservation_System
-It includes a sign up or signing in with a previously created account and stored in the database
-a class Guest has the information about a user and has methodes like storeGuest,findGuest and some validation methodes
-all the hotel roomes are stored in the database
-super class room has the basic attributes of a room : roomNumber indicates the room number,price indicates the price of a day,underMaintenance indicates if the room is available or not
and methodes : findRoom that return the roomes who match the conditiones from the database, isRoomAvailable return if the room is booked in a current date or not, 
bookRoom bookes a room and add info to the data base, deleteRoom delete a room from the database, deleteReservation delete a reservation from the database and method findReservation return the reservation details
-sup-classes of class room are StandardRoom has an additional capacity attribut indicates number of people a room ca take ,
DeluxeRoom extendes StandardRoom has an additional hasJacuzzi attribut indicates if the room contain a Jacuzzi or not
Suite extendes DeluxeRoom has an additional suiteType and bedConfiguration attributes which indicates the type of a suite and the kind of bed it has
-a class dbConn responsible for connecting to the database
-after logging in the user can search for available roomes, book a room, cancel a book, view book details and Exit
-all exceptions are handled and all operations done on the database
