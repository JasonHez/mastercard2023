The two classes 'TransactionAnalysis' and 'Transaction' represent the JSON objects our API works with:
	'TransactionAnalysis' contains the card number, transaction amount, a boolean representing whether the transaction was approved or not, and the number of times the card was used in the last seven days -- this is what our API responds with.
	'Transaction' holds just the card number and the number of times the card was used in the last seven days -- this is what our API ingests with requests to the 'analyzeTransaction' endpoint.

Our API is structured such that requests are handled by a RestController that passes them to a service where all the business logic is handled ('AnalysisService').
	Client -> RestController -> AnalysisService

API endpoint:
	localhost:8080/api/v1/analyzeTransaction
		|-> expects a transaction JSON object following the schema outlined in the exercise instructions
		|-> responds with a transaction analysis JSON object

Metrics endpoints:
	localhost:8080/actuator/prometheus
	localhost:8080/actuator/health
	localhost:8080/actuator/metrics

Some improvements that could be made (but seemed beyond the scope of this exercise):
	- monetary values should probably be handled by something more accurate (i.e. BigInteger)
	- tests should be more comprehensive and robust
	- API could be reactive instead of synchronous