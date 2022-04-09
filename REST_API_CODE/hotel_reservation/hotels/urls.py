from django.urls import path
from . import views


urlpatterns = [
    path('home/', views.home),
    path('', views.home),
    path('reserve_hotel/', views.reservationConfirmation,),
    path('hotel_list/', views.getListOfHotels),
    path('hotel_list_unrestricted/', views.getUnrestrictedListOfHotels),
    path('insert_hotel/', views.insertHotel),
    path('add_mock_hotels/', views.addMockHotels),
]
