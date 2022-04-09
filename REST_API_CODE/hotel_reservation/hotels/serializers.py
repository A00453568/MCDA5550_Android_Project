from rest_framework import serializers
from .models import Hotels, Reservations, Guest


class HotelSerializers(serializers.ModelSerializer):
    class Meta:
        model = Hotels
        fields = ['hotel_name', 'price', 'availability']

class ReservationSerializers(serializers.ModelSerializer):
    class Meta:
        model = Reservations
        fields = ['confirmation_number', 'hotel_name', 'checkin', 'checkout']


class GuestSerializers(serializers.ModelSerializer):

    class Meta:
        model = Guest
        fields = ['guest_name', 'gender', 'reservations']




