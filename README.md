# Spring Framework and AWS DynamoDB Session

## References

1. https://github.com/amazon-archives/aws-dynamodb-session-tomcat/releases
2. https://docs.aws.amazon.com/ko_kr/sdk-for-java/v2/developer-guide/welcome.html
3. https://docs.amazonaws.cn/sdk-for-java/v1/developer-guide/java-dg-tomcat-session-manager.html
4. https://aws.amazon.com/ko/blogs/developer/amazon-dynamodb-session-manager-for-apache-tomcat/

## Tomcat context configuration

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

2. Options Specified in context.xml

Below are the configuration attributes that you can use in the Manager element of your context.xml file:

- AwsAccessKey : Access key ID to use.
- AwsSecretKey : Secret key to use.
- AwsCredentialsFile : A properties file containing accessKey and secretKey properties with your AWS security credentials.
- Table : Optional string attribute. The name of the table used to store session data. The default is Tomcat_SessionState.
- RegionId : Optional string attribute. The AWS region in which to use DynamoDB. For a list of available AWS regions, see Regions and Endpoints in the Amazon Web Services General Reference.
- Endpoint : Optional string attribute; if present, this option overrides any value set for the Region option. The regional endpoint of the DynamoDB service to use. For a list of available AWS regions, see Regions and Endpoints in the Amazon Web Services General Reference.
- ReadCapacityUnits : Optional int attribute. The read capacity units to use if the session manager creates the table. The default is 10.
- WriteCapacityUnits : Optional int attribute. The write capacity units to use if the session manager creates the table. The default is 5.
- CreateIfNotExist : Optional Boolean attribute. The CreateIfNotExist attribute controls whether the session manager autocreates the table if it doesn't exist. The default is true. If this flag is set to false and the table doesn't exist, an exception is thrown during Tomcat startup.
