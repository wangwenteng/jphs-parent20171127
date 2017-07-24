package com.jinpaihushi.security;

import java.util.Map;



public interface I_ConfigurationParameterHandler {
	/** The name of the addConfigurationParameter() method. */
    String ADD_PARAMETER_METHOD = "addConfigurationParameter";

    /** The name of the initConfiguration() method. */
    String INIT_CONFIGURATION_METHOD = "initConfiguration";

    /**
     * Adds a configuration parameter to this parameter configurable class instance.<p>
     * 
     * @param paramName the name of the parameter 
     * @param paramValue the value for the parameter
     */
    void addConfigurationParameter(String paramName, String paramValue);

    /**
     * Returns the configuration of this parameter configurable class instance,
     * or <code>null</code> if the class does not need to be configured.<p>
     * 
     * All elements in the configuration are key, value String pairs,
     * set using the {@link #addConfigurationParameter(String, String)} method
     * during initialization of the loader.<p>
     * 
     * Implementations will (should) not to return a direct reference to
     * the internal configuration but just a copy of it, to avoid 
     * unwanted external manipulation.<p>
     * 
     * @return the configuration of this resource loader, or <code>null</code>
     */
    Map<String, String> getConfiguration();

    /**
     * Initializes a configuration after all parameters have been added.<p>
     * 
     * @throws CmsConfigurationException if something goes wrong
     */
    void initConfiguration() throws Exception;
}
