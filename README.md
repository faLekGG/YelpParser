# YelpParser

1. In the beginning I planned to implement everything initially parsing number of available pages
but the idea has failed because of yelp that doesn't give results after 50th page. 
However, I've kept this type functionality for the future needs. 
In that case you need uncomment method getNumberOfPages() in CollectLinkParserImpl. 
Also there you can find detailed explanation of method use

2. Scheduling has been implemented by facilitating Java's SchedulerExecutorService

3. For DB I chose Mongo which is an ideal candidate for parsing in my opinion. At least in current task

4. General flow of the app:  we parse all links for the contractor filter -> parse every contractor by link -> save everything in Mongo

5. Also I have tried make everything in multithreading but it's a very tricky - 
you have to create a proxy for every request and make a delay of certain time to execute everything perfectly to not get banned by website.
In the end multithreading lose it appeal considering everything I've said above. P.S. I was banned on every IP at home =)

6. The app was packed into docker containers for easier use. To run it on your local machine you need docker and docker-compose.
To execute everything simply run a command: <i>docker-compose up</i>  
To check results in docker:
<ol>
<li> docker exec -it mongo-database bash</li>
<li> Start an another terminal and execute: mongo -u vhalaveika -p 123 (Or any other user you are going to set up in env variables)</li>
<li> After you connected to mongo shell <br>
3.1 use yelp
  <br>
3.2 switched to db yelp
  <br>
3.3 db.yelpcollection.find()
  <br>
</li>
Eventually you see all parsed results in JSON
<li> docker-compose down - to  stop and remove containers, networks, images, and volumes </li>
</ol>     
<p>If you don't want to parse everything simply change property of INITIAL_STEP or LAST_STEP in OtherConfigurationsOfApp
Attention: those properties must be multiple to 20 (0, 20, 40, 60 up to 980) because of url building for yelp </p>
  
                                               
                                               
