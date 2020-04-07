### VatBird - REST APIs to obtain VAT information
This microservice exposes simple REST APIs that you can call to retrieve VAT information and also check wether a VAT id is valid.
The validation checks run against the official VAT API from the european union. Since the EU's API is exposed as SOAP API you can use this service to have a clean and simple REST API.
The VAT rates are provided by this [database](https://github.com/kdeldycke/vat-rates/blob/master/vat_rates.csv) (BSD 2-Clause license).

_Currently we only support VAT ids from the european union. Feel free to contribute._

#### How to start the service
You can use this service for example as microservice in your kubernetes cluster.
If you just want to start working with it, you can use the following docker command:
```
docker run -d --name=vatbird -p 8080:8080 registry.gitlab.com/encircle360-oss/vatbird:latest
```

#### Example VAT ID Validation
```
curl -X GET http://localhost:8080/vat-ids/DE/313551330
{"countryCode":"DE","vatId":"313551330","valid":true}
```

#### Example getting vat rates
```
curl -X GET http://localhost:8080/vat-rates/DE
{"territoryCode":"DE","currencyCode":"EUR","description":"Germany (member state) standard VAT rate.","rate":0.19}
```
An openAPI spec with swagger-ui will be released soon.

This is open source software by [encircle360](https://encircle360.com).
Use on your own risk and for personal use.
