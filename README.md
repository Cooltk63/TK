on login api hit ::  http://localhost:8080/auth/login
Payload ::
{
"username":"TUSHAR",
"password":"12345"
}
Response::
{
    "sub": "TUSHAR",
    "expiresIn": 900,
    "jti": "8dc74b87-8d4a-4f5e-8ac8-9e0cd0519487",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUVVNIQVIiLCJqdGkiOiI4ZGM3NGI4Ny04ZDRhLTRmNWUtOGFjOC05ZTBjZDA1MTk0ODciLCJpYXQiOjE3NTU1MDA3MTAsImV4cCI6MTc1NTUwMTYxMH0.6N3l7MOG4s0r4LYcUqmQt98evfs0Frn_ogPy491SSI8",
    "tokenType": "Bearer"
}

Console Output ::

2025-08-18T12:37:38.037+05:30  INFO 18644 --- [api-gateway] [ctor-http-nio-5] c.fincore.gateway.JwtUtil.HmacJwtUtil    : Generated exp in Seconds=2025-08-18T07:22:38.037816800Z
2025-08-18T12:37:38.037+05:30  INFO 18644 --- [api-gateway] [ctor-http-nio-5] c.fincore.gateway.JwtUtil.HmacJwtUtil    : Jti generated=d43f3d83-4ce4-439c-a8e2-41e3d4958a30
2025-08-18T12:37:38.037+05:30  INFO 18644 --- [api-gateway] [ctor-http-nio-5] c.f.gateway.Controller.AuthController    : Generated token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUVVNIQVIiLCJqdGkiOiJkNDNmM2Q4My00Y2U0LTQzOWMtYThlMi00MWUzZDQ5NThhMzAiLCJpYXQiOjE3NTU1MDA4NTgsImV4cCI6MTc1NTUwMTc1OH0.PTnFe23c7Bnb7QNhcesH6YlUK_m6UPEL9KM_sLAb3As
2025-08-18T12:37:38.038+05:30  INFO 18644 --- [api-gateway] [ctor-http-nio-5] c.f.gateway.Controller.AuthController    : Jti from claims claimsJws.getPayload().getId()d43f3d83-4ce4-439c-a8e2-41e3d4958a30
2025-08-18T12:37:38.038+05:30  INFO 18644 --- [api-gateway] [ctor-http-nio-5] c.f.g.Service.TokenSessionValidator      : Inside registerUserSession method USR:TUSHAR
2025-08-18T12:37:38.042+05:30  INFO 18644 --- [api-gateway] [ioEventLoop-5-1] c.f.g.Service.TokenSessionValidator      : ?? Found old session for user=TUSHAR -> blacklisting oldJti=4aa7d7ea-7631-4457-ab9f-75f8d12ba470
2025-08-18T12:37:38.045+05:30  INFO 18644 --- [api-gateway] [ioEventLoop-5-1] c.f.g.Service.TokenSessionValidator      : ? Blacklisted token jti=4aa7d7ea-7631-4457-ab9f-75f8d12ba470
2025-08-18T12:37:38.047+05:30  INFO 18644 --- [api-gateway] [ioEventLoop-5-1] c.f.g.Service.TokenSessionValidator      : ? Registered new session in Redis for user=TUSHAR jti=d43f3d83-4ce4-439c-a8e2-41e3d4958a30


On Secure Api Call::
 http://localhost:8080/secure/hello

 With Header
 
 Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUVVNIQVIiLCJqdGkiOiI4ZGM3NGI4Ny04ZDRhLTRmNWUtOGFjOC05ZTBjZDA1MTk0ODciLCJpYXQiOjE3NTU1MDA3MTAsImV4cCI6MTc1NTUwMTYxMH0.6N3l7MOG4s0r4LYcUqmQt98evfs0Frn_ogPy491SSI8

Response  ::

{
    "message": "Hello TUSHAR",
    "jti": "8dc74b87-8d4a-4f5e-8ac8-9e0cd0519487",
    "sub": "TUSHAR"
}
 


