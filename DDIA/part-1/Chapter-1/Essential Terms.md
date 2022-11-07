----
1. Vertical Scaling
	1. It is also called scale up  
	2. moving to a more powerful machine
2. Horizontal Scaling 
	1. It is  also called scaling out
	2. distributing load across multiple smaller machines(Shared-Nothing Architecture)
3. Reliability
	1. the system should continue to work correctly(performing the correct function at the desired level of performance) even in the face of adversity(hard-ware or software faults)
4. Scalability:
	1. As the system grows (in data volume or traffic volume) there should be reasonable ways of dealing with the growth.
5. Fault:
	1. defined as one component of the system deviating from its spec
6. Failure:
	1. when the system as a whole stops providing the required service to the user.
7. Latency
	1. Duration that a request is waiting to be handled
8. Response Time:
	1. Service Time + Network Delays + Queuing Delays 
9. Percentiles 
	1. p50: If one take lists of response times and sort it from fastest to slowest,  take the midpoint(which is median)
	2. p95,p99,p999 are called *tail latencies*
	3. If the 95th percentile response time is 1.5 seconds, that means 95 out of 100 requests take less than 1.5 seconds, and 5 out of 100 requests take 1.5 seconds or more.
10. Elastic Systems:
	1. Those systems that can automatically add computing resources when they detect a load increase 