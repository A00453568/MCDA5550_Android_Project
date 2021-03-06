# Generated by Django 2.1.3 on 2022-04-07 21:21

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('hotels', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Guest',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('guest_name', models.CharField(max_length=100)),
                ('gender', models.CharField(max_length=10)),
            ],
            options={
                'db_table': 'Guest',
            },
        ),
        migrations.CreateModel(
            name='Hotels',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('hotel_name', models.CharField(max_length=100)),
                ('price', models.IntegerField()),
                ('availability', models.BooleanField()),
            ],
            options={
                'db_table': 'Hotels',
            },
        ),
        migrations.CreateModel(
            name='Reservations',
            fields=[
                ('confirmation_number', models.AutoField(primary_key=True, serialize=False)),
                ('hotel_name', models.CharField(max_length=100)),
                ('checkin', models.DateField(max_length=20)),
                ('checkout', models.DateField(max_length=20)),
            ],
            options={
                'db_table': 'Reservations',
            },
        ),
        migrations.DeleteModel(
            name='Hotel',
        ),
        migrations.AddField(
            model_name='guest',
            name='reservations',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='hotels.Reservations'),
        ),
    ]
