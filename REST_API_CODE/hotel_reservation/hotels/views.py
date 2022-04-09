from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes
from .permissions import API_Auth
from .serializers import *
from .utils import *
import MySQLdb

@api_view(["GET"])
def home(request):
    '''
    This is the index or the home page.
    :param request:
    :return Home page:
    '''
    return HttpResponse("""<h1>Welcome User</h1> <h2>The following functionalities are available:</h2> 
    <ul><li><a href='/hotel_list/'>Get List of hotels</a> : endpoint is /hotel_list/</li> 
    <li><a href='/hotel_list_unrestricted/'>Get Unrestricted List of hotels</a> : endpoint is /hotel_list_unrestricted/</li> 
    <li>For Post use this endpoint: /reserve_hotel/</li> 
    <li>To load mock hotels data use this GET request: /add_mock_hotels/</li></ul>""")


@api_view(["GET"])
@permission_classes((API_Auth, ))
def getListOfHotels(request):
    '''
    This functions fetches the list of all hotels. This API is key secured.
    :param request:
    :return List of Hotels:
    '''
    try:
        print(request.query_params)
        hotel_list = Hotels.objects.all()
        hotelSerializer = HotelSerializers(hotel_list, many=True)
        return Response({"hotels_list":hotelSerializer.data})
    except Exception:
        return Response({"Message": "List of Hotels is empty"})

@api_view(["GET"])
def getUnrestrictedListOfHotels(request):
    '''
    This functions fetches the list of all hotels. This API is key unsecured.
    :param request:
    :return List of Hotels:
    '''
    try:
        hotel_list = Hotels.objects.all()
        hotelSerializer = HotelSerializers(hotel_list, many=True)
        return Response({"hotels_list":hotelSerializer.data})
    except Exception:
        return Response({"Message": "List of Hotels is empty"})


@api_view(["POST"])
@permission_classes((API_Auth, ))
def reservationConfirmation(request):
    '''
    This function helps in reserving a hotel. It will check the hotel's availability
    and validate the guest details and then insert data.
    This API is key secured.
    :param request:
    :return confirmation number:
    '''
    try:
        request_data = request.data
        if(not check_hotel_availability(request_data.get("hotel_name"))):
            return Response({"Message": "Sorry! this Hotel is unavailable at this time. "})

        if(not check_guest_details(request_data.get("guests_list"))):
            return Response({"Message": "Need to have at least one guest's details"})
        serialize_reservation_data = ReservationSerializers(data=request_data)
        if serialize_reservation_data.is_valid():
            serialize_reservation_data.save()

        reservation_number = serialize_reservation_data.data.get("confirmation_number")
        request_data["confirmation_number"] = str(reservation_number)
        guests = insert_guests(request_data)
        change_hotel_availability(request_data.get("hotel_name"))

        return Response({"confirmation_number": str(reservation_number)})

    except Exception as e:
        print(str(e))
        return Response({"Message": "An unknown error has occurred. Please try again later."})



@api_view(["POST"])
@permission_classes((API_Auth, ))
def insertHotel(request):
    '''
    This is an additional function for adding hotels.
    This API is key unsecured.
    :param request:
    :return:
    '''
    try:
        request_data = request.data
        if (Hotels.objects.filter(hotel_name=request_data['hotel_name']).exists()):
            return Response({"Message": "Hotel with this Name already exists"})
        serialize_request_data = HotelSerializers(data=request_data)
        if serialize_request_data.is_valid():
            serialize_request_data.save()
        return Response({"Message": "Hotel Added Successfully"})
    except Exception:
             return Response({"Message": "An unknown error has occurred. Please try again later."})

@api_view(["GET"])
@permission_classes((API_Auth, ))
def addMockHotels(request):
    '''
    This is an additional function for adding mocks hotels.
    This API is key unsecured.
    :param request:
    :return:
    '''
    conn = MySQLdb.connect(host="hotelreservation.cyhdgor7dxwe.us-west-2.rds.amazonaws.com", user="root",
                           passwd="admin1234", db="rest_api_project")
    cursor = conn.cursor()
    data = [('Comfort Inn', 500, 1), ('Holiday Inn', 450, 1), ('Hotel Inn', 350, 1), ('Country Inn', 250, 1),
            ('JW Marriott', 150, 1), ('Oberoi Hotel', 250, 1), ('Hotel Taj', 350, 1), ('Radisson Blue', 405, 1),
            ('Hyatt Regency', 650, 1), ('Hilton Hotel', 455, 1), ('The Mirage', 250, 1), ('Park Plaza', 505, 1),
            ('Cecil Hotel', 125, 1), ('Dunbar Hotel', 205, 1), ('Regency Plaza Suites', 355, 1)]
    stmt = "INSERT INTO rest_api_project.Hotels (hotel_name, price, availability) VALUES (%s, %s, %s)"
    cursor.executemany(stmt, data)
    conn.commit()
    cursor.close()
    conn.close()
    return Response({"Message": "Mock Hotels Added Successfully"})

def handler404(request, *args):
    '''
    Over ridden the default 404 handler with this custom one.
    :param request:
    :param args:
    :return custom output:
    '''
    return HttpResponse("<html><body> <h2>Error 404 Page</h2> <a href='/'>Goto Home Page</a><body></html>")


def handler500(request, *args):
    '''
    Over ridden the default 500 handler with this custom one.
    :param request:
    :param args:
    :return custom output:
    '''
    return HttpResponse("<html><body> <h2>Error 500 Page</h2> <a href='/'>Goto Home Page</a><body></html>")




