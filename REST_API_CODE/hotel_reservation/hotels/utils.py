from .models import Reservations, Guest, Hotels


def check_hotel_availability(hotel_name):
    '''
    It will check whether the hotel exists or not and is it available for booking
    :param hotel_name:
    :return True/False:
    '''
    hotel_existence = Hotels.objects.filter(hotel_name=hotel_name).exists()
    if(hotel_existence):
        hotel_availability = Hotels.objects.get(hotel_name=hotel_name).availability
        return hotel_availability
    return hotel_existence

def check_guest_details(guest_list):
    '''
    It will check whether the guest_list is empty or not
    :param guest_list:
    :return True/False:
    '''
    if(guest_list==None or guest_list==[]):
        return False
    return True

def insert_guests(validated_data):
    '''
    All guest details received are inserted by this function.
    Reservation's confirmation_number is a foreign key in guest and so it will be attached to
    the objects before saving.
    :param validated_data:
    :return Guest objects:
    '''
    guests_list = validated_data.pop('guests_list')
    confirmation_number = validated_data.pop('confirmation_number')
    reservation = Reservations.objects.get(confirmation_number=confirmation_number)
    guests_obj_list = [Guest(**item, reservations=reservation) for item in guests_list]
    Guest.objects.bulk_create(guests_obj_list)
    return Guest.objects.filter(reservations_id=confirmation_number)


def change_hotel_availability(hotel_name):
    '''
    When a hotel is booked it's availability is changed.
    :param hotel_name:
    :return None:
    '''
    hotel = Hotels.objects.get(hotel_name=hotel_name)
    hotel.availability = False
    hotel.save()
