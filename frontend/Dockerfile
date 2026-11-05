# Frontend estático servido con nginx
FROM nginx:1.27-alpine

# Estáticos en el web root (nginx.conf se copia aparte y se quita de aquí)
COPY . /usr/share/nginx/html
RUN rm -f /usr/share/nginx/html/nginx.conf

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
