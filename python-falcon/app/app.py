import falcon
import json
from sqlalchemy.orm import Session
from sqlalchemy.orm import joinedload
from random import randint
from .model import Country, User, UserCountry


class HelloWorldResource(object):
    def on_get(self, req, resp):
        resp.status = falcon.HTTP_200
        resp.body = json.dumps({ 'hello':'world' })


class ComputeResource(object):
    def on_get(self, req, resp):
        x = 0
        y = 1

        max = 10000 + randint(0, 500)

        for i in range(max):
            z = x + y
            x = y
            y = z

        resp.status = falcon.HTTP_200
        resp.body = json.dumps({ 'status':'done' })


class CountriesResource(object):
    def on_get(self, req, resp):
        countries = self.session.query(Country)

        resp.status = falcon.HTTP_200
        resp.body = json.dumps([{ 'id': country.id, 'name': country.name } for country in countries])


class UsersResource(object):
    def on_get(self, req, resp):
        users = self.session.query(User) \
            .options(joinedload(User.user_countries)) \
            .join("user_countries", "country") \
            .filter(Country.name == "France")

        resp.status = falcon.HTTP_200
        resp.body = json.dumps([{ 'id': user.id, 'email': user.email, 'first_name': user.first_name, 'last_name': user.last_name, 'countries': [{ 'id': country.id, 'name': country.name } for country in user.countries] } for user in users])
