# MCDA5550_Android_Project

Submitted by: Shailesh Pandey (A00453568)

server_port: 8000

database: hotel_rest_assignment

db_port: 3306

API-KEY: 4p_=va##$8y9_n#0lg=%o7m%&$29ubn_1p6&uv27l==52kyt(j

The functionalities implemented have been explained below. Please add API-KEY to your postman header wherever it is mentioned as API-KEY secured. For the POST request of reserving a hotel the following body format is needed:

{ "hotel_name": "string",

"checkin": "string of format: YYYY-MM-DD",

"checkout": "string of format: YYYY-MM-DD",

"guests_list": [

{ "guest_name" : "string", "gender": "string"

}, {……} ]

}

For example:

{ "hotel_name": "Holiday Inn",

"checkin": "2022-02-02",

"checkout": "2022-02-03",

"guests_list": [ { "guest_name" : "John", "gender": "Male"}, { "guest_name" : "Jeane", "gender": "Female"}]

}

Home Page:
![image](https://user-images.githubusercontent.com/90723999/162582023-8725530f-ab69-4b1c-806f-627e0d6f68a4.png)


Functionalities:

Get the list of hotels present(will require API-KEY). url suffix: /hotel_list/ 
![image](https://user-images.githubusercontent.com/90723999/162582032-8eac7c0d-9715-478f-ae9d-99a3575fffa3.png)
![image](https://user-images.githubusercontent.com/90723999/162582039-6ecddc90-5dd0-4d70-be88-5e0817f29ebc.png)

Same list irrespective of dates: 
![image](https://user-images.githubusercontent.com/90723999/162582048-62d822d4-39d6-4780-a125-7f6827735e71.png)
Cases when API-KEY is not set:
![image](https://user-images.githubusercontent.com/90723999/162582054-5a1c0462-0553-4b94-baa4-4441e4c70d66.png)
![image](https://user-images.githubusercontent.com/90723999/162582068-f2e7db9a-8073-4ba3-8df2-fc0a3a82e56a.png)


To access the hotel list from site itself instead of postman, here is a url suffix for unrestricted access: /hotel_list_unrestricted/ 
![image](https://user-images.githubusercontent.com/90723999/162582072-4f49d28e-f1f9-4943-b93f-1ba86abfd8ae.png)
![image](https://user-images.githubusercontent.com/90723999/162582074-434b3002-261c-41d2-aa78-610cb64b149d.png)


Post method to reserve a hotel(it is also secured by the API-KEY). url suffix is : /reserve_hotel/
![image](https://user-images.githubusercontent.com/90723999/162582086-30afd1c0-3cf1-4355-8563-4247d6712adc.png)

Once a hotel is booked/reserved it becomes unavailable: 
![image](https://user-images.githubusercontent.com/90723999/162582151-d86dfe46-3ebd-45d2-9838-7edae95ef309.png)
![image](https://user-images.githubusercontent.com/90723999/162582156-08333e2b-c3ff-4c6b-814e-d01f7ecc5b93.png)

Also, Certain checks have been done to deal with wrong hotel name or guest details: 
![image](https://user-images.githubusercontent.com/90723999/162582172-9780db04-02d3-4285-ab0f-e7418320fed9.png)
![image](https://user-images.githubusercontent.com/90723999/162582184-eb1f4f91-7a6b-461b-853b-c199410815bf.png)

Case when API-KEY is not used:
![image](https://user-images.githubusercontent.com/90723999/162582198-44294fc7-7735-4450-92bf-5bd80027dc71.png)


URL Error handling in cases when wrong url is entered:
![image](https://user-images.githubusercontent.com/90723999/162582205-1fa6291b-fb20-4c66-bd43-8f448d334961.png)


The data is saved in MySQL DB. The initial data is loaded from a hardcoded function(API secured). this GET url_suffix can be used for that /add_mock_hotels/
![image](https://user-images.githubusercontent.com/90723999/162582211-27ac959a-9590-4104-805a-aefd48a9c6ad.png)


To POST one hotel at a time you can use the following url suffix: /insert_hotel/
![image](https://user-images.githubusercontent.com/90723999/162582216-a9075a2e-310d-469b-803f-da262373d2a6.png)


Finally the database view:
![image](https://user-images.githubusercontent.com/90723999/162582223-d7cbef92-7616-4edd-baed-a7fb3fa66ff6.png)
![image](https://user-images.githubusercontent.com/90723999/162582227-df9ab591-540f-4da0-a2de-6fb32a5d0726.png)
![image](https://user-images.githubusercontent.com/90723999/162582232-ec30b3f3-187d-408e-8e79-f476fbc4fdde.png)

