package customapis;


import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Logger;

import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.*;

@TestApi( title="My Test Api"
        , summary=""
        , remarks=""
        , iconBase=""
        , defaultApiGroups={"Custom"}
        )
@TestApiParameterGroups(parameterGroups={
	    @TestApiParameterGroup(groupName="inputs", title="Inputs"),
	    @TestApiParameterGroup(groupName="result", title="Result"),
	    })
public class MyTestApi {
    
    
   

    @TestApiParameter(seq=10, 
            summary="The name that the result will be stored under.",
            remarks="",
            mandatory=true,
            parameterGroup="result")
    public String resultName;

    @TestApiParameter(seq=11, 
            summary="The lifespan of the result.",
            remarks="",
            mandatory=true,
            parameterGroup="result",
            defaultValue="Test")
    public ValueScope resultScope;

    /** 
     * Used to write to the test execution log.
     */
    @TestLogger
    public Logger testLogger;
    
    /** 
     * Provides access to facilities, mainly to set and get variable values.
     */
    @TestExecutionContext
    public ITestExecutionContext testExecutionContext;
    
    @TestApiExecutor
    public void execute() {
    	Random random = new Random();
    	int minDay = (int) LocalDate.of(2019, 1, 1).toEpochDay();
    	int maxDay = (int) LocalDate.of(2019, 9, 1).toEpochDay();
    	long randomDay = minDay + random.nextInt(maxDay - minDay);
        
    	LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        String date=randomDate.toString();
    	// Put our implementation logic here.
    	testLogger.info("Hello from " + this.getClass().getName());
        testLogger.info(date);
        // Store the result (if appropriate).
    	//String dummyResult = this.getClass().getName() + " result";
        testExecutionContext.setValue(resultName, date, resultScope);
        
    }
    
}
