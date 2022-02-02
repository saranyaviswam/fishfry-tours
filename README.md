# fishfry-tours
## Technologies used
- Spring boot
- JUnit Test
- H2 database
- Githib Actions
- AWS
- Sonar

## Components
- [x] REST Api Developed using Spring Boot, Java with H2 as the database. H2 is selected as it's in memory and comes out of the box. API Spec can be found [here](https://github.com/saranyaviswam/fishfry-tours/blob/main/api_spec_1.0.0.yaml)
- [x] CI/CD : Github Actions used to implement CI/CD pipeline. It involves 4 steps;
- Unit test execution using JUnit
- Sonar code quality analysis and code coverage using Jacoco [Sonar](https://sonarcloud.io/project/overview?id=saranyaviswam_fishfry-tours)
- Build
- Deploy to AWS : Elastic beanstalk was used as it's easy to integrate and deploy.
- [x] Test cases : JUnit test cases has been added for unit testing. Also added postman collection for testing [here](https://github.com/saranyaviswam/fishfry-tours/blob/main/postman_collection.json).

## Application URL
AWS Url for the application : http://fishfrytoursapideploy-env.eba-5ppauth7.us-east-1.elasticbeanstalk.com/

### Endpoints
For details on payload chcekout [Postman collection](https://github.com/saranyaviswam/fishfry-tours/blob/main/postman_collection.json) and [Api spec](https://github.com/saranyaviswam/fishfry-tours/blob/main/api_spec_1.0.0.yaml)
- GET Boats : http://fishfrytoursapideploy-env.eba-5ppauth7.us-east-1.elasticbeanstalk.com/api/v1/boats/
- Create Boat : http://fishfrytoursapideploy-env.eba-5ppauth7.us-east-1.elasticbeanstalk.com/api/v1/boats/
- GET Boats by status : http://fishfrytoursapideploy-env.eba-5ppauth7.us-east-1.elasticbeanstalk.com/api/v1/boats?status=Docked
Status values : Docked, Maintenance, Outbound To Sea, Inbound To Harbor
- Update boat : http://fishfrytoursapideploy-env.eba-5ppauth7.us-east-1.elasticbeanstalk.com/api/v1/boats/1/
- Get boat by id : http://fishfrytoursapideploy-env.eba-5ppauth7.us-east-1.elasticbeanstalk.com/api/v1/boats/1/
- Delete by id: http://fishfrytoursapideploy-env.eba-5ppauth7.us-east-1.elasticbeanstalk.com/api/v1/boats/1/




