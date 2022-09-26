# im-pulse-backend JSON REST API

## User
### READ User
Request:
- Method: GET
- Endpoint: `/api/users/{userId}`
- Header:
  - Accept: application/json
Response:
```json
{
  "data": {
    "id": "String, Unique",
    "fullName": "String",
    "pictureUrl": "String, Nullable",
    "role": "Role, Nullable",
    "status": "String, Nullable",
    "region": "String, Nullable",
    "devices": [],
    "createdAt": 1663334592916,
    "about": null,
    "auth": null,
    "verifiers": null,
    "updatedAt": null
  }
}
```
### CREATE User
Request:
- Method: POST
- Endpoint: `/api/users`
- Header:
    - Content-Type: application/json
    - Accept: application/json
- Body:
```json
{
  "userId": "String, Unique",
  "fullName": "String",
  "email": "String",
  "phone": "String",
  "username": "String",
  "password": "String"
}
```
Response:
```json
{
  "code": "Number",
  "status": "String",
  "data": {
    "userId": "String, Unique",
    "fullName": "String",
    "email": "String",
    "phone": "String",
    "username": "String",
    "createdAt": "Date",
    "updatedAt":"Date"
  }
}
```
### Update User
Request:
- Method: PUT
- Endpoint: `/api/users/{userId}`
- Header:
    - X-Api-Key: "utif.pages.dev"
    - Content-Type: application/json
    - Accept: application/json
- Body:
```json
{
  "fullName": "String",
  "email": "String",
  "phone": "String",
  "username": "String",
  "password": "String"
}
```
Response:
```json
{
  "code": "Number",
  "status": "String",
  "data": {
    "userId": "String, Unique",
    "fullName": "String",
    "email": "String",
    "phone": "String",
    "username": "String",
    "password": "String",
    "createdAt": "Date",
    "updatedAt": "Date"
  }
}
```
### Delete User
Request:
- Method: DELETE
- Endpoint: `/api/users/{userId}`
- Header:
    - X-Api-Key: "utif.pages.dev"
    - Accept: application/json

Response:
```json
{
  "code": "Number",
  "status": "String"
}
```
### List Users
Request:
- Method: GET
- Endpoint: `/api/users`
- Header:
    - X-Api-Key: "utif.pages.dev"
    - Accept: application/json
- Query Param:
    - size: Number,
    - page: Number

Response:
```json
{
  "code": "Number",
  "status": "String",
  "data": [
    {
      "userId": "String, Unique",
      "fullName": "String",
      "email": "String",
      "phone": "String",
      "username": "String",
      "createdAt": "Date",
      "updatedAt": "Date",
      "room": ["Room"]
    }
  ]
}
```
