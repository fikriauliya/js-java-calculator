package jp.co.worksap.calculator.endpoint;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import jp.co.worksap.calculator.service.Evaluator;
import jp.co.worksap.calculator.service.ShuntingYard;


@Path("/evaluator")
public class EvaluatorEndPoint {
	@POST
	@Path("calculate")
	public String calculate(
			@FormParam("exp") String exp
			) {
		try {
			String res = Evaluator.calculate(ShuntingYard.parse(exp));
			return res;
		}
		catch (IllegalArgumentException ex) {
			return ex.getMessage();
		}
		catch (Exception ex) {
			return "Invalid";
		}
	}
}
