/*******************************************************************************
 * Copyright (c) 2015 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package com.github.rustdt.tooling.ops;

import java.nio.file.Path;

import melnorme.lang.tooling.toolchain.ops.FindDefinitionResult;
import melnorme.lang.tooling.toolchain.ops.IToolOperationService;
import melnorme.lang.utils.parse.StringCharSource;
import melnorme.utilbox.core.CommonException;
import melnorme.utilbox.fields.validation.ValidatedValueSource;
import melnorme.utilbox.misc.Location;

public class RacerFindDefinitionOperation extends RacerOperation<FindDefinitionResult> {
	
	protected final int offset;
	
	public RacerFindDefinitionOperation(IToolOperationService toolRunner, 
			ValidatedValueSource<Path> racerPath, ValidatedValueSource<Location> sdkSrcLocation, 
			String source, boolean useSubstituteFile, int offset, int line_0, int col_0, Location fileLocation) {
		super(toolRunner, racerPath, sdkSrcLocation, source, useSubstituteFile,
			getArguments("find-definition", line_0, col_0, fileLocation));
		
		this.offset = offset;
	}
	
	@Override
	public FindDefinitionResult parseProcessOutput(StringCharSource output) throws CommonException {
		return createRacerOutputParser(offset).parseResolvedMatch(output.getSource());
	}
	
	@Override
	protected String getToolProcessName() {
		return getToolName();
	}
	
}