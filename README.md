# README.md
The purpose of this report is to give a detailed description of the requirements for the Help Hunters web application. It will explain the functions for the users and members of the company, and how this software will improve the way of getting a job and new clients.

The purpose of this part of the project is to provide a guide to the team, to set the objectives and time frames each of us have. This document leaves the objectives clear for the project as a whole, and dives deep into each subcomponent of the project, including a detailed description of the requirements or the web application. Then, we set the functions for the users and application managers of the company, and how this software will improve the situation we are in.

## 1) Introduction
The initiative for this application is the urging problem that is caretaking for the elderly all over the world, especially in Europe.  This application is intended to facilitate this process, creating a gateway between caretakers and elders from all over the world.

This is a web application that shows the closest caretaker or available job according to the city they are and the requirements each user has defined. An administrator will be monitoring the process, and taking into account any incidences that may occur assuring an excellent service overall.

As stated before, we will be developing a web application. Therefore, an internet connection is required. This will be a government funded project, they will assume the hiring cost for the administrators. All the information is saved in a database. 

## 2) Functionalities
App Function:
This application will be the intermediary between the clients and the caretakers, as we just explained. The connection of said parties will be achieved with requests. In order for both parties to make a tailored decision, reviews will be made available to both parties. This feedback will help both caretakers and clients before making any sort of agreement.

User profile:
This application is comprised of three types of users. The first type of user is the caretaker. The caretakers start the user experience when they sign up and get their credentials authenticated by the application managers. When admitted into the application, they will be visible to the clients and will start receiving requests. Notifications for requests will be available and the caretaker will be able to accept it or decline it. 

The second type of user is the client. The elderly in need of a caretaker. The client will create a user and then be able to surf all of the caretakers that are available, with search and filter functions for his or her desired query. When the client finds the caretaker that best suits his or her preferences, then he can make a request.

The third type of user is the application manager, who will make sure that all of the caretakers are qualified and that if someone gets out of the system, then he or she gets removed from the database as well.

Constraints:
Because we are software students and the technologies that we are using are not that advanced, we do have a set of constraints for this project given the time we have been given to develop it.

Database functions:
The database that we will use, mySQL, is cloud-based, and it provides many tools for our software development team. The main database functions that will be implemented in this project are inserting, updating, and deleting rows in our tables. Also, the database will update in real time while we are running the project so that everyone gets to have the same database.

## 3) Functional Requirements
This section contains all of the requirements of the system. It gives a detailed description of the system and all its features.

### User type 1 : The Administrator
#### Administrator Login (ID:FR1)
In order to administer the system, an administrator should be logged in to the web-portal. Each administrator of the app has its own username and password that will allow them to enter into their administrator’s account. 
Scenario: Successful log-in 
When the administrator logs in with an administrator account (checking the corresponding checkbox), then the administrator should be logged in as an administrator.

#### Change Password (ID:FR2)
If an administrator wants to change his/her account password, he/she can do it after entering their account by clicking on “Settings” in the administrator’s homepage. 
Scenario: Passwords match
If when clicking on the “submit” button both passwords match, the worker’s password will be updated.
Scenario: Passwords don’t match
If when clicking on the “submit” button both passwords don’t match, the page will reload with empty textboxes and a message saying that both passwords didn’t match.
Manage Caretakers
In order to have a catalog of workers and to grant them access to the system, administrators should be able to manage the web page’s list of workers.

#### Introduce caretaker into the system (ID:FR3)
The app administrators will be the ones in charge of registering qualified personnel in the web page. The administrators should make sure that all of the applicants for a job in nursing the elderly meet all the needed requirements. If an applicant is truly qualified for the job, an administrator will include him in the database.
Scenario: Approve applicant
Given the administrator is logged in, when the administrator registers a qualified worker, then the new worker should be able to login. The administrator will send a username and password by mail.

#### Delete Clients from the Database (ID:FR4)
In case of death or under request of a worker, the web page managers can delete a client as well as a worker from the catalog if they have too many reports.
Scenario: Delete a Caretaker
When the administrator deletes a worker, the deleted person should be removed from the list of workers and don’t appear in the catalog anymore.

#### Revise the Caretaker’s Incidents (ID:FR5)
Revise the reported incidents written by the clients, and make decisions regarding the worker’s position in the platform.

#### Warning (ID:FR6)
Whenever a client writes an incident and sends it to the web administrators, these will advise the worker with a warning. The worker will then be able to see this through his profile. 

#### Delete Caretaker’s Account (ID: FR7)
When administrators decide that a worker has done something unacceptable and therefore can’t be displayed in the catalog, the administrators will be able to delete the caretaker completely from the database and his/her account accordingly.

#### List of workers with filters (ID: FR8)
The administrator can see the list of workers in the platform filtering with a specific filter in one of the database’s fields.
Manage Clients:
The administrators should be able to know and have control of the clients who are interested in hiring caretakers from their web-page.

#### View all the Clients who use the web-page (ID:FR9)
Administrators should be able to see the list of clients who have accounts and use the platform.

#### View all the Clients with filters (ID:FR10)
The administrators should be able to apply filters when searching for employers in the webpage to filter and have a control of how many users the page has in each city and in each state for example. Or what is the most common job it’s users want workers to take care of.

