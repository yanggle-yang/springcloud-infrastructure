rem 为Eureka Client生成证书
keytool -genkeypair -alias client -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore client.p12 -validity 3650

rem 导出Eureka Client证书
keytool -export -alias client -file client.crt --keystore client.p12

rem 为Eureka Server生成证书
keytool -genkeypair -alias server -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore server.p12 -validity 3650

rem 导出Eureka Server证书
keytool -export -alias server -file server.crt --keystore server.p12

rem 将server.crt文件导入client.p12中，使client端信任server的证书
keytool -import -alias server -file server.crt -keystore client.p12

rem 将client.crt文件导入server.p12中，使server端信任client的证书
keytool -import -alias client -file client.crt -keystore server.p12