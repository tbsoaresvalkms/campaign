### Creating a customer
Resource: `POST /customer` 

##### Request
``` json
{
	"fullName": "Thiago batista",
	"email": "customer1@email.com",
	"team": "team 3",
	"birthdate": "2018-01-01"
}
```


##### Response (new customer)

``` json
{
    "message": "Customer successfully created",
    "customer": {
        "id": 1,
        "email": "customer1@email.com",
        "fullName": "Thiago batista",
        "team": "team 3",
        "birthDate": "2018-01-01"
    }
}
```


##### Response (already registered, but without available campaign)

``` json
{
    "message": "Customer already registered"
}
```

##### Response (already registered, with available campaign)

``` json
{
    "message": "Customer already registered",
    "availableCampaigns": [
        {
            "id": 12,
            "name": "Campaign Name ",
            "team": "TEAM 3",
            "startDate": "2018-01-11",
            "endDate": "2018-01-21",
            "createdAt": "2017-11-15T23:42:27.757",
            "updateAt": "2017-11-15T23:42:29.541"
        },
        {
            "id": 13,
            "name": "Campaign Name ",
            "team": "TEAM 3",
            "startDate": "2018-01-11",
            "endDate": "2018-01-20",
            "createdAt": "2017-11-15T23:42:27.942",
            "updateAt": "2017-11-15T23:42:29.541"
        }
    ]
}
```

### Enroll customer in all available campaign
Resource: `PUT /customer`
##### Request
``` json
{
	"email": "customer1@email.com"
}
```

##### Response

``` json
{
    "message": "Customer enrolled in the campaign successfully",
    "status": "CREATED"
}
```
