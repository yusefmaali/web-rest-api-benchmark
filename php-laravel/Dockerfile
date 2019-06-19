FROM php:7.2-apache
COPY 000-default.conf /etc/apache2/sites-available/
RUN sed -i 's/display_errors = Off/display_errors = On/g' /usr/local/etc/php/php.ini-production
RUN apt-get update -y && apt-get install -y openssl zip unzip git vim
RUN a2enmod rewrite
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer
RUN docker-php-ext-install pdo pdo_mysql mbstring
COPY --chown=www-data . .
RUN composer install
