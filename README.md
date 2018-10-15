# RICOH THETA Messaging Plug-in for LINE Messaging API

by [ue ue](https://qiita.com/ueue)

![line plugin](docs/img/line-plugin.png)

![line api](docs/img/line-api.png)

    curl -v -X POST https://api.line.me/v2/bot/message/push \
    -H 'Content-Type:application/json' \
    -H 'Authorization: Bearer <Access Token>' \
    -d '{
        "to": "<User ID>",
        "messages":[
            {
                "type":"text",
                "text":"I love you"
            },
            {
                "type":"text",
                "text":"I love you so much"
            },
            {
              "type": "sticker",
              "packageId": "1",
              "stickerId": "409"
            }
        ]
    }'

![line note](docs/img/line-note.png)

![line response](docs/img/line-response.png)

![Analytics](https://ga-beacon.appspot.com/UA-73311422-5/line-plugin)
