# Generated by Django 2.1.3 on 2022-03-01 12:46

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Hotel',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=200)),
                ('price', models.IntegerField()),
            ],
            options={
                'db_table': 'Hotel',
            },
        ),
    ]
