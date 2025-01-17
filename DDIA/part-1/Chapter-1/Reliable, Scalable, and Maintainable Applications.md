---------
[[Essential Terms]]

1. Why do we need to learn about data systems ?
	 Increasingly, many applications now have such demanding or wide-ranging requirements that a single tool can no longer meet all of its data processing and storage needs. Instead, the work is broken down into tasks that can be performed efficiently on a single tool, and those different tools are stitched together using application code.
	 
<mark style="background: #BBFABBA6;"> Ex: Application-Managed caching layer 
</mark>
[[Figure1-1.png]]

The factors that influence the design of a data system are
 - **Reliability**
	 -  The system should continue to work correctly(performing the correct function at the desired level of performance) even in the face of adversity(hard-ware or software faults).
	 - Typical expectations include:
		 - The application performs the function that the user expected.
		 - It can tolerate the user making mistakes or using the software in unexpected ways
			 - Eg: User might provide wrong inputs or try all possible ways to break the system.
		 -  Its performance is good enough for the required use case, under the expected load and volume.
			 - Ex: The amount of data varies from user to user, so for a particular threshold set the system should work seamlessly.
		 - The System prevents any unauthorized access and abuse.
			 - Ex: User should not be able to access his critical data without proper authentication.
			 - User should be seeing only his data, not others.
			 - Any Dos and DDoS attacks should be prevented.
	 - Systems that anticipate faults and cope with them are called fault-tolerant or resilient. Not all faults can be handled in the real world scenario.
	 - ***Fault:***
		 - defined as one component of the system deviating from its spec
		 - Types of Faults:
			 - ***Hardware Faults***
				 - These can be handled by using raid systems or by redundancy of hardware components
			 - *Software Faults*
				 - **Systematic Error**:
					 - These kinds of faults are hard to anticipate
					 - They tend to cause many more system failures than uncorrelated hardware faults
					 - Ex: A software bug that causes every instance of an application server to crash when given a particular bad input. For example, consider the leap second on **June 30, 2012,** that caused many applications to hang simultaneously due to a bug in the **Linux kernel**
					 - There are no proper solutions, but one could think of remedies such as
						 - Proper testing
						 - Process Isolation
						 - Allowing process to crash and restart.
						 - Monitoring and analyzing system behavior in production.
				 - ***Human Error***:
					 - one study of large internet services found that configuration errors by operators were the leading cause of outages, whereas hardware faults (servers or network) played a role in only 10–25% of outages
					 - How do we make our systems reliable, in spite of unreliable humans? 
						 - Design systems in a way that minimizes opportunities for error.
						 - Decouple the places where people make the most mistakes from the places where they can cause failures
							 - provide a sandbox to play around
						 - Test thoroughly at all levels, from unit tests to whole-system integration tests and manual tests.
							 - Automated testing
						 - Implement good management practices and training
						 - allow quick and easy recovery from human errors, to minimize the impact in the case of a failure
							 - Ex: Could be using versioning system. In case of events of failure, rollback to previous versions helps.
						 - Set up detailed and clear monitoring, such as performance metrics and error rates.
							 - Ex: Cloud watch metrics in AWS
 - **Scalability**
	 - As the system grows (in data volume or traffic volume) there should be reasonable ways of dealing with the growth.
	 - **Describing Load**
		 - Load is described by what is known as load parameters
			 - Parameters depends on the architecture of the system It could be:
				 - Number of requests per second to a web server 
				 - ratio of reads to writes in a database.
				 - Hit rate on a cache
	 - **Describing Performance** 
		 - Once load has been described on the system. Now one can investigate what happens when load increases. This can be looked in 2 ways
			 - When load ↑ and system resources remains unchanged, how is the performance of the system affected ? 
			 - when load ↑, how much resources needs to be increased in order for performance to remain same ?
		 - **Latency vs Response time**
			 - Latency : is the duration that a request is waiting to be handled.
			 - Response Time:  Service Time + Network Delays + Queueing Delays
				 - Even if same request is made again and again, there will be a difference in response times. Therefore, one can think of response time as not a single number, but as a distribution of values that you can measure.
				 - It is common to use average response time  = (Sum of  n values) / n; however, it doesn't tell how many users actually experienced that delay.
				 - So it is better to use *percentiles*. 
					 - If one take lists of response times and sort it from fastest to slowest, then the median is the halfway point:
					 - This makes the median a good metric if you want to know how long users typically have to wait: half of user requests are served in less than the median response time, and the other half take longer than the median. The median is also known as the _50th percentile_, and sometimes abbreviated as _p50_.
					 - **Note**:  that the median refers to a single request;
				 - In order to figure out how bad your outliers are, you can look at higher percentiles: the _95th_, _99th_, and _99.9th_ percentiles are common (abbreviated _p95_, _p99_, and _p999_).
					 - If the 95th percentile response time is 1.5 seconds, that means 95 out of 100 requests take less than 1.5 seconds, and 5 out of 100 requests take 1.5 seconds or more.
					 - These percentiles of response times are called *tail latencies*
				 - Why it is essential to consider tail latencies ?
					 - This is because the customers with the slowest requests are often those who have the most data on their accounts because they have made many purchases—that is, they’re the most valuable customers. It’s important to keep those customers happy by ensuring the website is fast for them:
				 - What is SLA ?
					 - An SLA may state that the service is considered to be up if it has a median response time of less than 200 ms and a 99th percentile under 1 s (if the response time is longer, it might as well be down), and the service may be required to be up at least 99.9% of the time.
			 
 - **Maintainability**
	 - Overtime, many different people will work on the system, and they should all be able to work on it productively.
	 - The three design principles for software system are 
		 - Operability
			 - Make it easy for operations teams to keep the system running smoothly.
		 - Simplicity
			 - Make it easy for new engineers to understand the system, by removing as much complexity as possible from the system. (Note this is not the same as simplicity of the user interface.)
		 - Evolvability
			 - Make it easy for engineers to make changes to the system in the future, adapting it for unanticipated use cases as requirements change. Also known as _extensibility_, _modifiability_, or _plasticity_.


## Summary 
1. We need to learn about data systems as many applications these days have a wide range of requirements which can't be met with a single tool.
2. The factors that influence data systems are
	1. Reliability 
	2. Scalability 
	3. Maintainability 
3. There is a difference between Fault and Failure, while the former means deviation in behavior; whereas, the latter means stopped working.
4. There are basically 2 types of faults 
	1. Hardware faults 
	2. Software faults 
		1. Systematic Errors 
		2. Human Errors
5. The common practice to measure response time is by doing averaging, but recommended is to go for percentiles.
6. There are 3 design principles 
	1. Operability
	2. Simplicity  
	3. Evolvability


 



