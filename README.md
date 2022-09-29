# im-pulse-backend JSON REST API

## Auth
### REGISTER Auth
Request:
- Method: POST
- Endpoint: `/api/auth/sign-up`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "email": "String, Unique",
    "password": "String"
  }
  ```
Response:
- Status Code: `Integer`
### LOGIN Auth
Request:
- Method: POST
- Endpoint: `/api/auth/sign-in`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "payload": "String, Unique (email, phone, username)",
    "password": "String"
  }
  ```
Response:
```json
{
  "data": "String, Unique Token Key"
}
```
### CLAIM Auth
Request:
- Method: GET
- Endpoint: `/api/auth/claim-auth-id`

Response:
```json
{
  "data": "String, Primary Unique UUID Key"
}
```
### LOGIN Auth
## Blacklist
### CREATE Blacklist
Request:
- Method: POST
- Endpoint: `/api/blacklists`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "targetUserId": "String, Foreign Unique UUID",
    "userId": "String, Foreign Unique UUID"
  }
  ```
Response:
```json
{
  "data": {
    "blacklistId": "String, Primary Unique UUID",
    "targetUser": "User, Nullable",
    "targetUserId": "String, Foreign Unique UUID",
    "userId": "String, Foreign Unique UUID"
  }
}
```
### REMOVE Blacklists
Request:
- Method: DELETE
- Endpoint: `/api/blacklists/{blacklistId}`

Response:
```json
{
  "data": "String, Primary Unique UUID"
}
```
### LIST Blacklists
Request:
- Method: GET
- Endpoint: `/api/blacklists/{userId}`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Params:
  - page: `Integer`
  - size: `Integer`

Response:
```json
{
  "data": [
    {
      "blacklistId": "String, Primary Unique UUID",
      "targetUser": "User, Nullable",
      "targetUserId": "String, Foreign Unique UUID",
      "userId": "String, Foreign Unique UUID"
    }
  ]
}
```
## Contact
### CREATE Contact
Request:
- Method: POST
- Endpoint: `/api/contacts`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "email": "String",
    "phone": "String",
    "username": "String",
    "address": "String"
  }
  ```
Response:
```json
{
  "contactId": "String, Primary Unique UUID",
  "email": "String",
  "phone": "String",
  "username": "String",
  "address": "String"
}
```
### UPDATE Contact
Request:
- Method: PUT
- Endpoint: `/api/contacts`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "contactId": "String, Primary Unique UUID",
    "email": "String",
    "phone": "String",
    "username": "String",
    "address": "String"
  }
  ```
Response:
```json
{
  "contactId": "String, Primary Unique UUID",
  "email": "String",
  "phone": "String",
  "username": "String",
  "address": "String"
}
```
## Conversation
### REMOVE Conversation
Request:
- Method: DELETE
- Endpoint: `/api/conversations/{conversationId}`

Response:
```json
{
  "data": "String, Primary UUID Key"
}
```
### LIST Conversation
Request:
- Method: GET
- Endpoint: `/api/conversations/{}`
- Param:
  - page: `Integer`
  - size: `Integer`

Response:
```json
{
  "data": [
    {
      "conversationId":  "String, Primary UUID Key",
      "createdAt":  "Long",
      "participants":  ["Participant"],
      "userId":  "String, Foreign UUID Key"
    }
  ]
}
```
## Device
### CREATE Device
Request:
- Method: POST
- Endpoint: `/api/devices`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "token": "String",
    "type": "String",
    "userId": "String, Foreign UUID Key"
  }
  ```
Response:

```json
{
  "data": {
    "token": "String",
    "type": "String",
    "updatedAt": "Long, Nullable"
  }
}
```
### DELETE Device
Request:
- Method: DELETE
- Endpoint: `/api/devices/{deviceId}`

Response:
```json
{
  "data": "String, Unique"
}
```
## File
### CREATE File
Request:
- Method: POST
- Endpoint: `/api/files/{fileId}`
- Header:
  - Content-Type: `multipart/form-data`
  - Accept: `image/*`
  
Response: 
```json
{
    "data": [
        {
            "id": "String, Unique",
            "type": "String",
            "role": "String, Enum(ORIGINAL, THUMBNAIL)"
        },
        {
            "id": "String, Unique",
            "type": "String",
            "role": "String, Enum(ORIGINAL, THUMBNAIL)"
        }
    ]
}
```
### READ File
Request:
- Method: GET
- Endpoint: `/api/files/{fileId}`
- Header:
  - Content-Type: `*/*`

Response:
- Header:
  - Content-Type: `image/*`
### DELETE File
Request:
- Method: DELETE
- Endpoint: `/api/files/{fileId}`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
  
Response:
```json
{
    "data": "String, Unique"
}
```
## Image
### CREATE Image
Request:
- Method: POST
- Endpoint: `/api/images`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "url": "String",
    "role": "String, Enum(ORIGINAL, THUMBNAIL)",
    "profileId": "String, Foreign Unique UUID"
  }
  ```
Response:
```json
{
  "data": {
    "url": "String",
    "role": "String, Enum(ORIGINAL, THUMBNAIL)",
    "updatedAt": "Long, Nullable"
  }
}
```
## Message
### CREATE Message
Request:
- Method: POST
- Endpoint: `/api/messages`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "text": "String",
    "type": "String, Enum(MESSAGE, TYPING)"
  }
  ```
