eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3IiwidXNlcklkIjoiMTIzNDU2NyIsInVzZXJOYW1lIjoiSUZSUyBVU0VSIiwiY2lyY2xlQ29kZSI6IjEwMCIsImNpcmNsZU5hbWUiOiJXSE9MRSBCQU5LIExFVkVMIC0gRlJUIiwiY2FwYWNpdHkiOiI5MSIsInN0YXR1cyI6IkEiLCJxdWFydGVyRW5kRGF0ZSI6IjMwLzA5LzIwMjQiLCJwcmV2aW91c1F1YXJ0ZXJFbmREYXRlIjoiMzAvMDYvMjAyNCIsInByZXZpb3VzWWVhckVuZERhdGUiOiIzMC8wOS8yMDIzIiwiZmluYW5jaWFsWWVhciI6IjIwMjQtMjAyNSIsInF1YXJ0ZXIiOiJRMiIsImlhdCI6MTczNzAxMzM3MCwiZXhwIjoxNzM3MDk5NzcwfQ.GQ-X4urokY59UqL9YbbdzDzLQSvmnZqIScpQJI8hs7w



HEADER:ALGORITHM & TOKEN TYPE

{
  "alg": "HS256"
}
PAYLOAD:DATA

{
  "sub": "1234567",
  "userId": "1234567",
  "userName": "IFRS USER",
  "circleCode": "100",
  "circleName": "WHOLE BANK LEVEL - FRT",
  "capacity": "91",
  "status": "A",
  "quarterEndDate": "30/09/2024",
  "previousQuarterEndDate": "30/06/2024",
  "previousYearEndDate": "30/09/2023",
  "financialYear": "2024-2025",
  "quarter": "Q2",
  "iat": 1737013370,
  "exp": 1737099770
}
{
  "sub": "1234567",
  "userId": "1234567",
  "userName": "IFRS USER",
  "circleCode": "100",
  "circleName": "WHOLE BANK LEVEL - FRT",
  "capacity": "91",
  "status": "A",
  "quarterEndDate": "30/09/2024",
  "previousQuarterEndDate": "30/06/2024",
  "previousYearEndDate": "30/09/2023",
  "financialYear": "2024-2025",
  "quarter": "Q2",
  "iat": 1737013370,
  "exp": 1737099770
}
VERIFY SIGNATURE

HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  
your-256-bit-secret

) secret base64 encoded
