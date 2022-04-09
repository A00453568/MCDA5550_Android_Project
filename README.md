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

Home Page: Screenshot (1238)

Functionalities:

Get the list of hotels present(will require API-KEY). url suffix: /hotel_list/ Screenshot (1239) Screenshot (1258) Same list irrespective of dates: Screenshot (1259) Cases when API-KEY is not set: Screenshot (1244) Screenshot (1239)

To access the hotel list from site itself instead of postman, here is a url suffix for unrestricted access: /hotel_list_unrestricted/ Screenshot (1260) Screenshot (1261)

Post method to reserve a hotel(it is also secured by the API-KEY). url suffix is : /reserve_hotel/ Screenshot (1246) Once a hotel is booked/reserved it becomes unavailable: Screenshot (1262) Screenshot (1249) Also, Certain checks have been done to deal with wrong hotel name or guest details: Screenshot (1248) Screenshot (1251) Case when API-KEY is not used: Screenshot (1247)

URL Error handling in cases when wrong url is entered: Screenshot (1252)

The data is saved in MySQL DB. The initial data is loaded from a hardcoded function(API secured). this GET url_suffix can be used for that /add_mock_hotels/ Screenshot (1242)

To POST one hotel at a time you can use the following url suffix: /insert_hotel/ Screenshot (1255)

Finally the database view: Screenshot (1257) Screenshot (1263) Screenshot (1264)
