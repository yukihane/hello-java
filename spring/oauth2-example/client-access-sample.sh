#!/bin/bash

# それぞれ起動時ログに出力されている次の値
# Using generated security password: 5bd786c2-7cbb-4c32-9fc9-778de3f3d94b
pass=5bd786c2-7cbb-4c32-9fc9-778de3f3d94b

# security.oauth2.client.client-id = 38b63a6f-0afb-4ede-8ac5-48cddaa72cd4
clientid=38b63a6f-0afb-4ede-8ac5-48cddaa72cd4

# security.oauth2.client.client-secret = a427c2df-b0ff-4bc1-af5d-70b9962bc79b
clientsec=a427c2df-b0ff-4bc1-af5d-70b9962bc79b

curl $clientid:$clientsec@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=$pass -d scope=any
