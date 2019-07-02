#!/bin/bash

# spring.security.user.name プロパティ
# 未設定なら user
username=resource-owner-id

# spring.security.user.password プロパティ.
# 未設定なら起動時ログに出力される次の値
# Using generated security password: 5bd786c2-7cbb-4c32-9fc9-778de3f3d94b
pass=resource-owner-pass

# security.oauth2.client.client-id プロパティ
# 未設定なら起動時ログに出力される次の値
# security.oauth2.client.client-id = 38b63a6f-0afb-4ede-8ac5-48cddaa72cd4
clientid=clid

# security.oauth2.client.client-secret プロパティ
# 未設定なら起動時ログに出力される次の値
# security.oauth2.client.client-secret = a427c2df-b0ff-4bc1-af5d-70b9962bc79b
clientsec=clsec

curl $clientid:$clientsec@localhost:8080/oauth/token -d grant_type=password -d username=$username -d password=$pass -d scope=any
