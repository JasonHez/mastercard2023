To build the project with maven:
		mvn clean install

To run the built .jar file as a Docker container:
		docker build -t mcapp .
		docker run -p 8080:8080 mcapp

The two classes 'TransactionAnalysis' and 'Transaction' represent the JSON objects our API works with:
	'TransactionAnalysis' contains the card number, transaction amount, a boolean representing whether the transaction was approved or not, and the number of times the card was used in the last seven days -- this is what our API responds with.
	'Transaction' holds just the card number and the number of times the card was used in the last seven days -- this is what our API ingests with requests to the 'analyzeTransaction' endpoint.

Our API is structured such that requests are handled by a RestController that passes them to a service where all the business logic is handled ('AnalysisService').
	Client -> RestController -> AnalysisService
					|-> ExternalService
ExternalService is used to abstract the would-be external API to get the number of times a card was used in the last seven days. While I was working on this exercise the random number generator api was down, so I added in a failsafe that returns equivalent results (i.e. a list of seven random ints in the range 0-12). Abstracting this layer also allows us to overload the service call with a function allowing us to remove random numbers from the pipeline altogether, enabling us to reliably test our main service.

API endpoint:
	localhost:8080/api/v1/analyzeTransaction
		|-> expects a transaction JSON object following the schema outlined in the exercise instructions
		|-> responds with a transaction analysis JSON object

Metrics endpoints:
	localhost:8080/actuator/prometheus
	localhost:8080/actuator/health
	localhost:8080/actuator/metrics

The JaCoCo plugin creates a coverage report that you can view by building the project, then opening '.\target\site\jacoco\index.html'

Some improvements that could be made (but seemed beyond the scope of this exercise):
	- monetary values should probably be handled by something more accurate (i.e. BigInteger)
	- tests should be more comprehensive and robust
	- API could be reactive instead of synchronous
