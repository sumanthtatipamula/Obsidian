---------
1. Why do we need to learn about data systems ?
	 Increasingly, many applications now have such demanding or wide-ranging requirements that a single tool can no longer meet all of its data processing and storage needs. Instead, the work is broken down into tasks that can be performed efficiently on a single tool, and those different tools are stitched together using application code.
	 
 EG: Application-Managed caching layer 

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
							 - Provide sandbox to play around
						 - Test thoroughly at all levels, from unit tests to whole-system integration tests and manual tests.
							 - Automated testing
						 - Implement good management practices and training
						 - Allow quick and easy recovery from human errors, to minimize the impact in the case of a failure
							 - Ex: Could be using versioning system. In case of events of failure rollback to previous versions helps.
						 - Set up detailed and clear monitoring, such as performance metrics and error rates.
							 - Ex: Cloud watch metrics in AWS
	 - *Failure*:
		 - when the system as a whole stops providing the required service to the user.
 - **Scalability**
	 - As the system grows (in data volume or traffic volume) there should be reasonable ways of dealing with the growth.
 - **Maintainability**
	 - Overtime, many different people will work on the system, and they should all be able to work on it productively.
