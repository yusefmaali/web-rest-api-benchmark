from django.db import models
from rest_framework import serializers


class Country(models.Model):
    name = models.CharField(max_length=50)

    class Meta:
        db_table = 'countries'


class User(models.Model):
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    email = models.CharField(max_length=100)
    countries = models.ManyToManyField(Country, through='UserCountryMapping')

    class Meta:
        db_table = 'users'


class UserCountryMapping(models.Model):
    users = models.ForeignKey(User, db_column='user_id', on_delete=models.CASCADE)
    countries = models.ForeignKey(Country, db_column='country_id', on_delete=models.CASCADE)

    class Meta:
        db_table = 'user_country_mapping'


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'email', 'first_name', 'last_name', 'countries')
        depth = 1


class CountrySerializer(serializers.ModelSerializer):
    class Meta:
        model = Country
        fields = ('id', 'name')
