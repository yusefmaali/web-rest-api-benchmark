from sqlalchemy import Column
from sqlalchemy import create_engine
from sqlalchemy import ForeignKey
from sqlalchemy import Integer
from sqlalchemy import String
from sqlalchemy.ext.associationproxy import association_proxy
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship


Base = declarative_base()


class Country(Base):
    __tablename__ = "countries"

    id = Column(Integer, nullable=False, primary_key=True)
    name = Column(String(50), nullable=False)


class User(Base):
    __tablename__ = "users"

    id = Column(Integer, nullable=False, primary_key=True)
    first_name = Column(String(50), nullable=False)
    last_name = Column(String(50), nullable=False)
    email = Column(String(100), nullable=False)
    user_countries = relationship("UserCountry", cascade="all, delete-orphan")
    countries = association_proxy("user_countries", "country")


class UserCountry(Base):
    __tablename__ = "user_country_mapping"

    user_id = Column(Integer, ForeignKey("users.id"), primary_key=True)
    country_id = Column(Integer, ForeignKey("countries.id"), primary_key=True)

    user = relationship(User, lazy="joined")
    country = relationship(Country, lazy="joined")