#### Delete a client (ID:FR11)
Administrators should be able to delete clients in case of death. When the administrator deletes a client, the deleted person should be removed from the list of clients and don’t appear in the catalog anymore as well as the offered job.

#### Logout  (ID:FR12)
The administrator will be able to log out his/her session once he has finished to use the web-page by clicking int this option.

### User type 2 : The Caretaker
#### Caretaker Login (ID: FR13)
In order to use the system a worker who has been previously approved by the administrators should be logged in to the web-portal by checking the checkbox marked “Caretaker” in the log-in page and introducing his/her username and password. This will be originally assigned by the administrators, and have to change it however, workers can later on change their login password.
Scenario: Successful log-in
Given the worker wants to log in, when he/she logs in with his/her account, then he/she should be logged in as a worker.
Caretaker’s Dashboard
Here the worker can find 5 different features:

#### Dashboard (ID: FR14)
A caretaker can see how many job offers he/she has received and how many views his/her profile has had. Also, his/her profile views will be displayed on a line graph, comparing them to previous months.
Due to this feature, workers also have the opportunity to add reminders in the notes sections. Each reminder can be assigned a level of importance.  Caretakers will receive notifications and warnings sent by the administrators through their dashboard.

#### Messages (ID: FR15)
Caretakers may receive messages from people who are interested in hiring them. These messages will also appear in their dashboard and they will be able to answer them directly from the web-page.

#### Reviews (ID: FR16)
In this section caretakers can see both, the reviews they have received from their employers and the ones they have wrote to rate the clients they have worked with.
If there are pending reviews they have to write, these will also appear in this feature.
Manage Jobs 
Caretakers will be able to manage the job proposals they receive. This proposals have expirations dates set by the web-page, and if the worker does not respond to the proposal during the established date, this one will expire and the caretaker will no longer be able to take the job. They can decide whether to accept a specific job or not by clicking on one of the two buttons: one intended to accept it and another intended to decline it.
 
#### Accepting a work proposal (ID: FR17)
When accepting a work proposal, his status in the database will automatically change to unavailable and a new record will be created which links the worker to the client who offered him the job, in order to keep track of which worker is working for each client.

#### Rejecting a work proposal (ID: FR18)
If a worker declines a work proposal, that proposal will be eliminated and the client who sent it will be notified about it.
Settings
In this section, the caretaker will be able to modify information.

#### Manage Personal Information (ID: FR19)
A worker is going to be able to change and update his personal information displayed in his/her profile. Once the worker submits the new information, it should be updated in the database. 

#### Password (ID: FR20)
If a caretaker wants to change his/her account’s password, he/she will be able to do so through settings. The worker will have to introduce the password twice in case he/she accidentally makes a typo mistake.
Scenario: Passwords match
If when clicking on the “submit” button both passwords match, the worker’s password will be updated.
Scenario: Passwords don’t match
If when clicking on the “submit” button both passwords don’t match, the page will reload with empty textboxes and a message saying that both passwords didn’t match.

#### Logout (ID: FR21)
Caretakers will also have the option to logout from their account once they’re done using the web-page.

### User type 3: The Client
#### Create an account (ID: FR22)
There will be an option in the HTML index login page saying “Create an account”. When clicking on the option, a form will appear for the the new client to fill in. He/she will be ask to provide a username (a username is unique for each employer), a password and all the other fields required to add him/her in the database.
Scenario: The Client has successfully created a new account
If the username is unique and information was provided correctly for all the existing fields, then the new employer will be added to the database and he/she will be able to login into his/her new account.
Scenario: The username provided is not unique
In this case the page will reload and a message will appear on screen asking the employer to pick another username.

#### Client Login (ID: FR23)
In order to use the system, an employer must login in the index Login page checking the checkbox marked as “Client”.
Scenario: Successful log-in
Given the client wants to log in, when he/she logs in with his/her username and password, then he/she will be logged in as an employer.
Look for workers to hire
If employers are looking for workers to hire, they can consult the company’s catalog. 

#### Search for workers in their area (ID: FR24)
Clients will be able to look for caretakers who work in a specific city or region specified by the client.

#### Send Job Requests to the Workers (ID: FR25)
The employers should be able to send job proposals to the workers. When wanting to send a request, they should fill in a form specifying the job they would be hiring for. This job request will be sent to the worker, who will have the choice of whether to accept it or not.

#### Leave reviews about workers (ID: FR26)
In the client sidebar, there will be a review page in which the client can leave reviews for the caretakers. They will also be able to see the reviews the caretakers have left for them. When submitting the review, this will be saved for other clients to view.

#### Message (ID: FR27)
The client will be able to message potential and current caretakers and coordinate future agendas.
Settings

#### Modify Personal Information (ID:FR28)
The client will be able to modify their basic information in case anything was introduced incorrectly or they desire a change. The fields that they will be able to modify are name and email.

#### Change Password (ID:FR29)
The client will be able to reset their password. 

#### Logout (ID:FR30)
The client will be able to logout from their account and will be taken back to the main page.

## 4) Storyboard
