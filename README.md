server {
    listen 80;

    server_name yourdomain.com;

    location / {
        root /usr/share/nginx/html;
        index index.html;
        try_files $uri /index.html;
    }

    # Redirect SSO POST request to Express backend
    location /sso-callback {
        proxy_pass http://localhost:9000/sso-callback;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}