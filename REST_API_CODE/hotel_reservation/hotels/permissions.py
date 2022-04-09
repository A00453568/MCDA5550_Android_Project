from django.conf import settings

from rest_framework.permissions import BasePermission

# It will check whether the API-KEY matches with the application's secret key
class API_Auth(BasePermission):
    def has_permission(self, request, view):
        # API_KEY should be in request headers to authenticate requests
        api_key_secret = request.META.get('HTTP_API_KEY')
        return api_key_secret == settings.SECRET_KEY