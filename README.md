# Simple Electronic Repair Store API
This API allows the ADMIN to manage his electronic repair store. 
<br> <br>
BaseUrl: `http://localhost:8080`
<br>

## Technology and Tools used
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- Criteria API
- H2 and MySQL
- Postman

## Before the First Run
- Check the `application.prorperties` file and configure the DatasSource.
- The method `commandLine` in `RepairAppApplicatino.class` file creates and saves sample data (ADMIN account, Priorities,...etc) in database. You may need to comment it after the first run.


## Endpoints
- [Authentication](#Authentication)
    - [Login](#Login)
- [Dashboard](#Dashboard)
    - [Get dashboard](#Get-dashboard)
- [Repair Priority](#Repair-Priority)
    - [Get all priorities](#Get-all-priorities)
    - [Get a priority](#Get-a-priority)
    - [Save a priority](#Save-a-priority)
    - [Modify a priority](#Modify-a-priority)
    - [Delete a priority](#Delete-a-priority)
- [Quick Reply](#Quick-Reply)
    - [Get all quick replies](#Get-all-replies)
    - [Get a quick reply](#Get-a-reply)
    - [Save a quick reply](#Create-a-new-reply)
    - [Modify a quick reply](#Modify-a-quick-reply)
    - [Delete a quick reply](#Delete-a-quick-reply)
- [Repair Status](#Repair-Status)
    - [Get all statuses](#Get-all-statuses)
    - [Get a status](#Get-a-status)
    - [Save a status](#Save-a-status)
    - [Modify a status](#Modify-a-status)
    - [Delete a status](#Delete-a-status)
- [Brand](#Brand)
    - [Get all brands](#Get-all-brands)
    - [Get a brand](#Get-a-brand)
    - [Save a brand](#Save-a-brand)
    - [Modify a brand](#Modify-a-brand)
    - [Delete a brand](#Delete-a-brand)
- [Device](#Device)
    - [Get all devices](#Get-all-devices)
    - [Get a device](#Get-a-device)
    - [Save a device](#Save-a-device)
    - [Modify a device](#Modify-a-device)
    - [Delete a device](#Delete-a-device)
- [Defect](#Defect)
    - [Get all defects](#Get-all-defects)
    - [Get a defect](#Get-a-defect)
    - [Save a defect](#Save-a-defect)
    - [Modify a defect](#Modify-a-defect)
    - [Delete a defect](#Delete-a-defect)
- [Repair Orders](#Repair-Order)
    - [Get all orders](#Get-all-orders)
    - [Get an order](#Get-an-order)
    - [Save an order](#Save-an-order)
    - [Modify an order](#Modify-an-order)
    - [Modify an order status](#Modify-an-order-status)
    - [Delete an order](#Delete-an-order)
- [User](#User)
    - [Save user](#Save-user)
- [Backup](#Backup)
    - [Create backup](#Create-backup)

## Authentication

All endpoints require authentication and authorization except `/api/v1/auth/**`. To submit or view the data, you need to authenticate and obtain an access token.
<br>
The endpoints that require authentication expect a bearer token sent in the `Authorization` header. Example:
`Authorization: Bearer <YOUR TOKEN>`

### Authorities used

| Authority Name                                                   |
| -----------------------------------------------------------------|
| `ACCESS_DASHBOARD`                                               |
| `MANAGE_REPAIR_ORDER`, `EDIT_REPAIR_ORDER`, `REMOVE_REPAIR_ORDER`|
| `MANAGE_DEVICE`                                                  |
| `MANAGE_DEFECT`                                                  |
| `MANAGE_BRAND`                                                   |
| `MANAGE_QUICK_REPLY`                                             |
| `MANAGE_REPAIR_STATUS`                                           |
| `MANAGE_REPAIR_PRIORITY`                                         |
| `MANAGE_USERS`                                                   |
| `DATABASE_BACKUP`                                                |



### Login

**`POST /api/v1/auth/authenticate`**

The request body needs to be in JSON format. Example:
```
{
    "email": "admin@mail.com",
    "password": "123456"
}

```

**Parameters**

| Name          | Type   | In   | Required | Description                 |
| ------------- | ------ | ---- | -------- | ----------------------------|
| `email`       | String | body | Yes      | The email address of USER.  |
| `password`    | String | body | Yes      | The password of USER.       |

## Dashboard

For the following endpoint, `ACCESS_DAHSBOARD` authority is required.

### Get dashboard

**`GET /dashboard`**

Returns some stats depending on filter's data recieved in the body. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": {
        "amount": 30.0,
        "cost": 10.0,
        "profit": 20.0,
        "orderCount": 1,
        "brandCount": 3,
        "defectCount": 1,
        "deviceCount": 1
    }
}
```
**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `paymentStatus` | Boolean  | body  | No       | Specifies order's payment status (paid/unpaid).      |
| `statusId`      | Long     | body  | No       | Specifies the status id of the order.                |
| `priorityId`    | Long     | body  | No       | Specifies the priority id of the order.              |

## Repair Priority

For the following endpoints, `MANAGE_REPAIR_PRIORITY` authority is required.

### Get all priorities

**`GET /repair-priorities`**

Returns a list of priorities. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": [
        {
            "id": 1,
            "priorityValue": 0,
            "name": "normal",
            "extraCharge": 0.0
        },
        ...
    ]
}
```
### Get a priority

**`GET /repair-priorities/show`**

The request body needs to be in JSON format .
<br>
Returns a single priority. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": [
        {
            "id": 1,
            "priorityValue": 0,
            "name": "normal",
            "extraCharge": 0.0
        }
    ]
}
```

**Parameters**

| Name            | Type    | In    | Required | Description                                      |
| --------------- | ------- | ----- | -------- | ------------------------------------------------ |
| `id`            | Long    | body  | Yes      | Specifies the priority's id you wish to retrieve.|


### Save a priority

**`POST /repair-priorities/save`**

The request body needs to be in JSON format. Example:
```
{
    "priorityValue": 1,
    "name": "normal",
    "extraCharge": 0.0
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                             |
| --------------- | -------- | ----- | -------- | ------------------------------------------------------- |
| `priorityValue` | Integer  | body  | Yes      | Specifies the priority's level.                         |
| `name`          | String   | body  | Yes      | Specifies the priority's name .                         |
| `extraCharge`   | double   | body  | No       | Specifies the extra charges added by using the priority.|

### Modify a priority

**`POST /repair-priorities/update`**

The request body needs to be in JSON format. Example:
```
{
    "id": 1
    "priorityValue": 1,
    "name": "normal",
    "extraCharge": 10.0
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `id`            | Long     | body  | Yes      | Specifies the priority's id.                         |
| `priorityValue` | Integer  | body  | Yes      | Specifies the priority's level.                      |
| `name`          | String   | body  | Yes      | Specifies the priority's name .                      |
| `extraCharge`   | double   | body  | No       | Specifies the extra charges added using the priority.|

### Delete a priority

**`DELETE /repair-priorities/delete`**

**Parameters**

| Name            | Type   | In     | Required | Description                                       |
| --------------- | ------ | ------ | -------- | --------------------------------------------------|
| `id`            | Long   | body   | Yes      | Specifies the priority's id you wish to delete.   |

## Quick Reply

For the following endpoints, `MANAGE_QUICK_REPLY` authority is required.

### Get all quick replies

**`GET /quick-replies`**

The request body needs to be in JSON format, and contains fitler's data. Example:
```
{
    "keyword": "",
    "filterKey": "NAME",
    "sorting": "DESC"
}
```

Returns a page of replies. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": {
        "content": [
            {
                "id": 1,
                "name": "name3",
                "body": "body3",
                "createdAt": "2023-05-10T17:58:18.214359",
                "updatedAt": "2023-05-10T17:58:18.214403"
            },
            ...
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 3,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 3,
        "size": 3,
        "number": 0,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "first": true,
        "numberOfElements": 3,
        "empty": false
    }
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `page`          | int     | query  | No       | Specifies the page number.                         |
| `size`          | int      | query | No      | Specifies the number of elements per page.                      |
| `keyword`       | String   | body  | Yes      | Specifies the search keyword. It searches by `name`                      |
| `filterKey`     | enum   | body  | Yes       | Specifies the filter key. Could be `NAME`, `CREATED_AT` or `UPDATED_AT`.|
| `sorting`       | enum   | body  | Yes       | Specifies the sorting direction. Could be `DESC` or `ASC`.|

### Get a quick reply

**`GET /quick-replies/show`**

Returns a single priority. Example:
```
{
    "id": 1,
    "name": "name3",
    "body": "body3",
    "createdAt": "2023-05-10T17:58:18.214359",
    "updatedAt": "2023-05-10T17:58:18.214403"
}
```

**Parameters**

| Name            | Type    | In    | Required | Description                                      |
| --------------- | ------- | ----- | -------- | ------------------------------------------------ |
| `id`            | Long    | body  | Yes      | Specifies the reply's id you wish to retrieve.|


### Save a quick reply

**`POST /quick-replies/save`**

The request body needs to be in JSON format. Example:
```
{
    "name": "quick reply name",
    "body": "quick reply body"
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `name`          | String   | body  | Yes      | Specifies the reply's name.                      |
| `body`          | String   | body  | No       | Specifies the reply's body.|

### Modify a quick reply

**`POST /quick-replies/update`**

The request body needs to be in JSON format. Example:
```
{
    "id": 1
    "name": "new quick reply name",
    "body": "new quick reply body"
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `id`            | Long     | body  | Yes      | Specifies the reply's id.                         |
| `name`          | String   | body  | Yes      | Specifies the reply's name.                      |
| `body`          | String   | body  | No       | Specifies the reply's body.|

### Delete a quick reply

**`DELETE /quick-replies/delete`**

**Parameters**

| Name            | Type   | In     | Required | Description                         |
| --------------- | ------ | ------ | -------- | ----------------------------------- |
| `id`            | Long   | body   | Yes      | The reply's id you wish to delete..                  |


## Repair status

For the following endpoints, `MANAGE_REPAIR_STATUS` authority is required.

### Get all statuses

**`GET /repair-statuses`**

Returns a list of statuses. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": [
        {
            "id": 3,
            "name": "closed",
            "createdAt": "2023-05-10T17:58:18.261562",
            "updatedAt": "2023-05-10T17:58:18.261603"
        },
        ...
    ]
}
```
### Get a status

**`GET /repair-statuses/show`**

Returns a single status. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": [
        {
            "id": 1,
            "name": closed,
            "createdAt": "2023-05-10T17:58:18.261562",
            "updatedAt": "2023-05-10T17:58:18.261603"
        }
    ]
}
```

**Parameters**

| Name            | Type    | In    | Required | Description                                      |
| --------------- | ------- | ----- | -------- | ------------------------------------------------ |
| `id`            | Long    | body  | Yes      | Specifies the status id you wish to retrieve.    |


### Save a status

**`POST /repair-statuses/save`**

The request body needs to be in JSON format. Example:
```
{
    "name": "pending",
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `name`          | String   | body  | Yes      | Specifies the status's name .                        |

### Modify a status

**`POST /repair-statuses/update`**

The request body needs to be in JSON format. Example:
```
{
    "id": 1
    "name": "closed",
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `id`            | Long     | body  | Yes      | Specifies the status id.                             |
| `name`          | String   | body  | Yes      | Specifies the status name.                           |

### Delete a status

**`DELETE /repair-statuses/delete`**
The request body needs to be in JSON format.

**Parameters**

| Name            | Type   | In     | Required | Description                         |
| --------------- | ------ | ------ | -------- | ----------------------------------- |
| `id`            | Long   | body   | Yes      | Specifies the status id you wish to delete..            |

## Brand

For the following endpoints, `MANAGE_BRAND` authority is required.
### Get all brands

**`GET /brands`**

Returns a list of brands. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": [
        {
            "id": 2,
            "name": "DELL",
            "hasDevices": true,
            "createdAt": "2023-05-10T17:58:18.27696",
            "updatedAt": "2023-05-10T17:58:18.277015"
        },
        ...
    ]
}
```
### Get a brand

**`GET /brands/show`**

Returns a single brand. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": [
        {
            "id": 2,
            "name": "DELL",
            "hasDevices": true,
            "createdAt": "2023-05-10T17:58:18.27696",
            "updatedAt": "2023-05-10T17:58:18.277015"
        },
    ]
}
```

**Parameters**

| Name            | Type    | In    | Required | Description                                      |
| --------------- | ------- | ----- | -------- | ------------------------------------------------ |
| `id`            | Long    | body  | Yes      | Specifies the brand id you wish to retrieve.     |


### Save a brand

**`POST /brands/save`**

The request body needs to be in JSON format. Example:
```
{
    "name":"Apple",
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `name`          | String   | body  | Yes      | Specifies the brand's name .                        |

### Modify a brand

**`POST /brands/update`**

The request body needs to be in JSON format. Example:
```
{
    "id": 1
    "name": "LG",
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `id`            | Long     | body  | Yes      | Specifies the brand's id.                            |
| `name`          | String   | body  | Yes      | Specifies the brand's name.                          |

### Delete a brand

**`DELETE /brands/delete`**
The request body needs to be in JSON format.

**Parameters**

| Name            | Type   | In     | Required | Description                         |
| --------------- | ------ | ------ | -------- | ----------------------------------- |
| `id`            | Long   | body   | Yes      | Specifies the brand id you wish to delete..                       |


## Defect

For the following endpoints, `MANAGE_DEFECT` authority is required.
### Get all defects

**`GET /defects`**

Returns a page of defects. Example:
```
{
	"id": 1,
	"title": "broken screen",
	"requiredTime": "3d",
	"price": 50.0,
	"cost": 1.0,
	"device": {
		"id": 1,
		"name": "inspiron 15",
		"model": "3000 series",
		"avatar": null,
		"brand": {
		    "id": 2,
		    "name": "DELL",
		    "hasDevices": true,
		    "createdAt": "2023-05-10T17:58:18.27696",
		    "updatedAt": "2023-05-10T17:58:18.277015"
		},
		"totalDefects": 0,
		"createdAt": "2023-05-10T17:58:18.291453",
		"updatedAt": "2023-05-10T17:58:18.291509"
	},
	"createdAt": "2023-05-10T17:58:18.301691",
	"updatedAt": "2023-05-10T17:58:18.301768"
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `page`          | int      | query | No       | Specifies the page number.                            |
| `size`          | int      | query | No       | Specifies the number of elements per page.             |
| `keyword`       | String   | body  | Yes      | Specifies the search keyword. It searches by `title`. |
| `filterKey`     | enum     | body  | Yes      | Specifies the filter key. Could be `TITLE`, `PRICE`, `COST` or `CREATED_AT`.|
| `sorting`       | enum     | body  | Yes      | Specifies the sorting direction. Could be `DESC` or `ASC`.|

### Get a defect

**`GET /defects/show`**

Returns a single defect. Example:
```
{
	"id": 1,
	"title": "broken screen",
	"requiredTime": "3d",
	"price": 50.0,
	"cost": 1.0,
	"device": {
		"id": 1,
		"name": "inspiron 15",
		"model": "3000 series",
		"avatar": null,
		"brand": {
		    "id": 2,
		    "name": "DELL",
		    "hasDevices": true,
		    "createdAt": "2023-05-10T17:58:18.27696",
		    "updatedAt": "2023-05-10T17:58:18.277015"
		},
		"totalDefects": 0,
		"createdAt": "2023-05-10T17:58:18.291453",
		"updatedAt": "2023-05-10T17:58:18.291509"
	},
	"createdAt": "2023-05-10T17:58:18.301691",
	"updatedAt": "2023-05-10T17:58:18.301768"
} 
```

**Parameters**

| Name            | Type    | In    | Required | Description                                      |
| --------------- | ------- | ----- | -------- | ------------------------------------------------ |
| `id`            | Long    | body  | Yes      | Specifies the defect id you wish to retrieve.    |


### Save a defect

**`POST /defects/save`**

The request body needs to be in JSON format. Example:
```
{
    "title": "repair motherboard",
    "requiredTime": "15d",
    "cost": 0.0,
    "deviceId": 1,
    "price": 8000.0
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `title`          | String   | body  | Yes      | Specifies the defect's title .                        |
| `requiredTime`          | String   | body  | Yes      | Specifies the required time to fix the defect .                        |
| `cost`          | String   | body  | Yes      | Specifies the defect's cost .                        |
| `deviceId`          | String   | body  | Yes      | Specifies the defect's device id .                        |
| `price`          | String   | body  | Yes      | Specifies the defect's price .                        |


### Modify a defect

**`POST /defects/update`**

The request body needs to be in JSON format. Example:
```
{
	"id": 1,
	"title": "broken screen",
	"requiredTime": "3d",
	"price": 50.0,
	"cost": 1.0,
	"device": {
		"id": 1,
		"name": "inspiron 15",
		"model": "3000 series",
		"avatar": null,
		"brand": {
		    "id": 2,
		    "name": "DELL",
		    "hasDevices": true,
		    "createdAt": "2023-05-10T17:58:18.27696",
		    "updatedAt": "2023-05-10T17:58:18.277015"
		},
		"totalDefects": 0,
		"createdAt": "2023-05-10T17:58:18.291453",
		"updatedAt": "2023-05-10T17:58:18.291509"
	},
	"createdAt": "2023-05-10T17:58:18.301691",
	"updatedAt": "2023-05-10T17:58:18.301768"
} 
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `id`          | String   | body  | Yes      | Specifies the defect's id .                        |
| `title`          | String   | body  | Yes      | Specifies the defect's title .                        |
| `requiredTime`          | String   | body  | Yes      | Specifies the required time to fix the defect .                        |
| `cost`          | String   | body  | Yes      | Specifies the defect's cost .                        |
| `deviceId`          | String   | body  | Yes      | Specifies the defect's device id .                        |
| `price`          | String   | body  | Yes      | Specifies the defect's price .                        |

### Delete a defect

**`DELETE /defects/delete`**

The request body needs to be in JSON format.

**Parameters**

| Name            | Type   | In     | Required | Description                         |
| --------------- | ------ | ------ | -------- | ----------------------------------- |
| `id`            | Long   | body   | Yes      | The defect id you wish to delete.                       |


## Device
For the following endpoints, `MANAGE_DEVICE` authority is required.
### Get all devices

**`GET /devices`**

Returns a page of devices. Example:
```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": {
        "content": [
            {
                "id": 1,
                "name": "inspiron 15",
                "model": "3000 series",
                "avatar": null,
                "brand": {
                    "id": 2,
                    "name": "DELL",
                    "hasDevices": true,
                    "createdAt": "2023-05-10T17:58:18.27696",
                    "updatedAt": "2023-05-10T17:58:18.277015"
                },
                "totalDefects": 0,
                "createdAt": "2023-05-10T17:58:18.291453",
                "updatedAt": "2023-05-10T17:58:18.291509"
            },
            ...
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 1,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 1,
        "size": 1,
        "number": 0,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "first": true,
        "numberOfElements": 1,
        "empty": false
    }
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `page`          | int      | query | No       | Specifies the page number.                            |
| `size`          | int      | query | No       | Specifies the number of elements per page.             |
| `keyword`       | String   | body  | Yes      | Specifies the search keyword. It searches by `name`. |
| `filterKey`     | enum     | body  | Yes      | Specifies the filter key. Could be `NAME`, `MODEL` or `CREATED_AT`.|
| `sorting`       | enum     | body  | Yes      | Specifies the sorting direction. Could be `DESC` or `ASC`.|

### Get a device

**`GET /devices/show`**

Returns a single device. Example:
```
{
    "id": 1,
    "name": "inspiron 15", 
    "model": "3000 series",
    "avatar": null,
    "brand": {
		"id": 2,
		"name": "DELL",
		"hasDevices": true,
		"createdAt": "2023-05-10T17:58:18.27696",
		"updatedAt": "2023-05-10T17:58:18.277015"
    },
    "totalDefects": 0,
    "createdAt": "2023-05-10T17:58:18.291453",
    "updatedAt": "2023-05-10T17:58:18.291509"
}  
```

**Parameters**

| Name            | Type    | In    | Required | Description                                      |
| --------------- | ------- | ----- | -------- | ------------------------------------------------ |
| `id`            | Long    | body  | Yes      | Specifies the device id you wish to retrieve.    |


### Save a device

**`POST /devices/save`**

The request body needs to be in JSON format. Example:
```
{
    "name": "inspiron 15",
    "brandId": 2,
    "model": "3000 series"
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `name`          | String   | body  | Yes      | Specifies the device's name .                        |
| `brandId`          | Long   | body  | Yes      | Specifies the device's brand id .                        |
| `model`          | String   | body  | No      | Specifies the device's model                        |


### Modify a device

**`POST /devices/update`**

The request body needs to be in JSON format. Example:
```
{
    "id": "1",
    "name": "inspiron 20",
    "brandId": 2,
    "model": "5000 series"
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `id`            | Long     | body  | Yes      | Specifies the device's id.                            |
| `name`          | String   | body  | Yes      | Specifies the device's name .                        |
| `brandId`          | Long   | body  | Yes      | Specifies the device's brand id .                   |
| `model`          | String   | body  | No      | Specifies the device's model                        |

### Delete a device

**`DELETE /devices/delete`**
he request body needs to be in JSON format.

**Parameters**

| Name            | Type   | In     | Required | Description                         |
| --------------- | ------ | ------ | -------- | ----------------------------------- |
| `id`            | Long   | body   | Yes      | The device id you wish to delete.                       |


## Repair Order

### Get all orders

**`GET /repair-orders`**

For this endpoint, `MANAGE_REPAIR_ORDER` authority is required.

Returns a page of orders. Example:

```
{
    "status": 200,
    "message": "Data fetched successfully",
    "data": {
        "content": [
            {
                "id": 3,
                "uuid": "bf847ccc-9d3f-40b1-92c7-5ceff1501050",
                "name": "Alicia",
                "email": "Annie.Keebler@hotmail.com",
                "phone": "626-282-2524",
                "address": "19417 Harris Field",
                "tracking": "1683738272729",
                "serialNumber": "q",
                "totalCharges": 30.0,
                "totalCost": 10.0,
                "defects": 1,
                "priorityResponse": {
                    "id": 1,
                    "priorityValue": 0,
                    "name": "normal",
                    "extraCharge": 0.0
                },
                "repairStatusResponse": {
                    "id": 1,
                    "name": "pending",
                    "createdAt": "2023-05-10T17:58:18.24747",
                    "updatedAt": "2023-05-10T17:58:18.247529"
                },
                "paymentStatus": false,
                "createdAt": "2023-05-10T18:04:32.733052",
                "updatedAt": "2023-05-10T18:04:32.733114"
            },
			...
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 10,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 2,
        "size": 10,
        "number": 0,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "first": true,
        "numberOfElements": 2,
        "empty": false
    }
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `page`          | int      | query | No       | Specifies the page number.                            |
| `size`          | int      | query | No       | Specifies the number of elements per page.             |
| `keyword`       | String   | body  | Yes      | Specifies the search keyword. It searches by `tracking`, `name`, `phone` or `repairStatus`. |
| `filterKey`     | enum     | body  | Yes      | Specifies the filter key. Could be `TRACKING`, `CUSTOMER_NAME`, `CUSTOMER_PHONE`, `UPDATED_AT` or `CREATED_AT`.|
| `sorting`       | enum     | body  | Yes      | Specifies the sorting direction. Could be `DESC` or `ASC`.|

### Get an order

**`GET /repair-orders/show`**

For this endpoint, `MANAGE_REPAIR_ORDER` authority is required.

Returns a single order. Example:
```
{
                "id": 3,
                "uuid": "bf847ccc-9d3f-40b1-92c7-5ceff1501050",
                "name": "Alicia",
                "email": "Annie.Keebler@hotmail.com",
                "phone": "626-282-2524",
                "address": "19417 Harris Field",
                "tracking": "1683738272729",
                "serialNumber": "q",
                "totalCharges": 30.0,
                "totalCost": 10.0,
                "defects": 1,
                "priorityResponse": {
                    "id": 1,
                    "priorityValue": 0,
                    "name": "normal",
                    "extraCharge": 0.0
                },
                "repairStatusResponse": {
                    "id": 1,
                    "name": "pending",
                    "createdAt": "2023-05-10T17:58:18.24747",
                    "updatedAt": "2023-05-10T17:58:18.247529"
                },
                "paymentStatus": false,
                "createdAt": "2023-05-10T18:04:32.733052",
                "updatedAt": "2023-05-10T18:04:32.733114"
            }
```

**Parameters**

| Name            | Type    | In    | Required | Description                                      |
| --------------- | ------- | ----- | -------- | ------------------------------------------------ |
| `id`            | Long    | body  | Yes      | Specifies the order id you wish to retrieve.    |


### Save an order

**`POST /repair-orders/save`**

For this endpoint, `MANAGE_REPAIR_ORDER` authority is required.

The request body needs to be in JSON format. Example:
```
{
    "name": "Sabrina",
    "email": "Coleman87@hotmail.com",
    "phone": "894-491-3709",
    "address": "8439 Xzavier Rapids",
    "serialNumber": "t",
    "diagnostics": "Et ad enim dolorum dolor eveniet et nisi odio. Dolores consequatur sunt excepturi doloribus molestiae nobis qui.",
    "totalCost": 10,
    "profit": 20,
    "prePaid": 30,
    "totalCharge":30,
    "repairPriorityId" : 1,
    "defectsIds": [1],
    "deviceId": 1,
    "repairStatusId": 1
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `name`          | String   | body  | Yes      | Specifies the customer's name.                        |
| `email`          | String   | body  | Yes      | Specifies the customer's email .                        |
| `phone`          | String   | body  | Yes      | Specifies the customer's phone .                        |
| `address`          | String   | body  | Yes      | Specifies the customer's address.                        |
| `serialNumber`          | String   | body  | Yes      | Specifies the broken device's serialNumber .|
| `diagnostics`          | String   | body  | Yes      | Specifies the diagnostics .|
| `profit`          | double   | body  | Yes      | Specifies profit from the order.|
| `prePaid`          | double   | body  | Yes      | Specifies the customer prepaid amount .|
| `totalCharge`          | double   | body  | Yes      | Specifies the order's price.|
| `repairPriorityId`          | Long   | body  | Yes      | Specifies the priority's id of the order .|
| `defectsIds`          | List   | body  | Yes      | Specifies the list of defects's id of the order.|
| `deviceId`          | Long   | body  | Yes      | Specifies the order's device .|
| `repairStatusId`          | Long   | body  | Yes      | Specifies the status id of the order.|


### Modify an order

**`POST /repair-orders/update`**

For this endpoint, `EDIT_REPAIR_ORDER` authority is required.
<br>
The request body needs to be in JSON format. Example:
```
{
	"id": 1,
    "name": "Sabrina",
    "email": "Coleman87@hotmail.com",
    "phone": "894-491-3709",
    "address": "8439 Xzavier Rapids",
    "serialNumber": "t",
    "diagnostics": "Et ad enim dolorum dolor eveniet et nisi odio. Dolores consequatur sunt excepturi doloribus molestiae nobis qui.",
    "totalCost": 10,
    "profit": 20,
    "prePaid": 30,
    "totalCharge":30,
    "repairPriorityId" : 1,
    "defectsIds": [1],
    "deviceId": 1,
    "repairStatusId": 1
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `id`          | Long   | body  | Yes      | Specifies the order's id.                        |
| `name`          | String   | body  | Yes      | Specifies the customer's name.                        |
| `email`          | String   | body  | Yes      | Specifies the customer's email .                        |
| `phone`          | String   | body  | Yes      | Specifies the customer's phone .                        |
| `address`          | String   | body  | Yes      | Specifies the customer's address.                        |
| `serialNumber`          | String   | body  | Yes      | Specifies the broken device's serialNumber .|
| `diagnostics`          | String   | body  | Yes      | Specifies the diagnostics .|
| `profit`          | double   | body  | Yes      | Specifies profit from the order.|
| `prePaid`          | double   | body  | Yes      | Specifies the customer prepaid amount .|
| `totalCharge`          | double   | body  | Yes      | Specifies the order's price.|
| `repairPriorityId`          | Long   | body  | Yes      | Specifies the priority's id of the order .|
| `defectsIds`          | List<Long>   | body  | Yes      | Specifies the list of defects's id of the order.|
| `deviceId`          | Long   | body  | Yes      | Specifies the order's device .|
| `repairStatusId`          | Long   | body  | Yes      | Specifies the status id of the order.|

### Modify an order status

**`PATCH /repair-orders/update/status`**

For this endpoint, `EDIT_REPAIR_ORDER` authority is required.

The request body needs to be in JSON format. Example:
```
{
	"id": 1,
    "statusId": 1
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `id`          | Long   | body  | Yes      | Specifies the order's id.                        |
| `statusId`          | Long   | body  | Yes      | Specifies the status id of the order.|

### Delete an order

**`DELETE /repair-orders/delete`**

For this endpoint, `REMOVE_REPAIR_ORDER` authority is required.

The request body needs to be in JSON format. Example:

**Parameters**

| Name            | Type   | In     | Required | Description                         |
| --------------- | ------ | ------ | -------- | ----------------------------------- |
| `id`            | Long   | body   | Yes      | The order id you wish to delete.    |

## User

For the following endpoints, `MANAGE_USER` authority is required.

### Save user

**`POST /users/save`**

The request body needs to be in JSON format. Example:
```
{
    "firstname": "FirstName",
    "lastname": "LastName",
    "email": "user@mail.com",
    "password": "123456",
    "authorityList": ["ACCESS_DASHBOARD"]
}
```

**Parameters**

| Name            | Type     | In    | Required | Description                                          |
| --------------- | -------- | ----- | -------- | ---------------------------------------------------- |
| `firstname`          | String   | body  | No      | Specifies the user's firstname .                 |
| `lastname`          | String   | body  | No      | Specifies the user's lastname .                   |
| `email`          | String   | body  | Yes      | Specifies the user's email .                        |
| `password`          | String   | body  | Yes      | Specifies the user's password .                  |
| `authorityList`          | List   | body  | Yes      | Specifies the user's assigned authorities .   |

## Backup

For the following endpoints, `MANAGE_BACKUP` authority is required.
### Create backup

**`GET /backup`**

It creates a SQL file in project folder.

## Exception
The Api throws RepairAppException defined in `RepairAppException.class` when some errors detected during the execution.

### Error code used
Error codes are defined in `ErrorCode.enum`.


| Name            | Code   | Description                         |
| --------------- | ------ |------------------------------------ |
| `NULL_ID`            | 1000   | When Object's id is null. |
| `USER_NOT_VALID`            | 1900   | When the data of User object doesn't not validated.      |
| `REPAIR_PRIORITY_NOT_FOUND`| 2000   | When RepairPriority object not found in database |
| `REPAIR_PRIORITY_NOT_VALID`| 2001   | When the data of RepairPriority Object doesn't not validated|
| `REPAIR_PRIORITY_ALREADY_IN_USE` | 2002 | When a RepairPriority object with same main data already existed in database |
| `REPAIR_PRIORITY_IS_RELATED_TO_EXISTING_REPAIR_ORDER`| 2004 |  When trying to delete a RepairPriority object related to existed RepairOrder object |
| `QUICK_REPLY_NOT_FOUND` | 2004 | When QuickReply object not found in database |
| `QUICK_REPLY_NOT_VALID` | 2005 | When the data of QuickReply Object doesn't not validated |
| `BRAND_NOT_FOUND` | 2007 | When Brand object not found in database |
| `BRAND_NOT_VALID` | 2008 | When the data of Brand Object doesn't not validated |
| `BRAND_ALREADY_IN_USE` | 2009 | When a Brand object with same main data already existed in database  |
| `BRAND_IS_RELATED_TO_EXISTING_DEVICE` | 2010 | When trying to delete a Brand object related to existed Device object |
| `REPAIR_STATUS_NOT_VALID` | 2013 | When the data of RepairStatus Object doesn't not validated |
| `REPAIR_STATUS_ALREADY_IN_USE` | 2014 | When a Brand object with same main data already existed in database  |
| `REPAIR_STATUS_IS_RELATED_TO_EXISTING_REPAIR_ORDER` | 2015 | When trying to delete a RepairStatus object related to existed RepairOrder object |
| `REPAIR_STATUS_NOT_FOUND` | 2016 |When RepairStatus object not found in database|
| `DEVICE_NOT_VALID` | 2019 | When the data of Device Object doesn't not validated |
| `DEVICE_ALREADY_IN_USE` | 2020 | When a Brand object with same main data already existed in database  |
| `DEVICE_IS_RELATED_TO_EXISTING_DEFECT_OR_REPAIR_ORDER` | 2021 | When trying to delete a Device object related to existed RepairOrder object |
| `DEVICE_NOT_FOUND` | 2025 | When Device object not found in database|
| `DEFECT_ALREADY_IN_USE` | 2026 | When a Brand object with same main data already existed in database  |
| `DEFECT_IS_RELATED_TO_EXISTING_DEFECT_OR_REPAIR_ORDER` | 2027 | When trying to delete a Defect object related to existed RepairOrder object |
| `DEFECT_NOT_FOUND` | 2028 | When a RepairPriority object with same main data already existed in database |
| `REPAIR_ORDER_NOT_VALID`            | 2032   | When the data of RepairOrder Object doesn't not validated |
| `REPAIR_ORDER_NOT_FOUND`            | 2033   | When RepairOrder object not found in database|
| `FILTER_DATA_IS_NOT_VALID`            | 4000   |When the data of Filter Object doesn't not validated|

## Testing 

Exported postman collection file `simple-electronic-repair-store-api.postman_collection.json` in project folder may help on testing and exploring the API.

## Contribution

If you want to contribute to this project and make it better with new ideas, your pull request is very welcomed. If you find any issue just put it in the repository issue section, thank you.

  