Response:
```json
{
  "data": {
    "messageId": "String, Primary UUID Key",
    "text": "String",
    "type": "String, Enum(MESSAGE, TYPING)",
    "createdAt": "Long",
    "updatedAt": "Long, Nullable",
    "deletedAt": "Long, Nullable",
    "userId": "String, Foreign UUID Key"
  }
}
```
### UPDATE Message
Request:
- Method: PUT
- Endpoint: `/api/messages`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "messageId": "String, Primary UUID Key",
    "text": "String",
    "type": "String, Enum(MESSAGE, TYPING)"
  }
  ```
  
Response:
```json
{
  "data": {
    "messageId": "String, Primary UUID Key",
    "text": "String",
    "type": "String, Enum(MESSAGE, TYPING)",
    "createdAt": "Long",
    "updatedAt": "Long, Nullable",
    "deletedAt": "Long, Nullable",
    "userId": "String, Foreign UUID Key"
  }
}
```
### LIST Message
Request:
- Method: GET
- Endpoint: `/api/messages/{userId}`
- Params:
  - page: `Integer`
  - size: `Integer`

Response:
```json
{
  "data": [
    {
      "messageId": "String, Primary UUID Key",
      "text": "String",
      "type": "String, Enum(MESSAGE, TYPING)",
      "createdAt": "Long",
      "updatedAt": "Long, Nullable",
      "deletedAt": "Long, Nullable",
      "userId": "String, Foreign UUID Key"
    }
  ]
}
```
## Profile
### CREATE Profile
Request:
- Method: POST
- Endpoint: `/api/profiles`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "about": "String",
    "status": "String",
    "region": "String",
    "userId": "String, Foreign UUID Key"
  }
  ```
Response:
```json
{
  "data": {
    "about": "String",
    "status": "String",
    "region": "String",
    "pictureUrl": "Long, Nullable",
    "pictureUpdatedAt": "Long, Nullable",
    "updatedAt": "Long, Nullable"
  }
}
```
### UPDATE Profile
Request:
- Method: PUT
- Endpoint: `/api/profiles`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "id": "String, Primary Unique UUID Key",
    "about": "String",
    "status": "String",
    "region": "String"
  }
  ```
Response:
```json
{
  "data": {
    "about": "String",
    "status": "String",
    "region": "String",
    "pictureUrl": "Long, Nullable",
    "pictureUpdatedAt": "Long, Nullable",
    "updatedAt": "Long, Nullable"
  }
}
```
## Role
### CREATE Role
Request:
- Method: POST
- Endpoint: `/api/roles`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "status": "String",
    "userId": "String, Foreign Unique UUID"
  }
  ```
Response:
```json
{
  "data": {
    "status": "String",
    "updatedAt": "Long, Nullable"
  }
}
```
### READ Role
Request:
- Method: GET
- Endpoint: `/api/roles/{roleId}`

Response:
```json
{
  "data": {
    "status": "String",
    "updatedAt": "Long, Nullable"
  }
}
```
## User
### CREATE User
Request:
- Method: POST
- Endpoint: `/api/users`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "firstName": "String",
    "lastName": "String",
    "contactId": "String, Foreign Unique UUID",
    "authId": "String, Foreign Unique UUID"
  }
  ```
Response:
```json
{
  "data": {
    "userId": "String, Primary Unique UUID Key",
    "firstName": "String, Nullable",
    "lastName": "String, Nullable",
    "contact": "Contact",
    "about": "String, Nullable",
    "status": "String, Nullable",
    "picture": "Image, Nullable",
    "region": "String, Nullable",
    "role": "Role, Nullable",
    "devices": [
      "Device"
    ],
    "createdAt": "Long",
    "updatedAt": "Long, Nullable"
  }
}
```
### READ User
Request:
- Method: GET
- Endpoint: `/api/users/{userId}`

Response:
```json
{
  "data": {
    "userId": "String, Primary Unique UUID Key",
    "firstName": "String, Nullable",
    "lastName": "String, Nullable",
    "contact": "Contact",
    "about": "String, Nullable",
    "status": "String, Nullable",
    "picture": "Image, Nullable",
    "region": "String, Nullable",
    "role": "Role, Nullable",
    "devices": [
      "Device"
    ],
    "createdAt": "Long",
    "updatedAt": "Long, Nullable"
  }
}
```
## Verifier
### CREATE Verifier
Request:
- Method: POST
- Endpoint: `/api/verifiers`
- Header:
  - Content-Type: `application/json`
  - Accept: `application/json`
- Body:
  ```json
  {
    "type": "String",
    "payload": "String",
    "userId": "String, Foreign Unique UUID"
  }
  ```
Response:
```json
{
  "data": {
    "type": "String",
    "payload": "String",
    "updatedAt": "Long, Nullable"
  }
}
```
### DELETE Verifier
Request:
- Method: DELETE
- Endpoint: `/api/verifiers/{verifierId}`

Response:
```json
{
  "data": "Primary, Unique UUID Key"
}
```
### LIST Verifier
Request:
- Method: GET
- Endpoint: `/api/verifiers/{userId}`
- Params
  - page: `Integer`
  - size: `Integer`
  
Response:
```json
{
  "data": [
    {
      "type": "String",
      "payload": "String",
      "updatedAt": "Long, Nullable"
    }
  ]
}
```