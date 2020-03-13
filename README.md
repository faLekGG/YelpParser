# YelpParser

1. In the beginning I planned to implement everything initially parsing number of available pages
but the idea has failed because of yelp doesn't give results after 50th page. 
However, I've kept this type functionality for the future needs. 
In that case you need uncomment method getNumberOfPages() in CollectLinkParserImpl. 
Also there you can find detailed explanation of method use
2. Scheduling has been implemented by facilitating Java's SchedulerExecutorService
3. For DB I chose Mongo which is ideal candidate for parsing in my opinion. At least in current task
4. General flow of app: 
we parse all links for the contractor filter -> parse every contractor by link -> save everything in Mongo
5. Also I have tried make everything in multithreading but it's very tricky - 
you have to create a proxy for every request and make a delay of certain time to execute everything perfectly to not get banned by website.
In the end multithreading lose it appeal considering everything I've said above.
P.S. I was banned on every IP at home =)