package customapis;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.provar.core.model.base.api.INamedValue;
import com.provar.core.model.base.api.ValueScope;
import com.provar.core.model.base.java.NamedValueImpl;
import com.provar.core.model.base.java.NamedValueListValueImpl;
import com.provar.core.model.base.java.PrimitiveValueImpl;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.TestApi;
import com.provar.core.testapi.annotations.TestApiExecutor;
import com.provar.core.testapi.annotations.TestApiParameter;
import com.provar.core.testapi.annotations.TestApiParameterGroup;
import com.provar.core.testapi.annotations.TestApiParameterGroups;
import com.provar.core.testapi.annotations.TestExecutionContext;
import com.provar.core.testapi.annotations.TestLogger;

@TestApi(title = "List Compare Custom", summary = "", remarks = "", iconBase = "", defaultApiGroups = {
		"My Test APIs" })
@TestApiParameterGroups(parameterGroups = { @TestApiParameterGroup(groupName = "inputs", title = "Inputs"),
		@TestApiParameterGroup(groupName = "result", title = "Result"), })
public class ListCompareCustom {

	@TestApiParameter(seq = 1, summary = "The first parameter's summary.", remarks = "", mandatory = true, parameterGroup = "inputs")
	public List ExcelList;

	@TestApiParameter(seq = 2, summary = "The second parameter's summary.", remarks = "", mandatory = true, parameterGroup = "inputs")
	public List UIList;

	@TestApiParameter(seq = 10, summary = "The name that the result will be stored under.", remarks = "", mandatory = true, parameterGroup = "result")
	public String resultName;

	@TestApiParameter(seq = 11, summary = "The lifespan of the result.", remarks = "", mandatory = true, parameterGroup = "result", defaultValue = "Test")
	public ValueScope resultScope;

	@TestApiParameter(seq = 11, summary = "The lifespan of the result.", remarks = "", mandatory = true, parameterGroup = "result", defaultValue = "Test")
	public ValueScope filePath;

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

		// Put our implementation logic here
		String result = "false";

		List excelDataList = new ArrayList<>();

		NamedValueListValueImpl excelData = (NamedValueListValueImpl) ExcelList.get(0);

		for (INamedValue el : excelData.getNamedValues()) {
			excelDataList.add(((PrimitiveValueImpl) el.getValue()).getValue());
		}

		testLogger.info("Excel List:" + excelDataList);
		testLogger.info("UI List:" + UIList);

		if (excelDataList.containsAll(UIList)) {
			testLogger.info("Excel List contains UI List");
			result = "true";
		}

		if (UIList.containsAll(excelDataList)) {

			testLogger.info("UI List contains Excel List");
			result = "true";
		}

		testExecutionContext.setValue(resultName, result, resultScope);

	}

}
