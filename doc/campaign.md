### Creating a new campaign
Resource: `POST /campaign`

##### Request
``` json
{
	"name": "Campaign Name ",
	"team": "Team Name",
	"startDate": "2018-01-11",
	"endDate": "2018-01-12"
}
```


##### Response

``` json
{
    "id": 1,
    "name": "Campaign Name ",
    "team": "TEAM NAME",
    "startDate": "2018-01-11",
    "endDate": "2018-01-12",
    "createdAt": "2017-11-15T23:19:10.372",
    "updateAt": "2017-11-15T23:19:10.372"
}
```

### Showing a campaign
Resource: `GET /campaign/:id`

##### Response

``` json
{
    "id": 2,
    "name": "Campaign Name ",
    "team": "TEAM NAME",
    "startDate": "2018-01-11",
    "endDate": "2018-01-21",
    "createdAt": "2017-11-15T23:21:21.846",
    "updateAt": "2017-11-15T23:21:23.567"
}
```

### Listing campaigns
Resource: `GET /campaign`

##### Response

``` json
[
    {
        "id": 2,
        "name": "Campaign Name ",
        "team": "TEAM NAME",
        "startDate": "2018-01-11",
        "endDate": "2018-01-21",
        "createdAt": "2017-11-15T23:21:21.846",
        "updateAt": "2017-11-15T23:21:23.567"
    },
    {
        "id": 4,
        "name": "Campaign Name ",
        "team": "TEAM NAME",
        "startDate": "2018-01-11",
        "endDate": "2018-01-19",
        "createdAt": "2017-11-15T23:21:22.222",
        "updateAt": "2017-11-15T23:21:23.565"
    }
]
```

### Listing campaigns by team
Resource: `GET /campaign/team/{team}`

##### Response

``` json
[
    {
        "id": 2,
        "name": "Campaign Name ",
        "team": "TEAM NAME",
        "startDate": "2018-01-11",
        "endDate": "2018-01-21",
        "createdAt": "2017-11-15T23:21:21.846",
        "updateAt": "2017-11-15T23:21:23.567"
    },
    {
        "id": 4,
        "name": "Campaign Name ",
        "team": "TEAM NAME",
        "startDate": "2018-01-11",
        "endDate": "2018-01-19",
        "createdAt": "2017-11-15T23:21:22.222",
        "updateAt": "2017-11-15T23:21:23.565"
    }
]
```

### Changing a campaign
Resource: `PUT /campaign/{id}`

##### Request
``` json
{
    "name": "New Name",
    "team": "New Team",
    "startDate": "2018-01-01",
    "endDate": "2018-11-18"
}
```

##### Response
``` json
{
    "id": 8,
    "name": "New Name",
    "team": "NEW TEAM",
    "startDate": "2018-01-01",
    "endDate": "2018-11-18",
    "createdAt": "2017-11-15T23:21:23.023",
    "updateAt": "2017-11-15T23:24:59.211"
}
```

### Deleting campaigns
Resource: `DELETE /campaign/{id}`

##### Response

``` json
{
    "message": "Campaign deleted with success",
    "status": "OK"
}
```
