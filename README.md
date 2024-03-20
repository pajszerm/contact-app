# Contact-app

Contact-app is an interface where users can create, store, delete and display their contacts.

## Install

Starting database container:

```
docker run --hostname=59f9a920943f --mac-address=02:42:ac:11:00:02 --env=POSTGRES_PASSWORD=password --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=LANG=en_US.utf8 --env=PG_MAJOR=16 --env=PG_VERSION=16.2 --env=PG_SHA256=446e88294dbc2c9085ab4b7061a646fa604b4bec03521d5ea671c2e5ad9b2952 --env=DOCKER_PG_LLVM_DEPS=llvm15-dev	clang15 --env=PGDATA=/var/lib/postgresql/data --volume=/var/lib/postgresql/data -p 5432:5432 --restart=no --runtime=runc -d postgres:alpine
```

I suggest high-availabe database with regular backups in production, this is only for demo purposes.

The database is created on the 5432 port, and the application assumes it this way.


Starting backend:

Create a new project in IntelliJ IDEA with "Starting new project from version control" and copy this link: https://github.com/pajszerm/contact-app.git

Start the backend using the IDE built in functionality.

Starting frontend:

Change directory to the /angular-frontend directory in terminal.

Run commands:

```
npm i
ng s
```


## Backend

Used technologies:
- Java version 17
- SpringBoot version 3.2.3
- Database: PostgreSQL

#Structure:

Domain classes:
- UserInfo
- Address
- PhoneNumber
  
The UserInfo class contains the main details about the contact, it can also contain multiple Addresses and PhoneNumbers.

Controllers:

-UserController

It manages all the functions:
- Creating new contacts
- Sending contact data to the frontend
- Delete contact data

Services:
- UserService
- PhoneNumberService
- AddressService
  
The separate services use separate repositories.

## Frontend

Used technologies:
- Angular version 14.2.0
- Bootsrap
- Angular Material


## Future developement

In the given period I cloud only create the functionalities mentioned above, but I have plans to continue the development.

- Implement security with JWT token. It is self-contained and carries all the necessary information which eliminates the need for a server-side session store.
- Create a User class, that people can use for registration and login. It would contain the already created UserInfo classes as contacts.
- Create unit tests and integration tests to ensure flawless operation.
- Expand the validation on backend and frontend.
