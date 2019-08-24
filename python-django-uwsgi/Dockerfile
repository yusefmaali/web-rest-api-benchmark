FROM python:3
ENV PYTHONUNBUFFERED 1
RUN mkdir /code
WORKDIR /code
COPY requirements.txt /code/
RUN pip install -r requirements.txt
COPY . /code/
CMD uwsgi --socket=0.0.0.0:8000 --env DJANGO_SETTINGS_MODULE=app.settings --module=app.wsgi:application --processes=16
