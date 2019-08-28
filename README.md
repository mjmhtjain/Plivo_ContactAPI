# ContactsAPI

Contacts API provides API implementations for adding/editing/deleting contacts.

The APIs are protected with Basic Authentication (username: user, password: user).

All the APIs accept requests in JSON format and responds in JSON format.

The application is deployed @ [https://stormy-atoll-93915.herokuapp.com/]()

## List of APIs and Usage

1. **/getAllContacts** : accepts GET request and returns a list of all available contacts

```bash
curl --user user:user https://stormy-atoll-93915.herokuapp.com/getAllContacts
```

2. **/saveContact** : accepts POST with contact payload in body of request and returns a copy of the saved contact in DB. 

```bash
curl --user user:user \
-X POST -H "Content-Type: application/json" \
 -d '{"name":"jlk","emailAddress":"email@email.com","phoneNumber":"12312312"}' \
 https://stormy-atoll-93915.herokuapp.com/saveContact
```

3. **/getContactByName** : accepts GET request with name param and returns a contact.

```bash
curl --user user:user \
https://stormy-atoll-93915.herokuapp.com/getContactByName?name=jlk
```

4. **/getContactByEmail** : accepts GET request with email param and returns a contact.

```bash
curl --user user:user \
https://stormy-atoll-93915.herokuapp.com/getContactByEmail?email=email@email.com
```

5. **/deleteContact** : accepts GET request with email param and returns the count of deleted contacts from the the DB.

```bash
curl --user user:user \
https://stormy-atoll-93915.herokuapp.com/deleteContact?email=email@email.com
```

6. **/editContact** : accepts POST request with contact payload in body and updates the given contact.

```bash
curl --user user:user \
-X POST -H "Content-Type: application/json" \
 -d '{"id":"-46", "name":"jlk","emailAddress":"email@email.com","phoneNumber":"12312312"}' \
 https://stormy-atoll-93915.herokuapp.com/editContact
```


## Contribution
Contributed by Mohit Jain. Suggestions and criticism are welcome.

## Contact

* EmailAddress : mjmhtjain@gmail.com
* Phone Number : 9639891413

## License
[MIT](https://choosealicense.com/licenses/mit/)
