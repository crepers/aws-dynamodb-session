# Spring Framework and AWS DynamoDB Session

## References
1. https://github.com/amazon-archives/aws-dynamodb-session-tomcat/releases
2. http://cloudsemina.com/index.php?mid=aws&document_srl=243
3. https://docs.aws.amazon.com/ko_kr/sdk-for-java/v2/developer-guide/welcome.html
4. https://docs.amazonaws.cn/sdk-for-java/v1/developer-guide/java-dg-tomcat-session-manager.html
5. https://aws.amazon.com/ko/blogs/developer/amazon-dynamodb-session-manager-for-apache-tomcat/

## Tomcat
1. context.xml
```
<Context>
  <WatchedResource>WEB-INF/web.xml</WatchedResource>
  <Manager className="com.amazonaws.services.dynamodb.sessionmanager.DynamoDBSessionManager"
    awsAccessKey=""
    awsSecretKey=""
    table=""
    regionId="ap-northeast-2"
    endpoint="dynamodb.ap-northeast-2.amazonaws.com"
    createIfNotExist="true" />
</Context>
```