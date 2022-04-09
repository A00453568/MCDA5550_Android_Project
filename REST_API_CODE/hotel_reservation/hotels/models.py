from django.db import models



class Hotels(models.Model):
    id = models.AutoField(primary_key=True)
    hotel_name = models.CharField(max_length=100, null=False)
    price = models.IntegerField()
    availability = models.BooleanField()

    def __str__(self):
        return self.hotel_name

    class Meta:
        db_table = 'Hotels'

class Reservations(models.Model):
    confirmation_number = models.AutoField(primary_key=True)
    hotel_name = models.CharField(max_length=100, null=False)
    checkin = models.DateField(max_length=20, null=False)
    checkout = models.DateField(max_length=20, null=False)

    def __str__(self):
        return self.confirmation_number

    class Meta:
        db_table = 'Reservations'

class Guest(models.Model):
    id = models.AutoField(primary_key=True)
    guest_name = models.CharField(max_length=100, null=False)
    gender = models.CharField(max_length=10, null=False)
    reservations = models.ForeignKey(Reservations, on_delete=models.CASCADE)

    def __str__(self):
        return self.guest_name

    class Meta:
        db_table = 'Guest'
