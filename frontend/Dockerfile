
FROM node:18-alpine as build

WORKDIR /app

COPY booking-frontend/package*.json ./

RUN npm install

COPY booking-frontend/ .

RUN npm run build

FROM node:18-alpine 

RUN npm install -g serve

COPY --from=build /app/build ./build

EXPOSE 3000

CMD ["serve", "-s", "build", "-l", "3000"]