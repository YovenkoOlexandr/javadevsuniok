# About top n email domains

This was done using an additional data structure - treeset. The main structure for storing domains is a hashmap. There were still options to do without treeset - it was necessary to sort the hashmap at the end.
But I am not sure that it will be effective with several billions, but with several hundreds - yes, it will be faster.

# Auto parts compatibility
It all depends on...


If we only need storage and do not need to delete or update data - the best solution will be to look in the direction of big data. Save data in the file system (yes, we will use the cluster and backups for this, so the selection of this data will not be fast. But, for example, it is good for reporting)

If we have frequent deletion and addition of data (as described in the problem conditions). In this case, we should look towards microservices. We need several separate databases. And we have to distribute on our api gateway - to which service this data will be sent (and, accordingly, in which database it will be stored). There are several basic strategies...
Also, the conditions of the problem do not describe whether we need transactionality. Because here it will be a completely different story. And the approach used by Netflix or Facebook for horizontal scaling is no longer quite suitable here.

How would I build the architecture according to the conditions of the task?

First, the load balancer, then the api gateway, then several services with separate databases. The api gateway would decide which service to send data to next, for example by looking at the load of this service (perhaps we need to automatically launch one or more additional ones)