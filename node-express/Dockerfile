FROM node:10
RUN npm install pm2 -g
WORKDIR /code
COPY . .
RUN npm i
CMD [ "pm2-runtime", "npm", "--", "start" ]
