### Enroll a campaign
Resource: `POST /associate` 

##### Request
``` json
{
	"campaignId": 3,
	"email": "email@email.com"
}
```


##### Response

``` json
{
    "message": "Successfully subscribed",
    "status": "CREATED"
}
```

### Showing campaigns by email
Resource: `GET /associate/campaign?email={email}`

##### Response

``` json
[
    {
        "id": 6,
        "name": "Campaign Name ",
        "team": "TEAM NAME",
        "startDate": "2018-01-11",
        "endDate": "2018-01-17",
        "createdAt": "2017-11-15T23:21:22.619",
        "updateAt": "2017-11-15T23:21:23.563"
    },
    {
        "id": 7,
        "name": "Campaign Name ",
        "team": "TEAM NAME",
        "startDate": "2018-01-11",
        "endDate": "2018-01-16",
        "createdAt": "2017-11-15T23:21:22.805",
        "updateAt": "2017-11-15T23:21:23.562"
    }
]
```
