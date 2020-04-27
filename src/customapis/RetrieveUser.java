package customapis;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.*;
import com.provar.plugins.forcedotcom.core.model.service.ISfOrgMetadataService;

@TestApi( title="Retrieve User"
        , summary=""
        , remarks=""
        , iconBase=""
        , defaultApiGroups={"Custom"}
        )
@TestApiParameterGroups(parameterGroups={
	    @TestApiParameterGroup(groupName="inputs", title="Inputs"),
	    @TestApiParameterGroup(groupName="result", title="Result"),
	    })
public class RetrieveUser {
    
	@TestApiParameter(seq = 1, summary = "Metadata", remarks = "", mandatory = true, parameterGroup = "inputs")
	@ConnectionType(variableName = true)
	public ISfOrgMetadataService orgMetadataService;
    
    @TestApiParameter(seq=2, 
            summary="The second parameter's summary.",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String connectionName;
    
    
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
    public void execute() throws IOException {    
        
    	// Put our implementation logic here.
    	testLogger.info("Hello from " + this.getClass().getName());
    	String url = testExecutionContext.getConnectionDetails(connectionName).getUrlString();
    	String[] arrurl = url.split(";");
    	String[] finaluser = arrurl[0].split("=");

        // Store the result (if appropriate).
    	String dummyResult = finaluser[1];
        testExecutionContext.setValue(resultName, dummyResult, resultScope);
        
    }
    
}
